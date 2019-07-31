package com.litiengine.gurknukem.entities.ai.behaviors;

import de.gurkenlabs.litiengine.entities.CombatEntity;

public class DeadEntityBehavior<C extends CombatEntity> extends Behavior<C>
{
	public DeadEntityBehavior(C entity) {
		super(entity);
	}

	@Override public void apply() { }
	@Override protected boolean checkConditions() { return this.entity.isDead(); }
}
