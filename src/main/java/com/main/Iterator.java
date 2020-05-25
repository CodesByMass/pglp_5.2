package com.main;

/**
 *
 * @author Mass'
 *
 * @param <PrintPersonnel> l'interface Composite.
 */
@SuppressWarnings("hiding")
public interface Iterator<PrintPersonnel> {
  boolean hasNext();

  PrintPersonnel next();

}
