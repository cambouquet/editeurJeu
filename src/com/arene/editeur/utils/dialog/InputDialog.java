package com.arene.editeur.utils.dialog;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings({"serial"})
public class InputDialog extends AbstractEasyDialog
{
	private String[] defaults; // Les valeurs par défaut à afficher
	private int fieldSize; // Les tailles des champs
	private JTextField[] textFields; // Liste des textfields
	private boolean[] mandatories = null;
	private JLabel[] labels;

	/**
	 * Crée une fenêtre de dialogue avec les titres demandés, les champs vides
	 * 
	 * @param parent
	 *        La fenêtre parente
	 * @param title
	 *        Le titre de la fenêtre
	 * @param modal
	 *        La modalité de la fenêtre : true - bloque l'application tant
	 *        qu'elle n'est pas fermée
	 * @param titres
	 *        Les titres de chaque TextFields. Autant de TextFieds sont créés
	 *        qu'il y a de titres.
	 */
	public InputDialog(JFrame parent, String title, boolean modal,
	        String[] titres)
	{
		super(parent, title, modal, titres);
		setElements();
	}

	/**
	 * Rajoute des valeurs par défaut pour les TextFields
	 * 
	 * @param def
	 *        Les valeurs par défaut
	 */
	public void setDefaults(String[] def)
	{
		this.defaults = def;
		for (int i = 0; i < Math.min(titles.length, defaults.length); i++)
		{
			textFields[i].setText(defaults[i]);
		}
	}

	/**
	 * Configure la taille des différents champs de saisie Les valeurs attendues
	 * sont situées entre 1 et 100. Dans le cas contraire, la taille par défaut
	 * est 8.
	 * 
	 * @param size
	 *        Les tailles voulues pour chaque champ
	 */
	public void setFieldSize(int size)
	{
		size = (size < 1 || size > 100) ? 8 : size;
		this.fieldSize = size;
		for (int i = 0; i < titles.length; i++)
		{
			textFields[i].setColumns(fieldSize);
		}

		pack();
	}

	@Override
	public void getData()
	{
		for (int i = 0; i < titles.length; i++)
		{
			returns[i] = textFields[i].getText();
		}
	}

	@Override
	protected void setElements()
	{
		labels = new JLabel[titles.length];
		textFields = new JTextField[titles.length];

		for (int i = 0; i < titles.length; i++)
		{
			labels[i] = new JLabel(titles[i]);
			textFields[i] = new JTextField(8);

			elementsPanel.add(labels[i], new GridBagConstraints(0, i + 1, 1, 1,
			        1.0, 0.0, GridBagConstraints.CENTER,
			        GridBagConstraints.BOTH, new Insets(5, 20, 5, 10), 0, 0));
			elementsPanel.add(textFields[i], new GridBagConstraints(1, i + 1,
			        1, 1, 1.0, 0.0, GridBagConstraints.WEST,
			        GridBagConstraints.NONE, new Insets(5, 10, 5, 20), 0, 0));
		}
	}

	@Override
	protected void setOkButtonListener()
	{
		okButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Boolean mandatoriesOk = checkMandatoriesData();

				if (mandatoriesOk)
				{
					getData();
					validated = true;
					setVisible(false);
				}
				else
				{
					JOptionPane
					        .showMessageDialog(
					                null,
					                "Vous n'avez pas rempli tous les champs obligatoires indiqués en rouge.",
					                "Champs obligatoires non remplis",
					                JOptionPane.ERROR_MESSAGE);
				}
			}

		});
	}

	private boolean checkMandatoriesData()
	{
		boolean mandatoriesOk = true;
		if (mandatories != null)
		{
			int i = 0;
			while (i < titles.length && mandatoriesOk)
			{
				if (mandatories[i] && textFields[i].getText().isEmpty())
				{
					mandatoriesOk = false;
				}
				i++;
			}
		}

		return mandatoriesOk;
	}

	public void setMandatories(boolean[] mandatories)
	{
		if (mandatories.length == titles.length)
		{
			this.mandatories = mandatories;
			for (int i = 0; i < titles.length; i++)
			{
				if (mandatories[i])
				{
					labels[i].setForeground(Color.RED);
				}
			}
			repaint();
		}
	}
}
