package com.github.TeamRA.FactoryFurnace;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class FactoryFurnace extends JavaPlugin {
	public static boolean dev = false;
	public static FactoryFurnace plugin;
	public static final Logger log = Logger.getLogger("Minecraft");
	PluginDescriptionFile pdfFile = this.getDescription();

	private FurnaceBlockListener bl = new FurnaceBlockListener(this);

	private long timeToWait = 200;
	private int taskID;
	private boolean pm = false;
	private PermissionHandler sec;
	

	@Override
	public void onEnable() {
		plugin = this;
		WatchHandler.load();
		PluginManager pluginManager = getServer().getPluginManager();

		pluginManager.registerEvent(Event.Type.BLOCK_BREAK, bl,
				Priority.Normal, this);
		pluginManager.registerEvent(Event.Type.BLOCK_DAMAGE, bl,
				Priority.Normal, this);
		 setupPermissions();
		taskID = getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new FurnaceChecker(), 200, timeToWait);

		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println(pdfFile.getName() + " version "
				+ pdfFile.getVersion() + " is enabled.");

	}
	 private void setupPermissions() {
	      Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

	      if (this.sec == null) {
	          if (permissionsPlugin != null) {
	              this.sec = ((Permissions) permissionsPlugin).getHandler();
	              pm = true;
	          } else {
	              logw("Permission system not detected, Everyone has free access");
	          }
	      }
	  }
	@Override
	public void onDisable() {
		getServer().getScheduler().cancelTask(taskID);

		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println(pdfFile.getName() + " version "
				+ pdfFile.getVersion() + " disabled.");
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		String cmd = command.getName();

		if (!(sender instanceof Player)) {
			sender.sendMessage("These commands can only be used in-game!");
			return true;
		}
		Player player = (Player) sender;
		if (cmd.equals("FactoryFurnace")) {
			if (pm) {
				if (!sec.has(player,
						"FactoryFurnace.help")) {
					sender.sendMessage("You do not have permission to use this command!");
					return true;
				}

				// TODO list command help

			}
			if (args.length >= 1) {
				if (args[0].equals("add")) {
					if (pm) {
						if (!sec.has(player,
								"FactoryFurnace.add")) {
							sender.sendMessage("You do not have permission to use this command!");
							return true;
						}
						bl.addPlayer(player.getName(), "add");
						sender.sendMessage("[FactoryFurnace]Left click the furnace!");
						return true;
					}
					/*
					 * TODO check if player is allowed to make more furnaces
					 * TODO tell listener to watch for player click
					 */

				} else if (args[0].equals("remove")) {
					if (pm) {
						if (!sec.has(player,
								"FactoryFurnace.remove")) {
							sender.sendMessage("You do not have permission to use this command!");
							return true;
						}
						bl.addPlayer(player.getName(), "remove");
						sender.sendMessage("[FactoryFurnace]Left click the furnace!");
						return true;

					}
					/*
					 * MOVE: FurnaceBlockListener MAYBE check player rank is >
					 * than other player TODO tell listener to watch for player
					 * click
					 */

				} else if (args[0].equals("info")) {
					if (pm) {
						if (!sec.has(player,
								"FactoryFurnace.info")) {
							sender.sendMessage("You do not have permission to use this command!");
							return true;
						}
						bl.addPlayer(player.getName(), "info");
						sender.sendMessage("[FactoryFurnace]Left click the furnace!");
						return true;
					}

				} else if (args[0].equals("name")) {
					if (pm) {
						if (!sec.has(player,
								"FactoryFurnace.info")) {
							sender.sendMessage("You do not have permission to use this command!");
							return true;
						}
						bl.addPlayer(player.getName(), "rename");
						sender.sendMessage("[FactoryFurnace]Left click the furnace!");
						return true;
					}
					return false;
				}
			}

		} else if (cmd.equals("ffa")) {
			if (pm) {
				if (!sec
						.has(player, "FactoryFurnace.add")) {
					sender.sendMessage("You do not have permission to use this command!");
					return true;
				}
				bl.addPlayer(player.getName(), "add");
				sender.sendMessage("[FactoryFurnace]Left click the furnace!");
				return true;
			}
		} else if (cmd.equals("ffr")) {
			if (pm) {
				if (!sec.has(player,
						"FactoryFurnace.remove")) {
					sender.sendMessage("You do not have permission to use this command!");
					return true;
				}
				bl.addPlayer(player.getName(), "remove");
				sender.sendMessage("[FactoryFurnace]Left click the furnace!");
				return true;
			}

		} else if (cmd.equals("ffi")) {
			if (pm) {
				if (!sec.has(player,
						"FactoryFurnace.info")) {
					sender.sendMessage("You do not have permission to use this command!");
					return true;
				}
				bl.addPlayer(player.getName(), "info");
				sender.sendMessage("[FactoryFurnace]Left click the furnace!");
				return true;
			} else if (cmd.equals("ffn")) {
				if (pm) {
					if (!sec.has(player,
							"FactoryFurnace.info")) {
						sender.sendMessage("You do not have permission to use this command!");
						return true;
					}
					bl.addPlayer(player.getName(), "rename");
					sender.sendMessage("[FactoryFurnace]Left click the furnace!");
					return true;
				}
			}
		}
		return false;
	}

	public void logi(String str) {
		log.info("[" + pdfFile.getName() + "] " + str);
	}

	public void logw(String str) {
		log.warning("[" + pdfFile.getName() + "] " + str);
	}

	public void logs(String str) {
		log.severe("[" + pdfFile.getName() + "] " + str);
	}
}
