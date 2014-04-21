package com.censoredsoftware.infractions.bukkit.issuer;

/**
 * Enum representing the type of issuer for an infraction.
 */
public enum IssuerType
{
	/**
	 * The staff issued an infraction.
	 */
	STAFF,
	/**
	 * The legacy plugin issued an infraction.
	 */
	LEGACY,
	/**
	 * An unknown or unknowable issuer issued an infraction.
	 */
	UNKNOWN,
	/**
	 * A plugin issued an infraction.
	 */
	CUSTOM
}
