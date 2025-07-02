package pokerItaliano;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public final class Pot {

	private int money;
	public Text potText;
	public String name;
	
	
	public Pot() {
		
		potText = new Text();
		this.potText.setText(this.name + " :" + this.money);
		this.potText.setFont(new Font(30));
		this.potText.setFill(Color.WHEAT);
	}
	
	public Pot(int pot) {
		// TODO Auto-generated constructor stub
		potText = new Text();
		this.money = pot;
		this.potText.setText(this.name + " :" + this.money);
		this.potText.setFill(Color.WHEAT);
	}
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int nmoney) {
		this.money = nmoney;
		this.potText.setText(name + " :" + this.money);
	}

	public void addMoney(int money) {
		this.setMoney(this.getMoney() + money);
	}

	public void subtractMoney(int money) {
		this.setMoney(this.getMoney() - money);
	}

	public void bet(int yourBetting, Pot gamePot) {
		gamePot.addMoney(yourBetting);
		this.subtractMoney(yourBetting);
	}

	public void checkBetting(int opponentBetting, Pot gamePot) {
		this.subtractMoney(opponentBetting);
		gamePot.addMoney(opponentBetting);
	}
	
	public void raise(int yourRaising, int opponentOriginalBetting, Pot gamePot) {
		gamePot.addMoney(yourRaising + opponentOriginalBetting);
		this.subtractMoney(yourRaising + opponentOriginalBetting);
	}
	
	public void checkRaising(int opponentRaising, Pot gamePot) {
		this.subtractMoney(opponentRaising);
		gamePot.addMoney(opponentRaising);
	}
	
	public static void fold(Pot opponentPot, Pot gamePot) {
		opponentPot.addMoney(gamePot.getMoney());
		gamePot.subtractMoney(gamePot.getMoney());
	}

	public Text getPotText() {
		return potText;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPotText(Text potText) {
		this.potText = potText;
	}
	
}