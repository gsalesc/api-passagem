CREATE TABLE aeroporto (
	id INT NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL, 
    sigla VARCHAR(6) NOT NULL,
    pais VARCHAR(30) NOT NULL,
    
    CONSTRAINT UQ_sigla UNIQUE (sigla)
);

/*CREATE TABLE passageiro (
	id INT NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    dataNascimento DATE NOT NULL,
    telefone VARCHAR(15) NOT NULL, 
    email CHAR(40) NOT NULL,
    
    CONSTRAINT UQ_cpf UNIQUE (cpf)
);*/

/*CREATE TABLE voo (
	id INT NOT NULL PRIMARY KEY,
    origem_id INT NOT NULL,
    destino_id INT NOT NULL,
    diaPartida DATE NOT NULL,
    horaPartida TIME NOT NULL,
    tempoEstimado DECIMAL NOT NULL,
    precoMinimo DECIMAL NOT NULL,
    qtdParadas INT NOT NULL,
    
    CONSTRAINT FK_voo_origem_id FOREIGN KEY (origem_id) REFERENCES aeroporto(id),
    CONSTRAINT FK_voo_destino_id FOREIGN KEY (destino_id) REFERENCES aeroporto(id),
    CONSTRAINT CK_voo_precoMinimo CHECK (precoMinimo > 0)
);*/

CREATE TABLE poltrona (
	id INT NOT NULL PRIMARY KEY,
    sigla VARCHAR(5) NOT NULL,
    situacao INT,
    classe INT,
    voo_id INT NOT NULL,
    passageiro_id INT NOT NULL
    
    /*CONSTRAINT FK_poltrona_voo_id FOREIGN KEY (voo_id) REFERENCES voo(id),
    CONSTRAINT FK_poltrona_passageiro_id FOREIGN KEY (passageiro_id) REFERENCES passageiro(id)*/
);

/*CREATE TABLE passagem (
	id INT NOT NULL PRIMARY KEY,
    voo_id INT NOT NULL,
    poltrona_id INT NOT NULL,
    passageiro_id INT NOT NULL, 
    preco DECIMAL NOT NULL,
    classe INT NOT NULL
    
	/*CONSTRAINT FK_passagem_voo_id FOREIGN KEY (voo_id) REFERENCES voo(id),
    CONSTRAINT FK_passagem_poltrona_id FOREIGN KEY (poltrona_id) REFERENCES poltrona(id),
    CONSTRAINT FK_passagem_passageiro_id FOREIGN KEY (passageiro_id) REFERENCES passageiro(id)*/
);*/