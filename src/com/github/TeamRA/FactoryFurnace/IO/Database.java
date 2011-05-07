package com.github.TeamRA.FactoryFurnace.IO;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.World;

import com.github.TeamRA.FactoryFurnace.FFurnace;
import com.github.TeamRA.FactoryFurnace.FactoryFurnace;

public class Database {

	private Connection connection = null;
	private Map<String, PreparedStatement> statementCache = new HashMap<String, PreparedStatement>();

	public PreparedStatement prepare(String sql) {
		if (connection == null) {
			try {
				connect();
			} catch (Exception e) {
				System.out.println("[FactoryFurnace]Encountered an error: ");
				e.printStackTrace();
				FactoryFurnace.plugin.getPluginLoader().disablePlugin(
						FactoryFurnace.plugin);
			}
		}

		if (statementCache.containsKey(sql)) {
			return statementCache.get(sql);
		}

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			statementCache.put(sql, preparedStatement);

			return preparedStatement;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// connects to database
	public synchronized boolean connect() {
		if(FactoryFurnace.dev)
		{
			System.out.println("connecting to database");
		}
		File file = new File(getDatabasePath());
		if (!file.exists()) {
			try{
				File f2 = new File("plugins/FactoryFurnace");
				if(!f2.exists())
				{
					f2.mkdir();
				}
				file.createNewFile();
			} catch (IOException e) {

				System.out.println("[FactoryFurnace]Encountered an error: ");
				e.printStackTrace();
				FactoryFurnace.plugin.getPluginLoader().disablePlugin(
						FactoryFurnace.plugin);
			}
		}
		if (connection != null) {
			return true;
		}

		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:"
					+ getDatabasePath());
		} catch (Exception e) {
			System.out.println("[FactoryFurnace]Encountered an error: ");
			e.printStackTrace();
			FactoryFurnace.plugin.getPluginLoader().disablePlugin(
					FactoryFurnace.plugin);
		}

		return true;
	}

	public String getDatabasePath() {

		return "plugins/FactoryFurnace/furnace.db";
	}

	public synchronized void load() {
		/**
		 * Updates that alter or rename a table go here
		 */
		if(connection == null)
		{
			connect();
		}
		try {
			final Statement statement = connection.createStatement();
			connection.setAutoCommit(false);

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS furnaces (" //
					+ "id INTEGER PRIMARY KEY," //
					+ "Name TEXT," //
					+ "Owner TEXT," //
					+ "World TEXT," //
					+ "Xcoord INTEGER," //
					+ "Ycoord INTEGER," //
					+ "Zcoord INTEGER" //
					+ ");");

			connection.commit();
			connection.setAutoCommit(true);

			statement.close();

		} catch (final SQLException e) {
			System.out.println("[FactoryFurnace]Encountered an error: ");
			e.printStackTrace();
			FactoryFurnace.plugin.getPluginLoader().disablePlugin(
					FactoryFurnace.plugin);
		}

	}

	public ArrayList<FFurnace> getFurnaces() {
		if(FactoryFurnace.dev)
		{
			System.out.println("gettin furns");
		}
		ArrayList<FFurnace> array = new ArrayList<FFurnace>();
		try {
			Statement statement = connection.createStatement();

			ResultSet set = statement.executeQuery("SELECT * FROM furnaces");

			while (set.next()) {
				int id = set.getInt("id");
				String name = set.getString("Name");
				String owner = set.getString("Owner");
				String world = set.getString("World");
				int x = set.getInt("Xcoord");
				int y = set.getInt("Ycoord");
				int z = set.getInt("Zcoord");
				FactoryFurnace plugin = FactoryFurnace.plugin;
				World wow = null;
				try {
					wow = plugin.getServer().getWorld(world);
				} catch (Exception e) {
					// TODO If it fails delete add furnaces on that world
					e.printStackTrace();
				}
				Location loc = new Location(wow, x, y, z);

				FFurnace furn = new FFurnace(owner, loc);
				furn.setId(id);
				furn.setName(name);
				array.add(furn);
			}

			set.close();
			statement.close();
			return array;
		} catch (SQLException e) {
			System.out.println("[FactoryFurnace]Encountered an error: ");
			e.printStackTrace();
			FactoryFurnace.plugin.getPluginLoader().disablePlugin(
					FactoryFurnace.plugin);
		}
		return null;
	}

	public synchronized void addFurnace(String owner, Location loc) {

		try {
			PreparedStatement statement = prepare("INSERT INTO furnaces (Name,Owner,World,Xcoord,Ycoord,Zcoord) VALUES(?, ?, ?, ?, ?, ?)");

			statement.setString(1, "");
			statement.setString(2, owner);
			statement.setString(3, loc.getWorld().getName());
			statement.setInt(4, loc.getBlockX());
			statement.setInt(5, loc.getBlockY());
			statement.setInt(6, loc.getBlockZ());

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println("[FactoryFurnace]Encountered an error: ");
			e.printStackTrace();
			FactoryFurnace.plugin.getPluginLoader().disablePlugin(
					FactoryFurnace.plugin);
		}
	}

	public synchronized void removeFurnace(int id) {

		try {
			PreparedStatement statement = prepare("DELETE FROM furnaces WHERE id=?");
			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[FactoryFurnace]Encountered an error: ");
			e.printStackTrace();
			FactoryFurnace.plugin.getPluginLoader().disablePlugin(
					FactoryFurnace.plugin);
		}
	}

	public FFurnace getFurnaceAt(Location loc) {
		try {
			Statement statement = connection.createStatement();

			ResultSet set = statement
					.executeQuery("SELECT id,name,owner FROM furnaces WHERE "//
							+ "World='" + loc.getWorld().getName() + "',"
							+ "Xcoord='" + loc.getBlockX() + "',"//
							+ "Ycoord='" + loc.getBlockY() + "',"//
							+ "Zcoord='" + loc.getBlockZ() + "'" + "");

			while (set.next()) {
				int id = set.getInt("id");
				String name = set.getString("Name");
				String owner = set.getString("Owner");

				FFurnace furn = new FFurnace(owner, loc);
				furn.setId(id);
				furn.setName(name);
				return furn;
			}

			set.close();
			statement.close();
			
		} catch (SQLException e) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
		}
		//return new FFurnace(null, null);
		return null;
		

	}
}
