package com.litiengine.gurknukem.entities.ai;

import de.gurkenlabs.litiengine.entities.Creature;

/**
 * The {@linkplain Behavior} class intends to separate different AI parts as some kind of plug&play desgin.
 * @param <C> the {@linkplain Creature} child class, allowing Behaviors to be desgined especially for a certain Creature type
 */
public abstract class Behavior<C extends Creature>
{
	protected boolean enabled = true;
	protected final C creature;
	
	/**
	 * Build a {@linkplain Behavior} for the specified creature
	 * @param creature its creature
	 */
	public Behavior(C creature) { this.creature = creature; }
	
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
	protected boolean isApplicable() { return this.enabled && this.checkConditions(); }
	public boolean isEnabled() { return this.enabled; }
	public C getCreature() { return this.creature; }
	
	public void enable() { this.enabled = true; }
	public void disable() { this.enabled = false; }
}
