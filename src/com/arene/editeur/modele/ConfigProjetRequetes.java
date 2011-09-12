package com.arene.editeur.modele;


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
