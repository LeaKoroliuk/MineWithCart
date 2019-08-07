package com.valerie;

public class Main {

	public static void main(String[] args) {

		GoldMine goldMine = new GoldMine(11);
		Cart cart = new Cart();

		new Thread(new Loader(goldMine, cart)).start();
		new Thread(new Transporter(goldMine, cart)).start();
		new Thread(new Unloader(goldMine, cart)).start();
	
	}
}
