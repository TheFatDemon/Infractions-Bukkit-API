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
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.net.InetAddress;
import java.util.Set;
import java.util.UUID;

/**
 * Interface representing the database holding the dossiers, infractions, and evidence.
 */
public interface Database
{
	/**
	 * Get the CompleteDossier from the Mojang UUID of a player.
	 *
	 * @param playerId The Mojang UUID.
	 * @return The CompleteDossier.
	 * @throws NullPointerException when no CompleteDossier exists.
	 */
	CompleteDossier getCompleteDossier(UUID playerId) throws NullPointerException;

	/**
	 * Get the CompleteDossier from the last known of a player.
	 * The implementation may attempt to create a new Dossier if none exists.
	 *
	 * @param playerName The last known name of the player.
	 * @return The CompleteDossier.
	 * @throws NullPointerException if the CompleteDossier already exists but the UUID does not match.
	 */
	CompleteDossier getCompleteDossier(String playerName) throws NullPointerException;

	/**
	 * Get the CompleteDossier from the Player.
	 *
	 * @param player The Bukkit Player object.
	 * @return The CompleteDossier.
	 */
	CompleteDossier getCompleteDossier(Player player);

	/**
	 * Get a set of CompleteDossiers sharing an IP address.
	 *
	 * @param address The Address.
	 * @return The set of  CompleteDossiers.
	 * @deprecated This will cause a lot of lag when used in conjunction with other checks. DO NOT USE IN THE MAIN THREAD.
	 */
	@Deprecated() Set<CompleteDossier> getCompleteDossiers(InetAddress address);

	/**
	 * Get the Dossier from the Mojang UUID of a player.
	 *
	 * @param playerId The Mojang UUID.
	 * @return The Dossier.
	 */
	Dossier getDossier(UUID playerId);

	/**
	 * Get the Dossier from the name of a player.
	 * The implementation may attempt to create a new Dossier if none exists.
	 *
	 * @param playerName The last known name of the player.
	 * @return The Dossier.
	 * @deprecated The data returned from this method <b>WILL BE WRONG</b> if a player changes their name.
	 */
	@Deprecated() Dossier getDossier(String playerName);

	/**
	 * Add a dossier to the database.
	 *
	 * @param dossier The dossier.
	 */
	void addDossier(Dossier dossier);

	/**
	 * Remove a dossier from the database.
	 *
	 * @param dossier The dossier.
	 */
	void removeDossier(Dossier dossier);

	/**
	 * Remove a dossier from the database.
	 *
	 * @param playerId The player Mojang id.
	 */
	void removeDossier(UUID playerId);

	/**
	 * The Set of all Dossiers.
	 *
	 * @return All Dossiers.
	 */
	Set<Dossier> allDossiers();

	/**
	 * The Set of all Infractions.
	 *
	 * @return All Infractions.
	 */
	Set<Infraction> allInfractions();

	/**
	 * The Set of all Evidence.
	 *
	 * @return All Evidence.
	 */
	Set<Evidence> allEvidence();

	/**
	 * Return the plugin implementing the API.
	 *
	 * @return The plugin.
	 */
	Plugin getPlugin();
}
