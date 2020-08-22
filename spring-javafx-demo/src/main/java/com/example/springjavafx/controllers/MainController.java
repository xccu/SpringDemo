package com.example.springjavafx.controllers;

import com.example.springjavafx.fx.FxmlView;
import com.example.springjavafx.fx.StageManager;
import com.example.springjavafx.utils.SpringUtils;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainController implements Initializable {


	@FXML
	public VBox sideBar;
	@FXML
	private VBox views;
	@FXML
	public ScrollPane body;
	@FXML
	public Label title;
	@FXML
	private ScrollPane scroll;

	@FXML
	private Button home;
	@FXML
	private Button about;
	@FXML
	private Button webview;
	@FXML
	private Button hamburger;

	@FXML
	private StackPane root;
	@FXML
	private JFXButton config;
	@FXML
	private VBox drawer;
	@FXML
	private JFXBadge messages;
	@FXML
	private JFXBadge notifications;
	@FXML
	private JFXBadge bg_info;

	public static final PopOver popConfig = new PopOver();
	public static final PopOver popup = new PopOver();

	private ObservableList<Button> items = FXCollections.observableArrayList();

	boolean scrolling = false;

	private Parent popContent;
	public static MainController ctrl;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ctrl = this;
		loadContentPopup();

		populateItems();

		// about();

		try {
			addSubPop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void altLayout() {

	}

	private void addEvents() {
	}

	private void addSubPop() throws Exception {

	}

	private void addEvent(Node node) {

	}

	private void populateItems() {

	}

	private void loadContentPopup() {

	}

	private void updateBody(FxmlView view) {
		title.setText(view.title());
		SpringUtils.getBean(StageManager.class).switchContent(view, body);
	}

	@FXML
	private void openConfig() {

	}

	@FXML
	private void dashboard() {
		try {
			URL url = new ClassPathResource("views/module/dashboard.fxml").getURL();
			StackPane other = FXMLLoader.load(url);
			body.setContent(other);
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
	}



	@FXML
	private void jfxTextField() {

	}

	@FXML
	private void about() {
		try {
			URL url = new ClassPathResource("views/module/profile.fxml").getURL();
			StackPane other = FXMLLoader.load(url);
			body.setContent(other);
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	@FXML
	private void webview() {
		try {
			URL url = new ClassPathResource("views/module/webview.fxml").getURL();
			StackPane other = FXMLLoader.load(url);
			body.setContent(other);
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	@FXML
	private void webview2() {

	}

	private PopOver pop = new PopOver();

	@FXML
	private void openMessages() {

	}

	@FXML
	private void openNotification() {

	}

}
