package fr.ensibs.robots;

public class Droid {
    protected String name;
    protected double energy;
    protected double bodyHeading; // Orientation du corps 
    protected double gunHeading;  // Orientation du canon 
    protected double gunHeat;     // Niveau de chauffe du canon
    protected double x;
    protected double y;
    protected Battlefield battlefield; 


    private static final double MOVE_COST = 0.5; // c'est l'énergie qu'il perd quand il avance
    private static final double ROTATE_COST = 0.5; // c'est l'énergie qu'il perd quand il tourne 
    private static final double COOLING_RATE = 0.1; // taux de refroidissement du canon par tour

 
    public Droid(String name, double initialEnergy) {
        this.name = name;
        this.energy = initialEnergy;
        this.bodyHeading = 0.0;
        this.gunHeading = 0.0;
        this.gunHeat = 0.0;
        this.x = 0.0;
        this.y = 0.0;
    }

    public void setposition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setBattlefield(Battlefield battlefield) {
        this.battlefield = battlefield;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public double getEnergy() {
        return energy;
    }

    public boolean isAlive() {
        return energy > 0; // Le droïde est éliminé s'il n'a plus d'énergie 
    }

    protected void consumeEnergy(double amount) {
        if (amount < 0) amount = 0;
        this.energy -= amount;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    // --- Mouvements (Body & Gun) ---

    public void turnBody(double angle) {
        if (!isAlive()) return;

        this.bodyHeading += angle;
        this.gunHeading += angle; // Le canon est fixé au corps, il tourne avec lui si on tourne le corps
        
        consumeEnergy(ROTATE_COST); // La rotation consomme de l'énergie 
        normalizeHeadings();
    }


    public void turnGun(double angle) {
        if (!isAlive()) return;

        this.gunHeading += angle; // Seul le canon tourne
        
        consumeEnergy(ROTATE_COST);
        normalizeHeadings();
    }

    public void move() {
        if (!isAlive()) return;
        consumeEnergy(MOVE_COST); // Le mouvement consomme de l'énergie 
    }

    // --- Combat ---

    public void fire(double power) {
        if (!isAlive()) return;

        // Vérification de la surchauffe
        if (this.gunHeat > 0) {
            System.out.println(name + " ne peut pas tirer : Canon en surchauffe !");
            return;
        }

        // Vérification de l'énergie disponible
        if (power > this.energy) {
            power = this.energy; // On ne peut tirer que ce qu'on a
        }

        // Consommation de l'énergie
        this.energy -= power;

        // Augmentation de la chauffe (formule arbitraire : 1 + power/5)
        this.gunHeat += 1.0 + (power / 5.0);

        System.out.println(name + " tire avec une puissance de " + power + ". Énergie restante : " + this.energy);
    }

    public void update() {
        if (this.gunHeat > 0) {
            this.gunHeat -= COOLING_RATE;
            if (this.gunHeat < 0) this.gunHeat = 0;
        }
    }

    private void normalizeHeadings() {
        this.bodyHeading = this.bodyHeading % 360;
        this.gunHeading = this.gunHeading % 360;
    }
    
    @Override
    public String toString() {
        return "Droid " + name + " [Energy=" + energy + ", Heat=" + gunHeat + "]";
    }
}
