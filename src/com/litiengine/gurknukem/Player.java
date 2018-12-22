package com.litiengine.gurknukem;

import de.gurkenlabs.litiengine.annotation.AnimationInfo;
import de.gurkenlabs.litiengine.annotation.CollisionInfo;
import de.gurkenlabs.litiengine.annotation.CombatInfo;
import de.gurkenlabs.litiengine.annotation.EntityInfo;
import de.gurkenlabs.litiengine.annotation.MovementInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.environment.IEnvironment;

@EntityInfo(width = 16, height = 16)
@MovementInfo(velocity = 5)
@CollisionInfo(collisionBoxWidth = 6, collisionBoxHeight = 3, collision = true)
@CombatInfo(hitpoints = 20)
@AnimationInfo(spritePrefix = "gurknukem-")
public class Player extends Creature {
  public Player() {
  }
  
  @Override
  public void loaded(IEnvironment environment) {
    super.loaded(environment);
    System.out.println("Player instance loaded: #" + this.getMapId());
  }
}