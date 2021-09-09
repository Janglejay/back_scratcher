package com.janglejay.utils;

import java.util.HashMap;
import java.util.Map;

public class BasicTypeTable {
    public static final Map<String, String> TYPE_TABLE;
    static {
        TYPE_TABLE = new HashMap<>();
//        字符类型char，布尔类型boolean以及数值类型 byte、short、int、long、float、double
        TYPE_TABLE.put("char", "'c'");
        TYPE_TABLE.put("boolean", "true");
        TYPE_TABLE.put("byte", "0");
        TYPE_TABLE.put("short", "1");
        TYPE_TABLE.put("int", "1");
        TYPE_TABLE.put("long", "1L");
        TYPE_TABLE.put("float", "1.0F");
        TYPE_TABLE.put("double", "1.0D");

        TYPE_TABLE.put("String", "\"String\"");

//        Character, Byte, Boolean, Short, Integer, Long, Float, Double
        TYPE_TABLE.put("Character", "null");
        TYPE_TABLE.put("Byte", "null");
        TYPE_TABLE.put("Boolean", "true");
        TYPE_TABLE.put("Short", "null");
        TYPE_TABLE.put("Integer", "1");
        TYPE_TABLE.put("Long", "1L");
        TYPE_TABLE.put("Float", "1.0F");
        TYPE_TABLE.put("Double", "1.0D");
    }
}
