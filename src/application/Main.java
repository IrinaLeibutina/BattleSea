package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import static application.Constants.*;

public class Main extends Application {

  public void start(Stage primaryStage) {
    Pane root = new Pane();

    // Create image for the background
    Image background = new Image(getClass().getResourceAsStream("Ship.jpg"));
    ImageView ground = new ImageView(background);
    ground.setFitHeight(610);
    ground.setFitWidth(910);
    root.getChildren().add(ground);

    // Create button for menu
    MenuItem newGame = new MenuItem("НОВАЯ ИГРА");
    MenuItem rule = new MenuItem("ПРАВИЛА ИГРЫ");
    MenuItem exitGame = new MenuItem("ВЫХОД");
    SubMenu mainMenu = new SubMenu(newGame, rule, exitGame);

    MenuItem players = new MenuItem("2 ИГРОКА");
    MenuItem computer = new MenuItem("ИНТЕЛЛЕКТ");
    MenuItem twoComps = new MenuItem("2 КОМПЬЮТЕРА");
    MenuItem back = new MenuItem("НАЗАД");
    SubMenu newGameMenu = new SubMenu(players, twoComps, back); // computer

    MenuItem levelEasy = new MenuItem("ЛЕГКИЙ");
    MenuItem levelHard = new MenuItem("ТРУДНЫЙ");
    MenuItem backMenu = new MenuItem("НАЗАД");
    SubMenu levelMenu = new SubMenu(levelEasy, levelHard, backMenu);

    MenuBox menuBox = new MenuBox(mainMenu);
    newGame.setOnMouseClicked(event -> menuBox.setSubMenu(newGameMenu));
    computer.setOnMouseClicked(event -> menuBox.setSubMenu(levelMenu));
    exitGame.setOnMouseClicked(event -> System.exit(0));

    back.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));
    backMenu.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));

    Scene scene = new Scene(root, WEIGHT, HEIGHT);
    FadeTransition fadeTrasition = new FadeTransition(Duration.seconds(3), menuBox);
    fadeTrasition.setFromValue(0);
    fadeTrasition.setToValue(1);
    fadeTrasition.play();
    menuBox.setVisible(true);

    // Create image for the background of window for rule
    Image backgroundForRule = new Image(getClass().getResourceAsStream("1.jpg"));
    ImageView groundForRule = new ImageView(backgroundForRule);
    groundForRule.setLayoutX(750);
    groundForRule.setFitHeight(140);
    groundForRule.setFitWidth(140);
    root.getChildren().add(groundForRule);

    Image animation = new Image(getClass().getResourceAsStream("5.gif"));
    primaryStage.setTitle("Морской бой");
    primaryStage.getIcons().add(animation);
    primaryStage.setScene(scene);

    // Rule of game
    rule.setOnMouseClicked(event -> {
      createRule(primaryStage, scene);
    });
    // Player game
    players.setOnMouseClicked(event -> {
      Ships ship = new Ships();
      ship.arrangeShips(primaryStage, NULL);
    });
    // Computer game
    levelEasy.setOnMouseClicked(event -> {
      Ships ship = new Ships();
      ship.arrangeShips(primaryStage, TWO);
    });
    levelHard.setOnMouseClicked(event -> {
      Ships ship = new Ships();
      ship.arrangeShips(primaryStage, TWO);
    });
    twoComps.setOnMouseClicked(event -> {
      TwoComp compGame = new TwoComp();
      compGame.createBattle(primaryStage);
    });
    root.getChildren().addAll(menuBox);
    primaryStage.setResizable(false);
    primaryStage.setFullScreen(false);
    primaryStage.show();
  }

  public void createRule(Stage primaryStage, Scene scene) {

    Pane root = new Pane();
    Scene sceneForRule = new Scene(root, 794, 563);
    primaryStage.setScene(sceneForRule);

    // Create image for background
    Image background = new Image(getClass().getResourceAsStream("backgroundForRules.png"));
    ImageView ground = new ImageView(background);
    ground.setFitHeight(HEIGHT);
    ground.setFitWidth(WEIGHT);
    root.getChildren().add(ground);

    Rectangle comeBack = new Rectangle(130, 25, Color.DARKSALMON);
    comeBack.setOpacity(TRANSPARENCY);
    comeBack.setLayoutX(290);
    comeBack.setLayoutY(500);

    Text info = new Text(" На игровой площадке\n размером 10 на 10 клеток\n"
        + " пользователь расставляет один\n корабль из четы"
        + "рех клеток,\nдва корабля из трех клеток,\nтри ко"
        + "рабля из двух клеток и\nчетыре корабля размером"
        + "\nв одну клетку.\nПосле расстановки начинается\nбой."
        + "Он представляет собой\nпоочередные выстрелы игроков."
        + "\nПри попадании в корабль\nпротивника участник боя\nполучает " + "возможность");
    Text infoSecond = new Text(" проведения внеочередного\nвыстрела. Игра заканчивается\nпри "
        + "уничтожении одним\nиз участников всех кораблей\nпротивника. ");
    info.setFill(Color.BLACK);
    info.setOpacity(0.7);
    info.setLayoutY(34);
    info.setLayoutX(53);
    info.setFont(Font.font("Arial", FontPosture.ITALIC, 16));

    infoSecond.setFill(Color.BLACK);
    infoSecond.setOpacity(0.7);
    infoSecond.setLayoutY(310);
    infoSecond.setLayoutX(400);
    infoSecond.setFont(Font.font("Arial", FontPosture.ITALIC, 16));

    Text text = new Text("НАЗАД");
    text.setFill(Color.FIREBRICK);
    text.setLayoutX(325);
    text.setLayoutY(520);
    text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
    root.getChildren().addAll(info, text, infoSecond, comeBack);

    FillTransition trasition = new FillTransition(Duration.seconds(TRANSPARENCY), comeBack);
    comeBack.setOnMouseEntered(event -> {
      trasition.setFromValue(Color.YELLOW);
      trasition.setToValue(Color.CORNSILK);
      trasition.setCycleCount(Animation.INDEFINITE);
      trasition.setAutoReverse(true);
      trasition.play();
    });

    comeBack.setOnMouseExited(event -> {
      trasition.stop();
      comeBack.setFill(Color.DARKSALMON);
    });

    comeBack.setOnMouseClicked(event -> {
      primaryStage.setScene(scene);
    });
  }

  private static class MenuItem extends StackPane {
    public MenuItem(String name) {
      Rectangle choice = new Rectangle(200, 25, Color.DARKSALMON);
      choice.setOpacity(TRANSPARENCY);

      Text text = new Text(name);
      text.setFill(Color.FIREBRICK);
      text.setOpacity(0.7);

      text.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
      setAlignment(Pos.CENTER);
      getChildren().addAll(choice, text);
      FillTransition trasition = new FillTransition(Duration.seconds(TRANSPARENCY), choice);
      setOnMouseEntered(event -> {
        trasition.setFromValue(Color.YELLOW);
        trasition.setToValue(Color.CORNSILK);
        trasition.setCycleCount(Animation.INDEFINITE);
        trasition.setAutoReverse(true);
        trasition.play();
      });
      setOnMouseExited(event -> {
        trasition.stop();
        choice.setFill(Color.DARKSALMON);
      });
    }
  }

  private static class MenuBox extends Pane {
    static SubMenu subMenu;

    public MenuBox(SubMenu subMenu) {
      MenuBox.subMenu = subMenu;

      setVisible(false);
      Rectangle choice = new Rectangle(WEIGHT, HEIGHT);
      choice.setOpacity(0.0);
      getChildren().addAll(choice, subMenu);
    }

    public void setSubMenu(SubMenu subMenu) {
      getChildren().remove(MenuBox.subMenu);
      MenuBox.subMenu = subMenu;
      getChildren().add(MenuBox.subMenu);
    }
  }

  private static class SubMenu extends VBox {
    public SubMenu(MenuItem... items) {
      setSpacing(20);
      setTranslateY(150);
      setTranslateX(50);
      for (MenuItem item : items) {
        getChildren().addAll(item);
      }
    }
  }
}
