package com.main;

import java.io.FileNotFoundException;

/**
 * L'interface Data Access Object.
 *
 * @author Mass'
 *
 * @param <T> Le type d'objet (Personnel, Composite Personnel)
 */
public interface DAO<T> {

  /**
   * Ajout de fichier.
   *
   * @param t L'employé à ajouter.
   * @throws Exception
   */
  public void create(T t) throws Exception;

  /**
   * Modification de structure.
   *
   * @param t la structure à modifier.
   * @throws FileNotFoundException Si la structure à modifier n'existe pas.
   */
  public void update(T t) throws FileNotFoundException;

  /**
   * Suppression de fichier.
   *
   * @param t le fichier à supprimer.
   * @throws FileNotFoundException
   */
  public void delete(T t) throws FileNotFoundException;

  /**
   * Lecture de fichier
   *
   * @param s le nom du fichier
   * @return un groupe ou un employé.
   */
  public T read(String s);

}
