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

import org.bukkit.OfflinePlayer;

import java.net.InetAddress;
import java.util.Set;

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

	/**
	 * Get a set of recorded IP addresses.
	 *
	 * @return The addresses.
	 */
	Set<InetAddress> getAssociatedIPAddresses();
}
