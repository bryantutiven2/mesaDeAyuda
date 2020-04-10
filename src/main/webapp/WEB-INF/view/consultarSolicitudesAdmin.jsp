<%-- 
    Document   : consultarSolicitudes
    Created on : 13/03/2020, 12:06:34
    Author     : bryan
--%>

<div class="main col pt-2" style="margin-top: 20px">
    <div class="col-xl-12 col-lg-8 mx-auto">
        <div class="bg-faded rounded p-3">
            <div class="card">
                <form action="${pageContext.request.contextPath}/admin/filtroConsultarSolicitud" method="post" style="font-size: 0.95em" autocomplete="off">
                    <div class="card-body">
                        <div class="form-row">
                            <div class="form-group col-md-1"></div>
                            <div class="form-group col-md-2">
                                <label>Buscar por grupo: </label>
                            </div>
                            <div class="form-group col-md-2">
                                <select  name="buscarGrupo" id="buscarGrupo" class="form-control" model="selected">
                                    <option selected disabled hidden style='display: none' value=''></option>
                                    <option value="sist">Sistemas</option>   
                                    <option value="mant">Mantenimiento</option>
                                </select>
                            </div>
                            <div class="form-group col-md-2"></div>
                            <div class="form-group col-md-2">
                                <label>Buscar por Estado: </label>
                            </div>
                            <div class="form-group col-md-2">
                                <select  name="buscarEstado" id="buscarEstado" class="form-control" model="selected">
                                    <option selected disabled hidden style='display: none' value=''></option>
                                    <option value="pendiente">Pendiente</option>   
                                    <option value="asignada">Asignada</option>
                                    <option value="finalizada">Finalizada</option>
                                </select>
                            </div>
                            
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary"  id="cargarSelectC" style="margin-right: 15px;"><i class="fas fa-search"></i> Cargar</button>
                            <button type="reset" class="btn btn-primary"  id="resetSelectC"><i class="fas fa-undo"></i></button>
                        </div>
                    </div>
                </form>
            </div>
            <br> <br>
        </div>
    </div>
    <div class="col-xl-12 col-lg-8 mx-auto">
        <div class="bg-faded rounded p-3">
            <div class="table-responsive">
                <table id="tableConsultarSolicitudAdmin" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                        <tr>
                            <th class="th-sm" style="font-size: 0.85em ">Cod</th>
                            <th class="th-sm" style="font-size: 0.85em; max-width: 260px; text-align: justify">Descripcion</th>
                            <th class="th-sm" style="font-size: 0.85em ">Grupo</th>
                            <th class="th-sm" style="font-size: 0.85em ">Tipo</th>
                            <th class="th-sm" style="font-size: 0.85em ">Tecnico</th>
                            <th class="th-sm" style="font-size: 0.85em ">Fecha Inicio</th>
                            <th class="th-sm" style="font-size: 0.85em ">Fecha Fin</th>
                            <th class="th-sm" style="font-size: 0.85em ">Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listaConsultaSolicitudes}" var="solicitudL">
                            <tr>
                                <th class="idsolicitud" style="font-size: 0.8em" scope="row">${solicitudL.id}</th>
                                <td style="max-width: 260px; font-size: 0.8em; text-align: justify">${solicitudL.descripcion}</td>
                                <td style="font-size: 0.8em">${solicitudL.grupo}</td>
                                <td style="font-size: 0.8em">${solicitudL.tipo}</td>
                                <td style="font-size: 0.8em">${solicitudL.userTecnico}</td>
                                <td style="font-size: 0.8em">${solicitudL.fechaInicio}</td>
                                <td style="font-size: 0.8em">${solicitudL.fechaFin}</td>
                                <td style="font-size: 0.8em">${solicitudL.estadoSolicitud}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>