package org.example.lmax;

import com.lmax.disruptor.WorkHandler;

public class TestWorker implements WorkHandler<Context>{
	private static int lifeTime = 0;
	public TestWorker(){
		lifeTime += 1;
		System.out.println("Init TestWorker round: " + TestWorker.lifeTime);
	}
	public void onEvent(Context arg0) throws Exception {
		
	}
}
