package com.multithreading;

public class MonitorLockExample {

	public void testMonitorLockExample() {

		MonitorLockClass obj = new MonitorLockClass();
		
		Thread t1 = new Thread(()->{
			obj.task1();
		});
		
		Thread t2 = new Thread(()->{
			obj.task2();
		});
		
		Thread t3 = new Thread(()->{
			obj.task3();
		});
		
		t1.start();
		t2.start();
		t3.start();
	}

class MonitorLockClass{ 
	synchronized public void task1() {
		System.out.println("inside task1");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" task1 completed");

	}
	
	public void task2() {
		System.out.println("inside task2 before syncronized");
		synchronized (this) {
			System.out.println("inside synchronized task2");
		}
		System.out.println(" task2 completed");

	}
	
	public void task3() {
		System.out.println("inside task3");

	}
}
}
