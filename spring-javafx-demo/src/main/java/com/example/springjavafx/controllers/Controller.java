package com.example.springjavafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private Button btn;

    @FXML
    private TextArea area;

    public void change(MouseEvent mouseEvent) {
        if(area.getText()==null||area.getText().equals(""))
            area.setText("En Taro Tallnut!");
        else
            area.setText("");

    }
}
