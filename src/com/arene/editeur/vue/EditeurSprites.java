package com.arene.editeur.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.arene.editeur.controleur.ControleurEditeurSprites;

/**
 * Vue de l'éditeur de sprites.
 * @author Camille
 *
 */
@SuppressWarnings("serial")
public class EditeurSprites extends JFrame
{
	// Controleur
	/**
	 * Controleur de l'éditeur de sprites.
	 */
	private ControleurEditeurSprites ctrlEditeurSprites = null;

	// Modeles

	// Menu
	/**
	 * La barre de menu.
	 */
	private JMenuBar menuBar = new JMenuBar();
	
	/**
	 * Le menu Fichier.
	 */
	private JMenu menuFichier = new JMenu("Fichier");
	
	/**
	 * Le sous-menu Sauvegarder : sauvegarder la configuration du sprite en édition.
	 */
	private JMenuItem menuSave = new JMenuItem("Sauvegarder");
	
	/**
	 * Le sous-menu Quitter : Quitter l'éditeur de sprites et revenir à l'éditeur de jeu.
	 */
	private JMenuItem menuQuit = new JMenuItem("Quitter");

	/**
	 * Sélection du sprite
	 */
	private PanneauSelection panneauSelection;

	/**
	 * Sélection du sprite
	 */
	private ESSpriteCarac panneauCarac;
	
	/**
	 * Créer une nouvelle vue pour l'éditeur de sprites.
	 * 
	 * @param ctrlEditeurSprites
	 * 		Le controleur de l'éditeur de sprites.
	 * @param dossierProjet
	 * 		Le dossier racine du projet actuel.
	 */
	public EditeurSprites(ControleurEditeurSprites ctrlEditeurSprites)
	{
		this.ctrlEditeurSprites = ctrlEditeurSprites;
		initFenetre();
		initMenu();
		initPanneaux();
	}


	private void initPanneaux()
    {
		this.panneauSelection = this.ctrlEditeurSprites.creerPanneauSelection();
		this.panneauCarac = this.ctrlEditeurSprites.creerPanneauCarac();
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(panneauSelection, BorderLayout.NORTH);
		this.getContentPane().add(panneauCarac, BorderLayout.CENTER);
    }


	/**
	 * Initialise la fenêtre.
	 */
	private void initFenetre()
	{
		this.setTitle("Éditeur de sprites");
		this.setSize(new Dimension(600, 600));
		this.setMinimumSize(new Dimension(400, 400));
		this.setLocationRelativeTo(this.getParent());
	}

	/**
	 * Initialise et ajoute le menu de l'éditeur de sprites.
	 */
	private void initMenu()
	{
		menuQuit.setMnemonic('S');
		menuQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
		        KeyEvent.CTRL_MASK));
		menuQuit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				EditeurSprites.this.setVisible(false);
			}
		});

		menuSave.setMnemonic('S');
		menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
		        KeyEvent.CTRL_MASK));
		menuSave.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "Bientôt disponible :D",
				        "En contruction...",
				        JOptionPane.INFORMATION_MESSAGE);
			}

		});
		menuFichier.add(menuSave);
		menuFichier.add(menuQuit);

		menuBar.add(menuFichier);

		this.setJMenuBar(menuBar);
	}
}
