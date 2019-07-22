package com.valerie;

public class Loader implements Runnable{
	
	private GoldMine goldMine;

	public Loader(GoldMine goldMine) {
		this.goldMine = goldMine;
	}
	
	public void run() {
		goldMine.loading();
	}

}
