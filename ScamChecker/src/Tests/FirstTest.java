package Tests;
import ScamChecker.AbstractBDController;
import ScamChecker.BDController;
import org.junit.jupiter.api.*;
 /*
    As it is a prototype, we are using the values that we know the database should return instead of asking the
    database for the exact value each time we run the test, cause we have not implemented those methods.
 */

class FirstTest {
    static private AbstractBDController globaltest;
    private static final int NUMOFENTRIES=105;

    @BeforeAll
    static void setUp() {
        //here I must write the things I want before the test start (like user input maybe)
        globaltest = new BDController("root", "Daniel16");
    }

    @AfterEach
    void tearDown() {
        //I don't know what it is for, but it is done after the test ends, so may delete temporary elements or something?
        System.out.println("Test completado");
    }

    @Test
    public void testInitialize() {
        System.out.println("testing initialize() which initializes the connection to the database");
        try {
            AbstractBDController testing = new BDController("root", "Daniel16");
        } catch (Exception e) {
            Assertions.fail("ERROR DE INICIALIZACION");
        }
    }

    @Test
    public void testInjection() {
        System.out.println("Testing checkInjection() which detects a ';' in the search and sends an exception");
        String s1 = "string de prueba";
        String s2 = "string ; de prueba";
        try {
            globaltest.searchForAuthor(s1, true);
        } catch (Exception e) {
            Assertions.fail("Exception thrown");
        }
        try {
            globaltest.searchForAuthor(s2, true);
            Assertions.fail("Exception not thrown");
        } catch (Exception e) {
            Assertions.assertEquals("Sorry, we don't accept searches with character ;", e.getMessage());
        }
    }

    @Test
    public void testAllData() {
        System.out.println("Testing allData() which returns the total entries of the database");
        for (int i = 0; i < 5; i++) {
            Assertions.assertEquals(NUMOFENTRIES, globaltest.allData()[i].length);//retrieve.length = 105
        }
    }


    @Test
    public void testAuthorSearch() {
            /*
            Our database has 105 data entries for the scams
            For the search engine we read 5 fields from each scam
            The return of allData() is a 2D array of 5 rows with <number of entries> columns
            */
        System.out.println("testing searchForAuthor() with user clementine; we expect 2 results");
        try {
            for(int i=0; i<5; i++) {
                Assertions.assertEquals(2, globaltest.searchForAuthor("clementine", true)[i].length);
            }
        }catch(Exception e){
            Assertions.fail("Exception thrown");
        }
    }
    @Test
    public void testCoincidenceSearch(){
        System.out.println("testing searchForCoincidence() with word in (in and 'in' for the different types of search; we expect 85 and 26 results respectively");
        try{
            for(int i=0; i<5; i++)
            {
                Assertions.assertEquals(85,globaltest.searchForCoincidence("in",true,false)[i].length);
                Assertions.assertEquals(26,globaltest.searchForCoincidence("\"in\"",true,false)[i].length);
            }
        }catch(Exception e){
            Assertions.fail("Exception thrown");
        }
    }
}