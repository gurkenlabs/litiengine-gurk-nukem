package com.litiengine.gurknukem.entities.ai.behaviors;

import java.awt.geom.Rectangle2D;

import com.litiengine.gurknukem.Util;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Creature;

/**
 * A behavior that makes its {@linkplain Creature} avoid falling upon coming across a cliff
 * @param <C> same as in {@linkplain Behavior}
 */
public class AvoidFallBehavior<C extends Creature> extends Behavior<C>
{
	public AvoidFallBehavior(C creature) { super(creature); }

	@Override
	public void apply()
	{
		this.entity.setFacingDirection(this.entity.getFacingDirection().getOpposite());
		Game.physics().move(this.entity, this.entity.getTickVelocity());
	}

	@Override
	protected boolean checkConditions()
	{
		int m = this.entity.getFacingDirection() == Direction.LEFT ? -1 : 1;
		Rectangle2D box = this.entity.getCollisionBox();
		Rectangle2D preview = new Rectangle2D.Double(box.getX() + m*box.getWidth(), box.getY(), box.getWidth(), box.getHeight());
		return !Util.isTouchingGround(preview);
	}
}
