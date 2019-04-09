/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zygmut
 */
public class Wooden_sword extends Item {

    int accuracy = 10;
    int damage = 20;
    
    public Wooden_sword() {
        name = "Wooden sword";
        id = "Wooden_sword";
        desc = "a wood sword lies in front of you";
        fulldesc = "A sword made purely by wood. Maybe it belonged to a child in the past";
        isWeareable = true;
        wearloc = "wield";
    }
}
