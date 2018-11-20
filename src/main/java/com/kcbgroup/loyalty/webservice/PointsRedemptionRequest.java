package com.kcbgroup.loyalty.webservice;

/*import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;*/
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
/*import com.google.common.base.Splitter;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kcbgroup.loyalty.model.Currency;*/
import com.kcbgroup.loyalty.model.Customer;
/*import com.kcbgroup.loyalty.model.Dao;*/
import com.kcbgroup.loyalty.model.Merchant;
/*import com.kcbgroup.loyalty.model.RedemptionDetails;*/
import com.kcbgroup.loyalty.model.RedemptionSummary;
import com.kcbgroup.loyalty.pointsredemptioninfiniaclient.PointsRedemption;
import com.kcbgroup.loyalty.pointsredemptioninfiniaclient.PointsRedemptionResponse;
/*import com.kcbgroup.loyalty.pointsredemptiont24client.DataInput;
import com.kcbgroup.loyalty.pointsredemptiont24client.DataOutput;
import com.kcbgroup.loyalty.pointsredemptiont24client.TransferRequestType;
import com.kcbgroup.loyalty.pointsredemptiont24client.TransferResponseType;
import com.kcbgroup.loyalty.pointsredemptiont24client.UpdateInputType;*/
/*import com.kcbgroup.loyalty.repository.CurrencyRepository;*/
import com.kcbgroup.loyalty.repository.CustomerRepository;
/*import com.kcbgroup.loyalty.repository.DaoRepository;*/
import com.kcbgroup.loyalty.repository.MerchantRepository;
/*import com.kcbgroup.loyalty.repository.RedemptionDetailsRepository;*/
import com.kcbgroup.loyalty.repository.RedemptionSummaryRepository;

@Component
public class PointsRedemptionRequest {
	
	private static final Logger LOG = LoggerFactory.getLogger(PointsRedemptionRequest.class);
	
	/*@Autowired
	private CurrencyRepository currencyRepository;*/
	
	@Autowired
	private CustomerRepository customerRepository;
	
	/*@Autowired
	private DaoRepository daoRepository;*/
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private PointsRedemptionInfiniaClient infiniaClient;
	
	/*@Autowired
	private PointsRedemptionT24Client t24Client;
	
	@Autowired
	private RedemptionDetailsRepository redemptionDetailsRepository;*/
	
	@Autowired
	private RedemptionSummaryRepository redemptionSummaryRepository;
	
	public ResponseEntity<String> pointsRedemption() {
		String customerCode = "6030406";
		Optional<Customer> customer = customerRepository.findByCustomerCode(customerCode);
		Optional<RedemptionSummary> redemptionSummary = redemptionSummaryRepository.findByCustomerNo(customerCode);
		String responseString;
		
		if (customer.isPresent()) {
			if (redemptionSummary.isPresent()) {
				Integer redeemPoints = 10;
				Integer availablePoints = Integer.valueOf(redemptionSummary.get().getPointsBalance());
				
				if (availablePoints < redeemPoints) {
					responseString = "Customer has insufficient points";
					LOG.error(responseString);
					return ResponseEntity.ok(responseString);
				}
				else {
					Merchant merchant = merchantRepository.findAll().get(0);
					Integer merchantId = merchant.getmId();
			        String merchantCode = merchant.getCode();
			        String merchantPassword = merchant.getPassword();
			        String customerPhone = "254722888269";
			        String merchantName = merchant.getName();
			        PointsRedemption request = new PointsRedemption();
			        com.kcbgroup.loyalty.pointsredemptioninfiniaclient.ObjectFactory factory = 
			        		new com.kcbgroup.loyalty.pointsredemptioninfiniaclient.ObjectFactory();
					
			        request.setPintMerchantId(merchantId);
			        request.setPstrMerchantCode(factory.createPointsRedemptionPstrMerchantCode(merchantCode));
			        request.setPstrMerchantPassword(factory.createPointsRedemptionPstrMerchantPassword(merchantPassword));
			        request.setPstrCIF(factory.createPointsRedemptionPstrCIF(customerCode));
			        request.setPstrPhoneNumber(factory.createPointsRedemptionPstrPhoneNumber(customerPhone));
			        request.setPintTotalPoints(redeemPoints);
			        request.setPstrMerchantName(factory.createPointsRedemptionPstrMerchantName(merchantName));
					
					PointsRedemptionResponse response = infiniaClient.pointsRedemption(request);
					responseString = response.getPointsRedemptionResult().getValue();
					/*pointsRedemptionT24Dao(responseString);
					pointsRedemptionT24Holding(responseString);*/
					LOG.info(responseString);
					return ResponseEntity.ok(responseString);
				}
			}
			else {
				responseString = "Customer has no points";
				LOG.error(responseString);
				return ResponseEntity.ok(responseString);
			}
		}
		else {
			responseString = "Customer does not exist";
			LOG.error(responseString);
			return ResponseEntity.ok(responseString);
		}
	}
	
	/*private ResponseEntity<String> pointsRedemptionT24Dao(String infiniaResponse) {
		JsonObject infiniaObject = new JsonParser().parse(infiniaResponse).getAsJsonObject();
		Integer points = infiniaObject.get("PointsRedeemed").getAsInt();
		Double pointsValue = infiniaObject.get("PointValue").getAsDouble();
		String abbreviation = "KES";
		String customerCode = infiniaObject.get("CIF").getAsString();
		Customer customer = customerRepository.findByCustomerCode(customerCode).orElse(new Customer());
		Optional<Currency> currency = currencyRepository.findByAbbreviation(abbreviation);
		String branchCode = customer.getAccountOfficer();
		Optional<Dao> dao = daoRepository.findByBranchCode(branchCode);
		List<String> names = Splitter.on(' ').trimResults().omitEmptyStrings().splitToList(customer.getName());
		RedemptionSummary redemptionSummary = 
				redemptionSummaryRepository.findByCustomerNo(customerCode).orElse(new RedemptionSummary());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String debitAccount;
		String creditAccount;
		Integer country;
		
		DataInput dataInput = new DataInput();
		UpdateInputType updateInput = new UpdateInputType();
		TransferRequestType request = new TransferRequestType();
		com.kcbgroup.loyalty.pointsredemptiont24client.ObjectFactory factory = 
				new com.kcbgroup.loyalty.pointsredemptiont24client.ObjectFactory();
		
		if (dao.isPresent()) {
			debitAccount = dao.get().getProfitLossAc();
			creditAccount = dao.get().getLoyaltyAc();
		}
		else {
			debitAccount = null;
			creditAccount = null;
		}
		
		if (currency.isPresent()) {
			country = currency.get().getCodeNumber();
		}
		else {
			country = null;
		}
		
		String bankCode = "KCB";
		String mobileNumber = customer.getCustMobile();
		String paymentDetails = "Redeem Cash " + customerCode;
		Double amount = points * pointsValue * 0.75;
		String companyCode = "KE0010001";
		String profitCenter = branchCode;
		String channel = "USSDCustomer";
		Integer redeemedPoints = points;
		Integer totalRedeemedPoints = redeemedPoints;
		Integer availablePoints = Integer.valueOf(redemptionSummary.getPointsBalance());
		String transferType = "ACZD";
		String validationCode = t24Client.randomNumber();
		String approvalCode = validationCode;
		String status = "SUCCESS";
		String dateTime = LocalDateTime.now().format(formatter);
		String extraData = "N/A";
		
		request.setBankCode(bankCode);
		request.setMobileNumber(mobileNumber);
		request.setDebitAccount(factory.createTransferRequestTypeDebitAccount(debitAccount));
		request.setCreditAccount(factory.createTransferRequestTypeCreditAccount(creditAccount));
		request.setPaymentDetails(factory.createTransferRequestTypePaymentDetails(paymentDetails));
		request.setAmount(factory.createTransferRequestTypeAmount(amount.toString()));
		request.setCurrency(abbreviation);
		request.setCompanyCode(companyCode);
		request.setBranchCode(factory.createTransferRequestTypeBranchCode(branchCode));
		request.setProfitCenter(factory.createTransferRequestTypeProfitCenter(profitCenter));
		request.setChannel(factory.createTransferRequestTypeChannel(channel));
		request.setCountry(factory.createTransferRequestTypeCountry(String.valueOf(country)));
		request.setRedeemedPoints(factory.createTransferRequestTypeRedeemedPoints(redeemedPoints.toString()));
		request.setTotalRedeemedPoints(BigDecimal.valueOf(totalRedeemedPoints));
		request.setAvailablePoints(BigDecimal.valueOf(availablePoints));
		request.setTransferType(factory.createTransferRequestTypeTransferType(transferType));
		request.setValidationCode(factory.createTransferRequestTypeValidationCode(validationCode));
		request.setApprovalCode(factory.createTransferRequestTypeApprovalCode(approvalCode));
		request.setStatus(factory.createTransferRequestTypeStatus(status));
		request.setCustomerNames(factory.createTransferRequestTypeCustomerNames(names.get(0)));
		request.setCustomerId(factory.createTransferRequestTypeCustomerId(customerCode));
		request.setDateTime(factory.createTransferRequestTypeDateTime(dateTime));
		request.setExtraData(factory.createTransferRequestTypeExtraData(extraData));
		updateInput.setTransferRequest(request);
		dataInput.setUpdateInput(updateInput);
		
		DataOutput response = t24Client.dataOutput(dataInput);
		TransferResponseType transferResponse = response.getUpdateOutput().getTransferResponse();
		
		if (transferResponse.getStatus().equals("00")) {
			Integer difference = availablePoints - redeemedPoints;
			redemptionSummary.setPointsBalance(difference.toString());
			redemptionSummaryRepository.save(redemptionSummary);
			
			RedemptionDetails redemptionDetails = new RedemptionDetails();
			redemptionDetails.setCustomerNo(customerCode);
			redemptionDetails.setCreatedDate(LocalDateTime.parse(dateTime, formatter2));
			redemptionDetails.setPoints(points);
			redemptionDetails.setPointsFlag("R");
			redemptionDetails.setTransRef(transferResponse.getReference());
			redemptionDetails.setAmount(amount);
			redemptionDetails.setNarration1(transferResponse.getRequestRef() + " - " + transferResponse.getStatus());
			redemptionDetails.setNarration2(transferResponse.getStatusCode() + " - " + transferResponse.getStatus());
			redemptionDetails.setDescription(transferResponse.getStatusDescription());
			redemptionDetails.setTransDate(LocalDateTime.parse(transferResponse.getResponseTime(), formatter2));
			redemptionDetails.setMobileNo(customer.getCustMobile());
			redemptionDetailsRepository.save(redemptionDetails);
		}
		
		JsonObject t24Object = new JsonObject();
		t24Object.addProperty("reference", transferResponse.getReference());
		t24Object.addProperty("responseTime", transferResponse.getResponseTime());
		t24Object.addProperty("status", transferResponse.getStatus());
		t24Object.addProperty("statusCode", transferResponse.getStatusCode());
		t24Object.addProperty("statusDescription", transferResponse.getStatusDescription());
		t24Object.addProperty("reason", transferResponse.getReason());
		t24Object.addProperty("requestRef", transferResponse.getRequestRef());
		String t24String = t24Object.toString();
		LOG.info(t24String);
		return ResponseEntity.ok(t24Object.toString());
	}*/
	
	/*private ResponseEntity<String> pointsRedemptionT24Holding(String infiniaResponse) {
		JsonObject infiniaObject = new JsonParser().parse(infiniaResponse).getAsJsonObject();
		Integer points = infiniaObject.get("PointsRedeemed").getAsInt();
		Double pointsValue = infiniaObject.get("PointValue").getAsDouble();
		String abbreviation = "KES";
		String customerCode = infiniaObject.get("CIF").getAsString();
		Customer customer = customerRepository.findByCustomerCode(customerCode).orElse(new Customer());
		Optional<Currency> currency = currencyRepository.findByAbbreviation(abbreviation);
		String branchCode = customer.getAccountOfficer();
		Optional<Dao> dao = daoRepository.findByBranchCode(branchCode);
		List<String> names = Splitter.on(' ').trimResults().omitEmptyStrings().splitToList(customer.getName());
		RedemptionSummary redemptionSummary = 
				redemptionSummaryRepository.findByCustomerNo(customerCode).orElse(new RedemptionSummary());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String debitAccount;
		String creditAccount;
		Integer country;
		
		DataInput dataInput = new DataInput();
		UpdateInputType updateInput = new UpdateInputType();
		TransferRequestType request = new TransferRequestType();
		com.kcbgroup.loyalty.pointsredemptiont24client.ObjectFactory factory = 
				new com.kcbgroup.loyalty.pointsredemptiont24client.ObjectFactory();
		
		if (dao.isPresent()) {
			debitAccount = dao.get().getProfitLossAc();
			creditAccount = dao.get().getLoyaltyAc();
		}
		else {
			debitAccount = null;
			creditAccount = null;
		}
		
		if (currency.isPresent()) {
			country = currency.get().getCodeNumber();
		}
		else {
			country = null;
		}
		
		String bankCode = "KCB";
		String mobileNumber = customer.getCustMobile();
		String paymentDetails = "Redeem Cash " + customerCode;
		Double amount = points * pointsValue * 0.25;
		String companyCode = "KE0010001";
		String profitCenter = branchCode;
		String channel = "USSDCustomer";
		Integer redeemedPoints = points;
		Integer totalRedeemedPoints = redeemedPoints;
		Integer availablePoints = Integer.valueOf(redemptionSummary.getPointsBalance());
		String transferType = "ACZD";
		String validationCode = t24Client.randomNumber();
		String approvalCode = validationCode;
		String status = "SUCCESS";
		String dateTime = LocalDateTime.now().format(formatter);
		String extraData = "N/A";
		
		request.setBankCode(bankCode);
		request.setMobileNumber(mobileNumber);
		request.setDebitAccount(factory.createTransferRequestTypeDebitAccount(debitAccount));
		request.setCreditAccount(factory.createTransferRequestTypeCreditAccount(creditAccount));
		request.setPaymentDetails(factory.createTransferRequestTypePaymentDetails(paymentDetails));
		request.setAmount(factory.createTransferRequestTypeAmount(amount.toString()));
		request.setCurrency(abbreviation);
		request.setCompanyCode(companyCode);
		request.setBranchCode(factory.createTransferRequestTypeBranchCode(branchCode));
		request.setProfitCenter(factory.createTransferRequestTypeProfitCenter(profitCenter));
		request.setChannel(factory.createTransferRequestTypeChannel(channel));
		request.setCountry(factory.createTransferRequestTypeCountry(String.valueOf(country)));
		request.setRedeemedPoints(factory.createTransferRequestTypeRedeemedPoints(redeemedPoints.toString()));
		request.setTotalRedeemedPoints(BigDecimal.valueOf(totalRedeemedPoints));
		request.setAvailablePoints(BigDecimal.valueOf(availablePoints));
		request.setTransferType(factory.createTransferRequestTypeTransferType(transferType));
		request.setValidationCode(factory.createTransferRequestTypeValidationCode(validationCode));
		request.setApprovalCode(factory.createTransferRequestTypeApprovalCode(approvalCode));
		request.setStatus(factory.createTransferRequestTypeStatus(status));
		request.setCustomerNames(factory.createTransferRequestTypeCustomerNames(names.get(0)));
		request.setCustomerId(factory.createTransferRequestTypeCustomerId(customerCode));
		request.setDateTime(factory.createTransferRequestTypeDateTime(dateTime));
		request.setExtraData(factory.createTransferRequestTypeExtraData(extraData));
		updateInput.setTransferRequest(request);
		dataInput.setUpdateInput(updateInput);
		
		DataOutput response = t24Client.dataOutput(dataInput);
		TransferResponseType transferResponse = response.getUpdateOutput().getTransferResponse();
		
		if (transferResponse.getStatus().equals("00")) {
			Integer difference = availablePoints - redeemedPoints;
			redemptionSummary.setPointsBalance(difference.toString());
			redemptionSummaryRepository.save(redemptionSummary);
			
			RedemptionDetails redemptionDetails = new RedemptionDetails();
			redemptionDetails.setCustomerNo(customerCode);
			redemptionDetails.setCreatedDate(LocalDateTime.parse(dateTime, formatter2));
			redemptionDetails.setPoints(points);
			redemptionDetails.setPointsFlag("R");
			redemptionDetails.setTransRef(transferResponse.getReference());
			redemptionDetails.setAmount(amount);
			redemptionDetails.setNarration1(transferResponse.getRequestRef() + " - " + transferResponse.getStatus());
			redemptionDetails.setNarration2(transferResponse.getStatusCode() + " - " + transferResponse.getStatus());
			redemptionDetails.setDescription(transferResponse.getStatusDescription());
			redemptionDetails.setTransDate(LocalDateTime.parse(transferResponse.getResponseTime(), formatter2));
			redemptionDetails.setMobileNo(customer.getCustMobile());
			redemptionDetailsRepository.save(redemptionDetails);
		}
		
		JsonObject t24Object = new JsonObject();
		t24Object.addProperty("reference", transferResponse.getReference());
		t24Object.addProperty("responseTime", transferResponse.getResponseTime());
		t24Object.addProperty("status", transferResponse.getStatus());
		t24Object.addProperty("statusCode", transferResponse.getStatusCode());
		t24Object.addProperty("statusDescription", transferResponse.getStatusDescription());
		t24Object.addProperty("reason", transferResponse.getReason());
		t24Object.addProperty("requestRef", transferResponse.getRequestRef());
		String t24String = t24Object.toString();
		LOG.info(t24String);
		return ResponseEntity.ok(t24Object.toString());
	}*/

}
