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

package com.progressoft.brix.domino.gwtjackson.ser.number;

import com.progressoft.brix.domino.gwtjackson.ser.AbstractJsonSerializerTest;
import com.progressoft.brix.domino.gwtjackson.ser.BaseNumberJsonSerializer.ShortJsonSerializer;

/**
 * @author Nicolas Morel
 */
public class ShortJsonSerializerTest extends AbstractJsonSerializerTest<Short> {

    @Override
    protected ShortJsonSerializer createSerializer() {
        return ShortJsonSerializer.getInstance();
    }

    public void testSerializeValue() {
        assertSerialization("34", new Short("34"));
        assertSerialization("-1", new Short("-1"));
        assertSerialization("-32768", Short.MIN_VALUE);
        assertSerialization("32767", Short.MAX_VALUE);
    }
}
