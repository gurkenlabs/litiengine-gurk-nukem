package com.litiengine.gurknukem.entities.ai.behaviors;

import de.gurkenlabs.litiengine.entities.IEntity;

/**
 * The {@linkplain Behavior} class intends to separate different AI parts as some kind of plug&play desgin.
 * @param <E> the {@linkplain IEntity} child class, allowing Behaviors to be desgined especially for certain Entity types
 */
public abstract class Behavior<E extends IEntity>
{
	protected boolean enabled = true;
	protected final E entity;
	
	/**
	 * Build a {@linkplain Behavior} for the specified creature
	 * @param entity its entity
	 */
	public Behavior(E entity) { this.entity = entity; }
	
	/**
	 * This method is called when this {@linkplain Behavior} is available but not enabled or selected
	 */
	public void ifNotSelected() {}
	
	/**
	 * Apply this {@linkplain Behavior} effects to its creature
	 */
	public abstract void apply();
	
	/**
	 * Check wether the creature is in conditions to be have this {@linkplain Behavior} applied to it
	 * @return false if the creature miss one or more condition, true otherwise
	 */
	protected abstract boolean checkConditions();
	
	/**
	 * @return wether this Behavior is applicable or not at runtime for its creature
	 */
	public boolean isApplicable() { return this.enabled && this.checkConditions(); }
	public boolean isEnabled() { return this.enabled; }
	public E getCreature() { return this.entity; }
	
	public void enable() { this.enabled = true; }
	public void disable() { this.enabled = false; }
}
