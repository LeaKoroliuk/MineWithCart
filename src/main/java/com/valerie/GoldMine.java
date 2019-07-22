package com.valerie;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class GoldMine {

	private int gold = 96;
	private volatile int cart;
	private int cartCapacity = 6;
	private Semaphore loaderSemaphore = new Semaphore(1);
	private Semaphore transporterSemaphore = new Semaphore(0);
	private Semaphore unloaderSemaphore = new Semaphore(0);

	public void loading() {
		while (gold > 0) {
			try {
				loaderSemaphore.acquire();
				System.out.println("In the mine: " + gold + " kg of gold.");
				System.out.println("---------- LOADER get cart.");
				for (int i = 0; i < cartCapacity / 2; i++) {
					cart += 2;
					System.out.println("Loader put in the cart " + 2 + " kg of gold...");
					TimeUnit.SECONDS.sleep(1);
				}
				System.out.println("Loader filled the cart.. In the cart: " + cart + " kg of gold.");
				gold -= cart;
				System.out.println("The loader handed the cart to the transporter!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			transporterSemaphore.release();
		}
	}

	public void shipping() {
		while (gold > 0) {
			try {
				transporterSemaphore.acquire();
				System.out.println("---------- TRANSPORTER get cart and takes it to unload ...");
				TimeUnit.SECONDS.sleep(5);
				System.out.println("The transporter handed the cart to the unloader!");
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			unloaderSemaphore.release();
		}
	}

	public void unloading() {
		while (gold > 0) {
			try {
				unloaderSemaphore.acquire();
				System.out.println("---------- UNLOADER get cart. In the cart: " + cart + " kg of gold.");
				for (int i = 0; i < cartCapacity / 3; i++) {
					cart -= 3;
					System.out.println("Unloader unloaded from the cart " + 3 + " kg of gold...");
					TimeUnit.SECONDS.sleep(1);
				}
				System.out.println("Unloader unloaded the cart.. In the cart: " + cart + " kg of gold.");
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if (gold > 0) {
				System.out.println("The unloader handed the cart to the loader!");
				System.out.println("------------------------------------------------------------------------");
				System.out.println("------------------------------------------------------------------------");
				loaderSemaphore.release();
			} else {
				System.out.println("\nTHERE IS NO GOLD IN THE MINE. THE WORK IS DONE!!");
			}
		}
	}

}
