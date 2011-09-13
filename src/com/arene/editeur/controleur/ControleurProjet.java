package com.arene.editeur.controleur;

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
	public ConfigProjet creerProjet(String nomProjet, String nomJeu)
	{
		configProjet = new ConfigProjet(nomProjet);
		configProjet.nouvelleConfig(nomJeu);
		
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
		ControleurEditeurSprites ctrlEditeurSprites = new ControleurEditeurSprites();
		EditeurSprites editeurSprites = new EditeurSprites(ctrlEditeurSprites, configProjet.getDossierProjet());
		editeurSprites.setVisible(true);
    }
}
