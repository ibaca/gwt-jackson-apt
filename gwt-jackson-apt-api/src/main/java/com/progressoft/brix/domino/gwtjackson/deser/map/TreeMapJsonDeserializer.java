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

package com.progressoft.brix.domino.gwtjackson.deser.map;

import com.progressoft.brix.domino.gwtjackson.JsonDeserializer;
import com.progressoft.brix.domino.gwtjackson.deser.map.key.KeyDeserializer;

import java.util.TreeMap;

/**
 * Default {@link JsonDeserializer} implementation for {@link TreeMap}.
 * <p>Cannot be overriden. Use {@link BaseMapJsonDeserializer}.</p>
 *
 * @param <K> Type of the keys inside the {@link TreeMap}
 * @param <V> Type of the values inside the {@link TreeMap}
 * @author Nicolas Morel
 * @version $Id: $
 */
public final class TreeMapJsonDeserializer<K, V> extends BaseMapJsonDeserializer<TreeMap<K, V>, K, V> {

    /**
     * <p>newInstance</p>
     *
     * @param keyDeserializer   {@link KeyDeserializer} used to deserialize the keys.
     * @param valueDeserializer {@link JsonDeserializer} used to deserialize the values.
     * @param <K>               Type of the keys inside the {@link TreeMap}
     * @param <V>               Type of the values inside the {@link TreeMap}
     * @return a new instance of {@link TreeMapJsonDeserializer}
     */
    public static <K, V> TreeMapJsonDeserializer<K, V> newInstance(KeyDeserializer<K> keyDeserializer,
                                                                   JsonDeserializer<V> valueDeserializer) {
        return new TreeMapJsonDeserializer<K, V>(keyDeserializer, valueDeserializer);
    }

    /**
     * @param keyDeserializer   {@link KeyDeserializer} used to deserialize the keys.
     * @param valueDeserializer {@link JsonDeserializer} used to deserialize the values.
     */
    private TreeMapJsonDeserializer(KeyDeserializer<K> keyDeserializer, JsonDeserializer<V> valueDeserializer) {
        super(keyDeserializer, valueDeserializer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TreeMap<K, V> newMap() {
        return new TreeMap<K, V>();
    }
}
