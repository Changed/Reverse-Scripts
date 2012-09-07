package util.strategies;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.TilePath;

public class WalkToRuin extends Strategy implements Runnable {

	Area inDesert = new Area(new Tile(2796, 2996, 0), new Tile(2876, 3033, 0));
	Tile[] tilesToRuin = new Tile[] {new Tile(2809, 3008, 0), new Tile(2816, 3009, 0), new Tile(2825, 3009, 0), new Tile(2834, 3009, 0), 
									 new Tile(2843, 3008, 0), new Tile(2852, 3008, 0), new Tile(2861, 3011, 0), new Tile(2867, 3017, 0)};
	TilePath toRuin = new TilePath(tilesToRuin);
	
	@Override
	public boolean validate() {
		return inDesert.contains(Players.getLocal().getLocation());
	}

	@Override
	public void run() {
		if (inDesert.contains(Players.getLocal().getLocation())) {
			toRuin.traverse();
		}
		
	}
	
}
