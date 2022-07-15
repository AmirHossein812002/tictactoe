import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TicTacToe extends Application  {
    Image TX = new Image("TX.png");
    Image TO = new Image("TO.png");
    ImageView showTurn = new ImageView(TX);
    private String turn = "X";
    private Button[][] button = new Button[3][3];
    private XOE[][] xoe = new XOE[3][3];
    private int sceneNum = 1;
    private int actionNum = 0;
    private int gameMood = 0;
    private int XOSelectTurn = 0;



    public void XOSelect(Stage primaryStage){
        Pane pane = new Pane();
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton X = new RadioButton("X");
        X.setStyle("-fx-background-color:" +
                "                         linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%)," +
                "                         linear-gradient(#020b02, #3a3a3a)," +
                "                         linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%)," +
                "                         linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);" +
                "                         -fx-font-weight: bold;" +
                "                          -fx-font-size: 24;"+
                "                         -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");

        X.setTextFill(Color.RED);
        RadioButton O = new RadioButton("O");
        O.setStyle("-fx-background-color:" +
                "                         linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%)," +
                "                         linear-gradient(#020b02, #3a3a3a)," +
                "                         linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%)," +
                "                         linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);" +
                "                         -fx-font-weight: bold;" +
                "                         -fx-font-size: 24;"+
                "                         -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");

        O.setTextFill(Color.GREEN);
        X.setToggleGroup(toggleGroup);
        O.setToggleGroup(toggleGroup);
        Button enter = new Button("Enter");
        enter.setStyle("-fx-background-color: " +
                "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%)," +
                "        linear-gradient(#020b02, #3a3a3a)," +
                "        linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%)," +
                "        linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%)," +
                "        linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);" +
                "    -fx-background-insets: 0,1,4,5,6;" +
                "    -fx-background-radius: 9,8,5,4,3;" +
                "    -fx-padding: 15 30 15 30;" +
                "    -fx-font-weight: bold;" +
                "    -fx-text-fill: white;" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
        enter.setMinSize(110,58);
        enter.setMaxSize(110,58);
        enter.setFont(new Font(18));
        enter.setDisable(true);
        enter.setLayoutX(170);
        enter.setLayoutY(490);
        O.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                enter.setDisable(false);
                enter.setFont(new Font(18));
                XOSelectTurn = 2;
                showTurn = new ImageView(TO);
                turn = "O";
            }
        });
        X.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                enter.setDisable(false);
                enter.setFont(new Font(18));
                XOSelectTurn = 2;
            }
        });
        Group root = new Group();
        root.getChildren().add(pane);
        pane.setPrefSize(450, 550);
        pane.getChildren().add(new ImageView(new Image("XOSelect.png")));
        pane.getChildren().add(O);
        pane.getChildren().add(X);
        pane.getChildren().add(enter);
        O.setLayoutX(290);
        O.setLayoutY(450);
        X.setLayoutX(100);
        X.setLayoutY(450);
        Scene intro = new Scene(root);
        primaryStage.setScene(intro);
        primaryStage.setResizable(false);
        primaryStage.show();
        enter.setOnMouseMoved(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                enter.setFont(new Font(15));
            }
        });
        enter.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                enter.setFont(new Font(18));
                gameStart(primaryStage);
            }
        });
    }
    public int comp(XOE[][] xoe) {
        if (xoe[1][1] == XOE.empty) {
            return 11;
        }
        if (XOSelectTurn == 2) {
            int Rm = 0, R = 0;
            for (int i = 0; i < 3; i++) {
                int row = 0, column = 0;
                for (int j = 0; j < 3; j++) {
                    if (xoe[i][j] == XOE.X)
                        row++;
                    if (xoe[j][i] == XOE.X)
                        column++;
                    if (i == j && xoe[i][j] == XOE.X)
                        Rm++;
                    if (j == 2 - i && xoe[i][j] == XOE.X)
                        R++;
                    if (row == 2) {
                        for (int k = 0; k < 3; k++) {
                            if (xoe[i][k] == XOE.empty)
                                return (i * 10) + k;
                        }
                    }
                    if (column == 2) {
                        for (int k = 0; k < 3; k++) {
                            if (xoe[k][i] == XOE.empty)
                                return (k * 10) + i;
                        }
                    }
                    if (Rm == 2) {
                        if (xoe[0][0] == XOE.empty)
                            return 0;
                        else if(xoe[1][1] == XOE.empty)
                            return 11;
                        else if (xoe[2][2] == XOE.empty)
                            return 22;
                    }
                    if (R == 2) {
                        if (xoe[0][2] == XOE.empty)
                            return 2;
                        else if(xoe[1][1] == XOE.empty)
                            return 11;
                        else if (xoe[2][0] == XOE.empty)
                            return 20;
                    }
                }
            }
        } else {
            int Rm = 0, R = 0;
            for (int i = 0; i < 3; i++) {
                int row = 0, column = 0;
                for (int j = 0; j < 3; j++) {
                    if (xoe[i][j] == XOE.O)
                        row++;
                    if (xoe[j][i] == XOE.O)
                        column++;
                    if (i == j && xoe[i][j] == XOE.O)
                        Rm++;
                    if (j == 2 - i && xoe[i][j] == XOE.O)
                        R++;
                    if (row == 2) {
                        for (int k = 0; k < 3; k++) {
                            if (xoe[i][k] == XOE.empty)
                                return (i * 10) + k;
                        }
                    }
                    if (column == 2) {
                        for (int k = 0; k < 3; k++) {
                            if (xoe[k][i] == XOE.empty)
                                return (k * 10) + i;
                        }
                    }
                    if (Rm == 2) {
                        if (xoe[0][0] == XOE.empty)
                            return 0;
                        else if(xoe[1][1] == XOE.empty)
                            return 11;
                        else if (xoe[2][2] == XOE.empty)
                            return 22;
                    }
                    if (R == 2) {
                        if (xoe[0][2] == XOE.empty)
                            return 2;
                        else if(xoe[1][1] == XOE.empty)
                            return 11;
                        else if (xoe[2][0] == XOE.empty)
                            return 20;
                    }
                }
            }
        }
        if (XOSelectTurn == 1) {
            int Rm = 0, R = 0;
            for (int i = 0; i < 3; i++) {
                int row = 0, column = 0;
                for (int j = 0; j < 3; j++) {
                    if (xoe[i][j] == XOE.X)
                        row++;
                    if (xoe[j][i] == XOE.X)
                        column++;
                    if (i == j && xoe[i][j] == XOE.X)
                        Rm++;
                    if (j == 2 - i && xoe[i][j] == XOE.X)
                        R++;
                    if (row == 2) {
                        for (int k = 0; k < 3; k++) {
                            if (xoe[i][k] == XOE.empty)
                                return (i * 10) + k;
                        }
                    }
                    if (column == 2) {
                        for (int k = 0; k < 3; k++) {
                            if (xoe[k][i] == XOE.empty)
                                return (k * 10) + i;
                        }
                    }
                    if (Rm == 2) {
                        if (xoe[0][0] == XOE.empty)
                            return 0;
                        else if(xoe[1][1] == XOE.empty)
                            return 11;
                        else if (xoe[2][2] == XOE.empty)
                            return 22;
                    }
                    if (R == 2) {
                        if (xoe[0][2] == XOE.empty)
                            return 2;
                        else if(xoe[1][1] == XOE.empty)
                            return 11;
                        else if (xoe[2][0] == XOE.empty)
                            return 20;
                    }
                }
            }
        } else {
            int Rm = 0, R = 0;
            for (int i = 0; i < 3; i++) {
                int row = 0, column = 0;
                for (int j = 0; j < 3; j++) {
                    if (xoe[i][j] == XOE.O)
                        row++;
                    if (xoe[j][i] == XOE.O)
                        column++;
                    if (i == j && xoe[i][j] == XOE.O)
                        Rm++;
                    if (j == 2 - i && xoe[i][j] == XOE.O)
                        R++;
                    if (row == 2) {
                        for (int k = 0; k < 3; k++) {
                            if (xoe[i][k] == XOE.empty)
                                return (i * 10) + k;
                        }
                    }
                    if (column == 2) {
                        for (int k = 0; k < 3; k++) {
                            if (xoe[k][i] == XOE.empty)
                                return (k * 10) + i;
                        }
                    }
                    if (Rm == 2) {
                        if (xoe[0][0] == XOE.empty)
                            return 0;
                        else if(xoe[1][1] == XOE.empty)
                            return 11;
                        else if (xoe[2][2] == XOE.empty)
                            return 22;
                    }
                    if (R == 2) {
                        if (xoe[0][2] == XOE.empty)
                            return 2;
                        else if(xoe[1][1] == XOE.empty)
                            return 11;
                        else if (xoe[2][0] == XOE.empty)
                            return 20;
                    }
                }
            }
        }
        if (XOSelectTurn == 1) {
            int Rm = 0, R = 0;
            for (int i = 0; i < 3; i++) {
                int row = 0, column = 0;
                for (int j = 0; j < 3; j++) {
                    if (xoe[i][j] == XOE.X)
                        row++;
                    if (xoe[j][i] == XOE.X)
                        column++;
                    if (i == j && xoe[i][j] == XOE.X)
                        Rm++;
                    if (j == 2 - i && xoe[i][j] == XOE.X)
                        R++;
                    if (row == 1) {
                        for (int k = 0; k < 3; k++) {
                            if (xoe[i][k] == XOE.empty)
                                return (i * 10) + k;
                        }
                    }
                    if (column == 1) {
                        for (int k = 0; k < 3; k++) {
                            if (xoe[k][i] == XOE.empty)
                                return (k * 10) + i;
                        }
                    }
                    if (Rm == 1) {
                        if (xoe[0][0] == XOE.empty)
                            return 0;
                        else if(xoe[1][1] == XOE.empty)
                            return 11;
                        else if (xoe[2][2] == XOE.empty)
                            return 22;
                    }
                    if (R == 1) {
                        if (xoe[0][2] == XOE.empty)
                            return 2;
                        else if(xoe[1][1] == XOE.empty)
                            return 11;
                        else if (xoe[2][0] == XOE.empty)
                            return 20;
                    }
                }
            }
        } else {
            int Rm = 0, R = 0;
            for (int i = 0; i < 3; i++) {
                int row = 0, column = 0;
                for (int j = 0; j < 3; j++) {
                    if (xoe[i][j] == XOE.O)
                        row++;
                    if (xoe[j][i] == XOE.O)
                        column++;
                    if (i == j && xoe[i][j] == XOE.O)
                        Rm++;
                    if (j == 2 - i && xoe[i][j] == XOE.O)
                        R++;
                    if (row == 1) {
                        for (int k = 0; k < 3; k++) {
                            if (xoe[i][k] == XOE.empty)
                                return (i * 10) + k;
                        }
                    }
                    if (column == 1) {
                        for (int k = 0; k < 3; k++) {
                            if (xoe[k][i] == XOE.empty)
                                return (k * 10) + i;
                        }
                    }
                    if (Rm == 1) {
                        if (xoe[0][0] == XOE.empty)
                            return 0;
                        else if(xoe[1][1] == XOE.empty)
                            return 11;
                        else if (xoe[2][2] == XOE.empty)
                            return 22;
                    }
                    if (R == 1) {
                        if (xoe[0][2] == XOE.empty)
                            return 2;
                        else if(xoe[1][1] == XOE.empty)
                            return 11;
                        else if (xoe[2][0] == XOE.empty)
                            return 20;
                    }
                }
            }
        }
        int i = (int)(Math.random() * 3);
        int j = (int)(Math.random() * 3);
        while(xoe[i][j] != XOE.empty){
            i = (int)(Math.random() * 3);
            j = (int)(Math.random() * 3);
        }
        return (i * 10) + j;
    }
    public void won(Stage primaryStage, int win) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                button[i][j].setDisable(true);
            }
        }
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));
        pauseTransition.playFromStart();
        pauseTransition.setOnFinished(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Group root = new Group();
                if(win == 1)
                    root.getChildren().add(new ImageView(new Image("XWon.png")));
                else if(win == 2)
                    root.getChildren().add(new ImageView(new Image("OWon.png")));
                else if (win == 3)
                    root.getChildren().add(new ImageView(new Image("OXWon.png")));
                Button yes = new Button("YES");
                Button no = new Button("NO");
                no.setStyle("-fx-background-color: " +
                        "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%)," +
                        "        linear-gradient(#020b02, #3a3a3a)," +
                        "        linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%)," +
                        "        linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%)," +
                        "        linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);" +
                        "    -fx-background-insets: 0,1,4,5,6;" +
                        "    -fx-background-radius: 9,8,5,4,3;" +
                        "    -fx-padding: 15 30 15 30;" +
                        "    -fx-font-weight: bold;" +
                        "     -fx-text-fill: white;"+
                        "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
                yes.setStyle("-fx-background-color: " +
                        "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%)," +
                        "        linear-gradient(#020b02, #3a3a3a)," +
                        "        linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%)," +
                        "        linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%)," +
                        "        linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);" +
                        "    -fx-background-insets: 0,1,4,5,6;" +
                        "    -fx-background-radius: 9,8,5,4,3;" +
                        "    -fx-padding: 15 30 15 30;" +
                        "    -fx-font-weight: bold;" +
                        "     -fx-text-fill: white;"+
                        "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
                yes.setFont(new Font(18));
                no.setFont(new Font(18));yes.setMaxSize(110, 58);
                yes.setMinSize(110, 58);
                no.setMinSize(110, 58);
                no.setMaxSize(110, 58);
                yes.setOnMouseMoved(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        yes.setFont(new Font(15));
                    }
                });
                no.setOnMouseMoved(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        no.setFont(new Font(15));
                    }
                });
                yes.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        actionNum = 0;
                        gameMood = 0;
                        sceneNum = 1;
                        turn = "X";
                        try {
                            start(primaryStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                no.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        primaryStage.close();
                    }
                });
                root.getChildren().add(yes);
                root.getChildren().add(no);
                yes.setLayoutY(450);
                yes.setLayoutX(75);
                no.setLayoutY(450);
                no.setLayoutX(300);
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
    }
    public void gameStart(Stage primaryStage) {
        Group root = new Group();
        int first = 1;
        primaryStage.setTitle("Tic-Tac-Toe");
        GridPane gridPane = new GridPane();;
        gridPane.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(0), new Insets(0))));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                xoe[i][j] = XOE.empty;
                button[i][j] = new Button(xoe[i][j].toString());
                button[i][j].setMinSize(150, 150);
                button[i][j].setStyle("-fx-background-color: " +
                        "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%)," +
                        "        linear-gradient(#020b02, #3a3a3a)," +
                        "        linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%)," +
                        "        linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%)," +
                        "        linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);" +
                        "    -fx-background-insets: 0,1,4,5,6;" +
                        "    -fx-background-radius: 9,8,5,4,3;" +
                        "    -fx-padding: 15 30 15 30;" +
                        "    -fx-font-weight: bold;" +
                        "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
                button[i][j].setTextFill(Color.WHITE);
                button[i][j].setFont(new Font("HelloStockholm.ttf", 18));
                int finalI = i;
                int finalJ = j;
                if(gameMood == 2) {
                    button[i][j].setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            ((Button) event.getSource()).setDisable(true);
                            if (turn.equals("X")) {
                                actionNum++;
                                turn = "O";
                                root.getChildren().remove(showTurn);
                                showTurn = new ImageView(TO);
                                showTurn.setX(175);
                                showTurn.setY(450);
                                root.getChildren().add(showTurn);
                                xoe[finalI][finalJ] = XOE.X;
                                ((Button) event.getSource()).setFont(new Font("HelloStockholm.ttf", 36));
                                ((Button) event.getSource()).setText("X");
                                ((Button) event.getSource()).setTextFill(Color.rgb(255, 0, 0));
                                int cntXr = 0, cntXc = 0, cntRm = 0, cntR = 0;
                                for (int k = 0; k < 3; k++) {
                                    cntXr = 0;
                                    cntXc = 0;
                                    for (int l = 0; l < 3; l++) {
                                        if (xoe[k][l] == XOE.X)
                                            cntXr++;
                                        if (xoe[l][k] == XOE.X)
                                            cntXc++;
                                        if (k == l && xoe[k][l] == XOE.X)
                                            cntRm++;
                                        if (l == 2 - k && xoe[k][l] == XOE.X)
                                            cntR++;
                                    }
                                    if (cntXr == 3 || cntXc == 3 || cntRm == 3 || cntR == 3) {
                                        sceneNum = 2;
                                        System.out.println("X won");
                                        won(primaryStage, 1);
                                    }

                                }
                            } else if (turn.equals("O")) {
                                actionNum++;
                                turn = "X";
                                root.getChildren().remove(showTurn);
                                showTurn = new ImageView(TX);
                                showTurn.setX(175);
                                showTurn.setY(450);
                                root.getChildren().add(showTurn);
                                xoe[finalI][finalJ] = XOE.O;
                                ((Button) event.getSource()).setFont(new Font("HelloStockholm.ttf", 36));
                                ((Button) event.getSource()).setText("O");
                                ((Button) event.getSource()).setTextFill(Color.rgb(0, 255, 0));
                                int cntOr = 0, cntOc = 0, cntORm = 0, cntOR = 0;
                                for (int k = 0; k < 3; k++) {
                                    cntOr = 0;
                                    cntOc = 0;
                                    for (int l = 0; l < 3; l++) {
                                        if (xoe[k][l] == XOE.O)
                                            cntOr++;
                                        if (xoe[l][k] == XOE.O)
                                            cntOc++;
                                        if (k == l && xoe[k][l] == XOE.O)
                                            cntORm++;
                                        if (l == 2 - k && xoe[k][l] == XOE.O)
                                            cntOR++;
                                        if (cntOr == 3 || cntOc == 3 || cntORm == 3 || cntOR == 3) {
                                            sceneNum = 2;
                                            System.out.println("O Won");
                                            won(primaryStage, 2);
                                        }
                                    }
                                }
                            }
                            if (sceneNum == 1 && actionNum == 9)
                                won(primaryStage, 3);
                        }
                    });
                } else {
                    if(first == 1) {
                        if (turn.equals("O")) {
                            first--;
                            actionNum++;
                            int comp = comp(xoe);
                            xoe[comp / 10][comp % 10] = XOE.X;
                            button[comp / 10][comp % 10].setFont(new Font("HelloStockholm.ttf", 36));
                            button[comp / 10][comp % 10].setText("X");
                            button[comp / 10][comp % 10].setTextFill(Color.rgb(255, 0, 0));
                            button[comp / 10][comp % 10].setDisable(true);
                            int cntXr = 0, cntXc = 0, cntRm = 0, cntR = 0;
                            for (int k = 0; k < 3; k++) {
                                cntXr = 0;
                                cntXc = 0;
                                for (int l = 0; l < 3; l++) {
                                    if (xoe[k][l] == XOE.X)
                                        cntXr++;
                                    if (xoe[l][k] == XOE.X)
                                        cntXc++;
                                    if (k == l && xoe[k][l] == XOE.X)
                                        cntRm++;
                                    if (l == 2 - k && xoe[k][l] == XOE.X)
                                        cntR++;
                                }
                                if (cntXr == 3 || cntXc == 3 || cntRm == 3 || cntR == 3) {
                                    sceneNum = 2;
                                    System.out.println("X won");
                                    won(primaryStage, 1);
                                }
                            }
                        }
                    }
                    button[i][j].setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            ((Button) event.getSource()).setDisable(true);
                            if (turn.equals("X")) {
                                actionNum+=2;
                                root.getChildren().remove(showTurn);
                                showTurn = new ImageView(TX);
                                showTurn.setX(175);
                                showTurn.setY(450);
                                root.getChildren().add(showTurn);
                                xoe[finalI][finalJ] = XOE.X;
                                ((Button) event.getSource()).setFont(new Font("HelloStockholm.ttf", 36));
                                ((Button) event.getSource()).setText("X");
                                ((Button) event.getSource()).setTextFill(Color.rgb(255, 0, 0));
                                int cntXr = 0, cntXc = 0, cntRm = 0, cntR = 0;
                                for (int k = 0; k < 3; k++) {
                                    cntXr = 0;
                                    cntXc = 0;
                                    for (int l = 0; l < 3; l++) {
                                        if (xoe[k][l] == XOE.X)
                                            cntXr++;
                                        if (xoe[l][k] == XOE.X)
                                            cntXc++;
                                        if (k == l && xoe[k][l] == XOE.X)
                                            cntRm++;
                                        if (l == 2 - k && xoe[k][l] == XOE.X)
                                            cntR++;
                                    }
                                    if (cntXr == 3 || cntXc == 3 || cntRm == 3 || cntR == 3) {
                                        sceneNum = 2;
                                        System.out.println("X won");
                                        won(primaryStage, 1);
                                    }

                                }
                                if(actionNum != 10) {
                                    int comp = comp(xoe);
                                    xoe[comp / 10][comp % 10] = XOE.O;
                                    button[comp / 10][comp % 10].setFont(new Font("HelloStockholm.ttf", 36));
                                    button[comp / 10][comp % 10].setText("O");
                                    button[comp / 10][comp % 10].setTextFill(Color.rgb(0, 255, 0));
                                    button[comp / 10][comp % 10].setDisable(true);
                                    int cntOr = 0, cntOc = 0, cntORm = 0, cntOR = 0;
                                    for (int k = 0; k < 3; k++) {
                                        cntOr = 0;
                                        cntOc = 0;
                                        for (int l = 0; l < 3; l++) {
                                            if (xoe[k][l] == XOE.O)
                                                cntOr++;
                                            if (xoe[l][k] == XOE.O)
                                                cntOc++;
                                            if (k == l && xoe[k][l] == XOE.O)
                                                cntORm++;
                                            if (l == 2 - k && xoe[k][l] == XOE.O)
                                                cntOR++;
                                            if (cntOr == 3 || cntOc == 3 || cntORm == 3 || cntOR == 3) {
                                                sceneNum = 2;
                                                System.out.println("O Won");
                                                won(primaryStage, 2);
                                            }
                                        }
                                    }
                                }
                                if (sceneNum == 1 &&  actionNum == 10)
                                    won(primaryStage, 3);
                            } else {
                                if (actionNum != 9) {
                                    root.getChildren().remove(showTurn);
                                    showTurn = new ImageView(TO);
                                    showTurn.setX(175);
                                    showTurn.setY(450);
                                    root.getChildren().add(showTurn);
                                    xoe[finalI][finalJ] = XOE.O;
                                    ((Button) event.getSource()).setFont(new Font("HelloStockholm.ttf", 36));
                                    ((Button) event.getSource()).setText("O");
                                    ((Button) event.getSource()).setTextFill(Color.rgb(0, 255, 0));
                                    int cntOr = 0, cntOc = 0, cntORm = 0, cntOR = 0;
                                    for (int k = 0; k < 3; k++) {
                                        cntOr = 0;
                                        cntOc = 0;
                                        for (int l = 0; l < 3; l++) {
                                            if (xoe[k][l] == XOE.O)
                                                cntOr++;
                                            if (xoe[l][k] == XOE.O)
                                                cntOc++;
                                            if (k == l && xoe[k][l] == XOE.O)
                                                cntORm++;
                                            if (l == 2 - k && xoe[k][l] == XOE.O)
                                                cntOR++;
                                            if (cntOr == 3 || cntOc == 3 || cntORm == 3 || cntOR == 3) {
                                                sceneNum = 2;
                                                System.out.println("O Won");
                                                won(primaryStage, 2);
                                            }
                                        }
                                    }
                                }
                                actionNum += 2;
                                int comp = comp(xoe);
                                xoe[comp / 10][comp % 10] = XOE.X;
                                button[comp / 10][comp % 10].setFont(new Font("HelloStockholm.ttf", 36));
                                button[comp / 10][comp % 10].setText("X");
                                button[comp / 10][comp % 10].setTextFill(Color.rgb(255, 0, 0));
                                button[comp / 10][comp % 10].setDisable(true);
                                int cntXr = 0, cntXc = 0, cntRm = 0, cntR = 0;
                                for (int k = 0; k < 3; k++) {
                                    cntXr = 0;
                                    cntXc = 0;
                                    for (int l = 0; l < 3; l++) {
                                        if (xoe[k][l] == XOE.X)
                                            cntXr++;
                                        if (xoe[l][k] == XOE.X)
                                            cntXc++;
                                        if (k == l && xoe[k][l] == XOE.X)
                                            cntRm++;
                                        if (l == 2 - k && xoe[k][l] == XOE.X)
                                            cntR++;
                                    }
                                    if (cntXr == 3 || cntXc == 3 || cntRm == 3 || cntR == 3) {
                                        sceneNum = 2;
                                        System.out.println("X won");
                                        won(primaryStage, 1);
                                    }
                                }
                                if (sceneNum == 1 && actionNum == 9)
                                    won(primaryStage, 3);
                            }
                        }
                    });
                }
                gridPane.add(button[i][j], i, j, 1, 1);
            }
        }
        root.getChildren().add(gridPane);
        root.getChildren().add(showTurn);
        showTurn.setX(175);
        showTurn.setY(450);
        gridPane.setPrefSize(450, 550);
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.getIcons().add(new Image("logo.png"));
        Pane pane = new Pane();
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton PVP = new RadioButton("PVP");
        PVP.setStyle("-fx-background-color:" +
                "                         linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%)," +
                "                         linear-gradient(#020b02, #3a3a3a)," +
                "                         linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%)," +
                "                         linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);" +
                "                         -fx-font-weight: bold;" +
                "                          -fx-font-size: 24;"+
                "                         -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");

        PVP.setTextFill(Color.WHITE);
        RadioButton PVE = new RadioButton("PVE");
        PVE.setStyle("-fx-background-color:" +
                "                         linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%)," +
                "                         linear-gradient(#020b02, #3a3a3a)," +
                "                         linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%)," +
                "                         linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);" +
                "                         -fx-font-weight: bold;" +
                "                         -fx-font-size: 24;"+
                "                         -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");

        PVE.setTextFill(Color.WHITE);
        PVP.setToggleGroup(toggleGroup);
        PVE.setToggleGroup(toggleGroup);
        Button enter = new Button("Enter");
        enter.setStyle("-fx-background-color: " +
                "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%)," +
                "        linear-gradient(#020b02, #3a3a3a)," +
                "        linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%)," +
                "        linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%)," +
                "        linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);" +
                "    -fx-background-insets: 0,1,4,5,6;" +
                "    -fx-background-radius: 9,8,5,4,3;" +
                "    -fx-padding: 15 30 15 30;" +
                "    -fx-font-weight: bold;" +
                "    -fx-text-fill: white;" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
        enter.setMinSize(110,58);
        enter.setMaxSize(110,58);
        enter.setFont(new Font(18));
        enter.setDisable(true);
        enter.setLayoutX(170);
        enter.setLayoutY(480);
        PVE.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                enter.setDisable(false);
                enter.setFont(new Font(18));
                gameMood = 1;
            }
        });
        PVP.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                enter.setDisable(false);
                enter.setFont(new Font(18));
                gameMood = 2;
            }
        });
        Group root = new Group();
        root.getChildren().add(pane);
        pane.setPrefSize(450, 550);
        pane.getChildren().add(new ImageView(new Image("intro.png")));
        pane.getChildren().add(PVP);
        pane.getChildren().add(PVE);
        pane.getChildren().add(enter);
        PVP.setLayoutX(250);
        PVP.setLayoutY(425);
        PVE.setLayoutX(100);
        PVE.setLayoutY(425);
        Scene intro = new Scene(root);
        primaryStage.setScene(intro);
        primaryStage.setResizable(false);
        primaryStage.show();
        enter.setOnMouseMoved(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                enter.setFont(new Font(15));
            }
        });
        enter.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                enter.setFont(new Font(18));
                if(gameMood == 1)
                    XOSelect(primaryStage);
                else
                    gameStart(primaryStage);
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
enum XOE {
    X, O, empty;
}