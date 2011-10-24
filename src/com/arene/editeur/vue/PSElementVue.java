package com.arene.editeur.vue;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class PSElementVue extends JPanel
{
	private Image image;
	private String nom;
	
	public PSElementVue(Image image, String nom)
	{
		this.image = image;
		this.nom = nom;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public Image getImage()
	{
		return this.image;
	}
	
	public void paintComponent(Graphics g)
	{
		if (image != null)
		{
			g.drawImage(this.image, 0, 0, this.image.getWidth(null), this.image.getHeight(null), null);
		}
		
	}
}
