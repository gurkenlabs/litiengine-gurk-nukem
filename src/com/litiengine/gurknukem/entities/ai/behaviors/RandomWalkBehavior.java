package com.litiengine.gurknukem.entities.ai.behaviors;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.util.MathUtilities;

/**
 * This is a simple behavior making its {@linkplain Creature} walk around randomly
 * @param <C> Same as in {@linkplain Behavior}
 */
public class RandomWalkBehavior<C extends Creature> extends Behavior<C>
{
	protected long lastDirectionChange;
	protected long scheduledDirectionChange;
	protected long minTimeBeforeDirectionChange;
	protected long maxTimeBeforeDirectionChange;
	
	public RandomWalkBehavior(C creature) { this(creature, 2000, 5000, Game.time().now() + 10); }
	public RandomWalkBehavior(C creature, long minTimeBeforeDirectionChange, long maxTimeBeforeDirectionChange, long scheduledDirectionChange)
	{
		super(creature);
		this.lastDirectionChange = Game.time().now();
		this.scheduledDirectionChange = scheduledDirectionChange - lastDirectionChange;
		this.minTimeBeforeDirectionChange = minTimeBeforeDirectionChange;
		this.maxTimeBeforeDirectionChange = maxTimeBeforeDirectionChange;
	}
	
	@Override
	public void apply()
	{
		if (Game.time().since(lastDirectionChange) > scheduledDirectionChange)
		{	
			this.entity.setFacingDirection(this.entity.getFacingDirection().getOpposite());
			this.lastDirectionChange = Game.time().now();
			this.scheduledDirectionChange = (long) MathUtilities.randomInRange(this.minTimeBeforeDirectionChange, this.maxTimeBeforeDirectionChange);
		}
		
		Game.physics().move(this.entity, this.entity.getTickVelocity());
	}

	@Override public boolean checkConditions() { return true; }
	
	public void setMinTimeBeforeDirectionChange(long time) { this.minTimeBeforeDirectionChange = time; }
	public void setMaxTimeBeforeDirectionChange(long time) { this.maxTimeBeforeDirectionChange = time; }
	public void setScheduledDirectionChange(long instant) { this.scheduledDirectionChange = instant - lastDirectionChange; }
	
	public long getMinTimeBeforeDirectionChange() { return minTimeBeforeDirectionChange; }
	public long getMaxTimeBeforeDirectionChange() { return maxTimeBeforeDirectionChange; }
	public long getLastDirectionChange() { return lastDirectionChange; }
	public long getScheduledDirectionChange() { return lastDirectionChange + scheduledDirectionChange; }
}
