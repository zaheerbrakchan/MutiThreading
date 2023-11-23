package com.multithreading;

import java.util.concurrent.CountDownLatch;

public class ThreadCounterProblem {

	int counter=0;
	
	// here the latch is using so that it can use to make the other operations wait till
	// the the no of thread defined in the CountDownLatch get completed

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
        
        // here this method will only execute once the above threads get completed
        displayFinalCounterValue();
	}
	
	void increment() {
		counter++;
	}
	
	void displayFinalCounterValue() {
		System.out.println("Counter value : "+this.counter);
	}
}
