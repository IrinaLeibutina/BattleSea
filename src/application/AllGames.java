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
  public static int findMaxNumber[][] = new int[FORTY_POSITION][SIZE];
  public static int maxNumber[] = new int[REPLAYS];
  public static String nameOfGames[] = new String[REPLAYS];
  
  AllGames() {
    nameOfGames[0] = "1";
  }

  public void workWithReplays(Stage primaryStage) {
    Pane battle = new Pane();
    Scene scene = new Scene(battle, WEIGHT, HEIGHT);
    Interface inter = new Interface();
    ImageView background = inter.createBackground();

    battle.getChildren().addAll(background);

    Text game = new Text("ÏÎÂÒÎÐ");
    game.setFill(Color.FIREBRICK);
    game.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    game.setLayoutX(540);
    game.setLayoutY(568);

    Text winner = new Text("ÌÅÄËÅÍÍÀß ÈÃÐÀ");
    winner.setFill(Color.FIREBRICK);
    winner.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    winner.setLayoutX(268);
    winner.setLayoutY(568);

    Text replay = new Text("ÁÛÑÒÐÀß ÈÃÐÀ(JAVA)");
    replay.setFill(Color.FIREBRICK);
    replay.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    replay.setLayoutX(15);
    replay.setLayoutY(568);

    Text secondReplay = new Text("ÁÛÑÒÐÀß ÈÃÐÀ(SCALA)");
    secondReplay.setFill(Color.FIREBRICK);
    secondReplay.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    secondReplay.setLayoutX(700);
    secondReplay.setLayoutY(568);

    // Rectangle for button
    Rectangle sortJava = new Rectangle(185, 25, Color.DARKSALMON);
    sortJava.setOpacity(TRANSPARENCY);
    sortJava.setLayoutX(10);
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

    Rectangle sortScala = new Rectangle(190, 25, Color.DARKSALMON);
    sortScala.setOpacity(TRANSPARENCY);
    sortScala.setLayoutX(697);
    sortScala.setLayoutY(550);
    FillTransition secondFillTrasition =
        new FillTransition(Duration.seconds(TRANSPARENCY), sortScala);
    sortScala.setOnMouseEntered(event -> {
      secondFillTrasition.setFromValue(Color.YELLOW);
      secondFillTrasition.setToValue(Color.CORNSILK);
      secondFillTrasition.setCycleCount(Animation.INDEFINITE);
      secondFillTrasition.setAutoReverse(true);
      secondFillTrasition.play();
    });
    sortScala.setOnMouseExited(event -> {
      secondFillTrasition.stop();
      sortScala.setFill(Color.DARKSALMON);
    });

    Rectangle slow = new Rectangle(155, 25, Color.DARKSALMON);
    slow.setOpacity(TRANSPARENCY);
    slow.setLayoutX(265);
    slow.setLayoutY(550);
    FillTransition forthFillTrasition = new FillTransition(Duration.seconds(TRANSPARENCY), slow);
    slow.setOnMouseEntered(event -> {
      forthFillTrasition.setFromValue(Color.YELLOW);
      forthFillTrasition.setToValue(Color.CORNSILK);
      forthFillTrasition.setCycleCount(Animation.INDEFINITE);
      forthFillTrasition.setAutoReverse(true);
      forthFillTrasition.play();
    });
    slow.setOnMouseExited(event -> {
      forthFillTrasition.stop();
      slow.setFill(Color.DARKSALMON);
    });

    Rectangle comeBack = new Rectangle(130, 25, Color.DARKSALMON);
    comeBack.setOpacity(TRANSPARENCY);
    comeBack.setLayoutX(505);
    comeBack.setLayoutY(550);
    FillTransition thirdFillTrasition =
        new FillTransition(Duration.seconds(TRANSPARENCY), comeBack);
    comeBack.setOnMouseEntered(event -> {
      thirdFillTrasition.setFromValue(Color.YELLOW);
      thirdFillTrasition.setToValue(Color.CORNSILK);
      thirdFillTrasition.setCycleCount(Animation.INDEFINITE);
      thirdFillTrasition.setAutoReverse(true);
      thirdFillTrasition.play();
    });
    comeBack.setOnMouseExited(event -> {
      thirdFillTrasition.stop();
      comeBack.setFill(Color.DARKSALMON);
    });

    sortJava.setOnMouseClicked(event -> {
      for (int i = 0; i < 10; i++) {
        System.out.println(nameOfGames[i] + maxNumber[i]);
      }
      getInfo(battle);
      long start = System.nanoTime();
      qSort(maxNumber, nameOfGames, FLAG, REPLAYS - 1);
      long end = System.nanoTime();
      long traceTime = end - start;
      createTextFotUtput(traceTime, battle, 1);
    });

    sortScala.setOnMouseClicked(event -> {
      getInfo(battle);
      ScalaReplay sr = new ScalaReplay();
      long start = System.nanoTime();
      sr.sort(maxNumber, nameOfGames);
      long end = System.nanoTime();
      long traceTime = end - start;

      for (int i = 0; i < SIZE; i++) {
        System.out.println(nameOfGames[i] + maxNumber[i]);
      }
      createTextFotUtput(traceTime, battle, TWO);
    });

    slow.setOnMouseClicked(event -> {
      getInfo(battle);
      ScalaReplay sr = new ScalaReplay();
      long start = System.nanoTime();
      sr.sort(maxNumber, nameOfGames);
      long end = System.nanoTime();
      long traceTime = end - start;

      for (int i = 0; i < 10; i++) {
        System.out.println(nameOfGames[i] + maxNumber[i]);
      }
      createTextFotUtput(traceTime, battle, 3);
    });

    comeBack.setOnMouseClicked(event -> {
      TwoComp game2 = new TwoComp();
      game2.playGame(primaryStage);
    });

    battle.getChildren().addAll(game, winner, replay, secondReplay, sortJava, sortScala, slow,
        comeBack);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void createTextFotUtput(long time, Pane battle, int type) {
    String output = "";
    if (type == 3) {
      {
        for (int i = REPLAYS - 1; i > REPLAYS - 11; i--) {
          output += nameOfGames[i] + " = " + maxNumber[i] + "\n\n";
        }
      }
    } else {
      for (int i = 0; i < SIZE; i++) {
        output += nameOfGames[i] + " = " + maxNumber[i] + "\n\n";
      }
      output += "Âðåìÿ ñîðòèðîâêè = " + time + "íñ\n";
    }
    print(battle, output, type);
  }

  // Get info about fiels
  public void getInfo(Pane battle) {
    Save read = new Save();
    for (int i = 0; i < REPLAYS; i++) {
      String path = "d:\\Replay\\Råplay" + i + ".txt";
      findMaxNumber = read.loadArrayFromFile(path, FIFE);
      maxNumber[i] = findMaxNumber(findMaxNumber);
      nameOfGames[i] = path;
    }
  }

  public int findMaxNumber(int[][] saveInformation) {
    int find = 0;
    for (int i = 0; i < FORTY_POSITION; i++) {
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

  public void print(Pane battle, String output, int type) {
    Text win = new Text();
    win.setFont(Font.font("Arial", FontPosture.ITALIC, 18));
    win.setFill(Color.SIENNA);
    win.setOpacity(2.5);
    if (type == ONE) {
      win.setLayoutX(FORTY_POSITION);
      win.setLayoutY(THIRTY_POSITION);
    }
    if (type == TWO) {
      win.setLayoutX(610);
      win.setLayoutY(30);
    }

    if (type == THREE) {
      win.setLayoutX(324);
      win.setLayoutY(30);
    }
    win.setText(output);

    battle.getChildren().addAll(win);
  }

  public String getPath() {
    return nameOfGames[0];
  }
}

