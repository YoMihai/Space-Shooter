package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Game extends Canvas implements Runnable {
	
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public static int HEALTH = 150;
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT , BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	private Player p;
	private Controller c;
	private Textures textures;
	private Menu menu;
	private int score = 0;
	public static int n_bullets = 10;
	private Endgame endgame;
	private int enemy_count = 5;
	private int enemy_killed = 0;
	public LinkedList<EntityA> listA;
	public LinkedList<EntityB> listB;
	
	public static enum STATE{
		MENU,
		GAME,
		ENDGAME
	};
	
	public static STATE State = STATE.MENU;
	
	private boolean spam = false;
	public void init() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			
			spriteSheet = loader.loadImage("/sprite_sheet.png");
			background = loader.loadImage("/background.png");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput());
		textures = new Textures(this);
		c = new Controller(textures, this);
		p = new Player(304, 350, textures, c, this);
		listA = c.getEntityA();
		listB = c.getEntityB();
		c.createEnemy(enemy_count);
		menu = new Menu();
		endgame = new Endgame();
		
		
	
	} 
	
	private synchronized void start() {
		if(running) {
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
		
	}
	
	private synchronized void stop() {
		if(!running) {
			return;
		}	
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
		
	}
	
	
	
	public void run() {
		
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				updates++;
				delta --;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ticks, fps " + frames);
				frames = 0;
				updates = 0;
			}
			
		}
		stop();
		
	}
	
	private void tick() {
		if(State == STATE.GAME) {
		p.tick();
		c.tick();
		
		if(enemy_killed == enemy_count) {
			enemy_count += 2;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
		}
		
		if(HEALTH <= 0) {
			State = STATE.ENDGAME;
			enemy_count = 5;
				
		}
		
		}
		
	}
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		////////////////////////////////////////////////////////
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(background, 0, 0, null);
		if(State == STATE.GAME) {
		p.render(g);
		c.render(g);
		g.setColor(Color.gray);
		g.fillRect(10, HEIGHT * 2 - 25, 150, 15);
		g.setColor(Color.green);
		g.fillRect(10, HEIGHT * 2 - 25, HEALTH, 15);
		g.setColor(Color.white);
		g.drawRect(10, HEIGHT * 2 - 25, 150, 15);
		g.setColor(Color.yellow);
		Font fnt0 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt0);
		g.drawString(" " + getN_bullets(), 170, HEIGHT * 2 - 10);
		}else if(State == STATE.MENU) {
			menu.render(g);
		}
		else if(State == STATE.ENDGAME) {
			endgame.render(g, this);

			
		}
		////////////////////////////////////////////////////////
		g.dispose();
		bs.show();
		
	}
	
	
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(State == STATE.GAME) {
		if(key == KeyEvent.VK_D) {
			p.setVelX(8);
			
		}
		else if(key == KeyEvent.VK_A) {
			p.setVelX(-8);
			
		}
		else if(key == KeyEvent.VK_S) {
			p.setVelY(8);
			
		}
		else if(key == KeyEvent.VK_W) {
			p.setVelY(-8);
			
		}
		else if(key == KeyEvent.VK_SPACE && spam == false && n_bullets > 0) {
			c.addEntity(new Bullet(p.getX(), p.getY() - 20, textures, c, this));
			n_bullets --;
			spam = true;
		}
		else if(key == KeyEvent.VK_ESCAPE && State == STATE.GAME) {
			State = STATE.MENU;
		}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D) {
			p.setVelX(0);
			
		}
		else if(key == KeyEvent.VK_A) {
			p.setVelX(0);
			
		}
		else if(key == KeyEvent.VK_S) {
			p.setVelY(0);
			
		}
		else if(key == KeyEvent.VK_W) {
			p.setVelY(0);
			
		}
		else if(key == KeyEvent.VK_SPACE) {
			spam = false;
		}
	}
	
	
	
	public static void main (String args[]) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));
		
		JFrame frame = new JFrame();
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Space Game");
		game.setFocusable(true);
		game.start();
		
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getN_bullets(){
		return n_bullets;
	}
	public void setN_bullets(int n_bullets) {
		this.n_bullets = n_bullets;
	}
	public int getHealth() {
		return HEALTH;
	}
	public void setHealth(int HEALTH) {
		this.HEALTH = HEALTH;
	}

	
}
