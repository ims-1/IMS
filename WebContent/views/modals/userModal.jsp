<div class="modal fade" id="myAssigneeModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">Edit User</div>
			<div class="modal-body">
				<table class="table borderless">
					<tr class="record">
						<td class="label-right">USER ID</td>
						<td><input id="txtUserId" type="text"
							class="required form-control" value="${userId}" ${disableUserId}
							maxlength="15" /></td>
					</tr>
					<tr class="record">
						<td class="label-right">FIRST NAME</td>
						<td><input id="txtFirstName" type="text"
							class="required form-control" value="${firstName}"
							maxlength="250" /></td>
					</tr>
					<tr class="record">
						<td class="label-right">MIDDLE INITIAL</td>
						<td><input id="txtMiddleInitial" type="text"
							class="required form-control" value="${middleInitial}"
							maxlength="3" /></td>
					</tr>
					<tr class="record">
						<td class="label-right">LAST NAME</td>
						<td><input id="txtLastName" type="text"
							class="required form-control" value="${lastName}" maxlength="250" /></td>
					</tr>
					<tr class="record">
						<td class="label-right">EMAIL</td>
						<td><input id="txtEmail" type="text"
							class="required form-control" value="${email}" maxlength="100" /></td>
					</tr>
					<tr class="record">
						<td class="label-right">STATUS</td>
						<td style="color: white;">ACTIVE<input id="rdActive"
							name="status" type="radio" /> INACTIVE<input id="rdInactive"
							name="status" type="radio" /></td>
					</tr>
					<tr class="record">
						<td class="label-right">USER ACCESS</td>
						<td><select id="selUserAccess" class="required form-control">
								<option value="A">ADMIN</option>
								<option value="U">REGULAR USER</option>
						</select></td>
					</tr>
					<tr class="record">
						<td class="label-right">ENTRY DATE</td>
						<td><input id="txtEntryDate" type="text" disabled="disabled"
							value="${entryDate}" /></td>
					</tr>
					<tr class="record">
						<td class="label-right">REMARKS</td>
						<td><input id="txtRemarks" type="text" class="form-control"
							value="${remarks}" maxlength="100" /></td>
					</tr>
					<tr class="record">
						<td class="label-right">LAST UPDATED BY</td>
						<td><input id="txtLastUpdatedBy" type="text"
							disabled="disabled" value="${lastUserId}" /></td>
					</tr>
					<tr class="record">
						<td class="label-right">LAST UPDATE</td>
						<td><input id="txtLastUpdate" type="text" disabled="disabled"
							value="${lastUpdate}" /></td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<button type=button class="btn btn-default" data-dismiss="modal">OK</button>
				<input id="btnBackToUserListingPage" type="button" value="BACK"
					class="btn btn-primary" onclick="backToUserListingPage()" /> <input
					id="btnSaveUser" type="button" value="SAVE" class="btn btn-primary"
					onclick="saveUser()" /> <input id="btnUserChangePassword"
					type="button" value="CHANGE PASSWORD" class="btn btn-primary"
					onclick="userChangePassword()" />
			</div>
		</div>
	</div>
</div>