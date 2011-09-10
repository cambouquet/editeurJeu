package com.arene.editeur.vue;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.arene.editeur.modele.ConfigProjet;
import com.arene.editeur.utils.dialog.InputDialog;
import com.arene.editeur.utils.dialog.RadioDialog;

@SuppressWarnings("serial")
public class Editeur extends JFrame
{
	// général
	private ConfigProjet configProjet = null;

	// Menu
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFichier = new JMenu("Fichier");
	private JMenuItem menuNew = new JMenuItem("Nouveau jeu");
	private JMenuItem menuOpen = new JMenuItem("Ouvrir un projet");
	private JMenuItem menuQuit = new JMenuItem("Quitter");

	private JMenu menuConfig = new JMenu("Configurer");
	private JMenuItem menuEditSprites = new JMenuItem("Sprites...");

	/**
	 * Constructeur basique d'un éditeur : créer une fenêtre et ajoute un mennu.
	 */
	public Editeur()
	{
		initFenetre();
		initMenu();
	}

	/**
	 * Initialise les paramètres de la fenêtre.
	 */
	private void initFenetre()
	{
		this.setTitle("Editeur de jeu");
		this.setMinimumSize(new Dimension(600, 600));
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Initialise le menu et ses actions.
	 */
	private void initMenu()
	{
		// Menu Fichier
		
		// Menu Quitter
		menuQuit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		menuQuit.setMnemonic('Q');
		menuQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
		        KeyEvent.CTRL_MASK));
		
		// Menu Ouvrir
		menuOpen.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				// Création et affichage de la boite de dialogue
				File dossierProjet = new File("projets");
				
				// On suppose pour le moment qu'il n'y a que des dossiers
				String[] titles = dossierProjet.list();

				RadioDialog dialogProjetSelection =
				        new RadioDialog(null, "Ouverture d'un projet", true,
				                titles);
				dialogProjetSelection.setTextOkButton("Ouvrir");
				dialogProjetSelection
				        .setTextIntro("Choisissez le projet à ouvrir");

				String[] results = new String[titles.length];
				boolean validated = dialogProjetSelection.showDialog(results);

				// Analyse des résultats
				if (validated)
				{
					String nomProjet = "";
					for (int i = 0; i < titles.length; i++)
					{
						if (!results[i].isEmpty())
						{
							nomProjet = results[i];
						}
					}

					configProjet = new ConfigProjet(nomProjet);
					configProjet.chargerConfig();
					Editeur.this.setTitle("Édition du jeu " + nomProjet);
					JOptionPane.showMessageDialog(null, "Projet "
					        + configProjet.getNom()
					        + " ouvert.\nLa suite bientôt disponible :D",
					        "En construction...",
					        JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		menuOpen.setMnemonic('O');
		menuOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
		        KeyEvent.CTRL_MASK));
		
		// Menu Nouveau
		menuNew.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				// Création et affichage de la boite de dialogue
				
				// String[] titles = {"Nom du projet",
				// "Chemin absolu du projet"};
				//
				// InputDialog dialogParam =
				// new InputDialog(null, "Création d'un nouveau jeu",
				// true, titles);
				// dialogParam.setTextOkButton("Créer");
				// dialogParam.setTextIntro("Configuration du nouveau jeu");
				// dialogParam.setFieldSize(25);
				//
				// String[] results = new String[titles.length];
				// boolean validated = dialogParam.showDialog(results);
				//
				// if (validated)
				// {
				// configProjet = new ConfigProjet(results[0], "");
				// Editeur.this.setTitle("Édition du jeu " + results[0]);
				// JOptionPane.showMessageDialog(null,
				// "Projet " +
				// configProjet.getNom()+" configuré.\nLa suite bientôt disponible :D",
				// "En construction...",
				// JOptionPane.INFORMATION_MESSAGE);
				// }

				String nomProjet =
				        JOptionPane.showInputDialog(null, "Nom du projet",
				                "Configuration du nouveau jeu",
				                JOptionPane.QUESTION_MESSAGE);
				
				
				// Analyse des résultats
				if (nomProjet != null && !nomProjet.isEmpty())
				{
					Editeur.this.setTitle("Édition du jeu " + nomProjet);
					configProjet = new ConfigProjet(nomProjet);
					configProjet.nouvelleConfig();
					JOptionPane.showMessageDialog(null,
					        "Projet " + configProjet.getNom()
					                + " configuré.\nDossier créé à : "
					                + configProjet.getCheminRacine()
					                + "\nLa suite bientôt disponible :D",
					        "Projet " + nomProjet + " créé !",
					        JOptionPane.INFORMATION_MESSAGE);
				}
				else if (nomProjet != null)
				{
					JOptionPane
					        .showMessageDialog(
					                null,
					                "Vous n'avez pas spécifié de nom pour le projet",
					                "Configuration invalide",
					                JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		menuNew.setMnemonic('N');
		menuNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
		        KeyEvent.CTRL_MASK));

		menuFichier.add(menuNew);
		menuFichier.add(menuOpen);
		menuFichier.add(menuQuit);
		menuFichier.setMnemonic('F');

		// Menu Configurer
		menuEditSprites.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (configProjet == null)
				{
					JOptionPane
					        .showMessageDialog(
					                null,
					                "Vous devez d'abord ouvrir ou créer un nouveau jeu",
					                "Pas de jeu en cours",
					                JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null,
					        "Bientôt disponible :D", "En construction...",
					        JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		menuEditSprites.setMnemonic('S');
		menuConfig.add(menuEditSprites);

		menuBar.add(menuFichier);
		menuBar.add(menuConfig);

		this.setJMenuBar(menuBar);

	}
}
