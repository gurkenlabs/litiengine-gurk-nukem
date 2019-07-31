package com.litiengine.gurknukem.entities.ai;

import java.util.ArrayList;
import java.util.List;

import com.litiengine.gurknukem.entities.ai.behaviors.Behavior;
import com.litiengine.gurknukem.entities.ai.behaviors.DeadEntityBehavior;

import de.gurkenlabs.litiengine.entities.CombatEntity;

/**
 * Basically hijacks the {@link EntityAI} behaviors to add {@link DeadEntityBehavior}
 * @param <C>
 */
public abstract class LivingEntityAI<C extends CombatEntity> extends EntityAI<C>
{
	protected DeadEntityBehavior<C> dead;
	
	public LivingEntityAI(C entity)
	{
		super(entity);
		this.dead = new DeadEntityBehavior<>(entity);
	}
	
	@Override
	public ArrayList<Behavior<? extends C>> getBehaviors()
	{
		ArrayList<Behavior<? extends C>> behaviors = new ArrayList<>(this.getBehaviorList());
		if (!behaviors.contains(dead)) behaviors.add(dead);
		return behaviors;
	}
	
	@Override
	public Behavior<? extends C> select(List<Behavior<? extends C>> applicable)
	{
		if (applicable.contains(dead)) return dead;
		return this.select(new ArrayList<>(applicable));
	}
	
	public abstract List<Behavior<? extends C>> getBehaviorList();
	public abstract Behavior<? extends C> select(ArrayList<Behavior<? extends C>> applicable);
}
