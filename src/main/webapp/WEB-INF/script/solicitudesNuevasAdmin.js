limitarChecksEncuesta();
function getAjaxTabla(){
    $.ajax({
    type: "GET",
    url: "/mesaayuda/admin/cargarNuevasSolicitudes",
    async: true,
    success: function(result){
        if(result.status == "success"){
            cargarEstiloT();
            let tableBody = $('#tablaNuevaS').DataTable();
            $.each(result.data, function(i, dato){
                let tr = '<tr>'+
                                '<td class="id_sn" scope="row" style="font-size: 0.8em">'+dato.id+'</td>'+
                                '<td class="descrip_sn" style="font-size: 0.8em; max-width: 120px;overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">'+dato.descripcion+'</td>'+
                                '<td class="user_sn" style="font-size: 0.8em">'+dato.userSolicitaAyuda+'</td>'+
                                '<td class="tecnico_sn" style="font-size: 0.8em">'+dato.userTecnico+'</td>'+
                                '<td class="n_sn" style="font-size: 0.8em; max-width: 85px">'+dato.n_vez+'</td>'+
                                '<td class="ids_sn" style="font-size: 0.8em">'+dato.ids_n_vez+'</td>'+
                                '<td class="fechaInicio_sn" style="font-size: 0.8em">'+dato.fechaInicio+'</td>';
                        
                if(dato.estadoSolicitud == "pendiente"){
                          tr += '<td class="text-center">'+
                                    '<button type="button" class="btn btn-success btn-sm asignarSolicitud" style=" font-size: 0.8em;" value="'+dato.id+'"><i class="far fa-edit"></i> Asignar</button>'+
                                '</td>'+
                                '<td class="text-center">'+
                                    '<button type="button" class="btn btn-info btn-sm crearObservacion" style=" font-size: 0.8em;" '+' value="'+dato.id+'"><i class="fas fa-envelope-open-text"></i></button>'+
                                '</td>'+
                              '</tr>';    
                    tableBody.row.add($(tr)).draw();
                }
                else if(dato.estadoSolicitud == "reevaluar"){
                          tr += '<td class="text-center">'+
                                    '<button type="button" class="btn btn-warning btn-sm reevaluarSolicitud" style=" font-size: 0.7em;" '+' value="'+dato.id+'"><i class="far fa-times-circle"></i> Reevaluar</button>'+
                                '</td>'+
                                '<td class="text-center">'+
                                    '<button type="button" class="btn btn-info btn-sm crearObservacion" style=" font-size: 0.8em;" '+' value="'+dato.id+'"><i class="fas fa-envelope-open-text"></i></button>'+
                                '</td>'+
                              '</tr>';    
                    tableBody.row.add($(tr)).draw();
                }
                else{
                         tr += '<td class="text-center"></td>'+
                                '<td class="text-center">'+
                                    '<button type="button" class="btn btn-info btn-sm crearObservacion" style=" font-size: 0.8em;" '+' value="'+dato.id+'"><i class="fas fa-envelope-open-text"></i></button>'+
                                '</td>'+
                              '</tr>';    
                    tableBody.row.add($(tr)).draw();
                }
            });
        }
    },
    error : function(e) {
            $("#bodyTableCargarNuevasSolicitudes").html("<strong>Error</strong>");
        }
    });
}

function postAjaxTabla(datos){
    $.ajax({
       type: 'POST',
       contentType: 'application/json',
       url: '/mesaayuda/admin/actualizarSolicitud',
       data: JSON.stringify(datos),
       success: function(response){
           $("#myModalNuevaSolicitud").modal('hide');
           $("#mensajePost").empty();
            if(response.status == "asignado"){
               reiniciarForm();
               getAjaxTabla();
               $(".loader").removeClass("hidden"); //remover loader
               $("#mensajePost").html("La solicitud se ha asigando exitosamente");
            }
            if(response.status == "finalizado"){
               reiniciarForm();
               getAjaxTabla();
               $(".loader").removeClass("hidden"); //remover loader
               $("#mensajePost").html("La solicitud se ha finalizado exitosamente");
            }
            else if(response.status == "error"){
                $(".loader").removeClass("hidden"); //remover loader
                $("#mensajePost").html("ERROR - No se ha actualizado la solicitud");
            }
            $("#procesoSolicitud").modal();
        }
    });
}

function postCargarObservaciones(datos){
    $.ajax({
       type: 'POST',
       contentType: 'application/json',
       url: '/mesaayuda/observaciones/cargar',
       data: JSON.stringify(datos),
       success: function(response){
            if(response.status == "success"){
               $(".loader").removeClass("hidden"); //remover loader
               $("#listaObservaciones").empty();
                let listaO = '';
                $.each(response.data, function(i, dato){
                    listaO += '<div class="form-row">'+
                                    '<div class="form-group col-12">'+
                                        '<label style="color: #054182;"> <i>'+ dato.usuario+':</i></label>'+
                                        '<div style="font-size: 0.85em;">'+ dato.mensaje+'</div>'+
                                    '</div>'+
                                '</div>';
                });
                $("#listaObservaciones").html(listaO);
                $("#modalObservacion").modal();
            }
            else if(response.status == "error"){
                $(".loader").removeClass("hidden"); //remover loader
                $("#mensajePost").html("ERROR - No se ha actualizado la solicitud");
            }
            else
                $(".loader").removeClass("hidden"); //remover loader
        }
    });
}

$(document).ready(function () {
    getAjaxTabla();
    $(document).on('click','#recargartabla', function(){
        getAjaxTabla();
    });
});

/*DateTimePicker de fecha fin para solicitudesNuevasAdmin.jsp*/
$(function () {
    $('#dtPicker').datetimepicker({
        format: "DD/MM/YYYY hh:mm a"
    });
});

/* Asignar solicitud*/
$(document).ready(function() {
    $("#formNsolicitudA").submit(function(e){
        e.preventDefault();
        let idD = document.getElementById("codigo_sn").value;
        let tipoD = document.getElementById("selectTipo").value;
        let fechaFinD = document.getElementById("fechaFin_sn").value;
        let userTecnicoD = document.getElementById("selectTecnico").value;
        let iEncuesta = $('#encuesta_cs').val();
        let datos ={
            id : idD,
            tipo: tipoD,
            userTecnico: userTecnicoD,
            fechaFin: fechaFinD,
            estadoSolicitud: 'asignada',
            idEncuesta: iEncuesta
            };
        $(".loader").addClass("hidden");
        postAjaxTabla(datos);
    });
});

/*reinciair form de actualizar solciitud*/
function reiniciarForm(){
    let selectTecnico=document.getElementById("selectTecnico");
    selectTecnico.options[0].selected=true;
    let selectTipo=document.getElementById("selectTipo");
    selectTipo.options[0].selected=true;
    $("#fechaFin_sn").val("");
    $('#encuesta_cs').val("");
    $('#formItemsEncuesta input[type=checkbox]').each(function(){
        if(this.checked){
            $(this).prop("checked", false);
        } 
    });
};
/*finalizar solicitud*/
$(document).ready(function () {
    $(document).on('click','.reevaluarSolicitud', function(){
        llenarCamposModal(this);
    });
});


/*Activar toggle de cargar encuestas en crearSolicitudAdmin.jsp */
$(document).ready(function() {
    $(document).on('click','#encuesta_cs', function(){
        $("#modalEncuestaNA").modal();
    });
});

/*Permite cargar la encuesta seleccionado en el toggle en crearSolicitudAdmin.jsp*/
$(document).ready(function() {
    $(document).on('click','#cargarIdEcuesta', function(){
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

/*Permite obtener una fila seleccionada y cargarla en el toggle para asignar un solicitud  un tecnico en solicitudesNuevasAdmin.jsp*/
$(document).ready(function() {
    $(document).on('click','.asignarSolicitud', function(){
        llenarCamposModal(this);
    });
    
});

/*Funcion cargar modal de modificar solicitud llenando los campos del mismo modal*/
function llenarCamposModal(objeto){
    $(objeto).parents("tr").find(".id_sn").each(function() {
        document.getElementById("codigo_sn").value = $(this).html();
    });
    $(objeto).parents("tr").find(".descrip_sn").each(function() {
        document.getElementById("descripcion_sn").value = $(this).html();
    });
    $(objeto).parents("tr").find(".user_sn").each(function() {
        document.getElementById("usuario_sn").value = $(this).html();
    });
    $(objeto).parents("tr").find(".n_sn").each(function() {
        document.getElementById("n_vez_sn").value = $(this).html();
    });
    $(objeto).parents("tr").find(".ids_sn").each(function() {
        document.getElementById("idsn_vez_sn").value = $(this).html();
    });
    $(objeto).parents("tr").find(".fechaInicio_sn").each(function() {
        document.getElementById("fechaInicio_sn").value = $(this).html();
    });
    $("#myModalNuevaSolicitud").modal();
}

function cargarEstiloT() {
  let tableBody = $('#tablaNuevaS').DataTable({
      retrieve: true,
      //"searching": false,
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
    tableBody.clear().draw();
};


