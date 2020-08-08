import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert.*;



public class Controller extends Application {
    private TextField[][] inputField;
    private HBox buttons;

    @Override
    public void start(Stage primaryStage) {
        int sceneWidth = 250, sceneHeight = 250;


        Button runButton = new Button("Solve");
        Button clearButton = new Button("Clear");
        Button genButton = new Button("Generate");
        Button checkButton = new Button("Check");

        inputField = new TextField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                inputField[i][j] = new TextField();
                inputField[i][j].setPrefColumnCount(1);

            }
        }

        // solve button
        runButton.setOnAction(e -> {
            int[][] input = new int[9][9];

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (inputField[i][j].getText().isEmpty()) {
                        input[i][j] = 0;
                    } else {
                        input[i][j] = Integer.parseInt(inputField[i][j].getText());
                    }
                }
            }

            SudokuSystem ss = new SudokuSystem(input);

            if (!ss.hasEmptyEntry()) {
                Alert a = new Alert(AlertType.ERROR, "Already finished!", ButtonType.CLOSE);
                a.show();
            } else if (ss.solve()) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        inputField[i][j].setText(String.valueOf(ss.getEntry(i, j)));
                    }
                }
            } else {
                Alert a = new Alert(AlertType.ERROR, "Invalid input!", ButtonType.CLOSE);
                a.show();
            }

        });

        // clear button
        clearButton.setOnAction(e -> {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    inputField[i][j].setText("");
                }
            }
        });

        // gen button
        genButton.setOnAction(event -> {
            SudokuSystem ss = new SudokuSystem();
            ss.generate();

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    inputField[i][j].setText(String.valueOf(ss.getEntry(i, j)));
                }
            }
        });

        checkButton.setOnAction(event -> {
            int[][] input = new int[9][9];

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (inputField[i][j].getText().isEmpty()) {
                        input[i][j] = 0;
                    } else {
                        input[i][j] = Integer.parseInt(inputField[i][j].getText());
                    }
                }
            }

            SudokuSystem ss = new SudokuSystem(input);

            if (ss.hasEmptyEntry()) {
                Alert a = new Alert(AlertType.ERROR, "You haven't finished it.", ButtonType.CLOSE);
                a.show();
            } else if (ss.check()) {
                Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Congrats");
                a.setHeaderText(null);
                a.setContentText("You solved it!");
                a.showAndWait();
            } else {
                Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Oof");
                a.setHeaderText(null);
                a.setContentText("Something is wrong here...");
                a.showAndWait();
            }
        });

        GridPane gp = new GridPane();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gp.add(inputField[i][j], i, j);

            }
        }
        buttons = new HBox();

        buttons.getChildren().addAll(runButton, clearButton, genButton, checkButton);

        BorderPane bp = new BorderPane();

        bp.setCenter(gp);
        bp.setBottom(buttons);

        Scene scene = new Scene(bp, sceneWidth, sceneHeight);
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
