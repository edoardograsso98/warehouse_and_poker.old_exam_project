package pokerItaliano;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TableButtons {

	Button change,raise,check,fold,giveCards,bet;
	TextField raisingOf;
	VBox buttonsBox;
	
	public TableButtons() {
		// TODO Auto-generated constructor stub
		change = new Button("CHANGE");
		raise = new Button("RAISE");
		check = new Button("CHECK");
		fold = new Button("FOLD");
		giveCards = new Button("GIVE CARDS");
		bet = new Button("BET");
		raisingOf = new TextField();
		buttonsBox = new VBox();
		this.setButtonsSize(150.0, 70.0);
		this.fullBox();	
		
	}
	

	private void setButtonsSize(Double w, Double h) {
		giveCards.setMaxSize(w, h);
		giveCards.setMinSize(w, h);
		this.setButtonsStyle(giveCards);
		change.setMaxSize(w, h);
		change.setMinSize(w, h);
		this.setButtonsStyle(change);
		raise.setMaxSize(w, h);
		raise.setMinSize(w, h);
		this.setButtonsStyle(raise);
		check.setMaxSize(w, h);
		check.setMinSize(w, h);
		this.setButtonsStyle(check);
		fold.setMaxSize(w, h);
		fold.setMinSize(w, h);
		this.setButtonsStyle(fold);
		bet.setMaxSize(w, h);
		bet.setMinSize(w, h);
		this.setButtonsStyle(bet);
		raisingOf.setMaxSize(w, h);
		raisingOf.setMinSize(w, h);
		raisingOf.setPromptText("Raising of...");
		raisingOf.setEditable(true);
	}

	private void setButtonsStyle(Button b) {
		b.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, new CornerRadii(0), new Insets(10,10,10,10))));
		b.setTextFill(Color.WHITE);
	}
	
	private void fullBox() {
		this.setButtons1();
		this.buttonsBox.setSpacing(10);
		this.buttonsBox.getChildren().addAll(giveCards,change,raisingOf,bet,raise,check,fold);
	}
	
	public void setNButton(Button bu,Double d) {
		bu.setOpacity(d);
		if(d==0.5) bu.setDisable(true);
		if(d==1) bu.setDisable(false);
		
	}
	
	public void setButtons1() {
		this.setNButton(giveCards, 1.0);
		this.setNButton(change, 0.5);
		this.setNButton(check, 0.5);
		this.setNButton(raise, 0.5);
		this.setNButton(bet, 0.5);
		this.setNButton(fold, 0.5);
		raisingOf.setDisable(true);
	}
	
	public void setButtons2() {
		this.setNButton(giveCards, 0.5);
		this.setNButton(change, 1.0);
		this.setNButton(check, 0.5);
		this.setNButton(raise, 0.5);
		this.setNButton(bet, 0.5);
		this.setNButton(fold, 0.5);
		raisingOf.setDisable(true);
	}
	
	public void setButton3() {
		this.setNButton(giveCards, 0.5);
		this.setNButton(change, 0.5);
		this.setNButton(check, 0.5);
		this.setNButton(raise, 0.5);
		this.setNButton(bet, 1.0);
		this.setNButton(fold, 1.0);
		raisingOf.setDisable(false);
	}
	
	public void setButton4() {
		this.setNButton(giveCards, 0.5);
		this.setNButton(change, 0.5);
		this.setNButton(check, 1.0);
		this.setNButton(raise, 1.0);
		this.setNButton(bet, 0.5);
		this.setNButton(fold, 1.0);
		raisingOf.setDisable(false);
	}
	public VBox getButtonsBox() {
		return buttonsBox;
	}

	public void setButtonsBox(VBox buttonsBox) {
		this.buttonsBox = buttonsBox;
	}

	public Button getChange() {
		return change;
	}

	public Button getRaise() {
		return raise;
	}

	public Button getCheck() {
		return check;
	}

	public Button getFold() {
		return fold;
	}

	public TextField getRaisingOf() {
		return raisingOf;
	}


	public Button getGiveCards() {
		return giveCards;
	}


	public void setGiveCards(Button giveCards) {
		this.giveCards = giveCards;
	}

	public Button getBet() {
		return bet;
	}
	
	
}
