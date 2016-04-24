package application;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
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
import javafx.util.Duration;
import static application.Constants.*;

public class Interface {

  Text createTextForButton() {
    Text textForButton = new Text("ÂÏÅÐÅÄ");
    textForButton.setFill(Color.FIREBRICK);
    textForButton.setOpacity(0.7);
    textForButton.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    textForButton.setLayoutX(785);
    textForButton.setLayoutY(568);
    return textForButton;
  }

  ImageView createBackground() {
    Image background = new Image(getClass().getResourceAsStream("Fon.jpg"));
    ImageView ground = new ImageView(background);
    ground.setFitHeight(HEIGHT);
    ground.setFitWidth(WEIGHT);
    return ground;
  }

  Rectangle createRectangle() {
    Rectangle choice = new Rectangle(130, 25, Color.DARKSALMON);
    choice.setOpacity(TRANSPARENCY);
    choice.setLayoutX(750);
    choice.setLayoutY(550);
    FillTransition fillTrasition = new FillTransition(Duration.seconds(TRANSPARENCY), choice);
    choice.setOnMouseEntered(event -> {
      fillTrasition.setFromValue(Color.YELLOW);
      fillTrasition.setToValue(Color.CORNSILK);
      fillTrasition.setCycleCount(Animation.INDEFINITE);
      fillTrasition.setAutoReverse(true);
      fillTrasition.play();
    });
    choice.setOnMouseExited(event -> {
      fillTrasition.stop();
      choice.setFill(Color.DARKSALMON);
    });
    return choice;
  }

  Text createTextForComeBack() {
    Text textForComeback = new Text("ÍÀÇÀÄ");
    textForComeback.setFill(Color.FIREBRICK);
    textForComeback.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    textForComeback.setLayoutX(785);
    textForComeback.setLayoutY(568);
    return textForComeback;
  }

  Text createInfoForUser() {
    Text textForUser = new Text("Êîìïüþòåð \nðàññòàâèë\n êîðàáëè");
    textForUser.setFill(Color.FIREBRICK);
    textForUser.setOpacity(0.7);
    textForUser.setFont(Font.font("Arial", FontPosture.ITALIC, 32));
    textForUser.setLayoutX(170);
    textForUser.setLayoutY(210);
    return textForUser;
  }

  public static class MenuItem extends StackPane {
    public MenuItem(String name) {
      Rectangle choice = new Rectangle(220, 28, Color.LIGHTBLUE);
      choice.setOpacity(TRANSPARENCY);

      Text text = new Text(name);
      text.setFill(Color.FIREBRICK);
      text.setOpacity(0.7);

      text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
      setAlignment(Pos.CENTER);
      getChildren().addAll(choice, text);

      FillTransition fillTrasition = new FillTransition(Duration.seconds(TRANSPARENCY), choice);
      choice.setOnMouseEntered(event -> {
        fillTrasition.setFromValue(Color.YELLOW);
        fillTrasition.setToValue(Color.CORNSILK);
        fillTrasition.setCycleCount(Animation.INDEFINITE);
        fillTrasition.setAutoReverse(true);
        fillTrasition.play();
      });
      choice.setOnMouseExited(event -> {
        fillTrasition.stop();
        choice.setFill(Color.DARKSALMON);
      });
    }
  }

  public static class MenuBox extends Pane {
    public MenuBox(Menu subMenu) {
      setVisible(false);
      Rectangle rectangleForButton = new Rectangle(WEIGHT, HEIGHT);
      rectangleForButton.setOpacity(0.0);
      getChildren().addAll(rectangleForButton, subMenu);
    }
  }

  public static class Menu extends VBox {
    public Menu(MenuItem... items) {
      setSpacing(30);
      setTranslateY(40);
      setTranslateX(550);
      for (MenuItem item : items) {
        getChildren().addAll(item);
      }
    }
  }
}
