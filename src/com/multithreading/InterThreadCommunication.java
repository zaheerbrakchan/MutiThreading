package com.multithreading;

public class InterThreadCommunication {

	public void testInterThreadCommunication() {
		
		SharedResource shredResourceObj = new SharedResource();
		
		Thread producerThread = new Thread(()->{
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			shredResourceObj.addItem();
		});
		
		Thread consumerThread = new Thread(()->{
			shredResourceObj.consumeItem();
		});
		
		
		producerThread.start();
		consumerThread.start();
	}
	
	
	class SharedResource{
		
		boolean itemAvailable=false;
		
	synchronized public void addItem() {
		
			itemAvailable=true;
			System.out.println("Item Added");
			notifyAll();
		}
		
	synchronized public void consumeItem() {
		System.out.println("Inside consumeItem");
		while(!itemAvailable) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			itemAvailable=false;
			System.out.println("Item Consumed");
		}
		
	}
}
