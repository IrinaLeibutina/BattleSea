package application;

import application.Ships;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import static application.Constants.*;
import java.security.SecureRandom;

public class BattleWithComp extends Ships {

  public boolean first = false;
  public boolean second = true;
  Button[][] field = new Button[SIZE][SIZE];
  Interface inter;
  Field fieldForBattle;

  BattleWithComp() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        field[i][j] = new Button();
        saveFirstField[i][j] = checkForShipFirst[i][j];
        saveSecondField[i][j] = checkForShipSecond[i][j];
        saveFirstBattle[i][j] = 0;
        saveSecondBattle[i][j] = 0;
        positionFirst = 0;
        positionSecond = 1;
        inter = new Interface();
        fieldForBattle = new Field();
      }
    }
  }

  public void createBattle(Stage primaryStage, int level) {
    Pane battle = new Pane();
    Scene scene = new Scene(battle, WEIGHT, HEIGHT);
    Battle battleWithComp = new Battle();
    battleWithComp.getInterface(battle, primaryStage);
    fieldForBattle.fieldNameForBattle(battle);
    battle(battle, level, primaryStage);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void playGame(Stage primaryStage, Text win) {
    Pane battle = new Pane();
    Scene scene = new Scene(battle, WEIGHT, HEIGHT);
    // Get interface for field
    Battle battleWithComp = new Battle();
    battleWithComp.getInterface(battle, primaryStage);
    battle.getChildren().addAll(win);

    Rectangle repeat = new Rectangle(150, 25, Color.DARKSALMON);
    repeat.setOpacity(TRANSPARENCY);

    repeat.setLayoutX(450);
    repeat.setLayoutY(550);
    FillTransition fillTrasition = new FillTransition(Duration.seconds(TRANSPARENCY), repeat);
    repeat.setOnMouseEntered(event -> {
      fillTrasition.setFromValue(Color.YELLOW);
      fillTrasition.setToValue(Color.CORNSILK);
      fillTrasition.setCycleCount(Animation.INDEFINITE);
      fillTrasition.setAutoReverse(true);
      fillTrasition.play();
    });
    repeat.setOnMouseExited(event -> {
      fillTrasition.stop();
      repeat.setFill(Color.DARKSALMON);
    });

    Text text = new Text("ПОВТОР ИГРЫ");
    text.setFill(Color.FIREBRICK);

    text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    text.setLayoutX(470);
    text.setLayoutY(568);

    battle.getChildren().addAll(text, repeat);

    repeat.setOnMouseClicked(event -> {
      battleWithComp.getInterface(battle, primaryStage);

      fieldForBattle.fieldNameForBattle(battle);
      repeat(battle, primaryStage);
    });

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void repeat(Pane battle, Stage primaryStage) {
    // Create field for battle
    Button[][] firstField = fieldForBattle.createFieldForBattle(ONE);
    Button[][] secondField = fieldForBattle.createFieldForBattle(TWO);

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        battle.getChildren().addAll(firstField[i][j], secondField[i][j]);
      }
    }

    Rectangle repeat = new Rectangle(150, 25, Color.DARKSALMON);
    repeat.setOpacity(TRANSPARENCY);

    repeat.setLayoutX(450);
    repeat.setLayoutY(550);

    FillTransition fillTrasition = new FillTransition(Duration.seconds(TRANSPARENCY), repeat);
    repeat.setOnMouseEntered(event -> {
      fillTrasition.setFromValue(Color.YELLOW);
      fillTrasition.setToValue(Color.CORNSILK);
      fillTrasition.setCycleCount(Animation.INDEFINITE);
      fillTrasition.setAutoReverse(true);
      fillTrasition.play();
    });
    repeat.setOnMouseExited(event -> {
      fillTrasition.stop();
      repeat.setFill(Color.DARKSALMON);
    });
    Text text = new Text("ПОВТОР ИГРЫ");
    text.setFill(Color.FIREBRICK);

    text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    text.setLayoutX(455);
    text.setLayoutX(550);
    battle.getChildren().addAll(repeat);

    Text win = new Text();
    win.setLayoutX(330);
    win.setLayoutY(250);
    win.setFont(Font.font("Arial", FontPosture.ITALIC, 32));
    win.setFill(Color.DARKSALMON);

    boat1 = FOUR;
    destroyer1 = THREE;
    cruiser1 = TWO;
    battleship1 = ONE;
    boat2 = FOUR;
    destroyer2 = THREE;
    cruiser2 = TWO;
    battleship2 = ONE;

    repeat.setOnMouseClicked(event -> {
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {

          if (boat1 == FLAG && battleship1 == FLAG && cruiser1 == FLAG && destroyer1 == FLAG) {
            win.setText("Комрьютер победил!!!");
            battle.getChildren().add(win);
            System.out.println("Второй ");
            return;
          }
          if (boat2 == FLAG && battleship2 == FLAG && cruiser2 == FLAG && destroyer2 == FLAG) {
            win.setText("Игрок Победил!!!");
            battle.getChildren().addAll(win);
            System.out.println("Первый");
            return;
          }

          if (saveSecondBattle[i][j] == positionSecond) {
            int x = i;
            int y = j;
            System.out.println("\nFindFirst" + positionSecond);
            positionSecond++;
            newCheck = saveFirstField;

            if (newCheck[x][y] != FLAG) {
              field = firstField;
              boat1 = boatCheck(field, x, y, boat1); // Boat
              destroyer1 = destroyerCheck(field, x, y, destroyer1); // Destroyer
              cruiser1 = cruiserCheck(field, x, y, cruiser1); // Cruiser
              battleship1 = battleshipCheck(field, x, y, battleship1, ONE); // Battleship

            } else {
              firstField[x][y].setStyle("-fx-base: lightgrey");
              firstField[x][y].setOpacity(FLAG);
              newCheck[x][y] = -1;
            }
            i = FLAG;
            j = FLAG;
          }
          if (saveFirstBattle[i][j] == positionSecond) {
            int x = i;
            int y = j;

            newCheck = saveSecondField;

            if (newCheck[x][y] != FLAG) {
              field = secondField;
              boat2 = boatCheck(field, x, y, boat2); // Boat
              battleship2 = battleshipCheck(field, x, y, battleship2, TWO); // Destroyer
              cruiser2 = cruiserCheck(field, x, y, cruiser2); // Cruiser
              destroyer2 = destroyerCheck(field, x, y, destroyer2); // Battleship
            } else {
              secondField[x][y].setStyle("-fx-base: lightgrey");
              secondField[x][y].setOpacity(FLAG);
              newCheck[x][y] = -1;
            }
            System.out.println("\nFindSecond" + positionSecond);
            positionSecond++;
            i = FLAG;
            j = FLAG;
          }
        }
      }
    });
  }

  public void battle(Pane battle, int level, Stage primaryStage) {
    // Create field for battle
    Button[][] firstField = fieldForBattle.createFieldForBattle(1);
    Button[][] secondField = fieldForBattle.createFieldForBattle(2);

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        battle.getChildren().addAll(firstField[i][j], secondField[i][j]);
      }
    }
    Text win = new Text();
    win.setLayoutX(350);
    win.setLayoutY(250);
    win.setFont(Font.font("Arial", FontPosture.ITALIC, 32));
    win.setFill(Color.DARKSALMON);
    // Battle
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        int x = i;
        int y = j;
        Save save = new Save();
        // Battle
        secondField[i][j].setOnMouseClicked(event -> {
          if (first == false) {
            if (boat1 == FLAG && battleship1 == FLAG && cruiser1 == FLAG && destroyer1 == FLAG) {
              win.setText("Игрок победил!!!");
              playGame(primaryStage, win);
              return;
            }
            newCheck = checkForShipSecond;
            if (newCheck[x][y] == -1 || newCheck[x][y] == 8 || newCheck[x][y] == SIX
                || newCheck[x][y] == FIFE || newCheck[x][y] == 9) {
              second = false;
              first = false;
            } else {
              if (newCheck[x][y] != FLAG) {
                second = false;
                first = false;
                field = secondField;
                boat1 = boatCheck(field, x, y, boat1); // Boat
                destroyer1 = destroyerCheck(field, x, y, destroyer1); // Destroyer
                cruiser1 = cruiserCheck(field, x, y, cruiser1); // Cruiser
                battleship1 = battleshipCheck(field, x, y, battleship1, 1); // Battleship
                positionFirst++;
                saveFirstBattle[x][y] = positionFirst;
              }
              if (newCheck[x][y] == FLAG) {
                secondField[x][y].setStyle("-fx-base: lightgrey");
                secondField[x][y].setOpacity(0);
                newCheck[x][y] = -1;
                second = true;
                first = true;
                positionFirst++;
                saveFirstBattle[x][y] = positionFirst;
              }
            }
            save.saveArrayToFile(saveFirstField, saveSecondField, saveFirstBattle, saveSecondBattle,
                "d:\\filename.txt");
          }

          if (second) {
            if (boat2 == FLAG && battleship2 == FLAG && cruiser2 == FLAG && destroyer2 == FLAG) {
              System.out.println("Second");
              win.setText("Компьютер победил!!!");
              playGame(primaryStage, win);
              return;
            }
            SecureRandom rand = new SecureRandom();
            int positionX = (rand.nextInt(SIZE));
            int positionY = (rand.nextInt(SIZE));
            newCheck = checkForShipFirst;
            if (newCheck[positionX][positionY] == -1 || newCheck[positionX][positionY] == 8
                || newCheck[positionX][positionY] == SIX || newCheck[positionX][positionY] == FIFE
                || newCheck[positionX][positionY] == 9) {
              second = true;
              first = true;
            } else {
              if (newCheck[positionX][positionY] != FLAG) {
                second = true;
                first = true;
                field = firstField;
                boat2 = boatCheck(field, positionX, positionY, boat2);
                destroyer2 = destroyerCheck(field, positionX, positionY, destroyer2);
                cruiser2 = cruiserCheck(field, positionX, positionY, cruiser2);
                battleship2 = battleshipCheck(field, positionX, positionY, battleship2, TWO);
                positionFirst++;
                saveSecondBattle[positionX][positionY] = positionFirst;
              }
              if (newCheck[positionX][positionY] == FLAG) {
                firstField[positionX][positionY].setStyle("-fpositionX-base: lightgrepositionY");
                firstField[positionX][positionY].setOpacity(0);
                newCheck[positionX][positionY] = -1;
                second = false;
                first = false;
                positionFirst++;
                saveSecondBattle[positionX][positionY] = positionFirst;
              }
            }
            save.saveArrayToFile(saveFirstField, saveSecondField, saveFirstBattle, saveSecondBattle,
                "d:\\filename.txt");
          }
        });
      }
    }
  }

  // System.out.println(...) use for check in console
  public int battleshipCheck(Button[][] field, int x, int y, int Battleship, int stroke) {
    int count = FLAG;
    if (Battleship == FLAG) {
      return FLAG;
    }
    if (newCheck[x][y] == FOUR && stroke == ONE) {

      for (int k = x; k < x + FOUR; k++) {
        if (k < SIZE && newCheck[k][y] == 8) {
          count++;
        }
      }
      if (count < THREE) {
        for (int k = x - FOUR; k < x; k++) {
          if (k >= FLAG && newCheck[k][y] == 8) {
            count++;
          }
        }
      }
      if (count < THREE) {
        for (int k = y - FOUR; k < y; k++) {
          if (k >= FLAG && newCheck[x][k] == 8) {
            count++;
          }
        }
      }
      if (count < THREE) {
        for (int k = y; k < y + FOUR; k++) {
          if (k < SIZE && newCheck[x][k] == 8) {
            count++;
          }
        }
      }
      if (count == THREE) {
        System.out.println("Четырехпалубный");
        Battleship--;
        count = FLAG;
      }
      newCheck[x][y] = 8;
      field[x][y].setStyle("-fx-base: blue");
    }

    if (newCheck[x][y] == FOUR && stroke == TWO) {
      for (int k = x + ONE; k < x + FOUR; k++) {
        if (k < SIZE && newCheck[k][y] == FOUR) {
          count++;
          newCheck[k][y] = 8;
          field[k][y].setStyle("-fx-base: blue");
        }
      }
      if (count < THREE) {
        for (int k = x - FOUR; k < x; k++) {
          if (k >= FLAG && newCheck[k][y] == FOUR) {
            count++;
            newCheck[k][y] = 8;
            field[k][y].setStyle("-fx-base: blue");
          }
        }
      }
      if (count < THREE) {
        for (int k = y - FOUR; k < y; k++) {
          if (k >= FLAG && newCheck[x][k] == FOUR) {
            count++;
            newCheck[x][k] = 8;
            field[x][k].setStyle("-fx-base: blue");
          }
        }
      }
      if (count < THREE) {
        for (int k = y + ONE; k < y + FOUR; k++) {
          if (k < SIZE && newCheck[x][k] == FOUR) {
            count++;
            newCheck[x][k] = 8;
            field[x][k].setStyle("-fx-base: blue");
          }
        }
      }
      if (count == THREE) {
        System.out.println("Четырехпалубный"); ////
        Battleship--;
        count = FLAG;
      }
      newCheck[x][y] = 8;
      field[x][y].setStyle("-fx-base: blue");
      System.out.println(count);
    }
    return Battleship;
  }

  public int cruiserCheck(Button[][] field, int x, int y, int cruiser) {
    int count = FLAG;
    if (cruiser == FLAG) {
      return FLAG;
    }
    if (newCheck[x][y] == THREE) {
      for (int k = x; k < x + THREE; k++) {
        if (k < SIZE && newCheck[k][y] == SIX) {
          count++;
        }
      }
      if (count < TWO) {
        for (int k = x - THREE; k < x; k++) {
          if (k >= FLAG && newCheck[k][y] == SIX) {
            count++;
          }
        }
      }
      if (count < TWO) {
        for (int k = y - THREE; k < y; k++) {
          if (k >= FLAG && newCheck[x][k] == SIX) {
            count++;
          }
        }
      }
      if (count < TWO) {
        for (int k = y; k < y + THREE; k++) {
          if (k < SIZE && newCheck[x][k] == SIX) {
            count++;
          }
        }
      }
      if (count == TWO) {
        System.out.println("Трехпалубный"); ////
        cruiser--;
        count = FLAG;
      }
      newCheck[x][y] = SIX;
      field[x][y].setStyle("-fx-base: green");
    }
    return cruiser;
  }

  public int destroyerCheck(Button[][] field, int x, int y, int destroyer) {
    int count = FLAG;
    if (destroyer == FLAG) {
      return FLAG;
    }
    if (newCheck[x][y] == TWO) {
      for (int k = x; k < x + TWO; k++) {
        if (k < SIZE && newCheck[k][y] == FIFE) {
          count++;
        }
      }
      if (count < ONE) {
        for (int k = x - TWO; k < x; k++) {
          if (k >= FLAG && newCheck[k][y] == FIFE) {
            count++;
          }
        }
      }
      if (count < ONE) {
        for (int k = y - TWO; k < y; k++) {
          if (k >= FLAG && newCheck[x][k] == FIFE) {
            count++;
          }
        }
      }
      if (count < ONE) {
        for (int k = y; k < y + TWO; k++) {
          if (k < SIZE && newCheck[x][k] == FIFE) {
            count++;
          }
        }
      }
      if (count == ONE) {
        System.out.println("Двухпалубный"); ////
        destroyer--;
        count = FLAG;
      }
      newCheck[x][y] = FIFE;
      field[x][y].setStyle("-fx-base: lightgrey");
    }
    return destroyer;
  }

  public int boatCheck(Button[][] field, int x, int y, int boat) {
    if (boat == FLAG) {
      return FLAG;
    }
    if (newCheck[x][y] == ONE) {
      newCheck[x][y] = 9;
      System.out.println("Однопалубный"); ////
      field[x][y].setStyle("-fx-base: yellow");
      boat--;
    }
    return boat;
  }
}
