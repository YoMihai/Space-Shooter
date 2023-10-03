package com.game.src.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.game.src.main.Game.STATE;

public class MouseInput implements MouseListener {

	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		
	//	public Rectangle playButton = new Rectangle(Game.WIDTH/2 + 115, 150, 100, 50);
	//	public Rectangle helpButton = new Rectangle(Game.WIDTH/2 + 115, 250, 100, 50);
	//	public Rectangle quitButton = new Rectangle(Game.WIDTH/2 + 115, 350, 100, 50);
		
		//Play Button
		if(mx >= Game.WIDTH/2 + 115 && mx <= Game.WIDTH/2 + 215 && my >= 150 && my <= 200 && Game.State == STATE.MENU) {
			Game.State = Game.STATE.GAME;
			
		}
		//Quit Button
		if(mx >= Game.WIDTH/2 + 115 && mx <= Game.WIDTH/2 + 215 && my >= 350 && my <= 400 && Game.State == STATE.MENU) {
			System.exit(1);
			
		}
		//Play Again
		if(mx >= Game.WIDTH/2 + 60 && mx <= Game.WIDTH/2 + 260 && my >= 280 && my <= 330 && Game.State == STATE.ENDGAME) {
			Game.State = Game.STATE.GAME;
			Game.HEALTH = 150;
			Game.n_bullets = 10;
			
			
		}
		//Quit Button PT ENDGAME
		if(mx >= Game.WIDTH/2 + 115 && mx <= Game.WIDTH/2 + 215 && my >= 380 && my <= 430 && Game.State == STATE.ENDGAME) {
			System.exit(1);
			
		}
		//public Rectangle quitButton = new Rectangle(Game.WIDTH/2 + 115, 380, 100, 50);
		//public Rectangle playAgainButton = new Rectangle(Game.WIDTH/2 + 60, 280, 200, 50);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




}
