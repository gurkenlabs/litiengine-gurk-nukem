package com.litiengine.gurknukem;

import java.awt.event.KeyEvent;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.Resources;
import de.gurkenlabs.litiengine.input.Input;

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
    Game.getInfo().setName("GURK NUKEM");
    Game.getInfo().setSubTitle("");
    Game.getInfo().setVersion("v0.0.1");
    Game.getInfo().setWebsite("https://github.com/gurkenlabs/litiengine-gurk-nukem");
    Game.getInfo().setDescription("An example 2D platformer with shooter elements made in the LITIengine");

    // init the game infrastructure
    Game.init(args);

    // set the icon for the game (this has to be done after initialization because the ScreenManager will not be present otherwise)
    Game.getScreenManager().setIconImage(Resources.getImage("icon.png"));

    // make the game exit upon pressing ESCAPE (by default there is no such key binding and the window needs to be shutdown otherwise, e.g. ALT-F4 on Windows)
    Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE, e -> System.exit(0));

    Game.start();
  }
}
