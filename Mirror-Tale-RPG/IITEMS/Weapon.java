/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zygmut
 */
public abstract class Weapon extends Item {

    Integer ACC; //Accuracy
    Integer DMG; //Damage
    Integer TDMG; //True damage
    Integer ARM; //Armour (Shields)
    public Weapon() {
        isWeareable = true;
        wearloc = "hand";
    }
}
