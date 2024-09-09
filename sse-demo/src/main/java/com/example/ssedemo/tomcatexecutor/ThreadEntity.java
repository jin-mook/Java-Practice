package com.example.ssedemo.tomcatexecutor;

public class ThreadEntity {

    private String currentThreadName;
    private int totalThreadCount;
    private int currentThreadCount;

    public ThreadEntity(String currentThreadName, int totalThreadCount, int currentThreadCount) {
        this.currentThreadName = currentThreadName;
        this.totalThreadCount = totalThreadCount;
        this.currentThreadCount = currentThreadCount;
    }
}
