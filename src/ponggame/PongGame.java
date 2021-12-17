/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;

import vghengine.window.Window;

/**
 *
 * @author david
 */
public class PongGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Window gameWindow = new Window("PONG-GAME", 1024, 608, new GameScene());
    }
    
}
