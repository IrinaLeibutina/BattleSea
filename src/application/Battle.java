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
  Button[][] firstField = fieldForBattle.createFieldForBattle(1);
  Button[][] secondField = fieldForBattle.createFieldForBattle(2);
  Interface inter;
  public int some = 0;
  // Field fieldForBattle;

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
    // fieldForBattle = new Field();
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
    repeat.setOpacity(0.5);
    repeat.setLayoutX(450);
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

    Text text = new Text("ПОВТОР ИГРЫ");
    text.setFill(Color.FIREBRICK);

    text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    text.setLayoutX(470);
    text.setLayoutY(568);

    battle.getChildren().addAll(text, repeat);

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

    Rectangle repeat = new Rectangle(150, 25, Color.DARKSALMON);
    repeat.setOpacity(0.5);
    repeat.setLayoutX(450);
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

    Text text = new Text("ПОВТОР ИГРЫ");
    text.setFill(Color.FIREBRICK);

    text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    text.setLayoutX(460);
    text.setLayoutY(568);
    battle.getChildren().addAll(text, repeat);

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

    Text text = inter.createTextForComeBack();
    battle.getChildren().addAll(text, comeBack);
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

    Text win = new Text();
    win.setLayoutX(330);
    win.setLayoutY(250);
    win.setFont(Font.font("Arial", FontPosture.ITALIC, 32));
    win.setFill(Color.DARKSALMON);

    for (int i = NULL; i < SIZE; i++) {
      for (int j = NULL; j < SIZE; j++) {
        int x = i;
        int y = j;
        Save save = new Save();

        // Battle
        firstField[i][j].setOnMouseClicked(event -> {
          if (first == false) {

            if (boat1 == NULL && battleship1 == NULL && cruiser1 == NULL && destroyer1 == NULL) {
              win.setText("Второй Игрок Победил!!!");
              playGame(primaryStage, win);
              save.saveArrayToFile(saveFirstField, saveSecondField, saveFirstBattle,
                  saveSecondBattle, "d:\\Replay.txt");

              /// For Check
              for (int s = 0; s < SIZE; s++) {
                System.out.println(" ");
                for (int z = 0; z < SIZE; z++) {
                  System.out.print(saveFirstField[s][z]);
                }
              }
              System.out.println("ПЕрвоначальная");
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
              if (newCheck[x][y] != NULL) {
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

            if (boat2 == NULL && battleship2 == NULL && cruiser2 == NULL && destroyer2 == NULL) {
              win.setText("Первый Игрок Победил!!!");
              playGame(primaryStage, win);
              save.saveArrayToFile(saveFirstField, saveSecondField, saveFirstBattle,
                  saveSecondBattle, "d:\\Rеplay.txt");
              return;
            }

            newCheck = checkForShipSecond;

            if (newCheck[x][y] == -1 || newCheck[x][y] == 8 || newCheck[x][y] == SIX
                || newCheck[x][y] == FIFE || newCheck[x][y] == 9) {
              second = true;
              first = true;
            } else {
              if (newCheck[x][y] != NULL) {
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
    int count = NULL;
    if (battleship == NULL) {
      return NULL;
    }
    if (newCheck[x][y] == FOUR) {
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
        System.out.println("Четырехпалубный"); // Use for check in console
        battleship--;
        count = NULL;
      }
      newCheck[x][y] = 8;
      field[x][y].setStyle("-fx-base: lightblue");
    }
    return battleship;
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
        System.out.println("Трехпалубный"); // Use for check in console
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
        System.out.println("Двухпалубный"); // Use for check in console
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
      System.out.println("Однопалубный"); // Use for check in console
      field[x][y].setStyle("-fx-base: yellow");
      boat--;
    }
    return boat;
  }

  class MyThread extends Thread {
    // Update
    int winner = 0;

    public void run() {
      boat1 = FOUR;
      destroyer1 = THREE;
      cruiser1 = TWO;
      battleship1 = ONE;
      boat2 = FOUR;
      destroyer2 = THREE;
      cruiser2 = TWO;
      battleship2 = ONE;
      for (int i = NULL; i < SIZE; i++) {
        for (int j = NULL; j < SIZE; j++) {

          if (boat1 == NULL && battleship1 == NULL && cruiser1 == NULL && destroyer1 == NULL
              && key == NULL) {
            // win.setText("Второй Игрок Победил!!!");
            // battle.getChildren().add(win);
            System.out.println("Второй ");
            winner = 1;
            return;
          }
          if (boat2 == NULL && battleship2 == NULL && cruiser2 == NULL && destroyer2 == NULL
              && key == NULL) {
            // win.setText("Первый Игрок Победил!!!");
            // battle.getChildren().addAll(win);
            winner = 2;
            System.out.println("Первый");
            return;
          }

          if (saveFirstBattle[i][j] == positionSecond) {
            int x = i;
            int y = j;
            System.out.println("\nFindFirst" + positionSecond);
            positionSecond++;
            newCheck = saveFirstField;

            if (newCheck[x][y] != NULL) {
              field = firstField;
              boat1 = boatCheck(field, x, y, boat1); // Boat
              destroyer1 = destroyerCheck(field, x, y, destroyer1); // Destroyer
              cruiser1 = cruiserCheck(field, x, y, cruiser1); // Cruiser
              battleship1 = battleshipCheck(field, x, y, battleship1); // Battleship
            } else {
              firstField[x][y].setStyle("-fx-base: lightgrey");
              firstField[x][y].setOpacity(NULL);
              newCheck[x][y] = -1;
            }
            i = NULL;
            j = NULL;
            try {
              Thread.sleep(300); 
            } catch (InterruptedException e) {
            }
          }

          if (saveSecondBattle[i][j] == positionSecond) {
            int x = i;
            int y = j;
            newCheck = saveSecondField;
            if (newCheck[x][y] != NULL) {
              field = secondField;
              boat2 = boatCheck(field, x, y, boat2); // Boat
              battleship2 = battleshipCheck(field, x, y, battleship2); // Destroyer
              cruiser2 = cruiserCheck(field, x, y, cruiser2); // Cruiser
              destroyer2 = destroyerCheck(field, x, y, destroyer2); // Battleship
            } else {
              secondField[x][y].setStyle("-fx-base: lightgrey");
              secondField[x][y].setOpacity(NULL);
              newCheck[x][y] = -1;
            }
            System.out.println("\nFindSecond" + positionSecond);
            positionSecond++;
            i = NULL;
            j = NULL;
            try {
              Thread.sleep(300); 
            } catch (InterruptedException e) {
            }
          }

        }
      }
    }

    public int getKey() {
      System.out.println("getKey = " + winner);
      return winner;
    }
  }
}
