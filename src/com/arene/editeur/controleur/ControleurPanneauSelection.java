package com.arene.editeur.controleur;

import java.util.ArrayList;

import com.arene.editeur.modele.ElementDeSelection;
import com.arene.editeur.vue.PanneauSelectionElement;

public class ControleurPanneauSelection
{

	private ArrayList<ArrayList<ElementDeSelection>> elementsRaw;
	private ArrayList<ArrayList<PanneauSelectionElement>> elementsVue;
	
	public void addElementsOnglet(ArrayList<ElementDeSelection> elementsOnglet)
	{
		elementsRaw.add(elementsOnglet);
		
		ArrayList<PanneauSelectionElement> elementsVueOnglet = new ArrayList<PanneauSelectionElement>();
		for (ElementDeSelection elementRaw : elementsOnglet)
		{
			elementsVueOnglet.add(new PanneauSelectionElement(elementRaw));
		}
		
		elementsVue.add(elementsVueOnglet);
	}
	
	public ArrayList<PanneauSelectionElement> getElements(int numeroOnglet)
    {
	    return elementsVue.get(numeroOnglet);
    }

}
