package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	public static int getPrice(final int id) {
		try {
			final URLConnection con = new URL("http://open.tip.it/json/ge_single_item?item=" + id).openConnection();
	       		final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        	final String line = in.readLine();
	       		in.close();
	        	final Matcher matcher = Pattern.compile("\"mark_price\":\"(.*?)\"").matcher(line);
	        	if (matcher.find()) {
	              		return new Integer(matcher.group(1).replaceAll(",", ""));
	        	}
		} catch (Exception e) {}
		return 0;
	}
}
