-- Tabela de Endereço
CREATE TABLE IF NOT EXISTS endereco (
    id SERIAL PRIMARY KEY,
    rua VARCHAR(255),
    logradouro VARCHAR(255),
    complemento VARCHAR(255),
    numero INTEGER
);

-- Tabela de Agência
CREATE TABLE IF NOT EXISTS agencia (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    razao_social VARCHAR(255),
    cnpj VARCHAR(20),
    endereco_id INTEGER,
    CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

