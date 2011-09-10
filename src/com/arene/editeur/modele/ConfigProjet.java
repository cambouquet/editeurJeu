package com.arene.editeur.modele;

import java.io.File;
import java.io.IOException;

public class ConfigProjet
{
	private String nom;
	private String cheminRacine;
	
	public ConfigProjet(String nom)
	{
		this.nom = nom;
		File dossierProjet = new File(nom);
		dossierProjet.mkdir();
		try
        {
	        cheminRacine = dossierProjet.getCanonicalPath();
	        System.out.println("Dossier créé à l'adresse : " + cheminRacine);
        }
        catch (IOException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
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
