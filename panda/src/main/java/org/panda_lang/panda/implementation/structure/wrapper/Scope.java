/*
 * Copyright (c) 2015-2017 Dzikoysk
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

package org.panda_lang.panda.implementation.structure.wrapper;

import org.panda_lang.panda.implementation.structure.value.Variable;
import org.panda_lang.panda.implementation.structure.dynamic.ScopeInstance;

import java.util.List;

/**
 * Specific type of scope which contains own memory, independence, etc.
 */
public interface Scope extends Container {

    /**
     * Creates new instance of the current wrapper for individual values for fields, etc.
     *
     * @return instance of the current wrapper
     */
    ScopeInstance createInstance();

    /**
     * @return list of variables in the proper order
     */
    List<Variable> getVariables();

}
