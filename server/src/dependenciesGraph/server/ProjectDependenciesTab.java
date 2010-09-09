/*
 * Copyright 2000-2010 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dependenciesGraph.server;

import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.users.SUser;
import jetbrains.buildServer.web.openapi.PagePlaces;
import jetbrains.buildServer.web.openapi.project.ProjectTab;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Simone
 * Date: 4-set-2010
 * Time: 14.47.29
 * To change this template use File | Settings | File Templates.
 */
public class ProjectDependenciesTab extends ProjectTab {
    protected ProjectDependenciesTab(PagePlaces pagePlaces, ProjectManager projectManager) {
        super("projectDependencies", "Project dependencies", pagePlaces, projectManager, "/dependenciesGraph.html");
    }

    @Override
    protected void fillModel(Map model, HttpServletRequest request, SProject project, SUser user) {
        model.put("projectId", project.getProjectId());
    }
}
