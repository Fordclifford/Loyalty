package com.kcbgroup.loyalty.webservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.kcbgroup.loyalty.pointsredemptioninfiniaclient.PointsRedemption;
import com.kcbgroup.loyalty.pointsredemptioninfiniaclient.PointsRedemptionResponse;

public class PointsRedemptionInfiniaClient extends WebServiceGatewaySupport {
	
	@Value("${infinia.url}")
    private String infiniaUrl;
	
	public PointsRedemptionResponse pointsRedemption(PointsRedemption request) {
		String callback = "http://tempuri.org/IKCBPGManager/PointsRedemption";
		return (PointsRedemptionResponse) getWebServiceTemplate()
			.marshalSendAndReceive(infiniaUrl, request, new SoapActionCallback(callback));
	}

}
