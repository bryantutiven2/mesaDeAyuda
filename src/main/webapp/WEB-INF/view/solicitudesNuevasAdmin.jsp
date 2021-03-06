<%-- 
    Document   : solicitudesNuevasAdmin
    Created on : 26/03/2020, 16:10:33
    Author     : bryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="main pt-1" style="margin-top: 20px">
    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12  mx-auto">
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
                <table id="tablaNuevaS" class="table table-striped table-bordered" style="width:100%;font-size: 0.85em">
                    <thead>
                        <tr>
                            <th class="th-sm">Cod</th>
                            <th class="th-sm">Descripción</th>
                            <th class="th-sm">Usuario</th>
                            <th class="th-sm">Técnico</th>
                            <th class="th-sm">N° problema</th>
                            <th class="th-sm">Ids n vez</th>
                            <th class="th-sm">Fecha Inicio</th>
                            <th class="th-sm">Acción</th>
                            <th class="th-sm">Obs</th>
                        </tr>
                    </thead>
                    <tbody id="bodyTableCargarNuevasSolicitudes">
                        
                    </tbody>
                </table>
            </div>
        </div>
    </div>
                    
    <div>
        <div id="myModalNuevaSolicitud" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Gestionar Solicitud</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form id="formNsolicitudA" autocomplete="off" style="font-size: 0.95em">
                        <div class="modal-body" style="overflow-y: auto; height:430px;">
                            <div class="col-xl-12 col-lg-10 mx-auto">
                                <div class="bg-faded rounded p-2">
                                    <!--<form autocomplete="off" style="font-size: 0.95em">-->
                                        <div class="form-row">
                                            <div class="form-group col-md-1">
                                                <label>Cod</label>
                                                <input type="text" class="form-control" v-model="subject" id="codigo_sn" name="codigo_sn" readonly="readonly">
                                            </div>
                                            <div class="form-group col-md-1"></div>
                                            <div class="form-group col-md-3">
                                                <label>Usuario</label>
                                                <input type="text" class="form-control" v-model="subject" id="usuario_sn" name="usuario_sn" readonly="readonly">
                                            </div>
                                            <div class="form-group col-md-1"></div>
                                            <div class="form-group col-md-2">
                                                <label>N° de vez</label>
                                                <input type="text" class="form-control" v-model="subject" id="n_vez_sn" name="n_vez_sn" readonly="readonly">
                                            </div>
                                            <div class="form-group col-md-1"></div>
                                            <div class="form-group col-md-3">
                                                <label>Ids n_vez</label>
                                                <input type="text" class="form-control" v-model="subject" id="idsn_vez_sn" name="idsn_vez_sn" readonly="readonly">
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-12    ">
                                              <label for="validationTextarea">Descripción</label>
                                              <textarea class="form-control text-justify" rows="4" id="descripcion_sn" name="descripcion_sn" v-model="text" readonly="readonly"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-3">
                                                <label>Técnico</label>
                                                <select id="selectTecnico" name="tecnico_sn" class="form-control" model="selected" required>
                                                    <option selected disabled hidden style='display: none' value=''></option>
                                                    <c:forEach items="${listarTecnicoSN}" var="tecnico">
                                                        <option value="${tecnico.idUsuario}">${tecnico.nombre} ${tecnico.apellido}</option> 
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-1"></div>
                                            <div class="form-group col-md-3">
                                                <label>Tipo Grupo</label>
                                                <select id="selectTipo" name="tipoGrupo_sn" class="form-control" model="selected" required>
                                                    <option selected disabled hidden style='display: none' value=''></option>
                                                    <c:forEach items="${listarTiposSN}" var="tipoGrupo">
                                                        <option value="${tipoGrupo.idTipo}">${tipoGrupo.nombreTipo}</option> 
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-1"></div>
                                            <div class="form-group col-md-3">
                                                <label>Encuesta</label>
                                                <input type="text" class="form-control" name="encuesta_cs" id="encuesta_cs" readonly required>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-4">
                                                <label>Fecha Inicio</label>
                                                <input type="text" class="form-control" v-model="subject" id="fechaInicio_sn" name="fechaInicio_sn" readonly="readonly">
                                            </div>
                                            <div class="form-group col-md-2"></div>
                                            <div class="form-group col-md-4">
                                                <label>Fecha Fin</label>
                                                <div class="form-group">
                                                    <div class="input-group date" id="dtPicker" data-target-input="nearest">
                                                        <input type="text" id="fechaFin_sn" name="fechaFin_sn" class="form-control datetimepicker-input" data-target="#dtPicker" required/>
                                                        <div class="input-group-append" data-target="#dtPicker" data-toggle="datetimepicker">
                                                            <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>  
                                        </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn-cambiar-color btn btn-primary" id="enviarSActualizada">Asignar Solicitud</button>
                            <!--<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>-->
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!--Modal o mop up de la tabla emergente para escoger la encuesta-->
    <div id="modalEncuestaNA" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Seleccione la Encuesta</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="formItemsEncuesta">
                        <div class="table-responsive">
                           <table id="dtEncuesta" class="table table-striped table-bordered" style="width:100%">
                                <thead>
                                    <tr>
                                        <th class="th-sm">Cod</th>
                                        <th class="th-sm">Nombre</th>
                                        <th class="th-sm" style="max-width: 260px; text-align: justify">Descripción</th>
                                        <th class="th-sm"></th>
                                    </tr>
                                </thead>
                                <tbody id="bodyTableCargarEncuesta">
                                    <c:forEach items="${listaEncuesta}" var="encuesta">
                                        <tr>
                                            <th class="idsolicitud" scope="row">${encuesta.idEncuesta}</th>
                                            <td>${encuesta.nombre}</td>
                                            <td>${encuesta.descripcion}</td>
                                            <td><input type="checkbox" name="encuesta-checkbox" value="${encuesta.idEncuesta}"/>&nbsp;</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table> 
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn-cambiar-color btn btn-primary" id="cargarIdEcuesta" data-dismiss="modal">Aceptar</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>
    <!-- modal de mensaje de la solicitud -->
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
                        
           