import com.GREENWORKS.controller.ServletController;
import com.GREENWORKS.object.EcoPillar;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.LinkedList;

public class Test_ShowPillars {

    @Test
    void test() {
        LinkedList<EcoPillar> param = ServletController.returnAll();
        String expectedVal = "EcoPillar [loc_id=26489, sp_id=31, address=1000 W Buena Vista Dr, description=Disney's Coronado Springs Resort | J-1772, zip_code=32830]",
        returnVal = param.get(0).toString();

        assertEquals(expectedVal, returnVal); //compares the expected value with the returned one
    }
}