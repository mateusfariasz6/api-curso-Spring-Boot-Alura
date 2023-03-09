create TABLE medicos(
    id BEGIN NOT NULL auto_increment,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    crm VARCHAR(6) NOT NULL UNIQUE,
    especialidade VARCHAR(100) NOT NULL,
    logradouro VARCHAR(300) NOT NULL,
    bairro VARCHAR(300) NOT NULL,
    cep VARCHAR(100) NOT NULL,
    complemento VARCHAR(500),
    numero VARCHAR(30),
    uf CHAR(2) not null,
    cidade VARCHAR(300) NOT NULL,
    PRIMARY KEY(id)
);