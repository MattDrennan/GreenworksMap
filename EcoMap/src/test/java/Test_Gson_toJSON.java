import com.GREENWORKS.controller.ServletController;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.GREENWORKS.object.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Test_Gson_toJSON {
    @Test
    public void test() {
        ArrayList<String> location = new ArrayList<String>();
        location.add("CALL SP_showTransportation();");
        LinkedList<EcoPillar> test = ServletController.returnFiltered(location);
        EcoPillar testVal = test.getLast();
        String returnedVal = ServletController.toJsonTest(testVal), 
            expectedVal = "{" +
                "\"address\":\"Shingle Creek Trail\"," +
                "\"descr\":\"Bird Watching/Nature, Trails (bike/jogging), Walking Path (Paved)\"," +
                "\"loc_id\":37850," +
                "\"sp_id\":32," +
                "\"zip_code\":0" +
                "}";

        
        assertEquals(expectedVal, returnedVal);
    }
}
