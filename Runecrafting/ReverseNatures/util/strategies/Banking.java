package util.strategies;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.bot.Context;

import util.Constants;
import util.PouchHandler;
import util.PouchHandler.Pouch;

public class Banking extends Strategy implements Runnable {
	
	public static boolean npcContact;
	
	public boolean haveNpcContactRunes() {
		return Inventory.getCount(Constants.AIR_RUNE) >= 2 && Inventory.getCount(Constants.ASTRAL_RUNE) >= 1
				&& Inventory.getCount(Constants.COSMIC_RUNE) >= 1;
	}
	
	public boolean haveRepairPouchRunes() {
		return Inventory.getCount(Constants.LAW_RUNE) >= 1 && Inventory.getCount(Constants.ASTRAL_RUNE) >= 2
				&& Inventory.getCount(Constants.COSMIC_RUNE) >= 1;
	}

	@Override
	public boolean validate() {
		return NPCs.getNearest(Constants.BANKER) != null && (!PouchHandler.allFull() 
		|| !Inventory.isFull() || !Inventory.contains(Constants.PURE_ESSENCE));
	}
	
	@Override
	public void run() {
	    if(!Bank.isOpen()) {
	    	if (PouchHandler.haveDegraded()) {
	    		if (npcContact && haveNpcContactRunes()) {
		    		//cast npc contact spell
		    	} else if (haveRepairPouchRunes()) {
		    		//cast repair rune pouch spell
		    	}
	    		return;
	    	}
	        NPC banker = NPCs.getNearest(Constants.BANKER);
	        if(banker != null && banker.isOnScreen()) {
	            if (banker.interact("Bank", banker.getName())) {
	            	final Timer timer = new Timer(1000);
	            	while (timer.isRunning() && !Bank.isOpen()) {
	            		Time.sleep(50);
	            		if (Players.getLocal().isMoving()) {
	            			timer.reset();
	            		}
	            	}
	            }
	        } else {
	            Camera.turnTo(banker);
	        }
	        return;
	    }
	    if (PouchHandler.haveDegraded()) {
	    	if (npcContact) {
	    		// withdraw runes for npc contact
	    	} else {
	    		//withdraw runes for repair rune pouch
	    	}
	    }
	    if (!PouchHandler.allFull()) {
	    	final Pouch pouch = PouchHandler.getPouch();
	    	if (pouch != null) {
	    		if (Inventory.getCount(Constants.PURE_ESSENCE) >= pouch.getMaxEss()) {
	    			if (PouchHandler.fillPouch(pouch)) {
	    				final Timer timer = new Timer(1000);
	    				while (timer.isRunning() && !pouch.isFull()) {
	    					Time.sleep(50);
	    				}
	    				return;
	    			}
	    		} else {
	    			if (Bank.getItemCount(true, Constants.PURE_ESSENCE) > pouch.getMaxEss()) {
	    				if (Bank.withdraw(Constants.PURE_ESSENCE, 0)) {
		    				Time.sleep(500);
		    				return;
		    			}
	    			} else {
	    				Context.get().getActiveScript().log.severe("Lack of pure essence, stopping script!!!");
	    				Context.get().getActiveScript().stop();
	    				return;
	    			}
	    		}
	    	}
	    	return;
	    }
	    if(!Inventory.isFull()) {
	    	if (Bank.getItemCount(true, Constants.PURE_ESSENCE) > (28 - Inventory.getCount())) {
				if (Bank.withdraw(Constants.PURE_ESSENCE, 0)) {
					Time.sleep(500);
					return;
				}
			} else {
				Context.get().getActiveScript().log.severe("Lack of pure essence, stopping script!!!");
				Context.get().getActiveScript().stop();
				return;
			}
	    }
	}	

}
