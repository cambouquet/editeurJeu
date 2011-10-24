package com.arene.editeur.modele;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class SelectionOngletModel implements SelectionOngletModelRequetes
{
	public static int AFFICHAGE_ICONES = 0;
	public static int AFFICHAGE_TEXTE = 1;
	public static int AFFICHAGE_ICONE_TEXTE = 2;

	private String nom;
	private String description;
	private int affichageType;
	private Hashtable<String, ArrayList<SelectionElement>> elements =
	        new Hashtable<String, ArrayList<SelectionElement>>();

	public SelectionOngletModel(String nom, String description)
	{
		this(nom, description, SelectionOngletModel.AFFICHAGE_ICONES);
	}

	public SelectionOngletModel(String nom, String description,
	        int typeAffichage)
	{
		this.nom = nom;
		this.description = description;
		this.affichageType = typeAffichage;
	}

	public void ajouterCategorie(String nomCategorie,
	        ArrayList<SelectionElement> elements)
	{
		this.elements.remove(nomCategorie);
		this.elements.put(nomCategorie, elements);
	}

	public Enumeration<String> getNomsCategories()
	{
		return this.elements.keys();
	}

	public ArrayList<SelectionElement> getCategorieElements(
	        String nomCategorie)
	{
		return elements.get(nomCategorie);
	}

	@Override
	public String getNom()
	{
		return nom;
	}

	@Override
	public String getDescription()
	{
		return description;
	}

	@Override
	public boolean hasCategories()
	{
		boolean hasCategories = (elements.size() > 1) ? true : false;

		return hasCategories;
	}

	@Override
	public int getAffichage()
	{
		return this.affichageType;
	}
}
