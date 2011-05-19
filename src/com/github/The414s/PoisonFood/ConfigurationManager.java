package com.github.The414s.PoisonFood;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.util.config.Configuration;

public class ConfigurationManager {
	public PoisonFood plugin;
	protected static Configuration config;
	private Logger debug = Logger.getLogger("TreeDrops");

	public ConfigurationManager(PoisonFood instance) {
		debug.setUseParentHandlers(false);
		plugin = instance;
	}

	@SuppressWarnings("static-access")
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
		debug.info("Loaded");
	}

	public void defaultConfig() {
		// Cake
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
		//Cooked pork
		config.setProperty("CookedPork", true);
		config.setProperty("CookedPork.Enabled", true);
		config.setProperty("CookedPork.Chance", 1);
		config.setProperty("CookedPork.Damage", 5);
		//RawFish
		config.setProperty("RawFish", true);
		config.setProperty("RawFish.Enabled", true);
		config.setProperty("RawFish.Chance", 10);
		config.setProperty("RawFish.Damage", 5);
		//CookedFish
		config.setProperty("CookedFish", true);
		config.setProperty("CookedFish.Enabled", true);
		config.setProperty("CookedFish.Chance", 1);
		config.setProperty("CookedFish.Damage", 5);
		//Bread
		config.setProperty("Bread", true);
		config.setProperty("Bread.Enabled", true);
		config.setProperty("Bread.Chance", 1);
		config.setProperty("Bread.Damage", 5);
		//Apple
		config.setProperty("Apple", true);
		config.setProperty("Apple.Enabled", true);
		config.setProperty("Apple.Chance", 1);
		config.setProperty("Apple.Damage", 5);
		//Golden Apple
		config.setProperty("GoldenApple", true);
		config.setProperty("GoldenApple.Enabled", true);
		config.setProperty("GoldenApple.Chance", 1);
		config.setProperty("GoldenApple.Damage", 20);
		//Soup
		config.setProperty("Soup", true);
		config.setProperty("Soup.Enabled", true);
		config.setProperty("Soup.Chance", 1);
		config.setProperty("Soup.Damage", 5);

		config.save();
		debug.info("Created new Config");
	}

}
