<%-- 
    Document   : notifiacion
    Created on : 09/05/2020, 21:27:04
    Author     : bryan
--%>

<!-- modal para cargar Encuesta -->
<div class="modal fade  bd-example-modal-lg" id="modalEncuesta" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="text-center" style="width: 100%;"> <h4 class="titulo">Encuesta</h4> </div>

            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            </div>
            <div class="modal-body " id="mensajeEncuesta">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn-cambiar-color btn btn-primary" id="aceptarET">Confirmar</button>
            </div>
        </div>
    </div>
</div>
<!-- modal para Confirmar encuesta -->
<div class="modal fade" id="modalConfirmar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="formCR" autocomplete="off">
                <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                </div>
                <div class="modal-body " id="mensajeConfirmar">
                    <div class="form-group">
                        <label><h6>Ingrese código de registro:</h6></label>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="idRegistroSol" id="idRegistroSol" required>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="submit" class="btn-cambiar-color btn btn-primary" id="aceptarEncuestaT">Aceptar</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- modal para cargar mensaje -->
<div class="modal fade" id="modalMensajeNotificacion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body " id="mensajeModalNotificacion">

            </div>
        </div>
    </div>
</div>