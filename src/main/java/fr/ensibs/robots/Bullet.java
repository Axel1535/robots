package fr.ensibs.robots;

public class Bullet {
    private Droid owner; // Le robot qui a tiré pour le score/énergie
    private double x;
    private double y;
    private double heading;
    private double power;
    private double speed;
    private boolean active;
    private static final double BULLET_SPEED = 15.0;

    public Bullet(Droid owner, double x, double y, double heading, double power) {
        this.owner = owner;
        this.x = x;
        this.y = y;
        this.heading = heading;
        this.power = power;
        this.speed = BULLET_SPEED;
        this.active = true;
    }

    public void move() {
        double rad = Math.toRadians(heading);
        this.x += Math.cos(rad) * speed;
        this.y += Math.sin(rad) * speed;
    }

    public boolean checkCollision(Droid target) {
        if (!active || target == owner || !target.isAlive()) return false;

        double dx = target.getX() - this.x;
        double dy = target.getY() - this.y;
        double distance = Math.sqrt(dx*dx + dy*dy);

        // Seuil de collision arbitraire (taille du robot)
        if (distance < 20.0) {
            active = false; // La balle disparait
            applyDamage(target);
            return true;
        }
        return false;
    }

    private void applyDamage(Droid target) {
        double damage = 4 * power;
        if (power > 1) {
            damage += 2 * (power - 1);
        }

        // Infliger les dégâts
        target.takeDamage(damage); 

        // Le tireur gagne de l'énergie
        if (owner.isAlive()) {
            double gain = 3 * power;
            owner.addEnergy(gain); 
            System.out.println(owner.getName() + " touche ! (Dégâts: " + damage + ", Gain: " + gain + ")");
        }
    }

    public boolean isActive() {
        return active;
    }
    
    public double getX() { return x; }
    public double getY() { return y; }
}