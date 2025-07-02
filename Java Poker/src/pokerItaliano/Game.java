package pokerItaliano;


import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {

	
	StartView startView = new StartView();
	Table table;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(startView.getStartScene());
		startView.getStartButtons().getPlay().setOnAction(e -> 	{	
				table = new Table(startView.startButtons.getDifficulty());
				primaryStage.setScene(table.getTableScene());
	});
		primaryStage.setX(500);
		primaryStage.setY(300);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
