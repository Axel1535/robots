package fr.ensibs.robots;

public class TestCombat {
    public static void main(String[] args) {
        System.out.println("=== INITIALISATION DU COMBAT ===");
        
        // 1. Création du terrain (1000x1000 pixels)
        SimpleBattlefield battlefield = new SimpleBattlefield(1000, 1000);
        
        // 2. Création des combattants avec 50 PV chacun
        Robot shooter = new Robot("Shooter", 50); 
        Robot target = new Robot("Target", 50);   
        
        // 3. Positionnement pour le test
        // Shooter à gauche (100, 100), Target à droite (200, 100) -> Distance = 100 px
        shooter.setposition(100, 100);
        target.setposition(200, 100); 
        
        // On tourne le canon du shooter vers 0° pour viser la Target.
        // ATTENTION : Cette action consomme un peu d'énergie (ex: 0.5).
        shooter.turnGun(0); 
        
        // --- CORRECTION POUR LE TEST ---
        // On remet l'énergie à 50 pile pour que le test mathématique soit exact.
        if (shooter.getEnergy() < 50.0) {
             shooter.addEnergy(50.0 - shooter.getEnergy());
        }
        // -------------------------------

        // Ajout des robots au moteur de jeu
        battlefield.addDroid(shooter);
        battlefield.addDroid(target);
        
        System.out.println("État initial (Corrigé) :");
        System.out.println(" -> " + shooter);
        System.out.println(" -> " + target);
        
        // 4. ACTION : Le shooter tire !
        // Puissance 2.0 (Coût : 2.0 énergie)
        // Dégâts prévus : 4*2 + 2*(2-1) = 10 dégâts
        // Gain prévu : 3*2 = 6 énergie
        System.out.println("\n=== ACTION : TIR (Puissance 2.0) ===");
        shooter.fire(2.0); 
        
        // À cet instant : Shooter a 48 PV (50 - 2).
        
        // 5. SIMULATION (Boucle de jeu)
        // La balle va à vitesse 15. Distance 100. Environ 7 tours pour l'impact.
        System.out.println("\n=== SIMULATION DU TEMPS (10 tours) ===");
        
        for (int i = 1; i <= 10; i++) {
            // A. Déplacement des balles
            for (Bullet b : battlefield.getBullets()) {
                if (b.isActive()) {
                    b.move();
                    // B. Vérification des collisions
                    if (b.checkCollision(target)) {
                        System.out.println(" [X] IMPACT CONFIRMÉ au tour " + i + " !");
                    }
                }
            }
            // C. Refroidissement des canons
            shooter.update();
            target.update();
        }
        
        // 6. RÉSULTAT FINAL
        System.out.println("\n=== RÉSULTAT FINAL ===");
        
        // Calcul Shooter : 48 (après tir) + 6 (gain) = 54.0 PV
        System.out.println(shooter.getName() + " (Attendu: 54.0) : " + shooter.getEnergy()); 
        
        // Calcul Target : 50 (départ) - 10 (dégâts) = 40.0 PV
        System.out.println(target.getName() + " (Attendu: 40.0) : " + target.getEnergy());
        
        // Vérification automatique
        if (Math.abs(shooter.getEnergy() - 54.0) < 0.01 && Math.abs(target.getEnergy() - 40.0) < 0.01) {
            System.out.println("\n>>> TEST RÉUSSI : La mécanique de combat est validée ! <<<");
        } else {
            System.out.println("\n>>> ECHEC DU TEST : Les valeurs sont incorrectes. <<<");
        }
    }
}