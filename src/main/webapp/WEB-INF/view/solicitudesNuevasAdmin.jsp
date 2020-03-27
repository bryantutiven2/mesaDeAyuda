<%-- 
    Document   : solicitudesNuevasAdmin
    Created on : 26/03/2020, 16:10:33
    Author     : bryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div style="margin-top: 20px">
    <div class="col-xl-12 col-lg-8 mx-auto">
        <div class="bg-faded rounded p-3">
            <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th class="th-sm" style="font-size: 0.85em">Cod</th>
                        <th class="th-sm" style="font-size: 0.85em">Descripción</th>
                        <th class="th-sm" style="font-size: 0.85em">Usuario</th>
                        <th class="th-sm" style="font-size: 0.85em">N° problema</th>
                        <th class="th-sm" style="font-size: 0.85em">Ids n vez</th>
                        <th class="th-sm" style="font-size: 0.85em">Fecha Inicio</th>
                        <th class="th-sm" style="font-size: 0.85em">Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listaNuevasSolicitudes}" var="nSolicitud">
                        <tr>
                            <th class="idsolicitud" scope="row" style="font-size: 0.85em">${nSolicitud.id}</th>
                            <td style="font-size: 0.8em">${nSolicitud.descripcion}</td>
                            <td style="font-size: 0.8em">${nSolicitud.userSolicitaAyuda}</td>
                            <td style="font-size: 0.8em">${nSolicitud.n_vez}</td>
                            <td style="font-size: 0.8em">${nSolicitud.ids_n_vez}</td>
                            <td style="font-size: 0.8em">${nSolicitud.fechaInicio}</td>
                            <td class="text-center">
                                <button type="button" class="btn btn-success btn-sm asignarSolicitud" value="${solicitud.id}">Asignar</button>
                                <button type="button" class="btn btn-danger btn-sm" value="${solicitud.id}">Finalizar</button>
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
                        <form action="gestionarNuevaSolicitud" method="post">
                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label>Cod</label>
                                    <input type="text" class="form-control" v-model="subject" name="codigo" readonly="readonly">
                                </div>
                                <div class="form-group col-md-1"></div>
                                <div class="form-group col-md-3">
                                    <label>Grupo</label>
                                    <input type="text" class="form-control" v-model="subject" name="grupo" readonly="readonly">
                                </div>
                                <div class="form-group col-md-1"></div>
                                <div class="form-group col-md-3">
                                    <label>Tipo</label>
                                    <select id="reincidencia" name="nvez" class="form-control" model="selected" onchange = "myFunction()">
                                        <c:forEach items="${listarTipos}" var="tipoGrupo">
                                            <option value="${tipoGrupo.idTipo}">${tipoGrupo.nombreTipo}</option> 
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="form-group col-md-10">
                                  <label for="validationTextarea">Descripción</label>
                                  <textarea class="form-control" id="validationTextarea" v-model="text" readonly="readonly"></textarea>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label>Técnico</label>
                                    <select id="reincidencia" name="nvez" class="form-control" model="selected" onchange = "myFunction()">
                                        <c:forEach items="${listarTipos}" var="tipoGrupo">
                                            <option value="${tipoGrupo.idTipo}">${tipoGrupo.nombreTipo}</option> 
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-md-1"></div>
                                <div class="form-group col-md-2">
                                    <label>Fecha Inicio</label>
                                    <input type="text" class="form-control" v-model="subject" name="fechaInicio" readonly="readonly">
                                </div>
                                <div class="form-group col-md-1"></div>
                                <div class="form-group col-md-2">
                                    <label>Fecha Fin</label>
                                    <div class="input-group date" data-provide="datepicker">
                                        <input type="text" class="form-control">
                                        <div class="input-group-addon">
                                            <span class="glyphicon glyphicon-th"></span>
                                        </div>
                                    </div>
                                </div>  
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="enviarSolicitud" data-dismiss="modal">Enviar</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
                        
                        