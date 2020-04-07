/*activar modal*/
function myFunction() {
    var option_value = document.getElementById("reincidencia").value;
    if (option_value == "2" || option_value == "3" || option_value == "4") {
       $("#myModal").modal();
     }
};

/*diseño del table*/
$(document).ready(function () {
  $('#dtBasicExample').DataTable();
  $('.dataTables_length').addClass('bs-select');
});
/*obtener ids y colocarlos el en input*/
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

/*limitar numero de checks*/
$(document).ready(function () {
   $("input[name='ug-checkbox']").change(function () {
      var limit = document.getElementById("reincidencia").value;
      var cantidadCkb = $("input[name='ug-checkbox']:checked").length;
      if (cantidadCkb > limit) 
      {
         $(this).prop("checked", "");
         alert("Solo puede seleccionar: "+ limit+" veces");
     }
  });
});