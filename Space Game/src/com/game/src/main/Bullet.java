package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;

public class Bullet extends GameObject implements EntityA {
	private Textures textures;
	private Game game;
	private Controller c;
	public Bullet(double x, double y, Textures textures, Controller c, Game game) {
		super(x,y);
		this.textures = textures;
		this.game = game;
		this.c = c;
	}
	
	public void tick() {
		y -= 7;
		/*if(Physics.Collision(this, game.listB)) {
			c.removeEntity(this);
		}*/
	
	}
	
	public void render(Graphics g) {
		g.drawImage(textures.missile,(int)x, (int)y, null);
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

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	
	
}
