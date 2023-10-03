package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Player extends GameObject implements EntityA {
	
	
	private double velX;
	private double velY;
	private Textures textures;
	private Game game;
	private Controller c;
	private int enemy_collided = 0;
	
	public Player(double x, double y, Textures textures, Controller c, Game game) {
		super(x,y);
		this.textures = textures;
		this.c = c;
		this.game = game;
		
		
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0) {
			x = 0;
		}
		if(x >= 608) {
			x = 608;
		}
		if(y <= 150) {
			y = 150;
		}
		if(y >= 442) {
			y = 442;
		}
		
		if(game.getHealth() == 150 && enemy_collided > 0) {
					game.setScore(0);
					enemy_collided = 0;
					
				}
		
		for(int i = 0 ; i < game.listB.size() ; i ++) {
			EntityB tempEnt = game.listB.get(i);
			if(Physics.Collision(this, tempEnt)) {
				game.setHealth(game.getHealth() - 15);
				c.removeEntity(tempEnt);
				game.setEnemy_killed(game.getEnemy_killed() + 1);
				enemy_collided ++;
				
			}
			
		}
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public void render(Graphics g) {
		g.drawImage(textures.player, (int)x, (int)y, null);
		
		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	public void setVelY(double velY) {
		this.velY = velY;
	}
	

}
