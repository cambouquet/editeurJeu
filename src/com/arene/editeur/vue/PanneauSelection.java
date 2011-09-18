package com.arene.editeur.vue;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.arene.editeur.controleur.ControleurPanneauSelection;
import com.arene.editeur.modele.SelectionOngletModelRequetes;

@SuppressWarnings("serial")
public class PanneauSelection extends JPanel
{
	public static int AFFICHAGE_ICONES = 0;
	public static int AFFICHAGE_TEXTE = 1;
	public static int AFFICHAGE_ICONE_TEXTE = 2;
	
	private ControleurPanneauSelection ctrlPanneauSelection;
	private JTabbedPane onglets = new JTabbedPane();
	private ArrayList<SelectionOngletModelRequetes> ongletsModel;

	public PanneauSelection(ControleurPanneauSelection ctrlPanneauSelection)
	{
		super();
		this.ctrlPanneauSelection = ctrlPanneauSelection;
		ongletsModel = ctrlPanneauSelection.getOnglets();
		for (SelectionOngletModelRequetes onglet : ongletsModel)
		{
			ajouterOnglet(onglet);
		}
		this.add(onglets);
	}
	
	private void ajouterOnglet(SelectionOngletModelRequetes ongletModel)
	{
		PanneauSelectionOnglet onglet = new PanneauSelectionOnglet(ongletModel);
		onglets.addTab(ongletModel.getNom(), null, onglet, ongletModel.getDescription());
	}
}
