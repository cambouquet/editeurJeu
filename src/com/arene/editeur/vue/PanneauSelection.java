package com.arene.editeur.vue;

import java.util.Enumeration;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.arene.editeur.controleur.ControleurPanneauSelection;

@SuppressWarnings("serial")
public class PanneauSelection extends JPanel
{
	public static int AFFICHAGE_ICONES = 0;
	public static int AFFICHAGE_TEXTE = 1;
	public static int AFFICHAGE_ICONE_TEXTE = 2;
	
	private ControleurPanneauSelection ctrlPanneauSelection;
	private JTabbedPane onglets = new JTabbedPane();

	public PanneauSelection(ControleurPanneauSelection ctrlPanneauSelection)
	{
		super();
		this.ctrlPanneauSelection = ctrlPanneauSelection;
		Enumeration<String> nomsOnglets = ctrlPanneauSelection.getNomsOnglets();
		while (nomsOnglets.hasMoreElements())
		{
			ajouterOnglet(nomsOnglets.nextElement(), true, PanneauSelection.AFFICHAGE_ICONES);
		}
		this.add(onglets);
	}
	
	public void ajouterOnglet(String titre, boolean avecCategories, int typeAffichage)
	{
		PanneauSelectionOnglet onglet = new PanneauSelectionOnglet(ctrlPanneauSelection.getElements(onglets.getTabCount()));
		onglets.addTab(titre, onglet);
	}
}
