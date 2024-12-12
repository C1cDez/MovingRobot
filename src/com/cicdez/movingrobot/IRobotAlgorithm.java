package com.cicdez.movingrobot;

public interface IRobotAlgorithm {
    boolean step(Robot robot);
    String getAlgorithmName();
}
