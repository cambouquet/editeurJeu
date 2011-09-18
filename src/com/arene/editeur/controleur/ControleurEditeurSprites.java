package com.arene.editeur.controleur;

import java.io.File;

import com.arene.editeur.modele.SelectionOngletModel;
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
	    SelectionOngletModel ongletTest = new SelectionOngletModel("Test", "Panneau de test");
	    ctrlPanneauSelection.ajouterOnglet(ongletTest);
	    SelectionOngletModel ongletTest2 = new SelectionOngletModel("Test 2", "Panneau de test 2");
	    ctrlPanneauSelection.ajouterOnglet(ongletTest2);
	    return new PanneauSelection(ctrlPanneauSelection);
    }
}
