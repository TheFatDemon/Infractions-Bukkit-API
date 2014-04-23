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
	UUID getId();

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

	/**
	 * If this Dossier isn't complete, complete it.
	 *
	 * @param playerName The name of the player to complete the data.
	 * @return Replacement CompleteDossier of the data.
	 */
	CompleteDossier complete(String playerName);

	/**
	 * Return the CompelteDossier instance of this object if it is complete.
	 *
	 * @return CompleteDossier of the data.
	 * @throws ClassCastException if data is incomplete.
	 */
	CompleteDossier complete() throws ClassCastException;
}
