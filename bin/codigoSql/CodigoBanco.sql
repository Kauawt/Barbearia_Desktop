create database dbBarbearia;
use dbBarbearia;

create table tbCliente(
codCliente int primary key,
nomeCliente varchar(50) not null,
enderecoCliente varchar(100) not null,
telefoneCliente varchar(100) not null,
cpfCliente varchar(14) not null);


insert into tbCliente values(
1,
'Mauricio de Souza',
'Rua das Flores 510',
'(11)99708-73121',
'465.176.722-71')

insert into tbCliente values(
2,
'Luiz Felipe',
'Rua dos bobos 0',
'(11)98728-7121',
'465.146.721-76')

insert into tbCliente values(
3,
'Vanessa Camargo',
'Rua Afonso Vergueiro 25',
'(11)97348-8981',
'265.746.921-16')

create table tbBarbeiro(
codUsuario int primary key,
nomeUsuario varchar(50) not null,
cpfUsuario varchar(50) not null,
dataNascimento date not null,
salarioUsuario money not null,
userUsuario varchar(10) not null unique,
senhaUsuario varchar(15) not null);


insert into tbBarbeiro values (
1,
'João Paulo',
'342.173.728-12',
'1996-05-21',
200,
'Admin',
'Admin');

insert into tbBarbeiro values (
2,
'Fabricio',
'522.313.228-12',
'1991-08-21',
200,
'Fabricio',
'Fabricio123');




