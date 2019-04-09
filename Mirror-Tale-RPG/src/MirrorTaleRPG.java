/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zygmut
 */
public class MirrorTaleRPG {

    static Game_Logic gl = new Game_Logic();

    public void main() {
        Game_objects.initializeNPCArray();
        Game_objects.initializeITEMArray();
        while (true) {
            game_loop();
        }
    }

    public void game_loop() {
        gl.waitforCommand();
    }

    public static void main(String[] args) {
        new MirrorTaleRPG().main();
    }

}
