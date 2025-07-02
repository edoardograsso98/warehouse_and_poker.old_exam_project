package pokerItaliano;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Classe Card: Permette di creare oggetti di tipo Card, i quali andranno a comporre il mazzo, questi oggetti sono descritti da due attributi: il valore Value e il seme Suit

public class Card {

	private Suit suit;
	private Value value;
	private Image image;
	private ImageView imageView;
	
	public enum Suit {
		
		SPADES(1), CLUBS(2), DIAMONDS(3), HEARTS(4);
		
		private int cardSuitValue;
		
		Suit(int cardSuitValue) {
			this.cardSuitValue = cardSuitValue;
		}
		
		
		int getcardSuitValue() {
			return this.cardSuitValue;
		}

	}
	
	public enum Value {
		
		NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);
		
		private int cardValue;
		
		Value(int cardValue) {
			this.cardValue = cardValue;
		}
		
		public int getcardValue() {
			return this.cardValue;
		}
		
	}

	//Funzioni get e set per valore e seme

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
	
	//Costruttore
	
	public Card() {
		// TODO Auto-generated constructor stub
	}
	
	public Card(Suit suit, Value value) {
/*		this.hbox = new HBox();
		this.hbox.setMaxSize(150, 200);
		this.hbox.setMinSize(150, 200);
*/		
		this.suit = suit;
		this.value = value;
		this.setCovered();
	}

	//Per mostrare le carte e le mani, queste vengono scritte nella forma "Value" OF "Suit"

	public void setCovered() {
		try {
			image = new Image(new FileInputStream("/home/nick/eclipse-workspace/Poker/card_images/red_back.png"));
			imageView = new ImageView(image);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void discoverCard()  {
		Image image;
		try {
			image = new Image(new FileInputStream("/home/nick/eclipse-workspace/Poker/card_images/" + value.toString() + " OF " + suit.toString()+ ".png"));
			imageView.setImage(image);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String toString() {
		return this.value.toString() + " OF " + this.suit.toString();
	}
	
	public int cardValue() {
		int k = 0;
		k = this.getValue().getcardValue()*10 + this.getSuit().getcardSuitValue();
		return k;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	
}

