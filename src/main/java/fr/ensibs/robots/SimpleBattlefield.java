package fr.ensibs.robots;

import java.util.ArrayList;
import java.util.List;

public class SimpleBattlefield implements Battlefield {
    private List<Droid> droids;
    private double width;
    private double height;

    public SimpleBattlefield(double width, double height) {
        this.width = width;
        this.height = height;
        this.droids = new ArrayList<>();
    }

    public void addDroid(Droid droid) {
        droids.add(droid);
        droid.setBattlefield(this); // Important : On dit au robot "Tu es sur ce terrain"
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