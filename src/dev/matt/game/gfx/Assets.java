package dev.matt.game.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	private static final int width = 180, height = 180;
	
	public static Font font28;
	
	private static final int animationWidth = 32, animationHeight = 64;
	
	public static BufferedImage Hurtplayer, rock, girl, dirt, wood, stone, itemapple, tree, itemlog, itemRock;
	public static BufferedImage[] player_down, player_up, player_left, player_right, player_standing;
	public static BufferedImage[] btn_start;
	public static BufferedImage attackDown, attackUp, attackLeft, attackRight;
	public static BufferedImage inventoryScreen;
	
	
	public static void init() {
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);//28 is the font size
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/MinecraftTextures.png"));
		SpriteSheet animationSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Sprite.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		player_down = new BufferedImage[4];//number in array is how many frames are in an animation.
		player_up = new BufferedImage[4];
		player_left = new BufferedImage[4];
		player_right = new BufferedImage[4];
		player_standing = new BufferedImage[4];
		btn_start = new BufferedImage[2];
		
		attackLeft = sheet.crop(0, height * 5, width,  height);
		attackRight = sheet.crop(width, height * 5, width,  height);
		attackDown = sheet.crop(width * 2, height * 5,  width,  height);
		attackUp = sheet.crop(0, height*6, width,  height);
		
		
		btn_start[0] = sheet.crop(0, height * 3 , width * 2, height);
		btn_start[1] = sheet.crop(0, height * 4, width * 2, height);
		
		
		
		player_down[0] = animationSheet.crop(animationWidth * 2, 0, animationWidth, 62);
		player_down[1] = animationSheet.crop(animationWidth * 3, 0, animationWidth, 62);
		player_down[2] = animationSheet.crop(animationWidth * 4, 0, animationWidth, 62);
		player_down[3] = animationSheet.crop(animationWidth * 5, 0, animationWidth, 62);
		
		player_left[0] = animationSheet.crop(animationWidth *2, animationHeight, animationWidth, animationHeight);
		player_left[1] = animationSheet.crop(animationWidth *3, animationHeight, animationWidth, animationHeight);
		player_left[2] = animationSheet.crop(animationWidth *4, animationHeight, animationWidth, animationHeight);
		player_left[3] = animationSheet.crop(animationWidth *5, animationHeight, animationWidth, animationHeight);
		
		player_up[0] = animationSheet.crop(animationWidth * 2, animationHeight * 2, animationWidth, animationHeight);
		player_up[1] = animationSheet.crop(animationWidth * 3, animationHeight * 2, animationWidth, animationHeight);
		player_up[2] = animationSheet.crop(animationWidth * 4, animationHeight * 2, animationWidth, animationHeight);
		player_up[3] = animationSheet.crop(animationWidth * 5, animationHeight * 2, animationWidth, animationHeight);
		
		player_right[0] = animationSheet.crop(animationWidth * 2, animationHeight * 3, animationWidth, animationHeight);
		player_right[1] = animationSheet.crop(animationWidth * 3, animationHeight * 3, animationWidth, animationHeight);
		player_right[2] = animationSheet.crop(animationWidth * 4, animationHeight * 3, animationWidth, animationHeight);
		player_right[3] = animationSheet.crop(animationWidth * 5, animationHeight * 3, animationWidth, animationHeight);
		
		player_standing[0] = animationSheet.crop(0, 0, 31, 62);
		player_standing[1] = animationSheet.crop(0, animationHeight, 31, 62);
		player_standing[2] = animationSheet.crop(0, animationHeight * 2, 31, 62);
		player_standing[3] = animationSheet.crop(0, animationHeight * 3, 31, 62);
		
		
		itemRock = sheet.crop(width *2, height * 4, width,height);
		itemlog = sheet.crop(width * 2, height * 3,width, height);
		Hurtplayer = sheet.crop(width, 0, width, height);
		rock = sheet.crop(width * 2, 0, width, height);
		girl = sheet.crop(0, height, width, height);
		dirt = sheet.crop(width, height, width, height);
		wood = sheet.crop(width*2, height, width, height);
		stone = sheet.crop(0, height * 2, width, height);
		itemapple = sheet.crop(width, height * 2 , width, height);
		tree = sheet.crop(width * 2, height * 2, width, height);
	}
	
	
}
