package com.cicdez.movingrobot;

public class Robot {
    private final Board board;
    private int x, y;
    private final IRobotAlgorithm algorithm;
    private final int maxDelay;
    private int delay;

    public Robot(Board board, IRobotAlgorithm algorithm, int x, int y, int delay) {
        this.board = board;
        this.board.setRobot(this);
        this.algorithm = algorithm;
        this.x = x;
        this.y = y;
        this.maxDelay = delay;
        this.delay = 0;
    }

    public boolean step() {
        delay++;
        if (delay > maxDelay) {
            delay = 0;
            return algorithm.step(this);
        }
        else return true;
    }

    public boolean canMoveTo(int dir) {
        switch (dir) {
            case Board.UP: return !board.hasUpWall(x, y) && y != 0;
            case Board.RIGHT: return !board.hasRightWall(x, y) && x != board.getWidth() - 1;
            case Board.DOWN: return !board.hasDownWall(x, y) && y != board.getHeight() - 1;
            case Board.LEFT: return !board.hasLeftWall(x, y) && x != 0;
            default: return false;
        }
    }
    public void moveTo(int dir) {
        if (canMoveTo(dir)) {
            switch (dir) {
                case Board.UP: {
                    y -= 1;
                    break;
                }
                case Board.RIGHT: {
                    x += 1;
                    break;
                }
                case Board.DOWN: {
                    y += 1;
                    break;
                }
                case Board.LEFT: {
                    x -= 1;
                    break;
                }
            }
        }
    }
    public void paint() {
        board.setPainted(x, y);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
