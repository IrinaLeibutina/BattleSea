package application;

import application.Battle;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.security.SecureRandom;

public class Ships {
  public final static int WIDTH = 900;
  public final static int HEIGHT = 600;
  public final static int SIZE = 10;
  public final static int ONE = 1;
  public final static int TWO = 2;
  public final static int THREE = 3;
  public final static int FOUR = 4;
  private static final int BATTLESHIP = 1;
  private static final int CRUISER = 2;
  private static final int DESTROYER = 3;
  private static final int BOAT = 4;
  public static int[][] checkForShip = new int[SIZE][SIZE];
  public static int[][] checkForShipFirst = new int[SIZE][SIZE];
  public static int[][] checkForShipSecond = new int[SIZE][SIZE];
  public int nextPlayer = 1;
  public int choice = 0;

  public Ships() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        checkForShip[i][j] = 0;
      }
    }
  }

  public void createField(Stage primaryStage, int buttle) {

    Pane shipsAndField = new Pane();
    Scene scene3 = new Scene(shipsAndField, WIDTH, HEIGHT);
    // Create image for background
    Image background = new Image(getClass().getResourceAsStream("Fon.jpg"));
    ImageView ground = new ImageView(background);
    ground.setFitHeight(HEIGHT);
    ground.setFitWidth(WIDTH);
    shipsAndField.getChildren().add(ground);
    // Create field
    Button[][] ship = new Button[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        double k = i + 1.8;
        double l = j + 1.8;
        ship[i][j] = new Button();
        ship[i][j].setStyle("-fx-base: lightgreen");
        ship[i][j].setMinSize(40, 40);
        ship[i][j].setLayoutX(40 * k);
        ship[i][j].setLayoutY(40 * l);
        shipsAndField.getChildren().addAll(ship[i][j]);
      }
    }
    fieldName(shipsAndField);

    /// Create Menu Button
    MenuItem battleship = new MenuItem("ËÈÍÊÎÐ(õ1)");
    MenuItem cruiser = new MenuItem("ÊÐÅÉÑÅÐ(õ2)");
    MenuItem destroyer = new MenuItem("ÝÑÌÈÍÅÖ(õ3)");
    MenuItem boat = new MenuItem("ÊÀÒÅÐ(õ4)");
    MenuItem random = new MenuItem("CËÓ×ÀÉÍÀß ÐÀÑÑÒÀÍÎÂÊÀ");
    Menu mainMenu = new Menu(battleship, cruiser, destroyer, boat, random);
    MenuBox menuBox = new MenuBox(mainMenu);
    menuBox.setVisible(true);

    shipsAndField.getChildren().addAll(menuBox);

    Rectangle choice = new Rectangle(130, 25, Color.DARKSALMON);
    choice.setOpacity(0.5);
    choice.setLayoutX(750);
    choice.setLayoutY(550);
    FillTransition transition = new FillTransition(Duration.seconds(0.5), choice);
    choice.setOnMouseEntered(event2 -> {
      transition.setFromValue(Color.YELLOW);
      transition.setToValue(Color.CORNSILK);
      transition.setCycleCount(Animation.INDEFINITE);
      transition.setAutoReverse(true);
      transition.play();
    });
    choice.setOnMouseExited(event3 -> {
      st.stop();
      choice.setFill(Color.DARKSALMON);
    });

    choice.setOnMouseClicked(event1 -> {
      if (buttle == 0) {
        if (nextPlayer == 2) {
          rememberItem();
          Battle battle = new Battle();
          battle.CreateBattleField(primaryStage);
        }
        if (nextPlayer == 1) {
          rememberItem();
          createField(primaryStage, 0);
          nextPlayer = 2;
        }
      }
      if (buttle == 2) {
        if (nextPlayer == 2) {
          rememberItem();
          BattleWithComp battle = new BattleWithComp();
          battle.createBattle(primaryStage);
        }
        if (nextPlayer == 1) {

          rememberItem();
          nextPlayer = 2;
          fifthButton(random, ship, 1);
          Text text = new Text("Êîìïüþòåð \nðàññòàâèë\n êîðàáëè");
          text.setFill(Color.FIREBRICK);
          text.setOpacity(0.7);
          text.setFont(Font.font("Arial", FontPosture.ITALIC, 32));
          text.setLayoutX(170);
          text.setLayoutY(210);
          shipsAndField.getChildren().addAll(text);
        }
      }
    });

    Text text = new Text("ÂÏÅÐÅÄ");
    text.setFill(Color.FIREBRICK);
    text.setOpacity(0.7);
    text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    text.setLayoutX(785);
    text.setLayoutY(568);
    shipsAndField.getChildren().addAll(choice, text);

    firstButton(battleship, ship, buttle);
    secondButton(cruiser, ship, buttle);
    thirdButton(destroyer, ship, buttle);
    forthButton(boat, ship, buttle);
    random.setOnMouseClicked(event6 -> {
      fifthButton(random, ship, buttle);
    });

    primaryStage.setScene(scene3);
    primaryStage.show();
  }

  // Occupancy ships
  public void firstButton(MenuItem battleship, Button[][] ship, int buttle) {
    battleship.setOnMouseClicked(eventONETWO -> {
      // Delete ship
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          if (checkForShip[i][j] == FOUR) {
            checkForShip[i][j] = 0;
            ship[i][j].setStyle("-fx-base: lightgreen");
          }
        }
      }
      for (int i = 0; i < BATTLESHIP; i++) {
        i = setBattleship(ship, i, buttle);
      }
    });
  }

  public void secondButton(MenuItem cruiser, Button[][] ship, int buttle) {
    cruiser.setOnMouseClicked(event -> {
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          if (checkForShip[i][j] == THREE) {
            checkForShip[i][j] = 0;
            ship[i][j].setStyle("-fx-base: lightgreen");
          }
        }
      }
      for (int i = 0; i < CRUISER; i++) {
        i = setCruiser(ship, i, buttle);
      }
    });
  }

  public void thirdButton(MenuItem destroyer, Button[][] ship, int buttle) {
    destroyer.setOnMouseClicked(event -> {
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          if (checkForShip[i][j] == TWO) {
            checkForShip[i][j] = 0;
            ship[i][j].setStyle("-fx-base: lightgreen");
          }
        }
      }
      for (int i = 0; i < DESTROYER; i++) {
        i = setDestroyer(ship, i, buttle);
      }
    });
  }

  public void forthButton(MenuItem boat, Button[][] ship, int buttle) {
    boat.setOnMouseClicked(eventTWOONE -> {
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          if (checkForShip[i][j] == ONE) {
            checkForShip[i][j] = 0;
            ship[i][j].setStyle("-fx-base: lightgreen");
          }
        }
      }
      for (int i = ONE; i < BOAT + ONE; i++) {
        i = setBoat(ship, i, buttle);
      }
    });
  }

  public void fifthButton(MenuItem random, Button[][] ship, int buttle) {

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        checkForShip[i][j] = 0;
        ship[i][j].setStyle("-fx-base: lightgreen");
      }
    }
    for (int i = 0; i < BATTLESHIP; i++) {
      i = setBattleship(ship, i, buttle);
    }
    for (int i = 0; i < CRUISER; i++) {
      i = setCruiser(ship, i, buttle);
    }
    for (int i = 0; i < DESTROYER; i++) {
      i = setDestroyer(ship, i, buttle);
    }
    for (int i = ONE; i < BOAT + ONE; i++) {
      i = setBoat(ship, i, buttle);
    }
  }

  public void fieldName(Pane shipsAndField) {
    // Fields name
    Button[] symbols = new Button[SIZE];
    Button[] numbers = new Button[11];
    // Part for symbols
    for (int i = 0; i < SIZE; i++) {
      symbols[i] = new Button();
      double shift = 1.8 + i;
      char[] name = new char[SIZE];
      name[0] = 'A'; // Create russian symbols
      name[1] = 'Á';
      name[2] = 'Â';
      name[3] = 'Ã';
      name[4] = 'Ä';
      name[5] = 'Å';
      name[6] = 'Æ';
      name[7] = 'Ç';
      name[8] = 'È';
      name[9] = 'Ê';

      String valueOfchar = String.valueOf(name[i]);
      symbols[i].setText(valueOfchar);
      symbols[i].setMinSize(40, 40);
      symbols[i].setLayoutX(32);
      symbols[i].setStyle("-fx-base: lightblue");
      symbols[i].setLayoutY(40 * shift);
      shipsAndField.getChildren().addAll(symbols[i]);
    }

    // Part for numbers
    for (int i = 0; i < 11; i++) {
      numbers[i] = new Button();
      double shift = i + 0.8;
      char[] name = new char[11];
      name[0] = ' ';
      name[1] = '1';
      name[2] = '2';
      name[3] = '3';
      name[4] = '4';
      name[5] = '5';
      name[6] = '6';
      name[7] = '7';
      name[8] = '8';
      name[9] = '9';
      name[SIZE] = ' ';

      // Set Number
      if (i != 10) {
        String valueOfchar = String.valueOf(name[i]);
        numbers[i].setText(valueOfchar);
      } else {
        numbers[10].setText("10");
      }
      numbers[i].setMinSize(40, 40);
      numbers[i].setStyle("-fx-base: lightblue");
      numbers[i].setLayoutY(32);
      numbers[i].setLayoutX(40 * shift);
      shipsAndField.getChildren().addAll(numbers[i]);
    }
  }

  // In next part you will meet large blocks of "if(){...}"
  // This is necessary, because I should check all position,
  // where boats may be arranged.Break it to function probably does
  // not make sense, because each test uses a different location.

  public int setBattleship(Button[][] ship, int i, int buttle) {
    SecureRandom rand = new SecureRandom();
    int number = FOUR;
    int choice = (rand.nextInt(TWO));
    int x = (rand.nextInt(SIZE - ONE)); // Coordinates
    int y = (rand.nextInt(SIZE - ONE)); // Coordinates
    // Vertical ship
    if (choice == 0) {

      if (y + number > SIZE) {
        i = i - ONE;
      } else
        for (int k = y; k < y + number; k++) {
          setShipColor(ship, x, k, buttle);
          checkForShip[x][k] = number;
        }
      // Horizontally ship
    } else if (choice == ONE) {
      if (x + number > SIZE) {
        i = i - ONE;
      } else
        for (int k = x; k < x + number; k++) {
          setShipColor(ship, k, y, buttle);
          checkForShip[k][y] = number;
        }
    }
    return i;
  }

  public int setCruiser(Button[][] ship, int i, int buttle) {
    SecureRandom rand = new SecureRandom();
    int number = THREE;
    int choice = (rand.nextInt(TWO));
    int x = (rand.nextInt(SIZE));
    int y = (ONE + rand.nextInt(6));
    // vertical
    if (choice == 0) {
      if (y + number > SIZE) {
        i = i - ONE;
      } else {
        int flag = 0;
        if (x == 0) {
          for (int k = y - ONE; k < y + FOUR; k++) {
            int chONE = x + ONE;

            if (checkForShip[x][k] == ONE || checkForShip[chONE][k] == ONE
                || checkForShip[x][k] == TWO || checkForShip[x][k] == FOUR
                || checkForShip[chONE][k] == TWO || checkForShip[chONE][k] == FOUR
                || checkForShip[chONE][k] == THREE || checkForShip[x][k] == THREE) {
              i = i - ONE;
              flag = ONE;
              k = y + FOUR;
            }
          }
          if (flag == 0) {
            for (int k = y; k < y + number; k++) {
              setShipColor(ship, x, k, buttle);
              checkForShip[x][k] = number;
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
                i = i - ONE;
                flag = ONE;
                k = y + FOUR;
              }
            }
            if (flag == 0) {
              for (int k = y; k < y + number; k++) {
                setShipColor(ship, x, k, buttle);
                checkForShip[x][k] = number;
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
                i = i - ONE;
                flag = ONE;
                k = y + FOUR;
              }
            }
            if (flag == 0) {
              for (int k = y; k < y + number; k++) {
                setShipColor(ship, x, k, buttle);
                checkForShip[x][k] = number;
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
      if (x + number > SIZE) {
        i = i - ONE;
      } else {
        int flag = 0;
        if (y == 0) {
          for (int k = x - ONE; k < x + FOUR; k++) {
            int chONE = y + ONE;

            if (checkForShip[k][y] == ONE || checkForShip[k][chONE] == ONE
                || checkForShip[k][y] == TWO || checkForShip[k][chONE] == TWO
                || checkForShip[k][y] == THREE || checkForShip[k][chONE] == THREE
                || checkForShip[k][y] == FOUR || checkForShip[k][chONE] == FOUR) {
              i = i - ONE;
              flag = ONE;
              k = x + FOUR;
            }
          }
          if (flag == 0) {
            for (int k = x; k < x + number; k++) {
              setShipColor(ship, k, y, buttle);
              checkForShip[k][y] = number;
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
                i = i - ONE;
                flag = ONE;
                k = x + FOUR;
              }
            }
            if (flag == 0) {
              for (int k = x; k < x + number; k++) {
                setShipColor(ship, k, y, buttle);
                checkForShip[k][y] = number;
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
                i = i - ONE;
                flag = ONE;
                k = x + FOUR;
              }
            }

            if (flag == 0) {
              for (int k = x; k < x + number; k++) {
                setShipColor(ship, k, y, buttle);
                checkForShip[k][y] = number;
              }
            }
          }
        }
      }
    }
    return i;
  }

  ///
  public int setDestroyer(Button[][] ship, int i, int buttle) {
    SecureRandom rand = new SecureRandom();
    int number = TWO;
    int choice = (rand.nextInt(number));
    int x = (rand.nextInt(SIZE)); // x
    int y = (ONE + rand.nextInt(7)); // y

    if (choice == 0) {
      if (y + number > SIZE) {
        i = i - ONE;
      } else {
        int flag = 0;
        if (x == 0) {

          for (int k = y - ONE; k < y + THREE; k++) {
            int chONE = x + ONE;

            if (checkForShip[x][k] == ONE || checkForShip[chONE][k] == ONE || checkForShip[x][k] == TWO
                || checkForShip[chONE][k] == TWO || checkForShip[x][k] == THREE || checkForShip[chONE][k] == THREE
                || checkForShip[x][k] == FOUR || checkForShip[chONE][k] == FOUR) {
              i = i - ONE;
              flag = ONE;
              k = y + THREE;
            }
          }
          if (flag == 0) {
            for (int k = y; k < y + number; k++) {
              setShipColor(ship, x, k, buttle);
              checkForShip[x][k] = number;
            }
          }
        } else {
          if (x == 9) {
            for (int k = y - ONE; k < y + THREE; k++) {
              int chONE = x - ONE;

              if (checkForShip[x][k] == ONE || checkForShip[chONE][k] == ONE
                  || checkForShip[x][k] == number || checkForShip[chONE][k] == TWO
                  || checkForShip[x][k] == THREE || checkForShip[chONE][k] == FOUR
                  || checkForShip[chONE][k] == THREE || checkForShip[x][k] == FOUR) {
                i = i - ONE;
                flag = ONE;
                k = y + THREE;
              }
            }
            if (flag == 0) {
              for (int k = y; k < y + number; k++) {
                setShipColor(ship, x, k, buttle);
                checkForShip[x][k] = number;
              }
            }
          } else {
            for (int k = y - ONE; k < y + THREE; k++) {
              int chONE = x + ONE;
              int chnumber = x - ONE;

              if (checkForShip[x][k] == ONE || checkForShip[chONE][k] == ONE
                  || checkForShip[chTWO][k] == ONE || checkForShip[x][k] == TWO
                  || checkForShip[chONE][k] == TWO || checkForShip[chTWO][k] == TWO
                  || checkForShip[x][k] == THREE || checkForShip[chONE][k] == THREE
                  || checkForShip[chTWO][k] == THREE || checkForShip[x][k] == FOUR
                  || checkForShip[chONE][k] == FOUR || checkForShip[chTWO][k] == FOUR) {
                i = i - ONE;
                flag = ONE;
                k = y + THREE;
              }
            }
            if (flag == 0) {
              for (int k = y; k < y + number; k++) {
                setShipColor(ship, x, k, buttle);
                checkForShip[x][k] = number;
              }
            }
          }
        }
      }
    }

    if (choice == ONE) {
      x = (ONE + rand.nextInt(7));
      y = (rand.nextInt(SIZE));
      if (x + number > SIZE) {
        i = i - ONE;
      } else {
        int flag = 0;
        if (y == 0) {
          for (int k = x - ONE; k < x + THREE; k++) {
            int chONE = y + ONE;

            if (checkForShip[k][y] == ONE || checkForShip[k][chONE] == ONE || checkForShip[k][y] == TWO
                || checkForShip[k][chONE] == TWO || checkForShip[k][y] == THREE
                || checkForShip[k][chONE] == THREE || checkForShip[k][y] == FOUR
                || checkForShip[k][chONE] == FOUR) {
              i = i - ONE;
              flag = ONE;
              k = x + THREE;
            }
          }
          if (flag == 0) {
            for (int k = x; k < x + number; k++) {
              setShipColor(ship, k, y, buttle);
              checkForShip[k][y] = number;
            }
          }
        } else {
          if (y == 9) {
            for (int k = x - ONE; k < x + THREE; k++) {
              int chONE = y - ONE;

              if (checkForShip[k][y] == ONE || checkForShip[k][chONE] == ONE
                  || checkForShip[k][y] == TWO || checkForShip[k][chONE] == TWO
                  || checkForShip[k][y] == THREE || checkForShip[k][chONE] == THREE || checkForShip[k][y] == FOUR
                  || checkForShip[k][chONE] == FOUR) {
                i = i - ONE;
                flag = ONE;
                k = x + THREE;
              }
            }
            if (flag == 0) {
              for (int k = x; k < x + number; k++) {
                setShipColor(ship, k, y, buttle);
                checkForShip[k][y] = number;
              }
            }
          } else {
            for (int k = x - ONE; k < x + THREE; k++) {
              int chONE = y + ONE;
              int chnumber = y - ONE;

              if (checkForShip[k][y] == ONE || checkForShip[k][chONE] == ONE
                  || checkForShip[k][chTWO] == ONE || checkForShip[k][y] == TWO
                  || checkForShip[k][chONE] == TWO || checkForShip[k][chTWO] == 
                  || checkForShip[k][y] == THREE || checkForShip[k][chONE] == THREE
                  || checkForShip[k][chTWO] == THREE || checkForShip[k][y] == FOUR
                  || checkForShip[k][chONE] == FOUR || checkForShip[k][chTWO] == FOUR) {
                i = i - ONE;
                flag = ONE;
                k = x + THREE;
              }
            }
            if (flag == 0) {
              for (int k = x; k < x + number; k++) {
                setShipColor(ship, k, y, buttle);
                checkForShip[k][y] = number;
              }
            }
          }
        }
      }
    }
    return i;
  }

  public int setBoat(Button[][] ship, int i, int buttle) {
    SecureRandom rand = new SecureRandom();
    int x = (rand.nextInt(SIZE - ONE));
    int y = (rand.nextInt(SIZE - ONE));

    if (x == 0 && y == 0) {
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
        i = i - ONE;
      } else {
        setShipColor(ship, x, y, buttle);
        checkForShip[x][y] = ONE;
      }
    } else {
      if (x == 0) {
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
          i = i - ONE;
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
            i = i - ONE;
          } else {
            setShipColor(ship, x, y, buttle);
            checkForShip[x][y] = ONE;
          }
        } else {
          if (y == 0) {
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
              i = i - ONE;
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
                i = i - ONE;
              } else {
                setShipColor(ship, x, y, buttle);
                checkForShip[x][y] = ONE;
              }
            } else {
              if (x == 0) {
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
                  i = i - ONE;
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
                i = i - ONE;
              } else {
                setShipColor(ship, x, y, buttle);
                checkForShip[x][y] = ONE;
              }
            }
          }
        }
      }
    }
    return i;
  }

  private static class MenuItem extends StackPane {
    public MenuItem(String name) {
      Rectangle choice = new Rectangle(220, 28, Color.LIGHTBLUE);
      choice.setOpacity(0.5);

      Text text = new Text(name);
      text.setFill(Color.FIREBRICK);
      text.setOpacity(0.7);

      text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
      setAlignment(Pos.CENTER);
      getChildren().addAll(choice, text);

      FillTransition st = new FillTransition(Duration.seconds(0.5), choice);
      setOnMouseEntered(event -> {
        st.setFromValue(Color.LIGHTBLUE);
        st.setToValue(Color.CORNSILK);
        st.setCycleCount(Animation.INDEFINITE);
        st.setAutoReverse(true);
        st.play();
      });
      setOnMouseExited(event -> {
        st.stop();
        choice.setFill(Color.LIGHTBLUE);
      });
    }
  }

  private static class MenuBox extends Pane {
    public MenuBox(Menu subMenu) {
      setVisible(false);
      Rectangle bg = new Rectangle(WIDTH, HEIGHT);
      bg.setOpacity(0.0);
      getChildren().addAll(bg, subMenu);
    }
  }

  private static class Menu extends VBox {
    public Menu(MenuItem... items) {
      setSpacing(30);
      setTranslateY(40);
      setTranslateX(550);
      for (MenuItem item : items) {
        getChildren().addAll(item);
      }
    }
  }

  public void rememberItem() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (nextPlayer == 1) {
          checkForShipFirst[i][j] = checkForShip[i][j];
        }
        if (nextPlayer == 2) {
          checkForShipSecond[i][j] = checkForShip[i][j];
        }
      }
    }
  }

  public void setShipColor(Button[][] ship, int x, int y, int buttle) {
    if (buttle == 0 || buttle == 2) {
      ship[x][y].setStyle("-fx-base: lightblue");
    }
    if (buttle == 1) {
      ship[x][y].setStyle("-fx-base: lightgreen");
    }
  }
}
