package com.arene.editeur.modele;

import java.util.ArrayList;

public class SelectionCategorie
{
	private ArrayList<SelectionElement> elements = new ArrayList<SelectionElement>();
	private String nom;
	private String id = "0";
	
	public SelectionCategorie(String nom, String id, ArrayList<SelectionElement> elements)
	{
		this.nom = nom;
		this.id = id;
		this.elements = elements;
	}
	
	public SelectionCategorie(String nom, ArrayList<SelectionElement> elements)
	{
		this(nom, "0", elements);
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public ArrayList<SelectionElement> getElements()
	{
		return this.elements;
	}
	
	public SelectionElement getElement(String nom)
	{
		SelectionElement returnElement = null;
		for (SelectionElement element : elements)
		{
			if (element.getNom().equals(nom))
			{
				returnElement = element;
			}
		}
		
		return returnElement;
	}
}
