package com.example.springjavafx.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import org.springframework.core.io.ClassPathResource;

import java.net.URL;

public class StageManager {

    public void switchContent(final FxmlView view, ScrollPane body) {
        try {

            //title.setText(view.title());
            URL url = new ClassPathResource(view.fxml()).getURL();
            StackPane other = FXMLLoader.load(url);
            body.setContent(other);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
