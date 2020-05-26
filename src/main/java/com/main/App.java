package com.main;

import java.time.LocalDate;
import com.main.Personnel.Builder;

public enum App {
  DATABASE;

  private CompositePersonnel init() throws Exception {
    Builder b1 =
        new Builder("Massyl", "Selmi", "Informaticien").addBirthDate(LocalDate.parse("1998-07-09"))
            .addNumber(new PhoneNumber("Mobile", "0000000000"))
            .addNumber(new PhoneNumber("Domicile", "0654567561"));

    Builder b2 =
        new Builder("Yumi", "Chastan", "Avocate").addBirthDate(LocalDate.parse("1999-06-12"))
            .addNumber(new PhoneNumber("Mobile", "0134567364"))
            .addNumber(new PhoneNumber("Domicile", "0675634522"));

    Personnel p1 = b1.build();
    Personnel p2 = b2.build();
    CompositePersonnel cp1 = new CompositePersonnel();
    cp1.add(p1);
    cp1.add(p2);
    return cp1;
  }

  private void run(String[] args) throws Exception {
    new DbConn().createTables();
    CompositePersonnel cp1 = init();
    CompositePersonnelJDBC jdbc = (CompositePersonnelJDBC) DaoFactoryJDBC.getCompositePersonnel();
    jdbc.create(cp1);


  }

  public static void main(String[] args) throws Exception {
    DATABASE.run(args);

  }
}
