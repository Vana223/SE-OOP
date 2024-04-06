package Test;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import src.Task16.src2.*;
public class MainTest {
    @Test
    public void testFormatting() {
        solver solver = new solver(10, Math.toRadians(45), 9.8);
        Result result = solver.solve(16);
        
        CartesianTableResultFactory cartesianFactory = new CartesianTableResultFactory();
        PolarTableResultFactory polarFactory = new PolarTableResultFactory();
        
        String cartesianTable = cartesianFactory.formatResultAsTable(result, 3);
        String polarTable = polarFactory.formatResultAsTable(result, 3);
        assertEquals("Expected Cartesian table", cartesianTable);
        assertEquals("Expected Polar table", polarTable);
    }
    
    @Test
    public void testSerialization() {
        solver solver = new solver(10, Math.toRadians(45), 9.8);
        Result expectedResult = solver.solve(16);
        
        String filename = "test_result.ser";
        saveResult(expectedResult, filename);
        
        Result restoredResult = restoreResult(filename);
        assertEquals("Expected restored result to be equal to expected result", expectedResult, restoredResult);
    }
    
    private static void saveResult(Result result, String filename) {
        // Реалізація методу збереження результату
    }
    
    private static Result restoreResult(String filename) {
        // Реалізація методу відновлення результату
        return null;
    }
}
