import java.awt.Graphics;
import java.awt.Graphics2D;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.bot.event.listener.PaintListener;

@Manifest(authors = { "Changed", "ArcaneSanity" }, name = "Reverse Natures")
public class ReverseNatures extends ActiveScript implements PaintListener {

	@Override
	public void onRepaint(Graphics render) {
		Graphics2D g = (Graphics2D) render;
	}

	@Override
	protected void setup() {
		
	}

}
