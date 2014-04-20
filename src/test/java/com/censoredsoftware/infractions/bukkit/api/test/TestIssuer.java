package com.censoredsoftware.infractions.bukkit.api.test;

import com.censoredsoftware.infractions.bukkit.Infraction;
import com.censoredsoftware.infractions.bukkit.Infractions;
import com.censoredsoftware.infractions.bukkit.dossier.Dossier;
import com.censoredsoftware.infractions.bukkit.evidence.Evidence;
import com.censoredsoftware.infractions.bukkit.issuer.Issuer;
import com.censoredsoftware.infractions.bukkit.issuer.IssuerType;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.util.Set;

public class TestIssuer extends Issuer
{
	public TestIssuer(IssuerType type, String id)
	{
		super(type, id);
	}

	@Override
	public Set<Infraction> getIssuedInfractions()
	{
		return Sets.filter(Infractions.allInfractions(), new Predicate<Infraction>()
		{
			@Override public boolean apply(Infraction infraction)
			{
				return getId().equals(infraction.getIssuer().getId());
			}
		});
	}

	@Override
	public Set<Evidence> getIssuedEvidence()
	{
		return Sets.filter(Infractions.allEvidence(), new Predicate<Evidence>()
		{
			@Override public boolean apply(Evidence evidence)
			{
				return getId().equals(evidence.getIssuer().getId());
			}
		});
	}

	@Override
	public Set<Dossier> getContributedDossiers()
	{
		return Sets.filter(Infractions.allDossiers(), new Predicate<Dossier>()
		{
			@Override public boolean apply(Dossier dossier)
			{
				return Iterables.any(dossier.getInfractions(), new Predicate<Infraction>()
				{
					@Override public boolean apply(Infraction infraction)
					{
						return getId().equals(infraction.getIssuer().getId());
					}
				});
			}
		});
	}
}
