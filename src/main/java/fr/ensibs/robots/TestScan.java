package fr.ensibs.robots;

public class TestScan {
    public static void main(String[] args) {
        // 1. Créer le terrain (800x600)
        SimpleBattlefield battlefield = new SimpleBattlefield(800, 600);

        // 2. Créer deux robots
        Robot robotA = new Robot("Terminator", 100);
        Robot robotB = new Robot("Cible", 100);

        // 3. Les positionner
        robotA.setposition(0, 0);
        robotB.setposition(100, 100); // Situé à 45° par rapport à (0,0)

        // 4. Ajouter au terrain
        battlefield.addDroid(robotA);
        battlefield.addDroid(robotB);

        System.out.println("--- TEST 1 : Radar mal orienté (0°) ---");
        // Le radar est à 0° par défaut. La cible est à 45°. 
        // Avec un champ de vision de 20°, on ne devrait PAS le voir.
        robotA.scan(); 

        System.out.println("\n--- TEST 2 : Radar bien orienté (45°) ---");
        // On tourne le radar vers la cible
        robotA.turnRadar(45); 
        robotA.scan(); // Devrait afficher "ENNEMI DÉTECTÉ"
    }
}