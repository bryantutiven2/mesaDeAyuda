<%-- 
    Document   : solicitudesNuevasAdmin
    Created on : 26/03/2020, 16:10:33
    Author     : bryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div style="margin-top: 20px">
    <div class="col-xl-12 col-lg-8 mx-auto">
        <div class="bg-faded rounded p-3">
            <table id="tablaNuevaS" class="table table-striped table-bordered table-sm" cellspacing="0"  width="100%">
                <thead>
                    <tr>
                        <th class="th-sm" style="font-size: 0.85em ">Cod</th>
                        <th class="th-sm" style="font-size: 0.85em; max-width: 150px;overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">Descripción</th>
                        <th class="th-sm" style="font-size: 0.85em">Usuario</th>
                        <th class="th-sm" style="font-size: 0.85em; max-width: 85px">N° problema</th>
                        <th class="th-sm" style="font-size: 0.85em">Ids n vez</th>
                        <th class="th-sm" style="font-size: 0.85em">Fecha Inicio</th>
                        <th class="th-sm" style="font-size: 0.85em">Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listaNuevasSolicitudes}" var="nSolicitud">
                        <tr>
                            <th class="id_sn" scope="row" style="font-size: 0.8em">${nSolicitud.id}</th>
                            <td class="descrip_sn" style="font-size: 0.8em; max-width: 150px;overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">${nSolicitud.descripcion}</td>
                            <td class="user_sn" style="font-size: 0.8em">${nSolicitud.userSolicitaAyuda}</td>
                            <td class="n_sn" style="font-size: 0.8em; max-width: 85px">${nSolicitud.n_vez}</td>
                            <td class="ids_sn" style="font-size: 0.8em">${nSolicitud.ids_n_vez}</td>
                            <td class="fechaInicio_sn" style="font-size: 0.8em">${nSolicitud.fechaInicio}</td>
                            <td class="text-center">
                                <button type="button" class="btn btn-success btn-sm asignarSolicitud" value="${solicitud.id}"><i class="far fa-edit"></i> Asignar</button>
                                <button type="button" class="btn btn-danger btn-sm" value="${solicitud.id}"><i class="far fa-window-close"></i> Finalizar</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
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
                    <div class="modal-body">
                        <div class="col-xl-12 col-lg-10 mx-auto">
                            <div class="bg-faded rounded p-2">
                                <form action="actualizarSolicitud" method="post">
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
                                    <div class="form-group">
                                        <div class="form-group col-md-10">
                                          <label for="validationTextarea">Descripción</label>
                                          <textarea class="form-control text-justify" id="descripcion_sn" name="descripcion_sn" v-model="text" readonly="readonly"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label>Técnico</label>
                                            <select id="selectTecnico" name="tecnico_sn" class="form-control" model="selected">
                                                <c:forEach items="${listarTecnicoSN}" var="tecnico">
                                                    <option value="${tecnico.idUsuario}">${tecnico.nombre} ${tecnico.apellido}</option> 
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-1"></div>
                                        <div class="form-group col-md-4">
                                            <label>Tipo Grupo</label>
                                            <select id="selectTipo" name="tipoGrupo_sn" class="form-control" model="selected">
                                                <c:forEach items="${listarTiposSN}" var="tipoGrupo">
                                                    <option value="${tipoGrupo.idTipo}">${tipoGrupo.nombreTipo}</option> 
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label>Fecha Inicio</label>
                                            <input type="text" class="form-control" v-model="subject" id="fechaInicio_sn" name="fechaInicio_sn" readonly="readonly">
                                        </div>
                                        <div class="form-group col-md-1"></div>
                                        <div class="form-group col-md-4">
                                            <label>Fecha Fin</label>
                                            <div class="input-group">
                                                <input type='text' class="form-control" name="fechaFin_sn" id='dtPicker' required="true"/>
                                                <div class='input-group-prepend' >
                                                    <button type="button" id="toggle" class="input-group-text">
                                                        <i class="fa fa-calendar-alt"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>  
                                    </div>
                                    <br />
                                    <div class="text-right">
                                        <button type="submit" class="btn btn-primary" id="enviarSActualizada">Enviar Solicitud</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        
                    </div>
                    <!--                            
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" id="enviarSActualizada">Enviar</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    </div>
                    -->
                </div>
            </div>
        </div>
    </div>
</div>
                        
                        