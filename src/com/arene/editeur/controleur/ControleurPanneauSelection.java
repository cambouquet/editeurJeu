package com.arene.editeur.controleur;

import java.util.ArrayList;

import com.arene.editeur.modele.SelectionOngletModel;
import com.arene.editeur.modele.SelectionOngletModelRequetes;

public class ControleurPanneauSelection
{

	private ArrayList<SelectionOngletModel> onglets = new ArrayList<SelectionOngletModel>();


	public void ajouterOnglet(SelectionOngletModel onglet)
	{
		onglets.add(onglet);
	}

	public ArrayList<SelectionOngletModelRequetes> getOnglets()
	{
		ArrayList<SelectionOngletModelRequetes> ongletsVue = new ArrayList<SelectionOngletModelRequetes>();
		for (SelectionOngletModel onglet : onglets)
		{
			ongletsVue.add(onglet);
		}
		return ongletsVue;
	}

}
