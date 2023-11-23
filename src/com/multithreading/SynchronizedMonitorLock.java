package com.multithreading;

public class SynchronizedMonitorLock {

	void testSynchronizedLock() {
        
		// even if we have used synchronized keyword but if we are calling multiple threads 
		// using multiple objects then parallely the threads will run
		// and if we use same object then synchrozized keyworkd will work
		ThreadHandler obj1 = new ThreadHandler();
		ThreadHandler obj2 = new ThreadHandler();

		Thread t1 = new Thread(() -> {
			obj1.producer();
		});

		Thread t2 = new Thread(() -> {
			obj1.consumer();
		});

		t1.start();
		t2.start();
	}
	

	 class ThreadHandler {
		
	  public void producer() {
			try {
				System.out.println("Lock Ocuurred By Producer : " + Thread.currentThread().getName());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			} 

				System.out.println("Lock Released By Producer : " + Thread.currentThread().getName());

		}
		
	  public void consumer() {
			try {
				System.out.println("Lock Ocuurred By Consumer : " + Thread.currentThread().getName());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			} 

				System.out.println("Lock Released By Consumer: " + Thread.currentThread().getName());

		}
	}
}
