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

package org.panda_lang.panda.utilities.commons;

public class BenchmarkUtils {

    public static void print(long start, int av, String message) {
        long nsDif = System.nanoTime() - start;
        float msDif = nsDif / 1000000;
        float sDif = msDif / 1000;
        float mDif = sDif / 60;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(message);
        stringBuilder.append(System.lineSeparator());

        stringBuilder.append("    ns: ");
        stringBuilder.append(nsDif);
        stringBuilder.append(" | av: ");
        stringBuilder.append(nsDif / av);
        stringBuilder.append(System.lineSeparator());

        stringBuilder.append("    ms: ");
        stringBuilder.append(msDif);
        stringBuilder.append(" | av: ");
        stringBuilder.append(msDif / av);
        stringBuilder.append(System.lineSeparator());

        stringBuilder.append("    s: ");
        stringBuilder.append(sDif);
        stringBuilder.append(" | av: ");
        stringBuilder.append(sDif / av);
        stringBuilder.append(System.lineSeparator());

        stringBuilder.append("    m: ");
        stringBuilder.append(mDif);
        stringBuilder.append(" | av: ");
        stringBuilder.append(mDif / av);
        stringBuilder.append(System.lineSeparator());

        System.out.println(stringBuilder.toString());
    }

}
