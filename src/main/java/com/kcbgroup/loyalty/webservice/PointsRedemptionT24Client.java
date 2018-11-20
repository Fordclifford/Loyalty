package com.kcbgroup.loyalty.webservice;

/*import java.io.IOException;*/
import java.util.Random;

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;*/
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
/*import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;*/

/*import com.kcbgroup.loyalty.pointsredemptiont24client.Control;
import com.kcbgroup.loyalty.pointsredemptiont24client.DataInput;
import com.kcbgroup.loyalty.pointsredemptiont24client.DataOutput;
import com.kcbgroup.loyalty.pointsredemptiont24client.HeaderRequest;*/

public class PointsRedemptionT24Client extends WebServiceGatewaySupport {
	
	/*private static final String REQUESTER = "KCB";
	private static final String SOAP_ACTION = "/KCB.co.ke/Transfer/Service/Transfer/Update/2.0";*/

	/*@Autowired
	private T24PostingService t24PostingService;*/
	
	/*private final class PointsRedemptionT24Header implements WebServiceMessageCallback {
		
		private final String messageId;
		private final String requesterSystemCode;
		private final String soapAction;
		
		public PointsRedemptionT24Header(String messageId, String requesterSystemCode, String soapAction) {
			this.messageId = messageId;
			this.requesterSystemCode = requesterSystemCode;
			this.soapAction = soapAction;
		}
		
		@Override
		public void doWithMessage(WebServiceMessage message) throws IOException {
			SoapMessage soapMessage = (SoapMessage) message;
	        SoapHeader soapHeader = soapMessage.getSoapHeader();
	        HeaderRequest headerRequest = new HeaderRequest();
	        Control control = new Control();
	        
	        soapMessage.setSoapAction(soapAction);
	        control.setRequesterSystemCode(requesterSystemCode);
	        control.setMessageID(messageId);
	        headerRequest.setControl(control);
	        getMarshaller().marshal(headerRequest, soapHeader.getResult());
		}
		
	}*/
	
	/*public DataOutput dataOutput(DataInput dataInput) {
		String random = t24PostingService.getRandomNumber();
		return (DataOutput) getWebServiceTemplate()
			.marshalSendAndReceive(dataInput, new PointsRedemptionT24Header(random, REQUESTER, SOAP_ACTION));
	}*/
	
	public String randomNumber() {
		Long min = 111111111111L;
		Long max = 999999999998L;
		Long random = new Random().longs(min, max + 1).findFirst().getAsLong();
		return random.toString();
	}
	
}
