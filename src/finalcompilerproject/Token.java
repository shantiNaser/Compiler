/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalcompilerproject;

/**
 *
 * @author macbookpro
 */
public class Token
{
	public String type;
	public String name;
	public int line;
	public int ID;
	public Token()
	{
		this("");
	}
//C++ TO JAVA CONVERTER NOTE: Java does not allow default values for parameters. Overloaded methods are inserted above:
//ORIGINAL LINE: token(String s="")
	public Token(String s)
	{
		type = "";
		name = s;
		ID = 0;
	}
	public final boolean isDataType()
	{
		String[] allDatatypes = {"int", "double", "bool", "float", "char"};
		for (int i = 0; i < 5; i++)
		{
			if (this.name.equals(allDatatypes[i]))
			{
				return true;
			}
		}
		return false;
	}
}