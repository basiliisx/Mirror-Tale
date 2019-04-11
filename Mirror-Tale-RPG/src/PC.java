
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
    Integer HP;
    Integer ACC;
    Integer ARM = 0;
    Integer inRoom = 0;
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Item> wornitems = new ArrayList<>();

    public String look() {
        String stats;
        stats = ("HP: " + HP) + "\n" + ("ACC: " + ACC);
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
                        if (items.get(j).wearloc.equals(wornitems.get(i).wearloc)) { //Here a bug?
                            System.out.println("You already have something worn in that location");
                            isWearing = true;
                        }
                    }
                    if (!isWearing) {
                        wornitems.add(items.get(j));
                        System.out.println("You're now wearing a " + items.get(j).name);
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
}
