package org.example.lmax;

import org.example.lmax.workerpool.OnePublisherWorkerPool;
import org.junit.Test;

import com.lmax.disruptor.WorkHandler;

public class TestWorkerPool {
	@Test
	public void testPublishToWorkerPool(){
		WorkHandler<Context> w1 = new WorkHandler<Context>() {
			
			public void onEvent(Context event) throws Exception {
				System.out.println("Worker 1 is processing data: " + event.getData());				
			}
		};
		WorkHandler<Context> w2 = new WorkHandler<Context>() {
			
			public void onEvent(Context event) throws Exception {
				System.out.println("Worker 2 is processing data: " + event.getData());				
			}
		};
		OnePublisherWorkerPool pool = new OnePublisherWorkerPool(w1,w2);
		for(int i = 0; i<100;i++)
			pool.push(i);
	}
}
