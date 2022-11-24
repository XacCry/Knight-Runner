/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import utilz.File_img;

/**
 *
 * @author koyrot
 */
public class Potion extends Entity{

    public boolean delay = true;
    public int counter = 0;
    int round=0;
    private Random random;
    public int potion;

    public Potion(int sX,int sY) {
        super(0, -50, sX, sY);
        loadAnimations();
    }
    
    @Override
    public void loadAnimations() {
        BufferedImage img = File_img.GetSprites(File_img.POTION);

        animations = new BufferedImage[6][6];
        for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                        animations[j][i] = img.getSubimage((i*32)+50, (j*32)+8, 32, 32);
    }

    @Override
    public void update() {
            Delay();
            updateAnimationTick();
    }

    @Override
    public void render(Graphics g) {
            g.drawImage(animations[potion][aniIndex],(int)x,(int)y,scaleX,scaleY, null);
    }

    @Override
    public void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= 6){
                aniIndex = 0;
            }
        }
    }
     public Rectangle getPotionArea(){
        return new Rectangle(x,y,scaleX,scaleY);
    }
    
    public void Delay(){
        counter++;
        if(counter > 10*160 && delay ==true){
             random = new Random();
            x =  random.nextInt(1024);
            round =  random.nextInt(2);
            if(round==0)
            {
            y =  550;
            }
            else if(round==1)
            {
               y = 660 ; 
            }
            counter=0;
            
        }
        else if(delay==false){
            delay = true;
             x =  0;
             y =  -50;
        }
    }
    
}

