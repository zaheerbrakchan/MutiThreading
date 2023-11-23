package com.multithreading.customlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.multithreading.customlock.ReentrantLockClass.ThreadHandler;

public class InterThreadCommunicationCustomLock {
	
	ReentrantLock lock = new ReentrantLock();
	Condition condition = lock.newCondition(); 
	
	boolean isProduced=false;

	public void testReentantInterThreadCommunicationCustomLock() {

		//unlike synchronized lock here even if we are using multiple objects 
		//only one thread will execute at one time
		ThreadHandler obj1 = new ThreadHandler();
		ThreadHandler obj2 = new ThreadHandler();

		Thread t1 = new Thread(() -> {
			for(int i=0;i<2;i++) {
			obj1.producer();}
		});

		Thread t2 = new Thread(() -> {
			for(int i=0;i<2;i++) {
			obj2.consumer();}
		});

		t1.start();
		t2.start();
	}

	class ThreadHandler {

		 public void producer() {
			try {
				lock.lock();
				while(isProduced) {
					condition.await();
				}
				System.out.println("Lock Ocuurred By Producer : " + Thread.currentThread().getName());
				Thread.sleep(5000);
				isProduced=true;
				condition.signal(); // signal the consumer
			} catch (InterruptedException e) {
			} finally {

				lock.unlock();

				System.out.println("Lock Released By Producer : " + Thread.currentThread().getName());
			}
		}

		 public void consumer() {
			try {
				lock.lock();
				while(!isProduced) {
					condition.await();
				}
				System.out.println("Lock Ocuurred By Consumer : " + Thread.currentThread().getName());
				Thread.sleep(5000);
				isProduced=false;
				condition.signal();
			} catch (InterruptedException e) {
			} finally {

				lock.unlock();
				System.out.println("Lock Released By Consumer: " + Thread.currentThread().getName());
			}
		}
	}
}
