package util.strategies;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;

import util.Constants;
import util.PouchHandler;

public class CraftRunes extends Strategy implements Runnable {

	@Override
	public boolean validate() {
		SceneObject altar = SceneEntities.getNearest(Constants.NATURE_ALTAR);
		return altar != null;
	}
	
	@Override
	public void run() {
		SceneObject altar = SceneEntities.getNearest(Constants.NATURE_ALTAR);
		if (altar != null) {
			if (altar.isOnScreen()) {
				if (Inventory.contains(Constants.NATURE_RUNE)) {
					altar.interact("Craft-rune",altar.getDefinition().getName());
					Time.sleep(1000);
				} else if (!PouchHandler.allEmpty()) {
					
				}
			} else {
				Camera.turnTo(altar);
			}
		}
	}

}
