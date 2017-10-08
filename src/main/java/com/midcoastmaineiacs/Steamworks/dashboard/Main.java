package com.midcoastmaineiacs.Steamworks.dashboard;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.TitleEvent;
import com.teamdev.jxbrowser.chromium.events.TitleListener;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main extends Application {
	public static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		Main.stage = stage;
		stage.setMaximized(true);
		stage.setTitle("Jeffrey");
		WebView webview = new WebView();
		StackPane pane = new StackPane();
		Browser browser = new Browser();
		BrowserView view = new BrowserView(browser);
		pane.getChildren().add(view);
		Scene scene = new Scene(pane, stage.getWidth(), stage.getHeight());
		stage.setScene(scene);
		stage.show();
		String index = readFile("index.html").toString();
		String jq = readFile("jquery-3.2.1.slim.min.js").toString();
		browser.loadHTML(index.replace(" src=\"jquery-3.2.1.slim.min.js\">", ">" + jq));
	}

	private static StringBuilder readFile(String name) {
		BufferedReader index = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(name)));
		StringBuilder page = new StringBuilder();
		try {
			String line = index.readLine();
			while (line != null) {
				page.append(line);
				line = index.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	}
}
