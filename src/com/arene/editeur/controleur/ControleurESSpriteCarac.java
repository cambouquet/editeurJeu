package com.arene.editeur.controleur;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JComboBox;

import com.arene.editeur.modele.SpriteTest;
import com.arene.editeur.utils.file.FileTools;
import com.arene.editeur.vue.ESSpriteCarac;

public class ControleurESSpriteCarac
{
	private ESSpriteCarac pCarac;
	private File dossierProjet;
	
	public ControleurESSpriteCarac(File dossierProjet)
	{
		this.dossierProjet = dossierProjet;
		pCarac = new ESSpriteCarac(this);
	}
	
	public void selectionnerSprite(SpriteTest spriteSelectionne)
    {
	    pCarac.selectionnerSprite(spriteSelectionne);
    }

	public ESSpriteCarac getPanneau()
    {
	    return this.pCarac;
    }

	public void getTypes(JComboBox cbType)
    {
		cbType.addItem("");
		try
        {
	    	File fichierConfig = new File(dossierProjet.getCanonicalPath() + "/config/types.config");
	        	    	
	    	if (fichierConfig.exists())
	        {
	        	Properties prop = FileTools.readConfig(fichierConfig);
	        	Enumeration<Object> e = prop.elements();
	        	while (e.hasMoreElements())
	        	{
	        		cbType.addItem(e.nextElement());
	        	}
	        	prop.values();
	        }
        }
        catch (IOException e)
        {
        	System.err
	        .println("Erreur lors de l'accèss au chemin du dossier projet dans ControleurESSpriteCarac.getTypes().");
        }
    }

}
