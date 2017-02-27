<div class="modal fade" id="myUnitModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">Computer Unit List</div>
			<div class="modal-body">
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Unit No</th>
							<th>Unit Name</th>
						</tr>
					</thead>
					<tbody id="modalContent"></tbody>
				</table>			
			<div class=spinner></div>
			</div>
			<div class="modal-footer">
				<button type=button class="btn btn-default" data-dismiss="modal"  id="btnOk" onclick="setUnit()">OK</button>
			</div>
		</div>
	</div>
</div>
<style type="text/css">


@keyframes spin{
	from{
		transform:rotate(0deg);
	}
	to{
		transform:rotate(360deg);
	}
}
	.spinner{
		width: 30px;
		height: 30px;
		border: 6px solid #000;
		border-left-color: #333;
		border-bottom-color: #555;
		border-right-color: transparent;
		border-radius: 100%;
		animation: spin 600ms linear infinite;
	}
</style>