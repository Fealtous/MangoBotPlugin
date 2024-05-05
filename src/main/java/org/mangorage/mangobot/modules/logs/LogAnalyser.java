/*
 * This file is written by Asbestosstar. It is not copyrighted, and a Ruby version will be included in the FeatureCreep Moderation Bot which is also not copyrighted. 
 * Feel free to use this in your own software. Free as in Speech, Free as in Beer, No warranties, No Export restrictions.
 * */

package org.mangorage.mangobot.modules.logs;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.mangorage.mangobot.core.Util;

import net.dv8tion.jda.api.entities.Message;

public class LogAnalyser {

	public static String[] supported_paste_sites = new String[] { "paste.mikumikudance.jp/", "paste.centos.org/",
			"pastebin.com/", "mclo.gs/" }; // Be sure to include slashes, Paste.ee and other sites without seperate or
											// only raw URLs will not work, ones with fancy raw URLs like OpenSUSE paste
											// will also not be included at this time.

	public static void scanMessage(Message message) {
		String content = message.getContentStripped();
		for (String uri : getLogURLs(content)) {
			InputStream log = Util.getFileInputStream(uri);
			if (log != null) {
				try {
					String str = Util.getStringFromInputStream(log);
					readLog(message, str);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public static ArrayList<String> getLogURLs(String messaje) {
		ArrayList<String> list = new ArrayList<String>();
		for (String word : messaje.split(" ")) {

			for (String paste : supported_paste_sites) {
				if (word.contains(paste)) {

					if (paste.equals("mclo.gs/")) {
						String[] url_arr = word.split("/");
						String slug = url_arr[url_arr.length - 1];
						list.add("https://api.mclo.gs/1/raw/" + slug);
					} else if (paste.equals("pastebin.com/")) {
						String[] url_arr = word.split("/");
						String slug = url_arr[url_arr.length - 1];
						list.add("https://pastebin.com/raw/" + slug);
					} else { // Add more else ifs for other sites
						if (word.contains("/view/raw/")) {
							list.add(word);
						} else if (word.contains("/view/")) {
							list.add(word.replace("/view/", "/view/raw/"));
						}

					}

				}

			}

		}

		return list;
	}

	public static void readLog(Message messaje, String log) {
			MissingDeps.analyse(log, messaje);
			BrokenDrivers.analyse(log, messaje);
			Java22.analyse(log, messaje);
			MissingScheme.analyse(log, messaje);
			PerfOSCounters.analyse(log, messaje);
			SSLError.analyse(log, messaje);
			URLClassLoaderIssue.analyse(log, messaje);
			WeRequireAtLeastJava17.analyse(log, messaje);
			EarlyWindow.analyse(log, messaje);
	}

}
