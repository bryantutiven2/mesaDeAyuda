
/*DateTimePicker para fecha inicio y fin en crearSolicitudAdmin.jsp*/
$(function () {
    $('#dtPickerFI').datetimepicker({
        format: "DD/MM/YYYY hh:mm a"
    });
    $('#dtPickerFF').datetimepicker({
        format: "DD/MM/YYYY hh:mm a",
        useCurrent: false
    });
    $("#dtPickerFI").on("change.datetimepicker", function (e) {
        $('#dtPickerFF').datetimepicker('minDate', e.date);
    });
    $("#dtPickerFF").on("change.datetimepicker", function (e) {
        $('dtPickerFI').datetimepicker('maxDate', e.date);
    });
});

/*Activar toggle de cargar usuarios en crearSolicitudAdmin.jsp */
$(document).ready(function() {
    $('.cargarTogleUsuarios').click(function(){
        $("#myModalCS").modal();
    });
    
});

/*Activar estilo de tabla*/
$(document).ready(function () {
  $('#dtUsuariosSA').DataTable({
    //para cambiar el lenguaje a español
        "language": {
            "lengthMenu": "Mostrar _MENU_ registros",
            "zeroRecords": "No se encontraron resultados",
            "info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "infoFiltered": "(filtrado de un total de _MAX_ registros)",
            "sSearch": "Buscar:",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast":"Último",
                "sNext":"Siguiente",
                "sPrevious": "Anterior"
                         },
                         "sProcessing":"Procesando...",
        }
    });
});

/*Permite cargar el usuario seleccionado en el toggle en crearSolicitudAdmin.jsp*/
$(document).ready(function() {
    $('#cargarIdUSA').click(function(){
        var selected = '';    
        $('#idsUSA input[type=checkbox]').each(function(){
            if(this.checked){
                selected += $(this).val();
            } 
        }); 
    document.getElementById("idUserSolicitaA").value= selected;
    $('#idUserSolicitaA').attr('value', selected);
    });         
});


/*filtrar subtipo por tipo*/

function filtrarSubtipo(value){
  var select=document.getElementById("selectSubtipo");
  // Cogemos el listado de opciones en un array de valores
  var op=select.getElementsByTagName("option");
  // Seleccionamos la primera opción
  select.options[0].selected=true;
  // Recorremos todas las opciones del segundo select
  for (var i = 0; i < op.length; i++) {
      var subtipo = op[i].value;
      var res = subtipo.split("-")[0];
      if(res == value){
        op[i].style.display="block";
      }
      else{
        op[i].style.display="none";
      }
  }
};  
