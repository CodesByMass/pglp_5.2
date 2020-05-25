package com.main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Connexion à la database.
 *
 * @author Mass'
 *
 */
public class DbConn {

  /**
   * Chaine de connexion.
   */
  public static String dburl = "jdbc:derby:pglp;create=true";


  private static final String user = "Mass";

  private static final String password = "feu-rouge";


  /**
   * Constructeur.
   */
  public DbConn() {
    Properties connectionProps = new Properties();
    connectionProps.put("user", user);
    connectionProps.put("password", password);
  }

  /**
   * Création des tables, si non créées.
   */
  public final void createTables() {
    try {
      Connection conn = DriverManager.getConnection(dburl);
      Statement state = conn.createStatement();
      DatabaseMetaData databaseMetadata = conn.getMetaData();
      ResultSet resultSet = databaseMetadata.getTables(null, null, "" + "PERSONNEL", null);
      if (resultSet.next()) {
        state.addBatch("DROP TABLE Personnel");
      }
      resultSet = databaseMetadata.getTables(null, null, "GROUPE", null);

      if (resultSet.next()) {
        state.addBatch("DROP TABLE Groupe");
      }
      resultSet = databaseMetadata.getTables(null, null, "" + "NUMPERSONNE", null);

      if (resultSet.next()) {
        state.addBatch("DROP TABLE NumPersonne");
      }
      resultSet = databaseMetadata.getTables(null, null, "" + "FAITPARTIE", null);

      if (resultSet.next()) {
        state.addBatch("DROP TABLE FaitPartie");
      }

      // Personnel
      state.addBatch("CREATE TABLE Personnel (nom VARCHAR(255) NOT NULL , "
          + "prenom VARCHAR(255) NOT NULL , " + "date DATE DEFAULT 1970-01-01, "
          + "fonction VARCHAR(255) NOT NULL, " + "PRIMARY KEY (nom))");
      // Groupe
      state.addBatch("CREATE TABLE Groupe( " + "NomGroupe " + "VARCHAR(255) NOT NULL , "
          + "PRIMARY KEY (NomGoup))");
      // Numéros
      state.addBatch(" CREATE TABLE NumPersonne ( num VARCHAR(10) NOT NULL ,"
          + " nomPersonnel VARCHAR(255) NOT NULL, "
          + " type VARCHAR(255) NOT NULL DEFAULT 'mobile' ," + " PRIMARY KEY (num), "
          + " CONSTRAINT fk_personne FOREIGN KEY nomPersonnel REFERENCES Personnel(nom))");
      // Personnel rattaché à un groupe.
      state.addBatch("CREATE TABLE  FaitPartie( nomGroupe varchar(255) NOT NULL ,"
          + "nomPersonnel VARCHAR(255) NOT NULL , " + "PRIMARY KEY (nomPersonnel,NomGroupe), "
          + " CONSTRAINT fk_personnel FOREIGN KEY (nomPersonnel) REFERENCES Personnel(nom),"
          + " CONSTRAINT fk_groupe FOREIGN KEY (nomGroupe) REFERENCES Groupe(NomGroupe))");

      state.executeBatch();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}

