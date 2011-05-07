package aintgotnodomain.PrivateAlpha.bukkit.Poison.Food;

import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

public class PoisonFoodPlayerListener extends PlayerListener {
	protected PoisonFood plugin = null;

	public PoisonFoodPlayerListener(PoisonFood poisonFood) {
		plugin = poisonFood;
	}

	public void onPlayerInteract(PlayerInteractEvent event) {
		plugin.log.info("[PoisonFood] Entering on player interact");
		// plugin.log.info("[PoisonFood]");

		try {
			if (event.getAction().equals(Action.LEFT_CLICK_AIR)
					|| event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
				try {
					plugin.log.info("[PoisonFood] Block type: "
							+ event.getClickedBlock().getType());
				} catch (Exception e) {
					plugin.log.info("[PoisonFood] Block type: AIR");
				}
				plugin.log.info("[PoisonFood] Its been returned");

				plugin.log
						.info("#############################################################");
				return;
			}

			if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if (event.getClickedBlock().getType() == Material.CHEST
						|| event.getClickedBlock().getType() == Material.FURNACE
						|| event.getClickedBlock().getType() == Material.WORKBENCH
						|| event.getClickedBlock().getType() == Material.BURNING_FURNACE
						|| event.getClickedBlock().getType() == Material.BOAT
						|| event.getClickedBlock().getType() == Material.BOOKSHELF
						|| event.getClickedBlock().getType() == Material.DISPENSER
						|| event.getClickedBlock().getType() == Material.DIODE
						|| event.getClickedBlock().getType() == Material.DIODE_BLOCK_OFF
						|| event.getClickedBlock().getType() == Material.DIODE_BLOCK_ON
						|| event.getClickedBlock().getType() == Material.WOODEN_DOOR
						|| event.getClickedBlock().getType() == Material.JUKEBOX
						|| event.getClickedBlock().getType() == Material.NOTE_BLOCK
						|| event.getClickedBlock().getType() == Material.LEVER
						|| event.getClickedBlock().getType() == Material.MINECART
						|| event.getClickedBlock().getType() == Material.STORAGE_MINECART
						|| event.getClickedBlock().getType() == Material.BED_BLOCK
						|| event.getClickedBlock().getType() == Material.STONE_BUTTON) {
					plugin.log.info("[PoisonFood] Block type: "
							+ event.getClickedBlock().getType());
					plugin.log.info("[PoisonFood] Its been returned");

					plugin.log
							.info("#############################################################");
					return;

				}
			}

			int health = event.getPlayer().getHealth();
			int roll = new Random().nextInt(100) + 1;
			plugin.log.info("[PoisonFood] Player health:" + health);
			plugin.log.info("[PoisonFood] Roll: " + roll);
			// Cake!
			boolean cake = false;
			try {
				plugin.log.info("[PoisonFood] Is it cake?");
				if (event.getClickedBlock().getType() == Material.CAKE_BLOCK) {
					cake = true;
				} else {
					plugin.log
							.info("[PoisonFood] No its not :( Bloody cake haters");
				}
			} catch (Exception e) {
			}

			if (cake) {// Yeah i have this whole long way to do this :)

				event.setCancelled(true);
				plugin.log.info("[PoisonFood]Its cake!");
				if (roll <= Integer.parseInt(plugin.prop
						.getProperty("PoisonedCakeChance"))) {
					plugin.log.info("[PoisonFood]Lessen health");
					health -= Integer.parseInt(plugin.prop
							.getProperty("CakeDamage"));
					event.getPlayer().sendMessage(
							ChatColor.DARK_GREEN
									+ "[PoisonFood] That cake was poisoned!");

				}
				plugin.log.info("[PoisonFood] steal some cake!");
				Block block = event.getClickedBlock();
				block.setData((byte) (block.getData() + 1));
				if (block.getData() >= (byte) 7) {
					block.setType(Material.AIR);
				}
				//
				plugin.log.info("[PoisonFood] End of cake method");
			} else if (event.getItem() == null
					|| event.getItem().getType() == Material.AIR) {
				plugin.log.info("[PoisonFood] Its been returned(Null / air)");

				plugin.log
						.info("#############################################################");
				return;
			} else if (event.getItem().getTypeId() == 319) {// raw pork
				plugin.log.info("[PoisonFood] raw Pork: Eeeeuuuwwwwwww");
				if (roll <= Integer.parseInt(plugin.prop
						.getProperty("PoisonedRawPorkChance"))) {
					plugin.log.info("[PoisonFood] lessen health and stack");
					event.setCancelled(true);
					ItemStack stack = event.getPlayer().getItemInHand();
					int amount = stack.getAmount();
					amount -= 1;
					if (amount <= 0) {
						event.getPlayer().setItemInHand(null);
					} else {
						stack.setAmount(amount);
					}
					health -= Integer.parseInt(plugin.prop
							.getProperty("RawPorkDamage"));
					event.getPlayer()
							.sendMessage(
									ChatColor.DARK_GREEN
											+ "[PoisonFood] That raw piece of pork was poisoned!");

				}
				plugin.log.info("[PoisonFood] end of raw pork chop");
			} else if (event.getItem().getTypeId() == 320) {// cooked pork
				plugin.log.info("[PoisonFood]Cooked Pork: Yummy Bacon!");
				if (roll <= Integer.parseInt(plugin.prop
						.getProperty("PoisonedCookedPorkChance"))) {
					plugin.log.info("[PoisonFood] lessen health and stack");
					event.setCancelled(true);
					ItemStack stack = event.getPlayer().getItemInHand();
					int amount = stack.getAmount();
					amount -= 1;
					if (amount <= 0) {
						event.getPlayer().setItemInHand(null);
					} else {
						stack.setAmount(amount);
					}
					health -= Integer.parseInt(plugin.prop
							.getProperty("CookedPorkDamage"));
					event.getPlayer()
							.sendMessage(
									ChatColor.DARK_GREEN
											+ "[PoisonFood] That cooked piece of pork was poisoned!");

				}
				plugin.log.info("[PoisonFood] end of cooked pork chop");
			} else if (event.getItem().getTypeId() == 297) {// bread
				plugin.log.info("[PoisonFood]Bread: gotta love toast");
				if (roll <= Integer.parseInt(plugin.prop
						.getProperty("PoisonedBreadChance"))) {
					plugin.log.info("[PoisonFood] lessen health and stack");
					event.setCancelled(true);
					ItemStack stack = event.getPlayer().getItemInHand();
					int amount = stack.getAmount();
					amount -= 1;
					if (amount <= 0) {
						event.getPlayer().setItemInHand(null);
					} else {
						stack.setAmount(amount);
					}
					health -= Integer.parseInt(plugin.prop
							.getProperty("BreadDamage"));
					event.getPlayer()
							.sendMessage(
									ChatColor.DARK_GREEN
											+ "[PoisonFood] That piece of bread was poisoned!");
				}
				plugin.log.info("[PoisonFood] end of bread");
			} else if (event.getItem().getTypeId() == 349) {// raw fish
				plugin.log.info("[PoisonFood]raw Fish: Sushi?");

				if (roll <= Integer.parseInt(plugin.prop
						.getProperty("PoisonedRawFishChance"))) {
					plugin.log.info("[PoisonFood] lessen health and stack");
					event.setCancelled(true);
					ItemStack stack = event.getPlayer().getItemInHand();
					int amount = stack.getAmount();
					amount -= 1;
					if (amount <= 0) {
						event.getPlayer().setItemInHand(null);
					} else {
						stack.setAmount(amount);
					}
					health -= Integer.parseInt(plugin.prop
							.getProperty("RawFishDamage"));
					event.getPlayer()
							.sendMessage(
									ChatColor.DARK_GREEN
											+ "[PoisonFood] That raw piece of fish was poisoned!");
				}
				plugin.log.info("[PoisonFood] end of raw fish");
			} else if (event.getItem().getTypeId() == 350) {// cooked fish
				plugin.log
						.info("[PoisonFood]Cooked Fish: Fish and chips! FU notch for not adding chips, idiot!");
				if (roll <= Integer.parseInt(plugin.prop
						.getProperty("PoisonedCookedFishChance"))) {
					plugin.log.info("[PoisonFood] lessen health and stack");
					event.setCancelled(true);
					ItemStack stack = event.getPlayer().getItemInHand();
					int amount = stack.getAmount();
					amount -= 1;
					if (amount <= 0) {
						event.getPlayer().setItemInHand(null);
					} else {
						stack.setAmount(amount);
					}
					health -= Integer.parseInt(plugin.prop
							.getProperty("CookedFishDamage"));
					event.getPlayer()
							.sendMessage(
									ChatColor.DARK_GREEN
											+ "[PoisonFood] That cooked piece of fish was poisoned!");
				}
				plugin.log.info("[PoisonFood] end of cooked fish");
			} else if (event.getItem().getTypeId() == 282) {// soup
				plugin.log
						.info("[PoisonFood]Soup: All you ever need on rainy days :)");
				if (roll <= Integer.parseInt(plugin.prop
						.getProperty("PoisonedSoupChance"))) {
					plugin.log.info("[PoisonFood] lessen health and stack");
					event.setCancelled(true);
					ItemStack stack = event.getPlayer().getItemInHand();
					int amount = stack.getAmount();
					amount -= 1;
					if (amount <= 0) {
						event.getPlayer().setItemInHand(null);
					} else {
						stack.setAmount(amount);
					}
					health -= Integer.parseInt(plugin.prop
							.getProperty("SoupDamage"));
					event.getPlayer().sendMessage(
							ChatColor.DARK_GREEN
									+ "[PoisonFood] That soup was poisoned!");
				}
				plugin.log.info("[PoisonFood] end of soup");
			} else if (event.getItem().getTypeId() == 260) {// apple
				plugin.log
						.info("[PoisonFood]Apples: Great for apple cider.... if you could make it");
				if (roll <= Integer.parseInt(plugin.prop
						.getProperty("PoisonedAppleChance"))) {
					plugin.log.info("[PoisonFood] lessen health and stack");
					event.setCancelled(true);
					ItemStack stack = event.getPlayer().getItemInHand();
					int amount = stack.getAmount();
					amount -= 1;
					if (amount <= 0) {
						event.getPlayer().setItemInHand(null);
					} else {
						stack.setAmount(amount);
					}
					health -= Integer.parseInt(plugin.prop
							.getProperty("AppleDamage"));
					event.getPlayer().sendMessage(
							ChatColor.DARK_GREEN
									+ "[PoisonFood] That apple was poisoned!");
				}
				plugin.log.info("[PoisonFood] end of apple");
			} else if (event.getItem().getTypeId() == 322) {// golden apple
				plugin.log
						.info("[PoisonFood] Golden Apples: How do you bite these anyway?");
				if (roll <= Integer.parseInt(plugin.prop
						.getProperty("PoisonedGoldenAppleChance"))) {
					plugin.log.info("[PoisonFood] lessen health and stack");
					event.setCancelled(true);
					ItemStack stack = event.getPlayer().getItemInHand();
					int amount = stack.getAmount();
					amount -= 1;
					if (amount <= 0) {
						event.getPlayer().setItemInHand(null);
					} else {
						stack.setAmount(amount);
					}
					health -= Integer.parseInt(plugin.prop
							.getProperty("GoldenAppleDamage"));
					event.getPlayer()
							.sendMessage(
									ChatColor.DARK_GREEN
											+ "[PoisonFood] That Golden apple was poisoned!");
				}
				plugin.log.info("[PoisonFood] end of Golden apple");
			} else if (event.getItem().getTypeId() == 357) {// Cookies
				plugin.log
						.info("[PoisonFood]Cookies: HE HAS A COOKIE???? WANT!!");
				if (roll <= Integer.parseInt(plugin.prop
						.getProperty("PoisonedCookieChance"))) {
					plugin.log.info("[PoisonFood] lessen health and stack");
					event.setCancelled(true);
					ItemStack stack = event.getPlayer().getItemInHand();
					int amount = stack.getAmount();
					amount -= 1;
					if (amount <= 0) {
						event.getPlayer().setItemInHand(null);
					} else {
						stack.setAmount(amount);
					}
					health -= Integer.parseInt(plugin.prop
							.getProperty("CookieDamage"));
					event.getPlayer().sendMessage(
							ChatColor.DARK_GREEN
									+ "[PoisonFood] That cookie was poisoned!");
				}
				plugin.log.info("[PoisonFood] end of cookies... NO:(");
			}
			plugin.log.info("[PoisonFood] Do some help calc");
			if (health < 0) {
				health = 0;
			}
			event.getPlayer().setHealth(health);
			plugin.log.info("[PoisonFood]Finish setting health");
			plugin.log
					.info("##################################################################################");
		} catch (Exception e) {
			plugin.log.warning("[PoisonFood] Error #1: " + e.getMessage());
			plugin.mlog.warning("[PoisonFood] Error #1");
			plugin.log
					.info("#############################################################");
		}
	}
}
