package com.valerie;

public class Unloader implements Runnable{
	
	private GoldMine goldMine;

	public Unloader(GoldMine goldMine) {
		this.goldMine = goldMine;
	}
	
	public void run() {
		goldMine.unloading();
	}

}

