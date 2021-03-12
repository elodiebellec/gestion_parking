package fr.eni.dal;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DalUtils 
{
	public static Connection getConnection() throws Exception
	{
		//Charger le Driver
		Class.forName("com.mysql.jdbc.Driver");
		//Je me connecte � la base de donn�es
		return DriverManager.getConnection("jdbc:mysql://localhost/gestion_parking","root","");
	}
}
