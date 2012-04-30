CREATE TABLE users
(
  iduser serial NOT NULL,
  "name" character varying(20) NOT NULL,
  forename character varying(20) NOT NULL,
  email character varying(200) NOT NULL,
  pass character varying(200) NOT NULL,
  picture character varying(200),
  authlvl integer NOT NULL,
  CONSTRAINT pk_users PRIMARY KEY (iduser)
);

CREATE TABLE projects
(
  idproject serial NOT NULL,
  idowner integer NOT NULL,
  "name" character varying(20),
  status integer,
  nbsuscribers integer,
  iseditallowed integer,
  issupressionallowed integer,
  ispublic integer,
  creationtime timestamp,

  CONSTRAINT pk_projects PRIMARY KEY (idproject),
  CONSTRAINT fk_projects_user FOREIGN KEY (idowner)
      REFERENCES users (iduser)
);



CREATE TABLE "connection"
(
  idconnection serial NOT NULL,
  ip character varying(20) NOT NULL,
  iduser integer,
  begintime timestamp without time zone,
  endtime timestamp without time zone,
  CONSTRAINT pk_connect PRIMARY KEY (idconnection)
);

CREATE TABLE follows
(
  follower integer NOT NULL,
  followed integer NOT NULL,
  CONSTRAINT pk_f PRIMARY KEY (follower, followed)
);

CREATE TABLE message
(
  idmessage serial NOT NULL,
  "content" character varying(600),
  "time" timestamp without time zone,
  iduser integer NOT NULL,
  idproject integer,
  edittime timestamp without time zone,
  idedituser integer,
  CONSTRAINT pk_message PRIMARY KEY (idmessage),
  CONSTRAINT fk_message_edit FOREIGN KEY (idedituser)
      REFERENCES users (iduser),
  CONSTRAINT fk_message_pj FOREIGN KEY (idproject)
      REFERENCES projects (idproject),
  CONSTRAINT fk_message_u FOREIGN KEY (iduser)
      REFERENCES users (iduser)
);

CREATE TABLE participate
(
  idproject integer NOT NULL,
  iduser integer NOT NULL,
  authlvl integer,
  CONSTRAINT pk_participate PRIMARY KEY (idproject, iduser)
);

CREATE VIEW projects_messages AS SELECT * FROM message WHERE idproject IS NOT NULL;
CREATE VIEW timeline_messages AS SELECT * FROM message WHERE idproject IS NULL;
