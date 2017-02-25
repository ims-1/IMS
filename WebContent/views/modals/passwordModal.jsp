<div class="modal fade" id="myPasswordModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">Change Password</div>
			<div class="modal-body">
				<table>
					<tr>
						<td class="align-right">CURRENT PASSWORD  </td>
						<td><input id="txtCurrentPassword" type="password"
							class="form-control" maxlength="100" /></td>
					</tr>
					<tr>
						<td class="align-right">NEW PASSWORD</td>
						<td><input id="txtNewPassword" type="password"
							class="form-control" maxlength="100" /></td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<input id="btnBackToUserListingPage" type="button" value="BACK" class="btn btn-primary" data-dismiss="modal" /> 
				<input id="btnConfirm" type="button" value="CONFIRM" class="btn btn-primary" onclick="confirmPassword()" />
			</div>
		</div>
	</div>
</div>