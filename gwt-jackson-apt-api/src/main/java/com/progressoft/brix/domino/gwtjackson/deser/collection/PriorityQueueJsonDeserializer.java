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

package com.progressoft.brix.domino.gwtjackson.deser.collection;

import com.progressoft.brix.domino.gwtjackson.JsonDeserializer;

import java.util.PriorityQueue;

/**
 * Default {@link JsonDeserializer} implementation for {@link PriorityQueue}.
 *
 * @param <T> Type of the elements inside the {@link PriorityQueue}
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PriorityQueueJsonDeserializer<T> extends BaseQueueJsonDeserializer<PriorityQueue<T>, T> {

    /**
     * <p>newInstance</p>
     *
     * @param deserializer {@link JsonDeserializer} used to deserialize the objects inside the {@link PriorityQueue}.
     * @param <T>          Type of the elements inside the {@link PriorityQueue}
     * @return a new instance of {@link PriorityQueueJsonDeserializer}
     */
    public static <T> PriorityQueueJsonDeserializer<T> newInstance(JsonDeserializer<T> deserializer) {
        return new PriorityQueueJsonDeserializer<T>(deserializer);
    }

    /**
     * @param deserializer {@link JsonDeserializer} used to deserialize the objects inside the {@link PriorityQueue}.
     */
    private PriorityQueueJsonDeserializer(JsonDeserializer<T> deserializer) {
        super(deserializer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PriorityQueue<T> newCollection() {
        return new PriorityQueue<T>();
    }
}
