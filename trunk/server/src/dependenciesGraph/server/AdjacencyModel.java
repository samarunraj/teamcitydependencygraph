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
 * Date: 9-set-2010
 * Time: 0.36.06
 * To change this template use File | Settings | File Templates.
 */
public class AdjacencyModel {
    private final String nodeTo;
    private AdjancencyDataModel data;

    public AdjacencyModel(String nodeTo, String nodeFrom) {
        this.nodeTo = nodeTo;
        this.data = new AdjancencyDataModel(nodeFrom, nodeTo);
    }
}
