package com.arene.editeur.modele;

public class ConfigProjet
{
	private String nom;
	private String cheminRacine;
	
	public ConfigProjet(String nom, String chemin)
	{
		this.nom = nom;
		this.cheminRacine = chemin;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public String getCheminRacine()
	{
		return this.cheminRacine;
	}
}
