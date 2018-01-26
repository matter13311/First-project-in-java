package dev.matt.game.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.matt.game.Handler;
import dev.matt.game.entities.Entity;
import dev.matt.game.gfx.Animation;
import dev.matt.game.gfx.Assets;
import dev.matt.game.gfx.Text;
import dev.matt.game.inventory.Inventory;

public class Player extends Creature{
	
	
	//Animations
	
	private Animation animDown, animUp, animLeft, animRight, animStanding;
	//attack timer
	
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
		
	//INVENTORY
	private Inventory inventory;
	public Player(Handler handler,float x, float y) {
		super(handler,x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		bounds.x = 24;
		bounds.y = 56;
		bounds.width = 51;
		bounds.height = 93;
		
		//Initialize animations
		
		animDown = new Animation(300, Assets.player_down);
		animUp = new Animation(300, Assets.player_up);
		animLeft = new Animation(300, Assets.player_left);
		animRight = new Animation(300, Assets.player_right);
		animStanding = new Animation(300, Assets.player_standing);
		
		inventory = new Inventory(handler);

	}
	

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		
		animStanding.tick();
		//Movement		
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		//ATTACKING
		checkAttacks();
		//INVENTROY
		inventory.tick();
	}
	private boolean attacking = false;
	private boolean attackingUp = false, attackingLeft = false, attackingDown = false, attackingRight = false;
	
	
	public void checkAttacks() {
		attackTimer += System.currentTimeMillis()-lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		if(attackTimer < attackCooldown)
		{
			return;
		}		
		if(inventory.isActive())
			return;
		Rectangle collisionbound = getCollisionBounds(0,0);
		Rectangle attackrectangle = new Rectangle();
		int arSize = 20;
		attackrectangle.width = arSize;
		attackrectangle.height = arSize;
		
		if(handler.getKeyManager().aUp) {
			attackrectangle.x = collisionbound.x + collisionbound.width/2 - arSize/2;
			attackrectangle.y = collisionbound.y - arSize;
			attacking = true;
			attackingUp = true;
			attackingLeft = false;
			attackingDown = false;
			attackingRight = false;
		}else if(handler.getKeyManager().aDown) {
			attackrectangle.x = collisionbound.x + collisionbound.width/2 - arSize/2;
			attackrectangle.y = collisionbound.y + collisionbound.height;	
			attacking = true;
			attackingUp = false;
			attackingLeft = false;
			attackingDown = true;
			attackingRight = false;
		}else if(handler.getKeyManager().aLeft) {
				attackrectangle.x = collisionbound.x - arSize;
				attackrectangle.y = collisionbound.y + collisionbound.height /2 - arSize /2;
				attacking = true;
				attackingUp = false;
				attackingLeft = true;
				attackingDown = false;
				attackingRight = false;
		}else if(handler.getKeyManager().aRight) {
			attackrectangle.x = collisionbound.x + collisionbound.width;
			attackrectangle.y = collisionbound.y + collisionbound.height /2 - arSize /2;
			attacking = true;
			attackingUp = false;
			attackingLeft = false;
			attackingDown = false;
			attackingRight = true;
		}else {
			attacking = false;
			attackingUp = false;
			attackingLeft = false;
			attackingDown = false;
			attackingRight = false;
			return;
			
		}
		//VIDEO 32
		
		attackTimer = 0;
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue; //if the entity isn't ourself then continue
			if(e.getCollisionBounds(0, 0).intersects(attackrectangle)) {
				System.out.println("Hit");
				e.hurt(50);
				return;
			}
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int)(x- handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height,null);
		Text.drawString(g, "Health: " + handler.getWorld().getEntityManager().getPlayer().getHealth(), (int)(bounds.x + x + 28 - handler.getGameCamera().getxOffset()),
				(int)(bounds.y + y- 70 - handler.getGameCamera().getyOffset()), true, Color.WHITE, Assets.font28);//shows player health
	
		if(attacking){
			{
				if(attackingUp)
				{
					g.drawImage(Assets.attackUp, (int)(bounds.x + x - 75 - handler.getGameCamera().getxOffset()),(int)( bounds.y + y - 110 - handler.getGameCamera().getyOffset()), null);
				}
				else if(attackingDown)
				{
					g.drawImage(Assets.attackDown, (int)(bounds.x + x - 65- handler.getGameCamera().getxOffset()),(int)( bounds.y + y + 20 - handler.getGameCamera().getyOffset()), null);
				}
				else if(attackingLeft)
				{
					g.drawImage(Assets.attackLeft, (int)(bounds.x + x - 100- handler.getGameCamera().getxOffset()),(int)( bounds.y + y - 40 - handler.getGameCamera().getyOffset()), null);
				}
				else if(attackingRight)
				{
					g.drawImage(Assets.attackRight, (int)(bounds.x + x -30 - handler.getGameCamera().getxOffset()),(int)( bounds.y + y - 40 - handler.getGameCamera().getyOffset()), null);
				}
			}
		}
		//g.drawImage(Assets.attackDown,(int)(bounds.x + attackrectangle.x - handler.getGameCamera().getxOffset()), (int)(bounds.y + attackrectangle.y - handler.getGameCamera().getyOffset()),200,200, null);
		//g.drawImage(Assets.attackDown,(int)(bounds.x + x - handler.getGameCamera().getxOffset()), (int)(bounds.y + y - handler.getGameCamera().getyOffset()),200,200, null);
	
		
	//g.setColor(Color.red);
	//g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),(int)(y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}
	
	public void postRender(Graphics g) {
		inventory.render(g);
	}
	public Inventory getInventory() {
		return inventory;
	}


	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}


	@Override
	public void die() {
		System.out.println("You lost");
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;
		if(inventory.isActive())
			return;
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
		
	}
	
	

	private int direction;

	private BufferedImage getCurrentAnimationFrame() {
		if(xMove != 0 || yMove != 0)
		{
			if(xMove < 0) 
			{
				direction =1;
				return animLeft.getCurrentFrame();	
			}
			else if(xMove > 0) 
			{	
				direction = 3;
				return animRight.getCurrentFrame();
			}
			else if(yMove < 0) 
			{	
				direction = 2;
				return animUp.getCurrentFrame();
			}
			else 
			{	
				direction = 0;
				return animDown.getCurrentFrame();
			}
	
		}
		return Assets.player_standing[direction];
	}
}//Add extra else if statements for standing still animation


