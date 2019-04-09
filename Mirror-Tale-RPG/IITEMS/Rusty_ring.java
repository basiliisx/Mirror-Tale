/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zygmut
 */
public class Rusty_ring extends Item {

    int accuracy = 10;
    int damage = 20;
    
    public Rusty_ring() {
        name = "Rusty ring";
        id = "Rusty_ring";
        desc = "Some sort of rust onion ring lies in front of you";
        fulldesc = "Why would someone put this thing on, you'll get 7 different types of tetanus";
        isWeareable = true;
        wearloc = "finger";
    }
}
