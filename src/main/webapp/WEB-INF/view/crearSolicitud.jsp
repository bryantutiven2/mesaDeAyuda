<%-- 
    Document   : crearSolicitud
    Created on : 13/03/2020, 11:56:46
    Author     : bryan
--%>

<div class="container-fluid">
    <h2 class="mt-4 text-center">Crear Solicitud de Ayuda</h2>
    <div>
        <section class="page-section about-heading">
            <div class="col-xl-8 col-lg-8 mx-auto">
                <div class="bg-faded rounded p-5">
                    <form action="enviarSolicitud" method="post">
                        <h5>Grupo</h5>
                        <br>
                        <fieldset>
                              <div class="form-group col-md-11 borderDiv">
                                  <div>
                                      <input type="radio" id="grupoSistema" name="grupo" id="g1" value="sist"checked>
                                      <label for="sistemas">Sistemas</label>
                                  </div>
                                  <div>
                                      <input type="radio" id="grupoMantenimiento" name="grupo" id="g2" value="mant">
                                      <label for="mantenimiento">Mantenimiento</label>
                                  </div>
                              </div>
                        </fieldset>
                        <h5>Descripcion</h5>
                        <br>
                        <div class="form-group">
                            <div class="form-group col-md-11">
                                <textarea class="form-control" name="descripcion" id="descripcion" v-model="text"  required></textarea>
                            </div>
                        </div>
                        <br>
                        <h5>Reincidencia del Problema</h5>
                        <div class="form-row">
                              <div class="form-group col-md-4">
                                  <select id="reincidencia" name="nvez" class="form-control" model="selected" onchange = "myFunction()">
                                      <option value="1">1vez</option>   
                                      <option value="2">2vez</option>
                                      <option value="3">3vez</option>
                                      <option value="4">4vez</option>
                                  </select>
                              </div>
                              <div class="form-group col-md-1">
                              </div>
                              <div class="form-group col-md-4">
                                   <input type="text" class="form-control" name="idsnvez" id="ids_aydudaas" readonly="readonly">
                              </div>
                        </div>
                        <br />
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary"  id="enviar">Enviar Solicitud</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
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
                      <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="th-sm">Cod</th>
                                <th class="th-sm">Grupo</th>
                                <th class="th-sm">Tipo</th>
                                <th class="th-sm" style="max-width: 260px; text-align: justify">Descripcion</th>
                                <th class="th-sm"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaSolicitudesModal}" var="solicitudM">
                                <tr>
                                    <th class="idsolicitud" scope="row">${solicitudM.id}</th>
                                    <td>${solicitudM.grupo}</td>
                                    <td>${solicitudM.tipo}</td>
                                    <td style="max-width: 260px; text-align: justify">${solicitudM.descripcion}</td>
                                    <td><input type="checkbox" name="name1" value="${solicitudM.id}"/>&nbsp;</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                  </form>
              </div>
              <div class="modal-footer">
                  <button type="button" class="btn btn-primary" id="cargarIds" data-dismiss="modal">Aceptar</button>
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
              </div>
          </div>
      </div>
  </div>
                                
