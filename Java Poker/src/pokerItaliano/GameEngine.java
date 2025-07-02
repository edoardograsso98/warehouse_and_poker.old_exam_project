package pokerItaliano;


import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameEngine{
	
	Deck playingDeck,yourHand,aiHand,changingCards,winner;
	Pot yourPot,aiPot,tablePot;
	int aiLevel, changedCards;
	Integer aiBet,yourBet;
	Integer times=0;
	Text text;
	
	public GameEngine(int aiLevel){
		text = new Text();
		this.setAiLevel(aiLevel);
		yourPot = new Pot();
		yourPot.setName("Your money");
		yourPot.setMoney(1000);
		aiPot = new Pot();
		aiPot.setName("Ai money");
		aiPot.setMoney(aiLevel*500);
		tablePot = new Pot();
		tablePot.setName("Pot");
		tablePot.setMoney(0);
		playingDeck = new Deck();
		playingDeck.generateOrderedDeck();
		changingCards = new Deck();
		aiHand = new Deck();
		yourHand = new Deck();
	}
	
	public void winForFold(Deck d) {
		this.winner = d;
	}
	
	public Deck setWinner() {
		if(yourHand.absHandValue()>aiHand.absHandValue()) winner =yourHand;
		else winner = aiHand;
		return winner;
	}
	
	public void startPlaying() {
		
		
		if(times>0) {
			for(int i = 0; i < 5; i++) {
				playingDeck.draw(yourHand);
				playingDeck.draw(aiHand);
			}
		}
		
		playingDeck.setCovered();
		
		times =+ 1;
		Deck nChangingCards = new Deck();
		Deck naiHand = new Deck();
		Deck nyourHand = new Deck();
		
		changingCards = nChangingCards;
		aiHand = naiHand;
		yourHand = nyourHand;
		
		for (int i=0; i < yourHand.getCards().size(); i++) {
			playingDeck.draw(yourHand);
		}
		playingDeck.shuffleDeck();
		
		for(int i = 0; i < 5; i++) {
			yourHand.draw(playingDeck);
			aiHand.draw(playingDeck);
		}
		
		yourHand.discoverCards();
		
		for (Card c : yourHand.getCards()) {
			c.getImageView().setOnMouseClicked(e -> clickCard(c));
		}
		System.out.println("AI's hand " + aiHand.getCards());
		System.out.println("Your hand " + yourHand.getCards());
	}

	public void clickCard(Card c) {
		if(changingCards.getCards().size()<3) {
			if(changingCards.getCards().contains(c)) {
				c.getImageView().setOpacity(1);
				changingCards.getCards().remove(c);
			}
			else  {
				c.getImageView().setOpacity(0.5);
				changingCards.getCards().add(c);
			}
		} else {
			c.getImageView().setOpacity(1);
			changingCards.removeCard(c);
		}
		System.out.println(c);
		System.out.println(changingCards.getCards());
		System.out.println(yourHand.getCards());
	}
	
	
	
	public void Change() {
		for (Card c : changingCards.getCards()) {
			yourHand.getCard(c).getImageView().setOpacity(1);
			yourHand.removeCard(c);
			playingDeck.addCard(c);
			yourHand.draw(playingDeck);
		}
		yourHand.discoverCards();
	}
	
	public void aiChange() {
		HBox hb = new HBox();
		hb.setSpacing(20);
		aiHand.reorderPoints();
		if(aiLevel == 1) System.out.println("Level easy");
		else {
			switch(aiHand.getPokerClass()) {
			case HIGH_HAND:
				for(int i = 0; i < 3; i++) {
					playingDeck.draw(aiHand);
					aiHand.draw(playingDeck);
				}
				System.out.println("AI changed three cards");
				changedCards = 3;
				break;	
			case ONE_PAIR:
				for(int i = 0; i < 3; i++) {
					playingDeck.draw(aiHand);
					aiHand.draw(playingDeck);
				}
				System.out.println("AI changed three cards");
				changedCards = 3;
				break;	
			case TWO_PAIR:
				playingDeck.draw(aiHand);
				aiHand.draw(playingDeck);
				System.out.println("AI changed one card");
				break;			
			case THREE_OF_A_KIND:
				for(int i = 0; i < 2; i++) {
					playingDeck.draw(aiHand);
					aiHand.draw(playingDeck);
				}
				System.out.println("AI changed two cards");
				break;	
			default: break;
				
				
			}
		}
		
	}
	
	
	public boolean aiBetting() {
		text.setFont(new Font(20));
		if(yourBet < aiPot.getMoney()*aiHand.getPokerClass().gethandValue()/100) {
			aiBet = aiHand.getPokerClass().gethandValue()*10*aiLevel;
			aiPot.raise(aiBet, yourBet, tablePot);	
			return true;
		}
		else if(yourBet >= aiPot.getMoney()*aiHand.getPokerClass().gethandValue()/100 && yourBet <= aiPot.getMoney()*aiHand.getPokerClass().gethandValue()/100 + 10*aiHand.getPokerClass().gethandValue()) {
			aiPot.checkBetting(yourBet, tablePot);
			text.setText("AI checked");
			this.setWinner();
			return false;
		}
		else if(yourBet > aiPot.getMoney()*aiHand.getPokerClass().gethandValue()/100 + 10*aiHand.getPokerClass().gethandValue()) {
			this.winForFold(yourHand);
			text.setText("AI folded");
			return false;
		} else return false;
		
	}
	
	public void endPlaying() {
		text.setFont(new Font(70));
		if(yourHand == winner) {
			yourPot.addMoney(tablePot.getMoney());
			text.setText("YOU WIN");
			text.setFill(Color.GOLD);
		} else {
			aiPot.addMoney(tablePot.getMoney());
			text.setText("AI WINS");
			text.setFill(Color.DARKRED);
		}
		tablePot.subtractMoney(tablePot.getMoney());
		aiHand.discoverCards();
		
	}
	
	
	
	public Deck getPlayingDeck() {
		return playingDeck;
	}

	public void setPlayingDeck(Deck playingDeck) {
		this.playingDeck = playingDeck;
	}

	public Deck getYourHand() {
		return yourHand;
	}

	public void setYourHand(Deck yourHand) {
		this.yourHand = yourHand;
	}

	public Deck getAiHand() {
		return aiHand;
	}

	public void setAiHand(Deck aiHand) {
		this.aiHand = aiHand;
	}

	public Deck getChangingCards() {
		return changingCards;
	}

	public void setChangingCards(Deck changingCards) {
		this.changingCards = changingCards;
	}

	public int getAiLevel() {
		return aiLevel;
	}

	public void setAiLevel(int aiLevel) {
		this.aiLevel = aiLevel;
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



	public Integer getAiBet() {
		return aiBet;
	}



	public void setAiBet(Integer aiBet) {
		this.aiBet = aiBet;
	}



	public Integer getYourBet() {
		return yourBet;
	}



	public void setYourBet(Integer yourBet) {
		this.yourBet = yourBet;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

}
