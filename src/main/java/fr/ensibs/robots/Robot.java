package fr.ensibs.robots;

public class Robot extends Droid {

    private static final double RADAR_FOV = 20.0;
    private double radarHeading; 

    public Robot(String name, double initialEnergy) {
        super(name, initialEnergy);
        this.radarHeading = 0.0;
    }

    // Gestion des mouvements

    @Override
    public void turnBody(double angle) {
        if (!isAlive()) return;
        
        super.turnBody(angle); // Met à jour bodyHeading et gunHeading
        this.radarHeading += angle; // Le radar est sur le canon, donc il tourne aussi
        
        normalizeRadar();
    }

    @Override
    public void turnGun(double angle) {
        if (!isAlive()) return;

        super.turnGun(angle); 
        this.radarHeading += angle; // Le radar tourne si le canon tourne
        
        normalizeRadar();
    }

    public void turnRadar(double angle) {
        if (!isAlive()) return;

        this.radarHeading += angle;
        consumeEnergy(0.1); 
        
        normalizeRadar();
    }


    public void scan() {
        if (!isAlive()) return;
        System.out.println(getName() + " scanne à l'angle " + String.format("%.2f", radarHeading));
        // Récupérer tous les robots du champ de bataille
        for (Droid other : battlefield.getDroids()) {
            // Ne pas se détecter soi-même
            if (other == this || !other.isAlive()) continue;

            // Calculer distance
            double dx = other.getX() - this.getX();
            double dy = other.getY() - this.getY();

            // Calculer l'angle 
            double angleToTargetRad = Math.atan2(dy, dx);
            double angleToTargetDeg = Math.toDegrees(angleToTargetRad);

            // Ajuster l'angle pour qu'il soit positif 
            if (angleToTargetDeg < 0) {
                angleToTargetDeg += 360.0;
            }

            // Vérifie si cet angle est dans la zone du radar 
            double diff = Math.abs(angleToTargetDeg - this.getRadarHeading());
            if (diff > 180) {
                diff = 360 - diff;
            }

            // Si la différence est inférieure à la moitié du champ de vision, on le voit !
            if (diff <= RADAR_FOV / 2.0) {
                System.out.println(" !!! ENNEMI DÉTECTÉ : " + ((other instanceof Robot) ? ((Robot)other).getName() : "Droid") + " à " + String.format("%.1f", diff) + " degrés du centre du radar.");
            }
        }
    }
    

    public double getRadarHeading() {
        return radarHeading;
    }
    
    private void normalizeRadar() {
        this.radarHeading = this.radarHeading % 360;
    }
    
    @Override
    public String toString() {
        return "Robot " + getName() + " [Energy=" + energy + ", GunHeat=" + gunHeat + ", RadarAngle=" + radarHeading + "]";
    }
}
