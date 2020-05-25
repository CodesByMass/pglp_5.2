import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import com.main.CompositePersonnel;
import com.main.CompositePersonnelDAO;
import com.main.Personnel;
import com.main.Personnel.Builder;
import com.main.PhoneNumber;
import com.main.PrintPersonnel;

public class CompositeDAOTest {

  private ArrayList<PrintPersonnel> liste1;
  private ArrayList<PrintPersonnel> liste2;
  private Personnel p1;
  private CompositePersonnel cp1, cp2;
  private CompositePersonnelDAO dao;

  @Before
  public void setUp() throws Exception {
    this.liste1 = new ArrayList<PrintPersonnel>();
    this.liste2 = new ArrayList<PrintPersonnel>();
    Builder b1 =
        new Builder("Selmi", "Massyl", "Informaticien").addBirthDate(LocalDate.parse("1998-07-09"))
            .addNumber(new PhoneNumber("Mobile", "00000000000"))
            .addNumber(new PhoneNumber("Domicile", "000000000"));
    p1 = b1.build();
    liste1.add(p1);
    liste1.add(new Builder("Yumi", "Chastan", "Avocate").addBirthDate(LocalDate.parse("1999-06-12"))
        .addNumber(new PhoneNumber("Mobile", "11111111111"))
        .addNumber(new PhoneNumber("Domicile", "2222222")).build());
    cp1 = new CompositePersonnel("Info", liste1);
    liste2.add(new Builder("Sofia", "Selmi", "Medecin").addBirthDate(LocalDate.parse("2003-07-24"))
        .addNumber(new PhoneNumber("Mobile", "11111111111"))
        .addNumber(new PhoneNumber("Domicile", "2222222")).build());
    cp2 = new CompositePersonnel("Medecine", liste2);
    dao = new CompositePersonnelDAO();
  }

  @Test
  public void testWrite() throws Exception {
    dao.create(cp1);
    assertTrue((new File(cp1.getNomGroupe() + ".groupe")).exists());
  }

  @Test(expected = FileNotFoundException.class)
  public void testUpdate() throws Exception {
    dao.update(cp2);
  }

  @Test
  public void testRead() throws Exception {
    dao.create(cp1);
    assertTrue(dao.read(cp1.getNomGroupe() + ".groupe").getNomGroupe().equals(cp1.getNomGroupe()));
  }


  @Test(expected = FileNotFoundException.class)
  public void testDelete() throws FileNotFoundException {
    dao.delete(cp2);
  }

}
