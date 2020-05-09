insert into grupo values('sist', 'sistemas', 0);

insert into grupo values('mant', 'mantenimiento',0);

insert into tipo_grupo values(tipo_seq.nextval, 'software',0,'sist');

insert into tipo_grupo values(tipo_seq.nextval, 'hardware',0,'sist');

insert into subtipo values(subtipo_seq.nextval, 'sistema notas',0,1);
insert into subtipo values(subtipo_seq.nextval, 'impresora',0,2);

insert into departamento values(departamento_seq.nextval, 'Departamento de Sistemas',0);
insert into departamento values(departamento_seq.nextval, 'Departamento de Administracion',0);

insert into usuario
  values(usuario_seq.nextval, 'bryan','tutiven','btutiven','123','btutiven@anai.edu.ec','admin_sist',0,1,1);

insert into usuario(id_usuario,nombre, apellido, usuario, contrasena, correo, rol, estado_borrado,id_departamento)
  values(usuario_seq.nextval,'ngeneral','ageneral','general','123','general@anai.edu.ec','general_acad',0,2);
  
insert into usuario
  values(usuario_seq.nextval, 'ntecnico1','atecnico1','tecnico1','123','tecnico1@anai.edu.ec','tecnico_sist',0,1,1);

insert into usuario
  values(usuario_seq.nextval, 'ntecnico2','atecnico2','tecnico2','123','tecnico2@anai.edu.ec','tecnico_sist',0,2,1);
  
select * from grupo;

select * from tipo_grupo;

select * from usuario;

select * from solicitud_ayuda;

select * from solicitud_ayuda where TO_CHAR(fecha_fin_tecnico) = TO_CHAR(current_date);

select current_date from dual;

select to_date ('05-SEP-2007 10:00 AM','DD-MON-YYYY HH:MI AM') from dual;

select encuesta_seq.CURRVAL from dual;

select usuario_seq.nextval from dual;

select tipo_seq.nextval from dual;


