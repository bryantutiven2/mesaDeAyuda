
function getAjaxEncuesta(){
    $.ajax({
    type: "GET",
    url: "/mesaayuda/dashboardEncuesta/cargarEncuestas",
    async: true,
    success: function(result){
        if(result.status == "success"){
            $("#bodyTableEncuesta").empty();
            var tr = '';
            $.each(result.data, function(i, dato){
                tr += "<tr>"+
                            "<th class='idsolicitud' scope='row'>"+dato.idEncuesta+"</th>"+
                            "<td>"+dato.nombre+"</td>"+
                            "<td>"+dato.descripcion+"</td>"+
                            "<td style='display: none;'>"+dato.codigoEmbebido+"</td>"+
                            "<td>"+dato.codigoRegistro+"</td>";
                    if(dato.estadoBorrado == 0)
                        tr += "<td> Activa</td>";
                    else if(dato.estadoBorrado == 1)
                        tr += "<td> Inactiva</td>";
                    
                tr += "<td>"+
                        "<div class='text-center'>"+
                            "<div class='btn-group'>"+
                                "<button class='btn btn-primary btn-sm btnEditar'>"+
                                    "<i class='fas fa-edit'></i> Editar"+
                                "</button>"+
                            "</div>"+
                        "</div>"+
                    "</td>"+
                "</tr>";
            });
            $("#bodyTableEncuesta").html(tr);
        }
        if(result.status == "vacio"){
            $("#bodyTableEncuesta").html('<strong>No hay encuestas</strong>');
        }
    },
    error : function(e) {
            $("#bodyTableEncuesta").html("<strong>Error</strong>");
        }
    });
}

function postAjaxCrearEncuesta(datos){
    console.log(datos);
    $.ajax({
       type: 'POST',
       contentType: 'application/json',
       url: '/mesaayuda/dashboardEncuesta/crearEncuesta',
       data: JSON.stringify(datos),
       success: function(response){
            $("#mensajeModal").empty();
            if(response.status == "exito"){
               getAjaxEncuesta();
               $("#mensajeModal").html('<i class="fas fa-check-circle" style="color: #02D90C;font-size: 24pt;margin-right: 30px;"></i> Se ha creado la encuesta');
               $("#modalMensaje").modal();
            }
            else if(response.status == "error"){
                $("#mensajeModal").html('<i class="fas fa-times" style="color: #04A9C8;font-size: 24pt;margin-right: 30px;"></i> No se ha podido crear la encuesta');
                $("#modalMensaje").modal();
            }
        }
    });
}

function postAjaxActualizarEncuesta(datos){
    console.log(datos);
    $.ajax({
       type: 'POST',
       contentType: 'application/json',
       url: '/mesaayuda/dashboardEncuesta/actualizarEncuesta',
       data: JSON.stringify(datos),
       success: function(response){
            $("#mensajeModal").empty();
            if(response.status == "exito"){
               getAjaxEncuesta();
               $("#mensajeModal").html('<i class="fas fa-check-circle" style="color: #02D90C;font-size: 24pt;margin-right: 30px;"></i> Se ha actualizado la encuesta');
               $("#modalMensaje").modal();
            }
            else if(response.status == "error"){
                $("#mensajeModal").html('<i class="fas fa-times" style="color: #04A9C8;font-size: 24pt;margin-right: 30px;"></i> No se ha podido actualizar la encuesta');
                $("#modalMensaje").modal();
            }
        }
    });
}


$(document).ready(function(){
    getAjaxEncuesta();
    $(document).on('click', '#agregarEncuesta', function(){
        let selectEstado=document.getElementById("estadoEC");
        selectEstado.options[0].selected=true;
        $('#codigoEC').val("");
        $('#nombreEC').val("");
        $('#descripcionEC').val("");
        $('#codigoEmbebido').val("");
        $('#codigoREC').val("");
        $('#grupoCod').hide();
        $('#modalCrearEncuesta').modal();
    });

    $("#formEncuesta").submit(function(e){
        e.preventDefault();
        let datos;
        let nombreE = $('#nombreEC').val();
        let descripcionE = $('#descripcionEC').val();
        let codEmbebidoE = $('#codigoEmbebido').val();
        let codRegistroE = $('#codigoREC').val();
        let estadoE = $('#estadoEC').val();
        let codE = $('#codigoEC').val();
        if(codE == ''){
            datos = {
                nombre: nombreE,
                descripcion: descripcionE,
                codigoEmbebido: codEmbebidoE,
                codigoRegistro: codRegistroE,
                estadoBorrado: estadoE
            };
            postAjaxCrearEncuesta(datos);
        }
        else if(codE != ''){
            datos = {
                idEncuesta: codE,
                nombre: nombreE,
                descripcion: descripcionE,
                codigoEmbebido: codEmbebidoE,
                codigoRegistro: codRegistroE,
                estadoBorrado: estadoE
            };
            postAjaxActualizarEncuesta(datos);
        }
        $("#modalCrearEncuesta").modal('hide');
    });
    $(document).on('click', '.btnEditar', function(){
            $('#codigoEC').val($(this).parents("tr").find("th")[0].innerHTML);
            $('#nombreEC').val($(this).parents("tr").find("td")[0].innerHTML);
            $('#descripcionEC').val($(this).parents("tr").find("td")[1].innerHTML);
            $('#codigoEmbebido').val($(this).parents("tr").find("td")[2].innerHTML);
            $('#codigoREC').val($(this).parents("tr").find("td")[3].innerHTML);
            let selectEstado=document.getElementById("estadoEC");
            selectEstado.options[0].selected=true;
            $('#grupoCod').show();
            $("#modalCrearEncuesta").modal();
    });
});