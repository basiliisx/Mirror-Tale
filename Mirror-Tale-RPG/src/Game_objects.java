/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zygmut
 */
public class Game_objects {

    static PC pc = new PC();
    static ArrayList<Room> room = new ArrayList<Room>();

    static List<Object> ITEMDataBase = new ArrayList<Object>();
    static List<Object> NPCDataBase = new ArrayList<Object>();

    /**
     * Base de datos de NPCs. Si uno quiere añadir otro NPC tambien debe ponerlo
     * aqui
     */
    public static void initializeNPCArray() {
        NPCDataBase.add(new Troll());
        NPCDataBase.add(new Skeleton());
    }

    /**
     * Base de datos de Items. Si uno quiere añadir otro item tambien debe
     * ponerlo aqui
     */
    public static void initializeITEMArray() {
        ITEMDataBase.add(new Rusty_sword());
        ITEMDataBase.add(new Rusty_shield());
        ITEMDataBase.add(new Rusty_ring());
        ITEMDataBase.add(new Wooden_sword());
        ITEMDataBase.add(new Health_potion());
    }
}
