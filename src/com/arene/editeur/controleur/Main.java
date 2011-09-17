package com.arene.editeur.controleur;

import java.io.File;

import com.arene.editeur.vue.Editeur;

/**
 * La classe Main du projet.
 * @author Camille
 *
 *	Contient la méthode main.
 */
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
		
		ControleurProjet controleurProjet = new ControleurProjet();
		Editeur editeur = new Editeur(controleurProjet);
		editeur.setVisible(true);
	}

}
