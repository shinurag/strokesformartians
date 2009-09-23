package se.combitech.strokesformartians.dancing;

import java.util.Vector;

import android.content.Intent;
import android.graphics.Bitmap;
/**
 * This class represents a figure and contains the necessary properties
 * to present it and extends an Intent in order to be able to pass it 
 * between the different activities.
 *  
 * @author Combitech
 *
 */
public class MartianProperty extends Intent
{
	public static final int INVALID_TEXTURE_ID = -1;
	
	private String name;
	
	private Vector<Integer> m_textureIds = new Vector<Integer>();
	
	private Bitmap bitmap;
	
	/**
	 * 
	 */
	public MartianProperty()
	{
		
	}
	
	/**
	 * 
	 * @param name	property name
	 */
	public MartianProperty(String name)
	{
		this.name = name;		
	}
	
	/**
	 * Adds a texture ID.
	 * 
	 * @param id	ID of texture to be added.
	 */
	public void addTexture(int id)
	{
		m_textureIds.add( new Integer(id) );		
	}
	
	/**
	 * Clears all stored texture IDs.
	 */
	public void clearTextures()
	{
		m_textureIds.clear();
	}
	/**
	 * Returns the name of this property.
	 * 
	 * @return 	property name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Returns the texture ID on the specified index.
	 * 
	 * @param index		index of texture to be returned
	 * @return			ID of texture if valid index,
	 *                  otherwise INVALID_TEXTURE_ID
	 */
	public int getTextureByIndex(int index)
	{
		/* Check that specified index is within valid range */
		if ( (index >= 0) && (index < m_textureIds.size()) )
		{
			return m_textureIds.get(index).intValue();			
		}
		
		return INVALID_TEXTURE_ID;		
	}
	
	/**
	 * Returns the texture with the specified ID.
	 * @param id
	 * @return
	 */
	public int getTexture(int id)
	{
		for (Integer tmpId : m_textureIds)
		{
			if (tmpId.intValue() == id)
			{
				return id;
			}
		}
		return INVALID_TEXTURE_ID;		
	}
	
	/**
	 * Returns an array of the texture IDs.
	 * 
	 * @return	texture IDs
	 */
	public int[] getTextures()
	{
		int[] textureIds = new int[textures()];
		int index = 0;
		for (Integer id : m_textureIds)
		{
			textureIds[index] = id;		
			index++;
		}
		return textureIds;	
	}
	
	/**
	 * Removes the texture with the specified ID.
	 * 
	 * @param id	ID of texture to be removed
	 * @return		true if texture was removed, otherwise false.
	 */
	public boolean removeTexture(int id)
	{
		return m_textureIds.remove( Integer.valueOf(id) );	
	}
	
	/**
	 * Removes the texture ID on the specified index.
	 * 
	 * @param index		index of texture to be removed
	 * @return			ID of removed texture if valid 
	 * 					index, otherwise INVALID_TEXTURE_ID		
	 */
	public int removeTextureByIndex(int index)
	{
		/* Check that specified index is within valid range */
		if ( (index >= 0) && (index < m_textureIds.size()) )
		{
			return m_textureIds.remove(index).intValue();			
		}
		return INVALID_TEXTURE_ID;
	}

	/**
	 * Sets the name of this property.
	 * 
	 * @param name	property name
	 */
	public void setName(String name)
	{
		this.name = name;
		
	}
	
	/**
	 * Returns the number of texture IDs.
	 * 
	 * @return number of texture IDs.
	 */
	public int textures() 
	{
		return m_textureIds.size();
	}
	
	public void setBitmap(Bitmap bitmap)
	{
		this.bitmap = bitmap;		
	}

	public Bitmap getBitmap() 
	{
		return bitmap;
	}
	
}
