package com.janglejay.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtil {
    public static <T> List<T> of(T... elements) {
        return new ArrayList<>(Arrays.asList(elements));
    }
}
