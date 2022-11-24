package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;


public class KeyboardInputs implements KeyListener {

	private GamePanel gamePanel;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}



        @Override
	public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
                    gamePanel.getGame().getplayer().setUp(true);
                    break;
		case KeyEvent.VK_A:
                    gamePanel.getGame().getplayer().setLeft(true);
                    break;
		case KeyEvent.VK_S:
                    gamePanel.getGame().getplayer().setDown(true);
                    break;
		case KeyEvent.VK_D:
                    gamePanel.getGame().getplayer().setRight(true);
                    break;
                case KeyEvent.VK_SPACE:
                    gamePanel.getGame().show_game=1;
                    break;
		}

	}
        
	@Override
	public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
                    gamePanel.getGame().getplayer().setUp(false);
                    break;
		case KeyEvent.VK_A:
                    gamePanel.getGame().getplayer().setLeft(false);
                    break;
		case KeyEvent.VK_S:
                    gamePanel.getGame().getplayer().setDown(false);
                    break;
		case KeyEvent.VK_D:
                    gamePanel.getGame().getplayer().setRight(false);
                    break;
               case KeyEvent.VK_SPACE:
                    gamePanel.getGame().show_game=1;
                    break;
		}

	}

    @Override
    public void keyTyped(KeyEvent e) {
    }



}
