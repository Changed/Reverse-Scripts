import java.awt.Graphics;
import java.awt.Graphics2D;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.bot.event.listener.PaintListener;

import util.Constants;
import util.Util;
import util.strategies.Banking;
import util.strategies.TeleportToZanaris;
import util.strategies.WalkToRing;

@Manifest
(authors = { "Changed", "ArcaneSanity" }, 
name = "Reverse Natures",
version = 0.01,
description = "",
website = "http://www.powerbot.org/community")

public class ReverseNatures extends ActiveScript implements PaintListener {

	public static int NATURE_PRICE = 0;
	public static int ESS_PRICE = 0;
	
	@Override
	protected void setup() {
		NATURE_PRICE = Util.getPrice(Constants.NATURE_RUNE);
		ESS_PRICE = Util.getPrice(Constants.ESSENCE);
		provide(new Banking());
		provide(new WalkToRing());
		provide(new TeleportToZanaris());
	}
	
	@Override
	public void onRepaint(Graphics g2d) {
		Graphics2D g = (Graphics2D) g2d;
	}
	
}
