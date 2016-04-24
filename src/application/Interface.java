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
    Text text = new Text("ÂÏÅÐÅÄ");
    text.setFill(Color.FIREBRICK);
    text.setOpacity(0.7);
    text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    text.setLayoutX(785);
    text.setLayoutY(568);
    return text;
  }

  ImageView createBackground() {
    Image imgage = new Image(getClass().getResourceAsStream("Fon.jpg"));
    ImageView img5 = new ImageView(imgage);
    img5.setFitHeight(HEIGHT);
    img5.setFitWidth(WEIGHT);
    return img5;
  }

  Rectangle createRectangle() {
    Rectangle choice = new Rectangle(130, 25, Color.DARKSALMON);
    choice.setOpacity(0.5);

    choice.setLayoutX(750);
    choice.setLayoutY(550);
    FillTransition fillTrasition = new FillTransition(Duration.seconds(0.5), choice);
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
    Text text = new Text("ÍÀÇÀÄ");
    text.setFill(Color.FIREBRICK);
    text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    text.setLayoutX(785);
    text.setLayoutY(568);
    return text;
  }

  Text createInfoForUser() {
    Text text = new Text("Êîìïüþòåð \nðàññòàâèë\n êîðàáëè");
    text.setFill(Color.FIREBRICK);
    text.setOpacity(0.7);
    text.setFont(Font.font("Arial", FontPosture.ITALIC, 32));
    text.setLayoutX(170);
    text.setLayoutY(210);
    return text;
  }

  public static class MenuItem extends StackPane {
    public MenuItem(String name) {
      Rectangle choice = new Rectangle(220, 28, Color.LIGHTBLUE);
      choice.setOpacity(0.5);

      Text text = new Text(name);
      text.setFill(Color.FIREBRICK);
      text.setOpacity(0.7);

      text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
      setAlignment(Pos.CENTER);
      getChildren().addAll(choice, text);

      FillTransition fillTrasition = new FillTransition(Duration.seconds(0.5), choice);
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
      Rectangle bg = new Rectangle(WEIGHT, HEIGHT);
      bg.setOpacity(0.0);
      getChildren().addAll(bg, subMenu);
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
