$(function () {
    $('#dtPicker').datetimepicker({
        format: "DD/MM/YYYY hh:mm a"
    });
});
            
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
  $('#tablaNuevaS').DataTable();
  $('.dataTables_length').addClass('bs-select');
});


