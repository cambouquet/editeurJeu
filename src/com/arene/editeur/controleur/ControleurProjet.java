package com.arene.editeur.controleur;

import com.arene.editeur.modele.ConfigProjet;

public class ControleurProjet
{
	private ConfigProjet configProjet;
	
	public ControleurProjet()
	{
	}
	
	public boolean creerProjet(String nom)
	{
		configProjet = new ConfigProjet(nom);
		return configProjet.nouvelleConfig();
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
