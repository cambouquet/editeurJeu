package com.arene.editeur.utils.file;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public abstract class FileTools
{
	/**
	 * Sauvegarde et/ou met à jour des valeurs dans un fichier de configuration.
	 * 
	 * @param file
	 *        Le fichier de configuration où enregistrer
	 * @param prop
	 *        Les propriétes à enregistrer
	 */
	public static boolean saveConfig(File file, Properties prop)
	{
		boolean saveOk = true;
		DataOutputStream dos;
		try
		{
			dos =
				new DataOutputStream(new BufferedOutputStream(
						new FileOutputStream(file)));
			try
			{
				prop.store(dos, null); // Le second argument permet
				// d'enregistrer des commentaires
			}
			catch (IOException e)
			{
				saveOk = false;
				System.err.println("IO erreur pendant l'enregistrement : "
						+ e.getMessage());
			}
			try
			{
				dos.close();
			}
			catch (IOException e)
			{
				System.err.println("IO erreur pendant la fermeture du fux : "
						+ e.getMessage());
			}
		}
		catch (FileNotFoundException e1)
		{
			saveOk = false;
			System.err.println("Fichier de configuration non trouvé : "
					+ e1.getMessage());
		}

		return saveOk;
	}
	
	/**
	 * Récupère des valeurs depuis un fichier de configuration.
	 * 
	 * @param file
	 *        Le nom du fichier de configuration
	 * @return Les paramètres lus (venant de la config si le paramètre a pu être
	 *         lu, sinon -1)
	 */
	public static Properties lireConfig(File file)
	{
		FileReader fr;
		Properties prop = new Properties();

		try
		{
			// création de l'objet de lecture
			fr = new FileReader(file);

			// Lecture des données
			prop.load(fr);

			fr.close();

		}
		catch (FileNotFoundException e)
		{
			System.err.println("Fichier de configuration non trouvé : "
					+ e.getMessage());
		}
		catch (IOException e)
		{
			System.err.println("IO erreur : " + e.getMessage());
		}

		return prop;
	}
}
