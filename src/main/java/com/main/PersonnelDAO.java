package com.main;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Implementation du DAO pour Personnel
 *
 * @author Mass'
 *
 */
public class PersonnelDAO extends Serialization<Personnel> implements DAO<Personnel> {


  /**
   * Création de fichier.
   *
   * @see Serialization#writeFile(java.io.Serializable, String)
   */
  @Override
  public void create(Personnel t) throws Exception {
    writeFile(t, t.getNom() + ".indiv");

  }

  /**
   * Lecture de fichier.
   *
   * @see Serialization#readFile(String)
   */
  @Override
  public Personnel read(String s) {
    return readFile(s);
  }

  /**
   * Modifiication d'un employé.
   *
   * @see Serialization#updateFile(java.io.Serializable, String)
   */
  @Override
  public void update(Personnel t) throws FileNotFoundException {
    File tempFile = new File(t.getNom() + ".indiv");
    if (!(tempFile.exists())) {
      throw new FileNotFoundException("Ce fichier n'existe pas !");
    } else {
      tempFile.delete();
      writeFile(t, t.getNom() + ".indiv");
    }

  }

  /**
   * Suppression d'un employé.
   *
   * @throws FileNotFoundException
   */
  @Override
  public void delete(Personnel t) throws FileNotFoundException {
    File tempFile = new File(t.getNom() + ".indiv");
    if (tempFile.exists()) {
      tempFile.delete();
    } else {
      throw new FileNotFoundException("Cet objet n'existe pas");
    }


  }



}
