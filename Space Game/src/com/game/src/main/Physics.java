package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Physics {


	public static boolean Collision(EntityA entA, EntityB entB) {

			if(entA.getBounds().intersects(entB.getBounds())) {
				return true;
			}

		return false;
		
	}
	
	
	public static boolean Collision(EntityB entB, EntityA entA) {

			if(entB.getBounds().intersects(entA.getBounds())) {
				return true;

		}
		return false;
		
	}
	


}
