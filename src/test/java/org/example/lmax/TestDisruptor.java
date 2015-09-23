package org.example.lmax;

import java.util.ArrayList;

import org.example.lmax.disruptor.DisruptorHandle;
import org.junit.Test;

import com.lmax.disruptor.EventHandler;

public class TestDisruptor {
	@Test
	public void test1PublishTo3Consummer(){
		ArrayList<EventHandler<Context>> arr = new ArrayList<EventHandler<Context>>();
		arr.add(new EventHandler<Context>() {
			
			public void onEvent(Context arg0, long arg1, boolean arg2) throws Exception {
				System.out.println("Handle with #1: " + arg0.getData());
				
			}
		});
		arr.add(new EventHandler<Context>() {
			
			public void onEvent(Context arg0, long arg1, boolean arg2) throws Exception {
				System.out.println("Handle with #2: "+ arg0.getData());
				
			}
		});
		arr.add(new EventHandler<Context>() {
			
			public void onEvent(Context arg0, long arg1, boolean arg2) throws Exception {
				System.out.println("Handle with #3: "+ arg0.getData());
				
			}
		});
		DisruptorHandle disruptor = new DisruptorHandle(512);
		disruptor.injectServices(arr);
		for(long i = 0; i<1000;i++)
		disruptor.push(i);
	}
}
