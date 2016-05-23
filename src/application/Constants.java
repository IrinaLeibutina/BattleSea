package application;

public class Constants {

  public final static int WEIGHT = 900;
  public final static int HEIGHT = 600;
  public final static int FIRST_TYPE = 0;
  public final static int SECOND_TYPE = 2;

  public final static int REPLAYS = 5000;
  public final static int SIZE = 10;
  public static final  int FLAG = 0;
  public static final int BATTLESHIP = 1;
  public static final int CRUISER = 2;
  public static final int DESTROYER = 3;
  public static final int BOAT = 4;
  public static final int TWO = 2;
  public static final int ONE = 1;
  public static final int FOUR = 4;
  public static final int THREE = 3;
  public static final int SIX = 6;
  public static final int FIFE = 5;
  public static final int FORTY_POSITION = 40;
  public static final int THIRTY_POSITION = 30;
  public static final int TWENTY_POSITION = 20;
  
  public static final double TRANSPARENCY = 0.5;
  public static double REMOVAL_FIRST_FIELD = 1.8;
  public static double REMOVAL_SECOND_FIELD = 1.5;

  public static int battleship1 = 1;
  public static int cruiser1 = 2;
  public static int destroyer1 = 3;
  public static int boat1 = 4;
  public static int battleship2 = 1;
  public static int cruiser2 = 2;
  public static int destroyer2 = 3;
  public static int boat2 = 4;

  public static int[][] newCheck = new int[SIZE][SIZE];
  // for save in file
  public static int[][] saveFirstField = new int[SIZE][SIZE];
  public static int[][] saveSecondField = new int[SIZE][SIZE];
  public static int[][] saveFirstBattle = new int[SIZE][SIZE];
  public static int[][] saveSecondBattle = new int[SIZE][SIZE];
  public static int positionFirst = 0;
  public static int positionSecond = 1;

  public static int[][] checkForShip = new int[SIZE][SIZE];
  public static int[][] checkForShipFirst = new int[SIZE][SIZE];
  public static int[][] checkForShipSecond = new int[SIZE][SIZE];
  
  public static int loadGame[][] = new int[FORTY_POSITION][SIZE];
  public static int nameOfGame[] = new int[REPLAYS];
  public static int winner[] = new int[REPLAYS];
  public static int coordinates[] = new int[REPLAYS];
  public static int fieldFilling[] = new int[REPLAYS];
  public static int keysForCoordinates[] = new int[REPLAYS];
  

  public static int findMaxNumber[][] = new int[FORTY_POSITION][SIZE];
  public static int maxNumber[] = new int[REPLAYS];
}
