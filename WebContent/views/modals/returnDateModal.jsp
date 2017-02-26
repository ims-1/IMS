<div class="modal fade" id="modalReturnDate">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">CONFIRMATION</div>
			<div class="modal-body">
				<table class="table table-striped table-bordered">
					<thead>
					</thead>
					<tbody id="modalContentReturnDate">
						<tr>
							<td>
								<p>Are you sure you want to re-assign this unit</p><br>
								<label>Return Date: </label>
								<input type="text" id="txtReturnDate" class="date required form-class" placeholder="MM/DD/YYYY" />
							</td>
						</tr>
					</tbody>
				</table>			
			</div>
			<div class="modal-footer">
				<button type=button class="btn btn-default" data-dismiss="modal"  id="btnYes">YES</button>
				<button type=button class="btn btn-default" data-dismiss="modal"  id="btnNo" onclick="cancelUnit()">NO</button>
			</div>
		</div>
	</div>
</div>