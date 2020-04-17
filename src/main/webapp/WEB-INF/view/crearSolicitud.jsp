<%-- 
    Document   : crearSolicitud
    Created on : 13/03/2020, 11:56:46
    Author     : bryan
--%>

<div>
    <h2 class="mt-4 text-center">Crear Solicitud de Ayuda</h2>
    <div class="col-xl-8 col-lg-8 mx-auto">
        <div class="bg-faded rounded p-2">
            <form action="" style="font-size: 0.95em" autocomplete="off">
                <div class="card">
                    <div class="card-body">
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <h5>Grupo</h5>
                                <br>
                                <fieldset id="testForm">
                                    <div>
                                        <input selected disabled hidden style='display: none' type="radio" name="grupo" value="none" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <div style="width: 120px;">
                                            <input type="radio" id="grupoSistema" name="grupo" id="g1" value="sist">
                                            <label for="sistemas">Sistemas</label>
                                        </div>
                                        <div style="width: 150px;">
                                            <input type="radio" id="grupoMantenimiento" name="grupo" id="g2" value="mant">
                                            <label for="mantenimiento">Mantenimiento</label>
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                            <div class="form-group col-md-1"></div>
                            <div class="form-group col-md-7">
                                <h5>Reincidencia del Problema</h5>
                                <br>
                                <div class="form-row">
                                      <div class="form-group col-md-5">
                                          <select id="reincidencia" name="nvez" class="form-control" required model="selected" onchange = "myFunction()">
                                              <option selected disabled hidden style='display: none' value=''></option>
                                              <option value="1">1vez</option>   
                                              <option value="2">2vez</option>
                                              <option value="3">3vez</option>
                                              <option value="4">4vez</option>
                                          </select>
                                      </div>
                                      <div class="form-group col-md-1">
                                      </div>
                                      <div class="form-group col-md-5">
                                           <input type="text" class="form-control" name="idsnvez" id="ids_aydudaas" readonly="readonly">
                                      </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <div class="card">
                    <div class="card-body">
                        <h5>Descripción</h5>
                        <br>
                        <div class="form-group">
                            <div class="form-group col-md-11">
                                <textarea class="form-control" rows="3" name="descripcion" id="descripcion" v-model="text"  required></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <br />
                <div class="text-center">
                    <button type="button" class="btn-cambiar-color btn btn-primary"  id="enviarSolicitud">Enviar Solicitud</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--Modal o mop up de la tabla emergente para escoger las ayudas solicitidas-->
<div id="myModal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Seleccione Ayudas realizadas</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="idsItems">
                    <div class="table-responsive">
                       <table id="dtBasicExample" class="table table-striped table-bordered" style="width:100%">
                            <thead>
                                <tr>
                                    <th class="th-sm">Cod</th>
                                    <th class="th-sm">Grupo</th>
                                    <th class="th-sm">Tipo</th>
                                    <th class="th-sm" style="max-width: 260px; text-align: justify">Descripción</th>
                                    <th class="th-sm"></th>
                                </tr>
                            </thead>
                            <tbody id="bodyTableCargarSolicitudes">
                                
                            </tbody>
                        </table> 
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-cambiar-color btn btn-primary" id="cargarIds" data-dismiss="modal">Aceptar</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
            </div>
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

