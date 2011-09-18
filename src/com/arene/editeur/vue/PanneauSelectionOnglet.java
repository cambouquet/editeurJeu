package com.arene.editeur.vue;

import javax.swing.JPanel;

import com.arene.editeur.modele.SelectionOngletModelRequetes;

@SuppressWarnings("serial")
public class PanneauSelectionOnglet extends JPanel
{
	private SelectionOngletModelRequetes ongletModel;
	
	public PanneauSelectionOnglet(SelectionOngletModelRequetes ongletModel)
	{
		this.ongletModel = ongletModel;
	}
}
