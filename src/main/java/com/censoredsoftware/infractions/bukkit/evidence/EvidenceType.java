package com.censoredsoftware.infractions.bukkit.evidence;

/**
 * Enum representing the different types of Evidence.
 */
public enum EvidenceType
{
	/**
	 * A simple message.
	 */
	TEXT,
	/**
	 * A URL leading to an image file.
	 */
	IMAGE_URL,
	/**
	 * A URL leading to something other than an image file.
	 */
	OTHER_URL,
	/**
	 * The type is unknown, will be treated as text.
	 */
	UNKNOWN,
	/**
	 * A custom type of evidence created by another plugin.
	 */
	CUSTOM
}
