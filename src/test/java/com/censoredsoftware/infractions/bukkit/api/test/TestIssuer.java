package com.censoredsoftware.infractions.bukkit.api.test;

import com.censoredsoftware.infractions.bukkit.api.*;
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
		return Sets.filter(TestInfractions.TEST_DATABASE.allInfractions(), new Predicate<Infraction>()
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
		return Sets.filter(TestInfractions.TEST_DATABASE.allEvidence(), new Predicate<Evidence>()
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
		return Sets.filter(TestInfractions.TEST_DATABASE.allDossiers(), new Predicate<Dossier>()
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
