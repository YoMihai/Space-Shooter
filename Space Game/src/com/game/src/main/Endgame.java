package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Endgame {
	private Game game;
	
	public Rectangle quitButton = new Rectangle(Game.WIDTH/2 + 115, 380, 100, 50);
	public Rectangle playAgainButton = new Rectangle(Game.WIDTH/2 + 60, 280, 200, 50);
	
	public void render(Graphics g, Game game) {
		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		Font fnt1 = new Font("arial", Font.BOLD, 35);
		g.setFont(fnt0);
		g.setColor(Color.red);
		g.drawString("GAME OVER", Game.WIDTH/2 , 100);
		g.setColor(Color.green);
		g.setFont(fnt1);
		g.drawString("SCORE : " + game.getScore(), Game.WIDTH/2 + 60, Game.HEIGHT);
		g.setColor(Color.white);
		g.drawString("Quit", quitButton.x + 14, quitButton.y + 35);
		g2d.draw(quitButton);
		g.drawString("Play Again", playAgainButton.x + 12, playAgainButton.y + 35);
		g2d.draw(playAgainButton);
		
		
		
		
		
	}

}
