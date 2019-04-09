/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirror.tale.rpg;

/**
 *
 * @author Zygmut
 */
public class PC {

    String name;
    Integer hp;
    Integer accuracy;
    Integer inRoom = 0;

    public String look() {
        String stats;
        stats = ("Hp: " + hp) + "\n" + ("Accuracy: " + accuracy);
        return stats;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" + "Hp: " + hp + "\n" + "Accuracy: " + accuracy;
    }

}
