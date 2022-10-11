package cl.yai.amandaia.web.component;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.timgroup.statsd.NoOpStatsDClient;
//import com.mercadolibre.metrics.datadog.DatadogMetricCollector;
import com.timgroup.statsd.NonBlockingStatsDClientBuilder;
import com.timgroup.statsd.StatsDClient;

@Component
public class DatadogClientComponent implements InitializingBean{
	private static Logger log = LoggerFactory.getLogger(DatadogClientComponent.class);
	private StatsDClient client;

	@Value("${datadog.host}")
	private String host;
	
	@Value("${datadog.isEnabled}")
	private Boolean isEnabled;
	
	public void setup() {
		//this.datadogMetricColector = new DatadogMetricCollector(true);
		if(isEnabled) {
			client = new NonBlockingStatsDClientBuilder()
				.prefix("aia")
				.hostname(host)
				.port(8125)
				.build();
		}else {
			this.client = new NoOpStatsDClient();
		}
		this.client.gauge("datadog_setup_client.gauge", 1);
	}

	public void incrementCounter(String metricName) {
		this.client.incrementCounter(metricName, new String[] { "environment:dev" });
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("Initilizing DatadogClient");
		this.setup();
	}

}
