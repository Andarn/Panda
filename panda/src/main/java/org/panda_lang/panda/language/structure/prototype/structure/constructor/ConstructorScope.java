/*
 * Copyright (c) 2015-2018 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.panda_lang.panda.language.structure.prototype.structure.constructor;

import org.panda_lang.panda.core.structure.util.AbstractScope;
import org.panda_lang.panda.language.structure.prototype.structure.parameter.Parameter;

import java.util.List;

public class ConstructorScope extends AbstractScope {

    private final List<Parameter> parameters;

    public ConstructorScope(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public ConstructorScopeInstance createInstance() {
        return new ConstructorScopeInstance(this);
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

}
