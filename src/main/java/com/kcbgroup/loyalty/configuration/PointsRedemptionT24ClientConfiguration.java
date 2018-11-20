package com.kcbgroup.loyalty.configuration;

/*import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;*/
import org.springframework.context.annotation.Configuration;
/*import org.springframework.oxm.jaxb.Jaxb2Marshaller;*/

/*import com.kcbgroup.loyalty.webservice.PointsRedemptionT24Client;*/

@Configuration
public class PointsRedemptionT24ClientConfiguration {
	
	/*@Value("${t24.url}")
    private String t24Url;*/
	
	/*@Bean
	public Jaxb2Marshaller t24Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.kcbgroup.loyalty.pointsredemptiont24client");
		return marshaller;
	}*/

	/*@Bean
	public PointsRedemptionT24Client pointsRedemptionT24Client(Jaxb2Marshaller t24Marshaller) {
		PointsRedemptionT24Client client = new PointsRedemptionT24Client();
		client.setDefaultUri(t24Url);
		client.setMarshaller(t24Marshaller);
		client.setUnmarshaller(t24Marshaller);
		return client;
	}*/

}
