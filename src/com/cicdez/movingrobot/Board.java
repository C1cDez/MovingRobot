package com.cicdez.movingrobot;

public class Board {
    private final int width, height;
    private final int[][] cells;

    private Robot robot;

    public static final int PAINTED = 0, UP = 1, RIGHT = 2, DOWN = 3, LEFT = 4;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new int[width][height];
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public int getCell(int x, int y) {
        return isPossiblePosition(x, y) ? cells[x][y] : -1;
    }

    public boolean isPainted(int x, int y) {
        return (getCell(x, y) >> PAINTED & 0b1) == 1;
    }
    public boolean hasWall(int x, int y) {
        return (getCell(x, y) >> 1 & 0b1111) != 0;
    }
    public boolean hasUpWall(int x, int y) {
        return (getCell(x, y) >> UP & 0b1) == 1;
    }
    public boolean hasRightWall(int x, int y) {
        return (getCell(x, y) >> RIGHT & 0b1) == 1;
    }
    public boolean hasDownWall(int x, int y) {
        return (getCell(x, y) >> DOWN & 0b1) == 1;
    }
    public boolean hasLeftWall(int x, int y) {
        return (getCell(x, y) >> LEFT & 0b1) == 1;
    }


    private boolean isPossiblePosition(int x, int y) {
        return (0 <= x && x < width) && (0 <= y && y < height);
    }
    public void setCell(int x, int y, int value) {
        if (isPossiblePosition(x, y)) cells[x][y] = value;
    }
    public void setCell(int x, int y, int painted, int up, int right, int down, int left) {
        int value = (painted & 0b1 << PAINTED) |
                (up & 0b1 << UP) |
                (right & 0b1 << RIGHT) |
                (down & 0b1 << DOWN) |
                (left & 0b1 << LEFT);
        setCell(x, y, value);
    }

    public void setPainted(int x, int y) {
        setCell(x, y, getCell(x, y) | (1 << PAINTED));
    }
    public void setUpWall(int x, int y) {
        setCell(x, y, getCell(x, y) | (1 << UP));
    }
    public void setRightWall(int x, int y) {
        setCell(x, y, getCell(x, y) | (1 << RIGHT));
    }
    public void setDownWall(int x, int y) {
        setCell(x, y, getCell(x, y) | (1 << DOWN));
    }
    public void setLeftWall(int x, int y) {
        setCell(x, y, getCell(x, y) | (1 << LEFT));
    }

    public void wall(int x, int y, int dir) {
        switch (dir) {
            case UP: {
                setUpWall(x, y);
                setDownWall(x, y - 1);
                break;
            }
            case RIGHT: {
                setRightWall(x, y);
                setLeftWall(x + 1, y);
                break;
            }
            case DOWN: {
                setDownWall(x, y);
                setUpWall(x, y + 1);
                break;
            }
            case LEFT: {
                setLeftWall(x, y);
                setRightWall(x - 1, y);
                break;
            }
        }
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }
    public Robot getRobot() {
        return robot;
    }
}
