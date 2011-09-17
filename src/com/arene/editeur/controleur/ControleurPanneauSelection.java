package com.arene.editeur.controleur;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import com.arene.editeur.modele.ElementDeSelection;
import com.arene.editeur.vue.PanneauSelectionElement;

public class ControleurPanneauSelection
{

	private Hashtable<String, Hashtable<String, ArrayList<ElementDeSelection>>> elementsRaw = new Hashtable<String, Hashtable<String, ArrayList<ElementDeSelection>>>();

	/**
	 * Liste des onglets (Hashtable) contenant les différentes catégories
	 * (Hashtable) qui contiennent les éléments à afficher (ArrayList).
	 */
	private Hashtable<String, Hashtable<String, ArrayList<PanneauSelectionElement>>> elementsVue = new Hashtable<String, Hashtable<String, ArrayList<PanneauSelectionElement>>>();

	public void ajouterOnglet(String nom)
	{
		elementsRaw.put(nom, new Hashtable<String, ArrayList<ElementDeSelection>>());
		elementsVue.put(nom, new Hashtable<String, ArrayList<PanneauSelectionElement>>());
	}
	
	public void ajouterListe(String nomOnglet, String nom, ArrayList<ElementDeSelection> elements)
	{
		if (elementsVue.containsKey(nomOnglet))
		{
			elementsRaw.get(nomOnglet).put(nom, elements);
			ArrayList<PanneauSelectionElement> elementsListe = new ArrayList<PanneauSelectionElement>();
			for (ElementDeSelection element : elements)
			{
				elementsListe.add(new PanneauSelectionElement(element));
			}
			elementsVue.get(nomOnglet).put(nom, elementsListe);
		}
	}
	
	public Enumeration<String> getNomsOnglets()
	{
		return elementsVue.keys();
	}

	public Hashtable<String, ArrayList<PanneauSelectionElement>> getElements(
	        int numeroOnglet)
	{
		return elementsVue.get(numeroOnglet);
	}

}
