package application;

import application.Battle;

import application.Interface.Menu;
import application.Interface.MenuBox;
import application.Interface.MenuItem;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.security.SecureRandom;
import static application.Constants.*;

public class Ships {
  public int nextPlayer = 1;
  public int choice = 0;
  Interface inter;

  public Ships() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        checkForShip[i][j] = 0;
      }
    }
    inter = new Interface();
  }

  public void arrangeShips(Stage primaryStage, int buttle) {

    Pane shipsAndField = new Pane();
    Scene scene = new Scene(shipsAndField, WEIGHT, HEIGHT);

    // Create image for background
    ImageView background = inter.createBackground();
    shipsAndField.getChildren().addAll(background);

    // Create field
    Field field = new Field();
    Button[][] ship = field.createFieldForShip();
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        shipsAndField.getChildren().addAll(ship[i][j]);
      }
    }
    field.fieldNameForShip(shipsAndField);

    /// Create Menu Button
    MenuItem battleship = new MenuItem("ËÈÍÊÎÐ(õ1)");
    MenuItem cruiser = new MenuItem("ÊÐÅÉÑÅÐ(õ2)");
    MenuItem destroyer = new MenuItem("ÝÑÌÈÍÅÖ(õ3)");
    MenuItem boat = new MenuItem("ÊÀÒÅÐ(õ4)");
    MenuItem random = new MenuItem("CËÓ×ÀÉÍÀß ÐÀÑÑÒÀÍÎÂÊÀ");
    Menu mainMenu = new Menu(battleship, cruiser, destroyer, boat, random);
    MenuBox menuBox = new MenuBox(mainMenu);
    menuBox.setVisible(true);

    // Create rectangle as a button
    shipsAndField.getChildren().addAll(menuBox);
    Rectangle choice = inter.createRectangle();

    if (buttle == FIFE) {
      // buttle = 1;
      fifthButton(ship, buttle);
      nextPlayer = ONE;
      rememberItem();
      // buttle = 6;
      fifthButton(ship, buttle);
      nextPlayer = TWO;
      rememberItem();
      return;
    }

    choice.setOnMouseClicked(event -> {
      if (buttle == FLAG) {
        if (nextPlayer == TWO) {
          rememberItem();
          Battle battle = new Battle();
          battle.CreateBattleField(primaryStage, buttle);
        }
        if (nextPlayer == ONE) {
          rememberItem();
          arrangeShips(primaryStage, FLAG);
          nextPlayer = TWO;
        }
      }
      if (buttle == TWO) {
        if (nextPlayer == TWO) {
          rememberItem();
          BattleWithComp battle = new BattleWithComp();
          battle.createBattle(primaryStage, buttle);
        }
        if (nextPlayer == ONE) {
          rememberItem();
          nextPlayer = TWO;
          fifthButton(ship, ONE);
          Text text = inter.createInfoForUser();
          shipsAndField.getChildren().addAll(text);
        }
      }
    });

    Text text = inter.createTextForButton();
    shipsAndField.getChildren().addAll(choice, text);

    firstButton(battleship, ship, buttle);
    secondButton(cruiser, ship, buttle);
    thirdButton(destroyer, ship, buttle);
    forthButton(boat, ship, buttle);
    random.setOnMouseClicked(event -> {
      fifthButton(ship, buttle);
    });

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  // Occupancy ships
  public void firstButton(MenuItem battleship, Button[][] ship, int buttle) {
    battleship.setOnMouseClicked(event -> {
      // Delete ship
      for (int i = FLAG; i < SIZE; i++) {
        for (int j = FLAG; j < SIZE; j++) {
          if (checkForShip[i][j] == FOUR) {
            checkForShip[i][j] = FLAG;
            ship[i][j].setStyle("-fx-base: lightgreen");
          }
        }
      }
      for (int i = FLAG; i < BATTLESHIP; i++) {
        i = setBattleship(ship, i, buttle);
      }
    });
  }

  public void secondButton(MenuItem cruiser, Button[][] ship, int buttle) {
    cruiser.setOnMouseClicked(event -> {
      for (int i = FLAG; i < SIZE; i++) {
        for (int j = FLAG; j < SIZE; j++) {
          if (checkForShip[i][j] == THREE) {
            checkForShip[i][j] = FLAG;
            ship[i][j].setStyle("-fx-base: lightgreen");
          }
        }
      }
      for (int i = FLAG; i < CRUISER; i++) {
        i = setCruiser(ship, i, buttle);
      }
    });
  }

  public void thirdButton(MenuItem destroyer, Button[][] ship, int buttle) {
    destroyer.setOnMouseClicked(event -> {
      for (int i = FLAG; i < SIZE; i++) {
        for (int j = FLAG; j < SIZE; j++) {
          if (checkForShip[i][j] == TWO) {
            checkForShip[i][j] = FLAG;
            ship[i][j].setStyle("-fx-base: lightgreen");
          }
        }
      }
      for (int i = FLAG; i < DESTROYER; i++) {
        i = setDestroyer(ship, i, buttle);
      }
    });
  }

  public void forthButton(MenuItem boat, Button[][] ship, int buttle) {
    boat.setOnMouseClicked(event -> {
      for (int i = FLAG; i < SIZE; i++) {
        for (int j = FLAG; j < SIZE; j++) {
          if (checkForShip[i][j] == ONE) {
            checkForShip[i][j] = FLAG;
            ship[i][j].setStyle("-fx-base: lightgreen");
          }
        }
      }
      for (int i = ONE; i < BOAT + ONE; i++) {
        i = setBoat(ship, i, buttle);
      }
    });
  }

  public void fifthButton(Button[][] ship, int buttle) {

    for (int i = FLAG; i < SIZE; i++) {
      for (int j = FLAG; j < SIZE; j++) {
        checkForShip[i][j] = FLAG;
        ship[i][j].setStyle("-fx-base: lightgreen");
      }
    }
    for (int numberOfShips = FLAG; numberOfShips < BATTLESHIP; numberOfShips++) {
      numberOfShips = setBattleship(ship, numberOfShips, buttle);
    }
    for (int numberOfShips = FLAG; numberOfShips < CRUISER; numberOfShips++) {
      numberOfShips = setCruiser(ship, numberOfShips, buttle);
    }
    for (int numberOfShips = FLAG; numberOfShips < DESTROYER; numberOfShips++) {
      numberOfShips = setDestroyer(ship, numberOfShips, buttle);
    }
    for (int numberOfShips = ONE; numberOfShips < BOAT + ONE; numberOfShips++) {
      numberOfShips = setBoat(ship, numberOfShips, buttle);
    }
  }

  // In next part you will meet large blocks of "if(){...}"
  // This is necessary, because I should check all position,
  // where boats may be arranged.Break it to function probably does
  // not make sense, because each test uses a different location.

  public int setBattleship(Button[][] ship, int numberOfShips, int buttle) {
    SecureRandom rand = new SecureRandom();
    int choice = (rand.nextInt(TWO));
    int x = (rand.nextInt(SIZE - ONE)); // Coordinates
    int y = (rand.nextInt(SIZE - ONE)); // Coordinates
    // Vertical ship
    if (choice == FLAG) {

      if (y + FOUR > SIZE) {
        numberOfShips = numberOfShips - ONE;
      } else
        for (int k = y; k < y + FOUR; k++) {
          setShipColor(ship, x, k, buttle);
          checkForShip[x][k] = FOUR;
        }
      // Horizontally ship
    } else if (choice == ONE) {
      if (x + FOUR > SIZE) {
        numberOfShips = numberOfShips - ONE;
      } else
        for (int k = x; k < x + FOUR; k++) {
          setShipColor(ship, k, y, buttle);
          checkForShip[k][y] = FOUR;
        }
    }
    return numberOfShips;
  }

  public int setCruiser(Button[][] ship, int numberOfShips, int buttle) {
    SecureRandom rand = new SecureRandom();
    int choice = (rand.nextInt(TWO));
    int x = (rand.nextInt(SIZE));
    int y = (ONE + rand.nextInt(6));
    // vertical
    if (choice == FLAG) {
      if (y + THREE > SIZE) {
        numberOfShips = numberOfShips - ONE;
      } else {
        int flag = FLAG;
        if (x == FLAG) {
          for (int k = y - ONE; k < y + FOUR; k++) {
            int chONE = x + ONE;

            if (checkForShip[x][k] == ONE || checkForShip[chONE][k] == ONE
                || checkForShip[x][k] == TWO || checkForShip[x][k] == FOUR
                || checkForShip[chONE][k] == TWO || checkForShip[chONE][k] == FOUR
                || checkForShip[chONE][k] == THREE || checkForShip[x][k] == THREE) {
              numberOfShips = numberOfShips - ONE;
              flag = ONE;
              k = y + FOUR;
            }
          }
          if (flag == FLAG) {
            for (int k = y; k < y + THREE; k++) {
              setShipColor(ship, x, k, buttle);
              checkForShip[x][k] = THREE;
            }
          }
        } else {
          if (x == 9) {
            for (int k = y - ONE; k < y + FOUR; k++) {
              int chONE = x - ONE;

              if (checkForShip[x][k] == ONE || checkForShip[chONE][k] == ONE
                  || checkForShip[x][k] == TWO || checkForShip[x][k] == FOUR
                  || checkForShip[chONE][k] == TWO || checkForShip[x][k] == FOUR
                  || checkForShip[chONE][k] == FOUR || checkForShip[chONE][k] == THREE) {
                numberOfShips = numberOfShips - ONE;
                flag = ONE;
                k = y + FOUR;
              }
            }
            if (flag == FLAG) {
              for (int k = y; k < y + THREE; k++) {
                setShipColor(ship, x, k, buttle);
                checkForShip[x][k] = THREE;
              }
            }
          } else {

            for (int k = y - ONE; k < y + FOUR; k++) {
              int chONE = x + ONE;
              int chTWO = x - ONE;

              if (checkForShip[x][k] == ONE || checkForShip[chONE][k] == ONE
                  || checkForShip[chTWO][k] == ONE || checkForShip[x][k] == TWO
                  || checkForShip[chONE][k] == TWO || checkForShip[chTWO][k] == TWO
                  || checkForShip[x][k] == FOUR || checkForShip[chONE][k] == FOUR
                  || checkForShip[chTWO][k] == FOUR || checkForShip[x][k] == THREE
                  || checkForShip[chONE][k] == THREE || checkForShip[chTWO][k] == THREE) {
                numberOfShips = numberOfShips - ONE;
                flag = ONE;
                k = y + FOUR;
              }
            }
            if (flag == FLAG) {
              for (int k = y; k < y + THREE; k++) {
                setShipColor(ship, x, k, buttle);
                checkForShip[x][k] = THREE;
              }
            }
          }
        }
      }
    }
    // Horizontally ship
    if (choice == ONE) {
      x = (ONE + rand.nextInt(6));
      y = (rand.nextInt(SIZE));
      if (x + THREE > SIZE) {
        numberOfShips = numberOfShips - ONE;
      } else {
        int flag = FLAG;
        if (y == FLAG) {
          for (int k = x - ONE; k < x + FOUR; k++) {
            int chONE = y + ONE;

            if (checkForShip[k][y] == ONE || checkForShip[k][chONE] == ONE
                || checkForShip[k][y] == TWO || checkForShip[k][chONE] == TWO
                || checkForShip[k][y] == THREE || checkForShip[k][chONE] == THREE
                || checkForShip[k][y] == FOUR || checkForShip[k][chONE] == FOUR) {
              numberOfShips = numberOfShips - ONE;
              flag = ONE;
              k = x + FOUR;
            }
          }
          if (flag == FLAG) {
            for (int k = x; k < x + THREE; k++) {
              setShipColor(ship, k, y, buttle);
              checkForShip[k][y] = THREE;
            }
          }
        } else {
          if (y == 9) {
            for (int k = x - ONE; k < x + FOUR; k++) {
              int chONE = y - ONE;

              if (checkForShip[k][y] == ONE || checkForShip[k][chONE] == ONE
                  || checkForShip[k][y] == TWO || checkForShip[k][chONE] == TWO
                  || checkForShip[k][y] == THREE || checkForShip[k][y] == FOUR
                  || checkForShip[k][chONE] == THREE || checkForShip[k][chONE] == FOUR) {
                numberOfShips = numberOfShips - ONE;
                flag = ONE;
                k = x + FOUR;
              }
            }
            if (flag == FLAG) {
              for (int k = x; k < x + THREE; k++) {
                setShipColor(ship, k, y, buttle);
                checkForShip[k][y] = THREE;
              }
            }
          } else {
            for (int k = x - ONE; k < x + FOUR; k++) {
              int chONE = y + ONE;
              int chTWO = y - ONE;

              if (checkForShip[k][y] == ONE || checkForShip[k][chONE] == ONE
                  || checkForShip[k][chTWO] == ONE || checkForShip[k][y] == TWO
                  || checkForShip[k][chONE] == TWO || checkForShip[k][chTWO] == TWO
                  || checkForShip[k][y] == THREE || checkForShip[k][chONE] == THREE
                  || checkForShip[k][chTWO] == THREE || checkForShip[k][y] == FOUR
                  || checkForShip[k][chONE] == FOUR || checkForShip[k][chTWO] == FOUR) {
                numberOfShips = numberOfShips - ONE;
                flag = ONE;
                k = x + FOUR;
              }
            }

            if (flag == FLAG) {
              for (int k = x; k < x + THREE; k++) {
                setShipColor(ship, k, y, buttle);
                checkForShip[k][y] = THREE;
              }
            }
          }
        }
      }
    }
    return numberOfShips;
  }

  public int setDestroyer(Button[][] ship, int numberOfShips, int buttle) {
    SecureRandom rand = new SecureRandom();
    int choice = (rand.nextInt(TWO));
    int x = (rand.nextInt(9));
    int y = (ONE + rand.nextInt(6));

    if (choice == FLAG) {
      if (y + TWO > SIZE) {
        numberOfShips = numberOfShips - ONE;
      } else {
        int flag = FLAG;
        if (x == FLAG) {

          for (int k = y - ONE; k < y + THREE; k++) {
            int chONE = x + ONE;

            if (checkForShip[x][k] == ONE || checkForShip[chONE][k] == ONE
                || checkForShip[x][k] == TWO || checkForShip[chONE][k] == TWO
                || checkForShip[x][k] == THREE || checkForShip[chONE][k] == THREE
                || checkForShip[x][k] == FOUR || checkForShip[chONE][k] == FOUR) {
              numberOfShips = numberOfShips - ONE;
              flag = ONE;
              k = y + THREE;
            }
          }
          if (flag == FLAG) {
            for (int k = y; k < y + TWO; k++) {
              setShipColor(ship, x, k, buttle);
              checkForShip[x][k] = TWO;
            }
          }
        } else {
          if (x == 9) {
            for (int k = y - ONE; k < y + THREE; k++) {
              int chONE = x - ONE;

              if (checkForShip[x][k] == ONE || checkForShip[chONE][k] == ONE
                  || checkForShip[x][k] == TWO || checkForShip[chONE][k] == TWO
                  || checkForShip[x][k] == THREE || checkForShip[chONE][k] == FOUR
                  || checkForShip[chONE][k] == THREE || checkForShip[x][k] == FOUR) {
                numberOfShips = numberOfShips - ONE;
                flag = ONE;
                k = y + THREE;
              }
            }
            if (flag == FLAG) {
              for (int k = y; k < y + TWO; k++) {
                setShipColor(ship, x, k, buttle);
                checkForShip[x][k] = TWO;
              }
            }
          } else {
            for (int k = y - ONE; k < y + THREE; k++) {
              int chONE = x + ONE;
              int chTWO = x - ONE;

              if (checkForShip[x][k] == ONE || checkForShip[chONE][k] == ONE
                  || checkForShip[chTWO][k] == ONE || checkForShip[x][k] == TWO
                  || checkForShip[chONE][k] == TWO || checkForShip[chTWO][k] == TWO
                  || checkForShip[x][k] == THREE || checkForShip[chONE][k] == THREE
                  || checkForShip[chTWO][k] == THREE || checkForShip[x][k] == FOUR
                  || checkForShip[chONE][k] == FOUR || checkForShip[chTWO][k] == FOUR) {
                numberOfShips = numberOfShips - ONE;
                flag = ONE;
                k = y + THREE;
              }
            }
            if (flag == FLAG) {
              for (int k = y; k < y + TWO; k++) {
                setShipColor(ship, x, k, buttle);
                checkForShip[x][k] = TWO;
              }
            }
          }
        }
      }
    }

    if (choice == ONE) {
      x = (ONE + rand.nextInt(7));
      y = (rand.nextInt(SIZE));
      if (x + TWO > SIZE) {
        numberOfShips = numberOfShips - ONE;
      } else {
        int flag = FLAG;
        if (y == FLAG) {
          for (int k = x - ONE; k < x + THREE; k++) {
            int chONE = y + ONE;

            if (checkForShip[k][y] == ONE || checkForShip[k][chONE] == ONE
                || checkForShip[k][y] == TWO || checkForShip[k][chONE] == TWO
                || checkForShip[k][y] == THREE || checkForShip[k][chONE] == THREE
                || checkForShip[k][y] == FOUR || checkForShip[k][chONE] == FOUR) {
              numberOfShips = numberOfShips - ONE;
              flag = ONE;
              k = x + THREE;
            }
          }
          if (flag == FLAG) {
            for (int k = x; k < x + TWO; k++) {
              setShipColor(ship, k, y, buttle);
              checkForShip[k][y] = TWO;
            }
          }
        } else {
          if (y == 9) {
            for (int k = x - ONE; k < x + THREE; k++) {
              int chONE = y - ONE;

              if (checkForShip[k][y] == ONE || checkForShip[k][chONE] == ONE
                  || checkForShip[k][y] == TWO || checkForShip[k][chONE] == TWO
                  || checkForShip[k][y] == THREE || checkForShip[k][chONE] == THREE
                  || checkForShip[k][y] == FOUR || checkForShip[k][chONE] == FOUR) {
                numberOfShips = numberOfShips - ONE;
                flag = ONE;
                k = x + THREE;
              }
            }
            if (flag == FLAG) {
              for (int k = x; k < x + TWO; k++) {
                setShipColor(ship, k, y, buttle);
                checkForShip[k][y] = TWO;
              }
            }
          } else {
            for (int k = x - ONE; k < x + THREE; k++) {
              int chONE = y + ONE;
              int chTWO = y - ONE;

              if (checkForShip[k][y] == ONE || checkForShip[k][chONE] == ONE
                  || checkForShip[k][chTWO] == ONE || checkForShip[k][y] == TWO
                  || checkForShip[k][chONE] == TWO || checkForShip[k][chTWO] == TWO
                  || checkForShip[k][y] == THREE || checkForShip[k][chONE] == THREE
                  || checkForShip[k][chTWO] == THREE || checkForShip[k][y] == FOUR
                  || checkForShip[k][chONE] == FOUR || checkForShip[k][chTWO] == FOUR) {
                numberOfShips = numberOfShips - ONE;
                flag = ONE;
                k = x + THREE;
              }
            }
            if (flag == FLAG) {
              for (int k = x; k < x + TWO; k++) {
                setShipColor(ship, k, y, buttle);
                checkForShip[k][y] = TWO;
              }
            }
          }
        }
      }
    }
    return numberOfShips;
  }

  public int setBoat(Button[][] ship, int numberOfShips, int buttle) {
    SecureRandom rand = new SecureRandom();
    int x = (rand.nextInt(SIZE - ONE));
    int y = (rand.nextInt(SIZE - ONE));

    if (x == FLAG && y == FLAG) {
      int chONE = x + ONE;
      int chTHREE = y + ONE;
      if (checkForShip[x][y] == ONE || checkForShip[chONE][y] == ONE
          || checkForShip[x][chTHREE] == ONE || checkForShip[chONE][chTHREE] == ONE
          || checkForShip[x][y] == TWO || checkForShip[chONE][y] == TWO
          || checkForShip[x][chTHREE] == TWO || checkForShip[chONE][chTHREE] == TWO
          || checkForShip[x][y] == THREE || checkForShip[chONE][y] == THREE
          || checkForShip[x][chTHREE] == THREE || checkForShip[chONE][chTHREE] == THREE
          || checkForShip[x][y] == FOUR || checkForShip[chONE][y] == FOUR
          || checkForShip[x][chTHREE] == FOUR || checkForShip[chONE][chTHREE] == FOUR) {
        numberOfShips = numberOfShips - ONE;
      } else {
        setShipColor(ship, x, y, buttle);
        checkForShip[x][y] = ONE;
      }
    } else {
      if (x == FLAG) {
        int chONE = x + ONE;
        int chTHREE = y + ONE;
        int chFOUR = y - ONE;
        if (checkForShip[x][y] == ONE || checkForShip[chONE][y] == ONE
            || checkForShip[x][chTHREE] == ONE || checkForShip[x][chFOUR] == ONE
            || checkForShip[chONE][chTHREE] == ONE || checkForShip[chONE][chFOUR] == ONE
            || checkForShip[x][y] == TWO || checkForShip[chONE][y] == TWO
            || checkForShip[x][chTHREE] == TWO || checkForShip[x][chFOUR] == TWO
            || checkForShip[chONE][chTHREE] == TWO || checkForShip[chONE][chFOUR] == TWO
            || checkForShip[x][y] == THREE || checkForShip[chONE][y] == THREE
            || checkForShip[x][chTHREE] == THREE || checkForShip[x][chFOUR] == THREE
            || checkForShip[chONE][chTHREE] == THREE || checkForShip[chONE][chFOUR] == FOUR
            || checkForShip[chONE][chFOUR] == THREE || checkForShip[x][y] == FOUR
            || checkForShip[chONE][y] == FOUR || checkForShip[x][chTHREE] == FOUR
            || checkForShip[x][chFOUR] == FOUR || checkForShip[chONE][chTHREE] == FOUR) {
          numberOfShips = numberOfShips - ONE;
        } else {
          setShipColor(ship, x, y, buttle);
          checkForShip[x][y] = ONE;
        }
      } else {
        if (x == 9) {
          int chTWO = x - ONE;
          int chTHREE = y + ONE;
          int chFOUR = y - ONE;
          if (checkForShip[x][y] == ONE || checkForShip[chTWO][y] == ONE
              || checkForShip[x][chTHREE] == ONE || checkForShip[x][chFOUR] == ONE
              || checkForShip[chTWO][chFOUR] == ONE || checkForShip[chTWO][chTHREE] == ONE
              || checkForShip[x][y] == TWO || checkForShip[chTWO][y] == TWO
              || checkForShip[x][chTHREE] == TWO || checkForShip[x][chFOUR] == TWO
              || checkForShip[chTWO][chFOUR] == TWO || checkForShip[chTWO][chTHREE] == TWO
              || checkForShip[x][y] == THREE || checkForShip[chTWO][y] == THREE
              || checkForShip[x][chTHREE] == THREE || checkForShip[x][chFOUR] == THREE
              || checkForShip[chTWO][chFOUR] == THREE || checkForShip[chTWO][chTHREE] == THREE
              || checkForShip[x][y] == FOUR || checkForShip[chTWO][y] == FOUR
              || checkForShip[x][chTHREE] == FOUR || checkForShip[x][chFOUR] == FOUR
              || checkForShip[chTWO][chFOUR] == FOUR || checkForShip[chTWO][chTHREE] == FOUR) {
            numberOfShips = numberOfShips - ONE;
          } else {
            setShipColor(ship, x, y, buttle);
            checkForShip[x][y] = ONE;
          }
        } else {
          if (y == FLAG) {
            int chONE = x + ONE;
            int chTWO = x - ONE;
            int chTHREE = y + ONE;

            if (checkForShip[x][y] == ONE || checkForShip[chONE][y] == ONE
                || checkForShip[chTWO][y] == ONE || checkForShip[x][chTHREE] == ONE
                || checkForShip[chONE][chTHREE] == ONE || checkForShip[chTWO][chTHREE] == ONE
                || checkForShip[x][y] == TWO || checkForShip[chONE][y] == TWO
                || checkForShip[chTWO][y] == TWO || checkForShip[x][chTHREE] == TWO
                || checkForShip[chONE][chTHREE] == TWO || checkForShip[chTWO][chTHREE] == TWO
                || checkForShip[x][y] == THREE || checkForShip[chONE][y] == THREE
                || checkForShip[chTWO][y] == THREE || checkForShip[x][chTHREE] == THREE
                || checkForShip[chONE][chTHREE] == THREE || checkForShip[chTWO][chTHREE] == THREE
                || checkForShip[x][y] == FOUR || checkForShip[chONE][y] == FOUR
                || checkForShip[chTWO][y] == FOUR || checkForShip[x][chTHREE] == FOUR
                || checkForShip[chONE][chTHREE] == FOUR || checkForShip[chTWO][chTHREE] == FOUR) {
              numberOfShips = numberOfShips - ONE;
            } else {
              setShipColor(ship, x, y, buttle);
              checkForShip[x][y] = ONE;
            }

          } else {
            if (y == 9) {
              int chONE = x + ONE;
              int chTWO = x - ONE;
              int chFOUR = y - ONE;
              if (checkForShip[x][y] == ONE || checkForShip[chONE][y] == ONE
                  || checkForShip[chTWO][y] == ONE || checkForShip[x][chFOUR] == ONE
                  || checkForShip[chTWO][chFOUR] == ONE || checkForShip[chONE][chFOUR] == ONE
                  || checkForShip[x][y] == TWO || checkForShip[chONE][y] == TWO
                  || checkForShip[chTWO][y] == TWO || checkForShip[x][chFOUR] == TWO
                  || checkForShip[chTWO][chFOUR] == TWO || checkForShip[chONE][chFOUR] == TWO
                  || checkForShip[x][y] == THREE || checkForShip[chONE][y] == THREE
                  || checkForShip[chTWO][y] == THREE || checkForShip[x][chFOUR] == THREE
                  || checkForShip[chTWO][chFOUR] == THREE || checkForShip[chONE][chFOUR] == THREE
                  || checkForShip[x][y] == FOUR || checkForShip[chONE][y] == FOUR
                  || checkForShip[chTWO][y] == FOUR || checkForShip[x][chFOUR] == FOUR
                  || checkForShip[chTWO][chFOUR] == FOUR || checkForShip[chONE][chFOUR] == FOUR) {
                numberOfShips = numberOfShips - ONE;
              } else {
                setShipColor(ship, x, y, buttle);
                checkForShip[x][y] = ONE;
              }
            } else {
              if (x == FLAG) {
                int chONE = x + ONE;
                int chTHREE = y + ONE;
                int chFOUR = y - ONE;
                if (checkForShip[x][y] == ONE || checkForShip[chONE][y] == ONE
                    || checkForShip[x][chTHREE] == ONE || checkForShip[x][chFOUR] == ONE
                    || checkForShip[chONE][chTHREE] == ONE || checkForShip[chONE][chFOUR] == ONE
                    || checkForShip[x][y] == TWO || checkForShip[chONE][y] == TWO
                    || checkForShip[x][chTHREE] == TWO || checkForShip[x][chFOUR] == TWO
                    || checkForShip[chONE][chTHREE] == TWO || checkForShip[chONE][chFOUR] == TWO
                    || checkForShip[x][y] == THREE || checkForShip[chONE][y] == THREE
                    || checkForShip[x][chTHREE] == THREE || checkForShip[x][chFOUR] == THREE
                    || checkForShip[chONE][chTHREE] == THREE || checkForShip[chONE][chFOUR] == THREE
                    || checkForShip[x][y] == FOUR || checkForShip[chONE][y] == FOUR
                    || checkForShip[x][chTHREE] == FOUR || checkForShip[x][chFOUR] == FOUR
                    || checkForShip[chONE][chTHREE] == FOUR
                    || checkForShip[chONE][chFOUR] == FOUR) {
                  numberOfShips = numberOfShips - ONE;
                } else {
                  setShipColor(ship, x, y, buttle);
                  checkForShip[x][y] = ONE;
                }
              }
              int chONE = x + ONE;
              int chTWO = x - ONE;
              int chTHREE = y + ONE;
              int chFOUR = y - ONE;
              if (checkForShip[x][y] == ONE || checkForShip[chONE][y] == ONE
                  || checkForShip[chTWO][y] == ONE || checkForShip[x][chTHREE] == ONE
                  || checkForShip[x][chFOUR] == ONE || checkForShip[chTWO][chFOUR] == ONE
                  || checkForShip[chONE][chTHREE] == ONE || checkForShip[chONE][chFOUR] == ONE
                  || checkForShip[chTWO][chTHREE] == ONE || checkForShip[x][y] == TWO
                  || checkForShip[chONE][y] == TWO || checkForShip[chTWO][y] == TWO
                  || checkForShip[x][chTHREE] == TWO || checkForShip[x][chFOUR] == TWO
                  || checkForShip[chTWO][chFOUR] == TWO || checkForShip[chONE][chTHREE] == TWO
                  || checkForShip[chONE][chFOUR] == TWO || checkForShip[chTWO][chTHREE] == TWO
                  || checkForShip[x][y] == THREE || checkForShip[chONE][y] == THREE
                  || checkForShip[chTWO][y] == THREE || checkForShip[x][chTHREE] == THREE
                  || checkForShip[x][chFOUR] == THREE || checkForShip[chTWO][chFOUR] == THREE
                  || checkForShip[chONE][chTHREE] == THREE || checkForShip[chONE][chFOUR] == THREE
                  || checkForShip[chTWO][chTHREE] == THREE || checkForShip[x][y] == FOUR
                  || checkForShip[chONE][y] == FOUR || checkForShip[chTWO][y] == FOUR
                  || checkForShip[x][chTHREE] == FOUR || checkForShip[x][chFOUR] == FOUR
                  || checkForShip[chTWO][chFOUR] == FOUR || checkForShip[chONE][chTHREE] == FOUR
                  || checkForShip[chONE][chFOUR] == FOUR || checkForShip[chTWO][chTHREE] == FOUR) {
                numberOfShips = numberOfShips - ONE;
              } else {
                setShipColor(ship, x, y, buttle);
                checkForShip[x][y] = ONE;
              }
            }
          }
        }
      }
    }
    return numberOfShips;
  }

  private void rememberItem() {
    for (int i = FLAG; i < SIZE; i++) {
      for (int j = FLAG; j < SIZE; j++) {
        if (nextPlayer == ONE) {
          checkForShipFirst[i][j] = checkForShip[i][j];
        }
        if (nextPlayer == TWO) {
          checkForShipSecond[i][j] = checkForShip[i][j];
        }
      }
    }
  }

  public void setShipColor(Button[][] ship, int x, int y, int buttle) {
    if (buttle == FLAG || buttle == TWO || buttle == FIFE) {
      ship[x][y].setStyle("-fx-base: lightblue");
    }
    if (buttle == ONE || buttle == SIX) {
      ship[x][y].setStyle("-fx-base: lightgreen");
    }
  }
}
