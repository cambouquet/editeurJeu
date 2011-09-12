package com.arene.editeur.controleur;

import com.arene.editeur.modele.ConfigProjet;
import com.arene.editeur.vue.EditeurSprites;

public class ControleurProjet
{
	private ConfigProjet configProjet;
	
	public ControleurProjet()
	{
	}
	
	public ConfigProjet creerProjet(String nomProjet, String nomJeu)
	{
		configProjet = new ConfigProjet(nomProjet);
		configProjet.nouvelleConfig(nomJeu);
		
		return configProjet;
	}
	
	public ConfigProjet ouvrirProjet(String nom)
	{
		configProjet = new ConfigProjet(nom);
		configProjet.chargerConfig();
		
		return configProjet;
	}
	
	public boolean projetOuvert()
	{
		return (configProjet == null) ? false : true;
	}

	public void lancerEditeurSprites()
    {
		ControleurEditeurSprites ctrlEditeurSprites = new ControleurEditeurSprites();
		EditeurSprites editeurSprites = new EditeurSprites(ctrlEditeurSprites, configProjet.getDossierProjet());
		editeurSprites.setVisible(true);
    }
}
