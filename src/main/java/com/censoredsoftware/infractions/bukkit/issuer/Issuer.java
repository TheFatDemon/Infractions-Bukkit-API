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

package com.censoredsoftware.infractions.bukkit.issuer;

import com.censoredsoftware.infractions.bukkit.Infraction;
import com.censoredsoftware.infractions.bukkit.Infractions;
import com.censoredsoftware.infractions.bukkit.dossier.Dossier;
import com.censoredsoftware.infractions.bukkit.evidence.Evidence;
import com.censoredsoftware.infractions.bukkit.origin.Origin;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Abstract class representing an Issuer of an Infraction/Evidence.
 */
public class Issuer
{
	// Private Data Fields
	private IssuerType type;
	private String id;
	private Origin origin;

	/**
	 * Create a new Issuer object.
	 *
	 * @param type The type of issuer.
	 * @param id   The ID of this issuer.
	 */
	public Issuer(IssuerType type, String id)
	{
		this(type, id, Infractions.getDefaultOrigin());
	}

	/**
	 * Create a new Issuer object.
	 *
	 * @param type   The type of issuer.
	 * @param id     The ID of this issuer.
	 * @param origin The Origin of this issuer.
	 */
	public Issuer(IssuerType type, String id, Origin origin)
	{
		this.type = type;
		this.id = id;
		this.origin = origin;
	}

	/**
	 * Get the IssuerType.
	 *
	 * @return The type.
	 */
	public IssuerType getType()
	{
		return this.type;
	}

	/**
	 * Get the ID of this Issuer.
	 *
	 * @return The ID.
	 */
	public String getId()
	{
		return this.id;
	}

	/**
	 * Return the origin of this Issuer.
	 *
	 * @return The origin.
	 */
	public Origin getOrigin()
	{
		return this.origin;
	}

	/**
	 * Set of all issued/created Infractions.
	 *
	 * @return Infractions.
	 */
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

	/**
	 * Set of Issued Evidence.
	 *
	 * @return Evidence.
	 */
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

	/**
	 * Set of Infractions contributed to.
	 *
	 * @return Infractions.
	 */
	public Set<Infraction> getContributedInfractions()
	{
		return Sets.filter(Infractions.allInfractions(), new Predicate<Infraction>()
		{
			@Override
			public boolean apply(Infraction infraction)
			{
				return Iterables.any(infraction.getEvidence(), new Predicate<Evidence>()
				{
					@Override
					public boolean apply(Evidence evidence)
					{
						return getId().equals(evidence.getIssuer().getId());
					}
				});
			}
		});
	}

	/**
	 * Set of Dossiers contributed to.
	 *
	 * @return Dossiers.
	 */
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
