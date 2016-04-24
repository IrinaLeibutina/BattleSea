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

public class TwoComp extends Ships {

  public boolean first = false;
  public boolean second = true;
  Pane battle = new Pane();
  Field fieldForBattle = new Field();
  Button[][] firstField = fieldForBattle.createFieldForBattle(ONE);
  Button[][] secondField = fieldForBattle.createFieldForBattle(TWO);
  Button[][] field = new Button[SIZE][SIZE];
  public static int keyPrint = 0;
  Interface inter;

  TwoComp() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        field[i][j] = new Button();
        inter = new Interface();
        saveFirstField[i][j] = checkForShipFirst[i][j];
        saveSecondField[i][j] = checkForShipSecond[i][j];
        saveFirstBattle[i][j] = 0;
        saveSecondBattle[i][j] = 0;
      }
    }
  }

  public void createBattle(Stage primaryStage) {
    // Pane battle = new Pane();
    Scene scene = new Scene(battle, WEIGHT, HEIGHT);

    Battle battleTwoComp = new Battle();
    battleTwoComp.getInterface(battle, primaryStage);

    Field field = new Field();
    field.fieldNameForBattle(battle);
    battle(battle, primaryStage);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void battle(Pane battle, Stage primaryStage) {
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

    Text text = new Text("ИГРА");
    text.setFill(Color.FIREBRICK);
    text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    text.setLayoutX(422);
    text.setLayoutY(568);

    battle.getChildren().addAll(text);
    Rectangle repeat = new Rectangle(130, 25, Color.DARKSALMON);
    repeat.setOpacity(0.5);
    repeat.setLayoutX(385);
    repeat.setLayoutY(550);
    FillTransition fillTrasition = new FillTransition(Duration.seconds(0.5), repeat);
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

    Ships computerShips = new Ships();
    computerShips.arrangeShips(primaryStage, 5);

    for (int i = 0; i < SIZE; i++) {

      for (int j = 0; j < SIZE; j++) {
        saveFirstField[i][j] = checkForShipFirst[i][j];
        saveSecondField[i][j] = checkForShipSecond[i][j];
      }
    }

    repeat.setOnMouseClicked(event -> {
      MyThread thread = new MyThread();
      try {
        thread.start();
      } catch (IllegalThreadStateException e) {
        e.printStackTrace();
      }
    });
    battle.getChildren().addAll(repeat);
  }

  class MyThread extends Thread {
    public void run() {
      Text win = new Text();
      win.setLayoutX(350);
      win.setLayoutY(250);
      win.setFont(Font.font("Arial", FontPosture.ITALIC, 32));
      win.setFill(Color.DARKSALMON);
      Save save = new Save();
      for (int i = 0; i < 50; i++) {
        for (int j = 0; j < 50; j++) {
          SecureRandom rand = new SecureRandom();

          if (boat2 == NULL && battleship2 == NULL && cruiser2 == NULL && destroyer2 == NULL) {
            System.out.println("Second");
            win.setText("Первый компьютер победил!!!");
            save.saveArrayToFile(saveFirstField, saveSecondField, saveFirstBattle, saveSecondBattle,
                "d:\\Rеplay.txt");
            return;
          }
          if (boat1 == NULL && battleship1 == NULL && cruiser1 == NULL && destroyer1 == NULL) {
            System.out.println("First");
            win.setText("Второй компьютер победил!!!");
            save.saveArrayToFile(saveFirstField, saveSecondField, saveFirstBattle, saveSecondBattle,
                "d:\\Replay.txt");
          return;
          }
          if (first == false) {
            int x = (rand.nextInt(SIZE));
            int y = (rand.nextInt(SIZE));
            newCheck = checkForShipFirst;

            if (newCheck[x][y] == -1) {
              second = false;
              first = false;
            } else {
              if (newCheck[x][y] != NULL) {
                second = false;
                first = false;
                field = firstField;
                boat1 = boatCheck(field, x, y, boat1); // Boat
                destroyer1 = destroyerCheck(field, x, y, destroyer1); // Destroyer
                cruiser1 = cruiserCheck(field, x, y, cruiser1); // Cruiser
                battleship1 = battleshipCheck(field, x, y, battleship1, 1); // Battleship
                positionFirst++;
                saveFirstBattle[x][y] = positionFirst;
              }
              if (newCheck[x][y] == NULL) {
                firstField[x][y].setStyle("-fx-base: lightgrey");
                firstField[x][y].setOpacity(0);
                newCheck[x][y] = -1;
                second = true;
                first = true;
                positionFirst++;
                saveFirstBattle[x][y] = positionFirst;
              }
            }
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
            }
          }

          if (second) {
            int positionX = (rand.nextInt(SIZE));
            int positionY = (rand.nextInt(SIZE));
            newCheck = checkForShipSecond;

            if (newCheck[positionX][positionY] == -1) {
              second = true;
              first = true;
            } else {
              if (newCheck[positionX][positionY] != NULL) {
                second = true;
                first = true;
                field = secondField;
                boat2 = boatCheck(field, positionX, positionY, boat2);
                destroyer2 = destroyerCheck(field, positionX, positionY, destroyer2);
                cruiser2 = cruiserCheck(field, positionX, positionY, cruiser2);
                battleship2 = battleshipCheck(field, positionX, positionY, battleship2, 1);
                positionFirst++;
                saveSecondBattle[positionX][positionY] = positionFirst;
              }
              if (newCheck[positionX][positionY] == NULL) {
                secondField[positionX][positionY].setStyle("-fx-base: lightgrey");
                secondField[positionX][positionY].setOpacity(0);
                newCheck[positionX][positionY] = -1;
                second = false;
                first = false;
                positionFirst++;
                saveSecondBattle[positionX][positionY] = positionFirst;
              }
            }
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
            }
          }
        }
      }
    }
  }

  // System.out.println(...) use for check in console
  public int battleshipCheck(Button[][] field, int x, int y, int Battleship, int stroke) {
    int count = NULL;
    if (Battleship == NULL) {
      return NULL;
    }
    if (newCheck[x][y] == FOUR && stroke == ONE) {

      for (int k = x; k < x + FOUR; k++) {
        if (k < SIZE && newCheck[k][y] == 8) {
          count++;
        }
      }
      if (count < THREE) {
        for (int k = x - FOUR; k < x; k++) {
          if (k >= NULL && newCheck[k][y] == 8) {
            count++;
          }
        }
      }
      if (count < THREE) {
        for (int k = y - FOUR; k < y; k++) {
          if (k >= NULL && newCheck[x][k] == 8) {
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
        count = NULL;
      }
      newCheck[x][y] = 8;
      field[x][y].setStyle("-fx-base: blue");
    }
    return Battleship;
  }

  public int cruiserCheck(Button[][] field, int x, int y, int cruiser) {
    int count = NULL;
    if (cruiser == NULL) {
      return NULL;
    }
    if (newCheck[x][y] == THREE) {
      for (int k = x; k < x + THREE; k++) {
        if (k < SIZE && newCheck[k][y] == SIX) {
          count++;
        }
      }
      if (count < TWO) {
        for (int k = x - THREE; k < x; k++) {
          if (k >= NULL && newCheck[k][y] == SIX) {
            count++;
          }
        }
      }
      if (count < TWO) {
        for (int k = y - THREE; k < y; k++) {
          if (k >= NULL && newCheck[x][k] == SIX) {
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
        count = NULL;
      }
      newCheck[x][y] = SIX;
      field[x][y].setStyle("-fx-base: green");
    }
    return cruiser;
  }

  public int destroyerCheck(Button[][] field, int x, int y, int destroyer) {
    int count = NULL;
    if (destroyer == NULL) {
      return NULL;
    }
    if (newCheck[x][y] == TWO) {
      for (int k = x; k < x + TWO; k++) {
        if (k < SIZE && newCheck[k][y] == FIFE) {
          count++;
        }
      }
      if (count < ONE) {
        for (int k = x - TWO; k < x; k++) {
          if (k >= NULL && newCheck[k][y] == FIFE) {
            count++;
          }
        }
      }
      if (count < ONE) {
        for (int k = y - TWO; k < y; k++) {
          if (k >= NULL && newCheck[x][k] == FIFE) {
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
        count = NULL;
      }
      newCheck[x][y] = FIFE;
      field[x][y].setStyle("-fx-base: lightgrey");
    }
    return destroyer;
  }

  public int boatCheck(Button[][] field, int x, int y, int boat) {
    if (boat == NULL) {
      return NULL;
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


