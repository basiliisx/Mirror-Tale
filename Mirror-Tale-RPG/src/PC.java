
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
    Integer hp;
    Integer accuracy;
    Integer inRoom = 0;
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Item> wornitems = new ArrayList<>();

    public String look() {
        String stats;
        stats = ("HP: " + hp) + "\n" + ("ACC: " + accuracy);
        return stats;
    }

    public void wear(String[] x) {
        if (wornitems.isEmpty()) {
            for (int i = 0; i < items.size(); i++) {
                if (x[1].equalsIgnoreCase(items.get(i).id) && items.get(i).isWeareable) {
                    wornitems.add(items.get(i));
                    System.out.println("You're now wearing a " + items.get(i).name);
                    items.remove(i);
                    break;
                }
            }
        } else {
            boolean isWearing = false;
            for (int i = 0; i < wornitems.size(); i++) {
                for (int j = 0; j < items.size(); j++) {
                    if (x[1].equalsIgnoreCase(items.get(j).id) && items.get(j).isWeareable) {
                        if (items.get(i).wearloc.equals(wornitems.get(j).wearloc)) {
                            System.out.println("You already have something worn in that location");
                            isWearing = true;
                        }
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

    public void remove(String[] x) {
        System.out.println(wornitems.size());
        for (int i = 0; i < wornitems.size(); i++) {
            if (x[1].equalsIgnoreCase(wornitems.get(i).id)) {
                System.out.println("You remove " + wornitems.get(i).name);
                items.add(wornitems.get(i));
                wornitems.remove(i);
            }
        }
    }

    public void equipment() {
        for (int i = 0; i < wornitems.size(); i++) {
            System.out.println(wornitems.get(i).name + " : " + wornitems.get(i).wearloc);
        }
    }

}
