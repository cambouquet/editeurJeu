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
	private ControleurEditeurSprites parentControleur;
	private ESSpriteCarac pCarac;
	private SpriteTest sprite;
	private Properties types;

	public ControleurESSpriteCarac(File dossierProjet,
	        ControleurEditeurSprites parentControleur)
	{
		this.parentControleur = parentControleur;
		types = new Properties();
		try
		{
			File fichierConfig =
			        new File(dossierProjet.getCanonicalPath()
			                + "/config/types.config");

			if (fichierConfig.exists())
			{
				types = FileTools.readConfig(fichierConfig);
			}
		}
		catch (IOException e)
		{
			System.err
			        .println("Erreur lors de l'accèss au chemin du dossier projet dans ControleurESSpriteCarac().");
		}
		pCarac = new ESSpriteCarac(this);
	}

	public void selectionnerSprite(SpriteTest spriteSelectionne)
	{
		this.sprite = spriteSelectionne;
		pCarac.selectionnerSprite(sprite);
	}

	public ESSpriteCarac getPanneau()
	{
		return this.pCarac;
	}

	@SuppressWarnings("rawtypes")
    public void getTypes(JComboBox cbType)
	{
		cbType.addItem("");
		Enumeration e = types.elements();
		while (e.hasMoreElements())
		{
			cbType.addItem(((String) e.nextElement()).substring(3));
		}
		types.values();
	}

	public void sauverProprietes()
	{
		String typeSprite = sprite.getType();
		if (!typeSprite.isEmpty())
		{
			parentControleur.sauverProprietes();
		}
		else
		{
			System.err.println("Pas de type sélectionné");
		}

	}
}
