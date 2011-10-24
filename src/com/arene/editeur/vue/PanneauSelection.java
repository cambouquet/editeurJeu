package com.arene.editeur.vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.arene.editeur.controleur.ControleurPanneauSelection;
import com.arene.editeur.modele.SelectionCategorie;
import com.arene.editeur.modele.SelectionElement;

@SuppressWarnings("serial")
public class PanneauSelection extends JPanel
{
	private ControleurPanneauSelection ctrlPS;
	private JPanel pCategories = new JPanel();
	private JPanel pElements = new JPanel();
	private JButton categorieSelectionnee;
	
	public PanneauSelection(ControleurPanneauSelection ctrlPS)
	{
		this.ctrlPS = ctrlPS;
		pCategories = creerPCategories();
		pElements = creerPElements();
		
		// Bordure du panneau Selection
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		
		this.setLayout(new BorderLayout());
		
		verifierNbreCategories();
		
		this.add(pElements, BorderLayout.CENTER);
	}
	
	private JPanel creerPCategories()
	{
		JPanel panneau = initPanneau("Catégories");
		
		return panneau;
	}
	
	private JPanel creerPElements()
	{
		JPanel panneau = initPanneau("Élements");
		
		
		return panneau;
	}
	
	private JPanel initPanneau(String titre)
	{
		JPanel panneau = new JPanel();
		// Bordure des panneaux internes
		Border b = BorderFactory.createLoweredBevelBorder();
		panneau.setBorder(new TitledBorder(b, titre));
		
		return panneau;
	}

	public void updateCategories(ArrayList<String> categories)
    {
		pCategories.removeAll();
		categorieSelectionnee = null;
	    for (String categorie : categories)
	    {
	    	JButton bCategorie = new JButton(categorie);
	    	bCategorie.addActionListener(new ActionListener(){

				@Override
                public void actionPerformed(ActionEvent arg0)
                {
					JButton boutonClique = ((JButton)arg0.getSource());
	                if (categorieSelectionnee != null)
	                {
	                	categorieSelectionnee.setEnabled(true);
	                }
	                
	                categorieSelectionnee = boutonClique;
	                boutonClique.setEnabled(false);
	                ctrlPS.selectionnerCategorie(boutonClique.getText());
                }
	    		
	    	});
	    	pCategories.add(bCategorie);
	    }
	    
	    verifierNbreCategories();
	    pCategories.revalidate();
	    pCategories.repaint();
	}
	
	private void verifierNbreCategories()
	{
		int nbreCategories = ctrlPS.getNbreCategories();
		if (nbreCategories > 1)
		{
			this.add(pCategories, BorderLayout.NORTH);
		}
	}

	public void afficherElements(ArrayList<SelectionElement> elements)
    {
		pElements.removeAll();
	    for (SelectionElement element : elements)
	    {
	    	JButton bElement = new JButton(element.getIcone());
	    	bElement.setName(element.getNom());
	    	pElements.add(bElement);
	    }
	    
	    pElements.revalidate();
	    pElements.repaint();
    }
}
