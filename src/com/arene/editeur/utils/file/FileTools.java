package com.arene.editeur.utils.file;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Properties;

/**
 * Classe utilitaire pour interagir avec des fichiers.
 * 
 * @author Camille
 */
public abstract class FileTools
{
	private static final int DIRECTORY = 0;
	private static final int FILE = 1;
	
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
	public static Properties readConfig(File file)
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

	public static File getDirectoryBeginsWith(File parentDirectory, String beginning)
	{
		return getFileBeginsWithTool(parentDirectory, beginning, DIRECTORY);
	}
	
	public static File getFileBeginsWith(File parentDirectory, String beginning)
	{
		return getFileBeginsWithTool(parentDirectory, beginning, FILE);
	}
	
	public static File getFileBeginsWithTool(File parentDirectory, String beginning, int type)
	{
		File[] fichiers = parentDirectory.listFiles();
		
		File fichierTrouve = null;
		for (File fichier : fichiers)
		{
			if ((fichier.isFile() && type == FILE) || (fichier.isDirectory() && type == DIRECTORY))
			{
				if (fichier.getName().startsWith(beginning))
				{
					fichierTrouve = fichier;
				}
			}
		}
		
		return fichierTrouve;
	}

	public static File getDirectoryMatching(File parentDirectory, String regex)
	{
		return getFileMatchingTool(parentDirectory, regex, DIRECTORY);
	}
	
	public static File getFileMatching(File parentDirectory, String regex)
	{
		return getFileMatchingTool(parentDirectory, regex, FILE);
	}
	
	public static File getFileMatchingTool(File parentDirectory, String regex, int type)
	{
		File[] fichiers = parentDirectory.listFiles();

		File fichierTrouve = null;
		for (File fichier : fichiers)
		{
			if ((fichier.isFile() && type == FILE) || (fichier.isDirectory() && type == DIRECTORY))
			{
				if (fichier.getName().matches(regex))
				{
					fichierTrouve = fichier;
				}
			}
		}

		return fichierTrouve;
	}

	public static boolean copier(File source, File destination)
	{
		boolean copy = true;
		// de developpez.com
		FileChannel in = null;
		FileChannel out = null;

		try
		{
			in = new FileInputStream(source).getChannel();
			out = new FileOutputStream(destination).getChannel();

			in.transferTo(0, in.size(), out);
		}
		catch (Exception e)
		{
			copy = false;
			e.printStackTrace();
		}
		finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
				}
			}
			if (out != null)
			{
				try
				{
					out.close();
				}
				catch (IOException e)
				{
				}
			}
		}

		return copy;
	}

	public static boolean deplacer(File source, File destination)
	{
		boolean copy = source.renameTo(destination);
		if (!copy)
		{
			copy = FileTools.copier(source, destination);
		}
		
		if (copy)
		{
			source.delete();
		}
		
		return copy;
	}
}
