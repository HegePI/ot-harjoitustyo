/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author heikki
 */
public class GameController implements Initializable {

    private logic.SudokuService server;

    @FXML
    private Button back, save;
    @FXML
    private Canvas canvas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        drawSudoku(canvas.getGraphicsContext2D());
    }

    public void back() {
        System.out.println("Palataan takaisin valikkoon");
    }

    public void save() {
        System.out.println("Tallennetaan peli");

    }

    private void drawSudoku(GraphicsContext context) {
        System.out.println("Alustetaan näkymä");
        context.fillRoundRect(0, 0, 45, 45, 1, 1);
    }
}
