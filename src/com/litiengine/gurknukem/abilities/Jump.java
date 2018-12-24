package com.litiengine.gurknukem.abilities;

import com.litiengine.gurknukem.Player;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.AbilityOrigin;
import de.gurkenlabs.litiengine.abilities.effects.ForceEffect;
import de.gurkenlabs.litiengine.annotation.AbilityInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.ICombatEntity;
import de.gurkenlabs.litiengine.physics.Force;
import de.gurkenlabs.litiengine.physics.GravityForce;

@AbilityInfo(cooldown = 500, origin = AbilityOrigin.COLLISIONBOX_CENTER, duration = 300, value = 200)
public class Jump extends Ability {

  public Jump(Creature executor) {
    super(executor);

    this.addEffect(new JumpEffect(this));
  }

  private class JumpEffect extends ForceEffect {

    protected JumpEffect(Ability ability) {
      super(ability, ability.getAttributes().getValue().getCurrentValue());
    }

    @Override
    protected Force applyForce(ICombatEntity affectedEntity) {
      GravityForce force = new GravityForce(affectedEntity, this.getStrength(), Direction.UP);
      force.setCancelOnCollision(true);
      Player.instance().getMovementController().apply(force);
      return force;
    }
  }
}