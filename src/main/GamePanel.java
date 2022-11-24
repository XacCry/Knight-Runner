package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import java.awt.image.BufferedImage;
import utilz.File_img;


public class GamePanel extends JPanel{
        private Game game;
        private BufferedImage bg_start,bg,bg_game_over,platform;
	public GamePanel(Game game){
            bg_start = File_img.GetSprites(File_img.GAMESTART);
            bg = File_img.GetSprites(File_img.Background);
            bg_game_over = File_img.GetSprites(File_img.GAMEOVER);
            platform = File_img.GetSprites(File_img.Platform);
            this.game = game;
            setPanelSize();
            addKeyListener(new KeyboardInputs(this));
	}

	private void setPanelSize() {
            Dimension size = new Dimension(1024,768);//BG2
            setPreferredSize(size);
	}
        
        @Override
	public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(game.show_game==0)
            {
            g.drawImage(bg_start, 0, 0,1024,768,null);//BG2
            }
            else if(game.show_game==1)
            {
                 g.drawImage(bg, 0, 0,1024,768,null);//BG2
                 g.drawImage(platform, 10, 600,256,8,null);//platform1
                 g.drawImage(platform, 266, 600,256,8,null);//platform1
                 g.drawImage(platform, 522, 600,256,8,null);//platform1
                 g.drawImage(platform, 758, 600,256,8,null);//platform1
                 game.render(g);
            }
            else if(game.show_game==2)
            {
                g.drawImage(bg_game_over, 0, 0,1024,768,null);//BG2
            }
	}
        public Game getGame(){
            return game;
        }
}
