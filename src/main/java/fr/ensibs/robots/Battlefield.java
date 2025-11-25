package fr.ensibs.robots;

import java.util.List;


public interface Battlefield {
    List<Droid> getDroids(); // liste des robots sur le terrain 
    double getWidth(); // largeur terrain 
    double getHeight(); // hauteur terrain
    void addBullet(Bullet bullet);
}