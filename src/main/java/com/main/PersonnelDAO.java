package com.main;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Implementation du DAO pour Personnel.
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
      if (tempFile.delete()) {
        System.out.println("Le fichier a été supprimé");
      } else {
        System.out.println("Le fichier n'a pas été supprimé");
      }
      writeFile(t, t.getNom() + ".indiv");
    }

  }

  /**
   * Suppression d'un employé.
   *
   * @throws FileNotFoundException si l'employé à supprimer n'existe pas.
   */
  @Override
  public void delete(Personnel t) throws FileNotFoundException {
    File tempFile = new File(t.getNom() + ".indiv");
    if (tempFile.exists()) {
      if (tempFile.delete()) {
        System.out.println("Le fichier a été supprimé");
      } else {
        System.out.println("Le fichier n'a pas été supprimé");
      }
    } else {
      throw new FileNotFoundException("Cet objet n'existe pas");
    }


  }



}
