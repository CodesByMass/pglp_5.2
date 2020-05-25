package com.main;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implémentation des groupes avec le SGBD Derby.
 *
 * @see DbConn
 *
 * @author Mass'
 *
 */
public class CompositePersonnelJDBC implements DAO<CompositePersonnel> {

  /**
   * Chaine de caractere de la base de données.
   */
  private static String dburl = DbConn.dburl;



  @Override
  public void create(CompositePersonnel t) throws Exception {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("INSERT INTO Groupe VALUES (?)");
      prepare.setString(1, t.getNomGroupe());
      int result = prepare.executeUpdate();
      assert result == 1;
      // Ajouter les membres du groupe
      ArrayList<PrintPersonnel> listePersonnel = t.getPersonnel();
      for (PrintPersonnel personnel : listePersonnel) {
        prepare = conn.prepareStatement("INSERT INTO FaitPartie VALUES (?, ?)");
        prepare.setString(1, t.getNomGroupe());
        prepare.setString(2, ((Personnel) personnel).getNom());
        prepare.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /**
   * J'ai décidé de supprimer toute la liste stockée et de mettre celle en mémoire vive.
   */
  @Override
  public void update(CompositePersonnel t) throws FileNotFoundException {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      ArrayList<PrintPersonnel> newPersonnel = t.getPersonnel();
      PreparedStatement prepare =
          conn.prepareStatement("DELETE FROM FaitPartie WHERE nomGroupe = ? ");
      prepare.setString(1, t.getNomGroupe());
      int result1 = prepare.executeUpdate();
      assert result1 == 1;
      for (PrintPersonnel p : newPersonnel) {
        prepare = conn.prepareStatement("INSERT INTO FaitPartie VALUES (?, ?)");
        prepare.setString(1, t.getNomGroupe());
        prepare.setString(2, ((Personnel) p).getNom());
        prepare.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /**
   * On supprime le groupe et ses membres.
   */
  @Override
  public void delete(CompositePersonnel t) throws FileNotFoundException {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("DELETE FROM Groupe WHERE nomGroupe = ?");
      prepare.setString(1, t.getNomGroupe());
      int result = prepare.executeUpdate();
      assert result == 1;
      ArrayList<PrintPersonnel> listePersonnel = t.getPersonnel();
      for (PrintPersonnel personnel : listePersonnel) {
        prepare = conn
            .prepareStatement("DELETE FROM FaitPartie WHERE nomGroupe = ? and nomPersonnel = ? ");
        prepare.setString(1, t.getNomGroupe());
        prepare.setString(2, ((Personnel) personnel).getNom());
        int result1 = prepare.executeUpdate();
        assert result1 == 1;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /*
   * Récupère un groupe.
   */
  @Override
  public CompositePersonnel read(String s) {
    CompositePersonnel group = null;
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare =
          conn.prepareStatement("SELECT * FROM FaitPartie WHERE nomGroupe = ?");
      prepare.setString(1, s);
      ResultSet result = prepare.executeQuery();
      ArrayList<PrintPersonnel> listePersonnel = new ArrayList<PrintPersonnel>();
      PersonnelJDBC personnel = new PersonnelJDBC();
      while (result.next()) {
        listePersonnel.add(personnel.read(result.getString("nomPersonnel")));
      }
      group = new CompositePersonnel(s, listePersonnel);

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return group;
  }


}
