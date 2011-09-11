package com.arene.editeur.modele;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.arene.editeur.utils.file.FileTools;

public class ConfigProjet
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
	 * Créé la condifguration pour un projet
	 * 
	 * @param nom
	 * 		Le nom du projet
	 */
	public ConfigProjet(String nom)
	{
		this.nomProjet = nom;
		dossierProjet = new File("projets/" + nom);
	}
	
	/**
	 * Renvoie le nom du projet
	 * 
	 * @return
	 * 		Le nom du projet
	 */
	public String getNomProjet()
	{
		return this.nomProjet;
	}
	
	/**
	 * Renvoie le nom du jeu
	 * 
	 * @return
	 * 		Le nom du jeu
	 */
	public String getNomJeu()
	{
		return prop.getProperty("nomJeu", null);
	}
	
	/**
	 * Renvoie le chemin du dossier racine du projet.
	 * 
	 * @return
	 * 		Le chemin canonique
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
	        System.err.println("Erreur lors de l'accèss au chemin du dossier projet dans ConfigProjet.getCheminRacine().");
        }
        
        return cheminProjet;
	}
	
	/**
	 * Créé une nouvelle configuration pour un nouveau projet.
	 * @param nomJeu 
	 * 
	 * @return
	 * 		true - La configuration a été correctement crée.
	 */
	public boolean nouvelleConfig(String nomJeu)
	{
		boolean creationOk = true;
		
		// Création du dossier projet dans le même dossier
		dossierProjet.mkdir();
		try
        {
	        String cheminRacine = dossierProjet.getCanonicalPath();
	        System.out.println("Dossier créé à l'adresse : " + cheminRacine);
	        
	        // Création du fichier de configuration et enregistrement des données
	        File configFile = new File(cheminRacine + "/config.txt");
	        prop.setProperty("nomJeu", nomJeu);
	        FileTools.saveConfig(configFile, prop);
        }
        catch (IOException e)
        {
        	creationOk = false;
	        System.err.println("Erreur lors de l'accèss au chemin du dossier projet dans ConfigProjet.nouvelleConfig().");
        }
        
        return creationOk;
	}
	
	/**
	 * Ouvre la configuration d'un projet existant.
	 * 
	 * @return
	 * 		true - La configuration a été correctement chargée.
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
        }
        catch (IOException e)
        {
        	chargementOk = false;
	        System.err.println("Erreur lors de l'accèss au chemin du dossier projet dans ConfigProjet.chargerConfig().");
        }
        
        return chargementOk;
	}
}
