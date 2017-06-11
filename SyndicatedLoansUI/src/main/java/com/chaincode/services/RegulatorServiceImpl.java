package com.chaincode.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.client.RestTemplate;

import com.chaincode.domain.BlockChain;
import com.chaincode.domain.ChainCode;
import com.chaincode.domain.ChaincodeId;
import com.chaincode.domain.ConstructorMsg;
import com.chaincode.domain.Loan;
import com.chaincode.domain.Params;
import com.chaincode.domain.Participant;
import com.chaincode.domain.Product;
import com.chaincode.domain.SingleBlock;
import com.chaincode.domain.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Filter.Chain;

@Service
public class RegulatorServiceImpl implements RegulatorService {

    private String chainCode;
    private String url ; 
    private String blockurl;


    
    public RegulatorServiceImpl (){
    	this.chainCode = "4b987ca9c261d4899eef92b1d93662f2074124ff89a6f3d33d49a3de3f640fd4ca476cd7b0447c013abf2bf2b9b992518090dc65ead481e211c4e3e59b49ced5";
    	this.url = "https://36615276e07f4e5eb6a9915ec8432e62-vp0.us.blockchain.ibm.com:5001/chain";
    	this.blockurl = "https://36615276e07f4e5eb6a9915ec8432e62-vp0.us.blockchain.ibm.com:5001/chain/blocks";
    }

  
	@Override
	public List<Transaction> getChain() {
/*        try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cd));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
        
        RestTemplate restTemplate = new RestTemplate();
        
        BlockChain blockHeight = restTemplate.getForObject(url, BlockChain.class);
        
        System.out.println("Height of blockChain is" + blockHeight.getHeight());
        
        int block;
        String eachBlockUrl;
        SingleBlock response;
        
        List<Transaction> blockChainList = new ArrayList<Transaction>();
        
        //for (block=0 ; block<blockHeight.getHeight();block ++)
        for (block=1 ; block<blockHeight.getHeight();block ++)
        {
        	eachBlockUrl = this.blockurl+"/"+block;
        	response = restTemplate.getForObject(eachBlockUrl, SingleBlock.class);
        	if(response!=null)
        	{
        		System.out.println("Block Transaction is "+ response);
        		
        		if(response.getTransactions()!=null){
        			System.out.println("Payload is " + response.getTransactions().get(0).getPayload());
        			blockChainList.add(response.getTransactions().get(0));
        		}
        	}
        }
        
        return blockChainList;
		
    		      
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

