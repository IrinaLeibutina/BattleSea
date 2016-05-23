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

import java.io.File;
import java.security.SecureRandom;

public class TwoComp extends Ships {

  public boolean first = false;
  public boolean second = true;
  Field fieldForBattle = new Field();
  Button[][] firstField = fieldForBattle.createFieldForBattle(ONE);
  Button[][] secondField = fieldForBattle.createFieldForBattle(TWO);
  Button[][] field = new Button[SIZE][SIZE];
  Interface inter;

  public String str = "d:\\Save.txt";

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
    Pane battle = new Pane();
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
    // Text for button
    Text win = new Text();
    win.setLayoutX(350);
    win.setLayoutY(250);
    win.setFont(Font.font("Arial", FontPosture.ITALIC, 32));
    win.setFill(Color.DARKSALMON);

    Text game = new Text("»√–¿");
    game.setFill(Color.FIREBRICK);
    game.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    game.setLayoutX(550);
    game.setLayoutY(568);

    Text winner = new Text("œŒ¡≈ƒ»“≈À‹");
    winner.setFill(Color.FIREBRICK);
    winner.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    winner.setLayoutX(268);
    winner.setLayoutY(568);

    Text replay = new Text("œŒ¬“Œ– »√–€");
    replay.setFill(Color.FIREBRICK);
    replay.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    replay.setLayoutX(15);
    replay.setLayoutY(568);

    // Buttons
    Rectangle playGame = new Rectangle(130, 25, Color.DARKSALMON);
    playGame.setOpacity(TRANSPARENCY);
    playGame.setLayoutX(510);
    playGame.setLayoutY(550);
    FillTransition fillTrasition = new FillTransition(Duration.seconds(TRANSPARENCY), playGame);
    playGame.setOnMouseEntered(event -> {
      fillTrasition.setFromValue(Color.YELLOW);
      fillTrasition.setToValue(Color.CORNSILK);
      fillTrasition.setCycleCount(Animation.INDEFINITE);
      fillTrasition.setAutoReverse(true);
      fillTrasition.play();
    });
    playGame.setOnMouseExited(event -> {
      fillTrasition.stop();
      playGame.setFill(Color.DARKSALMON);
    });

    Rectangle getWinner = new Rectangle(130, 25, Color.DARKSALMON);
    getWinner.setOpacity(TRANSPARENCY);
    getWinner.setLayoutX(260);
    getWinner.setLayoutY(550);
    FillTransition fillTrasition3 = new FillTransition(Duration.seconds(TRANSPARENCY), getWinner);
    getWinner.setOnMouseEntered(event -> {
      fillTrasition3.setFromValue(Color.YELLOW);
      fillTrasition3.setToValue(Color.CORNSILK);
      fillTrasition3.setCycleCount(Animation.INDEFINITE);
      fillTrasition3.setAutoReverse(true);
      fillTrasition3.play();
    });
    getWinner.setOnMouseExited(event -> {
      fillTrasition3.stop();
      getWinner.setFill(Color.DARKSALMON);
    });

    Rectangle repeatGame = new Rectangle(130, 25, Color.DARKSALMON);
    repeatGame.setOpacity(TRANSPARENCY);
    repeatGame.setLayoutX(10);
    repeatGame.setLayoutY(550);
    FillTransition fillTrasition2 = new FillTransition(Duration.seconds(TRANSPARENCY), repeatGame);
    repeatGame.setOnMouseEntered(event -> {
      fillTrasition2.setFromValue(Color.YELLOW);
      fillTrasition2.setToValue(Color.CORNSILK);
      fillTrasition2.setCycleCount(Animation.INDEFINITE);
      fillTrasition2.setAutoReverse(true);
      fillTrasition2.play();
    });
    repeatGame.setOnMouseExited(event -> {
      fillTrasition2.stop();
      repeatGame.setFill(Color.DARKSALMON);
    });

    Ships computerShips = new Ships();
    computerShips.arrangeShips(primaryStage, FIFE);
    // Save fields
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        saveFirstField[i][j] = checkForShipFirst[i][j];
        saveSecondField[i][j] = checkForShipSecond[i][j];
      }
    }

    playGame.setOnMouseClicked(event -> {

      MyThread thread = new MyThread();
      try {
        thread.start();
      } catch (IllegalThreadStateException e) {
        e.printStackTrace();
      }
    });

    getWinner.setOnMouseClicked(event2 -> {
      if (boat1 == FLAG && battleship1 == FLAG && cruiser1 == FLAG && destroyer1 == FLAG) {
        win.setText("¬ÚÓÓÈ »„ÓÍ œÓ·Â‰ËÎ!!!");
        battle.getChildren().add(win);
        System.out.println("¬ÚÓÓÈ ");
        return;
      }
      if (boat2 == FLAG && battleship2 == FLAG && cruiser2 == FLAG && destroyer2 == FLAG) {
        win.setText("œÂ‚˚È »„ÓÍ œÓ·Â‰ËÎ!!!");
        battle.getChildren().addAll(win);
        System.out.println("œÂ‚˚È");
        return;
      }
      System.out.println("123456789");
    });

    repeatGame.setOnMouseClicked(event -> {
      playGame(primaryStage);
    });

    battle.getChildren().addAll(game, replay, playGame, winner, getWinner, repeatGame);
  }

  public void playGame(Stage primaryStage) {
    Pane battle = new Pane();
    Scene scene = new Scene(battle, WEIGHT, HEIGHT);
    Battle bat = new Battle();
    bat.getInterface(battle, primaryStage);

    // Button for replay
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

    Text replay = new Text("œŒ¬“Œ– »√–€");
    replay.setFill(Color.FIREBRICK);
    replay.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    replay.setLayoutX(470);
    replay.setLayoutY(568);

    fieldForBattle.fieldNameForBattle(battle);
    repeat(battle, primaryStage);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void repeat(Pane battle, Stage primaryStage) {
    firstField = fieldForBattle.createFieldForBattle(ONE);
    secondField = fieldForBattle.createFieldForBattle(TWO);
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

    Text replay = new Text("œŒ¬“Œ– »√–€");
    replay.setFill(Color.FIREBRICK);
    replay.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    replay.setLayoutX(460);
    replay.setLayoutY(568);
    battle.getChildren().addAll(replay, repeat);

    AllGames all = new AllGames();


    String path = all.getPath();
    System.out.println("PATH" + path);

    if (path == "1") {
    } else {
      str = path;
    }

    repeat.setOnMouseClicked(event -> {
      ThreadForReplay thread = new ThreadForReplay();
      try {
        thread.start();
      } catch (IllegalThreadStateException e) {
        e.printStackTrace();
      }
    });
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
        System.out.println("◊ÂÚ˚ÂıÔ‡ÎÛ·Ì˚È");
        Battleship--;
        count = FLAG;
      }
      newCheck[x][y] = 8;
      field[x][y].setStyle("-fx-base: blue");
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
        System.out.println("“ÂıÔ‡ÎÛ·Ì˚È"); ////
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
        System.out.println("ƒ‚ÛıÔ‡ÎÛ·Ì˚È"); ////
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
      System.out.println("Œ‰ÌÓÔ‡ÎÛ·Ì˚È"); ////
      field[x][y].setStyle("-fx-base: yellow");
      boat--;
    }
    return boat;
  }

  class MyThread extends Thread {
    public void run() {
      /*
       * Save save = new Save(); for (int number = 523; number <= 2100; number++) { String path =
       * "d:\\RÂplay" + number + ".txt"; checkForShipFirst = save.loadArrayFromFile(path, ONE);
       * checkForShipSecond = save.loadArrayFromFile(path, TWO); saveSecondBattle =
       * save.loadArrayFromFile(path, FOUR); saveFirstBattle = save.loadArrayFromFile(path, THREE);
       * 
       * path = "d:\\RÂplay" + (number+9124) + ".txt"; save.saveArrayToFile(saveFirstField,
       * saveSecondField, saveFirstBattle, saveSecondBattle, path); // path = "d:\\RÂplay" +
       * (number*2+1) + ".txt"; // save.saveArrayToFile(saveFirstField, saveSecondField,
       * saveFirstBattle, saveSecondBattle, /// path); }
       */
      // Update
      positionFirst = FLAG;
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



      Text win = new Text();
      win.setLayoutX(350);
      win.setLayoutY(250);
      win.setFont(Font.font("Arial", FontPosture.ITALIC, 32));
      win.setFill(Color.DARKSALMON);

      // Time
      long start = System.nanoTime();
      Save save = new Save();


      String path1 = "d:\\Save.txt";
      for (int i = 0; i < 40; i++) {
        for (int j = 0; j < 40; j++) {
          SecureRandom rand = new SecureRandom();

          if (boat2 == FLAG && battleship2 == FLAG && cruiser2 == FLAG && destroyer2 == FLAG
              || boat1 == FLAG && battleship1 == FLAG && cruiser1 == FLAG && destroyer1 == FLAG) {
            save.saveArrayToFile(saveFirstField, saveSecondField, saveFirstBattle, saveSecondBattle,
                path1);
                // positionFirst = FLAG;
                // first = false;
                // second = false;
                /*
                 * boat1 = FOUR; destroyer1 = THREE; cruiser1 = TWO; battleship1 = ONE; boat2 =
                 * FOUR; destroyer2 = THREE; cruiser2 = TWO; battleship2 = ONE;
                 */

            // checkForShipFirst = save.loadArrayFromFile("d:\\RÂplay.txt", ONE);
            // checkForShipSecond = save.loadArrayFromFile("d:\\RÂplay.txt", TWO);

            // checkForShipFirst = saveFirstField;
            // checkForShipSecond = saveSecondField;
            i = 50;
            j = 50;
            break;
          }


          if (boat2 == FLAG && battleship2 == FLAG && cruiser2 == FLAG && destroyer2 == FLAG) {
            System.out.println("First");
            win.setText("œÂ‚˚È ÍÓÏÔ¸˛ÚÂ ÔÓ·Â‰ËÎ!!!");

            i = 100;
            j = 100;

            //


            break;
          }
          if (boat1 == FLAG && battleship1 == FLAG && cruiser1 == FLAG && destroyer1 == FLAG) {
            System.out.println("Second");
            win.setText("¬ÚÓÓÈ ÍÓÏÔ¸˛ÚÂ ÔÓ·Â‰ËÎ!!!");
            // save.saveArrayToFile(saveFirstField, saveSecondField, saveFirstBattle,
            // saveSecondBattle,
            // "d:\\Replay.txt");
            i = 100;
            j = 100;
            break;
          }
          if (first == false) {
            int x = (rand.nextInt(SIZE));
            int y = (rand.nextInt(SIZE));
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
                battleship1 = battleshipCheck(field, x, y, battleship1, 1); // Battleship
                positionFirst++;
                saveFirstBattle[x][y] = positionFirst;
              } else {
                if (newCheck[x][y] == FLAG) {
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
            try {
              Thread.sleep(10);
            } catch (InterruptedException e) {
            }
          }

          if (second) {
            int positionX = (rand.nextInt(SIZE));
            int positionY = (rand.nextInt(SIZE));
            newCheck = checkForShipSecond;

            if (newCheck[positionX][positionY] == -1 || newCheck[positionX][positionY] == 8
                || newCheck[positionX][positionY] == SIX || newCheck[positionX][positionY] == FIFE
                || newCheck[positionX][positionY] == 9) {
              second = true;
              first = true;
            } else {
              if (newCheck[positionX][positionY] != FLAG) {
                second = true;
                first = true;
                field = secondField;
                boat2 = boatCheck(field, positionX, positionY, boat2);
                destroyer2 = destroyerCheck(field, positionX, positionY, destroyer2);
                cruiser2 = cruiserCheck(field, positionX, positionY, cruiser2);
                battleship2 = battleshipCheck(field, positionX, positionY, battleship2, 1);
                positionFirst++;
                saveSecondBattle[positionX][positionY] = positionFirst;
              } else {
                if (newCheck[positionX][positionY] == FLAG) {
                  // secondField[positionX][positionY].setStyle("-fx-base: lightgrey");
                  secondField[positionX][positionY].setOpacity(0);
                  newCheck[positionX][positionY] = -1;
                  second = false;
                  first = false;
                  positionFirst++;
                  saveSecondBattle[positionX][positionY] = positionFirst;
                }
              }
            }
            try {
              Thread.sleep(10);
            } catch (InterruptedException e) {
            }
          }
        }
      }

      long end = System.nanoTime();
      long traceTime = end - start;

      System.out.println(traceTime);


      // For Check in console
      /*
       * for (int i = 0; i < SIZE; i++) { System.out.println(" "); for (int j = 0; j < SIZE; j++) {
       * System.out.print(saveFirstBattle[i][j] + " "); } }
       * 
       * for (int i = 0; i < SIZE; i++) { System.out.println(" "); for (int j = 0; j < SIZE; j++) {
       * System.out.print(saveSecondBattle[i][j] + " "); } } for (int i = 0; i < 10; i++) { for (int
       * j = 0; j < 10; j++) { if (saveFirstBattle[0][0] == positionSecond || saveSecondBattle[0][0]
       * == positionSecond) { System.out.println(positionSecond); positionSecond++; } else { if
       * (saveFirstBattle[i][j] == positionSecond || saveSecondBattle[i][j] == positionSecond) {
       * System.out.println(positionSecond); positionSecond++; i = 0; j = 0; } } } } }
       */
    }
  }


  class ThreadForReplay extends Thread {
    public void run() {
      // Get data from file

      File f = new File("d:\\Replay\\RÂplay2.txt");
      long len = f.length();

      System.out.println("SIZE" + len);
      Save save = new Save();
      long start = System.nanoTime();
      saveFirstBattle = save.loadArrayFromFile(str, THREE);
      long end = System.nanoTime();
      long traceTime = end - start;

      System.out.println(traceTime);
      saveSecondBattle = save.loadArrayFromFile(str, FOUR);
      saveFirstField = save.loadArrayFromFile(str, ONE);
      saveSecondField = save.loadArrayFromFile(str, TWO);
      // Check in console
      for (int i = 0; i < SIZE; i++) {
        System.out.println(" ");
        for (int j = 0; j < SIZE; j++) {
          System.out.print(saveFirstBattle[i][j] + " ");
        }
      }
      for (int i = 0; i < SIZE; i++) {
        System.out.println(" ");
        for (int j = 0; j < SIZE; j++) {
          System.out.print(saveSecondBattle[i][j] + " ");
        }
      }
      // Update
      positionSecond = ONE;
      boat1 = FOUR;
      destroyer1 = THREE;
      cruiser1 = TWO;
      battleship1 = ONE;
      boat2 = FOUR;
      destroyer2 = THREE;
      cruiser2 = TWO;
      battleship2 = ONE;
      for (int i = FLAG; i < SIZE; i++) {
        for (int j = FLAG; j < SIZE; j++) {

          if (boat1 == FLAG && battleship1 == FLAG && cruiser1 == FLAG && destroyer1 == FLAG) {
            System.out.println("¬ÚÓÓÈ ");
            return;
          }
          if (boat2 == FLAG && battleship2 == FLAG && cruiser2 == FLAG && destroyer2 == FLAG) {
            System.out.println("œÂ‚˚È");
            return;
          }

          if (saveFirstBattle[i][j] == positionSecond || saveFirstBattle[0][0] == positionSecond) {
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
              battleship1 = battleshipCheck(field, x, y, battleship1, 1); // Battleship
            } else {
              firstField[x][y].setStyle("-fx-base: lightgrey");
              firstField[x][y].setOpacity(FLAG);
              newCheck[x][y] = -1;
            }
            i = FLAG;
            j = FLAG;
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
            }
          }

          if (saveSecondBattle[i][j] == positionSecond
              || saveSecondBattle[0][0] == positionSecond) {
            int x = i;
            int y = j;
            newCheck = saveSecondField;
            if (newCheck[x][y] != FLAG) {
              field = secondField;
              boat2 = boatCheck(field, x, y, boat2); // Boat
              battleship2 = battleshipCheck(field, x, y, battleship2, 1); // Destroyer
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
            // try {
            // Thread.sleep(100);
            // } catch (InterruptedException e) {
            // }
          }
        }
      }
    }
  }
}
