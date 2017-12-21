/*
 * Copyright 2017 Ahmad Bawaneh
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
package com.progressoft.brix.domino.gwtjackson.processor;

import com.google.gwt.core.client.JavaScriptObject;
import com.progressoft.brix.domino.gwtjackson.deser.BaseDateJsonDeserializer.DateJsonDeserializer;
import com.progressoft.brix.domino.gwtjackson.deser.BaseDateJsonDeserializer.SqlDateJsonDeserializer;
import com.progressoft.brix.domino.gwtjackson.deser.BaseDateJsonDeserializer.SqlTimeJsonDeserializer;
import com.progressoft.brix.domino.gwtjackson.deser.BaseDateJsonDeserializer.SqlTimestampJsonDeserializer;
import com.progressoft.brix.domino.gwtjackson.deser.BaseNumberJsonDeserializer.*;
import com.progressoft.brix.domino.gwtjackson.deser.*;
import com.progressoft.brix.domino.gwtjackson.deser.array.*;
import com.progressoft.brix.domino.gwtjackson.deser.array.dd.*;
import com.progressoft.brix.domino.gwtjackson.deser.collection.*;
import com.progressoft.brix.domino.gwtjackson.deser.map.*;
import com.progressoft.brix.domino.gwtjackson.deser.map.key.*;
import com.progressoft.brix.domino.gwtjackson.ser.BaseDateJsonSerializer.DateJsonSerializer;
import com.progressoft.brix.domino.gwtjackson.ser.BaseDateJsonSerializer.SqlDateJsonSerializer;
import com.progressoft.brix.domino.gwtjackson.ser.BaseDateJsonSerializer.SqlTimeJsonSerializer;
import com.progressoft.brix.domino.gwtjackson.ser.BaseDateJsonSerializer.SqlTimestampJsonSerializer;
import com.progressoft.brix.domino.gwtjackson.ser.BaseNumberJsonSerializer.*;
import com.progressoft.brix.domino.gwtjackson.ser.*;
import com.progressoft.brix.domino.gwtjackson.ser.array.*;
import com.progressoft.brix.domino.gwtjackson.ser.array.dd.*;
import com.progressoft.brix.domino.gwtjackson.ser.map.MapJsonSerializer;
import com.progressoft.brix.domino.gwtjackson.ser.map.key.*;
import com.squareup.javapoet.TypeName;

import javax.lang.model.type.TypeMirror;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

import static com.progressoft.brix.domino.gwtjackson.processor.ObjectMapperProcessor.typeUtils;
import static java.util.Objects.nonNull;

public final class TypeRegistry {

    private static Map<String, ClassMapper> simpleTypes = new HashMap<>();
    private static Map<String, ClassMapper> keysMappers = new HashMap<>();
    private static Map<String, Class> collectionsDeserializers = new HashMap<>();
    private static Map<String, Class> mapDeserializers = new HashMap<>();
    private static Map<String, ClassMapper> customMappers = new HashMap<>();

    static final ClassMapperFactory MAPPER = new ClassMapperFactory();

    //bas types mappers
    static {
        MAPPER
                .forType(boolean.class)
                .serializer(BooleanJsonSerializer.class)
                .deserializer(BooleanJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(char.class)
                .serializer(CharacterJsonSerializer.class)
                .deserializer(CharacterJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(byte.class)
                .serializer(ByteJsonSerializer.class)
                .deserializer(ByteJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(double.class)
                .serializer(DoubleJsonSerializer.class)
                .deserializer(DoubleJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(float.class)
                .serializer(FloatJsonSerializer.class)
                .deserializer(FloatJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(int.class)
                .serializer(IntegerJsonSerializer.class)
                .deserializer(IntegerJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(long.class)
                .serializer(LongJsonSerializer.class)
                .deserializer(LongJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(short.class)
                .serializer(ShortJsonSerializer.class)
                .deserializer(ShortJsonDeserializer.class)
                .register(simpleTypes);

        // Common mappers
        MAPPER
                .forType(String.class)
                .serializer(StringJsonSerializer.class)
                .deserializer(StringJsonDeserializer.class)
                .register(simpleTypes);
        MAPPER
                .forType(Boolean.class)
                .serializer(BooleanJsonSerializer.class)
                .deserializer(BooleanJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Character.class)
                .serializer(CharacterJsonSerializer.class)
                .deserializer(CharacterJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(UUID.class)
                .serializer(UUIDJsonSerializer.class)
                .deserializer(UUIDJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Void.class)
                .serializer(VoidJsonSerializer.class)
                .deserializer(VoidJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(JavaScriptObject.class)
                .serializer(JavaScriptObjectJsonSerializer.class)
                .deserializer(JavaScriptObjectJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Enum.class)
                .serializer(EnumJsonSerializer.class)
                .deserializer(EnumJsonDeserializer.class)
                .register(simpleTypes);

        // Number mappers
        MAPPER
                .forType(BigDecimal.class)
                .serializer(BigDecimalJsonSerializer.class)
                .deserializer(BigDecimalJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(BigInteger.class)
                .serializer(BigIntegerJsonSerializer.class)
                .deserializer(BigIntegerJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Byte.class)
                .serializer(ByteJsonSerializer.class)
                .deserializer(ByteJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Double.class)
                .serializer(DoubleJsonSerializer.class)
                .deserializer(DoubleJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Float.class)
                .serializer(FloatJsonSerializer.class)
                .deserializer(FloatJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Integer.class)
                .serializer(IntegerJsonSerializer.class)
                .deserializer(IntegerJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Long.class)
                .serializer(LongJsonSerializer.class)
                .deserializer(LongJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Short.class)
                .serializer(ShortJsonSerializer.class)
                .deserializer(ShortJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Number.class)
                .serializer(NumberJsonSerializer.class)
                .deserializer(NumberJsonDeserializer.class)
                .register(simpleTypes);

        // Date mappers
        MAPPER
                .forType(Date.class)
                .serializer(DateJsonSerializer.class)
                .deserializer(DateJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(java.sql.Date.class)
                .serializer(SqlDateJsonSerializer.class)
                .deserializer(SqlDateJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Time.class)
                .serializer(SqlTimeJsonSerializer.class)
                .deserializer(SqlTimeJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Timestamp.class)
                .serializer(SqlTimestampJsonSerializer.class)
                .deserializer(SqlTimestampJsonDeserializer.class)
                .register(simpleTypes);

        // Iterable mappers
        MAPPER
                .forType(Iterable.class)
                .serializer(IterableJsonSerializer.class)
                .deserializer(IterableJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Collection.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(CollectionJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(AbstractCollection.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(AbstractCollectionJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(AbstractList.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(AbstractListJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(AbstractQueue.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(AbstractQueueJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(AbstractSequentialList.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(AbstractSequentialListJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(AbstractSet.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(AbstractSetJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(ArrayList.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(ArrayListJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(EnumSet.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(EnumSetJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(HashSet.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(HashSetJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(LinkedHashSet.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(LinkedHashSetJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(LinkedList.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(LinkedListJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(List.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(ListJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(PriorityQueue.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(PriorityQueueJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Queue.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(QueueJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Set.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(SetJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(SortedSet.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(SortedSetJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Stack.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(StackJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(TreeSet.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(TreeSetJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Vector.class)
                .serializer(CollectionJsonSerializer.class)
                .deserializer(VectorJsonDeserializer.class)
                .register(simpleTypes);

        // Map mappers
        MAPPER
                .forType(Map.class)
                .serializer(MapJsonSerializer.class)
                .deserializer(MapJsonDeserializer.class)
                .register(simpleTypes);
        MAPPER
                .forType(AbstractMap.class)
                .serializer(MapJsonSerializer.class)
                .deserializer(AbstractMapJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(EnumMap.class)
                .serializer(MapJsonSerializer.class)
                .deserializer(EnumMapJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(HashMap.class)
                .serializer(MapJsonSerializer.class)
                .deserializer(HashMapJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(IdentityHashMap.class)
                .serializer(MapJsonSerializer.class)
                .deserializer(IdentityHashMapJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(LinkedHashMap.class)
                .serializer(MapJsonSerializer.class)
                .deserializer(LinkedHashMapJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(SortedMap.class)
                .serializer(MapJsonSerializer.class)
                .deserializer(SortedMapJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(TreeMap.class)
                .serializer(MapJsonSerializer.class)
                .deserializer(TreeMapJsonDeserializer.class)
                .register(simpleTypes);

        // Primitive array mappers
        MAPPER
                .forType(boolean[].class)
                .serializer(PrimitiveBooleanArrayJsonSerializer.class)
                .deserializer(PrimitiveBooleanArrayJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(byte[].class)
                .serializer(PrimitiveByteArrayJsonSerializer.class)
                .deserializer(PrimitiveByteArrayJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(char[].class)
                .serializer(PrimitiveCharacterArrayJsonSerializer.class)
                .deserializer(PrimitiveCharacterArrayJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(double[].class)
                .serializer(PrimitiveDoubleArrayJsonSerializer.class)
                .deserializer(PrimitiveDoubleArrayJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(float[].class)
                .serializer(PrimitiveFloatArrayJsonSerializer.class)
                .deserializer(PrimitiveFloatArrayJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(int[].class)
                .serializer(PrimitiveIntegerArrayJsonSerializer.class)
                .deserializer(PrimitiveIntegerArrayJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(long[].class)
                .serializer(PrimitiveLongArrayJsonSerializer.class)
                .deserializer(PrimitiveLongArrayJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(short[].class)
                .serializer(PrimitiveShortArrayJsonSerializer.class)
                .deserializer(PrimitiveShortArrayJsonDeserializer.class)
                .register(simpleTypes);

        // Primitive 2D Array mappers
        MAPPER
                .forType(boolean[][].class)
                .serializer(PrimitiveBooleanArray2dJsonSerializer.class)
                .deserializer(PrimitiveBooleanArray2dJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(byte[][].class)
                .serializer(PrimitiveByteArray2dJsonSerializer.class)
                .deserializer(PrimitiveByteArray2dJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(char[][].class)
                .serializer(PrimitiveCharacterArray2dJsonSerializer.class)
                .deserializer(PrimitiveCharacterArray2dJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(double[][].class)
                .serializer(PrimitiveDoubleArray2dJsonSerializer.class)
                .deserializer(PrimitiveDoubleArray2dJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(float[][].class)
                .serializer(PrimitiveFloatArray2dJsonSerializer.class)
                .deserializer(PrimitiveFloatArray2dJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(int[][].class)
                .serializer(PrimitiveIntegerArray2dJsonSerializer.class)
                .deserializer(PrimitiveIntegerArray2dJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(long[][].class)
                .serializer(PrimitiveLongArray2dJsonSerializer.class)
                .deserializer(PrimitiveLongArray2dJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(short[][].class)
                .serializer(PrimitiveShortArray2dJsonSerializer.class)
                .deserializer(PrimitiveShortArray2dJsonDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(String[].class)
                .serializer(ArrayJsonSerializer.class)
                .deserializer(PrimitiveShortArray2dJsonDeserializer.class)
                .register(simpleTypes);
        MAPPER
                .forType(String[][].class)
                .serializer(Array2dJsonSerializer.class)
                .deserializer(Array2dJsonDeserializer.class)
                .register(simpleTypes);
    }

    // Map's key mappers
    static {

        MAPPER
                .forType(Object.class)
                .serializer(ObjectKeySerializer.class)
                .deserializer(StringKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(Serializable.class)
                .serializer(ObjectKeySerializer.class)
                .deserializer(StringKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(BigDecimal.class)
                .serializer(NumberKeySerializer.class)
                .deserializer(BaseNumberKeyDeserializer.BigDecimalKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(BigInteger.class)
                .serializer(NumberKeySerializer.class)
                .deserializer(BaseNumberKeyDeserializer.BigIntegerKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(Boolean.class)
                .serializer(BooleanKeySerializer.class)
                .deserializer(BooleanKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(Byte.class)
                .serializer(NumberKeySerializer.class)
                .deserializer(BaseNumberKeyDeserializer.ByteKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(Character.class)
                .serializer(ToStringKeySerializer.class)
                .deserializer(CharacterKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(Date.class)
                .serializer(DateKeySerializer.class)
                .deserializer(BaseDateKeyDeserializer.DateKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(Double.class)
                .serializer(NumberKeySerializer.class)
                .deserializer(BaseNumberKeyDeserializer.DoubleKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(Enum.class)
                .serializer(EnumKeySerializer.class)
                .deserializer(EnumKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(Float.class)
                .serializer(NumberKeySerializer.class)
                .deserializer(BaseNumberKeyDeserializer.FloatKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(Integer.class)
                .serializer(NumberKeySerializer.class)
                .deserializer(BaseNumberKeyDeserializer.IntegerKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(Long.class)
                .serializer(NumberKeySerializer.class)
                .deserializer(BaseNumberKeyDeserializer.LongKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(Short.class)
                .serializer(NumberKeySerializer.class)
                .deserializer(BaseNumberKeyDeserializer.ShortKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(java.sql.Date.class)
                .serializer(DateKeySerializer.class)
                .deserializer(BaseDateKeyDeserializer.SqlDateKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(Time.class)
                .serializer(DateKeySerializer.class)
                .deserializer(BaseDateKeyDeserializer.SqlTimeKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(Timestamp.class)
                .serializer(DateKeySerializer.class)
                .deserializer(BaseDateKeyDeserializer.SqlTimestampKeyDeserializer.class)
                .register(keysMappers);

        MAPPER
                .forType(String.class)
                .serializer(ToStringKeySerializer.class)
                .deserializer(StringKeyDeserializer.class)
                .register(keysMappers);


        MAPPER
                .forType(UUID.class)
                .serializer(UUIDKeySerializer.class)
                .deserializer(UUIDKeyDeserializer.class)
                .register(keysMappers);
    }

    // Collections deserializers
    static {

        collectionsDeserializers.put(AbstractCollection.class.getCanonicalName(), AbstractCollectionJsonDeserializer.class);
        collectionsDeserializers.put(AbstractList.class.getCanonicalName(), AbstractListJsonDeserializer.class);
        collectionsDeserializers.put(AbstractQueue.class.getCanonicalName(), AbstractQueueJsonDeserializer.class);
        collectionsDeserializers.put(AbstractSequentialList.class.getCanonicalName(), AbstractSequentialListJsonDeserializer.class);
        collectionsDeserializers.put(AbstractSet.class.getCanonicalName(), AbstractSetJsonDeserializer.class);
        collectionsDeserializers.put(ArrayList.class.getCanonicalName(), ArrayListJsonDeserializer.class);
        collectionsDeserializers.put(Collection.class.getCanonicalName(), CollectionJsonDeserializer.class);
        collectionsDeserializers.put(EnumSet.class.getCanonicalName(), EnumSetJsonDeserializer.class);
        collectionsDeserializers.put(HashSet.class.getCanonicalName(), HashSetJsonDeserializer.class);
        collectionsDeserializers.put(Iterable.class.getCanonicalName(), IterableJsonDeserializer.class);
        collectionsDeserializers.put(LinkedHashSet.class.getCanonicalName(), LinkedHashSetJsonDeserializer.class);
        collectionsDeserializers.put(LinkedList.class.getCanonicalName(), LinkedListJsonDeserializer.class);
        collectionsDeserializers.put(List.class.getCanonicalName(), ListJsonDeserializer.class);
        collectionsDeserializers.put(PriorityQueue.class.getCanonicalName(), PriorityQueueJsonDeserializer.class);
        collectionsDeserializers.put(Queue.class.getCanonicalName(), QueueJsonDeserializer.class);
        collectionsDeserializers.put(Set.class.getCanonicalName(), SetJsonDeserializer.class);
        collectionsDeserializers.put(SortedSet.class.getCanonicalName(), SortedSetJsonDeserializer.class);
        collectionsDeserializers.put(Stack.class.getCanonicalName(), StackJsonDeserializer.class);
        collectionsDeserializers.put(TreeSet.class.getCanonicalName(), TreeSetJsonDeserializer.class);
        collectionsDeserializers.put(Vector.class.getCanonicalName(), VectorJsonDeserializer.class);

    }

    //Maps deserializers
    static {

        mapDeserializers.put(Map.class.getName(), MapJsonDeserializer.class);
        mapDeserializers.put(AbstractMap.class.getName(), AbstractMapJsonDeserializer.class);
        mapDeserializers.put(EnumMap.class.getName(), EnumMapJsonDeserializer.class);
        mapDeserializers.put(HashMap.class.getName(), HashMapJsonDeserializer.class);
        mapDeserializers.put(IdentityHashMap.class.getName(), IdentityHashMapJsonDeserializer.class);
        mapDeserializers.put(LinkedHashMap.class.getName(), LinkedHashMapJsonDeserializer.class);
        mapDeserializers.put(SortedMap.class.getName(), SortedMapJsonDeserializer.class);
        mapDeserializers.put(TreeMap.class.getName(), TreeMapJsonDeserializer.class);
    }

    public static void register(ClassMapper mapper) {
        mapper.register(simpleTypes);
    }

    public static boolean isBasicType(String type) {
        return simpleTypes.containsKey(type);
    }

    public static void registerSerializer(String type, TypeName serializer) {
        if (customMappers.containsKey(type)) {
            customMappers.get(type).serializer = serializer;
        } else {
            ClassMapper classMapper = new ClassMapper(type);
            classMapper.serializer = serializer;
            customMappers.put(type, classMapper);
        }
    }

    public static void registerDeserializer(String type, TypeName deserializer) {
        if (customMappers.containsKey(type)) {
            customMappers.get(type).deserializer = deserializer;
        } else {
            ClassMapper classMapper = new ClassMapper(type);
            classMapper.deserializer = deserializer;
            customMappers.put(type, classMapper);
        }
    }

    public static TypeName getCustomSerializer(TypeMirror typeMirror) {
        return getCustomSerializer(typeMirror.toString());
    }

    public static TypeName getCustomSerializer(String type) {
        if (containsSerializer(type))
            return customMappers.get(type).serializer;
        throw new TypeSerializerNotFoundException(type);
    }


    public static TypeName getCustomDeserializer(TypeMirror typeMirror) {
        return getCustomDeserializer(typeMirror.toString());
    }

    public static TypeName getCustomDeserializer(String type) {
        if (containsDeserializer(type))
            return customMappers.get(type).deserializer;
        throw new TypeDeserializerNotFoundException(type);
    }
    public static ClassMapper get(String typeName) {
        return simpleTypes.get(typeName);
    }

    public static TypeName getSerializer(TypeMirror typeMirror) {
        return getSerializer(typeUtils.erasure(typeMirror).toString());
    }

    private static TypeName getSerializer(String typeName) {
        if (simpleTypes.containsKey(typeName))
            return get(typeName).serializer;
        throw new TypeSerializerNotFoundException(typeName);
    }

    public static TypeName getKeySerializer(String typeName) {
        if (keysMappers.containsKey(typeName))
            return keysMappers.get(typeName).serializer;
        throw new TypeSerializerNotFoundException(typeName);
    }

    public static TypeName getKeyDeserializer(String typeName) {
        if (keysMappers.containsKey(typeName))
            return keysMappers.get(typeName).deserializer;
        throw new TypeDeserializerNotFoundException(typeName);
    }

    public static TypeName getDeserializer(TypeMirror typeMirror) {
        return getDeserializer(typeUtils.erasure(typeMirror).toString());
    }

    private static TypeName getDeserializer(String typeName) {
        if (simpleTypes.containsKey(typeName))
            return simpleTypes.get(typeName).deserializer;
        throw new TypeDeserializerNotFoundException(typeName);
    }

    public static Class<?> getCollectionDeserializer(TypeMirror typeMirror) {
        return getCollectionDeserializer(asNoneGeneric(typeMirror));
    }

    private static String asNoneGeneric(TypeMirror type) {
        return typeUtils.asElement(type).toString().replace("<E>", "");
    }

    private static Class<?> getCollectionDeserializer(String collectionType) {
        if (collectionsDeserializers.containsKey(collectionType))
            return collectionsDeserializers.get(collectionType);
        throw new TypeDeserializerNotFoundException(collectionType);
    }

    public static Class<?> getMapDeserializer(TypeMirror typeMirror) {
        return getMapDeserializer(asNoneGeneric(typeMirror));
    }


    private static Class<?> getMapDeserializer(String mapType) {
        if (mapDeserializers.containsKey(mapType))
            return mapDeserializers.get(mapType);
        throw new TypeDeserializerNotFoundException(mapType);
    }

    public static boolean containsDeserializer(String typeName) {
        return nonNull(customMappers.get(typeName)) && nonNull(customMappers.get(typeName).deserializer);
    }

    public static boolean containsSerializer(String typeName) {
        return nonNull(customMappers.get(typeName)) && nonNull(customMappers.get(typeName).serializer);
    }

    public static boolean containsSerializer(TypeMirror typeMirror) {
        return containsSerializer(typeMirror.toString());
    }

    public static boolean containsDeserializer(TypeMirror typeMirror) {
        return containsDeserializer(typeMirror.toString());
    }


    static class ClassMapperFactory {
        ClassMapper forType(Class<?> clazz) {
            return new ClassMapper(clazz);
        }
    }


    public static class ClassMapper {

        private final String clazz;

        private TypeName serializer;

        private TypeName deserializer;

        private ClassMapper(Class clazz) {
            this.clazz = clazz.getCanonicalName();
        }

        private ClassMapper(String type) {
            this.clazz = type;
        }

        private ClassMapper serializer(Class serializer) {
            this.serializer = TypeName.get(serializer);
            return this;
        }

        private ClassMapper deserializer(Class deserializer) {
            this.deserializer = TypeName.get(deserializer);
            return this;
        }

        private ClassMapper serializer(TypeName serializer) {
            this.serializer = serializer;
            return this;
        }

        private ClassMapper deserializer(TypeName deserializer) {
            this.deserializer = deserializer;
            return this;
        }

        private ClassMapper register(Map<String, ClassMapper> registry) {
            registry.put(this.clazz, this);
            return this;
        }
    }

    private static class TypeSerializerNotFoundException extends RuntimeException {
        TypeSerializerNotFoundException(String typeName) {
            super(typeName);
        }
    }

    private static class TypeDeserializerNotFoundException extends RuntimeException {
        TypeDeserializerNotFoundException(String typeName) {
            super(typeName);
        }
    }
}
