/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirror.tale.rpg;

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

    public void processCommand(String[] x) {
        if (x[0].equals("look")) {
            look(x);
        }
        if (x[0].equals("exitgame")) {
            exitgame();
        }
    }

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
                }
            }
        }
        if (x.length == 2) {
            if (x[1].equals("self")) {
                System.out.println(Game_objects.pc.look());
            }
        }
    }

    public void exitgame() {
        System.out.println("Bye hero!");
        System.exit(0);
    }
}
