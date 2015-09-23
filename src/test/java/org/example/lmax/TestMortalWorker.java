package org.example.lmax;

import org.example.lmax.workerpool.MortalWorker;
import org.example.lmax.workerpool.OnePublisherWorkerPool;
import org.junit.Test;

public class TestMortalWorker {
	@Test
	public void testWorker(){
		MortalWorker w1;
		try {
			w1 = new MortalWorker(TestWorker.class, 10);
			OnePublisherWorkerPool pool = new OnePublisherWorkerPool(w1);
			for(int i = 0; i<100;i++)
				pool.push(i);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
