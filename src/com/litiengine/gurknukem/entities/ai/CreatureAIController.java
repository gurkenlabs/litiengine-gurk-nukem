package com.litiengine.gurknukem.entities.ai;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.ai.IBehaviorController;

public abstract class CreatureAIController<C extends Creature> implements IBehaviorController
{
	protected C creature;
	
	public CreatureAIController(C creature)
	{
		this.creature = creature;
	}
	
	/**
	 * I assume that if you use the LITIengine you already know when this method is called ^^<br />
	 * Here its the actual functionment:
	 * <ol>
	 * 	<li>If the creature is dead, return</li>
	 * 	<li>Store all {@linkplain Behavior}s returned by {@linkplain #getBehaviors()}</li>
	 * 	<li>Select and store a sole Behavior from all <i>applicables</i> Behaviors</li>
	 * 	<li>Call the {@linkplain Behavior#apply()} method of the stored instance</li>
	 * 	<li>Call the {@linkplain Behavior#ifNotSelected()} of all other Behaviors (even non-applicable ones)</li> 
	 * </ol>
	 * @see Behavior#isApplicable()
	 */
	@Override
	public void update()
	{
		if (this.creature.isDead()) return;
		Collection<Behavior<? extends C>> behaviors = this.getBehaviors();
		
		Behavior<? extends C> behavior = this.select(behaviors.stream().filter(Behavior::isApplicable).collect(Collectors.toList()));
		if (behavior != null) behavior.apply();
		
		behaviors.stream().filter(b -> b != behavior).forEach(Behavior::ifNotSelected);
	}
	
	/**
	 * @param clazz {@linkplain Class}{@literal <? extends }{@linkplain Behavior}{@literal <? extends C>>}
	 * @return the first Behavior instance of the specified class
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T extends Behavior> T getBehavior(Class<T> clazz)
	{
		return (T) this.getBehaviors().stream().filter(clazz::isInstance).findFirst().orElse(null);
	}
	
	/**
	 * @return All availables {@linkplain Behavior} for this {@linkplain CreatureAIController}
	 */
	public abstract Collection<Behavior<? extends C>> getBehaviors();
	
	/**
	 * Select a {@linkplain Behavior} from the provided list.
	 * This method intends to create a priority between behaviors.
	 * @return the selected Behavior
	 */
	public abstract Behavior<? extends C> select(List<Behavior<? extends C>> applicable);

	/**
	 * Return the stored creature
	 */
	@Override public C getEntity() { return creature; }
}
