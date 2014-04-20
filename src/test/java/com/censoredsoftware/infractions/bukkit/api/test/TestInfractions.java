package com.censoredsoftware.infractions.bukkit.api.test;

import com.censoredsoftware.infractions.bukkit.api.Dossier;
import com.censoredsoftware.infractions.bukkit.api.Infraction;
import com.censoredsoftware.infractions.bukkit.api.IssuerType;
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
	public static final TestDatabase TEST_DATABASE = new TestDatabase();
	UUID hqmId = MojangIdProvider.getId("HmmmQuestionMark");
	UUID alexId = MojangIdProvider.getId("_Alex");

	@Before()
	public void prepareData()
	{
		TEST_DATABASE.DOSSIER_MAP.put(hqmId, new TestCompleteDossier(hqmId, "HmmmQuestionMark", new Infraction(hqmId, System.currentTimeMillis(), "Testing.", 80, new TestIssuer(IssuerType.LEGACY, "LEGACY"))));
		TEST_DATABASE.DOSSIER_MAP.put(alexId, new TestCompleteDossier(alexId, "_Alex", new Infraction(alexId, System.currentTimeMillis(), "Testing.", 100, new TestIssuer(IssuerType.STAFF, hqmId.toString()))));
	}

	@Test()
	public void test()
	{
		Dossier hqmDossier = TEST_DATABASE.getDossier("HmmmQuestionMark");
		Dossier alexDossier = TEST_DATABASE.getDossier("_Alex");

		assertEquals("HmmmQuestionMark ID Match", hqmId, hqmDossier.getId());
		assertEquals("_Alex ID Match", alexId, alexDossier.getId());

		assertEquals("HmmmQuestionMark Score Match", 80, hqmDossier.getScore());
		assertEquals("_Alex Score Match", 100, alexDossier.getScore());
	}
}
