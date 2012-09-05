import java.awt.Graphics;
import java.awt.Graphics2D;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.bot.event.listener.PaintListener;

import util.Constants;
import util.Paint;
import util.Util;
import util.strategies.*;

@Manifest
(authors = { "Changed", "ArcaneSanity" }, 
name = "Reverse Natures",
version = 0.01,
description = "",
website = "http://www.powerbot.org/community")

public class ReverseNatures extends ActiveScript implements PaintListener {
	
	@Override
	protected void setup() {
		Constants.NATURE_PRICE = Util.getPrice(Constants.NATURE_RUNE);
		Constants.ESS_PRICE = Util.getPrice(Constants.PURE_ESSENCE);
		provide(new TeleportToBank());
		provide(new Banking());
		provide(new TeleportToZanaris());
		provide(new TeleportFromZanaris());
		provide(new WalkToRuin());
		provide(new GetInRuin());
		provide(new CraftRunes());
	}
	
	@Override
	public void onRepaint(Graphics g2d) {
		Graphics2D g = (Graphics2D) g2d;
		Paint.drawPaint(g);
		Paint.drawMouse(g);
	}
	
}
