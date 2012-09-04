package util;

import java.util.LinkedHashSet;
import java.util.Set;

import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;

public class PouchHandler {

	public enum Pouch {
		GIANT(5514, 0xF, 18, 0x3, 8, 12),
		LARGE(5512, 0xF, 9, 0x3, 4, 9),
		MEDIUM(5510, 0x7, 3, 0x3, 2, 6),
		SMALL(5509, 0x3, 0, 0x3, 0, 3);
		
		private final int id, mask, shift, mask2, shift2, maxEss;
		
		Pouch(final int id, final int mask, final int shift, final int mask2, final int shift2, final int maxEss) {
			this.id = id;
			this.mask = mask;
			this.shift = shift;
			this.mask2 = mask2;
			this.shift2 = shift2;
			this.maxEss = maxEss;
		}
		
		public int getId() {
			return this.id;
		}
		
		public Item getItem() {
			return Inventory.getItem(this.id);
		}
		
		public int getEssCount() {
			return Settings.get(486, this.shift, this.mask);
		}
		
		public boolean isEmpty() {
			return Settings.get(720, this.shift2, this.mask2) == 0;
		}
		
		public boolean isFull() {
			return getEssCount() == this.maxEss;
		}
	}
	
	public static boolean fillPouch(final Pouch pouch) {
		final Item item = pouch.getItem();
		if (item != null) {
			return item.getWidgetChild().interact("Fill", item.getName());
		}
		return false;
	}
	
	public static boolean emptyPouch(final Pouch pouch) {
		final Item item = pouch.getItem();
		if (item != null) {
			return item.getWidgetChild().interact("Empty", item.getName());
		}
		return false;
	}
	
	public static Item getPouch() {
		Item pouch = null;
		for (Pouch p : Pouch.values()) {
			if (Inventory.contains(p.getId()) && !p.isFull()) {
				pouch = p.getItem();
				break;
			}
		}
		return pouch;
	}
	
	public static Pouch[] getPouches() {
		Set<Pouch> used = new LinkedHashSet<Pouch>();
		for (Pouch p : Pouch.values()) {
			if (Inventory.contains(p.getId())) {
				used.add(p);
			}
		}
		return used.toArray(new Pouch[used.size()]);
	}
	
	public static boolean allFull() {
		for (Pouch p : getPouches()) {
			if (!p.isFull()) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean allEmpty() {
		for (Pouch p : getPouches()) {
			if (!p.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public static Item getDegradedPouch() {
		return Inventory.getItem(Constants.DEGRADED_POUCHES);
	}
	
	public static boolean haveDegraded() {
		return getDegradedPouch() != null;
	}
}
