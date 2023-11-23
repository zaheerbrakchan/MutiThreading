package com.multithreading.customlock;

import java.util.concurrent.Semaphore;

public class SemarPhorLockClass {

	public void testSemarPhorLock() {
		
		Semaphore lock = new Semaphore(2);

		//unlike synchronized lock here even if we are using multiple objects 
		//only one thread will execute at one time
		ThreadHandler obj1 = new ThreadHandler();
		ThreadHandler obj2 = new ThreadHandler();
		ThreadHandler obj3 = new ThreadHandler();

		Thread t1 = new Thread(() -> {
			obj1.producer(lock);
		});
		t1.setName("thread1");

		Thread t2 = new Thread(() -> {
			obj2.producer(lock);
		});
		t2.setName("thread2");
		
		Thread t3 = new Thread(() -> {
			obj3.producer(lock);
		});
		t3.setName("thread3");

		t1.start();
		t2.start();
		t3.start();
	}
	class ThreadHandler {

		 public void producer(Semaphore lock) {
			try {
				lock.acquire();
				System.out.println("Lock Ocuurred By Producer : " + Thread.currentThread().getName());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			} finally {

				lock.release();

				System.out.println("Lock Released By Producer : " + Thread.currentThread().getName());
			}
		}


	}
}
