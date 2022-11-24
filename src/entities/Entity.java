
package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Entity {
    
    protected int x,y;
    protected int scaleX =0,scaleY=0;
    protected BufferedImage[][] animations;
    protected int aniTick,aniIndex;
    protected final int aniSpeed = 13;
    
    public Entity(int x,int y,int sX,int sY) {
        this.x = x ;
        this.y = y;
        this.scaleX = sX;
        this.scaleY = sY;
    }

    
    
    public abstract void loadAnimations();
    public abstract void update();
    public abstract void render(Graphics g);
    public abstract void updateAnimationTick();
    
}
