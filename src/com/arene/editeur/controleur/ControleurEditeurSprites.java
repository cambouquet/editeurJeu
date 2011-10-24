package com.arene.editeur.controleur;

import java.io.File;
import java.util.ArrayList;

import com.arene.editeur.modele.ConfigProjet;
import com.arene.editeur.modele.SelectionCategorie;
import com.arene.editeur.modele.SelectionElement;
import com.arene.editeur.modele.SelectionOngletModel;
import com.arene.editeur.modele.SpriteTest;
import com.arene.editeur.vue.EditeurSprites;
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
	private ConfigProjet configProjet;

	public ControleurEditeurSprites(ConfigProjet configProjet)
	{
		this.configProjet = configProjet;
		this.dossierProjet = configProjet.getDossierProjet();
	}
	
	public void afficherEditeur()
	{
		EditeurSprites editeurSprites = new EditeurSprites(this);
		editeurSprites.setVisible(true);
	}

	public PanneauSelection creerPanneauSelection()
    {
		ControleurPanneauSelection ctrlPS = new ControleurPanneauSelection();
		
		ArrayList<SelectionCategorie> categories = new ArrayList<SelectionCategorie>();
		
		File dossierImages = new File(dossierProjet + "/images");
		File[] dossiers = dossierImages.listFiles();
		
		for (int i = 0 ; i < dossiers.length ; i ++)
		{
			if (dossiers[i].isDirectory())
			{
				String[] elementsNom = dossiers[i].getName().split("_");
				if (elementsNom.length == 2)
				{
					categories.add(new SelectionCategorie(elementsNom[1], new Integer(elementsNom[0]), creerSprites(dossiers[i])));
				}			
			}
		}
		categories.add(new SelectionCategorie("nouveau", creerSprites(dossierImages)));
		
		ctrlPS.setCategories(categories);
		
		ctrlPS.updateCategories();
		return ctrlPS.getPanneau();
    }
	
	private ArrayList<SelectionElement> creerSprites(File dossier)
	{
		ArrayList<SelectionElement> elements = new ArrayList<SelectionElement>();
		File[] images = dossier.listFiles();
		for (File image : images)
		{
			if (image.isFile())
			{
				elements.add(new SpriteTest(image, configProjet.getHauteur(), configProjet.getLargeur()));
			}
		}
		
		return elements;
	}
}
