package com.arene.editeur.controleur;

import java.util.ArrayList;

import javax.swing.event.EventListenerList;

import com.arene.editeur.listener.SelectionElementListener;
import com.arene.editeur.modele.SelectionCategorie;
import com.arene.editeur.modele.SelectionElement;
import com.arene.editeur.vue.PanneauSelection;

public class ControleurPanneauSelection
{
	private final EventListenerList listeners = new EventListenerList();
	private PanneauSelection panneauSelection;
	private ArrayList<SelectionCategorie> categories = new ArrayList<SelectionCategorie>();
	private SelectionCategorie categorieSelectionnee;
	
	public ControleurPanneauSelection()
	{
		this.panneauSelection = new PanneauSelection(this);
	}
	
	public void setCategories(ArrayList<SelectionCategorie> categories)
	{
		this.categories = categories;
	}
	
	public void addCategorie(SelectionCategorie categorie)
	{
		if (!categories.contains(categorie))
		{
			this.categories.add(categorie);
		}
	}

	public void rmvCategorie(SelectionCategorie categorie)
	{
		if (categories.contains(categorie))
		{
			this.categories.remove(categorie);
		}
	}
	
	public PanneauSelection getPanneau()
	{
		return this.panneauSelection;
	}
	
	public int getNbreCategories()
	{
		return categories.size();
	}

	public void updateCategories()
    {
		ArrayList<String> categoriesNoms = new ArrayList<String>();
		
		for (SelectionCategorie categorie : categories)
		{
			categoriesNoms.add(categorie.getNom());
		}
		
	    panneauSelection.updateCategories(categoriesNoms);
	    this.categorieSelectionnee = null;
    }

	public void selectionnerCategorie(String nom)
    {
	    for (SelectionCategorie categorie : categories)
	    {
	    	if (categorie.getNom() == nom)
	    	{
	    		categorieSelectionnee = categorie;
	    	}
	    }
	    
	    panneauSelection.afficherElements(categorieSelectionnee.getElements());
    }

	public void selectionnerElement(String nom)
    {
	    SelectionElement elementSelectionne = categorieSelectionnee.getElement(nom);
	    if (elementSelectionne != null)
	    {
	    	fireElementSelectionne(elementSelectionne);
	    } else
	    {
	    	System.err.println("Élement non trouvé : " + nom);
	    }
    }
	
	public void addSelectionElementListener(SelectionElementListener listener)
	{
		listeners.add(SelectionElementListener.class, listener);
	}
	
	public void rmvSelectionElementListener(SelectionElementListener listener)
	{
		listeners.remove(SelectionElementListener.class, listener);
	}
	
	private void fireElementSelectionne(SelectionElement elementSel)
	{
		SelectionElementListener[] SEListeners = listeners.getListeners(SelectionElementListener.class);
		for (SelectionElementListener listener : SEListeners)
		{
			listener.elementSelectionne(elementSel);
		}
	}
}
