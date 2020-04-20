$(document).ready(function () {
    var datosSolicitud = {};
    getAjaxTabla();

    function getAjaxTabla(){
        $.ajax({
        type: "GET",
        url: "/mesaayuda/dashboard/tecnico",
        async: true,
        success: function(result){
            if(result.status == "success"){
                $("#contenedor").empty();
                var bloqueTecnico = '';
                $.each(result.data, function(i, dato){
                    for(let i = 0; i <= dato.length; i++){
                        if(i == 0){
                            if(dato[i].tipo == 'software'){
                                bloqueTecnico += '<div class="card casilla-tecnico scrollbar">'+
                                                '<div class="card-body">'+
                                                '<div class="text-center"><h5><i class="fas fa-user-shield" style="color: #083464; font-size: 1.5em; margin-right: 10px;"></i>'+ dato[i].userTecnico+'</h5></div>'+
                                                '<br>';
                            }
                            else{
                                bloqueTecnico += '<div class="card casilla-tecnico scrollbar">'+
                                                '<div class="card-body">'+
                                                '<div class="text-center"><h5><i class="fas fa-user-cog" style="color: #083464; font-size: 1.5em; margin-right: 10px;"></i>'+ dato[i].userTecnico+'</h5></div>'+
                                                '<br>';
                            } 
                        }
                        if(i < dato.length && dato[i].userSolicitaAyuda != ''){
                            bloqueTecnico +=    '<div class="casilla-UserSA">'+
                                                    '<span><strong>'+dato[i].userSolicitaAyuda+'</strong></span>'+
                                                    '<br><br>'+
                                                    '<div class="form-row">'+
                                                        '<div class="col-9">'+
                                                            '<div class="progress">';
                            if(dato[i].estadoSolicitudTecnico == 'proceso'){
                                bloqueTecnico += '<div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>'+
                                                '</div>'+
                                                '</div>'+
                                                '<div class="col-2">50%</div>';
                            }
                            else if(dato[i].estadoSolicitudTecnico == 'inactiva'){
                                bloqueTecnico += '<div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" style="width: 10%" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div>'+
                                                '</div>'+
                                                '</div>'+
                                                '<div class="col-2">10%</div>';
                            }
                            else if(dato[i].estadoSolicitudTecnico == 'reevaluar'){
                                bloqueTecnico += '<div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>'+
                                                '</div>'+
                                                '</div>'+
                                                '<div class="col-2">75%</div>';
                            }
                            else if(dato[i].estadoSolicitudTecnico == 'finalizada'){
                                bloqueTecnico += '<div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>'+
                                                '</div>'+
                                                '</div>'+
                                                '<div class="col-2">100%</div>';
                            }
                            bloqueTecnico +=   '<div class="col-1 icon-info" id="info-'+dato[i].id+'"><i class="fas fa-info-circle"  style="color: #083464;"></i></div>'+
                                                    '</div>'+
                                                '</div>'+
                                                '<div class="dropdown-divider" ></div>';
                            if(dato[i].id != null){
                                datosSolicitud[dato[i].id] = '<div class="info">'+
                                                                '<p class="resumen"><Strong>Descripción:  </Strong>'+ dato[i].descripcion+'</p>'+
                                                                '<p class="resumen"><strong>Fecha creada:  </strong>'+ dato[i].fechaInicio+'</p>'+
                                                                '<p class="resumen"><strong>Fecha Fin:  </strong>'+ dato[i].fechaFin+'</p>'+
                                                                '<p class="resumen"><strong>Fecha comenzada T.:  </strong>'+ dato[i].fechaInicioTecnico+'</p>'+
                                                                '<p class="resumen"><strong>Fecha Finalizada T.:  </strong>'+ dato[i].fechaFinTecnico+'</p>'+
                                                                '<p class="resumen"><strong>Estado Solicitud:  </strong>'+ dato[i].estadoSolicitudTecnico+'</p>'+
                                                            '</div>';
                            }
                        }
                        if(i == dato.length-1){
                            bloqueTecnico += '</div>'+
                                        '</div>';
                        }
                    }
                });
                $("#contenedor").html(bloqueTecnico);
            }
        },
        error : function(e) {
            $("#contenedor").html("<strong>Error no se ha podido cargar el dashboard</strong>");
            }
        });
    }

   $(document).on('click', '.icon-info', function(){
        let labelId = $(this).attr('id');
        let id = labelId.split('-')[1];
        let datoInfo = datosSolicitud[id];
        $("#mensajeModal").empty();
        $("#mensajeModal").html(datoInfo);
        $("#modalMensaje").modal();
   });
});