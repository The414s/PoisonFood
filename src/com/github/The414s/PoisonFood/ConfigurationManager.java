package com.github.The414s.PoisonFood;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.util.config.Configuration;

public class ConfigurationManager {
	public PoisonFood plugin;
	private Configuration config;
	private Logger debug = Logger.getLogger("TreeDrops");
	

	// Beans
	protected static boolean cocoaBeans = false;
	protected static boolean cocoaBeansCropDrops = false;
	protected static double cocoaBeansCropChance = 0.0;
	protected static boolean cocoaBeansTreeDrops = false;
	protected static double cocoaBeansTreeChance = 0.0;

	// Apples
	protected static boolean apples = false;
	protected static boolean applesTreeDrops = false;
	protected static double applesTreeChance = 0.0;

	// goldenApples
	protected static boolean goldenApples = false;
	protected static boolean goldenApplesTreeDrops = false;
	protected static double goldenApplesTreeChance = 0.0;

	public ConfigurationManager(TreeDrops instance) {
		plugin = instance;
	}

	public void load() {
		config = plugin.getConfiguration();
		if (!new File(plugin.mainDirectory, "config.yml").exists()) {
			defaultConfig();
		}
		loadConfig();
	}

	public void loadConfig() {
		debug.info("Loading Config");
		config.load();
		// beans
		cocoaBeans = config.getBoolean("CocoaBeans.Enabled", false);
		cocoaBeansCropDrops = config.getBoolean("CocoaBeans.Drop-from-crops.Enabled",
				false);
		cocoaBeansCropChance = config.getDouble(
				"CocoaBeans.Drop-from-crops.chance", 0.0);
		cocoaBeansTreeDrops = config.getBoolean("CocoaBeans.Drop-from-trees.Enabled",
				false);
		cocoaBeansTreeChance = config.getDouble(
				"CocoaBeans.Drop-from-trees.chance", 0.0);
		debug.info("cocoaBeans: " + cocoaBeans);
		debug.info("cocoaBeansCropDrops: " + cocoaBeansCropDrops);
		debug.info("cocoaBeansCropChance: " + cocoaBeansCropChance);
		debug.info("cocoaBeansTreeDrops: " + cocoaBeansTreeDrops);
		debug.info("cocoaBeansTreeChance: " + cocoaBeansTreeChance);
		// apples
		apples = config.getBoolean("Apples.Enabled", false);
		applesTreeDrops = config.getBoolean("Apples.Drop-from-trees.Enabled", false);
		applesTreeChance = config.getDouble("Apples.Drop-from-trees.chance",
				0.0);
		debug.info("apples: " + apples);
		debug.info("applesTreeDrops: " + applesTreeDrops);
		debug.info("applesTreeChance: " + applesTreeChance);

		// golden
		goldenApples = config.getBoolean("GoldenApples.Enabled", false);
		goldenApplesTreeDrops = config.getBoolean(
				"GoldenApples.Drop-from-trees.Enabled", false);
		goldenApplesTreeChance = config.getDouble(
				"GoldenApples.Drop-from-trees.chance", 0.0);
		debug.info("goldenApples: " + goldenApples);
		debug.info("goldenApplesTreeDrops: " + goldenApplesTreeDrops);
		debug.info("goldenApplesTreeChance: " + goldenApplesTreeChance);
		debug.info("Loaded");
	}

	public void defaultConfig() {
		/*
		 * 
		 * 				prop.put("PoisonedCakeChance", "1");
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
		 * 
		 * */
		
		// beans
		config.setProperty("Cake", true);
		config.setProperty("Cake.Enabled", true);
		config.setProperty("Cake.Chance", 1);
		config.setProperty("Cake.Damage", 5);
		//cookies
		config.setProperty("Cookie", true);
		config.setProperty("Cookie.Enabled", true);
		config.setProperty("Cookie.Chance", 1);
		config.setProperty("Cookie.Damage", 5);
		//rawpork
		config.setProperty("RawPork", true);
		config.setProperty("RawPork.Enabled", true);
		config.setProperty("RawPork.Chance", 10);
		config.setProperty("RawPork.Damage", 5);

		config.save();
		debug.info("Created new Config");
	}

}
