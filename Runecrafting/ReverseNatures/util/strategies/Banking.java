package util.strategies;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;

import util.Constants;
import util.PouchHandler;

public class Banking extends Strategy implements Runnable {

	@Override
	public boolean validate() {
		NPC banker = NPCs.getNearest(Constants.BANKER);
		return banker != null && !PouchHandler.allFull();
	}
	
	@Override
	public void run() {
		NPC banker = NPCs.getNearest(Constants.BANKER);
		if (Bank.isOpen()) {
			
		} else {
			if (banker != null) {
				if (banker.isOnScreen()) {
					if (!Players.getLocal().isMoving()) {
						banker.interact("Bank",banker.getName());
						Time.sleep(1000);
					}
				} else {
					Camera.turnTo(banker);
				}
			}
		}
	}

}
