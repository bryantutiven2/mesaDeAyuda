/*activar modal en crearSolicitud.jsp*/
function myFunction() {
    let option_value = document.getElementById("reincidencia").value;
    if (option_value == "2" || option_value == "3" || option_value == "4") {
       $("#myModal").modal();
     }
};
/*solicitud ajax post para cargar solicitudes dependiendo del grupo escogido */
function postAjaxTablaSolicitudes(datos){
    $.ajax({
       type: 'POST',
       contentType: 'application/json',
       url: '/mesaayuda/usuario/solicitudes',
       data: JSON.stringify(datos),
       success: function(response){
            if(response.status == "success"){
               $("#bodyTableCargarSolicitudes").empty();
                var tr = '';
                $.each(response.data, function(i, dato){
                    tr+= '<tr>'+
                            '<th class="idsolicitud" scope="row">'+dato.id+'</th>'+
                            '<td>'+dato.grupo+'</td>'+
                            '<td>'+dato.tipo+'</td>'+
                            '<td style="max-width: 260px; text-align: justify">'+dato.descripcion+'</td>'+
                            '<td><input  type="checkbox" name="ug-checkbox" value="'+dato.id+'"/>&nbsp;</td>'+
                        '</tr>';
                });
                $("#bodyTableCargarSolicitudes").html(tr);
                limitarChecks();
                cargarEstiloT();
            }
        },
        error : function(e) {
            $("#bodyTableCargarSolicitudes").html("<strong>No se ha podido cargar</strong>");
        }
    });
}
/* enviar solicitud por medio de post */
function postAjaxEnviarSolicitud(datos){
    $.ajax({
       type: 'POST',
       contentType: 'application/json',
       url: '/mesaayuda/usuario/enviarSolicitud',
       data: JSON.stringify(datos),
       success: function(response){
           $("#mensajeModal").empty();
            if(response.status == "success"){
               $(".loader").removeClass("hidden"); //remover loader
               $("#mensajeModal").html('<i class="fas fa-check-circle" style="color: #02D90C;font-size: 24pt;margin-right: 30px;"></i> Se ha enviado con éxito la solicitud');
               reinciarForm();
               $("#modalMensaje").modal();
            }
            else if(response.status == "error"){
                $(".loader").removeClass("hidden"); //remover loader
                $("#mensajeModal").html('<i class="fas fa-times" style="color: #04A9C8;font-size: 24pt;margin-right: 30px;"></i> No se ha podido enviar la solcitud');
                $("#modalMensaje").modal();
           }
            
        },
        error : function(e) {
            $(".loader").removeClass("hidden"); //remover loader
            $("#mensajeModal").html('<i class="fas fa-times" style="color: #04A9C8;font-size: 24pt;margin-right: 30px;"></i> No se ha podido enviar la solcitud');
            $("#modalMensaje").modal();
        }
    });
}
/* Reniciar formulario*/
function reinciarForm(){
    let selectNvez=document.getElementById("reincidencia");
    selectNvez.options[0].selected=true;
    $('#ids_aydudaas').val("");
    $('#descripcion').val("");
    $('input[name="grupo"]').prop('checked', false);
}

/*enviar solicitud desde usuario*/
$( document ).ready(function() {
    $(document).on('click','#enviarSolicitud', function(){
        $("#mensajeModal").empty();
        let datos;
        let grupoS = $('input[name=grupo]:checked', '#testForm').val();
        let descripcionT = $('#descripcion').val();
        let nvez = $('#reincidencia').val();
        let ids = $('#ids_aydudaas').val();
        if(ids == null || ids == "")
            ids = "null"
        if(nvez === null || descripcionT == "" || grupoS === undefined){
            $("#mensajeModal").html('<i class="fas fa-exclamation-triangle" style="color: #F87011;font-size: 24pt;margin-right: 30px;"></i> Tiene campos sin llenar');
            $("#modalMensaje").modal();
        }
        if(nvez != null && descripcionT != "" && nvez != null && grupoS != undefined){
           datos={
                grupo: grupoS,
                n_vez: nvez,
                ids_n_vez: ids,
                descripcion: descripcionT
            };
            $(".loader").addClass("hidden"); //cargar loader
            postAjaxEnviarSolicitud(datos);
        }
    });
});

/*cargar solicitudes dependiendo de la eleccion de sistemas o mantenimiento*/
$( document ).ready(function() {
    $(document).on('click','#testForm', function(){
        let datos;
        let grupoT = $('input[name=grupo]:checked', '#testForm').val();
        if(grupoT =="sist" || grupoT =="mant"){
            datos={
                grupo: grupoT
            };
            postAjaxTablaSolicitudes(datos);
        }
    });
});

/*diseño del table*/
function cargarEstiloT() {
    $('#dtBasicExample').DataTable({
        retrieve: true,
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
};
/*obtener ids y colocarlos el en input de crearSolicitud.jsp*/
$(document).ready(function() {
    $(document).on('click','#cargarIds', function(){
        let selected = '';    
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

/*limitar numero de checks en crearSolicitud.jsp*/
function limitarChecks() {
   $("input[name='ug-checkbox']").change(function () {
      let limit = document.getElementById("reincidencia").value - 1;
      let cantidadCkb = $("input[name='ug-checkbox']:checked").length;
      if (cantidadCkb > limit) 
      {
         $(this).prop("checked", "");
         alert("Solo puede seleccionar: "+ limit+" veces");
     }
  });
};