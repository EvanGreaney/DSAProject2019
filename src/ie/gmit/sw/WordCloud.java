package ie.gmit.sw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class WordCloud {

	Menu menu = new Menu();
	
	int randFont;
	int rand2;
	int randx;
	int randy;
	
	String newFileName = menu.getNewName();
	int numWords = menu.getMaxWords();
	
	public void GenerateImage(ArrayList<String> hash, ArrayList<String> update,int maxWords, String newFileName) throws Exception{
		
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
		BufferedImage image = new BufferedImage(600, 300, BufferedImage.TYPE_3BYTE_BGR);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.red);
		graphics.setFont(font);
		
		//System.out.println((newFileName + numWords));
		Random rand = new Random();
		for (int i = 0; i < numWords ; i++) {
			randFont = rand.nextInt(2);
			randx = rand.nextInt(600);
			randy = rand.nextInt(300);
			
			if (randFont == 0) {
				font = new Font(Font.SANS_SERIF, Font.ITALIC, 42);
				graphics.setColor(Color.yellow);
			}
			else if (randFont == 1) {
				font = new Font(Font.MONOSPACED, Font.PLAIN, 22);
				graphics.setColor(Color.blue);
			}
			else if (randFont == 2) {
				font = new Font(Font.MONOSPACED, Font.BOLD, 32);
				graphics.setColor(Color.red);
			}			
			graphics.drawString(update.get(i),randx,randy);
			
		}
		graphics.dispose();
		ImageIO.write(image, "png", new File(newFileName+".png"));
		System.out.println("The Image has been created");
	}
}
