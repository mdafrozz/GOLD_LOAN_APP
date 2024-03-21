package com.bridgelabz.bookstoreapp.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.ApplicationDTO;
import com.bridgelabz.bookstoreapp.dto.ItemDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.ApplicationModel;
import com.bridgelabz.bookstoreapp.model.CustomerModel;
import com.bridgelabz.bookstoreapp.model.ItemModel;
import com.bridgelabz.bookstoreapp.repository.ApplicationRepository;
import com.bridgelabz.bookstoreapp.repository.CustomerRepository;
import com.bridgelabz.bookstoreapp.repository.ItemRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;

@Service
public class ApplicationService implements IApplicationService {

	@Autowired ApplicationRepository applicationRepository;
	@Autowired CustomerRepository customerRepository;
	@Autowired ICustomerService iCustomerService;
	@Autowired ItemRepository itemRepository;
	@Autowired ItemService itemService;
	
	// Create Application details
	@Override
	public int createApplication(ApplicationDTO applicationDTO) {
		Optional<CustomerModel> customerModel = customerRepository.findById(applicationDTO.getCustId());
		int applicationId = 0;
		if(customerModel.isPresent()) {
			ApplicationModel applicationModel = new ApplicationModel(applicationDTO);
			applicationModel.setCustomer(customerModel.get());
			applicationModel.setLoanStatus("active");
	        List<ItemModel> itemList = applicationModel.getItemList();
	        for(int i=0; i < itemList.size(); i++) {
	        	itemService.deleteItem(itemList.get(i).getItemId());
			}
			applicationRepository.save(applicationModel);
			applicationId = applicationModel.getApplicationId();
		}
		else {
			applicationId = 0;
		}
		return applicationId;
	}

	// Get all User Details list
	@Override
	public List<ApplicationModel> getAllApplicationData() {
		List<ApplicationModel> applicationModel = applicationRepository.findAll();
		if (applicationModel.isEmpty()) {
			throw new BookStoreException("No Application Created yet!!!!");
		} else
			return applicationModel;
	}

	// Get the user data by id
	@Override
	public ApplicationModel getApplicationDataById(int id) {
		ApplicationModel applicationModel = applicationRepository.findById(id).orElse(null);
	    if (applicationModel != null) {
			return applicationModel;
		} else
			throw new BookStoreException("ApplicationId: " + id + ", does not exist");
	}
	
	// Get all User Details list
	@Override
	public List<ApplicationModel> getAllOverDueLoans() {
		List<ApplicationModel> applicationModel = applicationRepository.findOverDueLoans("overdue");
		if (applicationModel.isEmpty()) {
			throw new BookStoreException("No OverDue Loans found!!!!");
		} else
			return applicationModel;
	}
	
	// Get all User Details list
	@Override
	public List<ApplicationModel> getAllClosedLoans() {
		List<ApplicationModel> applicationModel = applicationRepository.findClosedLoans("closed");
		if (applicationModel.isEmpty()) {
			throw new BookStoreException("No Closed Loans found!!!!");
		} else
			return applicationModel;
	}
	
	// Get all User Details list
	@Override
	public List<ApplicationModel> getActiveLoans() {
		List<ApplicationModel> applicationModel = applicationRepository.findClosedLoans("active");
		if (applicationModel.isEmpty()) {
			throw new BookStoreException("No Active Loans found!!!!");
		} else
			return applicationModel;
	}
	
	// Get customer Data by Name	
	@Override
	public List<ApplicationModel> searchbyName(String name) {
		List<CustomerModel> customerModel = iCustomerService.searchbyName(name);
		List<ApplicationModel> applicationModel = new ArrayList<ApplicationModel>();
		System.out.println(customerModel);
		if (customerModel != null) {
			for(int i=0; i< customerModel.size(); i++) {
				List<ApplicationModel> applicationModel1  = applicationRepository.findByCustomerId(customerModel.get(i).getCustomerId());
				applicationModel.addAll(applicationModel1);
			}
			return applicationModel;

		} else
			throw new BookStoreException("Name: " + name + " is not available");
	}
	
	// Create Application details
	@Override
	public int closeLoan(ApplicationDTO applicationDTO, int loanId) {
		int applicationId = 0;
		ApplicationModel applicationModel = applicationRepository.findById(loanId).orElse(null);
		if(applicationModel != null) {
			applicationModel.setClosingDate(applicationDTO.getClosingDate());
			applicationModel.setClosingPhoto(Base64.getDecoder().decode(applicationDTO.getClosingPhoto()));
			applicationModel.setLoanStatus("closed");
			applicationRepository.save(applicationModel);
			applicationId = applicationModel.getApplicationId();
		}
		else {
			applicationId = 0;
		}
		return applicationId;
	}
		
	String html = "<div style=\"font-family: Helvetica, sans-serif;\">\n"
			+ "  <p style=\"text-align: center; margin: auto; line-height: 22px; font-size: 14px\"><strong>SKS MONEY LENDERS AND PAN BROKERS</strong>&nbsp;\n"
			+ "  (+91-8660128782, +91-8553304009)<br>\n"
			+ "        no. 9/5/450/133, Kinnal road, Near railway gate, Kalyan nagar\n"
			+ "    Koppal - 583231<br><br>\n"
			+ "  </p>\n"
			+ "  <p style=\"text-align: center; margin: auto; line-height: 22px; font-size: 14px\"><u><strong>GOLD LOAN RRECEIPT</strong></u></p>\n"
			+ "   <div style=\"width: 100%; display: table; font-size: 14px; font-weight: 400; line-height: 22px; margin-left: 20px; margin-top: 10px\">\n"
			+ "    <div style=\"display: table-row; height: 100px; text-align: left\">\n"
			+ "      <div style=\"width: 50%; display: table-cell;\">\n"
			+ "        <p>\n"
			+ "          Customer ID: ###CUSTOMER_ID### &nbsp;\n"
			+ "          Loan ID: ###LOAN_ID###<br>\n"
			+ "          Loan Amount: &#8377;###LOAN_AMOUNT###<br>\n"
			+ "         Interest Rate (per month): ###INTEREST_RATE### %<br>\n"
			+ "        Tenure (months): ###TENURE### months<br>\n"
			+ "         Loan Start Date: ###START_DATE###<br>\n"
			+ "      </p>\n"
			+ "      </div>\n"
			+ "      <div style=\"display: table-cell; text-align: left\">\n"
			+ "       Applicant Name: ###FIRST_NAME### ###LAST_NAME###<br>\n"
			+ "        Father / Husband Name: ###FATHER_NAME###<br>\n"
			+ "       Mobile Number: ###MOBILE_NUMBER###<br>\n"
			+ "        Address: ###ADDRESS###<br>\n"
			+ "     Loan End Date: ###END_DATE###<br>\n"
			+ "        </div>\n"
			+ "    </div>\n"
			+ "  </div>\n"
			+ "  <p style=\"text-align: left; margin-left: 20px; font-size: 14px;\"><strong>Gold Ornament Details:</strong><br><br>\n"
			+ "  Items: ###GOLD_DETAILS###,&nbsp;&nbsp;Total Gross Weight: ###GROSS_WEIGHT### gms<br>Qty: ###ITEM_QTY###,&nbsp;&nbsp;Total Net Weight: ###NET_WEIGHT### gms\n"
			+ "  </p>\n"
			+ "  <p style=\"text-align: left; font-size: 14px; margin-left: 20px;\">Total Items: ###TOTAL_ITEMS### &nbsp;&nbsp;\n"
			+ "  Market Value: &#8377; ###MARKET_VALUE###</p>\n"
			+ "   <p style=\"text-align: right; margin-right: 20px\">Authorised Signature\n"
			+ "    </p>\n"
			+ "<p style=\"text-align: center; margin-left: 20px; font-size: 14px;\">For the attention of the pawner</p>\n"
			+ "<p style=\"align: justify; margin-left: 20px; font-size: 14px; text-justify: inter-word; line-height: 17px; letter-spacing: 0.8px\">\n"
			+ "  The maximum period of the loan will be two months/ three months/ six months/ one year.\n"
			+ "If the pawner fails to redeem the pledged articles within the said period, the Bank will have the right to dispose off the ornament/s either in the public\n"
			+ "auction or by negotiation and the proceeds of such sale will be credited to the loan account. The pawner will be personally liable for the deficit in the \n"
			+ "loan account, if any. The bank will also have \"General Lien\" over the articles pledged.\n"
			+ "</p>\n"
			+ "</div>";
	   		
	@Override
	public String replaceContractVariables(String html, int loanId) {
		System.out.println("inside if");
			ApplicationModel applicationModel = applicationRepository.findById(loanId).orElse(null);
			System.out.println(applicationModel);
			CustomerModel customerModel = customerRepository.findById(applicationModel.getCustomer().getCustomerId()).orElse(null);
		    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			html = html.replaceAll("###APPLICATION_DATE###", sdf.format(customerModel.getDateCreated()).toString());
			html = html.replaceAll("###CUSTOMER_ID###", Integer.toString(customerModel.getCustomerId()));
			html = html.replaceAll("###FIRST_NAME###", customerModel.getFirstName());
			html = html.replaceAll("###LAST_NAME###", customerModel.getLastName());
			html = html.replaceAll("###FATHER_NAME###", customerModel.getFather_husband_name());
			html = html.replaceAll("###MOBILE_NUMBER###", customerModel.getMobileNumber());
			html = html.replaceAll("###ADDRESS###", customerModel.getAddress());
			
			html = html.replaceAll("###DATE_OF_BIRTH###", sdf.format(customerModel.getDOB()).toString());
			html = html.replaceAll("###MARITAL_STATUS###", customerModel.getMaritalStatus());
			html = html.replaceAll("###GENDER###", customerModel.getGender());
			
			if(customerModel.getPanNumber()!=null) {
				html = html.replaceAll("###PAN_NUMBER###", customerModel.getPanNumber());
			}else {
				html = html.replaceAll("###PAN_NUMBER###", "");
			}
			
			if(customerModel.getAadharNumber()!=null) {
				html = html.replaceAll("###AADHAR_NUMBER###", customerModel.getAadharNumber());
			}else {
				html = html.replaceAll("###AADHAR_NUMBER###", "");
			}
	
			
			html = html.replaceAll("###NOMINEE_NAME###", customerModel.getNomineeName());
			html = html.replaceAll("###NOMINEE_RELATION###", customerModel.getNomineeRelation());
			html = html.replaceAll("###NOMINEE_GENDER###", customerModel.getNomineeGender());
			html = html.replaceAll("###NOMINEE_DOB###", sdf.format(customerModel.getNomineeDob()).toString());
			html = html.replaceAll("###NOMINEE_MOBILE###", customerModel.getNomineeMobile());
			html = html.replaceAll("###NOMINEE_ADDRESS###", customerModel.getNomineeAddress());
			html = html.replaceAll("###LOAN_ID###", Integer.toString(applicationModel.getApplicationId()));

			html = html.replaceAll("###LOAN_AMOUNT###", Long.toString(applicationModel.getLoanAmount()));
			html = html.replaceAll("###INTEREST_RATE###", applicationModel.getInterestRate().toString());
			html = html.replaceAll("###TENURE###", Integer.toString(applicationModel.getTenure()));
			html = html.replaceAll("###START_DATE###", sdf.format(applicationModel.getStartDate()));
			html = html.replaceAll("###END_DATE###", sdf.format(applicationModel.getEndDate()));

			html = html.replaceAll("###PROCESSING_FEES###", Long.toString(applicationModel.getProcessingFees()));
			html = html.replaceAll("###STAMP_DUTY###", Long.toString(applicationModel.getStampDuty()));
			html = html.replaceAll("###DISBURSED_AMOUNT###", Long.toString(applicationModel.getDisbursedAmount()));
			html = html.replaceAll("###PAYMENT_MODE###", applicationModel.getPaymentMode().toString());
			
			if(applicationModel.getRemarks() !=null) {
				html = html.replaceAll("###REMARKS###", applicationModel.getRemarks().toString());
			}else {
				html = html.replaceAll("###REMARKS###", "");
			}
			
	        List<ItemModel> itemList = applicationModel.getItemList();
	        
			System.out.println(itemList);

			long total = 0;
			BigDecimal grossWeight = BigDecimal.ZERO;
			BigDecimal netWeight = BigDecimal.ZERO;
			List<String> itemType = new ArrayList<>();
			List<Integer> qty = new ArrayList<>();
			int totalQty = 0;
			for(int i=0; i < itemList.size(); i++) {
				total+= itemList.get(i).getTotalAmt();
			    grossWeight = grossWeight.add(itemList.get(i).getGrossWeight());
			    netWeight = netWeight.add(itemList.get(i).getNetWeight());
				itemType.add(itemList.get(i).getItemType());
				qty.add(itemList.get(i).getQty());
				totalQty+= itemList.get(i).getQty();
			}
			System.out.println(grossWeight);
			html = html.replaceAll("###MARKET_VALUE###", Long.toString(total));
			html = html.replaceAll("###TOTAL_ITEMS###", Integer.toString(totalQty));
			html = html.replaceAll("###GROSS_WEIGHT###", grossWeight.toString());
			html = html.replaceAll("###NET_WEIGHT###", netWeight.toString());
			html = html.replaceAll("###GOLD_DETAILS###", itemType.toString());
			html = html.replaceAll("###ITEM_QTY###", qty.toString());
			
			return html;
	}
	
	@Override
	public byte[] generatePdfContent(int loanId) {
	try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
		if(html != null)
		{
			html = replaceContractVariables(html,loanId);
		}
			PdfWriter pdfWriter = new PdfWriter(baos);
			DefaultFontProvider defaultFontProvider = new DefaultFontProvider();
			ConverterProperties converterProperties = new ConverterProperties();
			converterProperties.setFontProvider(defaultFontProvider);
			HtmlConverter.convertToPdf(html, pdfWriter, converterProperties);
          	return baos.toByteArray();
       } catch (IOException e) {
           // Handle exception
           e.printStackTrace();
           return new byte[0];
       }
}

	
	
}
