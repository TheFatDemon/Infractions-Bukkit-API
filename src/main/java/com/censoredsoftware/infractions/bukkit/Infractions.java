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
	private static Database database;

	public Database getDatabase()
	{
		return database;
	}

	public static void setDatabase(Database database)
	{
		if(Infractions.database != null)
		{
			throw new UnsupportedOperationException("Cannot redefine singleton Database");
		}

		Infractions.database = database;
	}

	public CompleteDossier getCompleteDossier(UUID playerId) throws NullPointerException
	{
		return database.getCompleteDossier(playerId);
	}

	public CompleteDossier getCompleteDossier(String playerName) throws NullPointerException
	{
		return database.getCompleteDossier(playerName);
	}

	public CompleteDossier getCompleteDossier(Player player)
	{
		return database.getCompleteDossier(player);
	}

	public Dossier getDossier(UUID playerId)
	{
		return database.getDossier(playerId);
	}

	public Dossier getDossier(String playerName)
	{
		return database.getDossier(playerName);
	}

	public Set<Dossier> allDossiers()
	{
		return database.allDossiers();
	}

	public Set<Infraction> allInfractions()
	{
		return database.allInfractions();
	}

	public Set<Evidence> allEvidence()
	{
		return database.allEvidence();
	}

	public Plugin getPlugin()
	{
		return database.getPlugin();
	}
}
