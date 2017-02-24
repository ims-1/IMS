package com.ims.entity.computerunitsinventory;

import java.util.Date;

public class ComputerUnits {
	private Integer unitNo;
	private String unitName;
	private String tagNumber;
	private String ipAddress;
	private String type;
	private Date acquiredDate;
	private String description;
	private String serialNo;
	private String brand;
	private String model;
	private String color;
	private String remarks;
	private String deleteTag;
	private String userId;
	private Date lastUpdate;

/*	public ComputerUnits(Integer unitNo, String unitName, String tagNumber, String ipAddress, String type,
			Date acquiredDate, String description, String serialNo, String brand, String model, String color,
			String remarks,  String userId, Date lastUpdate) {
		super();
		this.unitNo = unitNo;
		this.unitName = unitName;
		this.tagNumber = tagNumber;
		this.ipAddress = ipAddress;
		this.type = type;
		this.acquiredDate = acquiredDate;
		this.description = description;
		this.serialNo = serialNo;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.remarks = remarks;
		this.userId = userId;
		this.lastUpdate = lastUpdate;
	}*/

	public Integer getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(Integer unitNo) {
		this.unitNo = unitNo;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getTagNumber() {
		return tagNumber;
	}

	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getAcquiredDate() {
		return acquiredDate;
	}

	public void setAcquiredDate(Date acquiredDate) {
		this.acquiredDate = acquiredDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDeleteTag() {
		return deleteTag;
	}

	public void setDeleteTag(String deleteTag) {
		this.deleteTag = deleteTag;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
