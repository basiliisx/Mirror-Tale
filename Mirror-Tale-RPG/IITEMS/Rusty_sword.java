/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zygmut
 */
public class Rusty_sword extends Item {

    int accuracy = 10;
    int damage = 20;
    
    public Rusty_sword() {
        name = "Rusty sword";
        id = "Rusty_sword";
        desc = "a rusty sword lies in front of you";
        fulldesc = "A sword given to the poorest fella around. What a huge mess";
        isWeareable = true;
        wearloc = "wield";
    }
}
