package com.kcbgroup.loyalty.webservice;

/*import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;*/
import org.springframework.stereotype.Service;
/*import com.google.gson.JsonObject;
import com.kcbgroup.loyalty.model.DaoSummary;*/
/*import com.kcbgroup.loyalty.pointsredemptiont24client.DataInput;
import com.kcbgroup.loyalty.pointsredemptiont24client.DataOutput;
import com.kcbgroup.loyalty.pointsredemptiont24client.TransferRequestType;
import com.kcbgroup.loyalty.pointsredemptiont24client.TransferResponseType;
import com.kcbgroup.loyalty.pointsredemptiont24client.UpdateInputType;*/
/*import com.kcbgroup.loyalty.repository.DaoSummaryRepository;*/

@Service
public class T24PostingService {
	
	/*private static final Logger LOG = LoggerFactory.getLogger(T24PostingService.class);*/
	private String randomNumber;
	
	/*@Autowired
	private DaoSummaryRepository daoSummaryRepository;
	
	@Autowired
	private PointsRedemptionT24Client t24Client;*/
	
	/*public ResponseEntity<String> post75ToT24() {
		List<DaoSummary> daoSummaries = daoSummaryRepository.findByPosting75(0);
		String responseString;
		
		if (daoSummaries.isEmpty()) {
			responseString = "No branches found";
			LOG.error(responseString);
			return ResponseEntity.ok(responseString);
		}
		else {
			List<DaoSummary> t24s = new ArrayList<>();
			List<JsonObject> responses = new ArrayList<>();
			daoSummaries.forEach(t24Posting -> {
				String branchCode = t24Posting.getDao();
				String debitAccount = t24Posting.getProfitLossAc();
				String creditAccount = t24Posting.getSuspenseAc();
				String paymentDetails = "Award Points " + branchCode;
				Integer amount = t24Posting.getAmount2();
				Integer points = t24Posting.getTotalPoints();
				String code = t24Client.randomNumber();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss");
				String dateTime = LocalDateTime.now().format(formatter);
				setRandomNumber(code);
				
				DataInput dataInput = new DataInput();
				UpdateInputType updateInput = new UpdateInputType();
				TransferRequestType request = new TransferRequestType();
				com.kcbgroup.loyalty.pointsredemptiont24client.ObjectFactory factory = 
						new com.kcbgroup.loyalty.pointsredemptiont24client.ObjectFactory();
				
				request.setBankCode("KCB");
				request.setMobileNumber("");
				request.setDebitAccount(factory.createTransferRequestTypeDebitAccount(debitAccount));
				request.setCreditAccount(factory.createTransferRequestTypeCreditAccount(creditAccount));
				request.setPaymentDetails(factory.createTransferRequestTypePaymentDetails(paymentDetails));
				request.setAmount(factory.createTransferRequestTypeAmount(amount.toString()));
				request.setCurrency("KES");
				request.setCompanyCode("KE0010001");
				request.setBranchCode(factory.createTransferRequestTypeBranchCode(branchCode));
				request.setProfitCenter(factory.createTransferRequestTypeProfitCenter(branchCode));
				request.setChannel(factory.createTransferRequestTypeChannel("USSDCustomer"));
				request.setCountry(factory.createTransferRequestTypeCountry("404"));
				request.setRedeemedPoints(factory.createTransferRequestTypeRedeemedPoints(points.toString()));
				request.setTotalRedeemedPoints(BigDecimal.valueOf(points));
				request.setAvailablePoints(BigDecimal.valueOf(0));
				request.setTransferType(factory.createTransferRequestTypeTransferType("ACZD"));
				request.setValidationCode(factory.createTransferRequestTypeValidationCode(code));
				request.setApprovalCode(factory.createTransferRequestTypeApprovalCode(code));
				request.setStatus(factory.createTransferRequestTypeStatus("SUCCESS"));
				request.setCustomerNames(factory.createTransferRequestTypeCustomerNames(""));
				request.setCustomerId(factory.createTransferRequestTypeCustomerId(""));
				request.setDateTime(factory.createTransferRequestTypeDateTime(dateTime));
				request.setExtraData(factory.createTransferRequestTypeExtraData("N/A"));
				updateInput.setTransferRequest(request);
				dataInput.setUpdateInput(updateInput);
				
				DataOutput response = t24Client.dataOutput(dataInput);
				TransferResponseType transferResponse = response.getUpdateOutput().getTransferResponse();
				
				DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime postingDate = LocalDateTime.parse(transferResponse.getResponseTime(), formatter2);
				t24Posting.setPostingDate(postingDate);
				t24Posting.setT24Ref(transferResponse.getReference());
				t24Posting.setMessageId(transferResponse.getRequestRef());
				t24Posting.setPosting75(1);
				t24Posting.setDescription(transferResponse.getStatusDescription());
				t24Posting.setReason(transferResponse.getReason());
				t24s.add(t24Posting);
				
				JsonObject t24Object = new JsonObject();
				t24Object.addProperty("reference", transferResponse.getReference());
				t24Object.addProperty("responseTime", transferResponse.getResponseTime());
				t24Object.addProperty("status", transferResponse.getStatus());
				t24Object.addProperty("statusCode", transferResponse.getStatusCode());
				t24Object.addProperty("statusDescription", transferResponse.getStatusDescription());
				t24Object.addProperty("reason", transferResponse.getReason());
				t24Object.addProperty("requestRef", transferResponse.getRequestRef());
				responses.add(t24Object);
			});
			daoSummaryRepository.saveAll(t24s);
			responses.forEach(response -> LOG.info(response.toString()));
			return ResponseEntity.ok(responses.toString());
		}
	}*/
	
	/*public ResponseEntity<String> post25ToT24() {
		List<DaoSummary> daoSummaries = daoSummaryRepository.findByPosting25(0);
		String responseString;
		
		if (daoSummaries.isEmpty()) {
			responseString = "No branches found";
			LOG.error(responseString);
			return ResponseEntity.ok(responseString);
		}
		else {
			List<DaoSummary> t24s = new ArrayList<>();
			List<JsonObject> responses = new ArrayList<>();
			daoSummaries.forEach(t24Posting -> {
				String branchCode = t24Posting.getDao();
				String debitAccount = t24Posting.getProfitLossAc();
				String creditAccount = t24Posting.getSuspenseAc();
				String paymentDetails = "Award Points " + branchCode;
				Integer amount = t24Posting.getAmount2();
				Integer points = t24Posting.getTotalPoints();
				String code = t24Client.randomNumber();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss");
				String dateTime = LocalDateTime.now().format(formatter);
				setRandomNumber(code);
				
				DataInput dataInput = new DataInput();
				UpdateInputType updateInput = new UpdateInputType();
				TransferRequestType request = new TransferRequestType();
				com.kcbgroup.loyalty.pointsredemptiont24client.ObjectFactory factory = 
						new com.kcbgroup.loyalty.pointsredemptiont24client.ObjectFactory();
				
				request.setBankCode("KCB");
				request.setMobileNumber("");
				request.setDebitAccount(factory.createTransferRequestTypeDebitAccount(debitAccount));
				request.setCreditAccount(factory.createTransferRequestTypeCreditAccount(creditAccount));
				request.setPaymentDetails(factory.createTransferRequestTypePaymentDetails(paymentDetails));
				request.setAmount(factory.createTransferRequestTypeAmount(amount.toString()));
				request.setCurrency("KES");
				request.setCompanyCode("KE0010001");
				request.setBranchCode(factory.createTransferRequestTypeBranchCode(branchCode));
				request.setProfitCenter(factory.createTransferRequestTypeProfitCenter(branchCode));
				request.setChannel(factory.createTransferRequestTypeChannel("USSDCustomer"));
				request.setCountry(factory.createTransferRequestTypeCountry("404"));
				request.setRedeemedPoints(factory.createTransferRequestTypeRedeemedPoints(points.toString()));
				request.setTotalRedeemedPoints(BigDecimal.valueOf(points));
				request.setAvailablePoints(BigDecimal.valueOf(0));
				request.setTransferType(factory.createTransferRequestTypeTransferType("ACZD"));
				request.setValidationCode(factory.createTransferRequestTypeValidationCode(code));
				request.setApprovalCode(factory.createTransferRequestTypeApprovalCode(code));
				request.setStatus(factory.createTransferRequestTypeStatus("SUCCESS"));
				request.setCustomerNames(factory.createTransferRequestTypeCustomerNames(""));
				request.setCustomerId(factory.createTransferRequestTypeCustomerId(""));
				request.setDateTime(factory.createTransferRequestTypeDateTime(dateTime));
				request.setExtraData(factory.createTransferRequestTypeExtraData("N/A"));
				updateInput.setTransferRequest(request);
				dataInput.setUpdateInput(updateInput);
				
				DataOutput response = t24Client.dataOutput(dataInput);
				TransferResponseType transferResponse = response.getUpdateOutput().getTransferResponse();
				
				DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime postingDate = LocalDateTime.parse(transferResponse.getResponseTime(), formatter2);
				t24Posting.setPostingDate(postingDate);
				t24Posting.setT24Ref(transferResponse.getReference());
				t24Posting.setMessageId(transferResponse.getRequestRef());
				t24Posting.setPosting25(1);
				t24Posting.setDescription(transferResponse.getStatusDescription());
				t24Posting.setReason(transferResponse.getReason());
				t24s.add(t24Posting);
				
				JsonObject t24Object = new JsonObject();
				t24Object.addProperty("reference", transferResponse.getReference());
				t24Object.addProperty("responseTime", transferResponse.getResponseTime());
				t24Object.addProperty("status", transferResponse.getStatus());
				t24Object.addProperty("statusCode", transferResponse.getStatusCode());
				t24Object.addProperty("statusDescription", transferResponse.getStatusDescription());
				t24Object.addProperty("reason", transferResponse.getReason());
				t24Object.addProperty("requestRef", transferResponse.getRequestRef());
				responses.add(t24Object);
			});
			daoSummaryRepository.saveAll(t24s);
			responses.forEach(response -> LOG.info(response.toString()));
			return ResponseEntity.ok(responses.toString());
		}
	}*/
	
	public String getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(String randomNumber) {
		this.randomNumber = randomNumber;
	}

}
