CREATE DATABASE IF NOT EXISTS clinica;

USE clinica;
CREATE TABLE IF NOT EXISTS pais(
  ID_pais int not null AUTO_INCREMENT,
  pais varchar(230) not null UNIQUE,
  PRIMARY KEY (ID_pais)
);

USE clinica;
CREATE TABLE IF NOT EXISTS estado(
  ID_estado int not null AUTO_INCREMENT,
  ID_pais int not null,
  estado varchar(100) not null UNIQUE,
  abreviacao varchar(4) not null,
  FOREIGN KEY (ID_pais) REFERENCES pais ( ID_pais ),
  PRIMARY KEY (ID_estado)
);

USE clinica;
CREATE TABLE IF NOT EXISTS cidades(
  ID_cidade int not null AUTO_INCREMENT,
  ID_estado int not null,
  cidade varchar(230) not null,
  FOREIGN KEY (ID_estado) REFERENCES estado ( ID_estado ),
  PRIMARY KEY (ID_cidade)
);

USE clinica;
CREATE TABLE IF NOT EXISTS bairros(
  ID_bairro int not null AUTO_INCREMENT,
  ID_cidade int not null,
  bairro varchar(230) not null,
  FOREIGN KEY (ID_cidade) REFERENCES cidades ( ID_cidade ),
  PRIMARY KEY (ID_bairro)
);

USE clinica;
CREATE TABLE IF NOT EXISTS ruas(
  ID_rua int not null AUTO_INCREMENT,
  ID_bairro int not null,
  rua varchar(230) not null,
  FOREIGN KEY (ID_bairro) REFERENCES bairros ( ID_bairro ),
  PRIMARY KEY (ID_rua)
);


USE clinica;
CREATE TABLE IF NOT EXISTS sexos(
  ID_sexo int not null AUTO_INCREMENT,
  sexo varchar(200) not null UNIQUE,
  PRIMARY KEY(ID_sexo)
);

USE clinica;
CREATE TABLE IF NOT EXISTS clientes (
  ID_cliente int not null AUTO_INCREMENT,
  cpf varchar(16) not null,
  nome varchar(220) not null,
  mae varchar(220),
  pai varchar(220),
  data_nascimento date not null,
  cartao_sus varchar(50) UNIQUE,
  ID_sexo int not null,
  email VARCHAR (250) ,
  foto TEXT,
  FOREIGN KEY (ID_sexo) REFERENCES sexos(ID_sexo),
  PRIMARY KEY(ID_cliente)

);


USE clinica;
CREATE TABLE IF NOT EXISTS corpo_cliente(
  ID_corpo_cliente int not null AUTO_INCREMENT,
  ID_cliente int not null , 
  peso float,
  altura float,
  quadril FLOAT,
  data_corpo_cliente date not null,
  FOREIGN KEY (ID_cliente) REFERENCES clientes ( ID_cliente ),
  PRIMARY KEY(ID_corpo_cliente)
);

USE clinica;
CREATE TABLE IF NOT EXISTS endereco_cliente (
  ID_endereco_cliente int not null AUTO_INCREMENT,
  ID_cliente int not null UNIQUE, 
  telefone varchar(21),
  telefone_fixo varchar(21),
  ID_cidade int not null,
  ID_rua int,
  ID_bairro int,
  numero_casa int,
  complemento TEXT,
  FOREIGN KEY (ID_cidade) REFERENCES cidades ( ID_cidade ),
  FOREIGN KEY (ID_rua) REFERENCES ruas ( ID_rua ),
  FOREIGN KEY (ID_bairro) REFERENCES bairros ( ID_bairro ),
  FOREIGN KEY (ID_cliente) REFERENCES clientes ( ID_cliente ),
  PRIMARY KEY(ID_endereco_cliente)
);
USE clinica;
CREATE TABLE IF NOT EXISTS ficha(
  ID_ficha int not null AUTO_INCREMENT,
  ID_cliente int not null,
  data_ficha date,
  observacao text,
  FOREIGN KEY (ID_cliente) REFERENCES clientes ( ID_cliente ),
  PRIMARY KEY(ID_ficha)
);

USE clinica;
CREATE TABLE IF NOT EXISTS clinica(
    ID_clinica int not null AUTO_INCREMENT,
    nome varchar(220) not null,
    PRIMARY KEY(ID_clinica)
);
USE clinica;
CREATE TABLE IF NOT EXISTS endereco_clinica (
  ID_endereco_clinica int not null AUTO_INCREMENT,
  ID_clinica int not null UNIQUE, 
  telefone varchar(21),
  telefone_fixo varchar(21),
  ID_cidade int not null,
  ID_rua int,
  ID_bairro int,
  numero_casa int,
  complemento TEXT,
  FOREIGN KEY (ID_cidade) REFERENCES cidades ( ID_cidade ),
  FOREIGN KEY (ID_rua) REFERENCES ruas ( ID_rua ),
  FOREIGN KEY (ID_bairro) REFERENCES bairros ( ID_bairro ),
  FOREIGN KEY (ID_clinica) REFERENCES clinica ( ID_clinica ),
  PRIMARY KEY(ID_endereco_clinica)
);
USE clinica;
CREATE TABLE IF NOT EXISTS usuario(
    ID_usuario int not null AUTO_INCREMENT,
    Nome varchar(220) not null,
    Sobrenome varchar(220) not null,
    Senha varchar(50) not null,
    ID_sexo int not null,
    DataNascimento date,
    Usuario varchar(100) not null UNIQUE,
    Email varchar(220) not null UNIQUE,
    FOREIGN KEY (ID_sexo) REFERENCES sexos(ID_sexo),  
    PRIMARY KEY(ID_usuario)
);
USE clinica;
CREATE TABLE IF NOT EXISTS agenda( 
    ID_agenda int not null AUTO_INCREMENT,
    ID_usuario int not null,
    ID_cliente int not null,
    ID_clinica int not null,
    data_agenda date not null,
    horario time not null,
    Observacao text,
    FOREIGN KEY (ID_usuario) REFERENCES usuario ( ID_usuario ),
    FOREIGN KEY (ID_cliente) REFERENCES clientes ( ID_cliente ),
    FOREIGN KEY (ID_clinica) REFERENCES clinica ( ID_clinica ),
    PRIMARY KEY(ID_agenda)
);

USE clinica;
CREATE TABLE IF NOT EXISTS homograma(
  ID_homograma int not null AUTO_INCREMENT,
  ID_ficha int not null,
  data_do_exame date,
  ht char,
  hb char,
  hm char,
  basofilos_porcento float,
  basofilos_mm float,
  eosinofilos_porcento float,
  eosinofilos_mm float,
  metamielocitos_porcento float,
  metamielocitos_mm float,
  bastonetes_porcento float,
  bastonetes_mm float,
  linfocitos_porcento float,
  linfocitos_mm float,
  linfocitos_atip_porcento float,
  linfocitos_atip_mm float,
  monocitos_porcento float,
  monocitos_mm float,
  obs TEXT,
  global_leucocitos char, 
  FOREIGN KEY(ID_ficha) REFERENCES ficha(ID_ficha), 
  PRIMARY KEY(ID_homograma)
);

USE clinica;
CREATE TABLE IF NOT EXISTS fezes(
  ID_fezes int not null AUTO_INCREMENT,
  ID_ficha int not null,
  data_do_exame date,
  sangue_oculto char,
  observacao TEXT,
  FOREIGN KEY(ID_ficha) REFERENCES ficha(ID_ficha),
  PRIMARY KEY(ID_fezes)
);

USE clinica;
CREATE TABLE IF NOT EXISTS helmito(
  ID_helmito int not null AUTO_INCREMENT,
  ID_fezes int not null,
  a_lumbricoides float,
  t_trichiura float,
  e_vermicularis float,
  ancilostomideos float,
  s_stercoralis float,
  taenia_sp float,
  outros text,
  FOREIGN KEY (ID_fezes) REFERENCES fezes(ID_fezes),
  PRIMARY KEY(ID_helmito)
);

USE clinica;
CREATE TABLE IF NOT EXISTS protozoarios(
  ID_protozoarios int not null AUTO_INCREMENT,
  ID_fezes int not null,
  e_histolitica float,
  e_coli float,
  g_lamblia float,
  outros text,
  FOREIGN KEY (ID_fezes) REFERENCES fezes(ID_fezes),
  PRIMARY KEY(ID_protozoarios)
);

USE clinica;
CREATE TABLE IF NOT EXISTS eas(
  ID_eas int not null AUTO_INCREMENT,
  ID_ficha int not null,
  data_do_exame date,
  cor varchar(120),
  aspecto varchar(120),
  deposito  varchar(120),
  urobilinogenio  varchar(120),
  glicose  varchar(120),
  bilirrubina  varchar(120),
  cetona  varchar(120),
  densidade  varchar(120),
  sangue  varchar(120),
  ph  varchar(120),
  proteinas  varchar(120),
  nitritos  varchar(120),
  leucocitos  varchar(120),
  FOREIGN KEY(ID_ficha) REFERENCES ficha(ID_ficha),
  PRIMARY KEY(ID_eas)
);

USE clinica;
CREATE TABLE IF NOT EXISTS sedimentoscopia(
  ID_sedimentoscopia int not null AUTO_INCREMENT,
  ID_ficha int not null,
  data_do_exame date,
  celulas_epiteliais varchar(120),
  muco varchar(120),
  hemacia varchar(120),
  piocitos varchar(120),
  cilindros varchar(120),
  cristais varchar(120),
  flora varchar(120),
  obs text,
  FOREIGN KEY(ID_ficha) REFERENCES ficha(ID_ficha),
  PRIMARY KEY(ID_sedimentoscopia)
);

