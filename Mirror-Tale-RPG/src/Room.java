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
public class Room {
    Integer number;
    String name;
    List<String> desc = new ArrayList<>();
    List<String> exits = new ArrayList<>();
    List<NPC> npc = new ArrayList<>();
    ArrayList<Item> item = new ArrayList<>();
/**
 * Constructor de una sala
 * @param x 
 */
    public Room(int x) {
        number = x;
    }
    
}
