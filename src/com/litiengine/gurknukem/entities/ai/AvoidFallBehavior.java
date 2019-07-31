package com.litiengine.gurknukem.entities.ai;

import java.awt.geom.Rectangle2D;

import com.litiengine.gurknukem.Util;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Creature;

/**
 * A behavior that makes the {@linkplain Creature} avoid falling when going on a cliff
 * @param <C> same as in {@linkplain Behavior}
 */
public class AvoidFallBehavior<C extends Creature> extends Behavior<C>
{
	public AvoidFallBehavior(C creature) { super(creature); }

	@Override
	public void apply()
	{
		this.creature.setFacingDirection(this.creature.getFacingDirection().getOpposite());
		Game.physics().move(this.creature, this.creature.getTickVelocity());
	}

	@Override
	protected boolean checkConditions()
	{
		int m = this.creature.getFacingDirection() == Direction.LEFT ? -1 : 1;
		Rectangle2D box = this.creature.getCollisionBox();
		Rectangle2D preview = new Rectangle2D.Double(box.getX() + m*box.getWidth(), box.getY(), box.getWidth(), box.getHeight());
		return !Util.isTouchingGround(preview);
	}
}
