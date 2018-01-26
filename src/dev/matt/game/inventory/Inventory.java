package dev.matt.game.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.matt.game.Handler;
import dev.matt.game.gfx.Assets;
import dev.matt.game.gfx.Text;
import dev.matt.game.items.Item;

public class Inventory {

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
		//Items in inventory
		//addItem(Item.logItem.createNew(5));
		
	}
	
	public void tick() {
		if(handler.getKeyManager().KeyJustPressed(KeyEvent.VK_E))
			active = !active;
		
		if(!active)
			return;
		if(handler.getKeyManager().KeyJustPressed(KeyEvent.VK_UP))//Navigates the inventory using the keyboard. IMPORTANT
			selectedItem--;
		if(handler.getKeyManager().KeyJustPressed(KeyEvent.VK_DOWN))
			selectedItem++;
			
		if(selectedItem < 0)
			selectedItem = inventoryItems.size() - 1;
		else if(selectedItem >= inventoryItems.size())
			selectedItem = 0;
		
		
		//Temporary code to show inventory in console
		///System.out.println("INVENTORY:");
		//for(Item i: inventoryItems) {
			//System.out.println(i.getCount());
	//}
	}
		
	
	public boolean isActive() {
		return active;
	}


	//Inventory variables
	private int invListCenterX = 230, invListCenterY = 245;
	private int invImageX = 454, invImageY = 82, invImageWidth = 64, invImageHeight = 64;
	private int invCountX = 484, invCountY = 172;
	private int selectedItem = 0;
	
	
	public void render(Graphics g) {
		if(!active)
			return;
		g.drawImage(Assets.inventoryScreen, 64, 48, 512, 384, null); //locations and size that will center image into center of the screen
		
		int len = inventoryItems.size();
		if(len == 0)//if nothing in our inventory then continue
			return;
		
		for(int i = -5; i< 6; i++) {
			if(selectedItem + i < 0 || selectedItem + i >= len)
				continue;
			if(i == 0) {
				Text.drawString(g,"> " +  inventoryItems.get(selectedItem + i).getName()+ " <", invListCenterX,
						invListCenterY + i * 30, true, Color.GREEN,Assets.font28);
			}else
			{
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX,
						invListCenterY + i * 30, true, Color.WHITE,Assets.font28);
			}
		}
		Item item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(),invImageX,invImageY,invImageWidth, invImageHeight, null);
		Text.drawString(g, Integer.toString(item.getCount()), invCountX,invCountY,true,Color.WHITE,Assets.font28);
	}
//Inventory methods
	public void addItem(Item item) { //This block of code stacks all item together. If comment it out, then it will unstack it
		for(Item i : inventoryItems){
			if(i.getId() == item.getId()){
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
