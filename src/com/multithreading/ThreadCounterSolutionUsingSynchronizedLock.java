package com.multithreading;

import java.util.concurrent.CountDownLatch;

public class ThreadCounterSolutionUsingSynchronizedLock {

	int counter=0;
	
	CountDownLatch latch = new CountDownLatch(2);
	
	void testThreadCounterProblem() {
		
		Thread t1 = new Thread(()-> {
			for(int i=0;i<100;i++) {
				increment();
			}
			latch.countDown();
		});
		
		Thread t2 = new Thread(()-> {
			for(int i=0;i<100;i++) {
				increment();
			}
			latch.countDown();
		});
		
		t1.start();
		t2.start();
		
        try {
            latch.await(); // Wait for both threads to signal completion
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        displayFinalCounterValue();
	}
	
	 synchronized void increment() {
		counter++;
	}
	
	void displayFinalCounterValue() {
		System.out.println("Counter value : "+this.counter);
	}
}
