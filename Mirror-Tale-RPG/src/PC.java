
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Zygmut
 */
public class PC {

    String name;
    private Integer MAXHP;
    Integer HP;
    Integer ACC;
    Integer ARM = 0;
    Integer inRoom = 0;
    Integer inventorySize = 5;
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Item> wornitems = new ArrayList<>(2);

    public String look() {
        String stats;
        stats = ("HP: " + MAXHP + '/' + HP) + "\n" + ("ACC: " + ACC);
        return stats;
    }

    public void wear(String[] x) {

        if (x.length == 1) {
            System.out.println("Wear what?");
        }
        if (x.length == 2) {
            if (wornitems.isEmpty()) {
                for (int i = 0; i < items.size(); i++) {
                    if (x[1].equalsIgnoreCase(items.get(i).id) && items.get(i).isWeareable) {
                        if (items.get(i).getClass().equals("Trinked")) {
                            System.out.println("trinked");
                        }
                        wornitems.add(items.get(i));
                        System.out.println("You're now wearing a " + items.get(i).name);
                        items.remove(i);
                        break;
                    }else{
                        System.out.println("You cannot wear that"); //NotWeareableException
                        break;
                    }
                }
            } else {
                boolean isWearing = false;
                for (int i = 0; i < wornitems.size(); i++) {
                    for (int j = 0; j < items.size(); j++) {
                        if (x[1].equalsIgnoreCase(items.get(j).id) && items.get(j).isWeareable) {
                            if (items.get(j).wearloc.equals(wornitems.get(i).wearloc)) {
                                System.out.println("You already have something worn in that location");
                                isWearing = true;
                            }
                        }else{
                                System.out.println("You cannot wear that"); //NotWeareableException
                                break;
                            }
                    }
                    if (!isWearing) {
                        wornitems.add(items.get(i));
                        System.out.println("You're now wearing a " + items.get(i).name);
                        items.remove(i);
                        break;
                    }
                }
            }
        }
    }

    public void remove(String[] x) {
        if (x.length == 1) {
            System.out.println("remove what");
        }
        if (x.length == 2) {
            for (int i = 0; i < wornitems.size(); i++) {
                if (x[1].equalsIgnoreCase(wornitems.get(i).id)) {
                    System.out.println("You remove " + wornitems.get(i).name);
                    items.add(wornitems.get(i));
                    wornitems.remove(i);
                    System.out.println(items.size());
                    System.out.println(wornitems.size());
                }
            }
        }

    }

    /**
     * Permite eliminar objetos del inventario del usuario
     *
     * @param x
     */
    public void drop(String[] x) {
        if (x.length == 1) {
            System.out.println("Drop what?");
        }
        if (x.length == 2) {
            for (int i = 0; i < Game_objects.pc.items.size(); i++) {
                if (x[1].equalsIgnoreCase(Game_objects.pc.items.get(i).id)) {
                    for (int j = 0; j < Game_objects.room.size(); j++) {
                        if (Game_objects.room.get(j).number == Game_objects.pc.inRoom) {
                            Game_objects.room.get(j).item.add(Game_objects.pc.items.get(i));
                            System.out.println("You droped a " + Game_objects.pc.items.get(i).name);
                            Game_objects.pc.items.remove(i);
                        }
                    }
                }
            }
        }
    }

    public void setMAXHP(Integer MAXHP) {
        this.MAXHP = MAXHP;
        HP = MAXHP;
    }
    
}
