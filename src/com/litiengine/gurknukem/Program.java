package com.litiengine.gurknukem;

import com.litiengine.gurknukem.screens.IngameScreen;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;

/**
 * This class provides the main entry point for the LITIengine game "GURK NUKEM".
 */
public class Program {

  /**
   * The main entry point for the Game.
   * 
   * @param args
   *          The command line arguments.
   */
  public static void main(String[] args) {
    // set meta information about the game
    Game.info().setName("GURK NUKEM");
    Game.info().setSubTitle("");
    Game.info().setVersion("v0.0.2");
    Game.info().setWebsite("https://github.com/gurkenlabs/litiengine-gurk-nukem");
    Game.info().setDescription("An example 2D platformer with shooter elements made in the LITIengine");

    // init the game infrastructure
    Game.init(args);

    // set the icon for the game (this has to be done after initialization because the ScreenManager will not be present otherwise)
    Game.window().setIconImage(Resources.images().get("icon.png"));
    Game.graphics().setBaseRenderScale(4.001f); 

    // load data from the utiLITI game file
    Resources.load("game.litidata");

    PlayerInput.init();
    GurkNukemLogic.init();

    // add the screens that will help you organize the different states of your game
    Game.screens().add(new IngameScreen());

    // load the first level (resources for the map were implicitly loaded from the game file)
    Game.world().loadEnvironment("level1");

    Game.start();
  }
}