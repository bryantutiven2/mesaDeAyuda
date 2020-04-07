
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