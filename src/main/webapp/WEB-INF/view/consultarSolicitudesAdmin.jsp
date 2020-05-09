<%-- 
    Document   : consultarSolicitudes
    Created on : 05/10/2020, 12:06:34
    Author     : bryan
--%>

<div class="main col pt-2" style="margin-top: 20px">
    <div class="col-xl-12 col-lg-12 col-md-12 mx-auto">
        <div class="bg-faded rounded p-3">
            <div class="loader">
                <img src="${pageContext.request.contextPath}/images/spinner-reload.gif" alt="Loading..."/>
            </div>
            <form style="font-size: 0.95em" autocomplete="off">
                <div class="card">
                    <div class="card-body">
                        <div class="form-row" style="padding-left: 30px;">
                            <div class="form-row col-6">
                                <div class="form-group col-3">
                                    <label>Buscar por: </label>
                                </div>
                                <div class="form-group col-9">
                                    <fieldset id="testForm">
                                        <div class="form-group col-md-11" required>
                                            <input selected disabled hidden style='display: none' type="radio" id="grupoSistemaCS" name="grupo" value="" required>
                                            <div>
                                                <input type="radio" name="solicitudesR" id="g1" value="mSolicitudes">
                                                <label for="">Mis Solicitudes</label>
                                            </div>
                                            <div>
                                                <input type="radio" name="solicitudesR" id="g2" value="tSolicitudes">
                                                <label for="">Técnico</label>
                                            </div>
                                            <div>
                                                <input type="radio" name="solicitudesR" id="g3" value="uSolicitudes">
                                                <label for="">Usuario Solicita Ayuda</label>
                                            </div>
                                        </div>
                                    </fieldset> 
                                </div>
                            </div>
                            <div class="form-row col-6">
                                <div class="form-row col-12">
                                    <div class="form-group col" id="filtroUsuario" style="display: none;">
                                        <div class="form-row" >
                                            <div class="form-group col-3">
                                                <label>Id usuario: </label>
                                            </div>
                                            <div class="form-group col-4">
                                                <input type="text" class="form-control cargarTogleUsuarios" name="idUserSolicitaA" id="idUserSolicitaA" required readonly>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-row col-12">
                                    <div class="form-group col" id="filtroTecnico" style="display: none;">
                                        <div class="form-row" >
                                            <div class="form-group col-3">
                                                <label>Técnico: </label>
                                            </div>
                                            <div class="form-group col-5">
                                                <select id="selectTecnico" name="tecnico_cs" class="form-control" model="selected" required>
                                                    <option selected disabled hidden style='display: none;' value=''></option>
                                                    <c:forEach items="${listarTecnicoCS}" var="tecnico">
                                                        <option value="${tecnico.idUsuario}">${tecnico.nombre} ${tecnico.apellido}</option> 
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-row col-12">
                                    <div class="form-group col">
                                        <div class="form-row" id="filtroGrupo" style="display: none;">
                                            <div class="form-group col-3">
                                                <label>Grupo: </label>
                                            </div>
                                            <div class="form-group col-4">
                                                <select  name="buscarGrupo" id="buscarGrupo" class="form-control" model="selected">
                                                    <option selected disabled hidden style='display: none' value=''></option>
                                                    <option value="sist">Sistemas</option>   
                                                    <option value="mant">Mantenimiento</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-row" id="filtroEstado" style="display: none;">
                                            <div class="form-group col-3">
                                                <label>Estado: </label>
                                            </div>
                                            <div class="form-group col-4">
                                                <select  name="buscarEstado" id="buscarEstado" class="form-control" model="selected">
                                                    <option selected disabled hidden style='display: none' value=''></option>
                                                    <option value="pendiente">Pendiente</option>   
                                                    <option value="asignada">Asignada</option>
                                                    <option value="reevaluar">Reevaluar</option>
                                                    <option value="finalizada">Finalizada</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                        <div class="form-row" style="padding-left: 30px;">
                            <div class="form-row col-5">
                                <div class="form-group col-4">
                                    <label>Desde: </label>
                                </div>
                                <div class="form-group col-7">
                                    <div class="form-group">
                                        <div class="input-group date" id="datetimeDesde" data-target-input="nearest">
                                            <input type="text" id="dtpDesde" class="form-control datetimepicker-input" data-target="#datetimeDesde"/>
                                             <div class="input-group-append" data-target="#datetimeDesde" data-toggle="datetimepicker">
                                                 <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                             </div>
                                         </div>
                                     </div>
                                </div>
                            </div>
                            <div class="form-row col-5">
                                <div class="form-group col-4">
                                    <label>Hasta: </label>
                                </div>
                                <div class="form-group col-7">
                                    <div class="form-group">
                                        <div class="input-group date" id="datetimeHasta" data-target-input="nearest">
                                             <input type="text"id="dtpHasta" class="form-control datetimepicker-input" data-target="#datetimeHasta"/>
                                             <div class="input-group-append" data-target="#datetimeHasta" data-toggle="datetimepicker">
                                                 <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                             </div>
                                         </div>
                                     </div>
                                </div>
                            </div>
                        </div>                            
                        <div class="form-row col" style="padding-left: 30px;">
                            <div class="text-center" style="width: 100%">
                                <button type="button" class="btn-cambiar-color btn btn-primary"  id="cargarSelectC" style="margin-right: 15px;"><i class="fas fa-search"></i> Cargar</button>
                                <button type="reset" class="btn-cambiar-color btn btn-primary"  id="resetSelectC"><i class="fas fa-undo"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <br>
    <div class="col-xl-12 col-lg-8 mx-auto">
        <div class="bg-faded rounded p-3">
            <div id="tableM" class="table-responsive" style="display: none;">
                <table id="tableConsultarSolicitudM" class="table table-striped table-bordered" style="width:100%">
                    <thead id="headTableCargarSolicitudes">
                        <tr>
                            <th class="th-sm" style="font-size: 0.85em ">Cod</th>
                            <th class="th-sm" style="font-size: 0.85em; max-width: 260px; text-align: justify">Descripción</th>
                            <th class="th-sm" style="font-size: 0.85em ">Grupo</th>
                            <th class="th-sm" style="font-size: 0.85em ">Tipo</th>
                            <th class="th-sm" style="font-size: 0.85em ">Técnico</th>
                            <th class="th-sm" style="font-size: 0.85em ">Fecha Inicio</th>
                            <th class="th-sm" style="font-size: 0.85em ">Fecha Fin</th>
                            <th class="th-sm" style="font-size: 0.85em ">Estado</th>
                            <th class="th-sm" style="font-size: 0.85em ">Observación</th>
                        </tr>
                    </thead>
                    <tbody id="bodyTableCargarSolicitudes" style="font-size: 0.85em;">
                        
                    </tbody>
                </table>
            </div>
            <div id="tableR" class="table-responsive" style="display: none;">    
                <table id="tableConsultarSolicitudR" class="table table-striped table-bordered" style="width:100%">
                    <thead id="headTableCargarSolicitudesR">
                        <tr>
                            <th class="th-sm" style="font-size: 0.85em ">Cod</th>
                            <th class="th-sm" style="font-size: 0.85em; max-width: 260px; text-align: justify">Descripción</th>
                            <th class="th-sm" style="font-size: 0.85em ">Usuario Solicita Ayuda</th>
                            <th class="th-sm" style="font-size: 0.85em ">Fecha Inicio</th>
                            <th class="th-sm" style="font-size: 0.85em ">Fecha Fin</th>
                            <th class="th-sm" style="font-size: 0.85em ">Fecha Inicio Técnico</th>
                            <th class="th-sm" style="font-size: 0.85em ">Fecha Fin nTécnico</th>
                            <th class="th-sm" style="font-size: 0.85em ">Estado Solicitud</th>
                            <th class="th-sm" style="font-size: 0.85em ">Observación</th>
                        </tr>
                    </thead>
                    <tbody id="bodyTableCargarSolicitudesR" style="font-size: 0.85em;">
                        
                    </tbody>
                </table>
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
    </div><!--Modal o mop up de la tabla emergente para escoger las ayudas solicitidas-->
    <div id="myModalCS" class="modal fade bd-example" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Seleccione el Usuario que solicita ayuda</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="idsUSA">
                        <div class="table-responsive">
                            <table id="dtUsuariosSA" class="table table-striped table-bordered" style="width:100%">
                                <thead>
                                    <tr>
                                        <th class="th-sm">Id</th>
                                        <th class="th-sm">Usuario</th>
                                        <th class="th-sm"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                     <c:forEach items="${listaUsuariosSA}" var="usuariosSA">
                                        <tr>
                                            <th class="idsolicitud" scope="row">${usuariosSA.idUsuario}</th>
                                            <td>${usuariosSA.nombre} ${usuariosSA.apellido}</td>
                                            <td><input type="checkbox" name="adU-checkbox" value="${usuariosSA.idUsuario}"/>&nbsp;</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </form>
                 </div>
                <div class="modal-footer">
                    <button type="button" class="btn-cambiar-color btn btn-primary" id="cargarIdUSA" data-dismiss="modal">Aceptar</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>
</div>
