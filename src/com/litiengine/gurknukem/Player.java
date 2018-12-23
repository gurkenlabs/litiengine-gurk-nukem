package com.litiengine.gurknukem;

import de.gurkenlabs.litiengine.annotation.CollisionInfo;
import de.gurkenlabs.litiengine.annotation.EntityInfo;
import de.gurkenlabs.litiengine.annotation.MovementInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;

@EntityInfo(width = 18, height = 18)
@MovementInfo(velocity = 50)
@CollisionInfo(collisionBoxWidth = 5, collisionBoxHeight = 18, collision = true)
public class Player extends Creature  {

  private static Player instance;

  private Player() {
    super("gurknukem");

    // setup movement controller
    this.addController(new PlatformingMovementController<>(this));
  }

  public static Player instance() {
    if (instance == null) {
      instance = new Player();
    }

    return instance;
  }
}