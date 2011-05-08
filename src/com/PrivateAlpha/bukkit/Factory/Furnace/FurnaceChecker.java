package com.PrivateAlpha.bukkit.Factory.Furnace;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.ContainerBlock;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FurnaceChecker implements Runnable {

	public void run() {
		if (FactoryFurnace.dev) {
			System.out.println("!!!!!running furnace checks!!!!!");
		}

		/*
		 * TODO request list of current blocks check if block is furnace check
		 * if input 1| intput 2 is empty if empty fill check if input 1| intput
		 * 2 is empty if empty fill check if output has if has check if there is
		 * space in the chest if space move to chest else message player
		 */
		ArrayList<FFurnace> furns = null;
		try {
			furns = WatchHandler.getFurnaces();
		} catch (Exception e) {
			if (FactoryFurnace.dev) {
				System.out.println("failed to get list of furns");
			}
			return;

		}
		if (furns == null) {
			if (FactoryFurnace.dev) {
				System.out.println("furns == null");
			}
			return;
		}
		for (FFurnace ele : furns) {
			if (FactoryFurnace.dev) {
				System.out.println("check furn");
			}
			Block block = ele.getLocation().getWorld()
					.getBlockAt(ele.getLocation());
			Block[] neighbours = new Block[8];
			Block temp;

			temp = block.getRelative(1, 0, 0);
			if (temp.getType() == Material.CHEST) {
				neighbours[0] = temp;
				temp = DoubleChest(temp);
				if (temp != null) {
					neighbours[1] = temp;
				}
			}

			temp = block.getRelative(-1, 0, 0);
			if (temp.getType() == Material.CHEST) {
				neighbours[2] = temp;
				temp = DoubleChest(temp);
				if (temp != null) {
					neighbours[3] = temp;
				}
			}

			temp = block.getRelative(0, 0, 1);
			if (temp.getType() == Material.CHEST) {
				neighbours[4] = temp;
				temp = DoubleChest(temp);
				if (temp != null) {
					neighbours[5] = temp;
				}
			}

			temp = block.getRelative(0, 0, -1);
			if (temp.getType() == Material.CHEST) {
				neighbours[6] = temp;
				temp = DoubleChest(temp);
				if (temp != null) {
					neighbours[7] = temp;
				}
			}

			Furnace furnace = (Furnace) block.getState();

			Inventory inventory = furnace.getInventory();

			ItemStack[] stack = inventory.getContents();

			if (stack[0].getAmount() == 0 || stack[0].getType() == Material.AIR) {
				Block b;
				search: for (int j = 0; j < 8; j++) {
					b = neighbours[j];
					if (b != null
							&& neighbours[j & ~1] != null
							&& neighbours[j & ~1].getRelative(0, -1, 0)
									.getType() == Material.LAPIS_BLOCK) {
						Inventory i = null;
						try {
							try {
								i = ((Chest) b.getState()).getInventory();
							} catch (Exception e) {/* TODO what to do! */
								e.printStackTrace();
							}
						} catch (Exception e) {/* TODO what to do! */
							e.printStackTrace();
						}
						if (i == null) {
							continue;
						}

						ItemStack[] contents = i.getContents();

						for (ItemStack s : contents) {
							if (s.getAmount() > 0) {
								inventory.setItem(0, s);
								i.clear(i.first(s));
								break search;
							}
						}
					}
				}
			}

			if (stack[1].getAmount() == 0 || stack[1].getType() == Material.AIR) {
				Block b;
				search: for (int j = 0; j < 8; j++) {
					b = neighbours[j];
					if (b != null
							&& neighbours[j & ~1] != null
							&& neighbours[j & ~1].getRelative(0, -1, 0)
									.getType() == Material.OBSIDIAN) {
						Inventory i = null;
						try {
							i = ((Chest) b.getState()).getInventory();
						} catch (Exception e) {/* TODO what to do! */
							e.printStackTrace();
						}
						if (i == null) {
							continue;
						}

						ItemStack[] contents = i.getContents();

						for (ItemStack s : contents) {
							if (s.getAmount() > 0) {
								inventory.setItem(1, s);
								i.clear(i.first(s));
								break search;
							}
						}
					}
				}
			}

			if (stack[2].getAmount() != 0) {
				Block b;
				for (int j = 0; j < 8; j++) {
					b = neighbours[j];
					if (b != null
							&& neighbours[j & ~1] != null
							&& neighbours[j & ~1].getRelative(0, -1, 0)
									.getType() == Material.GOLD_BLOCK) {
						HashMap<Integer, ItemStack> h = ((Chest) b.getState())
								.getInventory().addItem(stack[2]);
						if (!h.isEmpty()) {
							stack[2] = h.get(0);
						} else {
							stack[2].setAmount(0);
							break;
						}
					}
				}
				if (stack[2] != null && stack[2].getAmount() > 0) {
					inventory.setItem(2, stack[2]);
				} else {
					inventory.clear(2);
				}
			}
		}
	}

	Block DoubleChest(Block block) {
		Block temp;

		temp = block.getRelative(1, 0, 0);
		if (temp.getType() == Material.CHEST) {
			return temp;
		}

		temp = block.getRelative(-1, 0, 0);
		if (temp.getType() == Material.CHEST) {
			return temp;
		}

		temp = block.getRelative(0, 0, 1);
		if (temp.getType() == Material.CHEST) {
			return temp;
		}

		temp = block.getRelative(0, 0, -1);
		if (temp.getType() == Material.CHEST) {
			return temp;
		}
		return temp;

	}
}
