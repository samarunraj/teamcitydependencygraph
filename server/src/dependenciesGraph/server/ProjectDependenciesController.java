package dependenciesGraph.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import jetbrains.buildServer.BuildType;
import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import jetbrains.buildServer.web.util.WebUtil;
import org.springframework.web.servlet.ModelAndView;
import dependenciesGraph.common.Util;

/**
 * Example custom page controller
 */
public class ProjectDependenciesController extends BaseController {
    private PluginDescriptor pluginDescriptor;
    private final WebControllerManager controllerManager;
    private final ProjectManager projectManager;

    public ProjectDependenciesController(PluginDescriptor pluginDescriptor, WebControllerManager controllerManager, ProjectManager projectManager) {
        this.pluginDescriptor = pluginDescriptor;
        this.controllerManager = controllerManager;
        this.projectManager = projectManager;
        // this will make the controller accessible via <teamcity_url>\dependenciesGraph.html
        controllerManager.registerController("/dependenciesGraph.html", this);
    }

    @Override
    protected ModelAndView doHandle(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        String projectId = request.getParameter("projectId");
        SProject project = projectManager.findProjectById(projectId);

        NodeModel[] nodes = ComputeDependencies(project);

        JsonHierarchicalStreamDriver driver = new JsonHierarchicalStreamDriver();
        XStream xs = new XStream(driver);

        String json = xs.toXML(nodes);

        ModelAndView view = new ModelAndView(pluginDescriptor.getPluginResourcesPath("dependenciesGraph.jsp"));

        String errorImageUrl = WebUtil.getPathWithoutContext(request, "img/buildStates/error.gif");
        String successImageUrl = WebUtil.getPathWithoutContext(request, "img/buildStates/success.gif");

        final Map model = view.getModel();
        model.put("name", Util.NAME);
        model.put("projectName", project.getName());
        model.put("errorImageUrl", errorImageUrl);
        model.put("successImageUrl", successImageUrl);
        model.put("json", json);
        return view;
    }

    private NodeModel[] ComputeDependencies(SProject project) {
        Map<BuildType, List<BuildType>> buildTypesWithDependencies = new HashMap<BuildType, List<BuildType>>();

        for (SBuildType buildType : project.getBuildTypes()) {
            ComputeDependencies(buildType, buildTypesWithDependencies);
        }

        List<NodeModel> nodes = new ArrayList<NodeModel>();

        for (Map.Entry<BuildType, List<BuildType>> buildTypeListEntry : buildTypesWithDependencies.entrySet()) {
            nodes.add(CreateNodeModel(buildTypeListEntry));
        }

        return nodes.toArray(new NodeModel[0]);
    }

    private NodeModel CreateNodeModel(Map.Entry<BuildType, List<BuildType>> buildTypeListEntry) {
        AdjacencyModel[] adjacencies = ComputeAdjacencies(buildTypeListEntry);

        return ComputeNodeModel(buildTypeListEntry.getKey(), adjacencies);
    }

    private NodeModel ComputeNodeModel(BuildType buildType, AdjacencyModel[] adjacencies) {
        return new NodeModel(buildType.getBuildTypeId(), buildType.getName(), adjacencies);
    }

    private AdjacencyModel[] ComputeAdjacencies(Map.Entry<BuildType, List<BuildType>> buildTypeListEntry) {
        List<AdjacencyModel> adjacencies = new ArrayList<AdjacencyModel>();

        for (BuildType adjacency : buildTypeListEntry.getValue()) {
            adjacencies.add(ComputeAdjacency(adjacency, buildTypeListEntry.getKey()));
        }

        return adjacencies.toArray(new AdjacencyModel[0]);
    }

    private AdjacencyModel ComputeAdjacency(BuildType adjacency, BuildType key) {
        return new AdjacencyModel(adjacency.getBuildTypeId(), key.getBuildTypeId());
    }

    private void ComputeDependencies(SBuildType buildType, Map<BuildType, List<BuildType>> buildTypesWithDependencies) {
        if(buildTypesWithDependencies.containsKey(buildType) == false) {
            buildTypesWithDependencies.put(buildType, new ArrayList<BuildType>());
        }

        for (SBuildType parent : buildType.getChildDependencies()) {
            if(buildTypesWithDependencies.containsKey(parent) == false)
                ComputeDependencies(parent, buildTypesWithDependencies);
        }

        for (SBuildType child : buildType.getDependencyReferences()) {
            buildTypesWithDependencies.get(buildType).add(child);

            if(buildTypesWithDependencies.containsKey(child) == false)
                ComputeDependencies(child, buildTypesWithDependencies);
        }
    }
}
