package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static application.Constants.*;

public class Save {
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
    int[][] read = new int[FORTY_POSITION][FORTY_POSITION];
    int[][] firstField = new int[SIZE][SIZE];
    int[][] secondField = new int[SIZE][SIZE];
    int[][] firstBattle = new int[SIZE][SIZE];
    int[][] secondBattle = new int[SIZE][SIZE];

    try (BufferedReader bufReader = new BufferedReader(new FileReader(path))) {
      for (int i = 0; i < FORTY_POSITION; i++) {
        String[] strArr = bufReader.readLine().trim().split(" ");
        for (int j = 0; j < SIZE; j++) {
          read[i][j] = Integer.parseInt(strArr[j]);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        firstField[j][i] = read[i][j];
      }
    }

    for (int i = SIZE, ch = 0; i < SIZE * TWO; ch++, i++) {
      for (int j = 0; j < SIZE; j++) {
        secondField[j][ch] = read[i][j];
      }
    }

    for (int i = SIZE * TWO, ch = 0; i < SIZE * THREE; ch++, i++) {
      for (int j = 0; j < SIZE; j++) {
        firstBattle[j][ch] = read[i][j];
      }
    }

    for (int i = SIZE * THREE, ch = 0; i < SIZE * FOUR; ch++, i++) {
      for (int j = 0; j < SIZE; j++) {
        secondBattle[j][ch] = read[i][j];
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
