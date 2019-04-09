/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author Zygmut
 */
public class Game_Logic {

    public Game_Logic() {
        //Creacion de una sala
        Game_objects.room.add(new Room(1));
        Game_objects.room.get(0).name = "Floating island";
        Game_objects.room.get(0).desc.add("Description 1");
        Game_objects.room.get(0).desc.add("Description 2");
        Game_objects.room.get(0).desc.add("Description 3");
        Game_objects.room.get(0).desc.add("Description 4");
        Game_objects.room.get(0).exits.add("North 2");
        Game_objects.room.get(0).exits.add("South 3");
    }

    /**
     * Permite escuhar las ordenes del heroe
     */
    public void waitforCommand() {
        if (Game_objects.pc.inRoom == 0) {
            createCharacter();
        }
        System.out.println("\n" + "What do?");
        Scanner sc = new Scanner(System.in);
        String com = sc.nextLine();

        String[] words = com.split(" ");
        processCommand(words);
    }

    /**
     * MEGA SWITCH. permite identificar las ordenes. En un futuro se puede hacer
     * más bonito mediante una clase enum y un switch correspondiente
     *
     * @param x
     */
    public void processCommand(String[] x) {
        if (x[0].equals("look")) {
            look(x);
        }
        if (x[0].equals("exitgame")) {
            exitgame();
        }
        if (x[0].equals("summon")) {
            summon(x);
        }
        if (x[0].equals("create")) {
            create_item(x);
        }
        if (x[0].equals("get")) {
            get(x);
        }
        if (x[0].equals("wear")) {
            Game_objects.pc.wear(x);
        }
        if (x[0].equals("eq")) {
            Game_objects.pc.equipment();
        }
        if (x[0].equals("remove")) {
            Game_objects.pc.remove(x);
        }
    }

    /**
     * Permite al usuario crear el heroe
     */
    public void createCharacter() {
        System.out.println("Welcome to Mirror Tale. What's your name?");
        Scanner sc = new Scanner(System.in);
        Game_objects.pc.name = sc.nextLine();
        System.out.println("By the grace of the creator, you have been given some stats");
        Game_objects.pc.hp = 100;
        Game_objects.pc.accuracy = 75;
        Game_objects.pc.inRoom = 1;
        System.out.println(Game_objects.pc.look() + "\n" + "Those are your stats, hero");
    }

    /**
     * Permitie al heroe veer lo que hay en sus alrededores En el caso de que se
     * introduzcan mas de un parametro a la hora de ejecutar el comando se
     * podran observar ciertas estadisticas
     *
     * @param x
     */
    public void look(String[] x) {
        if (x.length == 1) {
            for (int i = 0; i < Game_objects.room.size(); i++) {
                if (Game_objects.room.get(i).number == Game_objects.pc.inRoom) {
                    System.out.println(Game_objects.room.get(i).name);
                    for (int j = 0; j < Game_objects.room.get(i).desc.size(); j++) {
                        System.out.println(Game_objects.room.get(i).desc.get(j));
                    }
                    System.out.println("\n" + "Exits:");
                    //Solo se imprime donde se situa la salida el numero servira para en un futuro saber donde uno se mueve y que salas se conectan
                    for (int j = 0; j < Game_objects.room.get(i).exits.size(); j++) {
                        String exitFullName = Game_objects.room.get(i).exits.get(j);
                        String[] exitName = exitFullName.split(" ");
                        System.out.println(exitName[0]);
                    }
                    for (int j = 0; j < Game_objects.room.get(i).npc.size(); j++) {
                        System.out.println(Game_objects.room.get(i).npc.get(j).desc);
                    }
                    for (int j = 0; j < Game_objects.room.get(i).item.size(); j++) {
                        System.out.println(Game_objects.room.get(i).item.get(j).desc);
                    }
                }
            }
        }
        if (x.length == 2) {
            if (x[1].equals("self")) {
                System.out.println(Game_objects.pc.look());
                System.out.println("You're carrying: ");
                for (int i = 0; i < Game_objects.pc.items.size(); i++) {
                    System.out.println(Game_objects.pc.items.get(i).name);
                }
            }
            for (int i = 0; i < Game_objects.room.size(); i++) {
                if (Game_objects.room.get(i).number == Game_objects.pc.inRoom) {

                    for (int j = 0; j < Game_objects.room.get(i).npc.size(); j++) {
                        if (x[1].equalsIgnoreCase(Game_objects.room.get(i).npc.get(j).id)) {
                            System.out.println(Game_objects.room.get(i).npc.get(j).look());
                        }
                    }
                    for (int j = 0; j < Game_objects.room.get(i).item.size(); j++) {
                        if (x[1].equalsIgnoreCase(Game_objects.room.get(i).item.get(j).id)) {
                            System.out.println(Game_objects.room.get(i).item.get(j).fulldesc);
                        }
                    }

                }
            }
        }
        if (x.length == 3) {
            if (x[1].equals("self")) {
                System.out.println("Item that you're carrying");
                boolean found = false;
                for (int i = 0; i < Game_objects.pc.items.size(); i++) {
                    if (!found) {
                        if (x[2].equalsIgnoreCase(Game_objects.pc.items.get(i).id)) {
                            System.out.println(Game_objects.pc.items.get(i).fulldesc);
                            found = true;
                            break;
                        }

                    }
                }
                for (int i = 0; i < Game_objects.pc.wornitems.size(); i++) {
                    if (!found) {
                        if (x[2].equalsIgnoreCase(Game_objects.pc.wornitems.get(i).id)) {
                            System.out.println(Game_objects.pc.wornitems.get(i).fulldesc);
                            break;
                        }

                    }
                }
            }

        }
    }

    /**
     * Permite crear objetos y soltarlos en la sala
     *
     * @param x
     */
    public void create_item(String[] x) {
        if (x.length == 1) {
            System.out.println("Create what?");
        }
        if (x.length == 2) {

            for (int i = 0; i < Game_objects.ITEMDataBase.size(); i++) {
                Item localItem = (Item) Game_objects.ITEMDataBase.get(i);

                if (localItem.id.equalsIgnoreCase(x[1])) {

                    for (int j = 0; j < Game_objects.room.size(); j++) {
                        if (Game_objects.room.get(j).number == Game_objects.pc.inRoom) {
                            try {
                                Game_objects.room.get(j).item.add((Item) Class.forName(localItem.id).newInstance());
                            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                                e.printStackTrace();
                            }
                            System.out.println("You have created a " + Game_objects.room.get(j).item.get(Game_objects.room.get(j).item.size() - 1).name);
                        }
                    }
                }
            }
        }
    }

    /**
     * Permite al usuario poder coger ciertos objetos
     *
     * @param x
     */
    public void get(String[] x) {

        if (x.length == 1) {
            System.out.println("Get what?");
        }
        if (x.length == 2) {
            for (int i = 0; i < Game_objects.ITEMDataBase.size(); i++) {
                for (int j = 0; j < Game_objects.room.size(); j++) {
                    if (Game_objects.room.get(j).number == Game_objects.pc.inRoom) {
                        for (int k = 0; k < Game_objects.room.get(j).item.size(); k++) {
                            if (x[1].equalsIgnoreCase(Game_objects.room.get(j).item.get(k).id)) {
                                Item localitem = (Item) Game_objects.room.get(j).item.get(k);

                                Game_objects.pc.items.add(localitem);
                                System.out.println("You picked up " + localitem.name);
                                Game_objects.room.get(j).item.remove(k);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Permite spawnear cualquier npc dentro de una sala siempre y cuando este
     * este registrado en la base de datos y tenga su propia clase
     *
     * @param x
     */
    public void summon(String[] x) {
        if (x.length == 1) {
            System.out.println("Summmon what?");
        }
        if (x.length == 2) {
            for (int i = 0; i < Game_objects.NPCDataBase.size(); i++) {
                NPC localNPC = (NPC) Game_objects.NPCDataBase.get(i);
                if (localNPC.id.equalsIgnoreCase(x[1])) {
                    for (int j = 0; j < Game_objects.room.size(); j++) {
                        if (Game_objects.room.get(j).number == Game_objects.pc.inRoom) {
                            try {
                                Game_objects.room.get(j).npc.add((NPC) Class.forName(localNPC.id).newInstance());
                                System.out.println("You've summoned " + Game_objects.room.get(j).npc.get(Game_objects.room.get(j).npc.size() - 1).name);
                            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Salir del juego. Simple
     */
    public void exitgame() {
        System.out.println("Bye hero!");
        System.exit(0);
    }
}
