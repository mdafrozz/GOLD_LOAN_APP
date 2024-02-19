package com.bridgelabz.bookstoreapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreapp.dto.ApplicationDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.ApplicationModel;
import com.bridgelabz.bookstoreapp.service.IApplicationService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/application")
public class ApplicationController {

	@Autowired IApplicationService iApplicationService;

	// Create new application
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@RequestBody ApplicationDTO applicationDTO){
			int id = iApplicationService.createApplication(applicationDTO);
			ResponseDTO responseDTO = new ResponseDTO("Application Created Successfully..!!!", id);
			return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
	}

	// Get all data
	@GetMapping("/getall")
	public List<ApplicationModel> getAllApplicationDetails() {
		List<ApplicationModel> applicationList = iApplicationService.getAllApplicationData();
		return applicationList;
	}

	// Get User Data by ID
	@GetMapping("/getby/{id}")
	public ResponseEntity<ResponseDTO> getApplicationById(@PathVariable int id) {
		ApplicationModel applicationModel = iApplicationService.getApplicationDataById(id);
		ResponseDTO responseDTO = new ResponseDTO("Application Details with ID: " + id, applicationModel);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
	
	// Get all data
	@GetMapping("/getActiveLoans")
	public List<ApplicationModel> getActiveLoans() {
		List<ApplicationModel> applicationList = iApplicationService.getActiveLoans();
		return applicationList;
	}
	
	// Get all data
	@GetMapping("/getOverDueLoans")
	public List<ApplicationModel> getAllOverDueLoans() {
		List<ApplicationModel> applicationList = iApplicationService.getAllOverDueLoans();
		return applicationList;
	}
	
	// Get all data
	@GetMapping("/getClosedLoans")
	public List<ApplicationModel> getAllClosedLoans() {
		List<ApplicationModel> applicationList = iApplicationService.getAllClosedLoans();
		return applicationList;
	}
	
	// Search by Book
	@GetMapping("/search/{name}")
	public ResponseEntity<ResponseDTO> SearchByName(@PathVariable String name) {
		List<ApplicationModel> applicationList = iApplicationService.searchbyName(name);
		ResponseDTO responseDTO = new ResponseDTO("Number of Customers: " + applicationList.size(), applicationList);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
	
	// Update by Book ID
	@PostMapping("/closeLoan/{loanId}")
	public ResponseEntity<ResponseDTO> closeLoan(@RequestBody ApplicationDTO applicationDTO, @PathVariable int loanId){
		int applicationModel = iApplicationService.closeLoan(applicationDTO, loanId);
			ResponseDTO responseDTO = new ResponseDTO("", "Updated Successfully");
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
	
//    @GetMapping("/generate")
//    public ResponseEntity<byte[]> generatePdf() {
//        // Generate PDF using a library (e.g., Apache PDFBox or Flying Saucer)
//        byte[] pdfContent = generatePdfContent();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("document.pdf").build());
//        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
//    }
//    
//    private byte[] generatePdfContent() {
//    	try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                PDDocument document = new PDDocument()) {
//
//               // Create a page
//               PDPage page = new PDPage();
//               document.addPage(page);
//
//               // Add content to the page
//               try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
//                   contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
//                   contentStream.beginText();
//                   contentStream.newLineAtOffset(100, 700);
//                   contentStream.showText("Hello, this is a sample PDF generated using Apache PDFBox!");
//                   contentStream.endText();
//               }
//
//               // Save the document to ByteArrayOutputStream
//               document.save(baos);
//               document.close();
//
//               return baos.toByteArray();
//           } catch (IOException e) {
//               // Handle exception
//               e.printStackTrace();
//               return new byte[0];
//           }
//    }

}
