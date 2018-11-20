package com.kcbgroup.loyalty.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.kcbgroup.loyalty.webservice.PointsRedemptionInfiniaClient;

@Configuration
public class PointsRedemptionInfiniaClientConfiguration {
	
	@Value("${infinia.url}")
    private String infiniaUrl;
	
	@Bean
	public Jaxb2Marshaller infiniaMarshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.kcbgroup.loyalty.pointsredemptioninfiniaclient");
		return marshaller;
	}

	@Bean
	public PointsRedemptionInfiniaClient pointsRedemptionInfiniaClient(Jaxb2Marshaller infiniaMarshaller) {
		PointsRedemptionInfiniaClient client = new PointsRedemptionInfiniaClient();
		client.setDefaultUri(infiniaUrl);
		client.setMarshaller(infiniaMarshaller);
		client.setUnmarshaller(infiniaMarshaller);
		return client;
	}

}
