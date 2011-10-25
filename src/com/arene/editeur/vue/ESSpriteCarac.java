package com.arene.editeur.vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import com.arene.editeur.modele.SpriteTest;

public class ESSpriteCarac extends JPanel
{
	private SpriteTest sprite;
	private JScrollPane jsVisualisation;
	private ImagePanel pVisualisation;
	private JPanel pProp;
	
	private final static int VISUALISATION_HAUTEUR = 300;
	private final static int VISUALISATION_LARGEUR = 300;
	
	public ESSpriteCarac()
	{
		this.setBorder(new TitledBorder(BorderFactory.createRaisedBevelBorder(), "Caractéristiques"));
		
		this.setLayout(new BorderLayout());
		
		this.add(creerPanneauVisualisation(), BorderLayout.CENTER);
		this.add(creerPanneauInfos(), BorderLayout.WEST);
	}

	private Component creerPanneauInfos()
    {
	    pProp = new JPanel();
	    pProp.setBorder(new TitledBorder(BorderFactory.createLoweredBevelBorder(), "Propriétés"));
	    
	    
	    return pProp;
    }

	private Component creerPanneauVisualisation()
    {
	    pVisualisation = new ImagePanel(null);
		jsVisualisation = new JScrollPane(pVisualisation);
		jsVisualisation.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsVisualisation.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsVisualisation.setBorder(new TitledBorder(BorderFactory.createLoweredBevelBorder(), "Visualisation"));
		jsVisualisation.setMaximumSize(new Dimension(VISUALISATION_LARGEUR, VISUALISATION_HAUTEUR));
		jsVisualisation.setMinimumSize(new Dimension(VISUALISATION_LARGEUR, VISUALISATION_HAUTEUR));
		jsVisualisation.setPreferredSize(new Dimension(VISUALISATION_LARGEUR, VISUALISATION_HAUTEUR));
		return jsVisualisation;
    }

	public void selectionnerSprite(SpriteTest sprite)
    {
	    this.sprite = sprite;
	    pVisualisation.setImage(sprite.getImage());
	    revalidate();
	    repaint();
    }
}
