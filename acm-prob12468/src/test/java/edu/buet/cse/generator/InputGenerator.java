package edu.buet.cse.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class InputGenerator {
  public static void main(String... args) {
    Random randomGenerator = new Random();
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < 1000; i++) {
      int m = randomGenerator.nextInt(100);
      int n = randomGenerator.nextInt(100);
      
      sb.append(m).append(" ").append(n).append("\n");
    }
    
    sb.append(-1).append(" ").append(-1).append("\n");
    
    try {
      Path path = Paths.get("/home/shamim/personal/temp/acm-inputs/acm-prob12468.txt");
      Files.write(path, sb.toString().getBytes());
    } catch (IOException ex) {
      ex.printStackTrace(System.err);
    }
  }
}