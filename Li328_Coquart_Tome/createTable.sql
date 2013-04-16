create table users (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	login VARCHAR(32),
	mdp VARCHAR(32),
	nom BLOB,
	prenom BLOB,
	date DATE
)


create table session (
	clef VARCHAR(32),
	id BIGINT references users (id),
	perime BOOLEAN, 
	time TIMESTAMP
)

create table friend (
	id BIGINT references users (id),
	ami BIGINT references users (id)
	time TIMESTAMP
)
