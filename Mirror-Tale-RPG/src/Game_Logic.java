/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Zygmut
 */
public class Game_Logic {

    /**
     * Creacion del mapa
     */
    public Game_Logic() {
        Game_objects.initializeNPCArray();
        Game_objects.initializeITEMArray();
        Game_objects.room.add(new Room(0));
        List<String> roomInfo = new ArrayList<>();
        try {
            roomInfo = readLines("Textfiles/RoomDescriptions.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < roomInfo.size(); i++) {
            String[] firstWord = roomInfo.get(i).split(" ");
            String[] everythigElse = roomInfo.get(i).split(": ");
            if (firstWord[0].equals("RoomName:")) {
                Integer currentRoomSize = Game_objects.room.size();
                Game_objects.room.add(new Room(currentRoomSize));
                Game_objects.room.get(Game_objects.room.size() - 1).name = everythigElse[1];
                Game_objects.room.get(Game_objects.room.size() - 1).number = currentRoomSize;

                int roomcount = 0;
                for (int j = 0; j < roomInfo.size(); j++) {
                    String[] nextFirstWord = roomInfo.get(j).split(" ");
                    if (nextFirstWord[0].equals("RoomName:")) {
                        roomcount++;
                    }
                    if (roomcount == currentRoomSize) {
                        if (nextFirstWord[0].equals("Desc:")) {
                            String[] nextEverythingElse = roomInfo.get(j).split(": ");
                            Game_objects.room.get(Game_objects.room.size() - 1).desc.add(nextEverythingElse[1]);
                        }
                    }
                }
                roomcount = 0;
                for (int j = 0; j < roomInfo.size(); j++) {
                    String[] nextFirstWord = roomInfo.get(j).split(" ");
                    if (nextFirstWord[0].equals("RoomName:")) {
                        roomcount++;
                    }
                    if (roomcount == currentRoomSize) {
                        if (nextFirstWord[0].equals("Exit:")) {
                            String[] nextEverythingElse = roomInfo.get(j).split(": ");
                            Game_objects.room.get(Game_objects.room.size() - 1).exits.add(nextEverythingElse[1]);
                        }
                    }
                }
            }
        }
        crear_mobs(roomInfo);
        crear_items(roomInfo);
    }

    /**
     * Summonea todos los mobs dentro del mapa
     *
     * @param x
     */
    private void crear_mobs(List<String> x) {
        try {
            x = readLines("Textfiles/mobs.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < x.size(); i++) {
            String[] mobs = x.get(i).split(" ");

            for (int j = 0; j < Game_objects.NPCDataBase.size(); j++) {
                NPC localNPC = (NPC) Game_objects.NPCDataBase.get(j);
                if (localNPC.id.equalsIgnoreCase(mobs[0])) {
                    for (int k = 0; k < Game_objects.room.size(); k++) {
                        if (Game_objects.room.get(k).number == Integer.parseInt(mobs[1])) {
                            try {
                                Game_objects.room.get(k).npc.add((NPC) Class.forName(localNPC.id).newInstance());
                                System.out.println("You've summoned " + Game_objects.room.get(k).npc.get(Game_objects.room.get(k).npc.size() - 1).name);
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
     * Crea todos los objetos dentro del mapa
     *
     * @param x
     */
    private void crear_items(List<String> x) {
        try {
            x = readLines("Textfiles/items.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < x.size(); i++) {
            String[] items = x.get(i).split(" ");

            for (int j = 0; j < Game_objects.ITEMDataBase.size(); j++) {
                Item localITEM = (Item) Game_objects.ITEMDataBase.get(j);
                if (localITEM.id.equalsIgnoreCase(items[0])) {
                    for (int k = 0; k < Game_objects.room.size(); k++) {
                        if (Game_objects.room.get(k).number == Integer.parseInt(items[1])) {
                            try {
                                Game_objects.room.get(k).item.add((Item) Class.forName(localITEM.id).newInstance());
                                System.out.println("You've summoned " + Game_objects.room.get(k).item.get(Game_objects.room.get(k).item.size() - 1).name);
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

    public enum commands {
        look, exit, get, wear, remove, help, move, drop
    }

    /**
     * MEGA SWITCH. permite identificar las ordenes. En un futuro se puede hacer
     * más bonito mediante una clase enum y un switch correspondiente
     *
     * @param x
     */
    public void processCommand(String[] x) {
        switch (x[0]) {
            case "look":
                look(x);
                break;
            case "get":
                get(x);
                break;
            case "wear":
                Game_objects.pc.wear(x);
                break;
            case "drop":
                Game_objects.pc.drop(x);
                break;
            case "remove":
                Game_objects.pc.remove(x);
                break;
            case "move":
                move(x);
                break;
            case "help":
                help(x);
                break;
            case "exit":
                exitgame();
                break;
            default:
                System.out.println("Not a valid option");
                break;

        }
    }

    /**
     * Lee las lineas de el archivo de texto en el que se encuentra el nivel
     *
     * @param path
     * @return
     * @throws IOException
     */
    private List<String> readLines(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        return lines;
    }

    /**
     * Permite al usuario crear el heroe
     */
    public void createCharacter() {
        System.out.println("Welcome to Mirror Tale. What's your name?");
        Scanner sc = new Scanner(System.in);
        Game_objects.pc.name = sc.nextLine();
        System.out.println("By the grace of the creator, you have been given some stats");
        Game_objects.pc.setMAXHP(100);
        Game_objects.pc.ACC = 75;
        Game_objects.pc.inRoom = 1;
        System.out.println(Game_objects.pc.look() + "\n" + "Those are your stats, hero" + "\n\n");
        String[] nll = {"null"};
        look(nll);
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
                System.out.println("Inventory:");
                for (int i = 0; i < Game_objects.pc.items.size(); i++) {
                    System.out.println(Game_objects.pc.items.get(i).name);
                }
                System.out.println("Wearing:");
                for (int i = 0; i < Game_objects.pc.wornitems.size(); i++) {
                    System.out.println(Game_objects.pc.wornitems.get(i).name + " in " + Game_objects.pc.wornitems.get(i).wearloc);
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
     * Permite al jugador moverse a lo largo del mapa
     *
     * @param x
     */
    public void move(String[] x) {
        if (x.length == 1) {
            System.out.println("Move where?");
        }
        if (x.length == 2) {
            for (int i = 0; i < Game_objects.room.size(); i++) {
                if (Game_objects.room.get(i).number == Game_objects.pc.inRoom) {
                    for (int j = 0; j < Game_objects.room.get(i).exits.size(); j++) {

                        String exitRequested = Game_objects.room.get(i).exits.get(j);
                        String[] exitArray = exitRequested.split(" ");
                        if (x[1].equalsIgnoreCase(exitArray[0])) {
                            Game_objects.pc.inRoom = Integer.parseInt(exitArray[1]);
                            System.out.println("You leave " + exitArray[0] + "\n\n");
                            String[] y = {"null"};
                            look(y);
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
            Integer size = Game_objects.pc.items.size();
            Integer invsize = Game_objects.pc.inventorySize;
            if (size >= invsize) {
                System.out.println("You cannot get more objects");
            } else {
                for (int i = 0; i < Game_objects.ITEMDataBase.size(); i++) {
                    for (int j = 0; j < Game_objects.room.size(); j++) {
                        if (Game_objects.room.get(j).number == Game_objects.pc.inRoom) {
                            for (int k = 0; k < Game_objects.room.get(j).item.size(); k++) {
                                if (x[1].equalsIgnoreCase(Game_objects.room.get(j).item.get(k).id)) {
                                    Item localitem = (Item) Game_objects.room.get(j).item.get(k);
                                    Game_objects.pc.items.add(localitem);
                                    System.out.println("You picked up " + localitem.name);
                                    Game_objects.room.get(j).item.remove(k);
                                    return;
                                }
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
     * Imprime un String con todos los comandos que el usuario puede hacer
     *
     * @param x
     */
    public void help(String[] x) {
        String help = "";
        if (x.length == 1) {
            for (int i = 0; i < commands.values().length; i++) {
                help += commands.values()[i] + ", ";
            }
            System.out.println(help);
        }
        if (x.length == 2) {
            for (int i = 0; i < commands.values().length; i++) {
                if (x[1].equalsIgnoreCase(commands.values()[i].toString())) {
                    switch (commands.values()[i]) {
                        case look:
                            System.out.println("Allows you to see what's inside a room." + "\n" + "You can also check you'r stats by using look self and see other creature or item stats by doing so." + "\n" + "If you want to check an item you have, use look self and the item id");
                            break;
                        case exit:
                            System.out.println("Allows you to exit the game");
                            break;
                        case get:
                            System.out.println("Allows you to grab an item and put it in your inventory given the id");
                            break;
                        case wear:
                            System.out.println("Allows you to wear an item. Only worn items apply it's bonuses given the id");
                            break;
                        case remove:
                            System.out.println("Allows you to remove one worn item given the id");
                            break;
                        case help:
                            System.out.println("Allows you to see all user commands");
                            break;
                        case move:
                            System.out.println("Allows you to move to another room given the orientation");
                            break;
                        case drop:
                            System.out.println("Allows you to drop an item that you have in your inventory");
                            break;
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
