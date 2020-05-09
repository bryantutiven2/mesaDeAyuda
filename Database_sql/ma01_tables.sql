alter session set nls_date_format = 'DD/MM/YYYY hh24:mi:ss';

CREATE TABLE departamento(
  id_departamento INTEGER  NOT NULL,
  nombre_departamento VARCHAR2(100) NOT NULL,
  estado_borrado INTEGER DEFAULT 0,
  PRIMARY KEY(id_departamento)
);

CREATE TABLE grupo(
  id_grupo VARCHAR(5) NOT NULL,
  nombre_grupo VARCHAR2(50) NOT NULL,
  estado_borrado INTEGER DEFAULT 0,
  PRIMARY KEY(id_grupo)
);
  
CREATE TABLE tipo_grupo(
  id_tipo INTEGER  NOT NULL,
  nombre_tipo VARCHAR2(50) NOT NULL UNIQUE,
  estado_borrado INTEGER DEFAULT 0,
  id_grupo VARCHAR(5) NOT NULL,
  PRIMARY KEY(id_tipo),
  CONSTRAINT fk_id_grupo
    FOREIGN KEY (id_grupo)
    REFERENCES grupo(id_grupo)
);

CREATE TABLE subtipo(
  id_subtipo INTEGER NOT NULL,
  nombre_subtipo VARCHAR2(50) NOT NULL UNIQUE,
  estado_borrado INTEGER DEFAULT 0,
  id_tipo INTEGER NOT NULL,
  PRIMARY KEY(id_subtipo),
  CONSTRAINT fk_id_tipo
    FOREIGN KEY (id_tipo)
    REFERENCES tipo_grupo(id_tipo)
);

CREATE TABLE usuario(
  id_usuario INTEGER NOT NULL,
  nombre VARCHAR2(50) NOT NULL,
  apellido VARCHAR2(50) NOT NULL,
  usuario VARCHAR2(50) NOT NULL UNIQUE,
  contrasena VARCHAR(50) NOT NULL,
  correo VARCHAR2(200) NOT NULL UNIQUE,
  rol VARCHAR2(50) NOT NULL,
  estado_borrado INTEGER DEFAULT 0,
  id_tipo INTEGER,
  id_departamento INTEGER NOT NULL,
  PRIMARY KEY(id_usuario),
  CONSTRAINT fk_id_tipo_u
    FOREIGN KEY (id_tipo)
    REFERENCES tipo_grupo(id_tipo),
  CONSTRAINT fk_id_departamento
    FOREIGN KEY (id_departamento)
    REFERENCES departamento(id_departamento)
);

CREATE TABLE encuesta (
  id_encuesta INTEGER NOT NULL,
  nombre VARCHAR2(50) NOT NULL,
  descripcion VARCHAR2(1000) NOT NULL,
  codigo_embebido VARCHAR2(1000) NOT NULL,
  codigo_registro VARCHAR2(25) NOT NULL,
  estado_borrado INTEGER DEFAULT 0,
  PRIMARY KEY(id_encuesta)
);

CREATE TABLE solicitud_ayuda(
  id_solicitud INTEGER NOT NULL,
  descripcion VARCHAR2(1000),
  mensaje_user_tecnico VARCHAR2(1000),
  ayuda_n_vez INTEGER,
  ids_solicitud_n_vez VARCHAR2(500),
  fecha_inicio DATE,
  fecha_fin DATE,
  fecha_inicio_tecnico DATE,
  fecha_fin_tecnico DATE,
  estado_solicitud VARCHAR(25),
  estado_solicitud_tecnico VARCHAR(25),
  estado_borrado INTEGER DEFAULT 0,
  estado_encuesta INTEGER DEFAULT 0,
  id_grupo VARCHAR(5) NOT NULL,
  id_tipo INTEGER,
  id_subtipo INTEGER,
  id_user_admin INTEGER,
  id_user_solicita_ayuda INTEGER,
  id_user_tecnico INTEGER,
  id_encuesta INTEGER,
  PRIMARY KEY(id_solicitud,id_grupo),
  CONSTRAINT fk_id_grupo_s
    FOREIGN KEY (id_grupo)
    REFERENCES grupo(id_grupo),
  CONSTRAINT fk_id_tipo_s
    FOREIGN KEY (id_tipo)
    REFERENCES tipo_grupo(id_tipo),
  CONSTRAINT fk_id_subtipo_s
    FOREIGN KEY (id_subtipo)
    REFERENCES subtipo(id_subtipo),
  CONSTRAINT fk_id_user_admin_s
    FOREIGN KEY (id_user_admin)
    REFERENCES usuario(id_usuario),
  CONSTRAINT fk_id_user_solicita_ayuda_s
    FOREIGN KEY (id_user_solicita_ayuda)
    REFERENCES usuario(id_usuario),
  CONSTRAINT fk_id_user_tecnico_s
    FOREIGN KEY (id_user_tecnico)
    REFERENCES usuario(id_usuario),
  CONSTRAINT fk_id_encuesta
    FOREIGN KEY (id_encuesta)
    REFERENCES encuesta(id_encuesta)
);

CREATE TABLE observacion (
  id_observacion INTEGER NOT NULL,
  mensaje VARCHAR2(200) NOT NULL,
  fecha_mensaje DATE,
  id_solicitud INTEGER NOT NULL,
  id_grupo VARCHAR(5) NOT NULL,
  id_usuario INTEGER NOT NULL,
  PRIMARY KEY(id_observacion),
  CONSTRAINT fk_id_user_o
    FOREIGN KEY (id_usuario)
    REFERENCES usuario(id_usuario),
  CONSTRAINT fk_id_solicitud_o
    FOREIGN KEY (id_solicitud, id_grupo)
    REFERENCES solicitud_ayuda(id_solicitud, id_grupo)
);

CREATE TABLE auditoria(
       id_auditoria INTEGER NOT NULL,
       tipo_evento VARCHAR2(50),
       fecha_transaccion DATE,
       fecha_gra DATE,
       tabla_mod VARCHAR2(50),
       campo_mod VARCHAR2(50),
       dato_anterior VARCHAR2(1000),
       dato_nuevo VARCHAR2(1000),
       usuario VARCHAR2(15),
       pcid VARCHAR2(50),
       ip VARCHAR2(50),
       PRIMARY KEY(id_auditoria)
);
