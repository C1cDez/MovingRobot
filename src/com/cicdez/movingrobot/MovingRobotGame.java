package com.cicdez.movingrobot;

import java.util.function.Consumer;

public class MovingRobotGame {
    private final Board board;
    private final Robot robot;
    private final Window window;
    private final Screen screen;

    private final Thread simulation, render;

    private boolean running;

    public MovingRobotGame(int boardWidth, int boardHeight, int robotX, int robotY, Consumer<Board> wallGenerator,
                           IRobotAlgorithm algorithm, int robotDelay) {
        this.board = new Board(boardWidth, boardHeight);
        wallGenerator.accept(board);
        this.robot = new Robot(board, algorithm, robotX, robotY, robotDelay);
        this.screen = new Screen(board);
        this.window = new Window(board, screen, algorithm.getAlgorithmName());

        this.simulation = new Thread(new Loop(this::update, 20), "Simulation");
        this.render = new Thread(new Loop(screen::prerender, 60), "Render");
    }

    private static MovingRobotGame GAME;
    public static MovingRobotGame create(int boardWidth, int boardHeight, int robotX, int robotY,
                                         Consumer<Board> wallGenerator, IRobotAlgorithm algorithm, int robotDelay) {
        if (GAME == null) GAME = new MovingRobotGame(boardWidth, boardHeight, robotX, robotY,
                wallGenerator, algorithm, robotDelay);
        return GAME;
    }

    public void start() {
        running = true;
        window.setVisible(true);
        simulation.start();
        render.start();
    }

    public void update() {
        if (!running) return;
        running = robot.step();
    }
}
