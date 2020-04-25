
function getAjaxEncuesta(){
    $.ajax({
    type: "GET",
    url: "/mesaayuda/dashboardEncuesta/cargarencuestas",
    async: true,
    success: function(result){
        if(result.status == "success"){
            $("#bodyTableEncuesta").empty();
            var tr = '';
            $.each(result.data, function(i, dato){
                tr += "<tr>"+
                            "<th class='idsolicitud' scope='row'>"+dato.id+"</th>"+
                            "<td>"+dato.nombre+"</td>"+
                            "<td>"+dato.descripcion+"</td>"+
                            "<td>"+dato.codigoEmbebido+"</td>"+
                            "<td>"+dato.codigoRegistro+"</td>"+
                            "<td>"+
                                "<div class='text-center'>"+
                                    "<div class='btn-group'>"+
                                        "<button class='btn btn-primary btn-sm btnEditar'>"+
                                            "<i class='fas fa-edit'></i>"+
                                        "</button>"+
                                    "</div>"+
                                    "<div class='btn-group'>"+
                                        "<button class='btn btn-danger btn-sm btnBorrar'>"+
                                            "<i class='fas fa-trash'></i>"+
                                        "</button>"+
                                    "</div>"+
                                "</div>"+
                            "</td>"+
                        "</tr>";
            });
            $("#bodyTableEncuesta").html(tr);
        }
    },
    error : function(e) {
            $("#bodyTableEncuesta").html("<strong>Error</strong>");
        }
    });
}

function postAjaxCrearEncuesta(datos){
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

$(document).on('click', '#agregarEncuesta', function(){
    let selectEstado=document.getElementById("estadoEC");
    selectEstado.options[0].selected=true;
    $('#codigoEC').val("");
    $('#nombreEC').val("");
    $('#descripcionEC').val("");
    $('#codigoEmbebid').val("");
    $('#codigoREC').val("");
    $('#grupoCod').hide();
    $('#modalCrearEncuesta').modal();
});
$(document).ready(function(){
    $("#formEncuesta").submit(function(e){
        e.preventDefault();
        let datos;
        let nombreE = $('#nombreEC').val();
        let descripcionE = $('#descripcionEC').val();
        let codEmbebidoE = $('#codigoEmbebid').val();
        let codRegistroE = $('#codigoREC').val();
        let estadoE = $('#estadoEC').val();
        let codE = $('#codigoEC').val();
        if(codE == ''){
            datos = {
                nombre: nombreE,
                descripcion: descripcionE,
                codigo_Embebido: codEmbebidoE,
                codigo_registro: codRegistroE,
                estado_encuesta: estadoE
            }
            //postAjaxCrearEncuesta(datos);
        }
        else if(codE != ''){
            datos = {
                id_encuesta: codE,
                nombre: nombreE,
                descripcion: descripcionE,
                codigo_Embebido: codEmbebidoE,
                codigo_registro: codRegistroE,
                estado_encuesta: estadoE
            }
            console.log(codE)
            //postAjaxActualizarEncuesta(datos)
        }
        $("#modalCrearEncuesta").modal('hide');
    });
    $(document).on('click', '.btnEditar', function(){
            $('#codigoEC').val($(this).parents("tr").find("td")[0].innerHTML);
            $('#nombreEC').val($(this).parents("tr").find("td")[1].innerHTML);
            $('#descripcionEC').val($(this).parents("tr").find("td")[2].innerHTML);
            $('#codigoEmbebid').val($(this).parents("tr").find("td")[3].innerHTML);
            $('#codigoREC').val($(this).parents("tr").find("td")[4].innerHTML);
            $('#grupoCod').show();
            $("#modalCrearEncuesta").modal();
    });
});