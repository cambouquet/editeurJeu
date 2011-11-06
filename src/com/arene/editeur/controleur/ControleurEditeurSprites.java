package com.arene.editeur.controleur;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

import com.arene.editeur.listener.SelectionElementListener;
import com.arene.editeur.modele.ConfigProjet;
import com.arene.editeur.modele.SelectionCategorie;
import com.arene.editeur.modele.SelectionElement;
import com.arene.editeur.modele.SpriteTest;
import com.arene.editeur.utils.file.FileTools;
import com.arene.editeur.vue.ESSpriteCarac;
import com.arene.editeur.vue.EditeurSprites;
import com.arene.editeur.vue.PanneauSelection;

/**
 * Le controleur de l'éditeur de sprites.
 * 
 * @author Camille
 */
public class ControleurEditeurSprites implements SelectionElementListener
{
	/**
	 * Le dossier racine du projet.
	 */
	private File dossierProjet = null;

	/**
	 * La configuration du projet.
	 */
	private ConfigProjet configProjet;

	private SpriteTest spriteSelectionne;

	private ControleurESSpriteCarac ctrlESSC;

	private ControleurPanneauSelection ctrlPS;

	/**
	 * Constructeur avec la configuration du projet.
	 * 
	 * @param configProjet
	 *        La configuration du projet.
	 */
	public ControleurEditeurSprites(ConfigProjet configProjet)
	{
		this.configProjet = configProjet;
		this.dossierProjet = configProjet.getDossierProjet();
	}

	/**
	 * Afficher l'éditeur à l'écran.
	 */
	public void afficherEditeur()
	{
		EditeurSprites editeurSprites = new EditeurSprites(this);
		editeurSprites.setVisible(true);
	}

	/**
	 * Créer le panneau de sélection.
	 * 
	 * @return Le panneau de sélection.
	 */
	public PanneauSelection creerPanneauSelection()
	{
		ctrlPS = new ControleurPanneauSelection();
		ctrlPS.addSelectionElementListener(this);

		ctrlPS.setCategories(creerCategories());

		return ctrlPS.getPanneau();
	}

	private ArrayList<SelectionCategorie> creerCategories()
	{
		ArrayList<SelectionCategorie> categories =
		        new ArrayList<SelectionCategorie>();

		File dossierImages = new File(dossierProjet + "/images");
		File[] dossiers = dossierImages.listFiles();

		categories.add(new SelectionCategorie("nouveau",
		        creerSprites(dossierImages)));
		Properties types =
		        FileTools.readConfig(new File(dossierProjet.getPath()
		                + "/config" + "/types.config"));
		Iterator it = types.values().iterator();

		while (it.hasNext())
		{
			String type = (String) it.next();

			String[] elementsNom = type.split("_");

			if (elementsNom.length == 2)
			{
				categories.add(new SelectionCategorie(elementsNom[1],
				        elementsNom[0], creerSprites(new File(dossierImages
				                .getPath() + "/" + type))));
			}
		}

		return categories;
	}

	/**
	 * Créer les sprites pour le panneau de sélection à partir d'un dossier.
	 * 
	 * @param dossier
	 *        Le dossier contenant les images à transformer en sprites.
	 * @return Les sprites créés.
	 */
	private ArrayList<SelectionElement> creerSprites(File dossier)
	{
		ArrayList<SelectionElement> elements =
		        new ArrayList<SelectionElement>();
		File[] images = dossier.listFiles();
		for (File image : images)
		{
			if (image.isFile() && !image.getName().endsWith(".sprconf"))
			{
				elements.add(new SpriteTest(image, configProjet.getHauteur(),
				        configProjet.getLargeur()));
			}
		}

		return elements;
	}

	public ESSpriteCarac creerPanneauCarac()
	{
		ctrlESSC = new ControleurESSpriteCarac(dossierProjet, this);
		return ctrlESSC.getPanneau();
	}

	@Override
	public void elementSelectionne(SelectionElement element)
	{
		this.spriteSelectionne = (SpriteTest) element;
		SelectionCategorie categorie = ctrlPS.getCategorieSelectionnee();
		String nomCategorie = "";
		String cheminImage = dossierProjet.getPath() + "/images" + "/";
		if (!categorie.getId().equals("0"))
		{
			nomCategorie += categorie.getId() + "_" + categorie.getNom();
			cheminImage += nomCategorie + "/";

			Properties prop =
			        FileTools.readConfig((FileTools.getFileMatching(new File(
			                cheminImage), spriteSelectionne.getCode()
			                + ".*.sprconf")));
			spriteSelectionne.setType(prop.getProperty("type", ""));
		}
		cheminImage += spriteSelectionne.getFichierOrigine().getName();
		ctrlESSC.selectionnerSprite(this.spriteSelectionne);
	}

	public void updateCategories()
	{
		ArrayList<SelectionCategorie> categories = creerCategories();
		SelectionCategorie categorieSelectionnee =
		        new SelectionCategorie(null, null);
		String nomCategorieSelectionnee =
		        ctrlPS.getCategorieSelectionnee().getNom();

		for (SelectionCategorie categorie : categories)
		{
			if (categorie.getNom().equalsIgnoreCase(nomCategorieSelectionnee))
			{
				categorieSelectionnee = categorie;
			}
		}

		ctrlPS.updateCategories(categories, categorieSelectionnee);
		ctrlESSC.selectionnerSprite(null);
	}

	@Override
	public void elementDeselectionne(SelectionElement element)
	{
		this.spriteSelectionne = null;
		ctrlESSC.selectionnerSprite(null);
	}

	@SuppressWarnings("rawtypes")
	public void sauverProprietes()
	{
		/**
		 * Si l'image d'origine est directement à la racine du dossier images,
		 * alors c'est qu'il faut créer un nouveau fichier de configuration.
		 */
		boolean newFile =
		        spriteSelectionne.getFichierOrigine().getParentFile().getName()
		                .equalsIgnoreCase("images");

		/**
		 * Si le nom d'origine de l'image est identique à celui que l'on veut
		 * maintenant, il ne s'agit que d'un update du fichier de configuration.
		 */
		boolean update =
		        spriteSelectionne.getFichierOrigine().getName()
		                .equals(spriteSelectionne.getFichierNomCree() + ".png");

		/**
		 * Permet de savoir si la sauvegarde s'est bien déroulée.
		 */
		boolean sauvegardeOk = true;

		String cheminDossier = dossierProjet.getPath() + "/images";

		if (newFile || !update)
		{
			String typeSprite = spriteSelectionne.getType();
			Properties types = new Properties();
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
			Enumeration eTypes = types.elements();
			String typeDossierId = "00";
			String typeDossierNom = "";
			while (eTypes.hasMoreElements())
			{
				String type = (String) eTypes.nextElement();
				String typeNom = type.substring(3);
				if (typeSprite.equals(typeNom))
				{
					typeDossierId = type.substring(0, 2);
					typeDossierNom = type;
				}
			}
			String dernierNumero = recupererDernierNumero(typeDossierId);
			spriteSelectionne.setCode(typeDossierId + dernierNumero);

			cheminDossier += "/" + typeDossierNom + "/";
			// déplacement du fichier
			sauvegardeOk =
			        FileTools.deplacer(
			                spriteSelectionne.getFichierOrigine(),
			                new File(cheminDossier
			                        + spriteSelectionne.getFichierNomCree()
			                        + ".png"));
		}
		else
		{
			cheminDossier = spriteSelectionne.getFichierOrigine().getParent();
		}

		if (sauvegardeOk)
		{
			if (!update)
			{
				// récupération du fichier de configuration actuel et
				// destruction
				String configOriginePath =
				        spriteSelectionne.getFichierOrigine().getPath();
				configOriginePath =
				        configOriginePath.substring(0,
				                configOriginePath.lastIndexOf(".png"));
				configOriginePath += ".sprconf";
				File configOrigineFile = new File(configOriginePath);
				if (configOrigineFile.exists())
				{
					configOrigineFile.delete();
				}
			}

			FileTools.saveConfig(
			        new File(cheminDossier
			                + spriteSelectionne.getFichierNomCree()
			                + ".sprconf"), spriteSelectionne.getProprietes());
			updateCategories();
			System.out.println("fichier sauvegardé");
		}
		else
		{
			System.err.println("erreur lors du déplacement du fichier");
		}

	}

	private String recupererDernierNumero(String categorieId)
	{
		File dossierCategorie =
		        FileTools.getDirectoryBeginsWith(
		                new File(dossierProjet.getPath() + "/images"),
		                categorieId);
		File[] fichiers = dossierCategorie.listFiles();

		String dernierNumeroStr = "001";
		int dernierNumero = 0;

		for (File fichier : fichiers)
		{
			if (fichier.getName().endsWith(".png"))
			{
				int fichierNumero =
				        new Integer(fichier.getName().substring(2, 5));
				if (fichierNumero > dernierNumero)
				{
					dernierNumero = fichierNumero;
				}
			}
		}
		dernierNumero++;
		// Ajout des 0
		if (dernierNumero < 10)
		{
			dernierNumeroStr = "00" + dernierNumero;
		}
		else if (dernierNumero < 100)
		{
			dernierNumeroStr = "0" + dernierNumero;
		}
		else
		{
			dernierNumeroStr = "" + dernierNumero;
		}

		return dernierNumeroStr;
	}
}
