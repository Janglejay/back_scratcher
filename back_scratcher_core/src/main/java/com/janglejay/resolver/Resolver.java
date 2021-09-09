package com.janglejay.resolver;

import com.janglejay.deconstruction.Deconstruction;

import java.util.List;

public interface Resolver {
    public Deconstruction resolve(String line) throws Exception;
}
