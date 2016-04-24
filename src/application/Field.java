package application;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import static application.Constants.*;

public class Field {
  Button[][] createFieldForShip() {
    Button[][] ship = new Button[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        double newI = i + 1.8;
        double newJ = j + 1.8;
        ship[i][j] = new Button();
        ship[i][j].setStyle("-fx-base: lightgreen");
        ship[i][j].setMinSize(FORTY, FORTY);
        ship[i][j].setLayoutX(FORTY * newI);
        ship[i][j].setLayoutY(FORTY * newJ);
      }
    }
    return ship;
  }

  Button[][] createFieldForBattle(int typeOfField) {
    Button[][] firstField = new Button[SIZE][SIZE];
    Button[][] secondField = new Button[SIZE][SIZE];

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        double newI = i + 1.5;
        double newJ = j + 1.5;

        firstField[i][j] = new Button();
        secondField[i][j] = new Button();

        firstField[i][j].setStyle("-fx-base: lightgreen");
        secondField[i][j].setStyle("-fx-base: lightgreen");
        firstField[i][j].setMinSize(THIRTY, THIRTY);
        secondField[i][j].setMinSize(THIRTY, THIRTY);
        firstField[i][j].setLayoutX(THIRTY * newI);
        firstField[i][j].setLayoutY(THIRTY * newJ);
        secondField[i][j].setLayoutX(THIRTY * newI + 500);
        secondField[i][j].setLayoutY(THIRTY * newJ);
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
      symbols[i].setMinSize(FORTY, FORTY);
      symbols[i].setLayoutX(32);
      symbols[i].setStyle("-fx-base: lightblue");
      symbols[i].setLayoutY(FORTY * shift);
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
      numbers[i].setMinSize(FORTY, FORTY);
      numbers[i].setStyle("-fx-base: lightblue");
      numbers[i].setLayoutY(32);
      numbers[i].setLayoutX(FORTY * shift);
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
      symbols[i].setMinSize(THIRTY, THIRTY);
      symbols[i].setLayoutX(15);
      symbols[i].setStyle("-fx-base: lightblue");
      symbols[i].setLayoutY(THIRTY * shift);
      secondSymbols[i].setText(valueOfchar);
      secondSymbols[i].setMinSize(THIRTY, THIRTY);
      secondSymbols[i].setLayoutX(515);
      secondSymbols[i].setStyle("-fx-base: lightblue");
      secondSymbols[i].setLayoutY(THIRTY * shift);
      battle.getChildren().addAll(symbols[i], secondSymbols[i]);
    }

    // Part for numbers
    for (int i = 0; i < 11; i++) {
      numbers[i] = new Button();
      secondNumbers[i] = new Button();
      double shift = i + TRANSPARENCY;
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
      if (i != SIZE) {
        String valueOfchar = String.valueOf(name[i]);
        numbers[i].setText(valueOfchar);
        secondNumbers[i].setText(valueOfchar);
      } else {
        numbers[SIZE].setText("10");
        secondNumbers[SIZE].setText("10");
      }
      numbers[i].setMinSize(THIRTY, THIRTY);
      numbers[i].setStyle("-fx-base: lightblue");
      numbers[i].setLayoutY(15);
      numbers[i].setLayoutX(THIRTY * shift);
      secondNumbers[i].setMinSize(THIRTY, THIRTY);
      secondNumbers[i].setStyle("-fx-base: lightblue");
      secondNumbers[i].setLayoutY(15);
      secondNumbers[i].setLayoutX(THIRTY * shift + 500);
      battle.getChildren().addAll(numbers[i], secondNumbers[i]);
    }
  }
}
