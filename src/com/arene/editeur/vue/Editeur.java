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

import com.arene.editeur.controleur.ControleurProjet;
import com.arene.editeur.modele.ConfigProjetRequetes;
import com.arene.editeur.utils.dialog.InputDialog;
import com.arene.editeur.utils.dialog.RadioDialog;

@SuppressWarnings("serial")
public class Editeur extends JFrame
{
	// général
	private ControleurProjet controleurProjet = null;
	private ConfigProjetRequetes configProjet = null;

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
	public Editeur(ControleurProjet controleurProjet)
	{
		this.controleurProjet = controleurProjet;
		initFenetre();
		initMenu();
	}

	/**
	 * Initialise les paramètres de la fenêtre.
	 */
	private void initFenetre()
	{
		this.setTitle("Éditeur de jeu");
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

				if (titles.length > 0)
				{

					RadioDialog dialogProjetSelection =
					        new RadioDialog(null, "Ouverture d'un projet",
					                true, titles);
					dialogProjetSelection.setTextOkButton("Ouvrir");
					dialogProjetSelection
					        .setTextIntro("Choisissez le projet à ouvrir");

					String[] results = new String[titles.length];
					boolean validated =
					        dialogProjetSelection.showDialog(results);

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

						Editeur.this.configProjet =
						        controleurProjet.ouvrirProjet(nomProjet);
						Editeur.this.setTitle("Édition du jeu " + nomProjet);
						JOptionPane.showMessageDialog(null, "Projet "
						        + nomProjet
						        + " ouvert.\nLa suite bientôt disponible :D",
						        "En construction...",
						        JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
				{
					JOptionPane
					        .showMessageDialog(
					                null,
					                "Il n'y a actuellement aucun projet.\nVous pouvez en créer un nouveau avec le menu Nouveau.",
					                "Aucun projet",
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

				String[] titles = {"Nom du projet", "Nom du jeu"};

				InputDialog dialogParam =
				        new InputDialog(null, "Création d'un nouveau jeu",
				                true, titles);
				dialogParam.setTextOkButton("Créer");
				dialogParam.setTextIntro("Configuration du nouveau jeu");
				dialogParam.setFieldSize(10);
				boolean[] mandatories = {true, true};
				dialogParam.setMandatories(mandatories);

				String[] results = new String[titles.length];
				boolean validated = dialogParam.showDialog(results);

				if (validated && !results[0].isEmpty())
				{
					Editeur.this.setTitle("Édition du projet " + results[0]
					        + " : " + results[1]);
					Editeur.this.configProjet =
					        controleurProjet
					                .creerProjet(results[0], results[1]);
					JOptionPane.showMessageDialog(null, "Projet " + results[0]
					        + " configuré pour le jeu : " + results[1] + ".\n"
					        + "Dossier projet créé à : " + Editeur.this.configProjet.getCheminRacine()
					        + "\nLa suite bientôt disponible :D", "Projet "
					        + results[0] + " créé !",
					        JOptionPane.INFORMATION_MESSAGE);
				}
				else if (validated && results[0].isEmpty())
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
				if (!controleurProjet.projetOuvert())
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
