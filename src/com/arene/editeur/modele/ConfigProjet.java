package com.arene.editeur.modele;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import com.arene.editeur.utils.file.FileTools;

/**
 * La configuration d'un projet.
 * 
 * @author Camille 
 * 
 * Représente la configuration d'un projet et gère le fichier de
 *         configuration : config.txt à travers l'objet Properties et la classe
 *         utilitaire FileTools. Elle implémente l'interface
 *         ConfigProjetRequetes qui offre un interface de requetes
 *         d'informations aux vues.
 */
public class ConfigProjet implements ConfigProjetRequetes
{
	/**
	 * Le nom du projet
	 */
	private String nomProjet;

	/**
	 * Le dossier contenant le projet
	 */
	private File dossierProjet;

	/**
	 * Les propriétés de la configuration
	 */
	private Properties prop = new Properties();

	/**
	 * Innitialise la condifguration pour un projet.
	 * 
	 * La configuration n'est complete que lorsque l'on en configure une nouvelle ou que l'on en ouvre une existante.
	 * 
	 * @param nom
	 *        Le nom du projet
	 */
	public ConfigProjet(String nom)
	{
		this.nomProjet = nom;
		dossierProjet = new File("projets/" + nom);
	}

	/**
	 * Renvoie le nom du projet
	 * 
	 * @return Le nom du projet
	 */
	public String getNomProjet()
	{
		return this.nomProjet;
	}

	/**
	 * Renvoie le nom du jeu
	 * 
	 * @return Le nom du jeu
	 */
	public String getNomJeu()
	{
		return prop.getProperty("nomJeu", null);
	}

	/**
	 * Renvoie le chemin du dossier racine du projet.
	 * 
	 * @return Le chemin canonique
	 */
	public String getCheminRacine()
	{
		String cheminProjet = "";
		try
		{
			cheminProjet = this.dossierProjet.getCanonicalPath();
		}
		catch (IOException e)
		{
			System.err
			        .println("Erreur lors de l'accèss au chemin du dossier projet dans ConfigProjet.getCheminRacine().");
		}

		return cheminProjet;
	}

	/**
	 * Créé une nouvelle configuration pour un nouveau projet.
	 * 
	 * @param config
	 * @return true - La configuration a été correctement crée.
	 */
	public boolean nouvelleConfig(HashMap<String, String> config)
	{
		boolean creationOk = true;

		// Création du dossier projet dans le même dossier
		dossierProjet.mkdir();
		try
		{
			String cheminRacine = dossierProjet.getCanonicalPath();
			System.out.println("Dossier créé à l'adresse : " + cheminRacine);

			// Création du fichier de configuration et enregistrement des
			// données
			File configFile = new File(cheminRacine + "/config.txt");
			
			for (String key : config.keySet())
			{
				prop.setProperty(key, config.get(key));
			}
			
			FileTools.saveConfig(configFile, prop);
			
			// création de la structure du dossier
			verifierStructure();
		}
		catch (IOException e)
		{
			creationOk = false;
			System.err
			        .println("Erreur lors de l'accèss au chemin du dossier projet dans ConfigProjet.nouvelleConfig().");
		}

		return creationOk;
	}

	private void verifierStructure()
    {
	    // Vérification de la présence du dossier images
        try
        {
	        File dossierImages = new File(dossierProjet.getCanonicalPath() + "/images");
	        if (!dossierImages.exists())
	        {
	        	dossierImages.mkdir();
	        }
        }
        catch (IOException e)
        {
        	System.err
	        .println("Erreur lors de l'accèss au chemin du dossier projet dans ConfigProjet.verifierStructure().");
        }
    }

	/**
	 * Ouvre la configuration d'un projet existant.
	 * 
	 * @return true - La configuration a été correctement chargée.
	 */
	public boolean chargerConfig()
	{
		boolean chargementOk = true;

		try
		{
			String cheminRacine = dossierProjet.getCanonicalPath();
			System.out.println("Ouverture du dossier : " + cheminRacine);
			// Lecture du fichier de configuration et enregistrement des données
			File configFile = new File(cheminRacine + "/config.txt");

			prop = FileTools.readConfig(configFile);
			verifierStructure();
		}
		catch (IOException e)
		{
			chargementOk = false;
			System.err
			        .println("Erreur lors de l'accèss au chemin du dossier projet dans ConfigProjet.chargerConfig().");
		}

		return chargementOk;
	}

	/**
	 * Renvoie le dossier racine du projet.
	 * 
	 * @return
	 * 		Le dossier racine du projet.
	 */
	public File getDossierProjet()
	{
		return dossierProjet;
	}

	public int getHauteur()
    {
	    return new Integer(prop.getProperty("hauteurCase", "32"));
    }

	public int getLargeur()
	{
		return new Integer(prop.getProperty("largeurCase", "32"));
	}
}
