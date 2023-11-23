package com.multithreading.customlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockClass {

	public void testReentantLock() {
		ReentrantLock lock = new ReentrantLock();

		//unlike synchronized lock here even if we are using multiple objects 
		//only one thread will execute at one time
		ThreadHandler obj1 = new ThreadHandler();
		ThreadHandler obj2 = new ThreadHandler();

		Thread t1 = new Thread(() -> {
			obj1.producer(lock);
		});

		Thread t2 = new Thread(() -> {
			obj2.consumer(lock);
		});

		t1.start();
		t2.start();
	}

	class ThreadHandler {

		 public void producer(ReentrantLock lock) {
			try {
				lock.lock();
				System.out.println("Lock Ocuurred By Producer : " + Thread.currentThread().getName());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			} finally {

				lock.unlock();

				System.out.println("Lock Released By Producer : " + Thread.currentThread().getName());
			}
		}

		 public void consumer(ReentrantLock lock) {
			try {
				lock.lock();
				System.out.println("Lock Ocuurred By Consumer : " + Thread.currentThread().getName());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			} finally {

				lock.unlock();
				System.out.println("Lock Released By Consumer: " + Thread.currentThread().getName());
			}
		}
	}
}
