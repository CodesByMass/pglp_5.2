package com.main;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Implémentation du JDBC avec DAO.
 *
 * @author Mass'
 *
 */
public class PersonnelJDBC implements DAO<Personnel> {

  private static String dburl = DbConn.dburl;

  @Override
  public void create(Personnel t) throws Exception {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("INSERT INTO Personnel (nom, prenom, "
          + "date" + ",fonction)" + "VALUES " + "( ?, ? , ? , ? )");
      prepare.setString(1, t.getNom());
      prepare.setString(2, t.getPrenom());
      prepare.setString(3, t.getBirthDate().toString());
      prepare.setString(4, t.getFonction());
      int result = prepare.executeUpdate();
      assert result == 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Personnel t) throws FileNotFoundException {
    // TODO Auto-generated method stub
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement(
          "UPDATE Personnel " + "SET" + " fonction = ?, " + "date" + " = ? WHERE nom = ?");
      prepare.setString(1, t.getFonction());
      prepare.setString(2, t.getBirthDate().toString());
      prepare.setString(3, t.getNom());
      int result = prepare.executeUpdate();
      assert result == 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void delete(Personnel t) throws FileNotFoundException {
    // TODO Auto-generated method stub

  }

  @Override
  public Personnel read(String s) {
    // TODO Auto-generated method stub
    return null;
  }

}
