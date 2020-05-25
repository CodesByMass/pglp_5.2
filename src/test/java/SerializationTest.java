import static org.junit.Assert.assertTrue;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import com.main.CompositePersonnel;
import com.main.Personnel;
import com.main.Personnel.Builder;
import com.main.PhoneNumber;
import com.main.PrintPersonnel;


/**
 *
 * @author Mass'
 *
 */
public class SerializationTest {

  private ArrayList<PrintPersonnel> tempList;
  private CompositePersonnel cp;
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
  public void testWriteFilePersonnel() {
    boolean check = false;


    try (FileOutputStream fichier = new FileOutputStream("personnel.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fichier);) {


      oos.writeObject(p);
      oos.flush();
      check = true;
    } catch (IOException e) {
      e.printStackTrace();
      check = false;
      assertTrue(check);

    } finally {
      assertTrue(check);
    }
  }

  @Test
  public void testWriteFileGroupe() {
    tempList = new ArrayList<PrintPersonnel>();
    tempList.add(p);
    cp = new CompositePersonnel("Groupe", tempList);


    boolean check = false;


    try (FileOutputStream fichier = new FileOutputStream("personnel.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fichier);) {


      oos.writeObject(cp);
      oos.flush();
      check = true;
    } catch (IOException e) {
      e.printStackTrace();
      check = false;
      assertTrue(check);

    } finally {
      assertTrue(check);
    }
  }
}
