package com.example.springjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

@Slf4j   //日志使用注解
@SpringBootApplication
public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    //public static void main(String[] args) {SpringApplication.run(SpringjavafxApplication.class, args); }

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {
            //获取resources文件夹路径
            URL url = new ClassPathResource("views/main.fxml").getURL();
            //获取java文件夹路径
            //URL url = getClass().getResource("/sample.fxml");

            Parent root = FXMLLoader.load(url);
            //设置标题
            primaryStage.setTitle("DashboardFX");
            //设置窗体图标
            primaryStage.getIcons().add(new Image("/icons/icon.png"));
            //设置窗体尺寸
            primaryStage.setScene(new Scene(root, 1400, 840));

            primaryStage.show();
        }
        catch (Exception ex){
            log.error(ex.toString());
        }
    }
}
