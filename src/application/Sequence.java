package application;

import static application.Constants.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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


public class Sequence {

  public static int sequence[][][] = new int[REPLAYS][FORTY_POSITION][SIZE];
  public static int searchingResults[] = new int[REPLAYS];

  public void workWithReplays(Stage primaryStage) {
    Pane battle = new Pane();
    Scene scene = new Scene(battle, WEIGHT, HEIGHT);
    Interface inter = new Interface();
    ImageView background = inter.createBackground();
    battle.getChildren().addAll(background);
    // Text for button
    Text sequenceOfGame = new Text("ÏÎÈÑÊ");
    sequenceOfGame.setFill(Color.FIREBRICK);
    sequenceOfGame.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    sequenceOfGame.setLayoutX(294);
    sequenceOfGame.setLayoutY(568);

    Text back = new Text("ÍÀÇÀÄ");
    back.setFill(Color.FIREBRICK);
    back.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    back.setLayoutX(545);
    back.setLayoutY(568);

    // Rectangle for button
    Rectangle comeBack = new Rectangle(150, 25, Color.DARKSALMON);
    comeBack.setOpacity(TRANSPARENCY);
    comeBack.setLayoutX(500);
    comeBack.setLayoutY(550);
    FillTransition fillTrasition = new FillTransition(Duration.seconds(TRANSPARENCY), comeBack);
    comeBack.setOnMouseEntered(event -> {
      fillTrasition.setFromValue(Color.YELLOW);
      fillTrasition.setToValue(Color.CORNSILK);
      fillTrasition.setCycleCount(Animation.INDEFINITE);
      fillTrasition.setAutoReverse(true);
      fillTrasition.play();
    });
    comeBack.setOnMouseExited(event -> {
      fillTrasition.stop();
      comeBack.setFill(Color.DARKSALMON);
    });

    Rectangle game = new Rectangle(150, 25, Color.DARKSALMON);
    game.setOpacity(TRANSPARENCY);
    game.setLayoutX(250);
    game.setLayoutY(550);
    FillTransition secondFillTrasition = new FillTransition(Duration.seconds(TRANSPARENCY), game);
    game.setOnMouseEntered(event -> {
      secondFillTrasition.setFromValue(Color.YELLOW);
      secondFillTrasition.setToValue(Color.CORNSILK);
      secondFillTrasition.setCycleCount(Animation.INDEFINITE);
      secondFillTrasition.setAutoReverse(true);
      secondFillTrasition.play();
    });
    game.setOnMouseExited(event -> {
      secondFillTrasition.stop();
      game.setFill(Color.DARKSALMON);
    });

    battle.getChildren().addAll(sequenceOfGame, back, comeBack, game);

    comeBack.setOnMouseClicked(event -> {
      Main mainMenu = new Main();
      mainMenu.start(primaryStage);
    });

    game.setOnMouseClicked(event -> {
      getInfo(battle);
    });
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void getInfo(Pane battle) {
    Save read = new Save();
    for (int i = 0; i < REPLAYS; i++) {
      String path = "d:\\Replay\\Råplay" + i + ".txt";
      loadGame = read.loadArrayFromFile(path, FIFE);
      sequence[i] = loadGame;
    }

    PopularCell cell = new PopularCell();
    int results[] = new int[FIFE];
    results = cell.findMaxSeq(sequence, searchingResults, REPLAYS);
    workWithFile(battle, results);
  }

  public void workWithFile(Pane battle, int[] results) {
    String forUser[] = new String[250];
    int nameOfFile = results[1];
    int cell = 1;
    int currentPosition = 1;

    char[] name = new char[SIZE];
    name[0] = 'A'; // Create Russian symbols
    name[1] = 'Á';
    name[2] = 'Â';
    name[3] = 'Ã';
    name[4] = 'Ä';
    name[5] = 'Å';
    name[6] = 'Æ';
    name[7] = 'Ç';
    name[8] = 'È';
    name[9] = 'Ê';

    Save read = new Save();
    String adress = "d:\\Replay\\Råplay" + nameOfFile + ".txt";
    loadGame = read.loadArrayFromFile(adress, FIFE);

    forUser[0] = "Ïîñëåäîâàòåëüíîñòü õîäîâ";
    for (int i = 20; i < 40; i++) {
      for (int j = 0; j < 10; j++) {
        String winner;
        if (cell == loadGame[i][j]) {
          if (i >= 20 && i < 30) {
            forUser[currentPosition] = "Õîä âòîðîãî èãðîêà - ";
            winner = "Ïîáåäèë âòîðîé èãðîê!!!";
            i -= 20;
          } else {
            forUser[currentPosition] = "Õîä ïåðâîãî èãðîêà - ";
            winner = "Ïîáåäèë ïåðâûé èãðîê!!!";
            i -= 30;
          }
          forUser[currentPosition] += name[i];
          forUser[currentPosition] += j;
          currentPosition++;
          cell++;
          forUser[currentPosition] = winner;
          i = 20;
          j = 0;
        }
      }
    }
    String path = "d:\\ForUser.txt";
    try {
      BufferedWriter bufWriter = new BufferedWriter(new FileWriter(path));
      for (int i = 0; i < cell + 1; i++) {
        bufWriter.write(String.valueOf(forUser[i]) + " ");
        bufWriter.newLine();
      }
      bufWriter.newLine();
      bufWriter.flush();
      bufWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    print(battle, path, results);
  }

  public void print(Pane battle, String path, int[] results) {
    String games;
    games = "ÎÁÙÅÅ ÊÎËÈ×ÅÑÒÂÎ ÈÃÐ - " + REPLAYS + "\n";
    Text out = new Text();
    out.setFont(Font.font("Arial", FontPosture.ITALIC, 20));
    out.setFill(Color.SIENNA);
    out.setOpacity(2.5);
    out.setLayoutX(280);
    out.setLayoutY(28);
    out.setText(games);

    String adress;
    adress =
        "ÑÀÌÀß ×ÀÑÒÎ ÂÑÒÐÅ×ÀÞÙÀßÑß ÏÎÑËÅÄÎÂÀÒÅËÜÍÎÑÒÜ Â ÈÃÐÀÕ ÑÎÕÐÀÍÅÍÀ\n\nÂ ÔÀÉË. ÀÄÐÅÑ : " + path;
    adress += "\n\nÅÅ ÑÎÄÅÐÆÀÍÈÅ Â ÎÁÙÅÌ ÊÎËÈ×ÅÑÒÂÅ ÈÃÐ = " + results[0];
    Text outSeq = new Text();
    outSeq.setFont(Font.font("Arial", FontPosture.ITALIC, 20));
    outSeq.setFill(Color.SIENNA);
    outSeq.setOpacity(2.5);
    outSeq.setLayoutX(50);
    outSeq.setLayoutY(68);
    outSeq.setText(adress);

    battle.getChildren().addAll(out, outSeq);
  }
}
