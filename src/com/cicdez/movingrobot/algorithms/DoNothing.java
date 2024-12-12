package com.cicdez.movingrobot.algorithms;

import com.cicdez.movingrobot.IRobotAlgorithm;
import com.cicdez.movingrobot.Robot;

public class DoNothing implements IRobotAlgorithm {
    @Override
    public boolean step(Robot robot) {
        return true;
    }

    @Override
    public String getAlgorithmName() {
        return "Doing Nothing";
    }
}
