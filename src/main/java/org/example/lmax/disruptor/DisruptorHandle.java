package org.example.lmax.disruptor;

import java.io.PrintStream;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.example.lmax.Context;

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
	public void injectServices(List<EventHandler<Context>> services){		
		if(services.size() == 0) return;
		disruptor.handleEventsWith(services.get(0));
		if(services.size() > 1) 
			for(int i = 1; i < services.size(); i++)
				disruptor.after(services.get(i - 1)).handleEventsWith(services.get(i));
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
