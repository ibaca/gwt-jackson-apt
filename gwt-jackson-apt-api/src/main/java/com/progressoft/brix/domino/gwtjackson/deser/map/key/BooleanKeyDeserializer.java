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

package com.progressoft.brix.domino.gwtjackson.deser.map.key;

import com.progressoft.brix.domino.gwtjackson.JsonDeserializationContext;

/**
 * Default {@link KeyDeserializer} implementation for {@link Boolean}.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public final class BooleanKeyDeserializer extends KeyDeserializer<Boolean> {

    private static final BooleanKeyDeserializer INSTANCE = new BooleanKeyDeserializer();

    /**
     * <p>getInstance</p>
     *
     * @return an instance of {@link BooleanKeyDeserializer}
     */
    public static BooleanKeyDeserializer getInstance() {
        return INSTANCE;
    }

    private BooleanKeyDeserializer() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Boolean doDeserialize(String key, JsonDeserializationContext ctx) {
        return Boolean.valueOf(key);
    }
}
