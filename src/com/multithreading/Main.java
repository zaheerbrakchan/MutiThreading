package com.multithreading;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

import com.multithreading.customlock.InterThreadCommunicationCustomLock;
import com.multithreading.customlock.ReadWriteLockClass;
import com.multithreading.customlock.ReentrantLockClass;
import com.multithreading.customlock.SemarPhorLockClass;
import com.multithreading.customlock.StampedLockClass;

public class Main {
	
  public static void main(String args[]) {
	 
	  // counter problem with multiple thread
	  // if we run this for 50 times then for some it will give some inconsistent outputs
	  for(int i=0;i<100;i++) {
	  ThreadCounterProblem 	threadCounterProblemObj = new ThreadCounterProblem();
	  threadCounterProblemObj.testThreadCounterProblem();
	  }
	  
	  // counter solution with multiple thread
	  // now as we have used syncronized keyword at a time only one thread will execute and we won't see inconsitent in the output
	  for(int i=0;i<50;i++) {
	  ThreadCounterSolutionUsingSynchronizedLock threadCounterSolutionObj = new ThreadCounterSolutionUsingSynchronizedLock();
	 // threadCounterSolutionObj.testThreadCounterProblem();
	  }
	  
	  // counter solution with multiple thread without using syncronized keyword
	  // now as we have used atomic lock free, at a time only one thread will execute and we won't see inconsitent in the output
	  for(int i=0;i<50;i++) {
	  ThreadCounterSolutionUsingAtomicLockFree threadCounterSolutionObj1 = new ThreadCounterSolutionUsingAtomicLockFree();
	 // threadCounterSolutionObj1.testThreadCounterProblem();
	  }
	  
	  //Syncronized monitor Lock example
	  
	  MonitorLockExample monitorLockExampleObj = new MonitorLockExample();
	  //monitorLockExampleObj.testMonitorLockExample();
	  
	 // SynchronizedMonitorLock synchronizedMonitorLockObj = new SynchronizedMonitorLock();
	  //synchronizedMonitorLockObj.testSynchronizedLock();
	  
	  // Inter Thread Communication Example
	  
	  InterThreadCommunication interThreadCommunication = new InterThreadCommunication();
	  //interThreadCommunication.testInterThreadCommunication();
	  
	  //ReentratLock example
	  
	    ReentrantLockClass reentrantLockClassObj = new ReentrantLockClass();
	  //  reentrantLockClassObj.testReentantLock();
	    
	    
	  //ReadWriteLock example
	    ReadWriteLockClass readWriteLockClass = new ReadWriteLockClass();
	    //readWriteLockClass.testReadWriteLock();
	    
	  //StampedLock example
	    StampedLockClass stampedLockClass = new StampedLockClass();
	   // stampedLockClass.testStampedLock();
	    
	 //SemaPhorLock exmaple
	    SemarPhorLockClass semarPhorLockClass= new SemarPhorLockClass();
	   // semarPhorLockClass.testSemarPhorLock();
	  
	  //Custom Lock inter thread communication exmaple  
	    InterThreadCommunicationCustomLock interThreadCommunicationCustomLock = new InterThreadCommunicationCustomLock();
	    //interThreadCommunicationCustomLock.testReentantInterThreadCommunicationCustomLock();
   }
  
}
