package utilz;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class File_img {
    public static final String KNIGHT = "KnightSpite.png";
    public static final String MONSTER = "MonsterSpite.png";
    public static final String POTION = "Potions.png";
    public static final String Background ="Background.png";
    public static final String GAMESTART = "GAMESTART.png";
    public static final String GAMEOVER = "GAMEOVER.png";
    public static final String Platform = "Platform.png";
    
    public static BufferedImage GetSprites(String filename){
        BufferedImage img = null;
        InputStream is = File_img.class.getClassLoader().getResourceAsStream("ress\\" + filename);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
    }