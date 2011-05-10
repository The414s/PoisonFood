package com.github.The414s.PoisonFood;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.The414s.util.T4Formatter;

public class PoisonFood extends JavaPlugin {

	protected Logger log = Logger.getLogger("Minecraft");
	protected Logger debug = Logger.getLogger("PoisonFood");
	private final PoisonFoodPlayerListener playerListener = new PoisonFoodPlayerListener(
			this);
	static String mainDirectory = "plugins/PoisonFood";
	protected static File config = new File(mainDirectory + File.separator
			+ "config.cfg");
	protected static Properties prop = new Properties();
	PluginDescriptionFile pdfFile = this.getDescription();

	@Override
	public void onDisable() {
		this.debug.info("Plugin disabled");
		for (Handler ele : debug.getHandlers()) {
			debug.removeHandler(ele);
			ele.close();
		}
		//this.log.info("["+pdfFile.getName() + "]["
		//		+ pdfFile.getVersion() + "] is disabled.");
		this.log.info("[PoisonFood]Plugin disabled");
		
		debug = null;
	}

	@Override
	public void onEnable() {
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener,
				Event.Priority.Low, this);
		File file = new File("plugins/PoisonFood/plugin.log");
		new File(mainDirectory).mkdir();
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				this.log.warning("[PoisonFood] Error #7" + e.getMessage());
			}
		}

		try {
			for (Handler ele : debug.getHandlers()) {
				debug.removeHandler(ele);
			}
			debug.setUseParentHandlers(false);
			FileHandler fh = new FileHandler("plugins/PoisonFood/plugin.log");
		
			fh.setFormatter(new T4Formatter());
			debug.addHandler(fh);
			
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			this.log.warning("[PoisonFood] Error #5");
			this.debug.warning(" Error #5");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			this.log.warning("[PoisonFood] Error #6");
			this.debug.warning(" Error #6");
		}
		if (!config.exists()) {
			try {
				config.createNewFile();
				FileOutputStream out = new FileOutputStream(config);
				// gen the config
				prop.put("PoisonedCakeChance", "1");
				prop.put("PoisonedCookieChance", "1");
				prop.put("PoisonedRawPorkChance", "10");
				prop.put("PoisonedCookedPorkChance", "1");
				prop.put("PoisonedRawFishChance", "10");
				prop.put("PoisonedCookedFishChance", "1");
				prop.put("PoisonedBreadChance", "1");
				prop.put("PoisonedAppleChance", "1");
				prop.put("PoisonedGoldenAppleChance", "1");
				prop.put("PoisonedSoupChance", "1");
				//
				prop.put("CakeDamage", "5");
				prop.put("CookieDamage", "5");
				prop.put("RawPorkDamage", "5");
				prop.put("CookedPorkDamage", "5");
				prop.put("RawFishDamage", "5");
				prop.put("CookedFishDamage", "5");
				prop.put("BreadDamage", "5");
				prop.put("AppleDamage", "5");
				prop.put("GoldenAppleDamage", "20");
				prop.put("SoupDamage", "20");
				prop.store(out, null);
				out.flush();
				out.close();
				debug.info("Created Config.cfg");
				log.info("[PoisonFood]Created Config.cfg");
			} catch (IOException ex) {
				this.log.warning("[PoisonFood] Error #2");
				this.debug.warning(" Error #2: " + ex.getMessage());

			}
		} else {
			try {
				prop.load(new FileInputStream(config));
			} catch (FileNotFoundException e) {
				this.log.warning("[PoisonFood] Error #3");
				this.debug.warning(" Error #3: " + e.getMessage());
			} catch (IOException e) {
				this.debug.warning(" Error #4: " + e.getMessage());
				this.log.warning("[PoisonFood] Error #4");
			}
			
			//this.mlog.info("["+pdfFile.getName() + "]["
			//		+ pdfFile.getVersion() + "] is enabled.");
			this.debug.info(" Plugin enabled");
			this.log.info("[PoisonFood] Plugin enabled");
		}
	}

}
