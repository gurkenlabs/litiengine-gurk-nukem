package com.litiengine.gurknukem.entities.ai;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.litiengine.gurknukem.entities.ai.behaviors.Behavior;

import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.ai.IBehaviorController;

public class EntityAI<E extends IEntity> implements IBehaviorController
{
	protected final E entity;
	protected final List<Behavior<? extends E>> behaviors;
	
	public EntityAI(E entity, List<Behavior<? extends E>> behaviors)
	{
		this.entity = entity;
		this.behaviors = behaviors;
	}
	
	/**
	 * I assume that if you use the LITIengine you already know when this method is called ^^<br />
	 * Here its the actual functionment:
	 * <ol>
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
		Behavior<? extends E> behavior = this.select(this.behaviors.stream().filter(Behavior::isApplicable).collect(Collectors.toList()));
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
		return (T) this.behaviors.stream().filter(clazz::isInstance).findFirst().orElse(null);
	}
	
	/**
	 * @return All availables {@linkplain Behavior} for this {@linkplain EntityAI}
	 */
	public List<Behavior<? extends E>> getBehaviors() { return Collections.unmodifiableList(this.behaviors); }
	
	/**
	 * Select a {@linkplain Behavior} from the provided list.
	 * The default {@linkplain EntityAI} implementation prioritize behaviors from given list order
	 * @return the selected Behavior
	 */
	public Behavior<? extends E> select(List<Behavior<? extends E>> applicable)
	{
		for (Behavior<? extends E> behavior : this.behaviors) if (applicable.contains(behavior)) return behavior;
		return null;
	}

	/**
	 * Return the stored entity
	 */
	@Override public E getEntity() { return entity; }
}
