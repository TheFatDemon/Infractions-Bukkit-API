package com.censoredsoftware.infractions.bukkit.api.test;

import com.censoredsoftware.infractions.bukkit.Infraction;
import com.censoredsoftware.infractions.bukkit.dossier.CompleteDossier;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class TestCompleteDossier extends TestDossier implements CompleteDossier
{
	private String lastKnownName;

	public TestCompleteDossier(UUID mojangId, String lastKnownName, Infraction... infractions)
	{
		super(mojangId, infractions);
		this.lastKnownName = lastKnownName;
	}

	@Override
	public OfflinePlayer getOfflinePlayer() throws IllegalStateException
	{
		throw new IllegalStateException("This is a test case, no server is running.");
	}

	@Override
	public String getLastKnownName()
	{
		return lastKnownName;
	}
}
