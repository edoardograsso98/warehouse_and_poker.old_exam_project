package pokerItaliano;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javafx.scene.layout.HBox;

import java.lang.Math;

//Classe Deck: Oggetti che hanno come attributi un insieme di 24 carte e con i quali è possibile fare operazioni come mescolare, riordinare e mostrare il mazzo

public class Deck extends Card{
	
	public enum Hands {

		HIGH_HAND(1), ONE_PAIR(2), TWO_PAIR(3), THREE_OF_A_KIND(4), STRAIGHT(5), FLUSH(6), FULL_HOUSE(7), FOUR_OF_A_KIND(8), STRAIGHT_FLUSH(9), ROYAL_FLUSH(10);

		private int handValue;

		Hands(int handValue){
			this.handValue = handValue;
		}

		public int gethandValue() {
			return this.handValue;
		}	
	}

	public HBox deckHBox = new HBox();
	private ArrayList<Card> cards;
	private Hands pokerClass;

	public Hands getPokerClass() {
		this.handAnalyzer();
		return pokerClass;
	}

	public void setPokerClass(Hands pokerClass) {
		this.pokerClass = pokerClass;
	}

	public Deck() {
		this.cards = new ArrayList<Card>();
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public void setCovered(){
		for (Card card : cards) {
			card.setCovered();
		}
	}
	
	public void discoverCards() {
		for (Card card : cards) {
			card.discoverCard();
		}
	}
	
	public void generateOrderedDeck() {
		for(Suit cardSuit : Suit.values()) {
			for(Value cardValue : Value.values()) {
				Card c = new Card(cardSuit, cardValue);
				this.addCard(c);
			}
		}
	}

	public void shuffleDeck() {
		ArrayList<Card> temporaryDeck = new ArrayList<Card>();
		Random random = new Random();
		int randomCardIndex = 0;
		int originalSize = this.cards.size();
		for(int i = 0; i < originalSize; i++) {
			randomCardIndex = random.nextInt(this.cards.size());
			temporaryDeck.add(this.getCard(randomCardIndex));
			this.removeCard(randomCardIndex);
		}
		this.cards = temporaryDeck;
	}

	public String toString() {
		String deckString = "";
		int i = 1;
		for(Card aCard : this.cards) {
			deckString += i + " - " + aCard.toString() + "\n";
			i++;
		}
		return deckString;
	}

	public void addCard(Card addCard) {
		this.cards.add(addCard);
		this.deckHBox.getChildren().add(addCard.getImageView());
	}

	public Card getCard(int i) {
		return this.cards.get(i);
	}

	public Card getCard(Card c) {
		return c;
	}
	
	public void removeCard(int i) {
		this.deckHBox.getChildren().remove(this.cards.get(i).getImageView());
		this.cards.remove(i);
	}
	
	public void removeCard(Card c) {
		this.cards.remove(c);
		this.deckHBox.getChildren().remove(c.getImageView());
	}

	public void draw(Deck comingFrom) {
		this.addCard(comingFrom.getCard(0));
		comingFrom.removeCard(0);
//		comingFrom.getDeckHBox().getChildren().remove(comingFrom.getCard(0).getImageView());
		
	}

	
	public void reorderDeck() {
		Collections.sort(this.getCards(), new Comparator<Card>() {
			public int compare(Card randomC1, Card randomC2) {
				return Integer.valueOf(randomC1.getValue().getcardValue()*10+randomC1.getSuit().getcardSuitValue()).compareTo(randomC2.getValue().getcardValue()*10 + randomC2.getSuit().getcardSuitValue());
			}
		});
	}
	
	//Funzione che decreta il vincitore sfidante.theWinnerIs(vincitore) se la mano tra () è veramente la vincente ritorna true, sennò false
	public boolean winsOver (Deck aHand) {
			if(this.absHandValue() < aHand.absHandValue())
				return false;
		return true;
	}
	//modificata!
	public double absHandValue() {
		double absHandValue = 0;
		this.reorderPoints();
		for(int i = 0; i < 5; i++)
			absHandValue += this.getCard(i).cardValue()*Math.pow(10, i);
		switch(this.getPokerClass()) {
		case HIGH_HAND:
		return absHandValue * Math.pow(10, -9);
		case ONE_PAIR:
		return absHandValue * Math.pow(10, -8);
		case TWO_PAIR:
		return absHandValue * Math.pow(10, -7);
		case THREE_OF_A_KIND:
		return absHandValue * Math.pow(10, -6);
		case STRAIGHT:
		return absHandValue * Math.pow(10, -5);
		case FLUSH:
		return absHandValue * Math.pow(10, -4);
		case FULL_HOUSE:
		return absHandValue * Math.pow(10, -3);
		case FOUR_OF_A_KIND:
		return absHandValue * Math.pow(10, -2);
		case STRAIGHT_FLUSH:
		return absHandValue * Math.pow(10, -1);
		default:
		return absHandValue * Math.pow(10, 0);
		}
	}

	private Hands equalitiesAnalyzer() {
		int k = 0;
		ArrayList<Card> handCards = new ArrayList<Card>();
		ArrayList<Card> handCards2 = new ArrayList<Card>();
		handCards.addAll(this.getCards());
		handCards2.addAll(this.getCards());
		for(Card aCard : handCards) {
			handCards2.remove(aCard);
			for(Card anotherCard : handCards2) 
				if(aCard.getValue().equals(anotherCard.getValue())) {
					k++;
				}
		}
		switch(k) {
		case 0 : return Hands.HIGH_HAND;
		case 1 : return Hands.ONE_PAIR;
		case 2 : return Hands.TWO_PAIR;
		case 3 : return Hands.THREE_OF_A_KIND;
		case 4 : return Hands.FULL_HOUSE;
		default : return Hands.FOUR_OF_A_KIND;
		}
	}

	private boolean isFlush() {
		int k = 0;
		for(Card aCard : this.getCards()) {
			if(this.getCard(0).getSuit().equals(aCard.getSuit()))
				k++;
		}
		if(k == 5) return true;
		return false;
	}

	private boolean theresoneNine() {
		for(Card aCard : this.getCards())
			if(aCard.getValue().equals(Value.NINE))
				return true;
		return false;
	}


	private boolean theresoneAce() {
		for(Card aCard : this.getCards())
			if(aCard.getValue().equals(Value.ACE))
				return true;
		return false;
	}

	private boolean isStraight() {
		if(theresoneNine() && theresoneAce()) 
			return false;
		int k = 0;
		ArrayList<Card> handCards = new ArrayList<Card>();
		ArrayList<Card> handCards2 = new ArrayList<Card>();
		handCards.addAll(this.getCards());
		handCards2.addAll(this.getCards());
		for(Card aCard : handCards) {
			handCards2.remove(aCard);
			for(Card anotherCard : handCards2) 
				if(aCard.getValue().equals(anotherCard.getValue())) 
					k++;
		}
		if(k == 0) 
			return true;
		return false;
	}

	private void handAnalyzer() {
		if(isStraight() && isFlush() && theresoneAce()) this.setPokerClass(Hands.ROYAL_FLUSH);
		else if(isStraight() && isFlush() && theresoneNine()) this.setPokerClass(Hands.STRAIGHT_FLUSH);
		else if(this.equalitiesAnalyzer() == Hands.FOUR_OF_A_KIND) this.setPokerClass(Hands.FOUR_OF_A_KIND);
		else if(this.equalitiesAnalyzer() == Hands.FULL_HOUSE) this.setPokerClass(Hands.FULL_HOUSE);
		else if(isFlush()) this.setPokerClass(Hands.FLUSH);
		else if(isStraight()) this.setPokerClass(Hands.STRAIGHT);
		else if(this.equalitiesAnalyzer() == Hands.THREE_OF_A_KIND) this.setPokerClass(Hands.THREE_OF_A_KIND);
		else if(this.equalitiesAnalyzer() == Hands.TWO_PAIR) this.setPokerClass(Hands.TWO_PAIR);
		else if(this.equalitiesAnalyzer() == Hands.ONE_PAIR) this.setPokerClass(Hands.ONE_PAIR);
		else this.setPokerClass(Hands.HIGH_HAND);
	}
	
	//Funzione da chiamare in caso di parità tra i valori delle mani dei giocatori;
	//il suo scopo è quello di riordinare la mano in modo da avere i punti, per es la coppia o il tris come ultime due o ultime tre carte della mano;
	//in questo modo con la funzione absHandValue che associa ad ogni carta successiva un valore di un ordine di grandezza superiore al precedente;
	//è sempre possibile selezionare una mano vincente, non finendo mai in parità.
	public void reorderPoints() {
		this.reorderDeck();
		this.handAnalyzer();
		switch(this.getPokerClass()) {
		case ONE_PAIR:
			Card[] pair = new Card[2];
			for(int i = 0; i < 4; i++) {
				if(this.getCard(i).getValue() == this.getCard(i+1).getValue()) {
					pair[0] = this.getCard(i);
					pair[1] = this.getCard(i+1);
				}
			}
			Card[] nonpair = new Card[3];
			int l = 0;
			for(int i = 0; i < 5; i ++) {
				if(this.getCard(i) != pair[0] && this.getCard(i) != pair[1]) {
					nonpair[l] = this.getCard(i);
					l++;
				}
			}
			ArrayList<Card> every = new ArrayList<Card>();
			for(int i = 0; i < 3; i++)
				every.add(nonpair[i]);
			for(int i = 0; i < 2; i++)
				every.add(pair[i]);
			this.setCards(every);
			break;
		case TWO_PAIR:
			Card[] pair1 = new Card[2];
			Card[] pair2 = new Card[2];
			Card[] single = new Card[1];
			for(int i = 0; i < 2; i++) {
				if(this.getCard(i).getValue() == this.getCard(i+1).getValue()) {
					pair1[0] = this.getCard(i);
					pair1[1] = this.getCard(i+1);
				}
			}
			for(int i = 2; i < 4; i++) {
				if(this.getCard(i).getValue() == this.getCard(i+1).getValue()) {
					pair2[0] = this.getCard(i);
					pair2[1] = this.getCard(i+1);
				}
			}
			for(int i = 0; i < 5; i++) {
				if(this.getCard(i) != pair1[0] && this.getCard(i) != pair1[1] && this.getCard(i) != pair2[0] && this.getCard(i) != pair2[1])
					single[0] = this.getCard(i);
			}
			ArrayList<Card> ordered = new ArrayList<Card>();
			ordered.add(single[0]);
			for(int i = 0; i < 2; i++)
				ordered.add(pair1[i]);
			for(int i = 0; i < 2; i++)
				ordered.add(pair2[i]);
			this.setCards(ordered);
			break;
		case THREE_OF_A_KIND:
			Card[] three = new Card[3];
			for(int i = 0; i < 3; i++) {
				if(this.getCard(i).getValue() == this.getCard(i+1).getValue() && this.getCard(i).getValue() == this.getCard(i+2).getValue()) {
					three[0] = this.getCard(i);
					three[1] = this.getCard(i+1);
					three[2] = this.getCard(i+2);
				}
			}
			Card[] notthree = new Card[2];
			int k = 0;
			for(int i = 0; i < 5; i++) {
				if(this.getCard(i) != three[0] && this.getCard(i) != three[1] && this.getCard(i) != three[2]) {
					notthree[k] = this.getCard(i);
					k++;
				}
			}
			ArrayList<Card> all = new ArrayList<Card>();
			for(int i = 0; i < 2; i++)
				all.add(notthree[i]);
			for(int i = 0; i < 3; i++)
				all.add(three[i]);
			this.setCards(all);
			break;
		case FOUR_OF_A_KIND:
			if(this.getCard(0).getValue() != this.getCard(1).getValue())
				break;
			else {
				Card[] lonely = new Card[1];
				lonely[0] = this.getCard(4);
				Card[] four = new Card[4];
				for(int i = 0; i < 4; i++)
					four[i] = this.getCard(i);
				ArrayList<Card> poker = new ArrayList<Card>();
				poker.add(lonely[0]);
				for(int i = 0; i < 4; i++)
					poker.add(four[i]);
				break;
			}
		default:
			if(this.getCard(1).getValue() != this.getCard(2).getValue())
				break;
			else {
				Card[] couple = new Card[2];
				Card[] threesome = new Card[3];
				couple[0] = this.getCard(3); couple[1] = this.getCard(4);
				threesome[0] = this.getCard(0); threesome[1] = this.getCard(1); threesome[2] = this.getCard(2);
				ArrayList<Card> full = new ArrayList<Card>();
				for(int i = 0; i < 2; i++)
					full.add(couple[i]);
				for(int i = 0; i < 3; i++)
					full.add(threesome[i]);
				this.setCards(full);
				break;
			}
		}
	}

	public HBox getDeckHBox() {
		return deckHBox;
	}

	public void setDeckHBox(HBox deckHBox) {
		this.deckHBox = deckHBox;
	}

}


