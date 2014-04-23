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

package com.censoredsoftware.infractions.bukkit.test;

import com.censoredsoftware.infractions.bukkit.Infraction;
import com.censoredsoftware.infractions.bukkit.Infractions;
import com.censoredsoftware.infractions.bukkit.dossier.Dossier;
import com.censoredsoftware.infractions.bukkit.issuer.Issuer;
import com.censoredsoftware.infractions.bukkit.issuer.IssuerType;
import com.censoredsoftware.library.helper.MojangIdProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Infractions Bukkit API.
 *
 * @author me@hqm.me (HmmmQuestionMark)
 */
@RunWith(JUnit4.class)
public class TestInfractions
{
	UUID hqmId = MojangIdProvider.getId("HmmmQuestionMark");
	UUID alexId = MojangIdProvider.getId("_Alex");

	@Before()
	public void prepareData()
	{
		// Set the test database.
		Infractions.setDatabase(new TestDatabase());

		Infractions.addDossier(new TestCompleteDossier(hqmId, "HmmmQuestionMark", new Infraction(hqmId, System.currentTimeMillis(), "Testing.", 80, new Issuer(IssuerType.LEGACY, "LEGACY"))));
		Infractions.addDossier(new TestCompleteDossier(alexId, "_Alex", new Infraction(alexId, System.currentTimeMillis(), "Testing.", 100, new Issuer(IssuerType.STAFF, hqmId.toString()))));
	}

	@Test()
	public void test()
	{
		Dossier hqmDossier = Infractions.getDossier("HmmmQuestionMark");
		Dossier alexDossier = Infractions.getDossier("_Alex");

		assertEquals("Failure: HmmmQuestionMark ID No Match", hqmId, hqmDossier.getId());
		assertEquals("Failure: _Alex ID No Match", alexId, alexDossier.getId());

		assertEquals("Failure: HmmmQuestionMark Score No Match", 80, hqmDossier.getScore());
		assertEquals("Failure: _Alex Score No Match", 100, alexDossier.getScore());
	}
}
