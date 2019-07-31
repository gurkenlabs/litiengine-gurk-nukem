package com.litiengine.gurknukem.entities;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.litiengine.gurknukem.entities.ai.AvoidFallBehavior;
import com.litiengine.gurknukem.entities.ai.Behavior;
import com.litiengine.gurknukem.entities.ai.CreatureAIController;
import com.litiengine.gurknukem.entities.ai.FollowEntityBehavior;
import com.litiengine.gurknukem.entities.ai.RandomWalkBehavior;

/**
 * An AI Controller made especially for the Jorge
 */
public class JorgeAIController extends CreatureAIController<Jorge>
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
	
	@Override public Collection<Behavior<? extends Jorge>> getBehaviors() { return Arrays.asList(randomWalk, followEntity, avoidFall); }

	@Override
	public Behavior<? extends Jorge> select(List<Behavior<? extends Jorge>> applicable)
	{
		Behavior<Jorge> behavior = null;
		if (applicable.contains(randomWalk)) behavior = randomWalk;
		if (applicable.contains(followEntity)) behavior = followEntity;
		if (applicable.contains(avoidFall)) behavior = avoidFall;
		return behavior;
	}
}
