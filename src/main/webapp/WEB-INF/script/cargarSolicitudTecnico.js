
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
                         "<td class='idsolicitud' scope='row'>"+dato.id+"</td>"+
                         "<td>"+dato.descripcion+"</td>"+
                         "<td>"+dato.fechaInicio+"</td>"+
                         "<td>"+dato.fechaFin+"</td>"+
                         "<td>"+dato.userSolicitaAyuda+"</td>"+
                         "<td style='display: none'>"+dato.tipo+"</td>";
                if(dato.estadoSolicitudTecnico == "inactiva"){
                    tr+='<td class="text-center">'+
                            '<button type="button" class="btn btn-success btn-sm comenzarSolicitud" style=" font-size: 0.75em;" '+' value="'+dato.id+'"><i class="far fa-edit"></i> Comenzar</button>'+
                        '</td>';
                }
                else if(dato.estadoSolicitudTecnico == "proceso"){
                         tr+='<td class="text-center">'+
                                 '<button type="button" class="btn btn-info btn-sm finalizarSolicitud" style=" font-size: 0.75em;" '+' value="'+dato.id+'"><i class="far fa-times-circle"></i> Procesar</button>'+
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
        if(op[i].value == 'none'){
            op[i].style.display="block";
        }
    }
}; 

$(document).ready(function () {
    $(document).on('click','.comenzarSolicitud', function(){
        let cod;
        $(this).parents("tr").find(".idsolicitud").each(function() {
            cod = $(this).html();
        });
        let datos = {
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
    });
});

$(document).ready(function () {
    $(document).on('click','.finalizarSolicitud', function(){
        let cod;
        $(this).parents("tr").find(".idsolicitud").each(function() {
            cod = $(this).html();
        });
        let idTip = $(this).parents("tr").find("td")[5].innerHTML;
        filtrarSubtipo(idTip);
        localStorage.setItem("idSolicitud",cod);
        $("#finalSolicitudModal").modal();
    });
});
$(document).ready(function () {
    
    $(document).on('click','#finalSolicitud', function(e){
        
    //$('#finalSolicitud').click(function(){
        let cod = localStorage.getItem("idSolicitud");
        let descripcion = $("#descripcion_st").val();
        let st = document.getElementById("selectSubtipo").value;
        let subtipo;
        if(st != 'none' )
            subtipo = st.split('-')[1];
        else
            subtipo = st;
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
        let st = document.getElementById("selectSubtipo").value;
        let subtipo
        if(st != 'none' )
            subtipo = st.split('-')[1];
        else
            subtipo = st;
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


