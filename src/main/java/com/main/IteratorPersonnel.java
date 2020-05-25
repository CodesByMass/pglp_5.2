package com.main;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Implémentation du pattern iterator.
 *
 * @author Mass'
 *
 */
public abstract class IteratorPersonnel implements Iterator<PrintPersonnel> {

  private CompositePersonnel groupe;

  private ArrayList<PrintPersonnel> liste;

  private int position;

  /**
   *
   * @param groupe Récupération du groupe de personnel.
   * @param liste qui va copier le groupe.
   */
  public IteratorPersonnel(CompositePersonnel groupe, ArrayList<PrintPersonnel> liste) {
    this.groupe = groupe;
    this.liste = liste;
    this.liste.addAll(groupe.getPersonnel());
    this.position = 0;

  }


  @Override
  public boolean hasNext() {
    if (position < liste.size()) {
      return true;
    }
    return false;
  }

  @Override
  public PrintPersonnel next() {
    if (!hasNext()) {
      throw new NoSuchElementException("Il n y a plus d'éléments");
    } else {
      PrintPersonnel temp = this.liste.get(position++);
      return temp;
    }
  }

  public CompositePersonnel getGroupe() {
    return groupe;
  }

  public ArrayList<PrintPersonnel> getListe() {
    return liste;
  }

  public abstract void iteratorPersonnel();

}
