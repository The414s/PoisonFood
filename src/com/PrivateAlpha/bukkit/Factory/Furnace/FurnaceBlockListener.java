package com.PrivateAlpha.bukkit.Factory.Furnace;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.*;
import org.bukkit.block.Block;

public class FurnaceBlockListener extends BlockListener {

	FactoryFurnace plugin;

	public FurnaceBlockListener(FactoryFurnace plugin) {
		this.plugin = plugin;
	}

	private HashMap<String, String> watchClick = new HashMap<String, String>();

	// listen for left click
	public void onBlockDamage(BlockDamageEvent event) {
		Player player = event.getPlayer();
		String todo;
		if ((todo = watchClick.get(player.getName())) != null) {
			if (event.getBlock().getType() != Material.FURNACE && event.getBlock().getType() != Material.BURNING_FURNACE)
			{
				player.sendMessage(ChatColor.RED
						+ "[FactoryFurnace]The Block you left click on needs to be a furnace!");
				return;
			}
			
			watchClick.remove(player.getName());
			Block block = event.getBlock();
			Location loc = block.getLocation();
			if (todo.equals("add")) {
				WatchHandler.addFurnace(player, loc);

			} else if (todo.equals("remove")) {
				WatchHandler.removeFurnace(player,loc);
			} else if (todo.equals("info")) {
				/*
				 * TODO get info about the clicked furnace
				 */
			} else if (todo.equals("rename")) {
				//TODO Find way to allow renaming!
				//WatchHandler.rename(player, loc, "");
				player.sendMessage(ChatColor.RED
						+ "[FactoryFurnace]Renaming is Disabled!");
			}

		}
		/*
		 * MOVED: FurnaceChecker.. TODO check if block is a furnace check if block
		 * has 2 input + 1 output chest send to <get block>
		 */
	}

	public void onBlockBreak(BlockBreakEvent event) {

		Location loc = event.getBlock().getLocation();
		if(WatchHandler.isWatched(loc))
		{
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED+"[FactoryFurnace]You are not allowed to break this furnace!");
			event.getPlayer().sendMessage(ChatColor.RED+"[FactoryFurnace]Use /ffr then left click the furnace to remove");
		}

	}

	protected void addPlayer(String name, String watchFor) {
		watchClick.put(name, watchFor);
	}

	protected void removePlayer(String name) {
		watchClick.remove(name);
	}
}
