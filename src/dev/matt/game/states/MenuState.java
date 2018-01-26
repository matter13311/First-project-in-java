package dev.matt.game.states;


import java.awt.Graphics;


import dev.matt.game.Handler;
import dev.matt.game.gfx.Assets;
import dev.matt.game.ui.ClickListener;
import dev.matt.game.ui.UIImageButton;
import dev.matt.game.ui.UIManager;

public class MenuState extends State{
	
	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		uiManager.addObject(new UIImageButton(360,450, 250, 80, Assets.btn_start, new ClickListener() {

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}}));
	}
	
	
	
	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		
		//temoporary code
		//handler.getMouseManager().setUIManager(null);
		//State.setState(handler.getGame().gameState);
		
		//g.setColor(Color.RED);
		//g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 8, 8);
	}

}
