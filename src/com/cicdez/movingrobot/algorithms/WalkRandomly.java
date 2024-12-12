package com.cicdez.movingrobot.algorithms;

import com.cicdez.movingrobot.IRobotAlgorithm;
import com.cicdez.movingrobot.Robot;

import java.util.Random;

public class WalkRandomly implements IRobotAlgorithm {
    private final int maxSteps;
    private int step;
    private final Random random;

    public WalkRandomly(int steps, long seed) {
        this.maxSteps = steps;
        this.step = 0;
        this.random = new Random(seed);
    }

    @Override
    public boolean step(Robot robot) {
        step++;
        robot.paint();
        int dir = random.nextInt(4) + 1;
        robot.moveTo(dir);
        return step < maxSteps;
    }

    @Override
    public String getAlgorithmName() {
        return "Random Walking";
    }
}
