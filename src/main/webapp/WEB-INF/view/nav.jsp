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
        <li class="dropdown  ml-auto" style="margin-right: 17px">
            <button class="btn btn-light dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span id="texto_usuario" style="padding-left: 20px; padding-right: 20px;">${username}</span>
              </button>
              <div class="dropdown-menu text-center">
                  <span class="dropdown-item-text"><i class="fas fa-user"></i>&nbsp;${usuario}</span>
                  <span id="texto_correo" class="dropdown-item-text">${correo}</span>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item" id="logoutU" href="${pageContext.request.contextPath}/login">Cerrar Sesión &nbsp;<i class="fas fa-key"></i></a>
              </div>
        </li>
    </ul>
</nav>
