package fr.eseo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnexionBDD {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3308/maven?user=somanager&password=network&useSSL=false";
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
			Connection conn = DriverManager.getConnection(url);
			System.out.println("Connexion effectuée");
			
			java.sql.Statement stat = conn.createStatement();
			ResultSet res = stat.executeQuery("SELECT * FROM ville_france ");
			/*ResultSet res = stat.executeQuery("SELECT * FROM ville_france WHERE Code_postal LIKE '49%'");*/
			System.out.println("Code_commune_INSEE - Nom_commune - Code_postal - Libelle_acheminement - Ligne_5 - Lattitude - Longitude");
			System.out.println("****************************************************************************************************************");
			while(res.next())
				System.out.println(res.getInt(1)+" - "+res.getString(2)+" - "+res.getInt(3)+" - "+res.getString(4)+" - "+res.getString(5)+" - "+res.getFloat(6)+" - "+res.getFloat(7));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
