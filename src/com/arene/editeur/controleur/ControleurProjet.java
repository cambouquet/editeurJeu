package com.arene.editeur.controleur;

import java.util.HashMap;

import com.arene.editeur.modele.ConfigProjet;
import com.arene.editeur.vue.EditeurSprites;

/**
 * Le controleur du projet.
 * @author Camille
 *
 *	Ce controleur gère le projet en général et la fenêtre Editeur de jeu.
 */
public class ControleurProjet
{
	/**
	 * La configuration du projet actuel.
	 */
	private ConfigProjet configProjet;
	
	/**
	 * Constructeur simple.
	 */
	public ControleurProjet()
	{
	}
	
	/**
	 * Créer un nouveau projet.
	 * 
	 * @param nomProjet
	 * 		Le nom du projet à créer.
	 * @param nomJeu
	 * 		Le nom du jeu.
	 * @return
	 * 		La configuration du projet créé.
	 */
	public ConfigProjet creerProjet(String[] parametres)
	{
		configProjet = new ConfigProjet(parametres[0]);
		
		HashMap<String, String> config = new HashMap<String, String>();
		
		config.put("nomJeu", parametres[1]);
		config.put("hauteurCase", parametres[2]);
		config.put("largeurCase", parametres[3]);
		configProjet.nouvelleConfig(config);
		
		return configProjet;
	}
	
	/**
	 * Ouvrir un projet existant.
	 * 
	 * @param nom
	 * 		Le nom du projet à ouvrir.
	 * @return
	 * 		La configuration du projet ouvert.
	 */
	public ConfigProjet ouvrirProjet(String nom)
	{
		configProjet = new ConfigProjet(nom);
		configProjet.chargerConfig();
		
		return configProjet;
	}
	
	/**
	 * Indique si un projet est actuellement ouvert.
	 * 
	 * @return
	 * 		true - un projet est actuellement ouvert;
	 * 		false - aucun projet n'est ouvert.
	 */
	public boolean projetOuvert()
	{
		return (configProjet == null) ? false : true;
	}

	/**
	 * Ouvre la fenêtre de l'éditeur de sprite pour les configurer.
	 */
	public void lancerEditeurSprites()
    {
		ControleurEditeurSprites ctrlEditeurSprites = new ControleurEditeurSprites(configProjet);
		ctrlEditeurSprites.afficherEditeur();	
    }
}
