package com.valerie;

import java.util.concurrent.TimeUnit;

public class Loader implements Runnable {

	private int howMuchCanLoad = 2;
	private GoldMine goldMine;
	private Cart cart;

	public Loader(GoldMine goldMine, Cart cart) {
		this.goldMine = goldMine;
		this.cart = cart;
	}

	public void run() {
		while (goldMine.getGold() > 0) {
			try {
				goldMine.getLoaderSemaphore().acquire();
				System.out.println("------------------------------------------------------------------------");
				System.out.println("In the mine: " + goldMine.getGold() + " kg of gold.");
				System.out.println("------------------------------------------------------------------------");
				System.out.println("     ************ LOADER get cart.");

				for (int i = 0; i < 3 && goldMine.getGold() > 0; i++) {
					int tmp = (Math.min(goldMine.getGold(), howMuchCanLoad));
					System.out.println("Loader put in the cart " + tmp + " kg of gold...");
					TimeUnit.SECONDS.sleep(1);
					cart.setWeight(cart.getWeight() + tmp);
					goldMine.setGold(goldMine.getGold() - tmp);
					
				}

				System.out.println("Loader filled the cart.. In the cart: " + cart.getWeight() + " kg of gold.");

				System.out.println("The loader handed the cart to the transporter!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			goldMine.getTransporterSemaphore().release();
		}
	}

}
