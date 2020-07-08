import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;

public class Controller extends Application {
    private TextField[][] inputField;

    @Override
    public void start(Stage primaryStage) {
        int sceneWidth = 500, sceneHeight = 500;


        Button runButton = new Button("Solve");

        inputField = new TextField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                inputField[i][j] = new TextField();
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                runButton.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
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

                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                inputField[i][j].setText(String.valueOf(ss.getEntry(i, j)));
                            }
                        }
                    }
                });
            }
        }



        GridPane gp = new GridPane();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gp.add(inputField[i][j], i, j);
            }
        }

        BorderPane bp = new BorderPane();
        bp.setCenter(gp);
        bp.setBottom(runButton);

        Scene scene = new Scene(bp, sceneWidth, sceneHeight);
        primaryStage.setTitle("Sudoku Solver");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
