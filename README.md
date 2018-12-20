**Software Maintainenance Coursework 2 (Diamond Hunter)** </br>
_for Mr. Hani's reference_</br>
</br>
**Group Member :**
1. Tariq Mohammad Elsyaed Aboelyazid, 18024368 (khfy6tme)
2. Wan Ameera Farhana Wan Azli , 20026533 (hcyww1)
3. Ooi Zheng Khai, 18025226 (khfy6ozk) </br>

**Important :**
1. The latest working branch is the **_Latest-Design_** </br>
2. The **jar file** is found in the **folder _bin, _(bin/)_** if jar didn't open click .bat file to run program</br>


**Disclaimer :** </br>
At the beginning of doing this coursework, we had issues with our github, as we had informed you through fb messenger that only the owner of the repository (Tariq) can do the pushing and adding in commits hence why we decided to just push in bulks hence there is less commit messages in the beginning. </br>

**What had been done :** </br>
1. Requirements : </br>
 - load viewmap </br>
 - axe and boat in maps : 1. default position, 2. saved changed position into playState </br></br>

2. Additional criteria and improvements : </br>
 - "press any key" button in main game is fixed </br>
 - Reset button in viewmap is added where after moving the positions around, player can reset it back to the default/initial state as in the default game </br>
 - Show current coordinates of the items (axe and boat) that has been moved around by player </br>
 - Exit buttons on viewmaps to exit the game entirely whereby when player press on it player is prompted with a confirmation pop-up messages to either proceed or cancel from exitting </br>
 - Fixed on game flow : main game -> viewmap -> main game </br>
 - Interactive viewmap buttons </br>

**The game flow are as below :** </br>
1. When player first enter the game, player will be given three options </br>
 1.1. Play (with default mode) </br>
 1.2. Edit mode (play with editing the item's positions on viewmap) </br>
 1.3. Quit </br>

2. If player choose to go with (1.1.) which is the "Play" button, player will play the game in the default state, where the positions of items (axe and boat) is set up according to the initial writer (programmer). </br>
 2.1. While in the playState, player can decide to pause the game by pressing the "Esc" button on the keyboard to pause the game, and pressing the same button to resume. </br>
 2.2. However, if player decided to go back to main menu from the pauseState, player should press "F1" </br>
 2.3. When player finishes the game and be directed to the gameOverState, player should be able to "press any key" (but in writer's definition, any key is only the 'Enter', 'Space Bar', 'Up', 'Down', 'Left', 'Right', 'Escape', and 'F1', so we will follow according to the writer's definitions), to go back to the main menu so that player can decide to either play again or simply Quit the game </br>
	
3. If player choose to go with (1.2.) which is the "edit mode", the main game window will exit, and player will be directed to the viewmap window whereby player will be able to edit the position of the axe and the boat. </br>
 3.1.In order to change the position of the items (axe and boat), player have to click on the "Change Position" button of the respective items before clicking on the desired position, and this can be done for a multiple of times until player is satisfied, the current coordinates of the axe and boat will also be shown during the process </br>
 3.2.  After changing the positions of the items, player can click on the "save & play!" button, where the new positions of the axe and boat is saved into the main game, and the viewmap window will close to open up the main game back again. As we had decided that player can only edit the viewmap once throughout the whole game, after saving the viewmap and opening up the new window of main game, the menu of the main game will only have 2 options which is "play" and "quit", whereby when player select to "play", player will be able to play the game with the edited positions of axe and boat. The menuState and playState condition will stay throughout the game (2 options menu and the changed positions of axe and boat) eventhough when user go back 	to the menuState from the playState or even from the gameOverState. </br>
 3.3.  On the 'viewmap' windows previously, if player decides to revert back the postion of the axe and boat after changing it here and there, player should press the "Reset" button which will bring the position of the items back to the default state.</br>
 3.4.  Also, on the 'viewmap' windows, if player decided to exit the game, he should press on the "Exit" buttons, and player will be prompted by the system via a pop-up warning to confirm the decisions. If player proceed on exiting the game, the entire game will exit. </br>

4. If player choose to go with (1.3.) which is the "Quit" button, the game will exit entirely. </br>


