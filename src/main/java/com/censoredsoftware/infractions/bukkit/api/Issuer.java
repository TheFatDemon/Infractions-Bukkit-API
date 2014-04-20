package com.censoredsoftware.infractions.bukkit.api;

import java.util.Set;

/**
 * Abstract class representing an Issuer of an Infraction/Evidence.
 */
public abstract class Issuer
{
	// Private Data Fields
	private IssuerType type;
	private String id;

	/**
	 * Create a new Issuer object.
	 *
	 * @param type The type of issuer.
	 * @param id   The ID of this issuer.
	 */
	public Issuer(IssuerType type, String id)
	{
		this.type = type;
		this.id = id;
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
	 * Set of Issued Infractions.
	 *
	 * @return Infractions.
	 */
	public abstract Set<Infraction> getIssuedInfractions();

	/**
	 * Set of Issued Evidence.
	 *
	 * @return Evidence.
	 */
	public abstract Set<Evidence> getIssuedEvidence();

	/**
	 * Set of Dossiers contributed to.
	 *
	 * @return Dossiers.
	 */
	public abstract Set<Dossier> getContributedDossiers();
}
