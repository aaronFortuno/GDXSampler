package com.mygdx.game.common;

public class SampleInfo {

    private final String name;
    private final Class<?> clazz;

    public SampleInfo(String name, Class<?> clazz) {
        this.clazz = clazz;
        this.name = clazz.getSimpleName();
    }

    public String getName() {
        return name;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
