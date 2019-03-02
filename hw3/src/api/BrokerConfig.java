/**
 * 
 */
package api;

/**
 * Some constants needed by the Manager and Worker objects.
 * 
 * @author david
 * @see manager.Manager
 * @see worker.Worker
 */
public interface BrokerConfig {
	/** the broker string for Apache ActiveMQ. */
	public static final String DEFAULT_BROKER = "tcp://localhost:61616";
	/** the name of the Manager-to-Worker queue */
	public static final String DATA_QUEUE = "hw3.queue.Data";
	/** the name of the Worker-to-Manager queue */
	public static final String WORKER_QUEUE = "hw3.queue.Worker";
}
