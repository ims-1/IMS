<div id="spinner"></div>
<style type="text/css">
@
keyframes spin {from { transform:rotate(0deg);
	
}

to {
	transform: rotate(360deg);
}

}
#spinner {
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