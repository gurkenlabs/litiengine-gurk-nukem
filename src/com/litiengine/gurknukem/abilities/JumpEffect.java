package com.litiengine.gurknukem.abilities;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.abilities.effects.EffectApplication;
import de.gurkenlabs.litiengine.abilities.effects.ForceEffect;
import de.gurkenlabs.litiengine.abilities.targeting.TargetingStrategy;
import de.gurkenlabs.litiengine.entities.CollisionBox;
import de.gurkenlabs.litiengine.entities.ICombatEntity;
import de.gurkenlabs.litiengine.entities.IMobileEntity;
import de.gurkenlabs.litiengine.physics.Force;
import de.gurkenlabs.litiengine.physics.GravityForce;
import java.util.Optional;

public class JumpEffect extends ForceEffect {

  protected JumpEffect(ICombatEntity executor, int strength, int duration) {
    super(TargetingStrategy.executingEntity(), executor, strength, duration);
  }

  @Override protected Force createForce(IMobileEntity affectedEntity) {
    return new GravityForce(affectedEntity, getStrength(), Direction.UP);
  }

  @Override protected boolean hasEnded(final EffectApplication appliance) {
    return super.hasEnded(appliance) || this.isTouchingCeiling();
  }

  /**
   * Make sure that the jump is cancelled when the entity touches a static collision box above it.
   *
   * @return True if the entity touches a static collision box above it.
   */
  private boolean isTouchingCeiling() {

    Optional<CollisionBox> opt =
        Game.world().environment().getCollisionBoxes().stream().filter(x -> x.getBoundingBox().intersects(getExecutingEntity().getBoundingBox()))
            .findFirst();
    if (opt.isEmpty()) {
      return false;
    }

    CollisionBox box = opt.get();
    return box.getCollisionBox().getMaxY() <= getExecutingEntity().getCollisionBox().getMinY();
  }
}
