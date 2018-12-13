// The main playing GameState.
// Contains everything you need for gameplay:
// Player, TileMap, Diamonds, etc.
// Updates and draws all game objects.

package com.neet.DiamondHunter.GameState;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import Mapviewer.Control;
import com.neet.DiamondHunter.Entity.Diamond;
import com.neet.DiamondHunter.Entity.Item;
import com.neet.DiamondHunter.Entity.Player;
import com.neet.DiamondHunter.Entity.Sparkle;
import com.neet.DiamondHunter.HUD.Hud;
import com.neet.DiamondHunter.Main.GamePanel;
import com.neet.DiamondHunter.Manager.Data;
import com.neet.DiamondHunter.Manager.GameStateManager;
import com.neet.DiamondHunter.Manager.JukeBox;
import com.neet.DiamondHunter.Manager.Keys;
import com.neet.DiamondHunter.TileMap.TileMap;

public class PlayState extends com.neet.DiamondHunter.GameState.GameState {
	
	// player
	private com.neet.DiamondHunter.Entity.Player player;
	
	// tilemap
	private com.neet.DiamondHunter.TileMap.TileMap tileMap;
	
	// diamonds
	private java.util.ArrayList<com.neet.DiamondHunter.Entity.Diamond> diamonds;
	
	// items
	private java.util.ArrayList<com.neet.DiamondHunter.Entity.Item> items;
	
	// sparkles
	private java.util.ArrayList<com.neet.DiamondHunter.Entity.Sparkle> sparkles;
	
	// camera position
	private int xsector;
	private int ysector;
	private int sectorSize; 
	
	// hud
	private com.neet.DiamondHunter.HUD.Hud hud;
	
	// events
	private boolean blockInput;
	private boolean eventStart;
	private boolean eventFinish;
	private int eventTick;
	
	// transition box
	private java.util.ArrayList<java.awt.Rectangle> boxes;
	
	public PlayState(com.neet.DiamondHunter.Manager.GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		// create lists
		diamonds = new java.util.ArrayList<com.neet.DiamondHunter.Entity.Diamond>();
		sparkles = new java.util.ArrayList<com.neet.DiamondHunter.Entity.Sparkle>();
		items = new java.util.ArrayList<com.neet.DiamondHunter.Entity.Item>();
		
		// load map
		tileMap = new com.neet.DiamondHunter.TileMap.TileMap(16);
		tileMap.loadTiles("/Tilesets/testtileset.gif");
		tileMap.loadMap("/Maps/testmap.map");
		
		// create player
		player = new com.neet.DiamondHunter.Entity.Player(tileMap);
		
		// fill lists
		populateDiamonds();
		populateItems();
		
		// initialize player
		player.setTilePosition(17, 17);
		player.setTotalDiamonds(diamonds.size());
		
		// set up camera position
		sectorSize = com.neet.DiamondHunter.Main.GamePanel.WIDTH;
		xsector = player.getx() / sectorSize;
		ysector = player.gety() / sectorSize;
		tileMap.setPositionImmediately(-xsector * sectorSize, -ysector * sectorSize);
		
		// load hud
		hud = new com.neet.DiamondHunter.HUD.Hud(player, diamonds);
		
		// load music
		com.neet.DiamondHunter.Manager.JukeBox.load("/Music/bgmusic.mp3", "music1");
		com.neet.DiamondHunter.Manager.JukeBox.setVolume("music1", -10);
		com.neet.DiamondHunter.Manager.JukeBox.loop("music1", 1000, 1000, com.neet.DiamondHunter.Manager.JukeBox.getFrames("music1") - 1000);
		com.neet.DiamondHunter.Manager.JukeBox.load("/Music/finish.mp3", "finish");
		com.neet.DiamondHunter.Manager.JukeBox.setVolume("finish", -10);
		
		// load sfx
		com.neet.DiamondHunter.Manager.JukeBox.load("/SFX/collect.wav", "collect");
		com.neet.DiamondHunter.Manager.JukeBox.load("/SFX/mapmove.wav", "mapmove");
		com.neet.DiamondHunter.Manager.JukeBox.load("/SFX/tilechange.wav", "tilechange");
		com.neet.DiamondHunter.Manager.JukeBox.load("/SFX/splash.wav", "splash");
		
		// start event
		boxes = new java.util.ArrayList<java.awt.Rectangle>();
		eventStart = true;
		eventStart();
			
	}
	
	private void populateDiamonds() {
		
		com.neet.DiamondHunter.Entity.Diamond d;
		
		d = new com.neet.DiamondHunter.Entity.Diamond(tileMap);
		d.setTilePosition(20, 20);
		d.addChange(new int[] { 23, 19, 1 });
		d.addChange(new int[] { 23, 20, 1 });
		diamonds.add(d);
		d = new com.neet.DiamondHunter.Entity.Diamond(tileMap);
		d.setTilePosition(12, 36);
		d.addChange(new int[] { 31, 17, 1 });
		diamonds.add(d);
		d = new com.neet.DiamondHunter.Entity.Diamond(tileMap);
		d.setTilePosition(28, 4);
		d.addChange(new int[] {27, 7, 1});
		d.addChange(new int[] {28, 7, 1});
		diamonds.add(d);
		d = new com.neet.DiamondHunter.Entity.Diamond(tileMap);
		d.setTilePosition(4, 34);
		d.addChange(new int[] { 31, 21, 1 });
		diamonds.add(d);
		
		d = new com.neet.DiamondHunter.Entity.Diamond(tileMap);
		d.setTilePosition(28, 19);
		diamonds.add(d);
		d = new com.neet.DiamondHunter.Entity.Diamond(tileMap);
		d.setTilePosition(35, 26);
		diamonds.add(d);
		d = new com.neet.DiamondHunter.Entity.Diamond(tileMap);
		d.setTilePosition(38, 36);
		diamonds.add(d);
		d = new com.neet.DiamondHunter.Entity.Diamond(tileMap);
		d.setTilePosition(27, 28);
		diamonds.add(d);
		d = new com.neet.DiamondHunter.Entity.Diamond(tileMap);
		d.setTilePosition(20, 30);
		diamonds.add(d);
		d = new com.neet.DiamondHunter.Entity.Diamond(tileMap);
		d.setTilePosition(14, 25);
		diamonds.add(d);
		d = new com.neet.DiamondHunter.Entity.Diamond(tileMap);
		d.setTilePosition(4, 21);
		diamonds.add(d);
		d = new com.neet.DiamondHunter.Entity.Diamond(tileMap);
		d.setTilePosition(9, 14);
		diamonds.add(d);
		d = new com.neet.DiamondHunter.Entity.Diamond(tileMap);
		d.setTilePosition(4, 3);
		diamonds.add(d);
		d = new com.neet.DiamondHunter.Entity.Diamond(tileMap);
		d.setTilePosition(20, 14);
		diamonds.add(d);
		d = new com.neet.DiamondHunter.Entity.Diamond(tileMap);
		d.setTilePosition(13, 20);
		diamonds.add(d);
		
	}
	
	public void populateItems() {
		com.neet.DiamondHunter.Entity.Item item;
		if ((Mapviewer.Control.axe_x) != 0 && (Mapviewer.Control.axe_y) !=0 && (Mapviewer.Control.boat_x) != 0 && (Mapviewer.Control.boat_y) != 0)
		{
			//Item item;

			item = new com.neet.DiamondHunter.Entity.Item(tileMap);
			item.setType(com.neet.DiamondHunter.Entity.Item.AXE);
			item.setTilePosition(Mapviewer.Control.axe_y, Mapviewer.Control.axe_x);
			items.add(item);

			item = new com.neet.DiamondHunter.Entity.Item(tileMap);
			item.setType(com.neet.DiamondHunter.Entity.Item.BOAT);
			item.setTilePosition(Mapviewer.Control.boat_y, Mapviewer.Control.boat_x);
			items.add(item);

		}
		else
		{
			//Item item;
			item = new com.neet.DiamondHunter.Entity.Item(tileMap);
			item.setType(com.neet.DiamondHunter.Entity.Item.AXE);
			item.setTilePosition(26, 37);
			items.add(item);

			item = new com.neet.DiamondHunter.Entity.Item(tileMap);
			item.setType(com.neet.DiamondHunter.Entity.Item.BOAT);
			item.setTilePosition(12, 4);
			items.add(item);
		}
	}
	
	public void update() {
		
		// check keys
		handleInput();
		
		// check events
		if(eventStart) eventStart();
		if(eventFinish) eventFinish();
		
		if(player.numDiamonds() == player.getTotalDiamonds()) {
			eventFinish = blockInput = true;
		}
		
		// update camera
		int oldxs = xsector;
		int oldys = ysector;
		xsector = player.getx() / sectorSize;
		ysector = player.gety() / sectorSize;
		tileMap.setPosition(-xsector * sectorSize, -ysector * sectorSize);
		tileMap.update();
		
		if(oldxs != xsector || oldys != ysector) {
			com.neet.DiamondHunter.Manager.JukeBox.play("mapmove");
		}
		
		if(tileMap.isMoving()) return;
		
		// update player
		player.update();
		
		// update diamonds
		for(int i = 0; i < diamonds.size(); i++) {
			
			com.neet.DiamondHunter.Entity.Diamond d = diamonds.get(i);
			d.update();
			
			// player collects diamond
			if(player.intersects(d)) {
				
				// remove from list
				diamonds.remove(i);
				i--;
				
				// increment amount of collected diamonds
				player.collectedDiamond();
				
				// play collect sound
				com.neet.DiamondHunter.Manager.JukeBox.play("collect");
				
				// add new sparkle
				com.neet.DiamondHunter.Entity.Sparkle s = new com.neet.DiamondHunter.Entity.Sparkle(tileMap);
				s.setPosition(d.getx(), d.gety());
				sparkles.add(s);
				
				// make any changes to tile map
				java.util.ArrayList<int[]> ali = d.getChanges();
				for(int[] j : ali) {
					tileMap.setTile(j[0], j[1], j[2]);
				}
				if(ali.size() != 0) {
					com.neet.DiamondHunter.Manager.JukeBox.play("tilechange");
				}
				
			}
		}
		
		// update sparkles
		for(int i = 0; i < sparkles.size(); i++) {
			com.neet.DiamondHunter.Entity.Sparkle s = sparkles.get(i);
			s.update();
			if(s.shouldRemove()) {
				sparkles.remove(i);
				i--;
			}
		}
		
		// update items
		for(int i = 0; i < items.size(); i++) {
			com.neet.DiamondHunter.Entity.Item item = items.get(i);
			if(player.intersects(item)) {
				items.remove(i);
				i--;
				item.collected(player);
				com.neet.DiamondHunter.Manager.JukeBox.play("collect");
				com.neet.DiamondHunter.Entity.Sparkle s = new com.neet.DiamondHunter.Entity.Sparkle(tileMap);
				s.setPosition(item.getx(), item.gety());
				sparkles.add(s);
			}
		}
		
	}
	
	public void draw(java.awt.Graphics2D g) {
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw player
		player.draw(g);
		
		// draw diamonds
		for(com.neet.DiamondHunter.Entity.Diamond d : diamonds) {
			d.draw(g);
		}
		
		// draw sparkles
		for(com.neet.DiamondHunter.Entity.Sparkle s : sparkles) {
			s.draw(g);
		}
		
		// draw items
		for(com.neet.DiamondHunter.Entity.Item i : items) {
			i.draw(g);
		}
		
		// draw hud
		hud.draw(g);
		
		// draw transition boxes
		g.setColor(java.awt.Color.BLACK);
		for(int i = 0; i < boxes.size(); i++) {
			g.fill(boxes.get(i));
		}
		
	}
	
	public void handleInput() {
		if(com.neet.DiamondHunter.Manager.Keys.isPressed(com.neet.DiamondHunter.Manager.Keys.ESCAPE)) {
			com.neet.DiamondHunter.Manager.JukeBox.stop("music1");
			gsm.setPaused(true);
		}
		if(blockInput) return;
		if(com.neet.DiamondHunter.Manager.Keys.isDown(com.neet.DiamondHunter.Manager.Keys.LEFT)) player.setLeft();
		if(com.neet.DiamondHunter.Manager.Keys.isDown(com.neet.DiamondHunter.Manager.Keys.RIGHT)) player.setRight();
		if(com.neet.DiamondHunter.Manager.Keys.isDown(com.neet.DiamondHunter.Manager.Keys.UP)) player.setUp();
		if(com.neet.DiamondHunter.Manager.Keys.isDown(com.neet.DiamondHunter.Manager.Keys.DOWN)) player.setDown();
		if(com.neet.DiamondHunter.Manager.Keys.isPressed(com.neet.DiamondHunter.Manager.Keys.SPACE)) player.setAction();
	}
	
	//===============================================
	
	private void eventStart() {
		eventTick++;
		if(eventTick == 1) {
			boxes.clear();
			for(int i = 0; i < 9; i++) {
				boxes.add(new java.awt.Rectangle(0, i * 16, com.neet.DiamondHunter.Main.GamePanel.WIDTH, 16));
			}
		}
		if(eventTick > 1 && eventTick < 32) {
			for(int i = 0; i < boxes.size(); i++) {
				java.awt.Rectangle r = boxes.get(i);
				if(i % 2 == 0) {
					r.x -= 4;
				}
				else {
					r.x += 4;
				}
			}
		}
		if(eventTick == 33) {
			boxes.clear();
			eventStart = false;
			eventTick = 0;
		}
	}
	
	private void eventFinish() {
		eventTick++;
		if(eventTick == 1) {
			boxes.clear();
			for(int i = 0; i < 9; i++) {
				if(i % 2 == 0) boxes.add(new java.awt.Rectangle(-128, i * 16, com.neet.DiamondHunter.Main.GamePanel.WIDTH, 16));
				else boxes.add(new java.awt.Rectangle(128, i * 16, com.neet.DiamondHunter.Main.GamePanel.WIDTH, 16));
			}
			com.neet.DiamondHunter.Manager.JukeBox.stop("music1");
			com.neet.DiamondHunter.Manager.JukeBox.play("finish");
		}
		if(eventTick > 1) {
			for(int i = 0; i < boxes.size(); i++) {
				java.awt.Rectangle r = boxes.get(i);
				if(i % 2 == 0) {
					if(r.x < 0) r.x += 4;
				}
				else {
					if(r.x > 0) r.x -= 4;
				}
			}
		}
		if(eventTick > 33) {
			if(!com.neet.DiamondHunter.Manager.JukeBox.isPlaying("finish")) {
				com.neet.DiamondHunter.Manager.Data.setTime(player.getTicks());
				gsm.setState(com.neet.DiamondHunter.Manager.GameStateManager.GAMEOVER);
			}
		}
	}
	
}
