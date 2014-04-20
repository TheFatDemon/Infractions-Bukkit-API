package com.censoredsoftware.infractions.bukkit.api.test;

import com.censoredsoftware.infractions.bukkit.Infraction;
import com.censoredsoftware.infractions.bukkit.Infractions;
import com.censoredsoftware.infractions.bukkit.dossier.Dossier;
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

		Infractions.addDossier(new TestCompleteDossier(hqmId, "HmmmQuestionMark", new Infraction(hqmId, System.currentTimeMillis(), "Testing.", 80, new TestIssuer(IssuerType.LEGACY, "LEGACY"))));
		Infractions.addDossier(new TestCompleteDossier(alexId, "_Alex", new Infraction(alexId, System.currentTimeMillis(), "Testing.", 100, new TestIssuer(IssuerType.STAFF, hqmId.toString()))));
	}

	@Test()
	public void test()
	{
		Dossier hqmDossier = Infractions.getDossier("HmmmQuestionMark");
		Dossier alexDossier = Infractions.getDossier("_Alex");

		assertEquals("Failure: HmmmQuestionMark ID No Match", hqmId, hqmDossier.getMojangId());
		assertEquals("Failure: _Alex ID No Match", alexId, alexDossier.getMojangId());

		assertEquals("Failure: HmmmQuestionMark Score No Match", 80, hqmDossier.getScore());
		assertEquals("Failure: _Alex Score No Match", 100, alexDossier.getScore());
	}
}
