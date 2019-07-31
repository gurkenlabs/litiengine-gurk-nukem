package com.litiengine.gurknukem.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.litiengine.gurknukem.entities.ai.LivingEntityAI;
import com.litiengine.gurknukem.entities.ai.behaviors.AvoidFallBehavior;
import com.litiengine.gurknukem.entities.ai.behaviors.Behavior;
import com.litiengine.gurknukem.entities.ai.behaviors.FollowEntityBehavior;
import com.litiengine.gurknukem.entities.ai.behaviors.RandomWalkBehavior;

/**
 * An AI Controller made especially for the Jorge
 */
public class JorgeAIController extends LivingEntityAI<Jorge>
{
	private final RandomWalkBehavior<Jorge> randomWalk;
	private final FollowEntityBehavior<Jorge> followEntity;
	private final AvoidFallBehavior<Jorge> avoidFall;
	
	public JorgeAIController(Jorge j)
	{
		super(j);
		this.randomWalk = new RandomWalkBehavior<>(j);
		this.followEntity = new FollowEntityBehavior<>(j);
		this.avoidFall = new AvoidFallBehavior<>(j);
	}
	
	@Override public List<Behavior<? extends Jorge>> getBehaviorList() { return Arrays.asList(randomWalk, followEntity, avoidFall); }

	@Override
	public Behavior<? extends Jorge> select(ArrayList<Behavior<? extends Jorge>> applicable)
	{
		if (applicable.contains(avoidFall)) return avoidFall;
		if (applicable.contains(followEntity)) return followEntity;
		if (applicable.contains(randomWalk)) return randomWalk;
		return null;
	}
}
