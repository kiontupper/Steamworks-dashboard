package com.midcoastmaineiacs.Steamworks.dashboard;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserPreferences;
import com.teamdev.jxbrowser.chromium.events.TitleEvent;
import com.teamdev.jxbrowser.chromium.events.TitleListener;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.prefs.Preferences;

public class Main extends Application {
	public static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		BrowserPreferences.setChromiumSwitches("--remote-debugging-port=9222");
		Main.stage = stage;
		Preferences p = Preferences.userRoot().node("MMDashboard");
		stage.setX(p.getDouble("xpos", 10));
		stage.setY(p.getDouble("ypos", 10));
		stage.setWidth(p.getDouble("width", 800));
		stage.setHeight(p.getDouble("height", 600));
		stage.setMaximized(p.getBoolean("maximized", true));
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
		System.out.println("Debugging url: " + browser.getRemoteDebuggingURL());
		browser.loadHTML(index.replace(" src=\"jquery-3.2.1.slim.min.js\">", ">" + jq));
		browser.addTitleListener(titleEvent -> Platform.runLater(() -> stage.setTitle(titleEvent.getTitle())));
		stage.setOnCloseRequest((we) -> {
			stage.hide();
			p.putBoolean("maximized", stage.isMaximized());
			p.putDouble("xpos", stage.getX());
			p.putDouble("ypos", stage.getY());
			p.putDouble("width", stage.getWidth());
			p.putDouble("height", stage.getHeight());
			System.exit(0);
		});
	}

	private static StringBuilder readFile(String name) {
		BufferedReader index = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(name)));
		StringBuilder page = new StringBuilder();
		try {
			String line = index.readLine();
			while (line != null) {
				page.append(line);
				page.append('\n');
				line = index.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	}
}
