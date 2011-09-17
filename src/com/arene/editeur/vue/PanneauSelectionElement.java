package com.arene.editeur.vue;

import java.awt.Image;

import javax.swing.JPanel;

import com.arene.editeur.modele.ElementDeSelection;

@SuppressWarnings("serial")
public class PanneauSelectionElement extends JPanel
{
	private ElementDeSelection element;
	private Image image;
	private String nom;
	
	public PanneauSelectionElement(ElementDeSelection element)
	{
		this.element = element;
		this.image = element.getImage();
		this.nom = element.getNom();
	}
}
