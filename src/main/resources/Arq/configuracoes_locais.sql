/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Pedro Marinho <pedro.marinho238@gmail.com & https://github.com/TECFlyingCommunity>
 * Created: 01/03/2019
 */
CREATE TABLE IF NOT EXISTS dados_db(
    ID_banco_de_dados int not null AUTO_INCREMENT,
    Hosts varchar(100) not null,
    Users varchar(100) not null,
    Passwords varchar(100),
    DataBases varchar(100) not null,
    Prefixs varchar(100) not null,
    Ports int not null 
    PRIMARY KEY (ID_banco_de_dados)
);

