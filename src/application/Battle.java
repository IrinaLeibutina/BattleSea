package application;

import application.Ships;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import application.Main;
import static application.Constants.*;

/**
 * In Battle class realized game between two people. Rule: Fight is alternate shots players. When
 * you hit an enemy ship in combat participant is able to holding an extraordinary shot. The game
 * ends in the destruction of one of the participants of all the enemy ships.
 */

public class Battle extends Ships {

  public boolean first = false;
  public boolean second = true;
  Button[][] field = new Button[SIZE][SIZE];
  Field fieldForBattle = new Field();
  Button[][] firstField = fieldForBattle.createFieldForBattle(ONE);
  Button[][] secondField = fieldForBattle.createFieldForBattle(TWO);
  Interface inter;

  public Battle() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        field[i][j] = new Button();
        saveFirstField[i][j] = checkForShipFirst[i][j];
        saveSecondField[i][j] = checkForShipSecond[i][j];
        saveFirstBattle[i][j] = 0;
        saveSecondBattle[i][j] = 0;
      }
    }
    inter = new Interface();
  }

  // Create main function
  public void CreateBattleField(Stage primaryStage, int level) {
    Pane battle = new Pane();
    Scene scene = new Scene(battle, WEIGHT, HEIGHT);
    getInterface(battle, primaryStage);
    fieldForBattle.fieldNameForBattle(battle);
    BeginBattle(battle, primaryStage);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void playGame(Stage primaryStage, Text win) {
    Pane battle = new Pane();
    Scene scene = new Scene(battle, WEIGHT, HEIGHT);
    getInterface(battle, primaryStage);
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

    Text replay = new Text("ПОВТОР ИГРЫ");
    replay.setFill(Color.FIREBRICK);
    replay.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    replay.setLayoutX(470);
    replay.setLayoutY(568);

    battle.getChildren().addAll(replay, repeat);

    repeat.setOnMouseClicked(event -> {
      getInterface(battle, primaryStage);
      fieldForBattle.fieldNameForBattle(battle);
      repeat(battle, primaryStage);
    });

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void repeat(Pane battle, Stage primaryStage) {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        battle.getChildren().addAll(firstField[i][j], secondField[i][j]);
      }
    }
    // Update
    first = false;
    second = false;
    boat1 = FOUR;
    destroyer1 = THREE;
    cruiser1 = TWO;
    battleship1 = ONE;
    boat2 = FOUR;
    destroyer2 = THREE;
    cruiser2 = TWO;
    battleship2 = ONE;

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

    Rectangle getWinner = new Rectangle(150, 25, Color.DARKSALMON);
    getWinner.setOpacity(TRANSPARENCY);
    getWinner.setLayoutX(150);
    getWinner.setLayoutY(550);
    FillTransition fillTrasitionSecond =
        new FillTransition(Duration.seconds(TRANSPARENCY), getWinner);
    getWinner.setOnMouseEntered(event -> {
      fillTrasitionSecond.setFromValue(Color.YELLOW);
      fillTrasitionSecond.setToValue(Color.CORNSILK);
      fillTrasitionSecond.setCycleCount(Animation.INDEFINITE);
      fillTrasitionSecond.setAutoReverse(true);
      fillTrasitionSecond.play();
    });
    getWinner.setOnMouseExited(event -> {
      fillTrasitionSecond.stop();
      getWinner.setFill(Color.DARKSALMON);
    });

    Text replay = new Text("ПОВТОР ИГРЫ");
    replay.setFill(Color.FIREBRICK);
    replay.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    replay.setLayoutX(460);
    replay.setLayoutY(568);

    Text winner = new Text("ПОБЕДИТЕЛЬ");
    winner.setFill(Color.FIREBRICK);
    winner.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    winner.setLayoutX(166);
    winner.setLayoutY(568);
    battle.getChildren().addAll(replay, repeat, winner, getWinner);

    Text win = new Text();
    win.setLayoutX(330);
    win.setLayoutY(250);
    win.setFont(Font.font("Arial", FontPosture.ITALIC, 32));
    win.setFill(Color.DARKSALMON);

    repeat.setOnMouseClicked(event -> {
      MyThread thread = new MyThread();
      try {
        thread.start();

      } catch (IllegalThreadStateException e) {
        e.printStackTrace();
      }
    });

    getWinner.setOnMouseClicked(event -> {
      if (boat1 == FLAG && battleship1 == FLAG && cruiser1 == FLAG && destroyer1 == FLAG) {
        System.out.println("Второй ");
        win.setText("Победил Второй игрок!!!");
        battle.getChildren().addAll(win);
        return;
      }
      if (boat2 == FLAG && battleship2 == FLAG && cruiser2 == FLAG && destroyer2 == FLAG) {

        System.out.println("Первый");
        win.setText("Победил Первый игрок!!!");
        battle.getChildren().addAll(win);
        return;
      }
    });
  }

  public void getInterface(Pane battle, Stage primaryStage) {
    // Create image for background
    ImageView background = inter.createBackground();
    battle.getChildren().addAll(background);

    // Create button to return
    Rectangle comeBack = inter.createRectangle();
    comeBack.setOnMouseClicked(event -> {
      Main mainMenu = new Main();
      mainMenu.start(primaryStage);
    });

    Text name = inter.createTextForComeBack();
    battle.getChildren().addAll(name, comeBack);
  }

  public void BeginBattle(Pane battle, Stage primaryStage) {
    // Create field for battle
    Button[][] firstField = fieldForBattle.createFieldForBattle(ONE);
    Button[][] secondField = fieldForBattle.createFieldForBattle(TWO);

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        battle.getChildren().addAll(firstField[i][j], secondField[i][j]);
      }
    }
    // Update
    boat1 = FOUR;
    destroyer1 = THREE;
    cruiser1 = TWO;
    battleship1 = ONE;
    boat2 = FOUR;
    destroyer2 = THREE;
    cruiser2 = TWO;
    battleship2 = ONE;
    positionFirst = FLAG;

    Text win = new Text();
    win.setLayoutX(330);
    win.setLayoutY(250);
    win.setFont(Font.font("Arial", FontPosture.ITALIC, 32));
    win.setFill(Color.DARKSALMON);

    for (int i = FLAG; i < SIZE; i++) {
      for (int j = FLAG; j < SIZE; j++) {
        int x = i;
        int y = j;
        Save save = new Save();

        // Battle
        firstField[i][j].setOnMouseClicked(event -> {
          if (first == false) {

            if (boat1 == FLAG && battleship1 == FLAG && cruiser1 == FLAG && destroyer1 == FLAG) {
              win.setText("Второй Игрок Победил!!!");
              playGame(primaryStage, win);
              save.saveArrayToFile(saveFirstField, saveSecondField, saveFirstBattle,
                  saveSecondBattle, "d:\\Players.txt");

              /// For Check
              for (int s = 0; s < SIZE; s++) {
                System.out.println(" ");
                for (int z = 0; z < SIZE; z++) {
                  System.out.print(saveFirstField[s][z]);
                }
              }
              System.out.println("Первоначальная");
              for (int s = 0; s < SIZE; s++) {
                System.out.println(" ");
                for (int z = 0; z < SIZE; z++) {
                  System.out.print(checkForShipFirst[s][z]);
                }
              }
              return;
            }

            newCheck = checkForShipFirst;

            if (newCheck[x][y] == -1 || newCheck[x][y] == 8 || newCheck[x][y] == SIX
                || newCheck[x][y] == FIFE || newCheck[x][y] == 9) {
              second = false;
              first = false;
            } else {
              if (newCheck[x][y] != FLAG) {
                second = false;
                first = false;
                field = firstField;
                boat1 = boatCheck(field, x, y, boat1); // Boat
                destroyer1 = destroyerCheck(field, x, y, destroyer1); // Destroyer
                cruiser1 = cruiserCheck(field, x, y, cruiser1); // Cruiser
                battleship1 = battleshipCheck(field, x, y, battleship1); // Battleship
                positionFirst++;
                saveFirstBattle[x][y] = positionFirst;
              } else {
                firstField[x][y].setStyle("-fx-base: lightgrey");
                firstField[x][y].setOpacity(0);
                newCheck[x][y] = -1;
                second = true;
                first = true;
                positionFirst++;
                saveFirstBattle[x][y] = positionFirst;
              }
            }
          }
        });

        secondField[i][j].setOnMouseClicked(event -> {
          if (second) {

            if (boat2 == FLAG && battleship2 == FLAG && cruiser2 == FLAG && destroyer2 == FLAG) {
              win.setText("Первый Игрок Победил!!!");
              playGame(primaryStage, win);
              save.saveArrayToFile(saveFirstField, saveSecondField, saveFirstBattle,
                  saveSecondBattle, "d:\\Players.txt");
              return;
            }

            newCheck = checkForShipSecond;

            if (newCheck[x][y] == -1 || newCheck[x][y] == 8 || newCheck[x][y] == SIX
                || newCheck[x][y] == FIFE || newCheck[x][y] == 9) {
              second = true;
              first = true;
            } else {
              if (newCheck[x][y] != FLAG) {
                second = true;
                first = true;
                field = secondField;
                boat2 = boatCheck(field, x, y, boat2); // Boat
                destroyer2 = destroyerCheck(field, x, y, destroyer2); // Destroyer
                cruiser2 = cruiserCheck(field, x, y, cruiser2); // Cruiser
                battleship2 = battleshipCheck(field, x, y, battleship2); // Battleship
                positionFirst++;
                saveSecondBattle[x][y] = positionFirst;
              } else {
                secondField[x][y].setStyle("-fx-base: lightgrey");
                secondField[x][y].setOpacity(0);
                newCheck[x][y] = -1;
                second = false;
                first = false;
                positionFirst++;
                saveSecondBattle[x][y] = positionFirst;
              }
            }
          }
        });
      }
    }
  }

  /**
   * Battleship check
   * 
   * @param field
   * @param x
   * @param y
   * @param battleship
   * @return
   */
  public int battleshipCheck(Button[][] field, int x, int y, int battleship) {
    int count = FLAG;
    if (battleship == FLAG) {
      return FLAG;
    }
    if (newCheck[x][y] == FOUR) {
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
        System.out.println("Четырехпалубный"); // Use for check in console
        battleship--;
        count = FLAG;
      }
      newCheck[x][y] = 8;
      field[x][y].setStyle("-fx-base: lightblue");
    }
    return battleship;
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
        System.out.println("Трехпалубный"); // Use for check in console
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
        System.out.println("Двухпалубный"); // Use for check in console
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
      System.out.println("Однопалубный"); // Use for check in console
      field[x][y].setStyle("-fx-base: yellow");
      boat--;
    }
    return boat;
  }

  class MyThread extends Thread {
    public void run() {
      // Update
      boat1 = FOUR;
      destroyer1 = THREE;
      cruiser1 = TWO;
      battleship1 = ONE;
      boat2 = FOUR;
      destroyer2 = THREE;
      cruiser2 = TWO;
      battleship2 = ONE;
      positionSecond = 1;
      for (int i = FLAG; i < SIZE; i++) {
        for (int j = FLAG; j < SIZE; j++) {

          if (boat1 == FLAG && battleship1 == FLAG && cruiser1 == FLAG && destroyer1 == FLAG) {
            System.out.println("Второй ");
            return;
          }
          if (boat2 == FLAG && battleship2 == FLAG && cruiser2 == FLAG && destroyer2 == FLAG) {
            System.out.println("Первый");
            return;
          }

          if (saveFirstBattle[i][j] == positionSecond) {
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
              battleship1 = battleshipCheck(field, x, y, battleship1); // Battleship
            } else {
              firstField[x][y].setStyle("-fx-base: lightgrey");
              firstField[x][y].setOpacity(FLAG);
              newCheck[x][y] = -1;
            }
            i = FLAG;
            j = FLAG;
            try {
              Thread.sleep(300);
            } catch (InterruptedException e) {
            }
          }

          if (saveSecondBattle[i][j] == positionSecond) {
            int x = i;
            int y = j;
            newCheck = saveSecondField;
            if (newCheck[x][y] != FLAG) {
              field = secondField;
              boat2 = boatCheck(field, x, y, boat2); // Boat
              battleship2 = battleshipCheck(field, x, y, battleship2); // Destroyer
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
            try {
              Thread.sleep(300);
            } catch (InterruptedException e) {
            }
          }
        }
      }
    }
  }
}


