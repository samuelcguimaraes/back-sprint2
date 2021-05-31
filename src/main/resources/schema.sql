CREATE TABLE IF NOT EXISTS pagamento (
   id bigint(20) NOT NULL AUTO_INCREMENT,
   valor decimal(19,2) NOT NULL,
   nome_cliente varchar(100) NOT NULL,
   numero_cartao varchar(50) NOT NULL,
   expiracao_cartao varchar(10) NOT NULL,
   codigo_verificacao_cartao varchar(3) NOT NULL,
   status enum('CRIADO', 'CONFIRMADO', 'CANCELADO') NOT NULL,
   PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
