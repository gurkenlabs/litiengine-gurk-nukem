package com.litiengine.gurknukem;

import com.litiengine.gurknukem.abilities.Jump;

import de.gurkenlabs.litiengine.annotation.CollisionInfo;
import de.gurkenlabs.litiengine.annotation.EntityInfo;
import de.gurkenlabs.litiengine.annotation.MovementInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;

@EntityInfo(width = 18, height = 18)
@MovementInfo(velocity = 50)
@CollisionInfo(collisionBoxWidth = 8, collisionBoxHeight = 16, collision = true)
public class Player extends Creature {

  private static Player instance;

  private final Jump jump;

  private Player() {
    super("gurknukem");

    // setup movement controller
    this.addController(new PlatformingMovementController<>(this));

    // setup the player's abilities
    this.jump = new Jump(this);
  }

  public static Player instance() {
    if (instance == null) {
      instance = new Player();
    }

    return instance;
  }

  public void jump() {
    this.jump.cast();
  }
}