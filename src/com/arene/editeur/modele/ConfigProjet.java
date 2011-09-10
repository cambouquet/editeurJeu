package com.arene.editeur.modele;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.arene.editeur.utils.file.FileTools;

public class ConfigProjet
{
	private String nom;
	private String cheminRacine;
	
	/**
	 * Créé la condifguration pour un projet
	 * 
	 * @param nom
	 * 		Le nom du projet
	 */
	public ConfigProjet(String nom)
	{
		this.nom = nom;
		
		// Création du dossier projet dans le même dossier
		File dossierProjet = new File(nom);
		dossierProjet.mkdir();
		try
        {
	        cheminRacine = dossierProjet.getCanonicalPath();
	        System.out.println("Dossier créé à l'adresse : " + cheminRacine);
        }
        catch (IOException e)
        {
	        System.err.println("Erreur lors de l'accèss au chemin du dossier projet.");
        }
        
        // Création du fichier de configuration et enregistrement des données
        File configFile = new File(cheminRacine + "/config.txt");
        Properties prop = new Properties();
        prop.setProperty("nom", nom);
        
        FileTools.saveConfig(configFile, prop);
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
