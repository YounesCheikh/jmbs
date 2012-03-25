/* 
Title: MBS Project Sql tables
Authors: Babic Benjamin, Cheikh Younes 
Version: 1.0
 */


/* Title: MBS Project Sql tables
 Authors: Babic Benjamin, Cheikh Younes */


CREATE TABLE acess ( /*Table des droits d'access.*/
	acesslevel integer NOT NULL,
	title varchar(100),
	
CONSTRAINT pk_acess PRIMARY KEY (acesslevel)
);


CREATE TABLE users ( /* Table des utilisateurs */
	iduser integer NOT NULL,
	name varchar(20),
	forename varchar(20),
	acesslevel integer,
	email varchar(200),
	pass varchar(200),

CONSTRAINT pk_users PRIMARY KEY (iduser),
CONSTRAINT fk_user_four FOREIGN KEY (acesslevel) REFERENCES acess (acesslevel)  
);


CREATE TABLE project ( /* Table décrivant un projet */
	idproject integer NOT NULL,
	name varchar(50),

CONSTRAINT pk_pj PRIMARY KEY (idproject)
);


CREATE TABLE post ( /* Table des postes */
	idpost integer NOT NULL,
	name varchar(50),

CONSTRAINT pk_post PRIMARY KEY (idpost )
);


CREATE TABLE participate ( /* Association ternaire entre un utilisateur, un poste et un projet.*/
	idproject integer NOT NULL,
	iduser integer NOT NULL,
	idpost integer NOT NULL,

CONSTRAINT pk_pp PRIMARY KEY (idproject, iduser,idpost),
CONSTRAINT fk_pp_post FOREIGN KEY (idpost) REFERENCES post (idpost),  
CONSTRAINT fk_pp_user FOREIGN KEY (iduser) REFERENCES users (iduser), 
CONSTRAINT fk_pp_project FOREIGN KEY (idproject) REFERENCES project (idproject)  
);

CREATE TABLE message ( /* Table des messages */
	idmessage integer NOT NULL,
	content varchar(600),
	time timestamp, 
	iduser integer NOT NULL,
	title varchar(100),

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

CREATE TABLE avatar (
	idavatar integer NOT NULL,
	url varchar(500),
	iduser integer NOT NULL,

CONSTRAINT pk_av PRIMARY KEY (idavatar),
CONSTRAINT fk_av_user FOREIGN KEY (iduser) REFERENCES users (iduser),
);




