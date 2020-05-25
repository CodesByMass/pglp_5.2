package com.main;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Implémentation du JDBC avec DAO.
 *
 * @author Mass'
 *
 */
public class PersonnelJDBC implements DAO<Personnel> {

  /**
   * Chaine de caractere de la base de données.
   */
  private static String dburl = DbConn.dburl;

  /**
   * Ajoute un employé à la base de données. Ici, une première requète ajoute l'employé, puis une
   * boucle ajoute les possibles numéros de l'employé.
   *
   * @see Personnel
   *
   */
  @Override
  public void create(Personnel t) throws Exception {
    // Avec le try with resources, pas besoin de fermeture de connexion manuelle
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("INSERT INTO Personnel (nom, prenom, "
          + "date" + ",fonction)" + "VALUES " + "( ?, ? , ? , ? )");
      prepare.setString(1, t.getNom());
      prepare.setString(2, t.getPrenom());
      prepare.setString(3, t.getBirthDate().toString());
      prepare.setString(4, t.getFonction());
      int result = prepare.executeUpdate();
      assert result == 1;
      for (PhoneNumber temp : t.getPhoneNumbers()) {
        PreparedStatement prepareNumber = conn.prepareStatement(
            "INSERT INTO NumPersonne (num, nomPersonnel, " + "type)" + "VALUES " + "( ?, ? , ?)");
        prepareNumber.setString(1, temp.getNumber());
        prepareNumber.setString(2, t.getNom());
        prepareNumber.setString(3, temp.getType());
        int resultNumber = prepare.executeUpdate();
        assert resultNumber == 1;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   *
   * Ici, je ne modifie que la date de naissance et la fonction de l'employé.
   */
  @Override
  public void update(Personnel t) throws FileNotFoundException {

    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement(
          "UPDATE Personnel " + "SET" + " fonction = ?, " + "date" + " = ? WHERE nom = ?");
      prepare.setString(1, t.getFonction());
      prepare.setString(2, t.getBirthDate().toString());
      prepare.setString(3, t.getNom());
      int result = prepare.executeUpdate();
      if (result != 1) {
        System.out.println("Cet utilisateur n'existe pas");
      } else {
        System.out.println(" L'employé a bien été modifié");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void delete(Personnel t) throws FileNotFoundException {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("DELETE FROM Personnel WHERE nom = ?");
      prepare.setString(1, t.getNom());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println(" L'employé a bien été supprimé");
      } else {
        System.out.println("Un problème est survenu lors de la suppression");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public Personnel read(String s) {
    Personnel p = null;
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("SELECT * FROM Personnel WHERE nom = ?");
      prepare.setString(1, s);
      ResultSet result = prepare.executeQuery();
      if (result.next()) {
        p = new com.main.Personnel.Builder(result.getString("nom"), result.getString("prenom"),
            result.getString("fonction"))
                .addBirthDate(LocalDate.parse(result.getString("birthdate"))).build();
        result.close();

      } else {
        throw new SQLException("La personne recherchée n'existe pas");
      }
      // Récupérer les numéros de téléphone
      PreparedStatement prepareNumbers =
          conn.prepareStatement("SELECT * FROM NumPersonne WHERE nomPersonnel = ?");
      prepareNumbers.setString(1, s);
      ResultSet result2 = prepareNumbers.executeQuery();
      while (result2.next()) {
        p.getPhoneNumbers()
            .add(new PhoneNumber(result2.getString("num"), result2.getString("type")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return p;
  }
}


