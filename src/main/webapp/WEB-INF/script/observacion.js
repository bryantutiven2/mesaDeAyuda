
function postCargarObservaciones(datos){
    $.ajax({
       type: 'POST',
       contentType: 'application/json',
       url: '/mesaayuda/observacion/cargarObservaciones',
       data: JSON.stringify(datos),
       success: function(response){
            if(response.status == "success"){
                $("#listaObservaciones").empty();
                let div = '';
                $.each(response.data, function(i, dato){
                    div += '<div class="form-row">'+
                                '<div class="form-group col-12">'+
                                    '<label style="color: #054182;"> <i>'+ dato.usuario +':</i></label>'+
                                    '<div style="font-size: 0.85em;">'+dato.mensaje +'</div>'+
                                    '<div style="font-size: 0.8em;;text-align: right;"> <small class="form-text text-muted"><i>'+dato.fecha+'</i></small></div>'+
                                '</div>'+
                            '</div>';
                });
                $("#listaObservaciones").html(div);
            }
            $(".loader").removeClass("hidden"); //remover loader
            
            $("#modalObservacion").modal();
        }
    });
}

function postCrearObservacion(datos){
    $.ajax({
       type: 'POST',
       contentType: 'application/json',
       url: '/mesaayuda/observacion/crearObservacion',
       data: JSON.stringify(datos),
       success: function(response){
            let fhActual = moment().format('DD/MM/YYYY hh:mm a');
            if(response.status == "exito"){
                let div = '<div class="form-row">'+
                                '<div class="form-group col-12">'+
                                    '<label style="color: #054182;"> <i> Yo:</i></label>'+
                                    '<div style="font-size: 0.85em;">'+datos.mensaje +'</div>'+
                                    '<div style="font-size: 0.8em;;text-align: right;"> <small class="form-text text-muted"><i>'+fhActual+'</i></small></div>'+
                                '</div>'+
                            '</div>';
                $("#listaObservaciones").append(div);
                $('#observacionM').val("");
            }
        }
    });
}

/*Activar modal de observaciones*/
$(document).ready(function() {
    var codigoSolicitud;
    $(document).on('click','.crearObservacion', function(){
        codigoSolicitud = $(this).parents("tr").find("td")[0].innerHTML;
        let datos = {
            id: codigoSolicitud
        };
        $(".loader").addClass("hidden");
        postCargarObservaciones(datos);
    });
    
    $("#formObservacion").submit(function(e){
        e.preventDefault();
        let mensajeI = $('#observacionM').val();
        let datos = {
            id: codigoSolicitud,
            mensaje: mensajeI
        };
        postCrearObservacion(datos);
    });
    
});