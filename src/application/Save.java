package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static application.Constants.*;

public class Save {
  public void printArray(int[][] arr) {
    for (int i = 0; i < 40; i++) {
      for (int j = 0; j < 10; j++) {
        System.out.print(arr[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public void saveArrayToFile(int[][] firstField, int[][] secondField, int[][] firstBattle,
      int[][] secondBattle, String path) {
    try {
      BufferedWriter bufWriter = new BufferedWriter(new FileWriter(path));
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          bufWriter.write(String.valueOf(firstField[j][i]) + " ");
        }
        bufWriter.newLine();
      }

      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          bufWriter.write(String.valueOf(secondField[j][i]) + " ");
        }
        bufWriter.newLine();
      }

      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          bufWriter.write(String.valueOf(firstBattle[j][i]) + " ");
        }
        bufWriter.newLine();
      }
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          bufWriter.write(String.valueOf(secondBattle[j][i]) + " ");
        }
        bufWriter.newLine();
      }
      bufWriter.flush();
      bufWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public int[][] loadArrayFromFile(String path, int key) {
    int[][] read = new int[40][40];
    int[][] firstField = new int[SIZE][SIZE];
    int[][] secondField = new int[SIZE][SIZE];
    int[][] firstBattle = new int[SIZE][SIZE];
    int[][] secondBattle = new int[SIZE][SIZE];
    try (BufferedReader bufReader = new BufferedReader(new FileReader(path))) {
      for (int i = 0; i < 40; i++) {
        String[] strArr = bufReader.readLine().trim().split(" ");
        for (int j = 0; j < 10; j++) {
          read[i][j] = Integer.parseInt(strArr[j]);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        firstField[i][j] = read[i][j];
      }
    }

    for (int i = 0; i < SIZE; i++) {
      for (int j = 10; j < SIZE * 2; j++) {
        secondField[i][j] = read[i][j];
      }
    }

    for (int i = 0; i < SIZE; i++) {
      for (int j = 10; j < SIZE * 3; j++) {
        firstBattle[i][j] = read[i][j];
      }
    }

    for (int i = 0; i < SIZE; i++) {
      for (int j = 10; j < SIZE * 4; j++) {
        secondBattle[i][j] = read[i][j];
      }
    }
    if (key == ONE) {
      return firstField;
    }
    if (key == TWO) {
      return secondField;
    }
    if (key == THREE) {
      return firstBattle;
    }
    if (key == FOUR) {
      return secondBattle;
    }
    return read;
  }
}
