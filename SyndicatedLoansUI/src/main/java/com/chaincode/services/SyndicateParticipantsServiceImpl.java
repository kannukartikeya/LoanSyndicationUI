package com.chaincode.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.client.RestTemplate;

import com.chaincode.domain.ChainCode;
import com.chaincode.domain.ChaincodeId;
import com.chaincode.domain.ConstructorMsg;
import com.chaincode.domain.Loan;
import com.chaincode.domain.Params;
import com.chaincode.domain.Participant;
import com.chaincode.domain.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SyndicateParticipantsServiceImpl implements SyndicateParticipantsService {

    private String chainCode;
    private String url ; 


    
    public SyndicateParticipantsServiceImpl (){
    	this.chainCode = "4b987ca9c261d4899eef92b1d93662f2074124ff89a6f3d33d49a3de3f640fd4ca476cd7b0447c013abf2bf2b9b992518090dc65ead481e211c4e3e59b49ced5";
    	this.url = "https://36615276e07f4e5eb6a9915ec8432e62-vp0.us.blockchain.ibm.com:5001/chaincode";
    }

  
	@Override
	public Participant getParticipantDetails(String id) {
    	ChainCode cd = new ChainCode();
    	ChaincodeId chaincodeID = new ChaincodeId();
    	chaincodeID.setName(chainCode);
    	
    	ConstructorMsg ctorMsg = new ConstructorMsg();
    	ctorMsg.setFunction("GetLoanParticipant");
    	String[] args = new String[1];
    	args[0] = "part1";
    	ctorMsg.setArgs(args);
    	Params params= new Params();
    	params.setType(1);
    	params.setChaincodeID(chaincodeID);
    	params.setCtorMsg(ctorMsg);
    	params.setSecureContext("user_type1_0");
            	
    	cd.setJsonrpc("2.0");
        cd.setMethod("query");
        cd.setParams(params);
        cd.setId(1);
        
        ObjectMapper mapper = new ObjectMapper();
        
        try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cd));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        RestTemplate restTemplate = new RestTemplate();
        
        ChainCode response = restTemplate.postForObject(url, cd, ChainCode.class);
        
        System.out.println("Response from server is" + response.getResult().getMessage());
        Participant participant_details = null;
        
        try {
        	participant_details = mapper.readValue(response.getResult().getMessage(), Participant.class);
			System.out.println("Fetched participantDetails " + participant_details.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return participant_details;
		}
    		      
    }

    /*@Override
    public Product getProductById(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }*/

