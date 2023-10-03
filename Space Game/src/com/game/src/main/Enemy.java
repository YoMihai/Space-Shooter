package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Enemy extends GameObject implements EntityB  {

	private Textures textures;
	private Game game;
	private Controller c;
	Random random = new Random();
	private int speed = (random.nextInt(3) + 1);
	public Enemy(double x, double y, Textures textures, Controller c, Game game) {
		super(x,y);
		this.textures = textures;
		this.c = c;
		this.game = game;
		
	}
	
	public void tick() {
		y += speed;
		
		if(y > Game.HEIGHT * Game.SCALE) {
			speed = (random.nextInt(3) + 1);
			y = -32;
			x = random.nextInt((Game.WIDTH * Game.SCALE) - 25);
		}
		
		for(int i = 0 ; i < game.listA.size() ; i ++) {
			EntityA tempEnt = game.listA.get(i);
			
			if(Physics.Collision(this, tempEnt)) {
			c.removeEntity(this);
			c.removeEntity(tempEnt);
			game.setScore(game.getScore() + 1);
			game.setN_bullets(game.getN_bullets() + 2);
			game.setEnemy_killed(game.getEnemy_killed() + 1);
			
			
		}
		}
		
		
		

	}
	
	public void render(Graphics g) {
		g.drawImage(textures.enemy,(int)x, (int)y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
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
	

}
