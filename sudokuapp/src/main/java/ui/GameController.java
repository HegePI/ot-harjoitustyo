package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.SudokuService;
import logic.UserSudoku;

public class GameController implements Initializable {

    private final SudokuService server;
    private UserSudoku us;
    private int selectedRow;
    private int selectedCol;

    @FXML
    private Button back, save, check;
    @FXML
    private Canvas canvas;
    @FXML
    private Label info;

    public GameController() throws ClassNotFoundException, SQLException {
        this.server = new SudokuService();
    }

    public void setUserSudoku(UserSudoku s) {
        this.us = s;
        drawSudoku(canvas.getGraphicsContext2D());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedCol = 0;
        selectedRow = 0;
    }

    public void back(ActionEvent event) throws IOException, SQLException {
        System.out.println("Takaisin valikkoon");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/menu.fxml"));
        loader.load();

        MenuController mc = loader.getController();
        mc.setUser(server.getUserById(us.getUserId()));

        Parent root = loader.getRoot();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene s = new Scene(root);
        window.setTitle("menu");
        window.setScene(s);
        window.show();
    }

    public void save() {
        boolean succes = server.save(us);
        if (!succes) {
            info.setText("Jokin meni pieleen, ei saatu tallennettua");
        } else {
            info.setText("Peli tallennettu");
        }
    }

    private void drawSudoku(GraphicsContext context) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int x = col * 50 + 2;
                int y = row * 50 + 2;
                int width = 46;
                if (row < 3 || row > 5) {
                    if (col < 3 || col > 5) {
                        context.setFill(Color.WHITE);
                    } else {
                        context.setFill(Color.GAINSBORO);
                    }
                } else {
                    if (col < 3 || col > 5) {
                        context.setFill(Color.GAINSBORO);
                    } else {
                        context.setFill(Color.WHITE);
                    }
                }
                context.fillRoundRect(x, y, width, width, 10, 10);
            }
        }
        context.setStroke(Color.ROYALBLUE);
        context.setLineWidth(5);
        context.strokeRoundRect(selectedCol * 50 + 2, selectedRow * 50 + 2, 46, 46, 10, 10);
        setNumbers(context);
        canvas.addEventFilter(MouseEvent.ANY, (e) -> canvas.requestFocus());
    }

    private void setNumbers(GraphicsContext context) {
        int[][] sudoku = us.getSudoku();
        int[][] originalSudoku = us.getOriginalSudoku();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int x = col * 50 + 20;
                int y = row * 50 + 30;
                if (originalSudoku[row][col] != 0) {
                    context.setFill(Color.RED);
                    context.setFont(new Font(20));
                    context.fillText(originalSudoku[row][col] + "", x, y);

                } else if (sudoku[row][col] != 0) {
                    context.setFill(Color.BLACK);
                    context.setFont(new Font(20));
                    context.fillText(sudoku[row][col] + "", x, y);
                }
            }
        }
    }

    public void canvasCellClicked() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedCol = (int) event.getX() / 50;
                selectedRow = (int) event.getY() / 50;
                drawSudoku(canvas.getGraphicsContext2D());
            }
        });
    }

    public void keyTyped() {
        canvas.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                int nro = 0;
                try {
                    if (event.getCharacter().equals("w")) {
                        if (selectedRow > 0) {
                            selectedRow = selectedRow - 1;
                            drawSudoku(canvas.getGraphicsContext2D());
                        }
                    } else if (event.getCharacter().equals("s")) {
                        if (selectedRow < 8) {
                            selectedRow = selectedRow + 1;
                            drawSudoku(canvas.getGraphicsContext2D());
                        }
                    } else if (event.getCharacter().equals("d")) {
                        if (selectedCol < 8) {
                            selectedCol = selectedCol + 1;
                            drawSudoku(canvas.getGraphicsContext2D());
                        }
                    } else if (event.getCharacter().equals("a")) {
                        if (selectedCol > 0) {
                            selectedCol = selectedCol - 1;
                            drawSudoku(canvas.getGraphicsContext2D());
                        }
                    } else {
                        nro = Integer.parseInt(event.getCharacter());
                        if (us.getOriginalSudoku()[selectedRow][selectedCol] != 0) {
                            info.setText("Alkuperäistä lukua ei voi muuttaa");
                        } else {
                            us.setCell(selectedRow, selectedCol, nro);
                        }
                        drawSudoku(canvas.getGraphicsContext2D());
                    }
                } catch (NumberFormatException e) {
                    info.setText("Kirjaimet eivät kelpaa");
                }
            }
        }
        );
    }

    public void checkSudoku() {
        boolean wrong = server.checkSudoku(us);
        if (wrong) {
            info.setText("Onnittelut! Oikein meni");
        } else {
            info.setText("Väärin meni");
        }
    }
}
