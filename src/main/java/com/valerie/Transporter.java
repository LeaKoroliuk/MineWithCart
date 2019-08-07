package com.valerie;

import java.util.concurrent.TimeUnit;

public class Transporter implements Runnable {

	private GoldMine goldMine;
	private Cart cart;

	public Transporter(GoldMine goldMine, Cart cart) {
		this.goldMine = goldMine;
		this.cart = cart;
	}

	public void run() {
		while (goldMine.getGold() > 0) {
			try {
				goldMine.getTransporterSemaphore().acquire();
				if (cart.getWeight() > 0) {
					System.out.println("     ************ TRANSPORTER get cart and takes it to unload ...");
					for (int i = 0; i < 5; i++) {
						System.out.println("Transporter comes with a cart ... ");
						TimeUnit.SECONDS.sleep(1);
					}
					System.out.println("The transporter handed the cart to the unloader!");
					goldMine.getUnloaderSemaphore().release();
				}
				if (cart.getWeight() == 0) {
					System.out.println("     ************ TRANSPORTER get cart and takes it to load ...");
					for (int i = 0; i < 5; i++) {
						System.out.println("Transporter comes with a cart ... ");
						TimeUnit.SECONDS.sleep(1);
					}
					System.out.println("The transporter handed the cart to the loader!");
					goldMine.getLoaderSemaphore().release();
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

}
