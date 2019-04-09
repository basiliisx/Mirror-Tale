
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
    ArrayList<Item> item = new ArrayList<>();

    public String look() {
        String stats;
        stats = ("HP: " + hp) + "\n" + ("ACC: " + accuracy);
        return stats;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" + "Hp: " + hp + "\n" + "Accuracy: " + accuracy;
    }

}
