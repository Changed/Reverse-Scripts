package util.strategies;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;

import util.Constants;

public class GetInRuin extends Strategy implements Runnable {

	@Override
	public boolean validate() {
		SceneObject ruins = SceneEntities.getNearest(Constants.NATURE_RUINS);
		return ruins != null;
	}
	
	@Override
	public void run() {
		SceneObject ruins = SceneEntities.getNearest(Constants.NATURE_RUINS);
		if (ruins != null) {
			if (ruins.isOnScreen()) {
				if (Game.getClientState()==11) {
					ruins.interact("Enter",ruins.getDefinition().getName());
					Time.sleep(1000);
				}
			} else Walking.walk(ruins);
		}
	}

}
