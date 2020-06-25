DROP TABLE IF EXISTS tipocambio;

CREATE TABLE tipocambio(
    id INTEGER NOT NULL AUTO_INCREMENT,
    origen VARCHAR(10) NOT NULL,
    destino VARCHAR(10) NOT NULL,
    valor DECIMAL(10,5) NOT NULL,
    PRIMARY KEY (`id`)
);