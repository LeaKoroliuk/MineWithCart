package com.valerie;


public class Transporter implements Runnable{
	
	private GoldMine goldMine;

	public Transporter(GoldMine goldMine) {
		this.goldMine = goldMine;
	}


	public void run() {
		goldMine.shipping();
	}

}
