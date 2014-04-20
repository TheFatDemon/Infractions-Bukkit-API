package com.censoredsoftware.infractions.bukkit.dossier;

import com.censoredsoftware.infractions.bukkit.Infraction;

import java.util.Set;
import java.util.UUID;

/**
 * Interface representing only the minimal data tracked by Infractions concerning a particular player.
 */
public interface Dossier
{
	/**
	 * Get the Mojang UUID associated with this player.
	 *
	 * @return The UUID.
	 */
	UUID getMojangId();

	/**
	 * Get the total score for this player.
	 *
	 * @return The total score.
	 */
	int getScore();

	/**
	 * Get a collection of Infractions that belong to this player.
	 *
	 * @return Collection of Infractions.
	 */
	Set<Infraction> getInfractions();

	/**
	 * Award an infraction to the player.
	 *
	 * @param infraction The infraction.
	 */
	void cite(Infraction infraction);

	/**
	 * Remove an infraction from the player.
	 *
	 * @param infraction The infraction.
	 */
	void acquit(Infraction infraction);
}
