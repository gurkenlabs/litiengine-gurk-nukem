package com.litiengine.gurknukem.entities.ai;

import java.util.ArrayList;
import java.util.List;

import com.litiengine.gurknukem.entities.ai.behaviors.Behavior;
import com.litiengine.gurknukem.entities.ai.behaviors.DeadEntityBehavior;

import de.gurkenlabs.litiengine.entities.CombatEntity;

/**
 * Basically hijacks the {@link EntityAI} behaviors to add {@link DeadEntityBehavior}
 * @param <C> a {@linkplain CombatEntity} type
 */
public class LivingEntityAI<C extends CombatEntity> extends EntityAI<C>
{
	public LivingEntityAI(C entity, List<Behavior<? extends C>> behaviors)
	{
		super(entity, new ArrayList<>(behaviors));
		this.behaviors.add(0, new DeadEntityBehavior<>(entity));
	}
}
