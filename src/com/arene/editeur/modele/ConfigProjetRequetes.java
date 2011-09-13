package com.arene.editeur.modele;

/**
 * Interface pour effectuer des requêtes sur la configuration d'un projet.
 * @author Camille
 *
 */
public interface ConfigProjetRequetes
{
	/**
	 * Renvoie le nom du projet
	 * 
	 * @return
	 * 		Le nom du projet
	 */
	public String getNomProjet();
	
	/**
	 * Renvoie le nom du jeu
	 * 
	 * @return
	 * 		Le nom du jeu
	 */
	public String getNomJeu();
	
	/**
	 * Renvoie le chemin du dossier racine du projet.
	 * 
	 * @return
	 * 		Le chemin canonique
	 */
	public String getCheminRacine();
}
