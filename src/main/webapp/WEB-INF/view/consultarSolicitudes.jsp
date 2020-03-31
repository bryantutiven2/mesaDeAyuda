<%-- 
    Document   : consultarSolicitudes
    Created on : 13/03/2020, 12:06:34
    Author     : bryan
--%>

<div style="margin-top: 20px">
    <div class="col-xl-12 col-lg-8 mx-auto">
        <div class="bg-faded rounded p-3">
            <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
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