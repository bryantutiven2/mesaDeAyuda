/*DateTimePicker de fecha fin para solicitudesNuevasAdmin.jsp*/
$(function () {
    $('#dtPicker').datetimepicker({
        format: "DD/MM/YYYY hh:mm a"
    });
});

/*Permite obtener una fila seleccionada y cargarla en el toggle para asignar un solicitud  un tecnico en solicitudesNuevasAdmin.jsp*/
$(document).ready(function() {
    $('.asignarSolicitud').click(function(){
        
        $(this).parents("tr").find(".id_sn").each(function() {
            document.getElementById("codigo_sn").value = $(this).html();
        });
        $(this).parents("tr").find(".descrip_sn").each(function() {
            document.getElementById("descripcion_sn").value = $(this).html();
        });
        $(this).parents("tr").find(".user_sn").each(function() {
            document.getElementById("usuario_sn").value = $(this).html();
        });
        $(this).parents("tr").find(".n_sn").each(function() {
            document.getElementById("n_vez_sn").value = $(this).html();
        });
        $(this).parents("tr").find(".ids_sn").each(function() {
            document.getElementById("idsn_vez_sn").value = $(this).html();
        });
        $(this).parents("tr").find(".fechaInicio_sn").each(function() {
            document.getElementById("fechaInicio_sn").value = $(this).html();
        });
        $("#myModalNuevaSolicitud").modal();
    });
    
});

$(document).ready(function () {
  $('#tablaNuevaS').DataTable({
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


