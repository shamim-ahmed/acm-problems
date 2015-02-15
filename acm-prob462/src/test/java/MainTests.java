import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class MainTests {
  private static final String CHAR_SET_NAME = "UTF-8";
  private static final int INITIAL_SIZE = 1024;
  
  private static final int INPUT_SET = 1; 
  private static final String INPUT_FILE_NAME = String.format("/input/input%d.txt", INPUT_SET);
  private static final String EXPECTED_RESULT_FILE_NAME = String.format("/result/result%d.txt", INPUT_SET);
  
  @Test
  public void test() throws Exception {    
    InputStream inputStream = getClass().getResourceAsStream(INPUT_FILE_NAME); 
    ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream(INITIAL_SIZE);
    PrintStream outputStream = new PrintStream(byteOutStream);
    Main.processInput(inputStream, outputStream);
    
    Path expectedResultPath = Paths.get(getClass().getResource(EXPECTED_RESULT_FILE_NAME).toURI());
    String expectedResult = new String(Files.readAllBytes(expectedResultPath), CHAR_SET_NAME);
    String actualResult = byteOutStream.toString(CHAR_SET_NAME);
    
    assertEquals("output different than expected", expectedResult, actualResult);
  }
}
