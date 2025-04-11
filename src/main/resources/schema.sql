CREATE TABLE credito (
                         id SERIAL PRIMARY KEY,
                         numero_credito VARCHAR(255),
                         numero_nfse VARCHAR(255),
                         data_constituicao DATE,
                         valor_issqn NUMERIC,
                         tipo_credito VARCHAR(255),
                         simples_nacional BOOLEAN,
                         aliquota NUMERIC,
                         valor_faturado NUMERIC,
                         valor_deducao NUMERIC,
                         base_calculo NUMERIC
);
