<%-- 
    Document   : crearSolicitud
    Created on : 09/05/2020, 21:56:46
    Author     : bryan
--%>

<div>
    <div class="loader">
        <img src="${pageContext.request.contextPath}/images/spinner-reload.gif" alt="Loading..."/>
    </div>
    <div style="padding-left: 30px; padding-top: 15px;">
        <span style="font-size: 1.25em;"><i class="fas fa-eye" id="eyeO" style="color: #054182"></i></span>
    </div>
    <div class="col-xl-12 col-lg-12 col-md-12 mx-auto" id="divCrearUsuario">
        <div class="bg-faded rounded p-3">
            <form id= "formUsuarioD" style="font-size: 0.95em" autocomplete="off">
                <div class="card" style="font-size: 0.85em;">
                    <div class="card-body">
                        <div><h4>Datos del usuario</h4></div>
                        <small class="form-text text-muted" style="font-size: 0.90em;"><i>Campos con (*) son obligatorios de llenar</i> </small>
                        <br>
                        <div class="form-row" style="padding-left: 20px;">
                            <div class="form-row col-12">
                                <div class="form-group col-1" style="margin-right: 25px;">
                                    <label><b style="color:red; font-size: 1.25em;">*</b> Nombre</label>
                                </div>
                                <div class="form-group col-3">
                                    <input type="text" class="form-control form-control-sm" name="nombreUser" id="nombreUser" maxlength="20" required>
                                </div>
                                <div class="form-group col-1" style="margin-right: 10px;">
                                    <label><b style="color:red; font-size: 1.25em;">*</b> Apellido</label>
                                </div>
                                <div class="form-group col-3">
                                    <input type="text" class="form-control form-control-sm" name="apellidoUser" id="apellidoUser" maxlength="20" required>
                                </div>
                            </div>
                        </div>
                        <div class="form-row" style="padding-left: 20px;">
                            <div class="form-row col-12">
                                <div class="form-group col-1" style="margin-right: 25px;">
                                    <label><b style="color:red; font-size: 1.25em;">*</b> Usuario</label>
                                </div>
                                <div class="form-group col-2">
                                    <input type="text" class="form-control form-control-sm" name="nUser" id="nUser" maxlength="15" required>
                                </div>
                                <div class="form-group col-1"></div>
                                <div class="form-group col-1" style="margin-right: 10px;">
                                    <label style="width: 90px;"><b style="color:red; font-size: 1.25em;">*</b> Contraseña</label>
                                </div>
                                <div class="form-group col-2">
                                    <input type="text" class="form-control form-control-sm" name="contrasenaUser" id="contrasenaUser" maxlength="15" required>
                                </div>
                                <div class="form-group col-1" style="margin-right: 10px;">
                                    <label><b style="color:red; font-size: 1.25em;">*</b> Correo</label>
                                </div>
                                <div class="form-group col-3">
                                    <input type="email" class="form-control form-control-sm" name="correoUser" id="correoUser" required>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="form-row" style="padding-left: 20px;">
                            <div class="form-row col-12">
                                <div class="form-group col-1" style="margin-right: 25px;">
                                    <label style="width: 200px;"><b style="color:red; font-size: 1.25em;">*</b> Departamento</label>
                                </div>
                                <div class="form-group col-3">
                                    <select  name="departamentoUser" id="departamentoUser" class="form-control form-control-sm" model="selected" required>
                                        <option selected disabled hidden style='display: none' value=''></option>
                                        <c:forEach items="${listarDepartamento}" var="departamento">
                                            <option value="${departamento.idDepartamento}">${departamento.nombreDepartamento}</option> 
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-row" style="padding-left: 20px;">
                            <div class="form-row col-12">
                                <div class="form-group col-2" style="margin-right: 25px;">
                                    <label style="width: 200px;"><b style="color:red; font-size: 1.25em;">*</b> Rol</label>
                                </div>
                                <div class="form-group col-2">
                                    <select  name="rolUser" id="rolUser" class="form-control form-control-sm" model="selected" required>
                                        <option selected disabled hidden style='display: none' value=''></option>
                                        <option value="admin_sist">admin_sist</option>
                                        <option value="admin_mant">admin_mant</option>
                                        <option value="admin_padres">admin_padres</option>
                                        <option value="tecnico_sist">tecnico_sist</option>
                                        <option value="tecnico_sist">tecnico_mant</option>
                                        <option value="general_acad">general_acad</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-row" style="padding-left: 20px;">
                            <div class="form-row col-12">
                                <div class="form-group col-2" style="margin-right: 25px;">
                                    <label style="width: 200px;"><b style="margin-right: 10px;"></b>Tipo</label>
                                </div>
                                <div class="form-group col-2">
                                    <select  name="tipoUser" id="tipoUser" class="form-control form-control-sm" model="selected">
                                        <option selected disabled hidden style='display: none' value=''></option>
                                        <c:forEach items="${listarTiposCS}" var="tipoGrupo">
                                            <option value="${tipoGrupo.idTipo}">${tipoGrupo.nombreTipo}</option> 
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-row" style="padding-left: 20px;">
                            <div class="form-row col-12">
                                <div class="form-group col-4"></div>
                                <div class="form-group col-2">
                                    <div class="text-center">
                                        <button type="submit" class="btn-cambiar-color btn btn-primary"  id="crearUsuario"><i class="fas fa-save"></i> Guardar</button>
                                    </div>
                                </div>
                                <div class="form-group col-1">
                                    <div class="text-center">
                                        <button type="reset" class="btn-cambiar-color btn btn-primary"  id="resetFormUsuario"><i class="fas fa-redo-alt"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="col-xl-12 col-lg-12 col-md-12 mx-auto">
        <div class="bg-faded rounded p-3">
            <form id= "formFiltroU" style="font-size: 0.95em" autocomplete="off">
                <div class="card" style="font-size: 0.85em;">
                    <div class="card-body">
                        <div class="form-row" style="padding-left: 20px;">
                            <div class="form-row col-12">
                                <div class="form-group col-2" style="margin-right: 25px;">
                                    <label style="width: 200px;"><b style="margin-right: 10px;">Filtro</b></label>
                                </div>
                                <div class="form-group col-3">
                                    <select  name="departamentoUser" id="departamentoUsuario" class="form-control form-control-sm" model="selected" required>
                                        <option selected disabled hidden style='display: none' value=''></option>
                                        <c:forEach items="${listarDepartamento}" var="departamento">
                                            <option value="${departamento.idDepartamento}">${departamento.nombreDepartamento}</option> 
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-1"></div>
                                <div class="form-group col-2">
                                    <div class="text-center" style="width: 100%;">
                                        <button type="submit" class="btn-cambiar-color btn btn-primary"  id="filtrarUsuarios">Buscar</button>
                                    </div>
                                </div>     
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 mx-auto">
        <div class="bg-faded rounded p-3">
            <div class="table-responsive">
                <table id="tableConsultarUsuarioD" class="table table-striped table-bordered" style="width:100%; font-size: 0.85em; display: none">
                    <thead>
                        <tr>
                            <th class="th-sm">Cod</th>
                            <th class="th-sm">Nombre</th>
                            <th class="th-sm">Apellido</th>
                            <th class="th-sm">Usuario</th>
                            <th class="th-sm">Contraseña</th>
                            <th class="th-sm">Correo</th>
                            <th class="th-sm">Rol</th>
                            <th class="th-sm">Tipo</th>
                            <th class="th-sm">Acción</th>
                        </tr>
                    </thead>
                    <tbody id="bodyTableCargarUsuarioD" style="font-size: 0.85em;">
                    
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
</div>
