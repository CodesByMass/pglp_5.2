package com.main;

/**
 * La Factory.
 *
 * @author Mass'
 *
 */
public class DAOFactory {


  /**
   * Appelle le constructeur du DAO de la classe Personnel.
   *
   * @see Personnel#Personnel(com.main.Personnel.Builder)
   *
   * @return Le DAO de la classe Personnel
   */
  public static DAO<Personnel> getPersonnelDAO() {
    return new PersonnelDAO();
  }

  /**
   * Appelle le constructeur du DAO de la classe Composite Personnel.
   *
   * @see CompositePersonnel#CompositePersonnel()
   *
   * @return Le DAO de la classe CompositePersonnel
   */
  public static DAO<CompositePersonnel> getCompositePersonnel() {
    return new CompositePersonnelDAO();
  }
}
