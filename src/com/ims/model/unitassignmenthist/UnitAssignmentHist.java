package com.ims.model.unitassignmenthist;

import java.util.Date;

public class UnitAssignmentHist {
	
	private Integer histNo;
	private Integer unitNo;
	private String unitName;
	private String assigneeNo;
	private String location;
	private String status;
	private String ipAddress;
	private String assignedBy;
	private Date assignedDate;
	private String assignTag;
	private Date returnDate;
	private String userId;
	private Date lastUpdate;
	
	public Date getAssignedDate() {
		return assignedDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public Integer getHistNo() {
		return histNo;
	}
	public void setHistNo(Integer histNo) {
		this.histNo = histNo;
	}
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
	public String getAssigneeNo() {
		return assigneeNo;
	}
	public void setAssigneeNo(String assigneeNo) {
		this.assigneeNo = assigneeNo;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}
	public String getAssignTag() {
		return assignTag;
	}
	public void setAssignTag(String assignTag) {
		this.assignTag = assignTag;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
