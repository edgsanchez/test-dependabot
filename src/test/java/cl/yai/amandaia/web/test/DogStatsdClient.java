package cl.yai.amandaia.web.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mercadolibre.metrics.datadog.DatadogMetricCollector;
import com.timgroup.statsd.NonBlockingStatsDClientBuilder;
import com.timgroup.statsd.StatsDClient;
public class DogStatsdClient {
	private static Logger log = LoggerFactory.getLogger(DogStatsdClient.class);
	
	@Test
	void contextLoads() throws InterruptedException {
	       StatsDClient Statsd = new NonBlockingStatsDClientBuilder()
	               .prefix("statsd")
	               .hostname("localhost")
	               .port(8125)
	               .build();
	           for (int i = 0; i < 10; i++) {
	        	   
	               Statsd.incrementCounter("example_metric_v2.increment", new String[]{"environment:dev"});
	               Statsd.decrementCounter("example_metric_v2.decrement", new String[]{"environment:dev"});
	               Statsd.count("example_metric_v2.count", 2, new String[]{"environment:dev"});
	               Statsd.recordGaugeValue("example_metric_v2.gauge", i, new String[]{"environment:dev"});
	               log.info("Iter:"+i);
	               Thread.sleep(1000);
	           }
	}
	
	@Test
	void contextLoads2() throws InterruptedException {

		DatadogMetricCollector datadogMetricColector = new DatadogMetricCollector();
		datadogMetricColector.incrementCounter("example_metric_v3.increment", new String[]{"environment:dev"});
		datadogMetricColector.recordGaugeValue("example_metric_v3.gauge", 7, new String[]{"environment:dev"});
		
	}	
}
