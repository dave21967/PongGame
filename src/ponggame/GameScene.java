/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import vghengine.clock.Timer;
import vghengine.entities.Circle;
import vghengine.entities.Rect;
import vghengine.gui.Button;
import vghengine.gui.Label;
import vghengine.gui.Scene;
import vghengine.math.Vector2;

/**
 *
 * @author david
 */
public class GameScene extends Scene {
    private Rect r1;
    private Rect r2;
    private Circle ball;
    private Rect bg;
    private String view;
    private String mode;
    private int scoreP1;
    private int scoreP2;
    private Label p1Lbl;
    private Label p2Lbl;
    
    private Label lbl;
    private Button startBtn;
    private Button exitBtn;
    private Button vsBtn;
    
    private Label timeLbl;
    
    private Label gameOverLbl;
    private Button menuBtn;
    
    private Label credits;
    private Timer timer;
    
    private enum difficulties {
        EASY, NORMAL, HARD
    };
    private difficulties diff;
    
    private Button easyBtn;
    private Button normalBtn;
    private Button hardBtn;
    
    public GameScene() {
        super("pong");
        diff = difficulties.NORMAL;
        view = "menu";
        mode = "single";
        
        timer = new Timer(120);
        timeLbl = new Label("Time: "+timer.getTimeLeft(), 456, 550);
        timeLbl.setColor(Color.WHITE);
        timeLbl.setFont(new Font("Aryal", Font.BOLD, 20));
        
        lbl = new Label("PONG-GAME", 320, 100);
        lbl.setColor(Color.WHITE);
        lbl.setFont(new Font("Aryal", Font.BOLD, 50));
        
        startBtn = new Button("START", 390, 200, 160, 50);
        startBtn.setColor(Color.WHITE);
        startBtn.setFont(new Font("Aryal", Font.BOLD, 20));
        
        //Bottoni difficoltà
        easyBtn = new Button("EASY", 190, 200, 160, 50);
        easyBtn.setColor(Color.WHITE);
        easyBtn.setFont(new Font("Aryal", Font.BOLD, 20));
        
        normalBtn = new Button("NORMAL", 490, 200, 160, 50);
        normalBtn.setColor(Color.WHITE);
        normalBtn.setFont(new Font("Aryal", Font.BOLD, 20));
        
        hardBtn = new Button("HARD", 790, 200, 160, 50);
        hardBtn.setColor(Color.WHITE);
        hardBtn.setFont(new Font("Aryal", Font.BOLD, 20));
        
        vsBtn = new Button("1 V 1", 390, 300, 160, 50);
        vsBtn.setColor(Color.WHITE);
        vsBtn.setFont(new Font("Aryal", Font.BOLD, 20));
        
        exitBtn = new Button("EXIT", 390, 400, 160, 50);
        exitBtn.setColor(Color.WHITE);
        exitBtn.setFont(new Font("Aryal", Font.BOLD, 20));
        
        r1 = new Rect(new Vector2(32, 0));
        r1.setColor(Color.WHITE);
        r1.setW(32);
        r1.setH(96);
        
        r2 = new Rect(new Vector2(940, 0));
        r2.setColor(Color.WHITE);
        r2.setW(32);
        r2.setH(96);
        
        ball = new Circle(new Vector2(512, 304), new Vector2(32, 32));
        ball.setColor(Color.WHITE);
        ball.setVelocity(1, -2);
        
        bg = new Rect(new Vector2(0, 0));
        bg.setW(1024);
        bg.setH(608);
        
        scoreP1 = 0;
        scoreP2 = 0;
        
        p1Lbl = new Label("Player1: 0", 70, 50);
        p1Lbl.setColor(Color.WHITE);
        p1Lbl.setFont(new Font("Aryal", Font.BOLD, 30));
        p2Lbl = new Label("Player2: 0", 800, 50);
        p2Lbl.setColor(Color.WHITE);
        p2Lbl.setFont(new Font("Aryal", Font.BOLD, 30));
        
        gameOverLbl = new Label("GAME OVER!", 320, 300);
        gameOverLbl.setColor(Color.WHITE);
        gameOverLbl.setFont(new Font("Aryal", Font.BOLD, 50));
        menuBtn = new Button("MENU", 390, 400, 160, 50);
        menuBtn.setColor(Color.WHITE);
        menuBtn.setFont(new Font("Aryal", Font.BOLD, 20));
        
        credits = new Label("By VideoGamesHub", 320, 500);
        credits.setColor(Color.WHITE);
        credits.setFont(new Font("Aryal", Font.BOLD, 50));
    }
    
    public void onDraw(Graphics2D g) {
        bg.draw(g);
        if(view.equals("game")) {
            r1.draw(g);
            r2.draw(g);
            ball.draw(g);
            p1Lbl.draw(g);
            p2Lbl.draw(g);
            timeLbl.draw(g);
        }
        if(view.equals("menu")) {
            lbl.draw(g);
            startBtn.draw(g);
            exitBtn.draw(g);
            credits.draw(g);
            vsBtn.draw(g);
        }
        if(view.equals("diff_select")) {
            lbl.draw(g);
            easyBtn.draw(g);
            normalBtn.draw(g);
            hardBtn.draw(g);
            menuBtn.draw(g);
        }
        if(view.equals("game_over")) {
            p1Lbl.draw(g);
            p2Lbl.draw(g);
            gameOverLbl.draw(g);
            menuBtn.draw(g);
            p1Lbl.setString("Player1: "+scoreP1);
            p2Lbl.setString("Player2: "+scoreP2);
        }
    }
    
    public void onUpdate() {
        if(view.equals("game")) {
            try {
                timer.start();
            }
            catch(Exception e) {
                
            }
            timeLbl.setString("Time: "+timer.getTimeLeft());
            r1.update();
            p1Lbl.setString("Player1: "+String.valueOf(scoreP1));
            p2Lbl.setString("Player2: "+String.valueOf(scoreP2));
            if(r1.getY() <= 0) {
                r1.setY(0);
            }
            else if((r1.getY()+r1.getH()) >= 608) {
                r1.setY(608-r1.getH());
            }
            r2.update();
            if(r2.getY() <= 0) {
                r2.setY(0);
            }
            else if((r2.getY()+r2.getH()) >= 608) {
                r2.setY(608-r2.getH());
            }
            if(mode.equals("single")) {
                if(diff == difficulties.EASY) {
                    if(r2.getY() < ball.getY()) {
                        r2.setVelocity(0, 1);
                    }
                    else if(r2.getY() > ball.getY()) {
                        r2.setVelocity(0, -1);
                    }
                }
                else if(diff == difficulties.NORMAL) {
                    if(r2.getY() < ball.getY()) {
                        r2.setVelocity(0, 4);
                    }
                    else if(r2.getY() > ball.getY()) {
                        r2.setVelocity(0, -4);
                    }
                }
                else if(diff == difficulties.HARD) {
                    if(r2.getY() < ball.getY()) {
                        r2.setVelocity(0, 6);
                    }
                    else if(r2.getY() > ball.getY()) {
                        r2.setVelocity(0, -6);
                    }
                }
            }
            ball.update();
            if(ball.getY() <= 0) {
                if(diff==difficulties.EASY) {
                    ball.velocity.y = 4;
                }
                else if(diff==difficulties.NORMAL) {
                    ball.velocity.y = 6;
                }
                else if(diff==difficulties.HARD) {
                    ball.velocity.y = 8;
                }
            }
            else if((ball.getY()+ball.getH()) >= 608) {
                if(diff==difficulties.EASY) {
                    ball.velocity.y = -4;
                }
                else if(diff== difficulties.NORMAL) {
                    ball.velocity.y = -6;
                }
                else if(diff==difficulties.HARD) {
                    ball.velocity.y = -8;
                }
            }

            if(ball.isColliding(r1)) {
                ball.velocity.x = 4;
            }
            else if(ball.isColliding(r2)) {
                ball.velocity.x = -4;
            }
            
            if(ball.getX() < 0) {
                scoreP2++;
                ball.setX(512);
                ball.setY(304);
            }
            else if(ball.getX() > 1024) {
                scoreP1++;
                ball.setX(512);
                ball.setY(304);
            }
            if(scoreP1 == 5 || scoreP2 == 5 || timer.getTimeLeft() == 0) {
                view = "game_over";
            }
        }
        else if(view.equals("menu")) {
            scoreP1 = 0;
            scoreP2 = 0;
            r1.setX(32);
            r1.setY(0);
            r2.setX(940);
            r2.setY(0);
            ball.setX(512);
            ball.setY(304);
            timer = new Timer(120);
        }
    }
    
    public void onKeyPressed(KeyEvent ke) {
        if(view.equals("game")) {
            if(ke.getKeyChar() == 'w') {
                r1.setVelocity(0, -3);
            }
            if(ke.getKeyChar() == 's') {
                r1.setVelocity(0, 3);
            }
            if(mode.equals("1v1")) {
                if(ke.getKeyCode() == 38) {
                    r2.setVelocity(0, -3);
                }
                if(ke.getKeyCode() == 40) {
                    r2.setVelocity(0, 3);
                }
            }
            if(ke.getKeyChar() == '\n') {
                view = "menu";
                scoreP1 = 0;
                scoreP2 = 0;
                r1.setX(32);
                r1.setY(0);
                r2.setX(940);
                r2.setY(0);
                ball.setX(512);
                ball.setY(304);
            }
        }
    }
    
    public void onKeyReleased(KeyEvent ke) {
        if(view.equals("game")) {
            if(ke.getKeyChar() == 'w' || ke.getKeyChar() == 's') {
                r1.setVelocity(0, 0);
            }
            else if(ke.getKeyCode() == 38 || ke.getKeyCode() == 40) {
                r2.setVelocity(0, 0);
            }
        }
    }
    
    public void onMouseClicked(MouseEvent me) {
        if(view.equals("menu")) {
            if(startBtn.rect.contains(me.getX(), me.getY())) {
                view = "diff_select";
                mode = "single";
            }
            if(vsBtn.rect.contains(me.getX(), me.getY())) {
                view = "game";
                mode = "1v1";
            }
            if(exitBtn.rect.contains(me.getX(), me.getY())) {
                System.exit(0);
            }
        }
        if(view.equals("game_over")) {
            if(menuBtn.rect.contains(me.getX(), me.getY())) {
                view = "menu";
            }
        }
        if(view.equals("diff_select")) {
            if(easyBtn.rect.contains(me.getX(), me.getY())) {
                view = "game";
                diff = difficulties.EASY;
            }
            if(normalBtn.rect.contains(me.getX(), me.getY())) {
                view = "game";
                diff = difficulties.NORMAL;
            }
            if(hardBtn.rect.contains(me.getX(), me.getY())) {
                view = "game";
                diff = difficulties.HARD;
            }
            if(menuBtn.rect.contains(me.getX(), me.getY())) {
                view = "menu";
            }
        }
    }
}
