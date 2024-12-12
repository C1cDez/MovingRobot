package com.cicdez.movingrobot;

import javax.swing.*;
import java.awt.*;
import java.awt.image.VolatileImage;

public class Screen extends JPanel {
    public static final int DEFAULT_WIDTH = 900;
    public static final int STRIPE_WIDTH = 2, WALL_STRIPE_WIDTH = 4;

    public static final Color BACKGROUND = new Color(130, 189, 100, 255),
            PAINTED_CELL = new Color(141, 70, 199),
            WALL = new Color(218, 184, 52),
            ROBOT = new Color(224, 224, 224);


    private final int width, height;
    private final Board board;
    private final float xDist, yDist;

    private VolatileImage image;

    public Screen(Board board) {
        this.board = board;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_WIDTH * board.getHeight() / board.getWidth();

        this.setPreferredSize(new Dimension(width, height));

        this.xDist = (float) width / board.getWidth();
        this.yDist = (float) height / board.getHeight();
    }

    public void prerender() {
        if (image == null || image.validate(this.getGraphicsConfiguration()) == VolatileImage.IMAGE_INCOMPATIBLE) {
            image = this.getGraphicsConfiguration().createCompatibleVolatileImage(width, height);
        }
        Graphics2D graphics = image.createGraphics();
        render(graphics);
        graphics.dispose();
        this.getGraphics().drawImage(image, 0, 0, null);
    }

    public void render(Graphics2D graphics) {
        graphics.setColor(BACKGROUND);
        graphics.fillRect(0, 0, width, height);

        graphics.setColor(PAINTED_CELL);
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (board.isPainted(x, y)) {
                    graphics.fillRect((int) (x * xDist), (int) (y * yDist), (int) xDist, (int) yDist);
                }
            }
        }

        graphics.setColor(Color.BLACK);
        for (int x = 0; x < board.getWidth() + 1; x++) {
            graphics.fillRect((int) (x * xDist - STRIPE_WIDTH), 0, STRIPE_WIDTH * 2, height);
        }
        for (int y = 0; y < board.getHeight() + 1; y++) {
            graphics.fillRect(0, (int) (y * yDist - STRIPE_WIDTH), width, STRIPE_WIDTH * 2);
        }

        graphics.setColor(WALL);
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                int rx = (int) (x * xDist), ry = (int) (y * yDist);
                if (board.hasUpWall(x, y)) {
                    graphics.fillRect(rx - WALL_STRIPE_WIDTH, ry - WALL_STRIPE_WIDTH,
                            (int) (WALL_STRIPE_WIDTH * 2 + xDist), WALL_STRIPE_WIDTH * 2);
                }
                if (board.hasRightWall(x, y)) {
                    graphics.fillRect((int) (rx + xDist - WALL_STRIPE_WIDTH), ry - WALL_STRIPE_WIDTH,
                            WALL_STRIPE_WIDTH * 2, (int) (WALL_STRIPE_WIDTH * 2 + yDist));
                }
                if (board.hasDownWall(x, y)) {
                    graphics.fillRect(rx - WALL_STRIPE_WIDTH, (int) (ry + yDist - WALL_STRIPE_WIDTH),
                            (int) (WALL_STRIPE_WIDTH * 2 + xDist), WALL_STRIPE_WIDTH * 2);
                }
                if (board.hasLeftWall(x, y)) {
                    graphics.fillRect(rx - WALL_STRIPE_WIDTH, ry - WALL_STRIPE_WIDTH,
                            WALL_STRIPE_WIDTH * 2, (int) (WALL_STRIPE_WIDTH * 2 + yDist));
                }
            }
        }

        graphics.setColor(ROBOT);
        Robot robot = board.getRobot();
        graphics.fillOval((int) (robot.getX() * xDist + xDist / 4), (int) (robot.getY() * yDist + yDist / 4),
                (int) (xDist / 2), (int) (yDist / 2));
    }
}
