package com.cicdez.movingrobot;

public class Loop implements Runnable {
    private final Runnable task;
    private final double interval;
    
    public Loop(Runnable task, int tps) {
        this.task = task;
        this.interval = 1e9 / tps;
    }
    
    @Override
    public void run() {
        double nextInterval = System.nanoTime() + interval;
        while (true) {
            task.run();
            double remaining = (nextInterval - System.nanoTime()) / 1e6;
            if (remaining < 0) remaining = 0;
            try {
                Thread.sleep((long) remaining);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            nextInterval += interval;
        }
    }
}
