<%-- 
    Document   : consultarSolicitudes
    Created on : 13/03/2020, 12:06:34
    Author     : bryan
--%>

<c:url var="home" value="/mesaayuda/" scope="request" />
<div class="main col pt-2" style="margin-top: 20px">
    <div class="col-xl-12 col-lg-8 col-md-12 mx-auto">
        <div class="bg-faded rounded p-3">
            <div class="loader">
                <img src="${pageContext.request.contextPath}/images/spinner-reload.gif" alt="Loading..."/>
            </div>
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
                            <th class="th-sm" style="font-size: 0.85em ">Usuario Solicita Ayuda</th>
                            <th class="th-sm" style="font-size: 0.85em ">Acción</th>
                            <th class="th-sm" style="font-size: 0.85em ">Observación</th>
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
                <form id="formRespuestaTecnico" autocomplete="off" style="font-size: 0.95em">
                    <div class="modal-body ">
                        <div class="form-row">
                            <div class="form-group col-md-2">
                                <label><h6>Subtipo</h6></label>
                            </div>
                            <div class="form-group col-md-5">
                                <select id="selectSubtipo" name="subtipo_CS" class="form-control" model="selected" required>
                                    <option selected disabled hidden style='display: none' value=''></option>
                                    <c:forEach items="${listarSubtipo_CS}" var="subtipo">
                                        <option value="${subtipo.idSubtipo}">${subtipo.nombreSubtipo}</option> 
                                    </c:forEach>
                                    <option value='none'>none</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <textarea class="form-control text-justify" rows="3" id="descripcion_st" name="descripcion_sn" v-model="text" required></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn btn-danger" id="finalSolicitud" >Finalizar</button>
                        <button type="button" class="btn btn-warning" id="reevaluarSolicitud">Reevaluar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- modal para cargar mensaje -->
    <div class="modal fade" id="modalMensaje" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body " id="mensajeModal">

                </div>
            </div>
        </div>
    </div>                                
    <!-- Modal observacion-->
    <div class="modal fade" id="modalObservacion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="text-center" style="width: 100%;"><h4 class="modal-title" id="exampleModalLabel" style="color: #032345;">Observaciones</h4></div>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" style="overflow-y: auto; height:300px; overflow-x: hidden;" id="listaObservaciones">
                    
                </div>
                <form id="formObservacion" autocomplete="off">
                    <div class="modal-footer justify-content-between ">
                        <div class="form-row col">
                            <div class="form-group col-11">
                                <input type="text" class="form-control" name="observacionM" id="observacionM" required>
                            </div>
                            <div class="form-group col-1">
                                <button type="submit" class="btn-cambiar-color btn btn-primary" id="enviarObservacion"><i class="fas fa-paper-plane"></i></button>
                            </div>
                        </div>
                    </div>
                </form> 
            </div>
        </div>
    </div>                                
</div>