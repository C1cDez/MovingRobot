package com.cicdez.movingrobot.algorithms;

import com.cicdez.movingrobot.IRobotAlgorithm;
import com.cicdez.movingrobot.Robot;

public class PatternDrawingAlgorithm implements IRobotAlgorithm {
    private final String name;
    private final int[] pattern;

    private int done;

    public PatternDrawingAlgorithm(String name, int[] pattern) {
        this.name = name;
        this.pattern = pattern;
        this.done = 0;
    }

    //LEFT - paint and go left
    //~LEFT - go left
    @Override
    public boolean step(Robot robot) {
        int action = pattern[done];
        if (action >= 0) {
            robot.paint();
            robot.moveTo(action);
        } else {
            robot.moveTo(~action);
        }
        done++;
        return done < pattern.length;
    }

    @Override
    public String getAlgorithmName() {
        return name;
    }
}
