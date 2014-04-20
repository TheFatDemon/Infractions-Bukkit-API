package com.censoredsoftware.infractions.bukkit.api;

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

	/**
	 * Create a new Infraction object.
	 *
	 * @param playerId    The Mojang UUID of the player.
	 * @param timeCreated The time the Infraction was first created.
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
		return issuer;
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
}
