import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import com.main.Personnel;
import com.main.Personnel.Builder;
import com.main.PhoneNumber;

public class PersonnelTest {

  private Personnel p;

  @Before
  public void setUp() throws Exception {
    Builder b1 =
        new Builder("Selmi", "Massyl", "Informaticien").addBirthDate(LocalDate.parse("1998-09-07"))
            .addNumber(new PhoneNumber("Mobile", "00000000000"))
            .addNumber(new PhoneNumber("Domicile", "000000000"));
    p = b1.build();
  }

  @Test
  public void testPersonnelNom() {
    assertTrue(p.getNom().compareTo("Selmi") == 0);
  }

  @Test
  public void testPersonnelPrenom() {
    assertTrue(p.getPrenom().compareTo("Massyl") == 0);
  }

  @Test
  public void testPersonnelbirthDate() {
    assertTrue(p.getBirthDate().equals(LocalDate.parse("1998-09-07")));
  }

}
