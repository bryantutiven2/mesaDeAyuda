/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    getAjaxEncuesta();
    var datoEncuesta ={}
    var codRegistro;
    var idSoli;
    function getAjaxEncuesta(){
        $.ajax({
        type: "GET",
        url: "/mesaayuda/notificacionEncuesta/pendiente",
        async: true,
        success: function(result){
            let cont = 0;
            if(result.status == "success"){
                $("#encuestaMenu").empty();
                var tr = '';
                $.each(result.data, function(i, dato){
                    datoEncuesta[dato.idSolicitud] = [dato.codigoEmbebido, dato.codigoRegistro, dato.idEncuesta]
                    tr += '<li>'+
                                '<a class="dropdown-item item-a" href="#" id="'+dato.idSolicitud+'">'+
                                     '<div style="max-width: 300px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">'+
                                         '<i class="fas fa-clipboard" style="margin-right: 5px"></i><span>Solicitud: '+dato.idSolicitud+', "'+dato.nombre+'"</span>'+
                                     '</div>'+
                                '</a>'+
                            '</li>'+
                           '<li class="divider"></li>';
                   cont++;
                });
                $("#encuestaMenu").html(tr);
                $('#cont-notificacion').text(cont);
                
            }
            else if(result.status == "vacio"){
                $('#cont-notificacion').text(0);
                $("#encuestaMenu").html("<strong>No tiene encuestas a llenar</strong>");
            }
            /*valiacion de numero de encuestas para bloquear boton de crear solicitud si tiene encuestas pendientes*/
            let contadorN =  $('#cont-notificacion').text();
            let tipoRolU = $('#tipoRolU').val();
            if(contadorN > 0 && tipoRolU != 'tecnico' && tipoRolU != 'admin' && tipoRolU != undefined){
                $("#enviarSolicitud").prop('disabled',true);
                $("#mensajeModal").html('<Strong>Usted tiene encuestas pendientes. Por favor proceda a llenarlas!!!</strong>');
                $("#modalMensaje").modal();
            }
            else if(contadorN == 0){
                $("#enviarSolicitud").prop('disabled',false);
            }
        },
        error : function(e) {
                $("#encuestaMenu").html("<strong>Error</strong>");
            }
        });
        
    }
    function postAjaxConfirmar(datosC){
        $.ajax({
           type: 'POST',
           contentType: 'application/json',
           url: '/mesaayuda/notificacionEncuesta/actualizarNEncuesta',
           data: JSON.stringify(datosC),
           success: function(response){
                if(response.status == "exito"){
                   getAjaxEncuesta();
                   $("#mensajeModalNotificacion").html("Ha culminado la encuesta");
                   $('#modalConfirmar').modal('hide');
                   $('#modalEncuesta').modal('hide');
                   
                }
                else if(response.status == "error"){
                    $("#mensajeModalNotificacion").html("No se ha podido culminar la encuesta");
                    $('#modalConfirmar').modal('hide');
                    $('#modalEncuesta').modal('hide');
                }
                $("#modalMensajeNotificacion").modal();
            }
        });
    }

    $(document).on('click', '.item-a', function(){
        let idSolicitud = $(this).attr('id');
        let codEmbebido = datoEncuesta[idSolicitud][0];
        codRegistro = datoEncuesta[idSolicitud][1];
        idSoli = idSolicitud;
        $("#mensajeEncuesta").html(codEmbebido);
        $('#modalEncuesta').modal();
        let iframeE = document.getElementsByTagName('iframe')[0];
        $(iframeE).css('width', '100%');
    });
    
    $(document).on('click','#aceptarET', function(){
        $('#modalConfirmar').modal();
    });
    
    $("#formCR").submit(function(e){
        e.preventDefault();
        let cod = $('#idRegistroSol').val();
        if(cod == codRegistro){
            let datosC ={
                idSolicitud: idSoli,
                estadoBorrado: 1
            }
            postAjaxConfirmar(datosC);
        }
    });
    
});


