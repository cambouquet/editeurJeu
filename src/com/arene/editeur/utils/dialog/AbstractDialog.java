package com.arene.editeur.utils.dialog;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


@SuppressWarnings("serial")
public abstract class AbstractDialog extends JDialog
{
	protected Container contentPane;
	protected JPanel buttonsPanel;
	
	protected String[] titles; // Les titre des éléments
	protected int[] groupes; // Les différents groupes
	protected boolean validated = true; // Si la boite de dialogue a été validée
	protected JButton okButton; // Le bouton de validation
	protected JButton cancelButton; // Le bouton d'annulation
	protected String textIntro = ""; // Le texte d'introduction - titre du panneau contenant les éléments

	/**
	 * Créer une nouvelle boîte de dialog personnalisée.
	 * 
	 * @param parent
	 * 			La fenêtre parente. Peut être null.
	 * @param title
	 * 			Le titre de la boîte de dialogue.
	 * @param modal
	 * 			La modalité de la fenêtre.
	 * @param sizeX
	 * 			Le nombre d'élément maximal en horizontal.
	 * @param sizeY
	 * 			Le nombre d'éléments maximum en vertical.
	 */
	public AbstractDialog(JFrame parent, String title, boolean modal)
	{
		super(parent, title, modal);
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		setButtons();
		initDialog(parent);
	}

	/**
	 * Initialise ce qui a rapport à la taille et la position de la fenêtre
	 */
	protected void initDialog(JFrame parent)
	{
		this.setLocationRelativeTo(parent);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.pack();
	}

	/**
	 * Crée les boutons et leurs actions
	 */
	protected void setButtons()
	{
		okButton = new JButton("Ok"); // Le bouton de validation
		cancelButton = new JButton("Annuler"); // Le bouton d'annulation
		this.getRootPane()
		.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				"action entree");
		this.getRootPane().getActionMap()
		.put("action entree", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				okButton.doClick();
			}
		});
		this.getRootPane()
		.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"action echap");
		this.getRootPane().getActionMap()
		.put("action echap", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				cancelButton.doClick();
			}
		});

		setOkButtonListener();
		setCancelButtonListener();

		buttonsPanel = new JPanel(new GridBagLayout());
		
		buttonsPanel.add(okButton, new GridBagConstraints(0, 0, 1, 1,
				1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(10, 10, 10, 10), 0, 0));
		buttonsPanel.add(cancelButton, new GridBagConstraints(1, 0, 1,
				1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(10, 10, 10, 10), 0, 0));
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
	}

	protected abstract void setCancelButtonListener();

	protected abstract void setOkButtonListener();

	/**
	 * Affiche la fenêtre
	 * 
	 * @return
	 * 		Les valeurs renvoyées par la fenêtre
	 */
	public boolean showDialog()
	{
		this.pack();
		this.setVisible(true);

		return validated;
	}

	/**
	 * Modifie le texte d'introduction.
	 * 
	 * @param texte
	 *        Le nouveau texte d'introduction
	 */
	public abstract void setTextIntro(String texte);

	/**
	 * Modifie le texte du bouton de validation. Ce bouton renvoie les valeurs
	 * rentrées dans les champs.
	 * 
	 * @param texte
	 *        Le nouveau texte du bouton
	 */
	public void setTextOkButton(String texte)
	{
		okButton.setText(texte);
		pack();
	}

	/**
	 * Modifie le texte du bouton d'annulation. Ce bouton renvoie des champs
	 * vides.
	 * 
	 * @param texte
	 *        Le nouveau texte du bouton
	 */
	public void setTextCancelButton(String texte)
	{
		cancelButton.setText(texte);
		pack();
	}
}
