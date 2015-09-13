package org.example.lmax;

import org.example.lmax.disruptor.DisruptorHandle;

import com.lmax.disruptor.EventHandler;

public class TestDisruptor {
	public void testPublicToDisruptor(){
		EventHandler<Context> handler = new EventHandler<Context>() {
			
			public void onEvent(Context arg0, long arg1, boolean arg2) throws Exception {
				System.out.println(arg0.getData());
				
			}
		};
		DisruptorHandle disruptor = new DisruptorHandle(512);
		disruptor.injectService(handler);
		disruptor.push(1);
		disruptor.push(2);
		disruptor.push(3);
	}
}
