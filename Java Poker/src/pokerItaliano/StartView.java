package pokerItaliano;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StartView {

	Scene startScene;
	StartButtons startButtons;
	Label label;
	BorderPane startView;
	Font f = new Font(70);
	
	public StartView() {
		// TODO Auto-generated constructor stub
		this.label = new Label("POKER ITALIANO");
		this.label.setFont(f);
		this.label.setTextFill(Color.RED);
		
		this.startButtons = new StartButtons();
		
		this.startView = new BorderPane();
//		this.startView.setBackground(new Background(new BackgroundImage(new Image)));
		this.fullBorderPane();
		this.startScene = new Scene(startView,600,300);
		
	}

	private void fullBorderPane() {
		this.startView.setTop(this.label);
		this.startView.setCenter(this.startButtons.getDifficultyBox());
		this.startView.setBottom(this.startButtons.getPlay());
		this.startView.setAlignment(startButtons.getPlay(), Pos.BOTTOM_CENTER);
		this.startView.setAlignment(label, Pos.TOP_CENTER);
		this.startView.setAlignment(startButtons.getDifficultyBox(), Pos.CENTER);
	}
	
	public StartButtons getStartButtons() {
		return startButtons;
	}

	public void setStartButtons(StartButtons startButtons) {
		this.startButtons = startButtons;
	}


	public BorderPane getStartView() {
		return startView;
	}

	public void setStartView(BorderPane startView) {
		this.startView = startView;
	}


	public Scene getStartScene() {
		return startScene;
	}


	public void setStartScene(Scene startScene) {
		this.startScene = startScene;
	}
	
	
	
}
