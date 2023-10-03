package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import org.w3c.dom.Entity;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Controller {
	private LinkedList <EntityA> listA = new LinkedList<EntityA>();
	private LinkedList <EntityB> listB = new LinkedList<EntityB>();
	Textures textures;
	EntityA entA;
	EntityB entB;
	private Game game;
	Bullet bullet;
	Random random = new Random(); 
	
	public Controller(Textures textures, Game game) {
		this.textures = textures;
		this.game = game;
	
		
				
	}
	public void createEnemy(int enemy_count) {
		for(int i = 0 ; i < enemy_count ; i++) {
			addEntity(new Enemy(random.nextInt(608), -32, textures, this, game));
		}
	}
	
	public void tick() {
		//A CLASS
		for(int i = 0 ; i < listA.size() ; i ++) {
			entA = listA.get(i);
			entA.tick();
		
		}	
		//B CLASS
		for(int j = 0 ; j < listB.size() ; j ++) {
			entB = listB.get(j);
			entB.tick();
		}
	}

	public void render(Graphics g) {
		//A CLASS
		for(int i = 0 ; i < listA.size() ; i ++) {
			entA = listA.get(i);
			entA.render(g);
		}
		//B CLASS
		for(int j = 0 ; j < listB.size() ; j ++) {
			entB = listB.get(j);
			entB.render(g);
		}
	}
		//A CLASS
		public void addEntity(EntityA block) {
			listA.add(block);
		}
		
		public void removeEntity(EntityA block) {
			listA.remove(block);
		}
		//B CLASS
		public void addEntity(EntityB block) {
			listB.add(block);
		}
		
		public void removeEntity(EntityB block) {
			listB.remove(block);
		}

		
		public LinkedList<EntityA> getEntityA(){
			return listA;
		}
		
		public LinkedList<EntityB> getEntityB(){
			return listB;
		}
}
