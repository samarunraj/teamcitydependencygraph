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

/**
 * Created by IntelliJ IDEA.
 * User: Simone
 * Date: 8-set-2010
 * Time: 23.56.11
 * To change this template use File | Settings | File Templates.
 */
public class NodeModel {
    private final String id;
    private final String name;
    private final AdjacencyModel[] adjacencies;

    public NodeModel(String nodeId, String nodeName, AdjacencyModel[] adjacencies) {

        this.id = nodeId;
        this.name = nodeName;
        this.adjacencies = adjacencies;
    }
}
