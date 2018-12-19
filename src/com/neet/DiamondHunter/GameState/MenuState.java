// The main menu GameState.

package com.neet.DiamondHunter.GameState;

public class MenuState extends GameState {

	public static int menupage_state = 0; /*is used as a flag to notify if player has updated the item's position in the
	 map, since we decided that the game should work in a way such that player can only edit the items position in the
	 viewmap only once throughout the game*/
	private java.awt.image.BufferedImage bg;
	private java.awt.image.BufferedImage diamond;
	private int currentOption = 0;
	private String[] options = {
		"PLAY",
		"EDIT MODE",
		"QUIT"
	};
    

	public MenuState(com.neet.DiamondHunter.Manager.GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		bg = com.neet.DiamondHunter.Manager.Content.MENUBG[0][0];
		diamond = com.neet.DiamondHunter.Manager.Content.DIAMOND[0][0];
		com.neet.DiamondHunter.Manager.JukeBox.load("/SFX/collect.wav", "collect");
		com.neet.DiamondHunter.Manager.JukeBox.load("/SFX/menuoption.wav", "menuoption");
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(java.awt.Graphics2D g) {
			g.drawImage(bg, 0, 0, null);
			if (menupage_state == 0) { //when user first open the game (have not update the viewmap)
				com.neet.DiamondHunter.Manager.Content.drawString(g, options[0], 48, 90);
				com.neet.DiamondHunter.Manager.Content.drawString(g, options[1], 28, 105);
				com.neet.DiamondHunter.Manager.Content.drawString(g, options[2], 48, 120);

                if (currentOption == 0) g.drawImage(diamond, 25, 86, null);
                else if (currentOption == 1) g.drawImage(diamond, 8, 101, null);
                else if (currentOption == 2) g.drawImage(diamond, 25, 116, null);
			}
			else if (menupage_state == 1) { //after user have update the viewmap
				com.neet.DiamondHunter.Manager.Content.drawString(g, options[0], 48, 90);
				com.neet.DiamondHunter.Manager.Content.drawString(g, options[2], 48, 105);

                if (currentOption == 0) g.drawImage(diamond, 25, 86, null);
                else if (currentOption == 1) g.drawImage(diamond, 25, 101, null);
			}


	}
	
	public void handleInput() {

	   if (com.neet.DiamondHunter.Manager.Keys.isPressed(com.neet.DiamondHunter.Manager.Keys.DOWN) && currentOption < options.length - 1) {
	        com.neet.DiamondHunter.Manager.JukeBox.play("menuoption");
	        currentOption++;
	    }

		if(com.neet.DiamondHunter.Manager.Keys.isPressed(com.neet.DiamondHunter.Manager.Keys.UP) && currentOption > 0) {
			com.neet.DiamondHunter.Manager.JukeBox.play("menuoption");
			currentOption--;
		}
		if(com.neet.DiamondHunter.Manager.Keys.isPressed(com.neet.DiamondHunter.Manager.Keys.ENTER)) {
			com.neet.DiamondHunter.Manager.JukeBox.play("collect");
			selectOption();
		}
	}
	
	private void selectOption() {
		if (currentOption == 0) {
			gsm.setState(com.neet.DiamondHunter.Manager.GameStateManager.PLAY);
		}
		if (currentOption == 1) {
			//Prevent game to run Mapviewer more than once and catch threading error
            try{
                com.neet.DiamondHunter.Main.Game.window.setVisible(false);//hides main game until user is done from mapviewer
                Mapviewer.Start.main();
				currentOption = 0;//resets currentOption
            }catch (IllegalStateException e)
            {
                System.exit(0);
            }

		}
		if (currentOption == 2) {
			System.exit(0);


		}
	}

}
