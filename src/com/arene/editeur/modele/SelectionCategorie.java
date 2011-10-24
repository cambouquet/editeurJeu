package com.arene.editeur.modele;

import java.util.ArrayList;

public class SelectionCategorie
{
	private ArrayList<SelectionElement> elements = new ArrayList<SelectionElement>();
	private String nom;
	private int id = 0;
	
	public SelectionCategorie(String nom, int id, ArrayList<SelectionElement> elements)
	{
		this.nom = nom;
		this.id = id;
		this.elements = elements;
	}
	
	public SelectionCategorie(String nom, ArrayList<SelectionElement> elements)
	{
		this(nom, 0, elements);
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public ArrayList<SelectionElement> getElements()
	{
		return this.elements;
	}
}
