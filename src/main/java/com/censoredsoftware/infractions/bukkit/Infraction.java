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

import com.censoredsoftware.infractions.bukkit.evidence.Evidence;
import com.censoredsoftware.infractions.bukkit.issuer.Issuer;
import com.censoredsoftware.infractions.bukkit.issuer.IssuerType;
import com.censoredsoftware.infractions.bukkit.origin.Origin;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;

import java.util.*;

/**
 * Object class representing an Infraction.
 */
public class Infraction
{
	// Private Data Fields
	private UUID playerId;
	private Issuer issuer;
	private Long timeCreated;
	private String reason;
	private Integer score;
	private Set<Evidence> evidence;
	private List<String> notes;

	/**
	 * Create a new Infraction object.
	 *
	 * @param playerId    The Mojang UUID of the player.
	 * @param timeCreated The time the Infraction was first created.
	 * @param reason      The reason for this Infraction.
	 * @param score       The score of this Infraction.
	 * @param issuer      The issuer of the Infraction.
	 * @param evidence    The evidence associated with this Infraction.
	 */
	public Infraction(UUID playerId, Long timeCreated, String reason, Integer score, Issuer issuer, Evidence... evidence)
	{
		this(playerId, timeCreated, reason, score, issuer, Sets.newHashSet(evidence));
	}

	/**
	 * Create a new Infraction object.
	 *
	 * @param playerId    The Mojang UUID of the player.
	 * @param timeCreated The time the Infraction was first created.
	 * @param reason      The reason for this Infraction.
	 * @param score       The score of this Infraction.
	 * @param issuer      The issuer of the Infraction.
	 * @param evidence    The evidence associated with this Infraction.
	 */
	public Infraction(UUID playerId, Long timeCreated, String reason, Integer score, Issuer issuer, Set<Evidence> evidence)
	{
		this.playerId = playerId;
		this.issuer = issuer;
		this.timeCreated = timeCreated;
		this.reason = reason;
		this.score = score;
		this.evidence = evidence;
		this.notes = new ArrayList<String>();
	}

	/**
	 * Get the Mojang UUID relating to the player.
	 *
	 * @return Mojang UUID.
	 */
	public UUID getPlayerId()
	{
		return playerId;
	}

	/**
	 * The Issuer of this Infraction.
	 *
	 * @return The Issuer.
	 */
	public Issuer getIssuer()
	{
		if(issuer == null) return new Issuer(IssuerType.UNKNOWN, "UNKNOWN");
		return issuer;
	}

	/**
	 * Get the creation origin.
	 *
	 * @return The origin.
	 */
	public Origin getOrigin()
	{
		return getIssuer().getOrigin();
	}

	public Set<Issuer> getEvidenceIssuers()
	{
		return Sets.newHashSet(Collections2.transform(getEvidence(), new Function<Evidence, Issuer>()
		{
			@Override
			public Issuer apply(Evidence evidence)
			{
				return evidence.getIssuer();
			}
		}));
	}

	/**
	 * The time in milliseconds this Infraction was created.
	 *
	 * @return Time in milliseconds.
	 */
	public Long getTimeCreated()
	{
		return timeCreated;
	}

	/**
	 * The date this Infraction was created.
	 *
	 * @return The Date.
	 */
	public Date getDateCreated()
	{
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(timeCreated);
		return cal.getTime();
	}

	/**
	 * Get the reason for this Infraction.
	 *
	 * @return The reason.
	 */
	public String getReason()
	{
		return reason;
	}

	/**
	 * Get the score.
	 *
	 * @return The score.
	 */
	public Integer getScore()
	{
		return score;
	}

	/**
	 * Set of relevant Evidence.
	 *
	 * @return Relevant Evidence.
	 */
	public Set<Evidence> getEvidence()
	{
		return evidence;
	}

	/**
	 * Misc. notes on an Infraction..
	 *
	 * @return List of notes.
	 */
	public List<String> getNotes()
	{
		return notes;
	}

	/**
	 * Set the list of notes on this Infraction.
	 *
	 * @param notes Notes to be set.
	 */
	public void setNotes(List<String> notes)
	{
		this.notes = notes;
	}
}
