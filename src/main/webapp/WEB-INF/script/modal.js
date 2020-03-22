function myFunction() {
    var option_value = document.getElementById("reincidencia").value;
    if (option_value == "2" || option_value == "3" || option_value == "4") {
       $("#myModal").modal();
     }
};

$(document).ready(function () {
  $('#dtBasicExample').DataTable();
  $('.dataTables_length').addClass('bs-select');
});

$(document).ready(function() {
    $('#cargarIds').click(function(){
        var selected = '';    
        $('#idsItems input[type=checkbox]').each(function(){
            if(this.checked){
                selected += $(this).val()+',';
                console.log($(this).val());
            } 
        }); 
    document.getElementById("ids_aydudaas").value= selected.slice(0,(selected.length-1));
    $('#ids_aydudaas').attr('value', selected.slice(0,(selected.length-1)));
    });         
});    