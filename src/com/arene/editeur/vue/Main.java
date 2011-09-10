package com.arene.editeur.vue;

import java.io.File;

public class Main
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Créé les dossiers nécessaires si non présents
		File dossierProjets = new File("projets");
		if (!dossierProjets.exists())
		{
			dossierProjets.mkdir();
		}
		
		Editeur editeur = new Editeur();
		editeur.setVisible(true);
	}

}
