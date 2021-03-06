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

package com.progressoft.brix.domino.gwtjackson.ser.array;

import com.progressoft.brix.domino.gwtjackson.JsonSerializer;
import com.progressoft.brix.domino.gwtjackson.ser.AbstractJsonSerializerTest;
import com.progressoft.brix.domino.gwtjackson.ser.array.dd.PrimitiveByteArray2dJsonSerializer;

/**
 * Test byte array serialization.
 */
public class ByteArray2dJsonSerializerTest extends AbstractJsonSerializerTest<byte[][]> {

    @Override
    protected JsonSerializer<byte[][]> createSerializer() {
        return PrimitiveByteArray2dJsonSerializer.getInstance();
    }

    @Override
    public void testSerializeValue() {
        assertSerialization(
                "[\"AAsWIQ==\",\"APXq3w==\",\"AGScAA==\",\"\"]",
                new byte[][]{{0, 11, 22, 33}, {0, -11, -22, -33}, {0, 100, -100, 0}, {}});
    }
}
