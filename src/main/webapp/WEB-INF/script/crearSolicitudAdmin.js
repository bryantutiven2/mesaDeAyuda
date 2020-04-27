limitarChecks();
limitarChecksEncuesta();

/*activar modal en de solicitudes realizadas*/
function myFunction() {
    let option_value = document.getElementById("reincidencia").value;
    if (option_value == "2" || option_value == "3" || option_value == "4") {
        let grupoT = $('input[name=grupo]:checked', '#testForm').val();
        if(grupoT =="mant"){
            $("#myModal").modal();
        }
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
       url: '/mesaayuda/admin/enviarSolicitud',
       data: JSON.stringify(datos),
       success: function(response){
           $("#mensajeModal").empty();
            if(response.status == "success"){
               $("#mensajeModal").html('<i class="fas fa-check-circle" style="color: #02D90C;font-size: 24pt;margin-right: 30px;"></i> Se ha enviado con éxito la solicitud');
               reinciarForm();
               $(".loader").removeClass("hidden"); //remover loader
               $("#modalMensaje").modal();
            }
            else if(response.status == "error"){
                $("#mensajeModal").html('<i class="fas fa-times" style="color: #04A9C8;font-size: 24pt;margin-right: 30px;"></i> No se ha podido enviar la solcitud');
                $(".loader").removeClass("hidden"); //remover loader
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
    let selectSubtipo=document.getElementById("selectSubtipo");
    selectSubtipo.options[0].selected=true;
    let selectTecnico=document.getElementById("selectTecnico");
    selectTecnico.options[0].selected=true;
    let selectTipo=document.getElementById("selectSubtipo");
    selectTipo.options[0].selected=true;
    let estadoSolicitud=document.getElementById("estadoSolicitud_cs");
    estadoSolicitud.options[0].selected=true;
    $('#idUserSolicitaA').val("");
    $('#encuesta_cs').val("");
    $('#fechaInicio_cs').val("");
    $('#fechaFin_cs').val("");
}

/*enviar solicitud desde usuario*/
$( document ).ready(function() {
    $(document).on('click','#enviarSolicitud_CS', function(){
        $("#mensajeModal").empty();
        let datos;
        let grupoS = $('input[name=grupo]:checked', '#testForm').val();
        let descripcionT = $('#descripcionCS').val();
        let nvez = $('#reincidencia').val();
        let subtipoS = $('#selectSubtipo').val();
        let tecnicoS = $('#selectTecnico').val();
        let tipoS = $('#selectTipo').val();
        let usuarioSA = $('#idUserSolicitaA').val();
        let estadoS = $('#estadoSolicitud_cs').val();
        let fInicio = $('#fechaInicio_cs').val();
        let fFin = $('#fechaFin_cs').val();
        let ids = $('#ids_aydudaas').val();
        let iEncuesta = $('#encuesta_cs').val();
        
        if(ids == null || ids == "" || ids == undefined)
            ids = "null";
        
        if(grupoS == undefined){
            $("#mensajeModal").html('<i class="fas fa-exclamation-triangle" style="color: #F87011;font-size: 24pt;margin-right: 30px;"></i> Tiene campos sin llenar');
            $("#modalMensaje").modal(); 
        }
        else if(grupoS=="sist"){
            if(nvez != null && descripcionT != "" && grupoS != undefined 
                    && subtipoS != null && tecnicoS != null && tipoS != null && usuarioSA != "" 
                    && estadoS != null && fInicio != "" && fFin != "" && iEncuesta != ""){
                datos={
                    grupo: grupoS,
                    n_vez: nvez,
                    ids_n_vez: ids,
                    descripcion: descripcionT,
                    idSubtipo: subtipoS,
                    tipo: tipoS, 
                    userTecnico: tecnicoS,
                    userSolicitaAyuda: usuarioSA,
                    estadoSolicitud: estadoS,
                    fechaInicio: fInicio,
                    fechaFin: fFin,
                    idEncuesta: iEncuesta
                };
                $(".loader").addClass("hidden"); //cargar loader
                postAjaxEnviarSolicitud(datos);
            }
            else if(nvez == null || descripcionT == "" || grupoS == undefined 
                    || subtipoS == null || tecnicoS == null || tipoS == null || usuarioSA == "" 
                    || iEncuesta == "" || estadoS == null || fInicio == "" || fFin == ""){
                $("#mensajeModal").html('<i class="fas fa-exclamation-triangle" style="color: #F87011;font-size: 24pt;margin-right: 30px;"></i> Tiene campos sin llenar');
                $("#modalMensaje").modal();
            }
        }
        else if(grupoS=="mant"){
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
            else if(nvez === null || descripcionT == "" || grupoS === undefined){
                $("#mensajeModal").html('<i class="fas fa-exclamation-triangle" style="color: #F87011;font-size: 24pt;margin-right: 30px;"></i> Tiene campos sin llenar');
                $("#modalMensaje").modal();
            }
        }
        
    });
});

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
    $(document).on('click','.cargarTogleUsuarios', function(){
        $("#myModalCS").modal();
    });
});

/*Activar toggle de cargar usuarios en crearSolicitudAdmin.jsp */
$(document).ready(function() {
    $(document).on('click','#encuesta_cs', function(){
        $("#modalEncuesta").modal();
    });
});

/*Activar estilo de tabla*/
$(document).ready(function () {
  $('#dtUsuariosSA').DataTable({
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
});
/*diseño del table solicitudes*/
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
/*Permite cargar el usuario seleccionado en el toggle en crearSolicitudAdmin.jsp*/
$(document).ready(function() {
    $(document).on('click','#cargarIdUSA', function(){
        //limitarChecks();
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

/*Permite cargar la encuesta seleccionado en el toggle en crearSolicitudAdmin.jsp*/
$(document).ready(function() {
    $(document).on('click','#cargarIdEcuesta', function(){
        //limitarChecksEncuesta();
        var selected = '';    
        $('#formItemsEncuesta input[type=checkbox]').each(function(){
            if(this.checked){
                selected += $(this).val();
            } 
        }); 
    document.getElementById("encuesta_cs").value= selected;
    $('#encuesta_cs').attr('value', selected);
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

/*cambiar interfaz dependiendo del tipo de grupo a escoger en crearSolicitudAdmin.jsp*/

$( document ).ready(function() {
    $(document).on('click','#testForm', function(){
        let grupo = $('input[name=grupo]:checked', '#testForm').val();
        let select=document.getElementById("reincidencia");
        let op=select.getElementsByTagName("option");
        let datos;
        if(grupo=="sist"){
            $('.crearAdmin').show();
            $('.idsA').hide();
            select.options[0].selected=true;
            for (var i = 2; i < op.length; i++) {
                $(op[i]).hide();
            }
        }
        else if(grupo=="mant"){
            $('.crearAdmin').hide();
            $('.idsA').show();
            select.options[0].selected=true;
            for (var i = 2; i < op.length; i++) {
                $(op[i]).show();
            }
            datos={
                grupo: grupo
            };
            postAjaxTablaSolicitudes(datos);
        }
    }); 
});

/*limitar numero de checks de usuario solicita ayuda en crearSolicitudAdmin.jsp*/
function limitarChecks() {
   $("input[name='adU-checkbox']").change(function () {
      var limit = 1;
      var cantidadCkb = $("input[name='adU-checkbox']:checked").length;
      if (cantidadCkb > limit) 
      {
         $(this).prop("checked", "");
         alert("Solo puede seleccionar: "+ limit+" usuario");
     }
  });
};

/*limitar numero de checks de encuesta en crearSolicitudAdmin.jsp*/
function limitarChecksEncuesta() {
   $("input[name='encuesta-checkbox']").change(function () {
      var limit = 1;
      var cantidadCkb = $("input[name='encuesta-checkbox']:checked").length;
      if (cantidadCkb > limit) 
      {
         $(this).prop("checked", "");
         alert("Solo puede seleccionar: "+ limit+" encuesta");
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
    document.getElementById("ids_aydudasCS").value= selected.slice(0,(selected.length-1));
    $('#ids_aydudasCS').attr('value', selected.slice(0,(selected.length-1)));
    });         
});  

/*limitar numero de checks en solicitudes*/
function limitarChecksIds() {
   $("input[name='ug-checkbox']").change(function () {
      let limit = document.getElementById("reincidencia").value;
      let cantidadCkb = $("input[name='ug-checkbox']:checked").length;
      if (cantidadCkb > limit) 
      {
         $(this).prop("checked", "");
         alert("Solo puede seleccionar: "+ limit+" veces");
     }
  });
};