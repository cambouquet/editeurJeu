package com.arene.editeur.modele;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteTest implements ElementDeSelection
{
	private Image image;
	private String nom;
	
	public SpriteTest(File file)
	{
		try
        {
	        image = ImageIO.read(file);
	        nom = file.getName();
        }
        catch (IOException e)
        {
	        e.printStackTrace();
        }
		
	}
	
	@Override
    public Image getImage()
    {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public String getNom()
    {
	    // TODO Auto-generated method stub
	    return null;
    }

}
