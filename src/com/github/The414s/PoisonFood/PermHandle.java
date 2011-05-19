package com.github.The414s.PoisonFood;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class PermHandle {

	protected static boolean cake;
	protected static boolean cookie;
	protected static boolean rawFish;
	protected static boolean cookedFish;
	protected static boolean rawPork;
	protected static boolean cookedPork;
	protected static boolean apple;
	protected static boolean goldenApple;
	protected static boolean soup;
	protected static boolean bread;

	private static PermissionHandler pm;
	private static boolean loadedP = false;
	private static boolean loaded = false;
	private static PoisonFood plugin;

	private static void load() {
		setupPermissions();
		loaded = true;
	}

	
	 static void handoff(PoisonFood poisonFood) {
		plugin = poisonFood;

	}

	protected static void newPlayer(Player player) {
		if (!loaded) {
			load();
		}
		if (loadedP) {
			cake = pm.has(player, "PoisonFood.immunity.cake");
			cookie = pm.has(player, "PoisonFood.immunity.cookie");
			rawFish = pm.has(player, "PoisonFood.immunity.rawFish");
			cookedFish = pm.has(player, "PoisonFood.immunity.cookedFish");
			rawPork = pm.has(player, "PoisonFood.immunity.rawPork");
			cookedPork = pm.has(player, "PoisonFood.immunity.cookedPork");
			apple = pm.has(player, "PoisonFood.immunity.apple");
			goldenApple = pm.has(player, "PoisonFood.immunity.goldenApple");
			soup = pm.has(player, "PoisonFood.immunity.soup");
			bread = pm.has(player, "PoisonFood.immunity.bread");
		} else {
			cake = false;
			cookie = false;
			rawFish = false;
			cookedFish = false;
			rawPork = false;
			cookedPork = false;
			apple = false;
			goldenApple = false;
			soup = false;
			bread = false;
		}

	}

	private static void setupPermissions() {
		
		plugin.log.info("[PoisonFood] Loading permissions");
		Plugin test = plugin.getServer().getPluginManager()
				.getPlugin("Permissions");
		
		if (pm == null) {
			if (test != null) {
				pm = ((Permissions) test).getHandler();
				loadedP = true;
				plugin.log.info("[PoisonFood] Permissions loaded");
			} else {
				loadedP = false;
				plugin.log.info("[PoisonFood] Permissions not found");
			}
		}
	}
}
