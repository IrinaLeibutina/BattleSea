package application;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import static application.Constants.*;

public class Field {
  Button[][] createFieldForShip() {
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
      }
    }
    return ship;
  }

  Button[][] createFieldForBattle(int typeOfField) {
    Button[][] firstField = new Button[SIZE][SIZE];
    Button[][] secondField = new Button[SIZE][SIZE];

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        double k = i + 1.5;
        double l = j + 1.5;

        firstField[i][j] = new Button();
        secondField[i][j] = new Button();

        firstField[i][j].setStyle("-fx-base: lightgreen");
        secondField[i][j].setStyle("-fx-base: lightgreen");
        firstField[i][j].setMinSize(30, 30);
        secondField[i][j].setMinSize(30, 30);
        firstField[i][j].setLayoutX(30 * k);
        firstField[i][j].setLayoutY(30 * l);
        secondField[i][j].setLayoutX(30 * k + 500);
        secondField[i][j].setLayoutY(30 * l);
      }
    }
    if (typeOfField == 1)
      return firstField;
    else
      return secondField;
  }

  public void fieldNameForShip(Pane shipsAndField) {
    // Fields name
    Button[] symbols = new Button[SIZE];
    Button[] numbers = new Button[11];
    // Part for symbols
    for (int i = 0; i < SIZE; i++) {
      symbols[i] = new Button();
      double shift = 1.8 + i;
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

  public void fieldNameForBattle(Pane battle) {
    Button[] symbols = new Button[SIZE];
    Button[] numbers = new Button[SIZE + 1];
    Button[] secondSymbols = new Button[SIZE];
    Button[] secondNumbers = new Button[SIZE + 1];
    // Part for symbols
    for (int i = 0; i < SIZE; i++) {
      symbols[i] = new Button();
      secondSymbols[i] = new Button();
      double shift = 1.5 + i;
      char[] name = new char[SIZE];
      name[0] = 'A'; // Russian symbols
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
      symbols[i].setMinSize(30, 30);
      symbols[i].setLayoutX(15);
      symbols[i].setStyle("-fx-base: lightblue");
      symbols[i].setLayoutY(30 * shift);
      secondSymbols[i].setText(valueOfchar);
      secondSymbols[i].setMinSize(30, 30);
      secondSymbols[i].setLayoutX(515);
      secondSymbols[i].setStyle("-fx-base: lightblue");
      secondSymbols[i].setLayoutY(30 * shift);
      battle.getChildren().addAll(symbols[i], secondSymbols[i]);
    }

    // Part for numbers
    for (int i = 0; i < 11; i++) {
      numbers[i] = new Button();
      secondNumbers[i] = new Button();
      double shift = i + 0.5;
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
      name[10] = ' ';

      // Set Number
      if (i != 10) {
        String valueOfchar = String.valueOf(name[i]);
        numbers[i].setText(valueOfchar);
        secondNumbers[i].setText(valueOfchar);
      } else {
        numbers[10].setText("10");
        secondNumbers[10].setText("10");
      }
      numbers[i].setMinSize(30, 30);
      numbers[i].setStyle("-fx-base: lightblue");
      numbers[i].setLayoutY(15);
      numbers[i].setLayoutX(30 * shift);
      secondNumbers[i].setMinSize(30, 30);
      secondNumbers[i].setStyle("-fx-base: lightblue");
      secondNumbers[i].setLayoutY(15);
      secondNumbers[i].setLayoutX(30 * shift + 500);
      battle.getChildren().addAll(numbers[i], secondNumbers[i]);
    }
  }
}
