/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.Sudoku;
import logic.SudokuService;

/**
 * FXML Controller class
 *
 * @author heikki
 */
public class GameController implements Initializable {

    private final logic.SudokuService server;
    private Sudoku s;
    int selectedRow;
    int selectedCol;

    @FXML
    private Button back, save;
    @FXML
    private Canvas canvas;
    @FXML
    private Label info;

    /**
     * Initializes the controller class.
     */
    public GameController() throws ClassNotFoundException, SQLException {
        this.server = new SudokuService();
    }

    public void setSudoku(Sudoku s) {
        this.s = s;
        drawSudoku(canvas.getGraphicsContext2D());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        selectedCol = 0;
        selectedRow = 0;
    }

    public void back() {
        System.out.println("Palataan takaisin valikkoon");
    }

    public void save() {
        System.out.println("Tallennetaan peli");

    }

    private void drawSudoku(GraphicsContext context) {
        System.out.println("Alustetaan näkymä");
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int x = col * 50 + 2;
                int y = row * 50 + 2;
                int width = 46;
                context.setFill(Color.WHITE);
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
        int[][] sudoku = s.getSudoku();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int x = col * 50 + 20;
                int y = row * 50 + 30;
                context.setFill(Color.BLACK);
                context.setFont(new Font(20));
                if (sudoku[row][col] != 0) {
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
                    nro = Integer.parseInt(event.getCharacter());
                    if (nro == 0) {
                        info.setText("0 ei kelpaa sudokuun >:(");
                    } else {
                        s.setCell(selectedRow, selectedCol, nro);
                        drawSudoku(canvas.getGraphicsContext2D());
                    }
                } catch (Exception e) {
                    info.setText("Kirjaimet eivät kelpaa");
                }
            }
        });
    }
}
