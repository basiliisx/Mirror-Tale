
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Zygmut
 */
public class Combat_logic {

    public void atack(String[] x) {
        if (x.length == 1) {
            System.out.println("Atack what?");
        }
        if (x.length == 2) {
            for (int i = 0; i < Game_objects.room.size(); i++) {
                if (Objects.equals(Game_objects.room.get(i).number, Game_objects.pc.inRoom)) {
                    for (int j = 0; j < Game_objects.room.get(i).npc.size(); j++) {
                        if (x[1].equalsIgnoreCase(Game_objects.room.get(i).npc.get(j).id)) {

                            //Start PC turn
                            Integer ATK = 0;
                            Integer TATK = 0;
                            Integer ACC = 0;
                            Integer ARM;
                            Random RNG = new Random();
                            for (int k = 0; k < Game_objects.pc.wornitems.size(); k++) {
                                if (Game_objects.pc.wornitems.get(k).wearloc.equals("Weapon")) {
                                    Weapon userWeapon = (Weapon) Game_objects.pc.wornitems.get(k);
                                    ATK = userWeapon.DMG;
                                    TATK = userWeapon.TDMG;
                                    ACC = userWeapon.ACC;
                                    break;
                                }
                            }
                            for (int k = 0; k < Game_objects.pc.wornitems.size(); k++) {
                                if (Game_objects.pc.wornitems.get(k).wearloc.equals("Trinked")) {
                                    Trinked userTrinked = (Trinked) Game_objects.pc.wornitems.get(k);
                                    ARM = userTrinked.ARM;
                                    ACC += userTrinked.ACC;
                                    break;
                                }
                            }

                            Integer Damage = ATK - Game_objects.room.get(i).npc.get(j).armour;
                            if (Damage < 0) {
                                Damage = TATK;
                            } else {
                                Damage += TATK;
                            }
                            Integer accuracycheck = ((RNG.nextInt(100) + 1) + ACC);
                            System.out.println(accuracycheck);
                            System.out.println(ATK);
                            System.out.println(TATK);
                            System.out.println(ACC);
                            if (accuracycheck >= 50) {
                                Game_objects.room.get(i).npc.get(j).hp -= Damage;
                                System.out.println("You hit " + Game_objects.room.get(i).npc.get(j).name + " for " + Damage);
                            } else {
                                System.out.println("You missed");
                            }

                            if (Game_objects.room.get(i).npc.get(j).hp <= 0) {
                                System.out.println("You've killed a " + Game_objects.room.get(i).npc.get(j).name);
                                Game_objects.room.get(i).npc.remove(j);
                                return;
                            }
                            //End PC turn

                            //Start NPC turn
                            if (Game_objects.room.get(i).npc.get(j).canAtack) {

                                if (Game_objects.pc.HP <= 0) {
                                    PCdeath();
                                    return;
                                }
                            }
                            //End NPC turn

                        }
                    }
                }
            }
        }
    }

    public void NPCdeath(String name) {
        System.out.println("You've killed a" + name);
    }

    public void PCdeath() {
        System.out.println("Your Journey has ended with the worst posible fate. Death");
        String death;
        System.exit(0);
    }
}
