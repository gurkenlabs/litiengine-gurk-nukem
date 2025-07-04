package com.litiengine.gurknukem.entities;

import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;

@EntityInfo(width = 18, height = 18)
@AnimationInfo(spritePrefix = "jorge")
//@MovementInfo(velocity = 10)
public class Jorge extends Creature {

  public Jorge() {
    this.addController(new EnemyController(this));
  }
}
