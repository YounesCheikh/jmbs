/* 
Title: MBS Project Sql tables
Authors: Babic Benjamin, Cheikh Younes 
Version: 1.1
 */


/* Title: MBS Project Sql tables
 Authors: Babic Benjamin, Cheikh Younes */



CREATE TABLE users ( /* Table des utilisateurs */
	iduser serial,
	name varchar(20),
	forename varchar(20),
	email varchar(200),
	pass varchar(200),
    	picture varchar(200),
	authlvl integer,

CONSTRAINT pk_users PRIMARY KEY (iduser)
);

CREATE TABLE projects (
	idproject serial,
	idowner integer NOT NULL,
	name varchar(60),
	status integer,
	
CONSTRAINT pk_projects PRIMARY KEY (idproject),
CONSTRAINT fk_projects_user FOREIGN KEY (idowner) REFERENCES users (iduser)
);

CREATE TABLE message ( /* Table des messages */
	idmessage serial,
	content varchar(600),
	time timestamp, 
	iduser integer NOT NULL,
	idproject integer,

CONSTRAINT pk_message PRIMARY KEY (idmessage),
CONSTRAINT fk_message_user FOREIGN KEY (iduser) REFERENCES users (iduser),
CONSTRAINT fk_message_project FOREIGN KEY (idproject) REFERENCES projects (idproject)
);

CREATE TABLE follows ( /* Table des suiveurs */
	follower integer NOT NULL,
	followed integer NOT NULL,

CONSTRAINT pk_f PRIMARY KEY (follower,followed),	
CONSTRAINT fk_follower_user FOREIGN KEY (follower) REFERENCES users (iduser), 
CONSTRAINT fk_followed_user FOREIGN KEY (followed) REFERENCES users (iduser)
);



CREATE TABLE participate (
	idproject integer NOT NULL,
	iduser integer NOT NULL,
	authlvl integer,

CONSTRAINT pk_participate PRIMARY KEY (idproject,iduser),
CONSTRAINT fk_part_user FOREIGN KEY (iduser) REFERENCES users (iduser), 
CONSTRAINT fk_part_project FOREIGN KEY (idproject) REFERENCES projects (idproject)
);


CREATE TABLE banned (
	ip varchar(20) UNIQUE,
	lifeban int,
	expiration Timestamp,
	reason varchar(500),

CONSTRAINT pk_banned PRIMARY KEY (ip)
);

CREATE VIEW timeline AS SELECT * FROM message WHERE idproject IS NULL;

