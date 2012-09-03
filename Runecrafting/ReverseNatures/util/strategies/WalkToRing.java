package util.strategies;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.TilePath;
import org.powerbot.game.api.wrappers.node.SceneObject;

import util.Constants;

public class WalkToRing extends Strategy implements Runnable {
	
	Tile[] TO_RING = new Tile[] {new Tile(0000,0000,0), new Tile(0000,0000,0)};
	TilePath toRing = new TilePath(TO_RING);
	
	@Override
	public boolean validate() {
		SceneObject fairyRing = SceneEntities.getNearest(Constants.TZ_HAAR_FAIRY_RING);
		SceneObject BANKER = SceneEntities.getNearest(Constants.BANKER);
		return fairyRing != null && !fairyRing.isOnScreen() && BANKER != null;
	}
	
	@Override
	public void run() {
		toRing.traverse();
	}

}
