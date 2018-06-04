package tests;
import scamChecker.AbstractBDController;
import scamChecker.BDController;
import org.junit.jupiter.api.*;
 /*
    As it is a prototype, we are using the values that we know the database should return instead of asking the
    database for the exact value each time we run the test, cause we have not implemented those methods.
 */

class TestScamChecker {

    static private AbstractBDController globaltest;
    private static String user = "root";
    private static String password = "";

    private static int size;
    private static int sizeClementine;
    private static int sizeIn;
    private static int sizeIn2;

    @BeforeAll
    static void setUp() throws AbstractBDController.ExceptionInjection {
        globaltest = new BDController(user, password);
        size = globaltest.allData()[0].length;
        sizeClementine = globaltest.searchForAuthor("clementine", true)[0].length;
        sizeIn =  globaltest.searchForCoincidence("in", false, false)[0].length;
        sizeIn2 =  globaltest.searchForCoincidence("\"in\"", false, false)[0].length;
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test completado");
    }

    @Test
    public void testInitialize() {
        System.out.println("Testing initialize() which starts the connection to the database");
        try {
            AbstractBDController testing = new BDController(user, password);
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
            /*
            Our database has 105 data entries for the scams
            For the search engine we read 5 fields from each scam
            The return of allData() is a 2D array of 5 rows with <number of entries> columns
            */
        System.out.println("Testing allData() which returns the total entries of the database; we expect 105 results");
        for (int i = 0; i < 5; i++) {
            Assertions.assertEquals(size, globaltest.allData()[i].length);
        }
    }


    @Test
    public void testAuthorSearch() {
            /*
            Our database has 105 data entries for the scams
            For the search engine we read 5 fields from each scam
            The return of allData() is a 2D array of 5 rows with <number of entries with author x> columns
            */
        System.out.println("Testing searchForAuthor() with user clementine; we expect 2 results");
        try {
            for(int i=0; i<5; i++) {
                Assertions.assertEquals(sizeClementine, globaltest.searchForAuthor("clementine", true)[i].length);
            }
        }catch(Exception e){
            Assertions.fail("Exception thrown");
        }
    }
    @Test
    public void testCoincidenceSearch(){
            /*
            Our database has 105 data entries for the scams
            For the search engine we read 5 fields from each scam
            The return of allData() is a 2D array of 5 rows with <number of entries with x or "x" in the text> columns
            */
        System.out.println("Testing searchForCoincidence() with word in (in and \"in\" for the different types of search; we expect 85 and 26 results respectively");
        try{
            for(int i=0; i<5; i++)
            {
                Assertions.assertEquals(sizeIn,globaltest.searchForCoincidence("in",true,false)[i].length);
                Assertions.assertEquals(sizeIn2,globaltest.searchForCoincidence("\"in\"",true,false)[i].length);
            }
        }catch(Exception e){
            Assertions.fail("Exception thrown");
        }
    }
}