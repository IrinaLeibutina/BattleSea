package application;

import java.security.SecureRandom;

public class JavaReplay {

  public void qSort(int[] maxNumber, String[] nameOfGame, int left, int right) {
    int i = left;
    int j = right;
    SecureRandom rand = new SecureRandom();
    int x = maxNumber[left + rand.nextInt(right - left + 1)];
    while (i <= j) {
      while (maxNumber[i] < x) {
        i++;
      }
      while (maxNumber[j] > x) {
        j--;
      }
      if (i <= j) {
        int temp = maxNumber[i];
        maxNumber[i] = maxNumber[j];
        maxNumber[j] = temp;
        String change;
        change = nameOfGame[i];
        nameOfGame[i] = nameOfGame[j];
        nameOfGame[j] = change;
        i++;
        j--;
      }
    }
    if (left < j) {
      qSort(maxNumber, nameOfGame, left, j);
    }
    if (i < right) {
      qSort(maxNumber, nameOfGame, i, right);
    }
  }
}
