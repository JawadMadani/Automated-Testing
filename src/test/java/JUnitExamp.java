import org.junit.*;

public class JUnitExamp {

    @BeforeClass
    public static void init(){
        System.out.println("Before Class");
    }

    @Before
    public void setUp(){
        System.out.println("Before");
    }


    @Test
    public void myFirstTest(){
        System.out.println("Test");
    }

    @After
    public void cleanUp(){
        System.out.println("After");
    }

    @AfterClass
    public static void tearDown(){
        System.out.println("After Class");
    }

}