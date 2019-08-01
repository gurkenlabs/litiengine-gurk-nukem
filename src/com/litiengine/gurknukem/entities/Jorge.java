package com.litiengine.gurknukem.entities;

import java.util.Arrays;

import com.litiengine.gurknukem.Player;
import com.litiengine.gurknukem.entities.ai.LivingEntityAI;
import com.litiengine.gurknukem.entities.ai.behaviors.AvoidFallBehavior;
import com.litiengine.gurknukem.entities.ai.behaviors.FollowEntityBehavior;
import com.litiengine.gurknukem.entities.ai.behaviors.RandomWalkBehavior;

import de.gurkenlabs.litiengine.annotation.AnimationInfo;
import de.gurkenlabs.litiengine.annotation.EntityInfo;
import de.gurkenlabs.litiengine.entities.Creature;

@EntityInfo(width = 18, height = 18)
@AnimationInfo(spritePrefix = "jorge")
public class Jorge extends Creature
{
	public Jorge()
	{
		this.addController(new JorgeAI(this));
		this.getController(JorgeAI.class).getBehavior(FollowEntityBehavior.class).follow(Player.instance());
	}
	
	public class JorgeAI extends LivingEntityAI<Jorge>
	{
		public JorgeAI(Jorge j)
		{
			super(j, Arrays.asList(new AvoidFallBehavior<>(j), new FollowEntityBehavior<>(j), new RandomWalkBehavior<>(j)));
		}
	}
}
