package com.arene.editeur.controleur;

import java.io.File;
import java.util.ArrayList;

import com.arene.editeur.listener.SelectionElementListener;
import com.arene.editeur.modele.ConfigProjet;
import com.arene.editeur.modele.SelectionCategorie;
import com.arene.editeur.modele.SelectionElement;
import com.arene.editeur.modele.SpriteTest;
import com.arene.editeur.vue.ESSpriteCarac;
import com.arene.editeur.vue.EditeurSprites;
import com.arene.editeur.vue.PanneauSelection;

/**
 * Le controleur de l'éditeur de sprites.
 * @author Camille
 *
 */
public class ControleurEditeurSprites implements SelectionElementListener
{
	/**
	 * Le dossier racine du projet.
	 */
	private File dossierProjet = null;
	
	/**
	 * La configuration du projet.
	 */
	private ConfigProjet configProjet;
	
	private SpriteTest spriteSelectionne;
	
	private ControleurESSpriteCarac ctrlESSC;
	
	private ControleurPanneauSelection ctrlPS;

	/**
	 * Constructeur avec la configuration du projet.
	 * 
	 * @param configProjet
	 * 				La configuration du projet.
	 */
	public ControleurEditeurSprites(ConfigProjet configProjet)
	{
		this.configProjet = configProjet;
		this.dossierProjet = configProjet.getDossierProjet();
	}
	
	/**
	 * Afficher l'éditeur à l'écran.
	 */
	public void afficherEditeur()
	{
		EditeurSprites editeurSprites = new EditeurSprites(this);
		editeurSprites.setVisible(true);
	}

	/**
	 * Créer le panneau de sélection.
	 * 
	 * @return
	 * 		Le panneau de sélection.
	 */
	public PanneauSelection creerPanneauSelection()
    {
		ctrlPS = new ControleurPanneauSelection();
		ctrlPS.addSelectionElementListener(this);
		
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
	
	/**
	 * Créer les sprites pour le panneau de sélection à partir d'un dossier.
	 * 
	 * @param dossier
	 * 				Le dossier contenant les images à transformer en sprites.
	 * @return
	 * 			Les sprites créés.
	 */
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
	
	public ESSpriteCarac creerPanneauCarac()
	{
		ctrlESSC = new ControleurESSpriteCarac();
		return ctrlESSC.getPanneau();
	}
	
	@Override
    public void elementSelectionne(SelectionElement element)
    {
	    this.spriteSelectionne = (SpriteTest) element;
	    ctrlESSC.selectionnerSprite(this.spriteSelectionne);
    }
}
