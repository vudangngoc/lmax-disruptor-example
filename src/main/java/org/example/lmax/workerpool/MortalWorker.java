package org.example.lmax.workerpool;

import org.example.lmax.Context;

import com.lmax.disruptor.WorkHandler;

public class MortalWorker implements WorkHandler<Context> {
	public MortalWorker(Class<? extends WorkHandler<Context>> clazz, int lifeTime) throws InstantiationException, IllegalAccessException{
		this.clazz = clazz;
		LIFE_TIME = lifeTime;
		RefreshWorker();
	}
	private int LIFE_TIME = 1024;
	private WorkHandler<Context> worker;
	private Class<? extends WorkHandler<Context>> clazz;
	int lifeTime = LIFE_TIME;
	public void onEvent(Context arg0) throws Exception {
		worker.onEvent(arg0);
		this.ReduceLifeTime();		
	}

	private void ReduceLifeTime() {
		this.lifeTime -= 1;
		if(lifeTime < 1)
			try {
				RefreshWorker();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void RefreshWorker() throws InstantiationException, IllegalAccessException {
		worker = clazz.newInstance();
		lifeTime = LIFE_TIME;
	}
}
