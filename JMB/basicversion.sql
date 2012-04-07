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

CONSTRAINT pk_users PRIMARY KEY (iduser)
);


CREATE TABLE message ( /* Table des messages */
	idmessage serial,
	content varchar(600),
	time timestamp, 
	iduser integer NOT NULL,

CONSTRAINT pk_message PRIMARY KEY (idmessage),
CONSTRAINT fk_message_user FOREIGN KEY (iduser) REFERENCES users (iduser)
);

CREATE TABLE follows ( /* Table des suiveurs */
	follower integer NOT NULL,
	followed integer NOT NULL,

CONSTRAINT pk_f PRIMARY KEY (follower,followed),	
CONSTRAINT fk_follower_user FOREIGN KEY (follower) REFERENCES users (iduser), 
CONSTRAINT fk_followed_user FOREIGN KEY (followed) REFERENCES users (iduser)
);

CREATE TABLE projects (
	idproject serial,
	name varchar(20),
	
CONSTRAINT pk_project PRIMARY_KEY (idproject)
);

CREATE TABLE participate (
	idproject integer NOT NULL,
	iduser integer NOT NULL,
	authlvl integer,

CONSTRAINT fk_part_user FOREIGN KEY (iduser) REFERENCES users (iduser), 
CONSTRAINT fk_part_project FOREIGN KEY (idproject) REFERENCES projects (idprojects)
);



