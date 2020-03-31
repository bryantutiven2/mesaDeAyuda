jQuery.datetimepicker.setDateFormatter('moment');
$("#dtPickerFI").datetimepicker({
    timepicker: true,
    datepicker: true,
    format: 'DD/MM/YYYY HH:mm',
    hours12: false
});
$('#toggleFI').on('click', function(){
    $('#dtPickerFI').datetimepicker('toggleFI');
});


jQuery.datetimepicker.setDateFormatter('moment');
$("#dtPickerFF").datetimepicker({
    timepicker: true,
    datepicker: true,
    format: 'DD/MM/YYYY HH:mm',
    hours12: false
});
$('#toggleFF').on('click', function(){
    $('#dtPickerFF').datetimepicker('toggleFF');
});

$(document).ready(function() {
    $('.cargarTogleUsuarios').click(function(){
        $("#myModalCS").modal();
    });
    
});


$(document).ready(function () {
  $('#dtUsuariosSA').DataTable();
  $('.dataTables_length').addClass('bs-select');
});

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