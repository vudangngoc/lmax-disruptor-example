package org.example.lmax;



import java.io.PrintStream;
import com.lmax.disruptor.EventFactory;

public class Context{
	private PrintStream client;
	public PrintStream getClient() {return client;}
	public void setClient(PrintStream client){this.client = client;}
	private long data;	
	public final static EventFactory<Context> EVENT_FACTORY = new EventFactory<Context>() {
        public Context newInstance() {
            return new Context();
        }
    };
	public void setData(long data) {
		this.data = data;		
	}
	public long getData(){return data;}
}
