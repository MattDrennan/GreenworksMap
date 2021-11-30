import com.GREENWORKS.controller.ServletController;
import com.GREENWORKS.object.EcoPillar;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.LinkedList;
import java.util.ArrayList;

public class Test_SelectedPillars {

    @Test
    void test() {
        ArrayList<String> test = new ArrayList<String>();
        test.add("Call SP_showFood();");
        LinkedList<EcoPillar> param = ServletController.returnFiltered(test);
        String expectedVal = "EcoPillar [loc_id=73275, sp_id=62, address=1517 Lake Highland Drive, description=Colonialtown Community Garden, zip_code=32803]",
        returnVal = param.get(0).toString();

        assertEquals(expectedVal, returnVal); //compares the expected value with the returned one
    }
}
