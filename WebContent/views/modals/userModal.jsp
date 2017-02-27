<div class="modal fade" id="myUserModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">Edit User</div>
			<div class="modal-body">
				<table class="table  borderless">
					<thead>
						<tr>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="align-right">USER ID</td>
							<td><input id="txtUserId" type="text"
								class="required form-control" disabled="disabled" maxlength="15" /></td>
						</tr>
							<tr>
								<td class="align-right">FIRST NAME</td>
								<td><input id="txtFirstName" type="text"
											class="required form-control"
											maxlength="250" /></td>
								</tr>
								<tr>
									<td class="align-right">MIDDLE INITIAL</td>
									<td><input id="txtMiddleInitial" type="text"
											class="required form-control"
											maxlength="3" /></td>
									</tr>
									<tr>
										<td class="align-right">LAST NAME</td>
										<td><input id="txtLastName" type="text"
											class="required form-control" maxlength="250" /></td>
									</tr>
									<tr>
										<td class="align-right">EMAIL</td>
										<td><input id="txtEmail" type="text"
											class="required form-control" maxlength="100" /></td>
									</tr>
									<tr>
										<td class="align-right">STATUS</td>
										<td style="color: black;">ACTIVE<input id="rdActive"
											name="status" type="radio" /> INACTIVE<input id="rdInactive"
											name="status" type="radio" /></td>
									</tr>
									<tr>
										<td class="align-right">USER ACCESS</td>
										<td><select id="selUserAccess" class="required form-control">
												<option value="A">ADMIN</option>
												<option value="U">REGULAR USER</option>
										</select></td>
									</tr>
									<tr>
										<td class="align-right">ENTRY DATE</td>
										<td><input id="txtEntryDate" type="text" disabled="disabled"
											 /></td>
									</tr>
									<tr>
										<td class="align-right">REMARKS</td>
										<td><input id="txtRemarks" type="text" class="form-control"
											maxlength="100" /></td>
									</tr>
									<tr>
										<td class="align-right">LAST UPDATED BY</td>
										<td><input id="txtLastUpdatedBy" type="text"
											disabled="disabled" /></td>
									</tr>
									<tr>
										<td class="align-right">LAST UPDATE</td>
										<td><input id="txtLastUpdate" type="text" disabled="disabled"
											 /></td>
									</tr>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<input id="btnBack" type="button" value="BACK"
					class="btn btn-primary" onclick="backToUserListingPage()"/> 
				<input id="btnSaveUser" type="button" value="SAVE" class="btn btn-primary"
					onclick="saveUserEdit()" /> 
				<input id="btnUserChangePassword"
					type="button" value="CHANGE PASSWORD" class="btn btn-primary" data-toggle=modal data-target=#myPasswordModal />
			</div>
		</div>
	</div>
</div>