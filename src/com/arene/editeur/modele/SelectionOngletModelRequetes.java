package com.arene.editeur.modele;

import java.util.ArrayList;
import java.util.Enumeration;

public interface SelectionOngletModelRequetes
{
	public String getNom();

	public String getDescription();

	public boolean hasCategories();

	public int getAffichage();

	public Enumeration<String> getNomsCategories();

	public ArrayList<ElementDeSelection> getCategorieElements(
	        String nomCategorie);
}
