/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zygmut
 */
public class Troll extends NPC {

    public Troll() {
        name = "a troll";
        desc = "A TROLL standing menacing";
        id = "Troll";
        MAXhp = 10;
        hp = MAXhp;
        accuracy = 25;
        armour = 5;
        atack = 5;
        canAtack = true;
    }

}
