package com.arene.editeur.vue;

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

@SuppressWarnings("serial")
public class Editeur extends JFrame
{
	// général
	String cheminRacine = null;
	
	// Menu
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFichier = new JMenu("Fichier");
	private JMenuItem menuNew = new JMenuItem("Nouveau jeu");
	private JMenuItem menuQuit = new JMenuItem("Quitter");

	private JMenu menuConfig = new JMenu("Configurer");
	private JMenuItem menuEditSprites = new JMenuItem("Sprites...");
	
	public Editeur()
	{
		initFenetre();
		initMenu();
	}
	
	private void initFenetre()
	{
		this.setTitle("Editeur de jeu");
		this.setMinimumSize(new Dimension(600, 600));
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initMenu()
	{
		// Menu Fichier
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
		menuNew.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				cheminRacine = JOptionPane.showInputDialog(null, "Chemin du projet", "Configuration du nouveau jeu", JOptionPane.QUESTION_MESSAGE);
			}
		});
		
		menuNew.setMnemonic('N');
		menuNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				KeyEvent.CTRL_MASK));
		
		menuFichier.add(menuNew);
		menuFichier.add(menuQuit);
		menuFichier.setMnemonic('F');
		
		// Menu Configurer
		menuEditSprites.addActionListener(new ActionListener()
		{
			@Override
            public void actionPerformed(ActionEvent arg0)
            {
	            if (cheminRacine == null)
	            {
	            	JOptionPane.showMessageDialog(null, "Vous devez d'abord ouvrir ou créer un nouveau jeu", "Pas de jeu en cours", JOptionPane.ERROR_MESSAGE);
	            } else
	            {
	            	JOptionPane.showMessageDialog(null, "Bientôt disponible :D", "En construction...", JOptionPane.INFORMATION_MESSAGE);
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
