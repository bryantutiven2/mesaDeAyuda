
function postAjaxCrearUsuario(datos){
    $.ajax({
       type: 'POST',
       contentType: 'application/json',
       url: '/mesaayuda/dashboardUsuario/crearUsuario',
       data: JSON.stringify(datos),
       success: function(response){
            $("#mensajeModal").empty();
            if(response.status == "exito"){
                $(".loader").removeClass("hidden"); //remover loader
                reiniciaForm();
               $("#mensajeModal").html('<i class="fas fa-check-circle" style="color: #02D90C;font-size: 24pt;margin-right: 30px;"></i> Se ha creado el usuario');
               $("#modalMensaje").modal();
            }
            else if(response.status == "error"){
                $(".loader").removeClass("hidden"); //remover loader
                $("#mensajeModal").html('<i class="fas fa-times" style="color: #04A9C8;font-size: 24pt;margin-right: 30px;"></i> No se ha podido crear el usuario');
                $("#modalMensaje").modal();
            }
        }
    });
}

$(document).ready(function(){
    $("#formUsuarioD").submit(function(e){
        e.preventDefault();
        let datos;
        let nombreU = $('#nombreUser').val();
        let apellidoU = $('#apellidoUser').val();
        let usuario = $('#nUser').val();
        let pass = $('#contrasenaUser').val();
        let email = $('#correoUser').val();
        let depart = document.getElementById("departamentoUser").value;
        let rolU = document.getElementById("rolUser").value;
        let tipo = document.getElementById("tipoUser").value;
        if(tipo == '')
            tipo = null;
        datos = {
            nombre: nombreU,
            apellido: apellidoU,
            username: usuario,
            password: pass,
            correo: email,
            idDepartamento: depart,
            rol: rolU,
            idTipo: tipo
        };
        console.log(datos);
        $(".loader").addClass("hidden");
        postAjaxCrearUsuario(datos);
    });
});

function reiniciaForm(){
    $('#nombreUser').val("");
    $('#apellidoUser').val("");
    $('#nUser').val("");
    $('#contrasenaUser').val("");
    $('#correoUser').val("");
    let depart = document.getElementById("departamentoUser");
    depart.options[0].selected=true;
    let rolU = document.getElementById("rolUser");
    rolU.options[0].selected=true;
    let tipo = document.getElementById("tipoUser");
    tipo.options[0].selected=true;
}