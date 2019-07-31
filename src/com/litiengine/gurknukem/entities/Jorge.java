package com.litiengine.gurknukem.entities;

import com.litiengine.gurknukem.Player;
import com.litiengine.gurknukem.entities.ai.behaviors.FollowEntityBehavior;

import de.gurkenlabs.litiengine.annotation.AnimationInfo;
import de.gurkenlabs.litiengine.annotation.EntityInfo;
import de.gurkenlabs.litiengine.entities.Creature;

@EntityInfo(width = 18, height = 18)
@AnimationInfo(spritePrefix = "jorge")
public class Jorge extends Creature
{
	public Jorge()
	{
		this.addController(new JorgeAIController(this));
		this.getController(JorgeAIController.class).getBehavior(FollowEntityBehavior.class).follow(Player.instance());
	}
}
