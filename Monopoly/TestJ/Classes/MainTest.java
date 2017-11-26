package Classes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest extends Main {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testCheck() throws Exception {
        String [] arr = new String[2];
        arr[0] = "Mert";
        arr[1] = "Rümeysa";
        assertEquals(true, check(arr, "Ferhat"));
    }



}