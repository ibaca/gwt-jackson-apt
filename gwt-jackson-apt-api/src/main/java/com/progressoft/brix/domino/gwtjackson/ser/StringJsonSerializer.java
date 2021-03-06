/*
 * Copyright 2013 Nicolas Morel
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

package com.progressoft.brix.domino.gwtjackson.ser;

import com.progressoft.brix.domino.gwtjackson.JsonSerializationContext;
import com.progressoft.brix.domino.gwtjackson.JsonSerializer;
import com.progressoft.brix.domino.gwtjackson.JsonSerializerParameters;
import com.progressoft.brix.domino.gwtjackson.stream.JsonWriter;

/**
 * Default {@link JsonSerializer} implementation for {@link String}.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class StringJsonSerializer extends JsonSerializer<String> {

    private static final StringJsonSerializer INSTANCE = new StringJsonSerializer();

    /**
     * <p>getInstance</p>
     *
     * @return an instance of {@link StringJsonSerializer}
     */
    public static StringJsonSerializer getInstance() {
        return INSTANCE;
    }

    private StringJsonSerializer() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isEmpty(String value) {
        return null == value || value.length() == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSerialize(JsonWriter writer, String value, JsonSerializationContext ctx, JsonSerializerParameters params) {
        writer.value(value);
    }
}
