package org.example.lmax.workerpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.example.lmax.Context;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.WorkerPool;

public class OnePublisherWorkerPool {
	public OnePublisherWorkerPool(WorkHandler<Context> ... w){
		workerPool = new WorkerPool<Context>(Context.EVENT_FACTORY, Context.EXCEPTION_HANDLE,w );
		EXECUTOR = Executors.newFixedThreadPool(w.length);
		ringBuffer = workerPool.start(Executors.newCachedThreadPool());
	}
	public void push(long data){
		long sequence = ringBuffer.next();
		Context event = ringBuffer.get(sequence);
		event.setData(data);
		ringBuffer.publish(sequence);
	}
	ExecutorService EXECUTOR;
	RingBuffer<Context> ringBuffer;
	WorkerPool<Context> workerPool;
}
