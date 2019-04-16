/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zygmut
 */
public abstract class Trinked extends Item {
    Integer ARM;
    Integer InventorySpace;
    Integer ACC;
    
    public Trinked(){
        isWeareable = true;
        wearloc = "Trinked";
    }
}
