/*
 * Copyright (c) 2014 Alexander Chauncey
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.censoredsoftware.infractions.bukkit;

import com.censoredsoftware.infractions.bukkit.dossier.CompleteDossier;
import com.censoredsoftware.infractions.bukkit.dossier.Dossier;
import com.censoredsoftware.infractions.bukkit.evidence.Evidence;
import com.censoredsoftware.infractions.bukkit.origin.Origin;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.net.InetAddress;
import java.util.Set;
import java.util.UUID;

/**
 * Represents the Infractions plugin.
 */
public final class Infractions
{
	// The Singleton Database
	private static Database database;

	// Default Origin
	private static Origin defaultOrigin;

	/**
	 * Get the singleton Database.
	 *
	 * @return The database.
	 */
	public static Database getDatabase()
	{
		return database;
	}

	/**
	 * Set the singleton Database.
	 *
	 * @param database The database.
	 */
	public static void setDatabase(Database database)
	{
		Infractions.database = database;
	}

	/**
	 * Get the singleton default Origin.
	 *
	 * @return The default origin.
	 */
	public static Origin getDefaultOrigin()
	{
		return defaultOrigin;
	}

	/**
	 * Set the singleton default origin.
	 *
	 * @param defaultOrigin The default origin.
	 */
	public static void setDefaultOrigin(Origin defaultOrigin)
	{
		if(Infractions.defaultOrigin != null)
		{
			throw new UnsupportedOperationException("Cannot redefine singleton Origin");
		}

		Infractions.defaultOrigin = defaultOrigin;
	}

	/**
	 * Get the CompleteDossier from the Mojang UUID of a player.
	 *
	 * @param playerId The Mojang UUID.
	 * @return The CompleteDossier.
	 * @throws NullPointerException when no CompleteDossier exists.
	 */
	public static CompleteDossier getCompleteDossier(UUID playerId) throws NullPointerException
	{
		return getDatabase().getCompleteDossier(playerId);
	}

	/**
	 * Get the CompleteDossier from the last known of a player.
	 * The implementation may attempt to create a new Dossier if none exists.
	 *
	 * @param playerName The last known name of the player.
	 * @return The CompleteDossier.
	 * @throws NullPointerException if the CompleteDossier already exists but the UUID does not match.
	 */
	public static CompleteDossier getCompleteDossier(String playerName) throws NullPointerException
	{
		return getDatabase().getCompleteDossier(playerName);
	}

	/**
	 * Get the CompleteDossier from the Player.
	 *
	 * @param player The Bukkit Player object.
	 * @return The CompleteDossier.
	 */
	public static CompleteDossier getCompleteDossier(Player player)
	{
		return getDatabase().getCompleteDossier(player);
	}

	/**
	 * Get a set of CompleteDossiers sharing an IP address.
	 *
	 * @param address The Address.
	 * @return The set of  CompleteDossiers.
	 * @deprecated This will cause a lot of lag when used in conjunction with other checks. DO NOT USE IN THE MAIN THREAD.
	 */
	@Deprecated()
	public static Set<CompleteDossier> getCompleteDossiers(InetAddress address)
	{
		return getDatabase().getCompleteDossiers(address);
	}

	/**
	 * Get the Dossier from the Mojang UUID of a player.
	 *
	 * @param playerId The Mojang UUID.
	 * @return The Dossier.
	 */
	public static Dossier getDossier(UUID playerId)
	{
		return getDatabase().getDossier(playerId);
	}

	/**
	 * Get the Dossier from the name of a player.
	 * The implementation may attempt to create a new Dossier if none exists.
	 *
	 * @param playerName The last known name of the player.
	 * @return The Dossier.
	 * @deprecated The data returned from this method <b>WILL BE WRONG</b> if a player changes their name.
	 */
	@Deprecated()
	public static Dossier getDossier(String playerName)
	{
		return getDatabase().getDossier(playerName);
	}

	/**
	 * Add a dossier to the database.
	 *
	 * @param dossier The dossier.
	 */
	public static void addDossier(Dossier dossier)
	{
		getDatabase().addDossier(dossier);
	}

	/**
	 * Remove a dossier from the database.
	 *
	 * @param dossier The dossier.
	 */
	public static void removeDossier(Dossier dossier)
	{
		getDatabase().removeDossier(dossier);
	}

	/**
	 * Remove a dossier from the database.
	 *
	 * @param playerId The player Mojang id.
	 */
	public static void removeDossier(UUID playerId)
	{
		getDatabase().removeDossier(playerId);
	}

	/**
	 * The Set of all Dossiers.
	 *
	 * @return All Dossiers.
	 */
	public static Set<Dossier> allDossiers()
	{
		return getDatabase().allDossiers();
	}

	/**
	 * The Set of all Infractions.
	 *
	 * @return All Infractions.
	 */
	public static Set<Infraction> allInfractions()
	{
		return getDatabase().allInfractions();
	}

	/**
	 * The Set of all Evidence.
	 *
	 * @return All Evidence.
	 */
	public static Set<Evidence> allEvidence()
	{
		return getDatabase().allEvidence();
	}

	/**
	 * Return the plugin implementing the API.
	 *
	 * @return The plugin.
	 */
	public static Plugin getPlugin()
	{
		return getDatabase().getPlugin();
	}
}
