package com.multithreading.customlock;

import java.util.concurrent.locks.StampedLock;


public class StampedLockClass {

	public void testStampedLock() {
        
		//ReadWrite lock plus optimistic
		// It will check using stamp whether some other thread changed or modified
		// the value if not then update else roll back
		
		StampedLock lock = new StampedLock();
		
		ThreadHandler stamplockObj1 = new ThreadHandler();
		ThreadHandler stamplockObj2 = new ThreadHandler();


		Thread t1 = new Thread(() -> {
			stamplockObj1.reading(lock);
		});

		Thread t2 = new Thread(() -> {
			stamplockObj2.writting(lock);
		});


		
		t1.start();
		t2.start();

	}
	

	 class ThreadHandler {
		 
		 int sharedVariable=11;
			
		 public void reading(StampedLock lock) {
			try {
				long stamp=lock.tryOptimisticRead();
				System.out.println("Taken optimistic lock and thread Ocuurred By reading : " + Thread.currentThread().getName());
				sharedVariable=10;
				Thread.sleep(5000);
				
				if(lock.validate(stamp)) {
					
					System.out.println("Updated the resource successfully: ");
				}else {
					sharedVariable=11;
					System.out.println("RollBack the  resource : ");
				}
			} catch (InterruptedException e) {
			} 
		}

		 public void writting(StampedLock lock) {
			 long stamp=lock.writeLock();
			 try {
				System.out.println("thread Ocuurred By writting : " + Thread.currentThread().getName());
				sharedVariable=9;
				Thread.sleep(1000);

			} catch (InterruptedException e) {
			} finally {
				lock.unlock(stamp);
				System.out.println("lock is released By writting : " + Thread.currentThread().getName());
			}
		}
		}
}
