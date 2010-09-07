package dependenciesGraph.server;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jetbrains.buildServer.BuildType;
import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.springframework.web.servlet.ModelAndView;
import dependenciesGraph.common.Util;

/**
 * Example custom page controller
 */
public class ProjectDependenciesController extends BaseController {
    private PluginDescriptor myPluginDescriptor;
    private final ProjectManager projectManager;

    public ProjectDependenciesController(PluginDescriptor pluginDescriptor, WebControllerManager manager, ProjectManager projectManager) {
        myPluginDescriptor = pluginDescriptor;
        this.projectManager = projectManager;
        // this will make the controller accessible via <teamcity_url>\dependenciesGraph.html
        manager.registerController("/dependenciesGraph.html", this);
    }

    @Override
    protected ModelAndView doHandle(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        String projectId = request.getParameter("projectId");
        SProject project = projectManager.findProjectById(projectId);

        

        ModelAndView view = new ModelAndView(myPluginDescriptor.getPluginResourcesPath("dependenciesGraph.jsp"));
        final Map model = view.getModel();
        model.put("name", Util.NAME);
        model.put("projectName", project.getName());
        return view;
    }
}
