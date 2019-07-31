package com.litiengine.gurknukem.entities.ai.behaviors;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.IEntity;

/**
 * A behavior that make its {@linkplain Creature} follow entities
 * @param <C> Same as in {@linkplain Behavior}
 * @see #follow(IEntity)
 */
public class FollowEntityBehavior<C extends Creature> extends Behavior<C>
{
	protected double followRange;
	protected IEntity followedEntity;
	protected boolean isFollowing = false;
	
	public FollowEntityBehavior(C creature) { this(creature, null, 90d); }
	public FollowEntityBehavior(C creature, IEntity followed, double followRange)
	{
		super(creature);
		this.followRange = 90d;
		this.followedEntity = followed;
	}
	
	@Override
	public void apply()
	{
		this.entity.setFacingDirection(getDirectionTo(this.entity, this.followedEntity.getLocation().getX()));
		Game.physics().move(this.entity, this.entity.getTickVelocity());
	}

	@Override
	protected boolean checkConditions()
	{
		return this.isFollowing = this.followedEntity != null
				&& this.entity.getLocation().distance(this.followedEntity.getLocation()) <= this.followRange
				&& Math.abs(this.entity.getY() - this.followedEntity.getY()) < this.followRange / 2d
				&& (this.isFollowing
						|| getDirectionTo(this.entity, this.followedEntity.getLocation().getX()) == this.entity.getFacingDirection());
	}
	
	@Override public void ifNotSelected() { this.isFollowing = false; }
	
	/**
	 * Get the {@linkplain Direction} [{@linkplain Direction#LEFT LEFT} or {@linkplain Direction#RIGHT RIGHT}] from a {@linkplain IEntity} to a
	 * specified x coordinate
	 * @param from
	 * @param x
	 * @return the Direction
	 */
	public static Direction getDirectionTo(IEntity from, double x) { return from.getX() < x ? Direction.RIGHT : Direction.LEFT; }
	
	/**
	 * Makes this {@linkplain Behavior} follow the specified entity
	 * @param entity the {@linkplain IEntity} that is followed
	 */
	public void follow(IEntity entity) { this.followedEntity = entity; }
	public void setFollowRange(double range) { this.followRange = range; }
	
	public IEntity getFollowedEntity() { return followedEntity; }
	public double getFollowRange() { return followRange; }
	
	/**
	 * 
	 * @return true if this {@linkplain Behavior}'s {@linkplain Creature} 
	 */
	public boolean isFollowing() { return isFollowing; }
}
