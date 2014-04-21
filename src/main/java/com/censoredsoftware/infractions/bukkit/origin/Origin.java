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

package com.censoredsoftware.infractions.bukkit.origin;

import com.google.common.base.Objects;

/**
 * Class object representing the origin of any form of data.
 */
public class Origin
{
	// Private Data Fields
	private String id;
	private String name;
	private final OriginType type;

	/**
	 * Create a new Origin object.
	 *
	 * @param id   The unique id for this origin.
	 * @param name The name of this origin.
	 * @param type The type of this origin.
	 */
	public Origin(String id, String name, OriginType type)
	{
		this.id = id;
		this.name = name;
		this.type = type;
	}

	/**
	 * Get the id.
	 *
	 * @return The id.
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Get the name.
	 *
	 * @return The name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Get the type.
	 *
	 * @return The type.
	 */
	public OriginType getType()
	{
		return type;
	}

	/**
	 * Overridden equals method.
	 *
	 * @param other Other object.
	 * @return They are equal.
	 */
	@Override
	public boolean equals(Object other)
	{
		return other instanceof Origin && getType().equals(((Origin) other).getType()) && getId().equals(((Origin) other).getId());
	}

	/**
	 * Overridden hashCode method.
	 *
	 * @return The hash code.
	 */
	@Override
	public int hashCode()
	{
		return Objects.hashCode(id, name, type);
	}

	/**
	 * Overridden toString method.
	 *
	 * @return The string representation.
	 */
	@Override
	public String toString()
	{
		return Objects.toStringHelper(this).add("id", id).add("name", name).add("type", type.name()).toString();
	}

	/**
	 * Check if the name of this Origin and another Origin match.
	 *
	 * @param other The other origin.
	 * @return The names match.
	 */
	public boolean nameEquals(Object other)
	{
		return other instanceof Origin && getName().equals(((Origin) other).getName());
	}

}

