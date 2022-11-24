/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import utilz.File_img;
import java.util.Random;
import main.Game;

/**
 *
 * @author koyrot
 */
public class Monster extends Entity {

    private Game game;
    private Random random;
    private int direction;
    private boolean canDmg = true;
    int top_or_down = 0;
    int left_or_right = 0;
    public int Speed_mons = 2;

    public Monster(int sX, int sY) {
        super(0, 0, sX, sY);
        loadAnimations();
    }

    @Override
    public void loadAnimations() {
        BufferedImage img = File_img.GetSprites(File_img.MONSTER);
        animations = new BufferedImage[3][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage((i * 120), (j * 80), 120, 80);
            }
        }
    }

    @Override
    public void update() {
        fireBallMove();
        updateAnimationTick();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.drawImage(animations[direction][aniIndex], (int) x, (int) y, scaleX, scaleY, null);
        //g.drawRect((int)x+30,(int)y+90,scaleX-90,scaleY-100);
    }

    @Override
    public void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 6) {
                aniIndex = 0;
            }
        }
    }

    private void fireBallMove() {
        if (direction == 0) {
            y += Speed_mons;
            if (y > 1000) {
                random = new Random();
                direction = random.nextInt(3);
                x = random.nextInt(1010);
                top_or_down = random.nextInt(2);
                left_or_right = random.nextInt(2);
                if (direction == 0) {
                    x = random.nextInt(1010);
                    y = -50;
                } else if (direction == 1) {
                    x = 1050;

                } else if (direction == 2) {
                    x = -10;
                }

                if (top_or_down == 0 && direction != 0) {
                    y = 390;
                } else if (top_or_down == 1 && direction != 0) {
                    y = 580;
                }

                canDmg = true;
            }
        } else if (direction == 1) {
            x = x - Speed_mons;
            if (x < -70) {
                direction = 0;
                x = random.nextInt(1024);
                y = 20;
                canDmg = true;
            }

        } else if (direction == 2) {
            x = x + Speed_mons;
            if (x > 1000) {
                direction = 0;
                x = random.nextInt(1024);
                y = 20;
                canDmg = true;
            }
        }

    }

    public Rectangle getCoinArea() {
        return new Rectangle((int) x + 30, (int) y + 90, scaleX - 90, scaleY - 100);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isCanDmg() {
        return canDmg;
    }

    public void setCanDmg(boolean canDmg) {
        this.canDmg = canDmg;
    }
}
