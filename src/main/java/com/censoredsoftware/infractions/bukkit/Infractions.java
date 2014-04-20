package com.censoredsoftware.infractions.bukkit;

import com.censoredsoftware.infractions.bukkit.dossier.CompleteDossier;
import com.censoredsoftware.infractions.bukkit.dossier.Dossier;
import com.censoredsoftware.infractions.bukkit.evidence.Evidence;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Set;
import java.util.UUID;

/**
 * Represents the Infractions plugin.
 */
public final class Infractions
{
	// The Singleton Database
	private static Database database;

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
		if(Infractions.database != null)
		{
			throw new UnsupportedOperationException("Cannot redefine singleton Database");
		}

		Infractions.database = database;
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
	 * Get the Dossier from the Mojang UUID of a player.
	 * The implementation may attempt to create a new Dossier if none exists.
	 *
	 * @param playerName The last known name of the player.
	 * @return The Dossier.
	 * @deprecated The data returned from this method WILL BE WRONG if a player changes their name.
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
