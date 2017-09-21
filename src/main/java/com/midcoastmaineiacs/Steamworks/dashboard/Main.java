package com.midcoastmaineiacs.Steamworks.dashboard;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		stage.setMaximized(true);
		stage.setTitle("Jeffrey");
		WebView webview = new WebView();
		Scene scene = new Scene(new Browser(), stage.getWidth(), stage.getHeight());
		stage.setScene(scene);
		stage.show();
	}
}

class Browser extends Region {
	private final WebView browser = new WebView();
	private final WebEngine engine = browser.getEngine();

	public Browser() {
		getStyleClass().add("browser");
		engine.load("file:///media/tupster24/Robotics/Steamworks Dashboard/index.html");
		getChildren().add(browser);
	}

	@Override
	protected void layoutChildren() {
		double w = getWidth();
		double h = getHeight();
		layoutInArea(browser,0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
	}
}
