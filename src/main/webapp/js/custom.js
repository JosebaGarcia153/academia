$(document).ready( function () {
	// seleccion por id => #example y ejecuta el plugin .DataTable();
    $('#myTable1').DataTable();
} );

$(document).ready( function () {
	// seleccion por id => #example y ejecuta el plugin .DataTable();
    $('#myTable2').DataTable();
} );

function confirmar(nombre) {
	
	// The confirm() method returns true if the user clicked "OK", and false otherwise. 
	if ( confirm('Estas seguro de querer hacer esto ' + nombre + '?') ){
		
		console.debug(' continua el evento por defecto del ancla ');
		
	}else {
		console.debug(' prevenimos o detenemos el evento del ancla ');
		event.preventDefault();
	}		
}