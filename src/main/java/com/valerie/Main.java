package com.valerie;

public class Main {

	public static void main(String[] args) {

		GoldMine goldMine = new GoldMine();

		new Thread(new Loader(goldMine)).start();
		new Thread(new Transporter(goldMine)).start();
		new Thread(new Unloader(goldMine)).start();
	
	}
}
