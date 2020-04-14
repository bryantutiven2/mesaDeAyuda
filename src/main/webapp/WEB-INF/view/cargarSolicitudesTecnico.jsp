<%-- 
    Document   : consultarSolicitudes
    Created on : 13/03/2020, 12:06:34
    Author     : bryan
--%>

<c:url var="home" value="/mesaayuda/" scope="request" />
<div class="main col pt-2" style="margin-top: 20px">
    <div class="col-xl-12 col-lg-8 mx-auto">
        <div class="bg-faded rounded p-3">
            <div class="card text-center">
                <div class="card-body">
                    <button type="button" class="btn-cambiar-color btn btn-primary"  id="recargartabla">Recargar <i class="fas fa-undo"></i></button>
                </div>
            </div>
            <br>
            <div class="table-responsive">
                <table id="tableCargarSolicitud" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                        <tr>
                            <th class="th-sm" style="font-size: 0.85em ">Cod</th>
                            <th class="th-sm" style="font-size: 0.85em; max-width: 260px; text-align: justify">Descripción</th>
                            <th class="th-sm" style="font-size: 0.85em ">Fecha Inicio</th>
                            <th class="th-sm" style="font-size: 0.85em ">Fecha Fin</th>
                            <th class="th-sm" style="font-size: 0.85em ">Usuario SolicitaAyuda</th>
                            <th class="th-sm" style="font-size: 0.85em ">Acción</th>
                        </tr>
                    </thead>
                    <tbody id="bodyTableCargarSolicitudes">
                        
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
    <!-- modal de comenzar a trabajar la solicitud -->
    <div class="modal fade" id="procesoSolicitud" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body " id="mensajePost">
                    
                </div>
            </div>
        </div>
    </div>
    
    <!-- modal para finalizar o evaluar la solicitud -->
    <div class="modal fade" id="finalSolicitudModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Descripción</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body ">
                    <form autocomplete="off" style="font-size: 0.95em">
                        <textarea class="form-control text-justify" rows="3" id="descripcion_st" name="descripcion_sn" v-model="text" required></textarea>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn btn-danger" id="finalSolicitud" data-dismiss="modal">Finalizar</button>
                    <button type="button" class="btn btn-warning" id="reevaluarSolicitud" data-dismiss="modal">Reevaluar</button>
                  </div>
                </div>
            </div>
        </div>
    </div>
</div>