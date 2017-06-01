package com.chaincode.domain;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Loan {
  
	private String id;
  	private String propertyId;
  	private String landId;
  	private String permitId;
  	
	private String buyerId;
	private String appraiserApplicationId;
	private String salesContractId;






	public Loan(String id, String propertyId, String landId, String permitId, String buyerId, int approvedAmount,int outstandingSettlementAmount ) {
		super();
		this.id = id;
		this.propertyId = propertyId;
		this.landId = landId;
		this.permitId = permitId;
		this.buyerId = buyerId;
		this.approvedAmount = approvedAmount;
		this.outstandingSettlementAmount = outstandingSettlementAmount;
	}
	private int approvedAmount;
	private int outstandingSettlementAmount;
	
   
   
	public Loan() {
		super();
    }

	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public String getPropertyId() {
		return propertyId;
	}





	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}





	public String getLandId() {
		return landId;
	}





	public void setLandId(String landId) {
		this.landId = landId;
	}





	public String getPermitId() {
		return permitId;
	}





	public void setPermitId(String permitId) {
		this.permitId = permitId;
	}





	public String getBuyerId() {
		return buyerId;
	}





	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}





	public String getAppraiserApplicationId() {
		return appraiserApplicationId;
	}





	public void setAppraiserApplicationId(String appraiserApplicationId) {
		this.appraiserApplicationId = appraiserApplicationId;
	}





	public String getSalesContractId() {
		return salesContractId;
	}





	public void setSalesContractId(String salesContractId) {
		this.salesContractId = salesContractId;
	}

	
	public int getApprovedAmount() {
		return approvedAmount;
	}
	public void setApprovedAmount(int approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	public int getOutstandingSettlementAmount() {
		return outstandingSettlementAmount;
	}
	public void setOutstandingSettlementAmount(int outstandingSettlementAmount) {
		this.outstandingSettlementAmount = outstandingSettlementAmount;
	}


}