package com.janglejay;

import com.janglejay.entity.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        method1();
        method2();
    }
    private static void method1() {
        long start = System.currentTimeMillis();

        User user = new User();
        for (int i = 0; i < 900000000; i++) {
            String name = user.getName();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
    private static void method2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        long start = System.currentTimeMillis();
        Class userClass = Class.forName("com.janglejay.entity.User");
        Object o = userClass.getDeclaredConstructor().newInstance();
        Method getName = userClass.getMethod("getName");
        for (int i = 0; i < 900000000; i++) {
            getName.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
