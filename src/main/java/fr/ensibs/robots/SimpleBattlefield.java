package fr.ensibs.robots;

import java.util.ArrayList;
import java.util.List;

public class SimpleBattlefield implements Battlefield {
    private List<Droid> droids;
    private double width;
    private double height;
    private List<Bullet> bullets;

    public SimpleBattlefield(double width, double height) {
        this.width = width;
        this.height = height;
        this.droids = new ArrayList<>();
        this.bullets = new ArrayList<>();
    }

    public void addDroid(Droid droid) {
        droids.add(droid);
        droid.setBattlefield(this); // On dit au robot "Tu es sur ce terrain"
    }

    @Override
    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    @Override
    public List<Droid> getDroids() {
        return droids;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }
}