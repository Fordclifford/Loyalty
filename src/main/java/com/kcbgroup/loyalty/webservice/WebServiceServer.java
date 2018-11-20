package com.kcbgroup.loyalty.webservice;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.google.gson.JsonObject;
import com.kcbgroup.loyalty.model.Customer;
import com.kcbgroup.loyalty.model.Kcb;
import com.kcbgroup.loyalty.model.Merchant;
import com.kcbgroup.loyalty.model.RedemptionDetails;
import com.kcbgroup.loyalty.model.RedemptionSummary;
import com.kcbgroup.loyalty.repository.CustomerRepository;
import com.kcbgroup.loyalty.repository.KcbRepository;
import com.kcbgroup.loyalty.repository.MerchantRepository;
import com.kcbgroup.loyalty.repository.RedemptionDetailsRepository;
import com.kcbgroup.loyalty.repository.RedemptionSummaryRepository;
import com.kcbgroup.loyalty.webserviceserver.MemberStatementResponseServer;
import com.kcbgroup.loyalty.webserviceserver.MemberStatementServer;
import com.kcbgroup.loyalty.webserviceserver.ObjectFactory;
import com.kcbgroup.loyalty.webserviceserver.PointsEnquiryResponseServer;
import com.kcbgroup.loyalty.webserviceserver.PointsEnquiryServer;
import com.kcbgroup.loyalty.webserviceserver.PointsRedemptionResponseServer;
import com.kcbgroup.loyalty.webserviceserver.PointsRedemptionServer;
import com.kcbgroup.loyalty.webserviceserver.PointsTransferResponseServer;
import com.kcbgroup.loyalty.webserviceserver.PointsTransferServer;
import com.kcbgroup.loyalty.webserviceserver.ProfileCheckResponseServer;
import com.kcbgroup.loyalty.webserviceserver.ProfileCheckServer;

@Endpoint
public class WebServiceServer {
	
	private static final String NAMESPACE_URI = "http://loyalty.kcbgroup.com/webservice";
	private static final String INVALID = "Invalid request";
	private static final String ADDITIONAL_DETAILS1 = "AdditionalDetails1";
	private static final String ADDITIONAL_DETAILS2 = "AdditionalDetails2";
	private static final String ADDITIONAL_DETAILS3 = "AdditionalDetails3";
	private static final String ADDITIONAL_DETAILS4 = "AdditionalDetails4";
	private static final String STATUS = "Status";
	private static final String POINTS = "Points";
	private static final String RECEIVER_BALANCE = "ReceiverBalance";
	private static final String MOBILE_NUMBER = "MobileNumber";
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private KcbRepository kcbRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private RedemptionDetailsRepository redemptionDetailsRepository;
	
	@Autowired
	private RedemptionSummaryRepository redemptionSummaryRepository;
	
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="pointsEnquiryRequest")
	@ResponsePayload
	public PointsEnquiryResponseServer pointsEnquiry(@RequestPayload PointsEnquiryServer request) {
		PointsEnquiryResponseServer response = new PointsEnquiryResponseServer();
		ObjectFactory factory = new ObjectFactory();
		String customerCode = request.getPstrCIF().getValue();
		Merchant merchant = merchantRepository.findAll().get(0);
		Optional<Customer> customer = customerRepository.findByCustomerCode(customerCode);
		String message;
		
		Boolean idMatch = merchant.getmId() == request.getPintMerchantId();
		Boolean codeMatch = merchant.getCode().equals(request.getPstrMerchantCode().getValue());
		Boolean passwordMatch = merchant.getPassword().equals(request.getPstrMerchantPassword().getValue());
		
		if (!idMatch || !codeMatch || !passwordMatch || !customer.isPresent()) {
			message = INVALID;
			response.setPointsEnquiryResultServer(
				factory.createPointsEnquiryResponseServerPointsEnquiryResultServer(message)
			);
			return response;
		}
		else {
			Optional<RedemptionSummary> customerPoints = redemptionSummaryRepository.findByCustomerNo(customerCode);
			
			if (!customerPoints.isPresent()) {
				message = "Customer has no points";
				response.setPointsEnquiryResultServer(
					factory.createPointsEnquiryResponseServerPointsEnquiryResultServer(message)
				);
				return response;
			}
			else {
				Optional<BigDecimal> sumPoints = redemptionDetailsRepository.sumPoints(customerCode);
				Integer availablePoints = Integer.valueOf(customerPoints.get().getPointsBalance());
				Integer pointsAccrued;
				Integer pointsRedeemed;
				
				if (!sumPoints.isPresent()) {
					pointsAccrued = 0;
					pointsRedeemed = 0;
				}
				else {
					pointsAccrued = sumPoints.get().intValueExact();
					pointsRedeemed = pointsAccrued - availablePoints;
				}
				
				JsonObject json = new JsonObject();
				json.addProperty(ADDITIONAL_DETAILS1, "");
				json.addProperty(ADDITIONAL_DETAILS2, "");
				json.addProperty(ADDITIONAL_DETAILS3, "");
				json.addProperty(ADDITIONAL_DETAILS4, "");
				json.addProperty("AvailablePoints", availablePoints);
				json.addProperty("CIF", customerCode);
				json.addProperty("PointValue", "");
				json.addProperty("PointsAccrued", pointsAccrued);
				json.addProperty("PointsRedeemed", pointsRedeemed);
				json.addProperty(STATUS, false);
				
				message = json.toString();
				response.setPointsEnquiryResultServer(
					factory.createPointsEnquiryResponseServerPointsEnquiryResultServer(message)
				);
				return response;
			}
		}
	}
	
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="pointsRedemptionRequest")
	@ResponsePayload
	public PointsRedemptionResponseServer pointsRedemption(@RequestPayload PointsRedemptionServer request) {
		PointsRedemptionResponseServer response = new PointsRedemptionResponseServer();
		ObjectFactory factory = new ObjectFactory();
		String customerCode = request.getPstrCIF().getValue();
		Integer totalPoints = request.getPintTotalPoints();
		String ft = request.getPstrMobiTransactionId().getValue();
		Merchant merchant = merchantRepository.findAll().get(0);
		Optional<Customer> customer = customerRepository.findByCustomerCode(customerCode);
		Optional<Kcb> transaction = kcbRepository.findByTransRef(ft);
		String message;
		Boolean cifMatch;
		Boolean phoneMatch;
		Boolean narrationMatch;
		
		Boolean idMatch = merchant.getmId() == request.getPintMerchantId();
		Boolean codeMatch = merchant.getCode().equals(request.getPstrMerchantCode().getValue());
		Boolean passwordMatch = merchant.getPassword().equals(request.getPstrMerchantPassword().getValue());
		Boolean invalidPoints = totalPoints <= 0;
		Boolean nameMatch = merchant.getName().equals(request.getPstrMerchantName().getValue());
		
		if (!customer.isPresent()) {
			cifMatch = false;
			phoneMatch = false;
		}
		else {
			cifMatch = true;
			phoneMatch = customer.get().getCustMobile().equals(request.getPstrPhoneNumber().getValue());
		}
		
		if (!transaction.isPresent()) {
			narrationMatch = false;
		}
		else {
			narrationMatch = transaction.get().getNarration().equals(request.getPstrNarration().getValue());
		}
		
		if (!idMatch || !codeMatch || !passwordMatch || !cifMatch || !phoneMatch || invalidPoints || !nameMatch || 
				!narrationMatch) {
			message = INVALID;
			response.setPointsRedemptionResultServer(
				factory.createPointsRedemptionResponseServerPointsRedemptionResultServer(message)
			);
			return response;
		}
		else {
			Optional<RedemptionSummary> customerPoints = redemptionSummaryRepository.findByCustomerNo(customerCode);
			
			if (!customerPoints.isPresent()) {
				message = "Customer has no points";
				response.setPointsRedemptionResultServer(
					factory.createPointsRedemptionResponseServerPointsRedemptionResultServer(message)
				);
				return response;
			}
			else {
				Integer customerPointsBalance = Integer.valueOf(customerPoints.get().getPointsBalance());
				
				if (customerPointsBalance < totalPoints) {
					message = "Customer has insufficient points";
					response.setPointsRedemptionResultServer(
						factory.createPointsRedemptionResponseServerPointsRedemptionResultServer(message)
					);
					return response;
				}
				else {
					Integer customerDifference = customerPointsBalance - totalPoints;
					customerPoints.get().setPointsBalance(customerDifference.toString());
					redemptionSummaryRepository.save(customerPoints.get());
					Optional<BigDecimal> sumPoints = redemptionDetailsRepository.sumPoints(customerCode);
					Integer availablePoints = Integer.valueOf(customerPoints.get().getPointsBalance());
					Double pointsValue = 0.5;
					Integer pointsAccrued;
					pointsAccrued = (!sumPoints.isPresent() ? 0 : sumPoints.get().intValueExact());
					RedemptionDetails redemptionDetails = new RedemptionDetails();
					String rawDate = transaction.get().getIncomingDate().toString();
					String rawTime = transaction.get().getIncomingTime().toString();
					String rawDatetime = rawDate + rawTime;
					Date date;
					LocalDateTime localDatetime;
					
					try {
						date = new SimpleDateFormat("ddMMyyyyhhmmss").parse(rawDatetime);
						localDatetime = new Timestamp(date.getTime()).toLocalDateTime();
						redemptionDetails.setTransDate(localDatetime);
					} 
					catch (ParseException e) {
						e.getMessage();
					}
					
					redemptionDetails.setCustomerNo(customerCode);
					redemptionDetails.setCreatedDate(LocalDateTime.now(ZoneId.systemDefault()));
					redemptionDetails.setPoints(totalPoints);
					redemptionDetails.setPointsFlag("R");
					redemptionDetails.setFileId(null);
					redemptionDetails.setRecordNo(null);
					redemptionDetails.setTransRef(ft);
					redemptionDetails.setAmount(totalPoints * pointsValue);
					redemptionDetails.setNarration1(request.getPstrNarration().getValue());
					redemptionDetails.setNarration2("Redemption through Loyalty");
					redemptionDetails.setDescription(merchant.getName());
					redemptionDetails.setMobileNo(request.getPstrPhoneNumber().getValue());
					redemptionDetailsRepository.save(redemptionDetails);
					
					JsonObject json = new JsonObject();
					json.addProperty(ADDITIONAL_DETAILS1, ft);
					json.addProperty(ADDITIONAL_DETAILS2, "");
					json.addProperty(ADDITIONAL_DETAILS3, "");
					json.addProperty(ADDITIONAL_DETAILS4, "");
					json.addProperty("AvailablePoints", availablePoints);
					json.addProperty("CIF", customerCode);
					json.addProperty("PointValue", pointsValue.toString());
					json.addProperty("PointsAccrued", pointsAccrued);
					json.addProperty("PointsRedeemed", totalPoints);
					json.addProperty(STATUS, true);
					
					message = json.toString();
					response.setPointsRedemptionResultServer(
						factory.createPointsRedemptionResponseServerPointsRedemptionResultServer(message)
					);
					return response;
				}
			}
		}
	}
	
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="pointsTransferRequest")
	@ResponsePayload
	public PointsTransferResponseServer pointsTransfer(@RequestPayload PointsTransferServer request) {
		PointsTransferResponseServer response = new PointsTransferResponseServer();
		ObjectFactory factory = new ObjectFactory();
		String senderCif = request.getPstrSenderCIF().getValue();
		String senderNo = request.getPstrSenderPhoneNumber().getValue();
		String receiverCif = request.getPstrReceiverCIF().getValue();
		String receiverNo = request.getPstrReceiverPhoneNumber().getValue();
		Integer transferPoints = request.getPintTransferPoints();
		Merchant merchant = merchantRepository.findAll().get(0);
		Optional<Customer> sender = customerRepository.findByCustomerCode(senderCif);
		Optional<Customer> receiver = customerRepository.findByCustomerCode(receiverCif);
		String message;
		Boolean senderCifMatch;
		Boolean senderNoMatch;
		Boolean receiverCifMatch;
		Boolean receiverNoMatch;
		
		Boolean idMatch = merchant.getmId() == request.getPintMerchantId();
		Boolean codeMatch = merchant.getCode().equals(request.getPstrMerchantCode().getValue());
		Boolean passwordMatch = merchant.getPassword().equals(request.getPstrMerchantPassword().getValue());
		Boolean invalidPoints = transferPoints <= 0;
		
		if (!sender.isPresent()) {
			senderCifMatch = false;
			senderNoMatch = false;
		}
		else {
			senderCifMatch = true;
			senderNoMatch = sender.get().getCustMobile().equals(request.getPstrSenderPhoneNumber().getValue());
		}
		
		if (!receiver.isPresent()) {
			receiverCifMatch = false;
			receiverNoMatch = false;
		}
		else {
			receiverCifMatch = true;
			receiverNoMatch = receiver.get().getCustMobile().equals(request.getPstrReceiverPhoneNumber().getValue());
		}
		
		if (!idMatch || !codeMatch || !passwordMatch || !senderCifMatch || !senderNoMatch || !receiverCifMatch || 
				!receiverNoMatch || invalidPoints) {
			JsonObject json = new JsonObject();
			// Confirm
			json.addProperty("ErrorCode", "");
			// Confirm
			json.addProperty("ExceptionMessage", "");
			json.addProperty("Gateway", "");
			json.addProperty("IsSucessful", false);
			json.addProperty(POINTS, 0);
			json.addProperty(RECEIVER_BALANCE, 0);
			// Confirm
			json.addProperty("ReturnObject", "");
			json.addProperty("SenderBalance", 0);
			// Confirm
			json.addProperty("Type", "");
			
			message = json.toString();
			response.setPointsTransferResultServer(
				factory.createPointsTransferResponseServerPointsTransferResultServer(message)
			);
			return response;
		}
		else {
			Optional<RedemptionSummary> senderPoints = redemptionSummaryRepository.findByCustomerNo(senderCif);
			Optional<RedemptionSummary> receiverPoints = redemptionSummaryRepository.findByCustomerNo(receiverCif);
			
			if (!senderPoints.isPresent()) {
				message = "Sender has no points";
				response.setPointsTransferResultServer(
					factory.createPointsTransferResponseServerPointsTransferResultServer(message)
				);
				return response;
			}
			else {
				Integer senderPointsBalance = Integer.valueOf(senderPoints.get().getPointsBalance());
				Integer receiverPointsBalance = Integer.valueOf(
					receiverPoints.orElse(new RedemptionSummary()).getPointsBalance()
				);
				
				if (senderPointsBalance < transferPoints) {
					message = "Sender has insufficient points";
					response.setPointsTransferResultServer(
						factory.createPointsTransferResponseServerPointsTransferResultServer(message)
					);
					return response;
				}
				else {
					Integer senderDifference = senderPointsBalance - transferPoints;
					senderPoints.get().setPointsBalance(senderDifference.toString());
					redemptionSummaryRepository.save(senderPoints.get());
					
					if (!receiverPoints.isPresent()) {
						RedemptionSummary redemptionSummary = new RedemptionSummary();
						redemptionSummary.setCustomerNo(receiverNo);
						redemptionSummary.setPointsBalance(transferPoints.toString());
						redemptionSummaryRepository.save(redemptionSummary);
					}
					else {
						Integer receiverAddition = receiverPointsBalance + transferPoints;
						receiverPoints.get().setPointsBalance(receiverAddition.toString());
						redemptionSummaryRepository.save(receiverPoints.get());
					}
					
					RedemptionDetails redemptionDetailsSender = new RedemptionDetails();
					RedemptionDetails redemptionDetailsReceiver = new RedemptionDetails();
					
					redemptionDetailsSender.setCustomerNo(senderCif);
					redemptionDetailsSender.setCreatedDate(LocalDateTime.now(ZoneId.systemDefault()));
					redemptionDetailsSender.setPoints(transferPoints);
					redemptionDetailsSender.setPointsFlag("T");
					redemptionDetailsSender.setFileId(null);
					redemptionDetailsSender.setRecordNo(null);
					redemptionDetailsSender.setTransRef(null);
					redemptionDetailsSender.setAmount(0.0);
					redemptionDetailsSender.setNarration1("Transfer points (debit)");
					redemptionDetailsSender.setNarration2(
						"Points transfer between " + senderCif + " and " + receiverCif
					);
					redemptionDetailsSender.setDescription(
						"Transfer " + transferPoints + " from " + senderNo
					);
					redemptionDetailsSender.setMobileNo(senderNo);
					redemptionDetailsSender.setTransDate(null);
					redemptionDetailsRepository.save(redemptionDetailsSender);
					
					redemptionDetailsReceiver.setCustomerNo(receiverCif);
					redemptionDetailsReceiver.setCreatedDate(LocalDateTime.now(ZoneId.systemDefault()));
					redemptionDetailsReceiver.setPoints(transferPoints);
					redemptionDetailsReceiver.setPointsFlag("T");
					redemptionDetailsReceiver.setFileId(null);
					redemptionDetailsReceiver.setRecordNo(null);
					redemptionDetailsReceiver.setTransRef(null);
					redemptionDetailsReceiver.setAmount(0.0);
					redemptionDetailsReceiver.setNarration1("Transfer points (credit)");
					redemptionDetailsReceiver.setNarration2(
						"Points transfer between " + senderCif + " and " + receiverCif
					);
					redemptionDetailsReceiver.setDescription(
						"Transfer " + transferPoints + " to " + receiverNo
					);
					redemptionDetailsReceiver.setMobileNo(receiverNo);
					redemptionDetailsReceiver.setTransDate(null);
					redemptionDetailsRepository.save(redemptionDetailsReceiver);
					
					JsonObject json = new JsonObject();
					json.addProperty("ErrorCode", "");
					// Confirm
					json.addProperty("ExceptionMessage", "Success");
					json.addProperty("Gateway", "");
					json.addProperty("IsSucessful", true);
					json.addProperty(POINTS, transferPoints);
					if (!receiverPoints.isPresent()) {
						json.addProperty(RECEIVER_BALANCE, transferPoints);
					}
					else {
						json.addProperty(RECEIVER_BALANCE, receiverPointsBalance + transferPoints);
					}
					json.addProperty("ReturnObject", "");
					json.addProperty("SenderBalance", senderDifference);
					json.addProperty("Type", "");
					
					message = json.toString();
					response.setPointsTransferResultServer(
						factory.createPointsTransferResponseServerPointsTransferResultServer(message)
					);
					return response;
				}
			}
		}
	}
	
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="profileCheckRequest")
	@ResponsePayload
	public ProfileCheckResponseServer profileCheck(@RequestPayload ProfileCheckServer request) {
		ProfileCheckResponseServer response = new ProfileCheckResponseServer();
		ObjectFactory factory = new ObjectFactory();
		String custMobile = request.getPstrMobileNumber().getValue();
		Merchant merchant = merchantRepository.findAll().get(0);
		Optional<Customer> customer = customerRepository.findByCustMobile(custMobile);
		String message;
		Boolean phoneMatch;
		
		Boolean idMatch = merchant.getmId() == request.getPintMerchantId();
		Boolean codeMatch = merchant.getCode().equals(request.getPstrMerchantCode().getValue());
		Boolean passwordMatch = merchant.getPassword().equals(request.getPstrMerchantPassword().getValue());
		phoneMatch = !customer.isPresent();
		
		if (!idMatch || !codeMatch || !passwordMatch || !phoneMatch) {
			JsonObject json = new JsonObject();
			json.addProperty(ADDITIONAL_DETAILS1, "");
			json.addProperty(ADDITIONAL_DETAILS2, "");
			json.addProperty(ADDITIONAL_DETAILS3, "");
			json.addProperty(ADDITIONAL_DETAILS4, "");
			json.addProperty("CIF", "");
			json.addProperty(MOBILE_NUMBER, custMobile);
			json.addProperty(STATUS, false);
			
			message = json.toString();
			response.setProfileCheckResultServer(
				factory.createProfileCheckResponseServerProfileCheckResultServer(message)
			);
			return response;
		}
		else {
			JsonObject json = new JsonObject();
			json.addProperty(ADDITIONAL_DETAILS1, "");
			json.addProperty(ADDITIONAL_DETAILS2, "");
			json.addProperty(ADDITIONAL_DETAILS3, "");
			json.addProperty(ADDITIONAL_DETAILS4, "");
			json.addProperty("CIF", customer.orElse(new Customer()).getCustomerCode());
			json.addProperty(MOBILE_NUMBER, custMobile);
			json.addProperty(STATUS, true);
			
			message = json.toString();
			response.setProfileCheckResultServer(
				factory.createProfileCheckResponseServerProfileCheckResultServer(message)
			);
			return response;
		}
	}
	
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="memberStatementRequest")
	@ResponsePayload
	public MemberStatementResponseServer memberStatement(@RequestPayload MemberStatementServer request) {
		MemberStatementResponseServer response = new MemberStatementResponseServer();
		ObjectFactory factory = new ObjectFactory();
		String custMobile = request.getPstrMobileNumber().getValue();
		Merchant merchant = merchantRepository.findAll().get(0);
		Optional<Customer> customer = customerRepository.findByCustMobile(custMobile);
		String message;
		Boolean phoneMatch;
		
		Boolean idMatch = merchant.getmId() == request.getPintMerchantId();
		Boolean codeMatch = merchant.getCode().equals(request.getPstrMerchantCode().getValue());
		Boolean passwordMatch = merchant.getPassword().equals(request.getPstrMerchantPassword().getValue());
		phoneMatch = !customer.isPresent();
		
		if (!idMatch || !codeMatch || !passwordMatch || !phoneMatch) {
			message = INVALID;
			response.setMemberStatementResultServer(
				factory.createMemberStatementResponseServerMemberStatementResultServer(message)
			);
			return response;
		}
		else {
			String customerNo = customer.orElse(new Customer()).getCustomerCode();
			List<RedemptionDetails> redemptionDetails = redemptionDetailsRepository.findByCustomerNo(customerNo);
			
			redemptionDetails.forEach(redemptionDetail -> {
				JsonObject json = new JsonObject();
				json.addProperty(ADDITIONAL_DETAILS1, redemptionDetail.getNarration1());
				json.addProperty(ADDITIONAL_DETAILS2, redemptionDetail.getNarration2());
				json.addProperty(ADDITIONAL_DETAILS3, "");
				json.addProperty(ADDITIONAL_DETAILS4, "");
				json.addProperty("Amount", redemptionDetail.getAmount());
				json.addProperty("CIF", redemptionDetail.getCustomerNo());
				json.addProperty("Description", redemptionDetail.getDescription());
				json.addProperty(MOBILE_NUMBER, redemptionDetail.getMobileNo());
				json.addProperty(POINTS, redemptionDetail.getPoints());
				json.addProperty("TransactionDate", redemptionDetail.getTransDate().toString());
			});
			
			message = redemptionDetails.toString();
			response.setMemberStatementResultServer(
				factory.createMemberStatementResponseServerMemberStatementResultServer(message)
			);
			return response;
		}
	}

}
