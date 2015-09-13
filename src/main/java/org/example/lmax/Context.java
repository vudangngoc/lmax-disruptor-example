package org.example.lmax;



import java.io.PrintStream;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.ExceptionHandler;

public class Context{
	public static ExceptionHandler<Context> EXCEPTION_HANDLE = new ExceptionHandler<Context>() {

		public void handleEventException(Throwable ex, long sequence, Context event) {
			System.out.println(ex.getMessage());			
		}

		public void handleOnStartException(Throwable ex) {
			System.out.println(ex.getMessage());			
		}

		public void handleOnShutdownException(Throwable ex) {
			System.out.println(ex.getMessage());			
		}
	};
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
