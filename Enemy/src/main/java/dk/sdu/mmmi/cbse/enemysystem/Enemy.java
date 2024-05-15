package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity {

    // In the Enemy class
    private int frameCounter = 0; // Counts frames since last shot
    private int bulletsFired = 0; // Tracks the number of bullets fired

    public int getFrameCounter() {
        return frameCounter;
    }

    public void setFrameCounter(int frameCounter) {
        this.frameCounter = frameCounter;
    }

    public int getBulletsFired() {
        return bulletsFired;
    }

    public void setBulletsFired(int bulletsFired) {
        this.bulletsFired = bulletsFired;
    }
}
