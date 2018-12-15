// Congratulations for finishing the game.
// Gives you a rank based on how long it took
// you to beat the game.

// Under two minutes = Speed Demon
// Under three minutes = Adventurer
// Under four minutes = Beginner
// Four minutes or greater = Bumbling Idiot

package com.neet.DiamondHunter.GameState;

import java.awt.Color;
import java.awt.Graphics2D;

import com.neet.DiamondHunter.Main.GamePanel;
import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.Data;
import com.neet.DiamondHunter.Manager.GameStateManager;
import com.neet.DiamondHunter.Manager.JukeBox;
import com.neet.DiamondHunter.Manager.Keys;

public class GameOverState extends com.neet.DiamondHunter.GameState.GameState {
	
	private java.awt.Color color;
	
	private int rank;
	private long ticks;
	
	public GameOverState(com.neet.DiamondHunter.Manager.GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		color = new java.awt.Color(164, 198, 222);
		ticks = com.neet.DiamondHunter.Manager.Data.getTime();
		if(ticks < 3600) rank = 1;
		else if(ticks < 5400) rank = 2;
		else if(ticks < 7200) rank = 3;
		else rank = 4;
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(java.awt.Graphics2D g) {
		
		g.setColor(color);
		g.fillRect(0, 0, com.neet.DiamondHunter.Main.GamePanel.WIDTH, com.neet.DiamondHunter.Main.GamePanel.HEIGHT2);
		
		com.neet.DiamondHunter.Manager.Content.drawString(g, "finish time", 20, 36);
		
		int minutes = (int) (ticks / 1800);
		int seconds = (int) ((ticks / 30) % 60);
		if(minutes < 10) {
			if(seconds < 10) com.neet.DiamondHunter.Manager.Content.drawString(g, "0" + minutes + ":0" + seconds, 44, 48);
			else com.neet.DiamondHunter.Manager.Content.drawString(g, "0" + minutes + ":" + seconds, 44, 48);
		}
		else {
			if(seconds < 10) com.neet.DiamondHunter.Manager.Content.drawString(g, minutes + ":0" + seconds, 44, 48);
			else com.neet.DiamondHunter.Manager.Content.drawString(g, minutes + ":" + seconds, 44, 48);
		}
		
		com.neet.DiamondHunter.Manager.Content.drawString(g, "rank", 48, 66);
		if(rank == 1) com.neet.DiamondHunter.Manager.Content.drawString(g, "speed demon", 20, 78);
		else if(rank == 2) com.neet.DiamondHunter.Manager.Content.drawString(g, "adventurer", 24, 78);
		else if(rank == 3) com.neet.DiamondHunter.Manager.Content.drawString(g, "beginner", 32, 78);
		else if(rank == 4) com.neet.DiamondHunter.Manager.Content.drawString(g, "bumbling idiot", 8, 78);
		
		com.neet.DiamondHunter.Manager.Content.drawString(g, "press any key", 12, 110);
		
	}
	
	public void handleInput() {
		if(com.neet.DiamondHunter.Manager.Keys.anyKeyPress()) {
			gsm.setState(com.neet.DiamondHunter.Manager.GameStateManager.MENU);
			com.neet.DiamondHunter.Manager.JukeBox.play("collect");
		}
	}
	
}