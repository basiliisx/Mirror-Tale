/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zygmut
 */
public abstract class NPC {

    String name;
    String desc;
    String id = "NPC";
    Integer MAXhp;
    Integer hp;
    Integer accuracy;
    Integer armour;
    Integer atack;
    Boolean canAtack;

    public String look() {
        String stats;
        stats = ("HP: " + MAXhp + '/' + hp) + "\n" + ("ACC: " + accuracy);
        return stats;
    }

}
