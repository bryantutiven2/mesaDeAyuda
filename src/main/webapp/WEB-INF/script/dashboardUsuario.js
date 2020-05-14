/*mostrar u ocultar contraseña*/
function mostrarOcultar() {
  let pass = document.getElementById("contrasenaUser");
  if (pass.type === "password") {
    pass.type = "text";
  } else {
    pass.type = "password";
  }
}

/*ocultar div para crear o actualiza usuario*/
$( document ).ready(function() {
    $(document).on("click", '#eyeO', function(){
        if ($('#divCrearUsuario').is(':visible')) {
            $('#divCrearUsuario').hide();
        } else {
            $('#divCrearUsuario').show();
        }
    });
});

/*solicitud ajax con método post para crear usuarios*/
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

/*solicitud ajax para cargar usuarios en datatable con método getw*/
function postAjaxCargarUsuarios(datos){
    $.ajax({
    type: 'POST',
    contentType: 'application/json',
    url: '/mesaayuda/dashboardUsuario/cargarUsuario',
    data: JSON.stringify(datos),
    success: function(result){
        if(result.status == "success"){
            cargarEstiloT();
            let tableBody = $('#tableConsultarUsuarioD').DataTable();
            $.each(result.data, function(i, dato){
                let tr = '<tr>'+
                                '<td>'+dato.idUsuario+'</td>'+
                                '<td>'+dato.nombre+'</td>'+
                                '<td>'+dato.apellido+'</td>'+
                                '<td>'+dato.username+'</td>'+
                                '<td>'+dato.password+'</td>'+
                                '<td>'+dato.correo+'</td>'+
                                '<td>'+dato.rol+'</td>'+
                                '<td>'+dato.idTipo+'</td>'+
                                "<td>"+
                                    "<div class='text-center'>"+
                                        "<div class='btn-group'>"+
                                            "<button class='btn btn-primary btn-sm btnEditar'>"+
                                                "<i class='fas fa-edit'></i> Editar"+
                                            "</button>"+
                                        "</div>"+
                                    "</div>"+
                                "</td>"+
                        '</tr>';
                $(".loader").removeClass("hidden"); //remover loader
                tableBody.row.add($(tr)).draw();
            });
        }
        if(result.status == "error"){
            $(".loader").removeClass("hidden"); //remover loader
            $("#bodyTableEncuesta").html('<strong>No hay usuarios</strong>');
        }
    },
    error : function(e) {
        $(".loader").removeClass("hidden"); //remover loader
        $("#bodyTableEncuesta").html("<strong>Error</strong>");
        }
    });
}
/*hacer la consulat de usuarios por departamento*/
$(document).ready(function(){
    $("#formFiltroU").submit(function(e){
        e.preventDefault();
        let datos;
        let codDepartamento = document.getElementById("departamentoUsuario").value;
        datos = {
            idDepartamento: codDepartamento
        };
        $('#tableConsultarUsuarioD').show();
        $(".loader").addClass("hidden");
        postAjaxCargarUsuarios(datos);
    });
});

/*crear el json usuario para crear un usuario*/
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

function cargarEstiloT() {
  let tableBody = $('#tableConsultarUsuarioD').DataTable({
      retrieve: true,
    //para cambiar el lenguaje a español
        "language": {
            "lengthMenu": "Mostrar _MENU_ registros",
            "zeroRecords": "No se encontraron resultados",
            "info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "infoFiltered": "(filtrado de un total de _MAX_ registros)",
            "sSearch": "Buscar:",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast":"Último",
                "sNext":"Siguiente",
                "sPrevious": "Anterior"
                         },
                         "sProcessing":"Procesando...",
        }
    });
    tableBody.clear().draw();
};