package main;

import entities.Monster;
import entities.Player;
import entities.Potion;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;



public class Game{

        int count=0;
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
        private final int UPS_SET = 200;
        private Player player;
        private Potion potion;
        private Monster monster;
        private ArrayList<Monster> mons = new ArrayList<Monster>();
        public int score = 0;
        public int show_game = 0;
        public int High_score = 0;
        
	public Game() {
            player = new Player(512,581,160,120);
            for(int i = 0 ; i < 5 ; i++){
                mons.add(new Monster(160,160));
              }
            potion =new Potion(40,40);//40 40 size
            gamePanel = new GamePanel(this);
            gameWindow = new GameWindow(gamePanel);
            gamePanel.requestFocus();
            Thread player_Thread = new Thread(new Runnable(){
                @Override
                public void run(){
                    while(true){
                        try{
                            if(show_game == 1){
                                player.update();
                                checkPlayerGetDmg();
                                checkPlayerGetpotion();
                                check_Hp();
                                gamePanel.repaint();
                                Thread.sleep(3);
                            }
                        }catch(Exception e){}
                    }
                }
            });
            Thread Monster_Thread = new Thread(new Runnable(){
                @Override
                public void run(){
                    while(true){
                        try{
                            if(show_game == 1){
                                for(int i = 0 ; i < 5 ; i++){mons.get(i).update();}
                                gamePanel.repaint();
                                Thread.sleep(3);
                            }
                        }catch(Exception e){}
                    }
                }
            });
            Thread Potion_Thread = new Thread(new Runnable(){
                @Override
                public void run(){
                    while(true){
                        try{
                            if(show_game == 1){
                                potion.update();
                                gamePanel.repaint();
                                Thread.sleep(3);
                            }
                        }catch(Exception e){}
                    }
                }
            });
            player_Thread.start();
            Potion_Thread.start();
            Monster_Thread.start();
        }
        
//	private void startGameLoop() {
//		gameThread = new Thread(this);
//		gameThread.start();
//	}
//        
//        private void update() {
//            if(show_game==1)
//            {
//            player.update();
//            for(int i = 0 ; i < 5 ; i++){
//                mons.get(i).update();
//            }
//            potion.update();
//            check_score();        
//            checkPlayerGetDmg();
//            checkPlayerGetpotion();
//           check_Hp();
//            }
//        }
        
        public void render(Graphics g){
            player.render(g);
            for(int i = 0 ; i < 5 ; i++){
                mons.get(i).render(g);
            }
            potion.render(g);
            g.setFont(new Font("rainyhearts",Font.BOLD,20));
            g.drawString("Score : " + score, 900, 50);
            g.setFont(new Font("rainyhearts",Font.BOLD,20));
            g.drawString("High_Score : " + High_score, 750, 50);
        }

//	@Override
//	public void run() {
//		double timePerFrame = 1000000000.0 / FPS_SET;
//                double timePerUpdate = 1000000000.0 / UPS_SET;
//                
//                long previousTime = System.nanoTime();
//
//		int frames = 0;
//                int updates = 0;
//		long lastCheck = System.currentTimeMillis();
//                
//                double deltaU = 0;
//                double deltaF = 0;
//
//		while (true) {
//                        long currentTime = System.nanoTime();
//                        
//                        deltaU = deltaU + (currentTime - previousTime)/timePerUpdate;
//                        deltaF = deltaF + (currentTime - previousTime)/timePerFrame;
//                        previousTime = currentTime;
//                        
//                        if(deltaU>=1){
//                            update();
//                            updates++;
//                            deltaU--;
//                        }
//                        
//                        if(deltaF>=1){
//                            gamePanel.repaint();                            
//                            frames++;
//                            deltaF--;
//                            
//                        }
//
//			if (System.currentTimeMillis() - lastCheck >= 1000) {
//				lastCheck = System.currentTimeMillis();
//				frames = 0;
//                                updates = 0;
//			}
//		}
//
//	}
        
        public Player getplayer(){
            return player;
        }
        
        public void windowFocusLost(){
            player.resetDirBooleans();
        }
        
        public void check_score(){
             count++;
            if(count>2*60)
            {
                score++;
                int level = 0 ;
                if(High_score<score)
                {
                    High_score = score;
                }
                count=0;
             
            }
        }
        public void checkPlayerGetDmg(){
            for(int i = 0 ; i < 5 ; i++){
                if(player.getPlayerArea().intersects(mons.get(i).getCoinArea()) && mons.get(i).isCanDmg() == true){
                player.gotDMG(9);
                mons.get(i).setCanDmg(false);
            }
              }
            
        }
        public void checkPlayerGetpotion(){
            if( player.getPlayerArea().intersects(potion.getPotionArea())){
                player.hp = player.hp+11;
                if(player.playerSpeed < 3){
                     player.playerSpeed ++;
                }
                
                if(player.hp>100)
                 {
                    player.hp=100;
                 }
                potion.delay=false;
            }
        }
          public void check_Hp(){
               if(player.hp<=0)
               {
                 show_game =2;
                 player.hp=100;
                 score=0;

               }
        }
        
 
}