package com.litiengine.gurknukem;

import de.gurkenlabs.litiengine.annotation.CollisionInfo;
import de.gurkenlabs.litiengine.annotation.EntityInfo;
import de.gurkenlabs.litiengine.annotation.MovementInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.environment.IEnvironment;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;

@EntityInfo(width = 18, height = 18)
@MovementInfo(velocity = 50)
@CollisionInfo(collisionBoxWidth = 5, collisionBoxHeight = 3, collision = true)
public class Player extends Creature  {

  private static Player instance;

  private Player() {
    super("gurknukem");

    // setup movement controller
    KeyboardEntityController<Player> controller = new KeyboardEntityController<>(this);
    controller.getUpKeys().clear();
    controller.getDownKeys().clear();
    this.addController(controller);
  }

  public static Player instance() {
    if (instance == null) {
      instance = new Player();
    }

    return instance;
  }

  @Override
  public void loaded(IEnvironment environment) {
    super.loaded(environment);

    System.out.println("Player instance loaded: #" + this.getMapId());
  }

}