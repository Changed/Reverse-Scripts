import java.awt.Graphics;
import java.awt.Graphics2D;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.bot.event.listener.PaintListener;

import util.strategies.Banking;
import util.strategies.WalkToRing;

@Manifest
(authors = { "Changed", "ArcaneSanity" }, 
name = "Reverse Natures",
version = 0.01,
description = "",
website = "http://www.powerbot.org/community")

public class ReverseNatures extends ActiveScript implements PaintListener {

	@Override
	protected void setup() {
		provide(new Banking());
		provide(new WalkToRing());
	}

	@Override
	public void onRepaint(Graphics g2d) {
		Graphics2D g = (Graphics2D) g2d;
	}
	
}
