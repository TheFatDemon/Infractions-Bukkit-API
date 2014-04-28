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

package com.censoredsoftware.infractions.bukkit.test;

import com.censoredsoftware.infractions.bukkit.Database;
import com.censoredsoftware.infractions.bukkit.Infraction;
import com.censoredsoftware.infractions.bukkit.dossier.CompleteDossier;
import com.censoredsoftware.infractions.bukkit.dossier.Dossier;
import com.censoredsoftware.infractions.bukkit.evidence.Evidence;
import com.censoredsoftware.library.helper.MojangIdProvider;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.net.InetAddress;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TestDatabase implements Database
{
	private final ConcurrentMap<UUID, Dossier> DOSSIER_MAP = new ConcurrentHashMap<UUID, Dossier>();

	@Override
	public CompleteDossier getCompleteDossier(UUID playerId) throws NullPointerException
	{
		Dossier dossier = DOSSIER_MAP.get(playerId);
		if(dossier instanceof CompleteDossier) return (CompleteDossier) dossier;
		throw new NullPointerException("Incomplete dossier.");
	}

	@Override
	public CompleteDossier getCompleteDossier(String playerName) throws NullPointerException
	{
		Dossier dossier = DOSSIER_MAP.get(MojangIdProvider.getId(playerName));
		if(dossier instanceof CompleteDossier) return (CompleteDossier) dossier;
		throw new NullPointerException("Incomplete dossier.");
	}

	@Override
	public CompleteDossier getCompleteDossier(Player player)
	{
		Dossier dossier = DOSSIER_MAP.get(player.getUniqueId());
		if(dossier instanceof CompleteDossier) return (CompleteDossier) dossier;
		throw new NullPointerException("Incomplete dossier.");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<CompleteDossier> getCompleteDossiers(final InetAddress address)
	{
		return (Set<CompleteDossier>) (Set) Sets.newHashSet(Collections2.filter(allDossiers(), new Predicate<Dossier>()
		{
			@Override
			public boolean apply(Dossier dossier)
			{
				return dossier instanceof CompleteDossier && ((CompleteDossier) dossier).getAssociatedIPAddresses().contains(address);
			}
		}));
	}

	@Override
	public Dossier getDossier(UUID playerId)
	{
		return DOSSIER_MAP.get(playerId);
	}

	@Override
	public Dossier getDossier(String playerName)
	{
		return DOSSIER_MAP.get(MojangIdProvider.getId(playerName));
	}

	@Override
	public void addDossier(Dossier dossier)
	{
		DOSSIER_MAP.put(dossier.getId(), dossier);
	}

	@Override
	public void removeDossier(Dossier dossier)
	{
		DOSSIER_MAP.remove(dossier.getId());
	}

	@Override
	public void removeDossier(UUID playerId)
	{
		DOSSIER_MAP.remove(playerId);
	}

	@Override
	public Set<Dossier> allDossiers()
	{
		return Sets.newHashSet(DOSSIER_MAP.values());
	}

	@Override
	public Set<Infraction> allInfractions()
	{
		Set<Infraction> infractions = Sets.newHashSet();
		for(Dossier dossier : allDossiers())
			infractions.addAll(dossier.getInfractions());
		return infractions;
	}

	@Override
	public Set<Evidence> allEvidence()
	{
		Set<Evidence> evidence = Sets.newHashSet();
		for(Infraction infraction : allInfractions())
			evidence.addAll(infraction.getEvidence());
		return evidence;
	}

	@Override
	public Plugin getPlugin()
	{
		return null;
	}
}
