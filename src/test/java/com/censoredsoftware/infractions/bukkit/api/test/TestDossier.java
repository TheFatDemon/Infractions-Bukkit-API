package com.censoredsoftware.infractions.bukkit.api.test;

import com.censoredsoftware.infractions.bukkit.Infraction;
import com.censoredsoftware.infractions.bukkit.dossier.Dossier;
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
	public UUID getMojangId()
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

	@Override
	public void cite(Infraction infraction)
	{
		infractions.add(infraction);
	}

	@Override
	public void acquit(Infraction infraction)
	{
		infractions.remove(infraction);
	}
}
