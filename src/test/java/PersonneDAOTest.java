import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import com.main.Personnel;
import com.main.Personnel.Builder;
import com.main.PersonnelDAO;
import com.main.PhoneNumber;

public class PersonneDAOTest {

  private Personnel p, p2;
  private PersonnelDAO dao;

  @Before
  public void setUp() throws Exception {
    Builder b1 =
        new Builder("Selmi", "Massyl", "Informaticien").addBirthDate(LocalDate.parse("1998-09-07"))
            .addNumber(new PhoneNumber("Mobile", "00000000000"))
            .addNumber(new PhoneNumber("Domicile", "000000000"));
    p = b1.build();

    Builder b2 =
        new Builder("Yumi", "Chastan", "Avocate").addBirthDate(LocalDate.parse("1999-12-06"))
            .addNumber(new PhoneNumber("Mobile", "11111111111"))
            .addNumber(new PhoneNumber("Domicile", "2222222"));
    p2 = b2.build();
    dao = new PersonnelDAO();
  }

  @Test
  public void testWrite() throws Exception {
    dao.create(p);
    assertTrue((new File(p.getNom() + ".indiv")).exists());
  }

  @Test(expected = FileNotFoundException.class)
  public void testUpdate() throws Exception {
    dao.update(p2);
  }

  @Test
  public void testRead() throws Exception {
    dao.create(p);
    assertTrue(dao.read(p.getNom() + ".indiv").getNom().equals(p.getNom()));
  }

  @Test
  public void testReadBirthDate() throws Exception {
    dao.create(p);
    assertTrue(dao.read(p.getNom() + ".indiv").getBirthDate().equals(p.getBirthDate()));

  }

  @Test(expected = FileNotFoundException.class)
  public void testDelete() throws FileNotFoundException {
    dao.delete(p2);
  }
}
