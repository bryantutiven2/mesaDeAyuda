function getAjaxTabla(){
    $.ajax({
    type: "GET",
    url: "/mesaayuda/admin/cargarNuevasSolicitudes",
    async: true,
    success: function(result){
        if(result.status == "success"){
            $("#bodyTableCargarNuevasSolicitudes").empty();
            var tr = '';
            $.each(result.data, function(i, dato){
                tr += "<tr>"+
                        '<th class="id_sn" scope="row" style="font-size: 0.8em">'+dato.id+'</th>'+
                        '<td class="descrip_sn" style="font-size: 0.8em; max-width: 150px;overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">'+dato.descripcion+'</td>'+
                        '<td class="user_sn" style="font-size: 0.8em">'+dato.userSolicitaAyuda+'</td>'+
                        '<td class="descripTecnico_sn" style="font-size: 0.8em; max-width: 150px;overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">'+dato.descripcionTecnico+'</td>'+
                        '<td class="tecnico_sn" style="font-size: 0.8em">'+dato.userTecnico+'</td>'+
                        '<td class="n_sn" style="font-size: 0.8em; max-width: 85px">'+dato.n_vez+'</td>'+
                        '<td class="ids_sn" style="font-size: 0.8em">'+dato.ids_n_vez+'</td>'+
                        '<td class="fechaInicio_sn" style="font-size: 0.8em">'+dato.fechaInicio+'</td>';
                if(dato.estadoSolicitud == "pendiente"){
                    tr+='<td class="text-center">'+
                            '<button type="button" class="btn btn-success btn-sm asignarSolicitud" value="'+dato.id+'"><i class="far fa-edit"></i> Asignar</button>'+
                        '</td>';
                }
                else if(dato.estadoSolicitud == "reevaluar"){
                         tr+='<td class="text-center">'+
                                 '<button type="button" class="btn btn-info btn-sm finalizarSolicitud"'+' value="'+dato.id+'"><i class="far fa-times-circle"></i> Reevaluar</button>'+
                             '</td>';
                }
                else{
                    tr+='<td class="text-center"></td>';
                }
                tr+="</tr>";
            });
            $("#bodyTableCargarNuevasSolicitudes").html(tr);
            cargarEstiloT();
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
            if(response.status == "asignado"){
               getAjaxTabla();
               $("#mensajePost").html("La solicitud se ha asigando exitosamente");
            }
            if(response.status == "finalizado"){
               getAjaxTabla();
               $("#mensajePost").html("La solicitud se ha finalizado exitosamente");
            }
            else if(response.status == "error"){
                $("#mensajePost").html("ERROR - No se ha actualizado la solicitud");
            }
            $("#procesoSolicitud").modal();
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
    $(document).on('click','#enviarSActualizada', function(){
        let idD = document.getElementById("codigo_sn").value;
        let tipoD = document.getElementById("selectTipo").value;
        let fechaFinD = document.getElementById("fechaFin_sn").value;
        let userTecnicoD = document.getElementById("selectTecnico").value;
        let datos ={
            id : idD,
            tipo: tipoD,
            userTecnico: userTecnicoD,
            fechaFin: fechaFinD,
            estadoSolicitud: 'asignada'
            };
        var selectTecnico=document.getElementById("selectTecnico");
        selectTecnico.options[0].selected=true;
        var selectTipo=document.getElementById("selectTipo");
        selectTipo.options[0].selected=true;
        $("#fechaFin_sn").val("");
        postAjaxTabla(datos);
    });
});

/*finalizar solicitud*/
$(document).ready(function () {
    $(document).on('click','.finalizarSolicitud', function(){
        let cod;
        $(this).parents("tr").find(".idsolicitud").each(function() {
            cod = $(this).html();
        });
        var datos = {
            id : cod,
            estadoSolicitud: 'finalizada'
        };
        postAjaxTabla(datos);
    });
});

/*Permite obtener una fila seleccionada y cargarla en el toggle para asignar un solicitud  un tecnico en solicitudesNuevasAdmin.jsp*/
$(document).ready(function() {
    $(document).on('click','.asignarSolicitud', function(){
        
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

function cargarEstiloT() {
  $('#tablaNuevaS').DataTable({
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


