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

package com.censoredsoftware.infractions.bukkit.evidence;

import com.censoredsoftware.infractions.bukkit.issuer.Issuer;
import com.censoredsoftware.infractions.bukkit.origin.Origin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Object class representing Evidence.
 */
public class Evidence
{
	// Private Data Fields
	private Issuer issuer;
	private EvidenceType type;
	private Long timeCreated;
	private String data;

	/**
	 * Create new Evidence.
	 *
	 * @param issuer      The issuer of the evidence.
	 * @param type        The type of evidence.
	 * @param timeCreated The time this evidence was created.
	 * @param data        The raw data.
	 */
	public Evidence(Issuer issuer, EvidenceType type, Long timeCreated, String data)
	{
		this.issuer = issuer;
		this.type = type;
		this.timeCreated = timeCreated;
		this.data = data;
	}

	/**
	 * Get the issuer.
	 *
	 * @return The Issuer.
	 */
	public Issuer getIssuer()
	{
		return issuer;
	}

	/**
	 * Get the origin of the evidence.
	 *
	 * @return The origin.
	 */
	public Origin getOrigin()
	{
		return getIssuer().getOrigin();
	}

	/**
	 * Get the type.
	 *
	 * @return The EvidenceType.
	 */
	public EvidenceType getType()
	{
		return type;
	}

	/**
	 * The time in milliseconds this Evidence was created.
	 *
	 * @return Time in milliseconds.
	 */
	public Long getTimeCreated()
	{
		return timeCreated;
	}

	/**
	 * The date this Evidence was created.
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
	 * Get the raw data.
	 *
	 * @return The data.
	 */
	public String getRawData()
	{
		return data;
	}

	/**
	 * Get the image data.
	 *
	 * @return Image evidence.
	 * @throws NullPointerException if there is no image evidence.
	 */
	public Image getImage() throws NullPointerException
	{
		// Check for the valid type.
		if(EvidenceType.IMAGE_URL.equals(type))
		{
			try
			{
				// Create the connection.
				URLConnection connection = new URL(data).openConnection();

				// Set a fake user agent.
				connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

				// Return the image.
				return ImageIO.read(connection.getInputStream());
			}
			catch(Exception ignored)
			{
			}
		}
		throw new NullPointerException("No image exists for this Evidence.");
	}
}
