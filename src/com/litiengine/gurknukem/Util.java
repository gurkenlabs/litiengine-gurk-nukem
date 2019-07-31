package com.litiengine.gurknukem;

import java.awt.geom.Rectangle2D;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.CollisionBox;
import de.gurkenlabs.litiengine.physics.Collision;

public final class Util
{
	private Util() {}

	public static boolean isTouchingCeiling(Rectangle2D boundingBox, Rectangle2D collisionBox)
	{
		CollisionBox cb = Game.world().environment().getCollisionBoxes().stream().filter(x -> x.getBoundingBox().intersects(boundingBox))
				.findFirst().orElse(null);

		return cb != null && cb.getCollisionBox().getMaxY() <= collisionBox.getMinY();
	}
	
	public static boolean isTouchingGround(Rectangle2D box)
	{
		Rectangle2D groundCheck = new Rectangle2D.Double(box.getX(), box.getY(), box.getWidth(), box.getHeight() + 1);
		return groundCheck.getMaxY() > Game.physics().getBounds().getMaxY() || Game.physics().collides(groundCheck, Collision.STATIC);
	}
}
