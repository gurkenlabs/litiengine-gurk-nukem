package com.litiengine.gurknukem.entities;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.ai.IBehaviorController;
import de.gurkenlabs.litiengine.util.MathUtilities;

public class EnemyController implements IBehaviorController {
  private final Creature enemy;
  private long directionChanged;
  private long nextDirectionChage;
  private Direction direction;

  public EnemyController(Creature enemy) {
    this.enemy = enemy;
  }

  @Override
  public IEntity getEntity() {
    return this.enemy;
  }

  @Override
  public void update() {
    if (this.enemy.isDead()) {
      return;
    }

    final long timeSinceDirectionChange = Game.time().since(this.directionChanged);
    if (timeSinceDirectionChange > this.nextDirectionChage) {
      direction = this.direction == Direction.LEFT ? Direction.RIGHT : Direction.LEFT;
      this.directionChanged = Game.time().now();
      this.nextDirectionChage = MathUtilities.randomInRange(1000, 2000);
    }

    this.getEntity().setAngle(this.direction.toAngle());
    Game.physics().move(this.enemy, this.enemy.getTickVelocity());
  }
}
