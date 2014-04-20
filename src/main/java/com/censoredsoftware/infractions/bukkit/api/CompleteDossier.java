package com.censoredsoftware.infractions.bukkit.api;

import org.bukkit.OfflinePlayer;

/**
 * Interface representing all data tracked by Infractions concerning a particular player.
 */
public interface CompleteDossier extends Dossier
{
	/**
	 * Get the OfflinePlayer object for this player.
	 *
	 * @return The player object.
	 * @throws IllegalStateException when this is called in the main thread.
	 */
	OfflinePlayer getOfflinePlayer() throws IllegalStateException;

	/**
	 * Get the last recorded name of this player.
	 *
	 * @return The name.
	 */
	String getLastKnownName();
}
