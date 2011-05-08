package com.PrivateAlpha.bukkit.Factory.Furnace;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FFurnace {
	private int id;
	private Location location;
	private String owner;
	private String name;
	/**
	 * @param loc: the location to set
	 */
	public FFurnace(String owner ,Location loc)
	{
		this.location = loc;
		this.setOwner(owner);
	}
	/**
	 * @param loc: change the location of the block
	 */
	public void setLocation(Location loc) {
		this.location = loc;
	}
	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * @param owner: change the owner of the block
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
