package com.valerie;

import java.util.concurrent.TimeUnit;

public class Unloader implements Runnable {

	private int howMuchCanUnload = 3;
	private GoldMine goldMine;
	private Cart cart;

	public Unloader(GoldMine goldMine, Cart cart) {
		this.goldMine = goldMine;
		this.cart = cart;
	}

	public void run() {
		while (goldMine.getGold() > 0) {
			try {
				goldMine.getUnloaderSemaphore().acquire();
				System.out.println(
						"     ************ UNLOADER get cart. In the cart: " + cart.getWeight() + " kg of gold.");

				for (int i = 0; i < 2 && cart.getWeight() > 0; i++) {
					int tmp = (Math.min(cart.getWeight(), howMuchCanUnload));
					System.out.println("Unloader unloaded from the cart " + tmp + " kg of gold...");
					TimeUnit.SECONDS.sleep(1);
					cart.setWeight(cart.getWeight() - tmp);
				}

				System.out.println("Unloader unloaded the cart.. In the cart: " + cart.getWeight() + " kg of gold.");
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if (goldMine.getGold() > 0) {
				System.out.println("The unloader handed the cart to the loader!");
				goldMine.getTransporterSemaphore().release();
			} else {
				System.out.println("\nTHERE IS NO GOLD IN THE MINE. THE WORK IS DONE!!");
			}
		}
	}

}
