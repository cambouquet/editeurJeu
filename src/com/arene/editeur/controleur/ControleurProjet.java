package com.arene.editeur.controleur;

import com.arene.editeur.modele.ConfigProjet;

public class ControleurProjet
{
	private ConfigProjet configProjet;
	
	public ControleurProjet()
	{
	}
	
	public boolean creerProjet(String nomProjet, String nomJeu)
	{
		configProjet = new ConfigProjet(nomProjet);
		return configProjet.nouvelleConfig(nomJeu);
	}
	
	public boolean ouvrirProjet(String nom)
	{
		configProjet = new ConfigProjet(nom);
		return configProjet.chargerConfig();
	}
	
	public boolean projetOuvert()
	{
		return (configProjet == null) ? false : true;
	}
}
