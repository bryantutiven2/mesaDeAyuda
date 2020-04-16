<%-- 
    Document   : consultarSolicitudes
    Created on : 13/03/2020, 12:06:34
    Author     : bryan
--%>

<div class="main col pt-2" style="margin-top: 20px">
    <div class="col-xl-12 col-lg-8 mx-auto">
        <div class="bg-faded rounded p-3">
            <div class="card">
                <form style="font-size: 0.95em" autocomplete="off">
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
                                    <option value="reevaluar">Reevaluar</option>
                                    <option value="finalizada">Finalizada</option>
                                </select>
                            </div>
                            
                        </div>
                        <div class="text-center">
                            <button type="button" class="btn-cambiar-color btn btn-primary"  id="cargarSelectC" style="margin-right: 15px;"><i class="fas fa-search"></i> Cargar</button>
                            <button type="reset" class="btn-cambiar-color btn btn-primary"  id="resetSelectC"><i class="fas fa-undo"></i></button>
                        </div>
                    </div>
                </form>
            </div>
            <br>
        </div>
    </div>
    <div class="col-xl-12 col-lg-8 mx-auto">
        <div class="bg-faded rounded p-3">
            <div class="table-responsive">
                <table id="tableConsultarSolicitud" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                        <tr>
                            <th class="th-sm" style="font-size: 0.85em ">Cod</th>
                            <th class="th-sm" style="font-size: 0.85em; max-width: 260px; text-align: justify">Descripción</th>
                            <th class="th-sm" style="font-size: 0.85em ">Grupo</th>
                            <th class="th-sm" style="font-size: 0.85em ">Tipo</th>
                            <th class="th-sm" style="font-size: 0.85em ">Técnico</th>
                            <th class="th-sm" style="font-size: 0.85em ">Fecha Inicio</th>
                            <th class="th-sm" style="font-size: 0.85em ">Fecha Fin</th>
                            <th class="th-sm" style="font-size: 0.85em ">Estado</th>
                        </tr>
                    </thead>
                    <tbody id="bodyTableCargarSolicitudes">
                    
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>