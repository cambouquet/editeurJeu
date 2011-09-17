package com.arene.editeur.controleur;

import java.io.File;

import com.arene.editeur.vue.PanneauSelection;

/**
 * Le controleur de l'éditeur de sprites.
 * @author Camille
 *
 */
public class ControleurEditeurSprites
{
	/**
	 * Le dossier racine du projet.
	 */
	private File dossierProjet = null;

	public ControleurEditeurSprites(File dossierProjet)
	{
		this.dossierProjet = dossierProjet;
	}

	public PanneauSelection creerPanneauSelection()
    {
	    ControleurPanneauSelection ctrlPanneauSelection = new ControleurPanneauSelection();
	    ctrlPanneauSelection.ajouterOnglet("Test");
	    return new PanneauSelection(ctrlPanneauSelection);
    }
}
