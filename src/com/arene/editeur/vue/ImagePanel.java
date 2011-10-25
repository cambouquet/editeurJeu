package com.arene.editeur.vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel
{
	private Image image;
	private final static int MARGE = 10;
	
	public ImagePanel(Image image)
	{
		this.image = image;
		this.setOpaque(false);
		if (image != null)
		{
			this.setPreferredSize(new Dimension(image.getWidth(null) + MARGE * 2, image.getHeight(null) + MARGE * 2));						
		}
	}
	
	public void paintComponent(Graphics g)
	{		
		if (image != null)
		{
			g.drawImage(image, MARGE, MARGE, null, null);
		}
	}
	
	public void setImage(Image image)
	{
		this.image = image;
		if (image != null)
		{
			this.setPreferredSize(new Dimension(image.getWidth(null) + MARGE * 2, image.getHeight(null) + MARGE * 2));						
		}
		
		revalidate();
		repaint();
	}
}
