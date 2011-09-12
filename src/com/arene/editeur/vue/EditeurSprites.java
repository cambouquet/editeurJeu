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

import com.arene.editeur.controleur.ControleurEditeurSprites;

public class EditeurSprites extends JFrame
{
	// Controleur
	private ControleurEditeurSprites ctrlEditeurSprites = null;

	// Modeles

	// Menu
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFichier = new JMenu("Fichier");
	private JMenuItem menuSave = new JMenuItem("Sauvegarder");
	private JMenuItem menuQuit = new JMenuItem("Quitter");

	// Autres
	private File dossierProjet = null;

	public EditeurSprites(ControleurEditeurSprites ctrlEditeurSprites,
	        File dossierProjet)
	{
		this.ctrlEditeurSprites = ctrlEditeurSprites;
		this.dossierProjet = dossierProjet;
		initFenetre();
		initMenu();
	}

	private void initFenetre()
	{
		this.setTitle("Éditeur de sprites");
		this.setSize(new Dimension(600, 600));
		this.setMinimumSize(new Dimension(600, 600));
		this.setLocationRelativeTo(this.getParent());
	}

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
