/*
 * Copyright 2014 Nicolas Morel
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

package com.progressoft.brix.domino.gwtjackson.deser.array.dd;

import com.progressoft.brix.domino.gwtjackson.JsonDeserializerParameters;
import com.progressoft.brix.domino.gwtjackson.JsonDeserializationContext;
import com.progressoft.brix.domino.gwtjackson.JsonDeserializer;
import com.progressoft.brix.domino.gwtjackson.deser.BooleanJsonDeserializer;
import com.progressoft.brix.domino.gwtjackson.stream.JsonReader;

import java.util.List;

/**
 * Default {@link JsonDeserializer} implementation for 2D array of boolean.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PrimitiveBooleanArray2dJsonDeserializer extends AbstractArray2dJsonDeserializer<boolean[][]> {

    private static final PrimitiveBooleanArray2dJsonDeserializer INSTANCE = new PrimitiveBooleanArray2dJsonDeserializer();

    /**
     * <p>getInstance</p>
     *
     * @return an instance of {@link PrimitiveBooleanArray2dJsonDeserializer}
     */
    public static PrimitiveBooleanArray2dJsonDeserializer getInstance() {
        return INSTANCE;
    }

    private PrimitiveBooleanArray2dJsonDeserializer() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean[][] doDeserialize(JsonReader reader, JsonDeserializationContext ctx, JsonDeserializerParameters params) {
        List<List<Boolean>> list = deserializeIntoList(reader, ctx, BooleanJsonDeserializer.getInstance(), params);

        if (list.isEmpty()) {
            return new boolean[0][0];
        }

        List<Boolean> firstList = list.get(0);
        if (firstList.isEmpty()) {
            return new boolean[list.size()][0];
        }

        boolean[][] array = new boolean[list.size()][firstList.size()];

        int i = 0;
        int j;
        for (List<Boolean> innerList : list) {
            j = 0;
            for (Boolean value : innerList) {
                if (null != value) {
                    array[i][j] = value;
                }
                j++;
            }
            i++;
        }
        return array;
    }
}
