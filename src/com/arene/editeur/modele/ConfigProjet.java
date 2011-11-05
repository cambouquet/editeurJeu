package com.arene.editeur.modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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

	/**
	 * Vérifier la structure du dossier projet.
	 * 
	 * Le dossier de configuration doit contenir :
	 * images/
	 * config/
	 * 			types.conf
	 * 
	 * Si les fichiers ou dossiers n'existent pas, ils sont créés.
	 */
	private void verifierStructure()
    {
	    // Vérification de la présence du dossier images
        try
        {
        	String cheminDossierProjet = dossierProjet.getCanonicalPath();
        	verifierDossier(cheminDossierProjet + "/images");
        	verifierDossier(cheminDossierProjet + "/config");
        	verifierFichier(cheminDossierProjet + "/config" + "/types.config");
        	Collection<Object> typesCollection = FileTools.readConfig(new File(cheminDossierProjet + "/config" + "/types.config")).values();
        	String[] types = new String[typesCollection.size()];
        	typesCollection.toArray(types);
        	verifierDossiers(cheminDossierProjet + "/images", types);
        }
        catch (IOException e)
        {
        	System.err
	        .println("Erreur lors de l'accès au chemin du dossier projet dans ConfigProjet.verifierStructure().");
        }
    }
	
	private void verifierDossiers(String cheminDossierParent, String[] nomDossiers)
    {
	    for (int i = 0 ; i < nomDossiers.length ; i ++)
	    {
	    	verifierDossier(cheminDossierParent + "/" + nomDossiers[i]);
	    }
    }

	private void verifierFichier(String chemin)
    {
	    File fichier = new File(chemin);
	    if (!fichier.exists())
	    {
	    	try
            {
	            fichier.createNewFile();
            }
            catch (IOException e)
            {
            	System.err
            	.println("Erreur lors de l'accès au chemin du dossier projet dans ConfigProjet.verifierFichier().");
            }
	    }
    }

	/**
	 * Vérifier la présence d'un dossier.
	 * 
	 * Si le dossier est absent, il est créé.
	 * 
	 * @param chemin
	 * 			Le chemin du dossier à vérifier.
	 */
	private void verifierDossier(String chemin)
	{
		File dossierImages = new File(chemin);
        if (!dossierImages.exists())
        {
        	dossierImages.mkdir();
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
