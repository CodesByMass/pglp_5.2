package com.main;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * DAO des groupes de personnel.
 *
 * @author Mass'
 *
 */
public class CompositePersonnelDAO extends Serialization<CompositePersonnel>
    implements DAO<CompositePersonnel> {

  /**
   * Création de fichier.
   *
   * @see Serialization#writeFile(java.io.Serializable, String)
   */
  @Override
  public void create(CompositePersonnel t) throws Exception {
    writeFile(t, t.getNomGroupe() + ".groupe");

  }

  /**
   * Modifiication d'un groupe.
   *
   * @see Serialization#updateFile(java.io.Serializable, String)
   */
  @Override
  public void update(CompositePersonnel t) throws FileNotFoundException {
    File tempFile = new File(t.getNomGroupe() + ".groupe");
    if (!(tempFile.exists())) {
      throw new FileNotFoundException("Ce fichier n'existe pas !");
    } else {
      tempFile.delete();
      writeFile(t, t.getNomGroupe() + ".groupe");
    }


  }

  /**
   * Suppression d'un groupe.
   */
  @Override
  public void delete(CompositePersonnel t) throws FileNotFoundException {
    File tempFile = new File(t.getNomGroupe() + ".groupe");
    if (tempFile.exists()) {
      tempFile.delete();
    } else {
      throw new FileNotFoundException("Cet objet n'existe pas");
    }


  }

  /**
   * Lecture de fichier de groupe.
   *
   * @see Serialization#readFile(String)
   */
  @Override
  public CompositePersonnel read(String s) {
    return readFile(s);
  }

}
