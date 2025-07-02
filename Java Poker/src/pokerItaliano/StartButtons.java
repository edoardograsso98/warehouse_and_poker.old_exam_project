package pokerItaliano;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StartButtons {

	Stage stage;
	Scene playingScene;
	Button easy,medium,hard,play;
	HBox difficultyBox;
	
	public StartButtons() {
		// TODO Auto-generated constructor stub
		easy = new Button("EASY");
		easy.setOpacity(0.5);
		medium = new Button("MEDIUM");
		hard = new Button("HARD");
		play = new Button("PLAY");
		this.setButtonsSize(200.0, 75.0);
		difficultyBox = new HBox();
		this.fullBox();
		
		easy.setOnAction(e -> setDifficulty(easy, medium, hard, 1));
		medium.setOnAction(e -> setDifficulty(medium, easy, hard, 2));
		hard.setOnAction(e -> setDifficulty(hard, easy, medium, 3));
	}
	
	private void setButtonsSize(Double w, Double h) {
		easy.setMaxSize(w, h);
		easy.setMinSize(w, h);
		medium.setMaxSize(w, h);
		medium.setMinSize(w, h);
		hard.setMaxSize(w, h);
		hard.setMinSize(w, h);
		play.setMaxSize(w, h);
		play.setMinSize(w, h);
		this.setButtonsStyle(easy, Color.WHITE, Color.DARKBLUE);
		this.setButtonsStyle(medium, Color.WHITE, Color.DARKBLUE);
		this.setButtonsStyle(hard, Color.WHITE, Color.DARKBLUE);
		this.setButtonsStyle(play, Color.MIDNIGHTBLUE, Color.YELLOW);
	}
	
	private void setButtonsStyle(Button b, Color textColor, Color backgroundColor) {
		b.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(100), new Insets(10,10,10,10))));
		b.setTextFill(textColor);
	}
	
	private void fullBox() {

		this.difficultyBox.getChildren().addAll(easy,medium,hard);
	}
	
	private void setDifficulty(Button b1,Button b2, Button b3,int i) {
		b1.setOpacity(0.5);
		b2.setOpacity(1);
		b3.setOpacity(1);
	}
	
	public int getDifficulty() {
		if(easy.getOpacity()==0.5) return 1;
		if(medium.getOpacity()==0.5) return 2;
		if(hard.getOpacity()==0.5) return 3;
		else return 1;
	}
	

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Scene getPlayingScene() {
		return playingScene;
	}

	public void setPlayingScene(Scene playingScene) {
		this.playingScene = playingScene;
	}

	public Button getEasy() {
		return easy;
	}

	public void setEasy(Button easy) {
		this.easy = easy;
	}

	public Button getMedium() {
		return medium;
	}

	public void setMedium(Button medium) {
		this.medium = medium;
	}

	public Button getHard() {
		return hard;
	}

	public void setHard(Button hard) {
		this.hard = hard;
	}

	public Button getPlay() {
		return play;
	}

	public void setPlay(Button play) {
		this.play = play;
	}

	public HBox getDifficultyBox() {
		return difficultyBox;
	}

	public void setDifficultyBox(HBox difficultyBox) {
		this.difficultyBox = difficultyBox;
	}
}
