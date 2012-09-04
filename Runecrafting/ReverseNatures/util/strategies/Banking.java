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
		return NPCs.getNearest(Constants.BANKER) != null && !PouchHandler.allFull() 
		&& !Inventory.isFull() && !Inventory.contains(Constants.PURE_ESSENCE);
	}
	
	@Override
	public void run() {
	    if(!Bank.isOpen()) {
	         NPC banker = NPCs.getNearest(Constants.BANKER);
	         if(banker != null && banker.isOnScreen()) {
	             banker.interact("Bank", banker.getName());
	         } else {
	             Camera.turnTo(banker);
	          }
	         return;
	    }
	    //TODO
	}	

}
