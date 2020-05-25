package com.main;

import java.util.ArrayList;

/**
 * Itération sur un groupe en particulier.
 *
 * @author Mass'
 *
 */
public class PrintGroups extends IteratorPersonnel {

  public PrintGroups(CompositePersonnel groupe, ArrayList<PrintPersonnel> liste) {
    super(groupe, liste);
  }

  /**
   * Affiche les membres du groupe.
   */
  @Override
  public void iteratorPersonnel() {

    java.util.Iterator<PrintPersonnel> iterator = this.getListe().iterator();
    while (iterator.hasNext()) {
      iterator.next().print();
    }

  }

}
