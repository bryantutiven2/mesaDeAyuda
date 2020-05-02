<%-- 
    Document   : nav
    Created on : 13/03/2020, 11:54:50
    Author     : bryan
--%>

<nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin-top: 10px">
    <button class="toggle-nav btn sticky-top mr-1" data-toggle="collapse" href="#sidebar" role="button">
        <i class="fa fa-bars"></i>
    </button>
    <ul class="navbar-nav ml-auto">
        <li class="dropdown dropdown-menu-right" style="margin-top: 5%; width: 50px; margin-right: 15px;">
            <a class="dropdown-toggle count-info"  data-toggle="dropdown" href="#" aria-expanded="true" style="color: gray;">
                <i class="fa fa-bell"></i> <span class="label label-primary" id="cont-notificacion"></span>
            </a>

            <ul class="dropdown-menu dropdown-menu-right dropdown-alerts" id="encuestaMenu">
                
            </ul>

        </li>
        <li class="dropdown  ml-auto" style="margin-top: 5%;">
            <a class="dropdown-toggle count-info texto_subrayado"  data-toggle="dropdown" href="#" aria-expanded="true">
                <span id="texto_usuario" style="color: black">${username}</span>
            </a>

            <ul class="dropdown-menu dropdown-menu-right dropdown-alerts">
                <li>
                    <div class="text-center">
                        <span class="dropdown-item-text"><i class="fas fa-user"></i>&nbsp;${usuario}</span>
                    </div>
                </li>
                <li>
                    <div class="text-center">
                        <span class="dropdown-item-text"><i class="fas fa-envelope">&nbsp;</i><span id="texto_correo">${correo}</span></span>
                    </div>
                </li>
                <li class="divider"></li>
                <li>
                    <div class="text-center">
                        <a class="dropdown-item itemLO" id="logoutU" href="${pageContext.request.contextPath}/logout">Cerrar Sesión &nbsp;<i class="fa fa-sign-out"></i></a>
                    </div>

                </li>
            </ul>

        </li>
    </ul>
</nav>
