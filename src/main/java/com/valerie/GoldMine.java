package com.valerie;

import java.util.concurrent.Semaphore;

public class GoldMine {

	private volatile int gold;

	public GoldMine(int gold) {
		this.gold = gold;
	}

	private Semaphore loaderSemaphore = new Semaphore(1);
	private Semaphore transporterSemaphore = new Semaphore(0);
	private Semaphore unloaderSemaphore = new Semaphore(0);

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public Semaphore getLoaderSemaphore() {
		return loaderSemaphore;
	}

	public void setLoaderSemaphore(Semaphore loaderSemaphore) {
		this.loaderSemaphore = loaderSemaphore;
	}

	public Semaphore getTransporterSemaphore() {
		return transporterSemaphore;
	}

	public void setTransporterSemaphore(Semaphore transporterSemaphore) {
		this.transporterSemaphore = transporterSemaphore;
	}

	public Semaphore getUnloaderSemaphore() {
		return unloaderSemaphore;
	}

	public void setUnloaderSemaphore(Semaphore unloaderSemaphore) {
		this.unloaderSemaphore = unloaderSemaphore;
	}

}
