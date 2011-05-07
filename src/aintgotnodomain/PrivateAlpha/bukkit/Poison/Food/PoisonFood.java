package aintgotnodomain.PrivateAlpha.bukkit.Poison.Food;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PoisonFood extends JavaPlugin {

	protected Logger mlog = Logger.getLogger("Minecraft");
	protected Logger log;
	private final PoisonFoodPlayerListener playerListener = new PoisonFoodPlayerListener(
			this);
	static String mainDirectory = "plugins/PoisonFood";
	protected static File config = new File(mainDirectory + File.separator
			+ "config.cfg");
	protected static Properties prop = new Properties();

	@Override
	public void onDisable() {
		for (Handler ele : log.getHandlers()) {
			log.removeHandler(ele);
			ele.close();
		}
		this.mlog.info("[PoisonFood]Plugin disabled");
		this.log.info("[PoisonFood]Plugin disabled");
		log = null;
	}

	@Override
	public void onEnable() {
		//System.out.println("BlagSSSSSSSSSSSSSSSSS");
		log = Logger.getLogger("PoisonFood");
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
			for (Handler ele : log.getHandlers()) {
				log.removeHandler(ele);
			}
			log.setUseParentHandlers(false);
			FileHandler fh = new FileHandler("plugins/PoisonFood/plugin.log");
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			log.addHandler(fh);
			
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			this.mlog.warning("[PoisonFood] Error #5");
			this.log.warning("[PoisonFood] Error #5");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			this.mlog.warning("[PoisonFood] Error #6");
			this.log.warning("[PoisonFood] Error #6");
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
				log.info("[PoisonFood]Created Config.cfg");
				mlog.info("[PoisonFood]Created Config.cfg");
			} catch (IOException ex) {
				this.mlog.warning("[PoisonFood] Error #2");
				this.log.warning("[PoisonFood] Error #2: " + ex.getMessage());

			}
		} else {
			try {
				prop.load(new FileInputStream(config));
			} catch (FileNotFoundException e) {
				this.mlog.warning("[PoisonFood] Error #3");
				this.log.warning("[PoisonFood] Error #3: " + e.getMessage());
			} catch (IOException e) {
				this.log.warning("[PoisonFood] Error #4: " + e.getMessage());
				this.mlog.warning("[PoisonFood] Error #4");
			}
			this.mlog.info("[PoisonFood] Plugin enabled");
			this.log.info("[PoisonFood] Plugin enabled");
		}
	}

}
