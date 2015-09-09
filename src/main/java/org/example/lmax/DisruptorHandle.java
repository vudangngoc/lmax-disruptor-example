package org.example.lmax;

import java.io.PrintStream;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class DisruptorHandle {
	public DisruptorHandle(int ringSize){
		this.exec = Executors.newCachedThreadPool();
		// Preallocate RingBuffer with 1024 ValueEvents
		this.disruptor = new Disruptor<Context>(Context.EVENT_FACTORY, ringSize, exec);

	}
	@SuppressWarnings("unchecked")
	public void injectService(EventHandler<Context> service){		
		final EventHandler<Context> hanler = service;
		disruptor.handleEventsWith(hanler);
		this.ringBuffer = disruptor.start();
	}
	public void stopDisruptor(){
		disruptor.shutdown();
		exec.shutdown();
	}
	public void push(long data){
		long sequence = ringBuffer.next();
		Context event = ringBuffer.get(sequence);
		event.setData(data);
		ringBuffer.publish(sequence);
	}
	RingBuffer<Context> ringBuffer;
	Disruptor<Context> disruptor;
	EventHandler<Context> eventHandle;
	ExecutorService exec;
}
