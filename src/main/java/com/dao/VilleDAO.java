package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.blo.Ville;

public class VilleDAO  extends DAO<Ville>{
	
	private static Logger logger = Logger.getLogger(VilleDAO.class.getName());

	public VilleDAO(DAOFactory daoFactory){
		super(daoFactory);
	}
	
	protected Connection creerConnexion() throws SQLException {
		return this.getDaoFactory().getConnection();
	}
	
	public void creer(Ville ville) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		if(ville.getLigne5()==null) {
			ville.setLigne5("");
		}
		try {
			String req = "INSERT INTO Ville_france (code_commune_INSEE, nom_commune, code_postal, libelle_acheminement, ligne_5, latitude, longitude) "
						+ "VALUES ('"+ville.getCodeCommuneINSEE()+"', '"+ville.getNomCommune()+"', '"+ville.getCodePostal()+"', '"+ville.getLibelleAcheminement()+"', '"+ville.getLigne5()+"', '"+ville.getLatitude()+"', '"+ville.getLongitude()+"')";
			connection = this.creerConnexion();
			preparedStatement = connection.prepareStatement(req);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.WARN, "Échec de la création de l'objet, aucune ligne ajoutée dans la table.", e);
		} finally {
			fermetures(preparedStatement, connection);
		}
	}

	/**
	 * Trouve les villes comprenant le bon code_commune_INSEE et nom_commune et code_postal
	 */
	@Override
	public List<Ville> trouver(Ville objet) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Ville> liste = new ArrayList<>();
		try {
			connection = this.creerConnexion();
			String req = "SELECT * FROM Ville_france WHERE code_commune_INSEE='"+objet.getCodeCommuneINSEE()+"'";
			preparedStatement = connection.prepareStatement(req);
			System.out.println(req);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				liste.add(recupererVille(resultSet));
			}
			
		} catch (SQLException e) {
			logger.log(Level.WARN, "Échec de la recherche de l'objet, aucune ligne trouvée dans la table.", e);
		} finally {
			fermetures(preparedStatement, connection, resultSet);
		}
		return liste;
	}

	public void modifier(String idVille, Ville objet) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.creerConnexion();
			
			boolean firstParam = true;
			String req = "UPDATE `ville_france` SET ";
			
			if(objet.getCodeCommuneINSEE()!=null) {
				if(!firstParam) {
					req += ", ";
				}else {
					firstParam=false;
				}
				req += "code_commune_INSEE='"+objet.getCodeCommuneINSEE()+"'";
			}
			if(objet.getNomCommune()!=null) {
				if(!firstParam) {
					req += ", ";
				}else {
					firstParam=false;
				}
				req += "nom_commune='"+objet.getNomCommune()+"'";
			}
			if(objet.getCodePostal()!=null) {
				if(!firstParam) {
					req += ", ";
				}else {
					firstParam=false;
				}
				req += "code_postal='"+objet.getCodePostal()+"'";
			}
			if(objet.getLibelleAcheminement()!=null) {
				if(!firstParam) {
					req += ", ";
				}else {
					firstParam=false;
				}
				req += "libelle_acheminement='"+objet.getLibelleAcheminement()+"'";
			}
			if(objet.getLigne5()!=null) {
				if(!firstParam) {
					req += ", ";
				}else {
					firstParam=false;
				}
				req += "ligne_5='"+objet.getLigne5()+"'";
			}
			if(objet.getLongitude()!=null) {
				if(!firstParam) {
					req += ", ";
				}else {
					firstParam=false;
				}
				req += "longitude='"+objet.getLongitude()+"'";
			}
			if(objet.getLatitude()!=null) {
				if(!firstParam) {
					req += ", ";
				}else {
					firstParam=false;
				}
				req += "latitude='"+objet.getLatitude()+"'";
			}
			
			req += " WHERE code_commune_INSEE='"+idVille+"'";
			
			System.out.println(req);
			preparedStatement = connection.prepareStatement(req);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.log(Level.WARN, "Échec de la maj de l'objet, aucune ligne modifiée dans la table.", e);
		} finally {
			fermetures(preparedStatement, connection);
		}
	}
	
	/**
	 * Supprimes les villes comprenant le bon code_commune_INSEE et nom_commune et code_postal
	 */
	@Override
	public void supprimer(Ville objet) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = this.creerConnexion();
			String req = "DELETE FROM Ville_france WHERE code_commune_INSEE='"+objet.getCodeCommuneINSEE()+"'";
			System.out.println(req);
			preparedStatement = connection.prepareStatement(req);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.log(Level.WARN, "Échec de la recherche de l'objet, aucune ligne trouvée dans la table.", e);
		} finally {
			fermetures(preparedStatement, connection);
		}
	}

	@Override
	public List<Ville> lister() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Ville> liste = new ArrayList<>();
		try {
			connection = this.creerConnexion();
			preparedStatement = connection.prepareStatement("SELECT * from Ville_france;");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				liste.add(recupererVille(resultSet));
			}
			
		} catch (SQLException e) {
			logger.log(Level.WARN, "Échec de la recherche de l'objet, aucune ligne trouvée dans la table.", e);
		} finally {
			fermetures(preparedStatement, connection);
		}
		return liste;
	}

	public static Ville recupererVille(ResultSet resultSet) throws SQLException {
		Ville ville = new Ville();
		ville.setCodeCommuneINSEE(resultSet.getString("code_commune_INSEE"));
		ville.setNomCommune(resultSet.getString("nom_commune"));
		ville.setCodePostal(resultSet.getString("code_postal"));
		ville.setLibelleAcheminement(resultSet.getString("libelle_acheminement"));
		ville.setLigne5(resultSet.getString("ligne_5"));
		ville.setLatitude(resultSet.getString("latitude"));
		ville.setLongitude(resultSet.getString("longitude"));
		return ville;
	}

	/**
	 * Toutes les fermetures!
	 * @param preparedStatement
	 * @param connection
	 */
	private void fermetures(PreparedStatement preparedStatement, Connection connection) {
		fermeture(connection);
		fermeture(preparedStatement);
	}
	private void fermetures(PreparedStatement preparedStatement, Connection connection, ResultSet resultSet) {
		fermeture(connection);
		fermeture(preparedStatement);
		fermeture(resultSet);
	}

	public static void fermeture(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.log(Level.WARN, "Echec de la fermeture du ResultSet : " + e.getMessage(), e);
			}
		}
	}
	public static void fermeture(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARN, "Echec de la fermeture du Statement : " + e.getMessage(), e);
			}
		}
	}
	public static void fermeture(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.log(Level.WARN, "Echec de la fermeture de la connexion : " + e.getMessage(), e);
			}
		}
	}

	@Override
	public void modifier(Ville objet) {
		// TODO Auto-generated method stub
		
	}

}
