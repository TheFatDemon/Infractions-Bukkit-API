package com.censoredsoftware.infractions.bukkit.api.test;

import com.censoredsoftware.infractions.bukkit.api.Dossier;
import com.censoredsoftware.infractions.bukkit.api.Infraction;
import com.google.common.collect.Sets;

import java.util.Set;
import java.util.UUID;

public class TestDossier implements Dossier
{
	private UUID mojangid;
	private Set<Infraction> infractions;

	public TestDossier(UUID mojangId, Infraction... infractions)
	{
		this.mojangid = mojangId;
		this.infractions = Sets.newHashSet(infractions);
	}

	@Override
	public UUID getId()
	{
		return mojangid;
	}

	@Override
	public int getScore()
	{
		int score = 0;
		for(Infraction infraction : getInfractions())
			score += infraction.getScore();
		return score;
	}

	@Override
	public Set<Infraction> getInfractions()
	{
		return infractions;
	}
}
