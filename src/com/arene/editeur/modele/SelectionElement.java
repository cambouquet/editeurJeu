package com.arene.editeur.modele;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Interface permettant d'utiliser un élément dans un panneau de sélection.
 * Cet élément doit pouvoir renvoyer une image et un nom, ainsi qu'une icone pour le représenter. 
 * @author Camille
 *
 */
public interface SelectionElement
{
	/**
	 * Récupérer l'image contenu dans cet élément.
	 * 
	 * @return
	 * 		L'image de l'élément.
	 */
	public Image getImage();
	
	/**
	 * Récupérer le nom de l'élément.
	 * 
	 * @return
	 * 		Le nom de l'élément.
	 */
	public String getNom();
	
	/**
	 * Récupérer l'icone représentant l'élément.
	 * 
	 * @return
	 * 		L'icone représentant l'élément.
	 */
	public ImageIcon getIcone();
}
