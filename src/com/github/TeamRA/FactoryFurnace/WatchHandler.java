package com.github.TeamRA.FactoryFurnace;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.TeamRA.FactoryFurnace.IO.Database;

public class WatchHandler {
	private static boolean loaded = false;
	//private static ArrayList<FFurnace> blocks = new ArrayList<FFurnace>();
	//private boolean useSql = true;
	private static Database  data;

	protected static void load() {
		if(FactoryFurnace.dev)
		{
			System.out.println("loading watch");
		}
		if (loaded)
			return;
		data = new Database();
		try {
			data.connect();
			if(FactoryFurnace.dev)
			{
				System.out.println("connected successfully");
			}
		} catch (Exception e) {
			System.out.println("[FactoryFurnace]Encountered an error: ");
			e.printStackTrace();
			FactoryFurnace.plugin.getPluginLoader().disablePlugin(FactoryFurnace.plugin);
		}
		data.load();
		loaded = true;
	}

	protected static synchronized void addFurnace(Player player, Location loc) {
		
		if(FactoryFurnace.dev)
		{
			System.out.println("adding furn");
		}
		try {
			ArrayList<FFurnace> furn = data.getFurnaces();
			FFurnace temp = new FFurnace(player.getName(), loc);
			for(FFurnace ele:furn)
			{
				if(ele.getLocation().equals(temp.getLocation()))
				{
					player.sendMessage("[FactoryFurnace]That furnace is already registered!");
					return;
				}
			}

			
		} catch (Exception e1) {
		}
		player.sendMessage("[FactoryFurnace]Furnace Registered!");
		data.addFurnace(player.getName(), loc);
	}

	protected static synchronized void removeFurnace(Player player, Location loc) {
		if(FactoryFurnace.dev)
		{
			System.out.println("removing furn");
		}
		/*
		 * TODO check first if allowed
		 * then message player
		 */
		FFurnace temp = data.getFurnaceAt(loc);
		data.removeFurnace(temp.getId());		
	}

	protected static boolean isWatched(Location loc) {
		if(FactoryFurnace.dev)
		{
			System.out.println("checking watch");
		}
		FFurnace temp = data.getFurnaceAt(loc);
		if(temp != null)
		{
			return true;
		}
		return false;
	}

	protected static void rename(Player player, Location loc, String newName) {
		
	}
	public static ArrayList<FFurnace> getFurnaces()
	{
	
		return data.getFurnaces();
	}
}
