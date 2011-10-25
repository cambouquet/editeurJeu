package com.arene.editeur.controleur;

import com.arene.editeur.modele.SpriteTest;
import com.arene.editeur.vue.ESSpriteCarac;

public class ControleurESSpriteCarac
{
	private ESSpriteCarac pCarac;
	
	public ControleurESSpriteCarac()
	{
		pCarac = new ESSpriteCarac();
	}
	
	public void selectionnerSprite(SpriteTest spriteSelectionne)
    {
	    pCarac.selectionnerSprite(spriteSelectionne);
    }

	public ESSpriteCarac getPanneau()
    {
	    return this.pCarac;
    }

}
