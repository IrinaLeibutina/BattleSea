package application;

import java.security.SecureRandom;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import static application.Constants.*;

public class AllGames {

  public static int findMaxNumber[][] = new int[FORTY][SIZE];
  public static String nameOfGame[] = new String[REPLAYS];
  public static int maxNumber[] = new int[REPLAYS];

  public void workWithReplays(Stage primaryStage) {
    Pane battle = new Pane();
    Scene scene = new Scene(battle, WEIGHT, HEIGHT);
    Interface inter = new Interface();
    ImageView background = inter.createBackground();

    battle.getChildren().addAll(background);

    // Rectangle for button
    Rectangle sortJava = new Rectangle(130, 25, Color.DARKSALMON);
    sortJava.setOpacity(TRANSPARENCY);
    sortJava.setLayoutX(20);
    sortJava.setLayoutY(550);
    FillTransition fillTrasition = new FillTransition(Duration.seconds(TRANSPARENCY), sortJava);
    sortJava.setOnMouseEntered(event -> {
      fillTrasition.setFromValue(Color.YELLOW);
      fillTrasition.setToValue(Color.CORNSILK);
      fillTrasition.setCycleCount(Animation.INDEFINITE);
      fillTrasition.setAutoReverse(true);
      fillTrasition.play();
    });
    sortJava.setOnMouseExited(event -> {
      fillTrasition.stop();
      sortJava.setFill(Color.DARKSALMON);
    });

    Rectangle sortScala = new Rectangle(130, 25, Color.DARKSALMON);
    sortScala.setOpacity(TRANSPARENCY);
    sortScala.setLayoutX(740);
    sortScala.setLayoutY(550);
    FillTransition fillTrasition2 = new FillTransition(Duration.seconds(TRANSPARENCY), sortScala);
    sortScala.setOnMouseEntered(event -> {
      fillTrasition2.setFromValue(Color.YELLOW);
      fillTrasition2.setToValue(Color.CORNSILK);
      fillTrasition2.setCycleCount(Animation.INDEFINITE);
      fillTrasition2.setAutoReverse(true);
      fillTrasition2.play();
    });
    sortScala.setOnMouseExited(event -> {
      fillTrasition2.stop();
      sortScala.setFill(Color.DARKSALMON);
    });

    Rectangle comeBack = new Rectangle(130, 25, Color.DARKSALMON);
    comeBack.setOpacity(TRANSPARENCY);
    comeBack.setLayoutX(380);
    comeBack.setLayoutY(550);
    FillTransition fillTrasition3 = new FillTransition(Duration.seconds(TRANSPARENCY), comeBack);
    comeBack.setOnMouseEntered(event -> {
      fillTrasition3.setFromValue(Color.YELLOW);
      fillTrasition3.setToValue(Color.CORNSILK);
      fillTrasition3.setCycleCount(Animation.INDEFINITE);
      fillTrasition3.setAutoReverse(true);
      fillTrasition3.play();
    });
    comeBack.setOnMouseExited(event -> {
      fillTrasition3.stop();
      comeBack.setFill(Color.DARKSALMON);
    });

    sortJava.setOnMouseClicked(event -> {
      getInfo(battle);
      qSort(maxNumber, nameOfGame, NULL, REPLAYS - 1);
    });

    sortScala.setOnMouseClicked(event -> {
      getInfo(battle);
      ScalaReplay sr = new ScalaReplay();
      sr.sort(maxNumber, nameOfGame);
    });

    comeBack.setOnMouseClicked(event -> {
      Main mainMenu = new Main();
      mainMenu.start(primaryStage);
    });
    battle.getChildren().addAll(sortJava, sortScala, comeBack);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  // Get info about fiels
  public void getInfo(Pane battle) {
    Save read = new Save();
    for (int i = 0; i < 10; i++) {
      String path = "d:\\Replay\\Råplay" + i + ".txt";
      findMaxNumber = read.loadArrayFromFile(path, FIFE);

      maxNumber[i] = findMaxNumber(findMaxNumber);
      nameOfGame[i] = path;

      System.out.println(nameOfGame[i] + maxNumber[i]);
    }
  }

  public int findMaxNumber(int[][] saveInformation) {
    int find = 0;
    for (int i = 0; i < FORTY; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (find < saveInformation[i][j]) {
          int change = find;
          find = saveInformation[i][j];
          saveInformation[i][j] = change;
        }
      }
    }
    return find;
  }

  public static void qSort(int[] maxNumber, String[] nameOfGame, int left, int right) {
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

  public void print(Pane battle, String output) {
    Text win = new Text();
    win.setFont(Font.font("Arial", FontPosture.ITALIC, 18));
    win.setFill(Color.DARKSALMON);
    win.setLayoutX(100);
    win.setLayoutY(30);
    win.setText(output);
    battle.getChildren().addAll(win);
  }
}
