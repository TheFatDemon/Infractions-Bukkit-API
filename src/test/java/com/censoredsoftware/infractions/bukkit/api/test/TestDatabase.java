package com.censoredsoftware.infractions.bukkit.api.test;

import com.censoredsoftware.infractions.bukkit.api.*;
import com.censoredsoftware.library.helper.MojangIdProvider;
import com.google.common.collect.Sets;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TestDatabase implements Database
{
	public final ConcurrentMap<UUID, Dossier> DOSSIER_MAP = new ConcurrentHashMap<UUID, Dossier>();

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
}
