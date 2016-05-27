package application;

import static application.Constants.*;
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

public class GameCard {

  public void workWithReplays(Stage primaryStage) {
    Pane battle = new Pane();
    Scene scene = new Scene(battle, WEIGHT, HEIGHT);
    Interface inter = new Interface();
    ImageView background = inter.createBackground();
    battle.getChildren().addAll(background);
    // Text for button
    Text mapForGame = new Text("ÊÀÐÒÀ ÈÃÐÛ");
    mapForGame.setFill(Color.FIREBRICK);
    mapForGame.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    mapForGame.setLayoutX(275);
    mapForGame.setLayoutY(568);

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

    Rectangle gameMap = new Rectangle(150, 25, Color.DARKSALMON);
    gameMap.setOpacity(TRANSPARENCY);
    gameMap.setLayoutX(250);
    gameMap.setLayoutY(550);
    FillTransition secondFillTrasition =
        new FillTransition(Duration.seconds(TRANSPARENCY), gameMap);
    gameMap.setOnMouseEntered(event -> {
      secondFillTrasition.setFromValue(Color.YELLOW);
      secondFillTrasition.setToValue(Color.CORNSILK);
      secondFillTrasition.setCycleCount(Animation.INDEFINITE);
      secondFillTrasition.setAutoReverse(true);
      secondFillTrasition.play();
    });
    gameMap.setOnMouseExited(event -> {
      secondFillTrasition.stop();
      gameMap.setFill(Color.DARKSALMON);
    });

    battle.getChildren().addAll(mapForGame, back, comeBack, gameMap);

    comeBack.setOnMouseClicked(event -> {
      Main mainMenu = new Main();
      mainMenu.start(primaryStage);
    });

    gameMap.setOnMouseClicked(event -> {
      getInfo(battle);
    });
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void getInfo(Pane battle) {
    Save read = new Save();
    AllGames getMaxNumber = new AllGames();

    for (int i = 0; i < REPLAYS; i++) {
      String path = "d:\\Replay\\Råplay" + i + ".txt";
      loadGame = read.loadArrayFromFile(path, FIFE);
      winner[i] = getWinner(loadGame);
      coordinates[i] = getCoordinates(loadGame);
      nameOfGame[i] = i;
      fieldFilling[i] = getMaxNumber.findMaxNumber(loadGame) / 2;

      if (fieldFilling[i] == 193 || fieldFilling[i] == 127 || fieldFilling[i] == 101) {
        fieldFilling[i] = 88;
      }
    }

    PopularCell cell = new PopularCell();
    int first;
    int second;
    int firstCell;
    int fullField;

    first = cell.winsFirstPlayer(winner);
    second = cell.winsSecondPlayer(winner);
    getKeys(coordinates);
    firstCell = cell.getCell(coordinates, keysForCoordinates, REPLAYS);
    fullField = cell.getField(fieldFilling);
    print(battle, first, second, firstCell, fullField);
  }

  public int getWinner(int[][] saveInformation) {
    int find = 0;
    int win = 0;
    for (int i = 0; i < FORTY_POSITION; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (find < saveInformation[i][j]) {
          int change = find;
          find = saveInformation[i][j];
          saveInformation[i][j] = change;
          win = i;
        }
      }
    }
    if (win >= 20 && win < 30) {
      return 2;
    }
    if (win >= 30 && win < 40) {
      return 1;
    }
    return 0;
  }

  public int getCoordinates(int[][] saveInformation) {
    int find = 0;
    for (int i = 20; i < FORTY_POSITION; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (saveInformation[i][j] == 1) {
          find = j * 10 + (i - 20);
        }
      }
    }
    return find;
  }

  public void getKeys(int[] coordinates) {
    int find = 0;
    int amount;
    for (int i = 0; i < REPLAYS; i++) {
      amount = 0;
      find = coordinates[i];
      for (int j = 0; j < REPLAYS; j++) {
        if (find == coordinates[j])
          amount++;
      }
      keysForCoordinates[i] = amount;
    }
  }

  public void print(Pane battle, int first, int second, int firstCell, int fullField) {
    String games;
    games = "ÎÁÙÅÅ ÊÎËÈ×ÅÑÒÂÎ ÈÃÐ - " + REPLAYS + "\n";
    Text out = new Text();
    out.setFont(Font.font("Arial", FontPosture.ITALIC, 20));
    out.setFill(Color.SIENNA);
    out.setOpacity(2.5);
    out.setLayoutX(280);
    out.setLayoutY(28);
    out.setText(games);

    String firstPlayer;
    firstPlayer = "ÊÎËÈ×ÅÑÒÂÎ ÏÎÁÅÄ ÏÅÐÂÎÃÎ ÈÃÐÎÊÀ - " + first + "\n";
    Text outFirstPlayer = new Text();
    outFirstPlayer.setFont(Font.font("Arial", FontPosture.ITALIC, 20));
    outFirstPlayer.setFill(Color.SIENNA);
    outFirstPlayer.setOpacity(2.5);
    outFirstPlayer.setLayoutX(50);
    outFirstPlayer.setLayoutY(68);
    outFirstPlayer.setText(firstPlayer);

    String secondPlayer;
    secondPlayer = "ÊÎËÈ×ÅÑÒÂÎ ÏÎÁÅÄ ÂÒÎÐÎÃÎ ÈÃÐÎÊÀ - " + second + "\n";
    Text outSecondPlayer = new Text();
    outSecondPlayer.setFont(Font.font("Arial", FontPosture.ITALIC, 20));
    outSecondPlayer.setFill(Color.SIENNA);
    outSecondPlayer.setOpacity(2.5);
    outSecondPlayer.setLayoutX(50);
    outSecondPlayer.setLayoutY(108);
    outSecondPlayer.setText(secondPlayer);

    String cell;

    Text outCell = new Text();
    outCell.setFont(Font.font("Arial", FontPosture.ITALIC, 20));
    outCell.setFill(Color.SIENNA);
    outCell.setOpacity(2.5);
    outCell.setLayoutX(50);
    outCell.setLayoutY(148);

    int coordinatesX;
    int coordinatesY;
    coordinatesX = firstCell / 10;
    coordinatesY = firstCell - coordinatesX * 10;
    coordinatesX++;
    coordinatesY++;

    cell =
        "ÑÀÌÀß ×ÀÑÒÎ ÎÁÑÒÐÅËÈÂÀÅÌÀß ÍÀ ÏÅÐÂÎÌ ÕÎÄÓ ÊËÅÒÊÀ ÈÌÅÅÒ ÊÎÎÐÄÈÍÀÒÛ \n\nX = " + coordinatesX;
    cell += ", Y = " + coordinatesY;
    outCell.setText(cell);

    String field;
    field = "ÌÀÊÑÈÌÀËÜÍÎÅ ÇÀÏÎËÍÅÍÈÅ ÏÎËß ÈÃÐÛ - " + fullField + " %";
    Text outField = new Text();
    outField.setFont(Font.font("Arial", FontPosture.ITALIC, 20));
    outField.setFill(Color.SIENNA);
    outField.setOpacity(2.5);
    outField.setLayoutX(50);
    outField.setLayoutY(228);
    outField.setText(field);

    battle.getChildren().addAll(out, outFirstPlayer, outSecondPlayer, outCell, outField);
  }
}
