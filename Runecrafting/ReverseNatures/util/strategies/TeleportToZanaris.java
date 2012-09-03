package util.strategies;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;

import util.Constants;

public class TeleportToZanaris extends Strategy implements Runnable {

	@Override
	public boolean validate() {
		SceneObject fairyRing = SceneEntities.getNearest(Constants.TZ_HAAR_FAIRY_RING);
		return fairyRing != null && fairyRing.isOnScreen() ;
	}
	
	@Override
	public void run() {
		SceneObject fairyRing = SceneEntities.getNearest(Constants.TZ_HAAR_FAIRY_RING);
		if (fairyRing != null && fairyRing.isOnScreen()) {
			if (Players.getLocal().getAnimation()==-1 && !Players.getLocal().isMoving() && Game.getClientState()==11)
				fairyRing.interact("Use",fairyRing.getDefinition().getName());
				Time.sleep(1000);
		}
	}
	
}
