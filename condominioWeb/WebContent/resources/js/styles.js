$(document).ready(function(){
	$("#pag_cont").load("introduccion.xhtml");
	
	$("#pgIntroduccion").on("click", function(){
		$("#pag_cont").load("introduccion.xhtml");
	});
	
	$("#pgPlantilla").on("click", function(){
		$("#pag_cont").load("plantilla.xhtml");
	});

	$("#pgIcon").on("click", function(){
		$("#pag_cont").load("iconografia.xhtml");
	});

	$("#pgEjemplo").on("click", function(){
		$("#pag_cont").load("ejemplo.xhtml");
	});
	
	$("#pgHorizontal").on("click", function(){
		$("#pag_cont").load("../menu/menuH.xhtml");
	});

	$("#pgVertical").on("click", function(){
		$("#pag_cont").load("../menu/menuV.xhtml");
	});

	$("#pgStep").on("click", function(){
		$("#pag_cont").load("../menu/Steps.xhtml");
	});
	
	$("#pgBoton").on("click", function(){
		$("#pag_cont").load("../componentes/button.xhtml");
	});
	
	$("#pgForm").on("click", function(){
		$("#pag_cont").load("../componentes/form.xhtml");
	});
	
	$("#pgTabla").on("click", function(){
		$("#pag_cont").load("../componentes/dataTable.xhtml");
	});
	
	$("#pgOverlay").on("click", function(){
		$("#pag_cont").load("../componentes/dialog.xhtml");
	});
	
	$("#pgPanel").on("click", function(){
		$("#pag_cont").load("../componentes/panel.xhtml");
	});
	
	$("#pgLogin").on("click", function(){
		$("#pag_cont").load("../paginas/login2.xhtml");
	});
	
	$("#pgLanding").on("click", function(){
		$("#pag_cont").load("../paginas/landing2.xhtml");
	});

	$("#pgNotFound").on("click", function(){
		$("#pag_cont").load("../paginas/4042.xhtml");
	});

	$("#pgDenegado").on("click", function(){
		$("#pag_cont").load("../paginas/denied2.xhtml");
	});

	$("#pgError").on("click", function(){
		$("#pag_cont").load("../paginas/error2.xhtml");
	});
});