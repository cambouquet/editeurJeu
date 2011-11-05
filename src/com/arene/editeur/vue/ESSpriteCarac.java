package com.arene.editeur.vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import com.arene.editeur.controleur.ControleurESSpriteCarac;
import com.arene.editeur.modele.SpriteTest;

public class ESSpriteCarac extends JPanel
{
	private SpriteTest sprite;
	private JScrollPane jsVisualisation;
	private ImagePanel pVisualisation;
	private JPanel pProp;
	private JLabel lCode;
	private JComboBox cbType;
	private JButton bSauver;
	
	private ControleurESSpriteCarac ctrlESSC;
	
	private final static int VISUALISATION_HAUTEUR = 300;
	private final static int VISUALISATION_LARGEUR = 300;
	
	public ESSpriteCarac(ControleurESSpriteCarac ctrlESSC)
	{
		this.ctrlESSC = ctrlESSC;
		this.setBorder(new TitledBorder(BorderFactory.createRaisedBevelBorder(), "Caractéristiques"));
		
		this.setLayout(new BorderLayout());
		
		this.add(creerPanneauVisualisation(), BorderLayout.EAST);
		this.add(creerPanneauInfos(), BorderLayout.WEST);
		
		bSauver = new JButton("Sauvegarder");
		bSauver.setEnabled(false);
		bSauver.addActionListener(new ActionListener(){

			@Override
            public void actionPerformed(ActionEvent arg0)
            {
	            ESSpriteCarac.this.ctrlESSC.sauverProprietes();
            }
			
		});
		this.add(bSauver, BorderLayout.SOUTH);
	}

	private Component creerPanneauInfos()
    {
	    pProp = new JPanel();
	    pProp.setLayout(new GridBagLayout());
	    
	    lCode = new JLabel("Code : Nom");
	    pProp.add(lCode, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
		        GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(
		                5, 10, 25, 10), 0, 0));

	    JLabel lType = new JLabel("Type");
	    pProp.add(lType, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
		        GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(
		                5, 10, 25, 10), 0, 0));
	    
	    cbType = new JComboBox();
	    cbType.addActionListener(new ActionListener(){

			@Override
            public void actionPerformed(ActionEvent arg0)
            {
				if (sprite != null)
				{
					sprite.setType((String) cbType.getSelectedItem());
				}
				
            }
	    	
	    });
	    ctrlESSC.getTypes(cbType);
	    pProp.add(cbType, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
		        GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(
		                5, 10, 25, 10), 0, 0));
	    
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
	    lCode.setText(sprite.getCode() + " : " + sprite.getNom());
	    
	    cbType.setSelectedItem(sprite.getType());
	    bSauver.setEnabled(true);
	    revalidate();
	    repaint();
    }
}
