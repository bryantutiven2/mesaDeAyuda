
$(document).ready(function() {
    $('.asignarSolicitud').click(function(){
        $("#myModalNuevaSolicitud").modal();
    });         
});

$('.datepicker').datepicker({
    format: 'mm/dd/yyyy'
});