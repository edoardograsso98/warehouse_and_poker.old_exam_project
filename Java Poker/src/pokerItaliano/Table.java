package pokerItaliano;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Table {
	
	
	Scene tableScene;
	BorderPane paneTable;
	TableButtons tableButtons;
	Pot yourPot,aiPot,tablePot;
	VBox potBox,textBox;
	GameEngine gameEngine;
	
	
	public Table(int aiLevel) {
		
		Text t = new Text("GOOD LUCK!");
		gameEngine = new GameEngine(aiLevel);
		potBox = new VBox();
		this.potBox.getChildren().addAll(
				gameEngine.getAiPot().getPotText(),
				gameEngine.getTablePot().getPotText(),
				gameEngine.getYourPot().getPotText());
		this.potBox.setSpacing(30);
		this.paneTable = new BorderPane();
		this.paneTable.setBackground(new Background(new BackgroundFill(Color.GREEN,new CornerRadii(1000), new Insets(0,200,0,0))));
		this.tableButtons = new TableButtons();
		this.textBox = new VBox();
		this.textBox.setMaxSize(200, 200);
		this.textBox.setMinSize(200, 200);
		this.textBox.getChildren().add(t);
		this.paneTable.getChildren().addAll(this.tableButtons.getButtonsBox(),this.potBox,this.textBox);
		this.tableButtons.getButtonsBox().relocate(1400, 100);
		this.textBox.relocate(0, 500);
		this.potBox.relocate(50, 225);
		this.tableScene = new Scene(paneTable,1600,650);
		this.setButtonsActions();
		
		
	}

	public void setButtonsActions() {
		tableButtons.getGiveCards().setOnAction(e ->clickGiveCards());
		tableButtons.getChange().setOnAction(e -> clickChange());
		tableButtons.getCheck().setOnAction(e -> clickCheck());
		tableButtons.getFold().setOnAction(e -> clickFold());
		tableButtons.getRaise().setOnAction(e -> clickRaise());
		tableButtons.getBet().setOnAction(e -> clickBet());
	}
	
	public void clickGiveCards() {
		gameEngine.getText().setText("");
		System.out.println(textBox.getChildren().size());
		textBox.getChildren().clear();
		tableButtons.setButtons2();
		gameEngine.startPlaying();
		gameEngine.getAiHand().getDeckHBox().setSpacing(20);
		gameEngine.getYourHand().getDeckHBox().setSpacing(20);
		this.getPaneTable().getChildren().addAll(gameEngine.getAiHand().getDeckHBox(),gameEngine.getYourHand().getDeckHBox());
		gameEngine.getAiHand().getDeckHBox().relocate(275,0);
		gameEngine.getYourHand().getDeckHBox().relocate(275,450);
	}
	
	public void clickChange() {
		Text t = new Text();
		this.tableButtons.setButton3();
		System.out.println(gameEngine.getYourHand().getCards());
		System.out.println(gameEngine.getAiHand().getCards());
		this.gameEngine.Change();
		this.gameEngine.aiChange();
		t.setText("AI changed " + gameEngine.changedCards + "cards" );
		textBox.getChildren().add(t);
		System.out.println(gameEngine.getYourHand().getCards());
		System.out.println(gameEngine.getAiHand().getCards());
		
		
	}
	
	public void clickCheck() {
		this.gameEngine.getYourPot().checkBetting(gameEngine.getAiBet(), gameEngine.getTablePot());
		gameEngine.setWinner();
		gameEngine.endPlaying();
		this.paneTable.setCenter(gameEngine.text);
		this.tableButtons.setButtons1();
	}
	
	public void clickFold() {
		gameEngine.winForFold(gameEngine.aiHand);
		gameEngine.endPlaying();
		this.tableButtons.setButtons1();
		this.getPaneTable().setCenter(gameEngine.text);
	}
	
	public void clickRaise(){
		Text t = new Text();
		if(tableButtons.getRaisingOf().getText()=="") {
			System.err.println("Invalide value");
			t.setText("Invalid input");
			textBox.getChildren().add(t);
		}
		else {
			gameEngine.getYourPot().raise(Integer.parseInt(tableButtons.getRaisingOf().getText()),gameEngine.getAiBet(), gameEngine.getTablePot());
			gameEngine.setYourBet(Integer.parseInt(tableButtons.getRaisingOf().getText()));
			this.tableButtons.setButton4();
			if(this.gameEngine.aiBetting()) {
				tableButtons.setButton4();
				t.setText("AI raised of " + gameEngine.getAiBet());
				textBox.getChildren().add(t);
			}
			else {
				t.setText(gameEngine.text.getText());
				textBox.getChildren().add(t);
				gameEngine.endPlaying();
				this.getPaneTable().setCenter(gameEngine.text);
				tableButtons.setButtons1();
			}
			
		}
	}
	
	public void clickBet() {
		Text t = new Text();
		if(tableButtons.getRaisingOf().getText()==""){
			System.err.println("Invalide value");
			t.setText("Invalid input");
		}	else {
			gameEngine.getYourPot().bet(Integer.parseInt(tableButtons.getRaisingOf().getText()), gameEngine.getTablePot());
			gameEngine.setYourBet(Integer.parseInt(tableButtons.getRaisingOf().getText()));
			this.tableButtons.setButton4();
			if(this.gameEngine.aiBetting()) {
				tableButtons.setButton4();
				t.setText("AI raised of " + gameEngine.getAiBet());
				textBox.getChildren().add(t);
			}
			else {
				t.setText(gameEngine.text.getText());
				textBox.getChildren().add(t);
				gameEngine.endPlaying();
				this.getPaneTable().setCenter(gameEngine.text);
				tableButtons.setButtons1();
			}
			
		}
		
	}
	
	
	
	
	
	public BorderPane getPaneTable() {
		return paneTable;
	}

	public void setPaneTable(BorderPane table) {
		this.paneTable = table;
	}


	public Scene getTableScene() {
		return tableScene;
	}

	public void setTableScene(Scene tableScene) {
		this.tableScene = tableScene;
	}


	public TableButtons getTableButtons() {
		return tableButtons;
	}

	public void setTableButtons(TableButtons tableButtons) {
		this.tableButtons = tableButtons;
	}

	public Pot getYourPot() {
		return yourPot;
	}

	public void setYourPot(Pot yourPot) {
		this.yourPot = yourPot;
	}

	public Pot getAiPot() {
		return aiPot;
	}

	public void setAiPot(Pot aiPot) {
		this.aiPot = aiPot;
	}

	public Pot getTablePot() {
		return tablePot;
	}

	public void setTablePot(Pot tablePot) {
		this.tablePot = tablePot;
	}

	public VBox getPotBox() {
		return potBox;
	}

	public void setPotBox(VBox potBox) {
		this.potBox = potBox;
	}

	public GameEngine getGameEngine() {
		return gameEngine;
	}

	public VBox getTextBox() {
		return textBox;
	}

	public void setTextBox(VBox textBox) {
		this.textBox = textBox;
	}
	
	
	
}
