import com.GREENWORKS.DAO.PillarDAO;
import com.GREENWORKS.object.EcoPillar;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.LinkedList;
import java.util.ArrayList;

public class Test_DAO_ShowPillars {

    @Test
    void test() {
        PillarDAO pd = new PillarDAO();
        ArrayList<String> test = new ArrayList<String>();
        test.add("CALL SP_showGWLocations();");
        LinkedList<EcoPillar> param = pd.showSelectedPillar(test);
        String expectedVal = "EcoPillar [loc_id=26489, sp_id=31, address=1000 W Buena Vista Dr, description=Disney's Coronado Springs Resort | J-1772, zip_code=32830]",
        returnVal = param.get(0).toString();

        assertEquals(expectedVal, returnVal); //compares the expected value with the returned one
    }
}