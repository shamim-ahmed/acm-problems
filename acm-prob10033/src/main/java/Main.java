import java.util.Scanner;

public class Main {
  private static final int RAM_SIZE = 1000;
  private static final int NO_OF_REGISTERS = 10;
  private static final String EMPTY_STRING = "";
  private static final int HALT_INSTRUCTION = 100;
  private static final int MODULO_BASE = RAM_SIZE;

  public static void main(String... args) {
    Scanner scanner = null;

    try {
      scanner = new Scanner(System.in);
      int numberOfCases = Integer.parseInt(scanner.nextLine());
      scanner.nextLine();

      while (numberOfCases > 0 && scanner.hasNextLine()) {
        numberOfCases--;
        readInputBlock(scanner);

        if (numberOfCases > 0) {
          System.out.println();
        }
      }
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
  }

  private static void readInputBlock(Scanner scanner) {
    int[] ram = new int[RAM_SIZE];
    int i = 0;

    while (scanner.hasNextLine()) {
      String str = scanner.nextLine().trim();

      if (str.equals(EMPTY_STRING)) {
        break;
      }

      ram[i++] = Integer.parseInt(str);
    }

    int instructionPointer = 0;
    int[] registers = new int[NO_OF_REGISTERS];
    boolean done = false;
    int count = 0;

    while (!done) {
      int instruction = ram[instructionPointer];
      count++;

      if (instruction == HALT_INSTRUCTION) {
        done = true;
      } else {
        instructionPointer = execute(instruction, instructionPointer, ram, registers);
      }
    }

    System.out.println(count);
  }

  private static int execute(final int instruction, int instructionPointer, final int[] ram, final int[] registers) {
    int input = instruction;

    int opcode = input / 100;
    input = input % 100;

    int operand1 = input / 10;
    input = input % 10;

    int operand2 = input;

    if (opcode == 0) {
      if (registers[operand2] != 0) {
        instructionPointer = registers[operand1];
      } else {
        instructionPointer++;
      }
    } else if (opcode == 2) {
      registers[operand1] = operand2;
      instructionPointer++;
    } else if (opcode == 3) {
      registers[operand1] += operand2;
      registers[operand1] %= MODULO_BASE;
      instructionPointer++;
    } else if (opcode == 4) {
      registers[operand1] *= operand2;
      registers[operand1] %= MODULO_BASE;
      instructionPointer++;
    } else if (opcode == 5) {
      registers[operand1] = registers[operand2];
      instructionPointer++;
    } else if (opcode == 6) {
      registers[operand1] += registers[operand2];
      registers[operand1] %= MODULO_BASE;
      instructionPointer++;
    } else if (opcode == 7) {
      registers[operand1] *= registers[operand2];
      registers[operand1] %= MODULO_BASE;
      instructionPointer++;
    } else if (opcode == 8) {
      int address = registers[operand2];
      registers[operand1] = ram[address] % MODULO_BASE;
      instructionPointer++;
    } else if (opcode == 9) {
      int address = registers[operand2];
      ram[address] = registers[operand1];
      instructionPointer++;
    }

    return instructionPointer;
  }
}
