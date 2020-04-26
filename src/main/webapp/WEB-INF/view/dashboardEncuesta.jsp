<%-- 
    Document   : dashboardEncuesta
    Created on : 25/04/2020, 17:51:22
    Author     : bryan
--%>

<div>
    <br>
    <div class="col-xl-12 col-lg-12 col-md-12 mx-auto">
        <br>
        <button id="agregarEncuesta" type="button" class="btn"><i class="fas fa-plus-square" style="color: #032345; font-size: 2em;"></i></button>
        <div class="bg-faded rounded p-4">

            <div class="card" style="height: 400px;">
                <div class="card-body">
                    <div class="table-responsive">
                        <table id="tableConsultarSolicitud" class="table table-striped table-bordered" style="width:100%; font-size: 0.95em;">
                            <thead >
                                <tr>
                                    <th class="th-sm">Cod</th>
                                    <th class="th-sm">Nombre</th>
                                    <th class="th-sm">Descripción</th>
                                    <th class="th-sm" style="display: none;">Código Embebido</th>
                                    <th class="th-sm">Código Registro</th>
                                    <th class="th-sm">Estado</th>
                                    <th class="th-sm">Acción</th>
                                </tr>
                            </thead>
                            <tbody id="bodyTableEncuesta">
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
<!-- Modal -->
<div class="modal fade" id="modalCrearEncuesta" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="text-center" style="width: 100%;"><h4 class="modal-title" id="exampleModalLabel" style="color: #032345;">Encuesta</h4></div>

                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form id="formEncuesta" autocomplete="off">
                <div class="modal-body">
                    <div class="form-row">
                        <div class="form-group col-sm-12" id="grupoCod" style="display: none;">
                            <div class="form-row">
                                <div class="col-sm-2">
                                    <label>Código</label>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" name="codigoEC" id="codigoEC" readonly>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-12">
                            <label>Nombre</label>
                            <input type="text" class="form-control" name="nombreEC" id="nombreEC" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-12">
                            <label>Descripción</label>
                            <textarea class="form-control" name="descripcionEC" id="descripcionEC" rows="2" required></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-12">
                            <label>Código Embebido</label>
                            <textarea class="form-control" name="codigoEmbebido" id="codigoEmbebido" rows="3" required></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-5">
                            <label>Código de registro</label>
                            <input type="text" class="form-control" name="codigREC" id="codigoREC" required>
                        </div>
                        <div class="form-group col-2"></div>
                        <div class="form-group col-4">
                            <label>Estado</label>
                            <select id="estadoEC" name="nvez" class="form-control" required model="selected">
                                <option selected disabled hidden style='display: none' value=''></option>
                                <option value="0">Activa</option>   
                                <option value="1">Inactiva</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn-cambiar-color btn btn-primary" id="crearEC">Guardar</button>
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