package com.multithreading.customlock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ReadWriteLockClass {

	public void testReadWriteLock() {
        
		//Read lock : more than one thread can aquire the read lock (shared)
		//Write Lock : only one thread can aquire the write lock(Exclusive)
		
		ReadWriteLock lock = new ReentrantReadWriteLock();
		
		ThreadHandler readlockObj1 = new ThreadHandler();
		ThreadHandler readlockObj2 = new ThreadHandler();
		ThreadHandler writelockObj1 = new ThreadHandler();
		ThreadHandler writelockObj2 = new ThreadHandler();

		Thread t1 = new Thread(() -> {
			readlockObj1.reading(lock);
		});

		Thread t2 = new Thread(() -> {
			readlockObj2.reading(lock);
		});

		Thread t3 = new Thread(() -> {
			writelockObj1.writting(lock);
		});

		Thread t4 = new Thread(() -> {
			writelockObj2.writting(lock);
		});
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	
	 class ThreadHandler {
			
		 public void reading(ReadWriteLock lock) {
			try {
				lock.readLock().lock();
				System.out.println("Lock Ocuurred By reading : " + Thread.currentThread().getName());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			} finally {

				lock.readLock().unlock();

				System.out.println("Lock Released By reading : " + Thread.currentThread().getName());
			}
		}

		 public void writting(ReadWriteLock lock) {
			try {
				lock.writeLock().lock();
				System.out.println("Lock Ocuurred By writting : " + Thread.currentThread().getName());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			} finally {

				lock.writeLock().unlock();
				System.out.println("Lock Released By writting: " + Thread.currentThread().getName());
			}
		}
		}
}
