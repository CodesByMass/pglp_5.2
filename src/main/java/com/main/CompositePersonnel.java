package com.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe composite avec une collection de Personnel.
 *
 * @see Personnel
 *
 * @author Mass'
 *
 */
public class CompositePersonnel implements PrintPersonnel, Serializable, Iterable<PrintPersonnel> {

  /**
   *
   */
  private static final long serialVersionUID = 6190276188322149977L;
  private String nomGroupe;
  private ArrayList<PrintPersonnel> listPersonnel;

  public CompositePersonnel() {
    this.listPersonnel = new ArrayList<PrintPersonnel>();
    this.nomGroupe = "Uknown";
  }

  public String getNomGroupe() {
    return nomGroupe;
  }

  public CompositePersonnel(String nom, ArrayList<PrintPersonnel> liste) {
    this.nomGroupe = nom;
    this.listPersonnel = liste;
  }

  /**
   *
   * @return les membres du groupe.
   */
  public ArrayList<PrintPersonnel> getPersonnel() {
    return this.getPersonnel();
    // return ( ArrayList<InterfacePersonne>) Collections.unmodifiableList(this.personnel);

  }

  @Override
  public void print() {
    for (PrintPersonnel personnel : listPersonnel) {
      personnel.print();
    }
  }

  public void add(PrintPersonnel personnel) {
    listPersonnel.add(personnel);
  }

  public void delete(PrintPersonnel personnel) {
    if (listPersonnel.contains(personnel)) {
      listPersonnel.remove(personnel);
    } else {
      throw new IllegalArgumentException("Cet �l�ment n'existe pas");
    }
  }

  /**
   * Implémentation de la méthode itérateur.
   */
  @Override
  public Iterator<PrintPersonnel> iterator() {
    // TODO Auto-generated method stub
    return this.listPersonnel.iterator();
  }
}
