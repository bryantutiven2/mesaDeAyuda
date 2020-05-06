
function postAjaxTabla(datos){
    $.ajax({
       type: 'POST',
       contentType: 'application/json',
       url: '/mesaayuda/tecnico/actualizarSolicitud',
       data: JSON.stringify(datos),
       success: function(response){
            if(response.status == "proceso"){
               getAjaxTabla();
               $("#mensajePost").html("Ha comenzado a trabajar en la solicitud");
            }
            if(response.status == "finalizado"){
               $(".loader").removeClass("hidden"); //remover loader
               getAjaxTabla();
               $("#mensajePost").html("Ha culminado con está solicitud");
            }
            else if(response.status == "error"){
                $(".loader").removeClass("hidden"); //remover loader
                $("#mensajePost").html("No se ha podido comenzar a trabajar en la solicitud");
            }
            $("#procesoSolicitud").modal();
        }
    });
}

function getAjaxTabla(){
    $.ajax({
    type: "GET",
    url: "/mesaayuda/tecnico/cargarSolicitudes",
    async: true,
    success: function(result){
        if(result.status == "success"){
            $("#bodyTableCargarSolicitudes").empty();
            var tr = '';
            $.each(result.data, function(i, dato){
                tr += "<tr>"+
                         "<th class='idsolicitud' style='font-size: 0.8em' scope='row'>"+dato.id+"</th>"+
                         "<td style='max-width: 260px; font-size: 0.8em; text-align: justify'>"+dato.descripcion+"</td>"+
                         "<td style='font-size: 0.8em'>"+dato.fechaInicio+"</td>"+
                         "<td style='font-size: 0.8em'>"+dato.fechaFin+"</td>"+
                         "<td style='font-size: 0.8em'>"+dato.userSolicitaAyuda+"</td>";
                if(dato.estadoSolicitudTecnico == "inactiva"){
                    tr+='<td class="text-center">'+
                            '<button type="button" class="btn btn-success btn-sm comenzarSolicitud" style=" font-size: 0.8em;" '+' value="'+dato.id+'"><i class="far fa-edit"></i> Comenzar</button>'+
                        '</td>';
                }
                else if(dato.estadoSolicitudTecnico == "proceso"){
                         tr+='<td class="text-center">'+
                                 '<button type="button" class="btn btn-info btn-sm finalizarSolicitud" style=" font-size: 0.8em;" '+' value="'+dato.id+'"><i class="far fa-times-circle"></i> Procesar</button>'+
                             '</td>';
                }
                else{
                    tr+='<td class="text-center"></td>';
                }
                tr += '<td class="text-center">'+
                        '<button type="button" class="btn btn-info btn-sm crearObservacion" style=" font-size: 0.8em;" '+' value="'+dato.id+'"><i class="fas fa-envelope-open-text"></i></button>'+
                    '</td>';
                tr+="</tr>";
            });
            $("#bodyTableCargarSolicitudes").html(tr);
        }
        else if(result.status == "vacio"){
            $("#bodyTableCargarSolicitudes").html("<strong>No tiene solicitudes a gestionar</strong>");
        }
    },
    error : function(e) {
            $("#bodyTableCargarSolicitudes").html("<strong>Error</strong>");
        }
    });
}

/*Activar modal de observaciones*/
$(document).ready(function() {
    $(document).on('click','.crearObservacion', function(){
        //let codSol = $(this).parents("tr").find("th")[0].innerHTML;
        /*let datos = {
            idSolicitud: codSol
        };*/
        //$(".loader").addClass("hidden");
        //postCargarObservaciones(datos);
        $("#modalObservacion").modal();
    });
});

$(document).ready(function () {
    $(document).on('click','.comenzarSolicitud', function(){
        let cod;
        $(this).parents("tr").find(".idsolicitud").each(function() {
            cod = $(this).html();
        });
        var datos = {
            id: cod,
            estadoSolicitudTecnico: 'proceso',
            estadoSolicitud: 'null'
            };
        postAjaxTabla(datos);
    });
});

$(document).ready(function () {
    getAjaxTabla();
    $(document).on('click','#recargartabla', function(){
        getAjaxTabla();
        console.log("se recargo la tabla");
    });
});

$(document).ready(function () {
    $(document).on('click','.finalizarSolicitud', function(){
        let cod;
        $(this).parents("tr").find(".idsolicitud").each(function() {
            cod = $(this).html();
        });
        localStorage.setItem("idSolicitud",cod);
        $("#finalSolicitudModal").modal();
    });
});
$(document).ready(function () {
    
    $(document).on('click','#finalSolicitud', function(e){
        
    //$('#finalSolicitud').click(function(){
        let cod = localStorage.getItem("idSolicitud");
        let descripcion = $("#descripcion_st").val();
        let subtipo = document.getElementById("selectSubtipo").value;
        if(descripcion == "" || subtipo == ""){
            $("#mensajeModal").html('<i class="fas fa-exclamation-triangle" style="color: #F87011;font-size: 24pt;margin-right: 30px;"></i> Tiene campos sin llenar');
            $("#modalMensaje").modal();
        }
        else if(descripcion != "" || subtipo != ""){
            let datos = {
                id: cod,
                estadoSolicitudTecnico: 'finalizada',
                descripcionTecnico: descripcion,
                estadoSolicitud: 'finalizada',
                idSubtipo: subtipo
                };
            let selectSubTipo=document.getElementById("selectSubtipo");
            selectSubTipo.options[0].selected=true;
            $("#descripcion_st").val("");
            $(".loader").addClass("hidden"); //cargar loader
            $("#finalSolicitudModal").modal('hide');
            postAjaxTabla(datos);
        }
        
    });
});
$(document).ready(function () {
    $(document).on('click','#reevaluarSolicitud', function(){
    //$('#reevaluarSolicitud').click(function(){
        let cod = localStorage.getItem("idSolicitud");
        let descripcion = $("#descripcion_st").val();
        let subtipo = document.getElementById("selectSubtipo").value;
        if(descripcion == "" || subtipo == ""){
            $("#mensajeModal").html('<i class="fas fa-exclamation-triangle" style="color: #F87011;font-size: 24pt;margin-right: 30px;"></i> Tiene campos sin llenar');
            $("#modalMensaje").modal();
        }
        else if(descripcion != "" || subtipo != ""){
            let datos = {
                id: cod,
                estadoSolicitudTecnico: 'reevaluar',
                descripcionTecnico: descripcion,
                estadoSolicitud: 'reevaluar',
                idSubtipo: subtipo
                };
            let selectSubTipo=document.getElementById("selectSubtipo");
            selectSubTipo.options[0].selected=true;
            $("#descripcion_st").val("");
            $("#finalSolicitudModal").modal('hide');
            postAjaxTabla(datos);
        }
        
    });
});


