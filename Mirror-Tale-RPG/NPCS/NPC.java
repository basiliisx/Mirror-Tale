/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zygmut
 */
public class NPC {

    String name = "an NPC";
    String desc = "A simple NPC";
    String id = "NPC";
    int hp;
    int accuracy;

    public String look() {
        String stats;
        stats = ("HP: " + hp) + "\n" + ("ACC: " + accuracy);
        return stats;
    }

}
