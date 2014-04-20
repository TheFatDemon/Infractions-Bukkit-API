package com.censoredsoftware.infractions.bukkit.api;

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
	 * This plugin was issued by a legacy version of Infractions.
	 */
	LEGACY,
	/**
	 * The origin is unknown or unknowable.
	 */
	UNKNOWN,
	/**
	 * A plugin has implemented a custom way to issue an infraction.
	 */
	CUSTOM
}
