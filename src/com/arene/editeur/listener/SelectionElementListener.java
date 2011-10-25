package com.arene.editeur.listener;

import java.util.EventListener;

import com.arene.editeur.modele.SelectionElement;

public interface SelectionElementListener extends EventListener
{
	public void elementSelectionne(SelectionElement element);
}
