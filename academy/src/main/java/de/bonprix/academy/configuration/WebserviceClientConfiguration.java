package de.bonprix.academy.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.bonprix.global.masterdata.service.application.ApplicationService;
import de.bonprix.global.masterdata.service.applicationgroup.ApplicationGroupService;
import de.bonprix.global.masterdata.service.applicationtype.ApplicationTypeService;
import de.bonprix.jersey.ClientFactoryConfig;
import de.bonprix.jersey.JaxRsClientFactory;
import de.bonprix.jersey.ClientFactoryConfig.ClientSideLogLevel;

/**
 * Configures the client for calling the webservice clients
 * 
 * @author Niels Schelbach
 */
@Configuration
public class WebserviceClientConfiguration {

	@Value("${webservice.url.masterdata}")
	private String webserviceUrl;

	@Bean
	public ApplicationService applicationService(final JaxRsClientFactory jaxRsClientFactory) {
		final ClientFactoryConfig config = new ClientFactoryConfig();
		config.setClientSideLogging(ClientSideLogLevel.METHOD_TIME);

		return jaxRsClientFactory.createClient(this.webserviceUrl, ApplicationService.class, config);
	}

	@Bean
	public ApplicationTypeService applicationTypeService(final JaxRsClientFactory jaxRsClientFactory) {
		final ClientFactoryConfig config = new ClientFactoryConfig();
		config.setClientSideLogging(ClientSideLogLevel.METHOD_TIME);

		return jaxRsClientFactory.createClient(this.webserviceUrl, ApplicationTypeService.class, config);
	}

	@Bean
	public ApplicationGroupService applicationGroupService(final JaxRsClientFactory jaxRsClientFactory) {
		final ClientFactoryConfig config = new ClientFactoryConfig();
		config.setClientSideLogging(ClientSideLogLevel.METHOD_TIME);

		return jaxRsClientFactory.createClient(this.webserviceUrl, ApplicationGroupService.class, config);
	}

}
