create database dbBarbearia;
use dbBarbearia;

create table tbCliente(
codCliente int primary key,
nomeCliente varchar(50) not null,
enderecoCliente varchar(100) not null,
telefoneCliente varchar(100) not null unique,
cpfCliente varchar(14) not null unique,
statusCliente varchar(7) not null);

create table tbUsuario(
codUsuario int primary key,
nomeUsuario varchar(50) not null,
cpfUsuario varchar(50) not null unique,
dataNascimentoUsuario date not null,
salarioUsuario money not null,
emailUsuario varchar(40) not null unique,
senhaUsuario varchar(20) not null,
perfilUsuario varchar(13) not null,
statusUsuario varchar(7) not null); 

create table tbServico(
codServico int primary key,
tipoServico varchar(20) not null unique,
descricaoServico varchar(120) not null,
precoServico float not null,
duracaoServico float not null,
statusServico varchar(10) not null);

insert into tbUsuario values (
1,
'João Paulo',
'260.263.490-57',
'1996-05-21',
10000,
'Admin@gmail.com',
'Admin',
'Administrador',
'Ativo');

insert into tbUsuario values (
2,
'Fabricio Vieira',
'091.492.780-96',
'1991-08-21',
3000,
'Fabricio@hotmail.com',
'Fabricio123',
'Funcionário',
'Ativo');

insert into tbUsuario values (
3,
'Matheus Inacio',
'164.738.720-51',
'1996-09-11',
3000,
'Matheus@gmail.com',
'Matheuzinho123',
'Funcionário',
'Inativo');

insert into tbUsuario values (
4,
'Anthony Oliveira',
'851.858.320-96',
'2001-05-29',
3000,
'Anthony@gmail.com',
'minhasenha',
'Funcionário',
'Inativo');

insert into tbUsuario values (
5,
'Sergio Ramos',
'977.279.120-009',
'1971-05-29',
3000,
'sergiramos@outlook.com',
'ramos',
'Funcionário',
'Ativo');

insert into tbCliente values(
1,
'Mauricio de Souza',
'Rua das Flores, 510',
'(11)99708-73121',
'106.554.540-12',
'Ativo')

insert into tbCliente values(
2,
'Luiz Felipe',
'Rua dos bobos, 0',
'(11)98728-7121',
'884.298.970-38',
'Inativo')

insert into tbCliente values(
3,
'Vanessa Camargo',
'Rua Afonso Vergueiro, 25',
'(11)97348-8981',
'568.967.360-97',
'Inativo')

insert into tbCliente values(
4,
'Megan Fox',
'Rua das Ostras, 35',
'(11)95448-8981',
'685.685.680-68',
'Inativo')

insert into tbCliente values(
5,
'Steve Rogers',
'Rua América, 3125',
'(11)92348-8981',
'503.581.880-24',
'Inativo')

insert into tbServico values(
1,
'Cabelo',
'Corte de cabelo simples, utilizando tesoura e navalha',
45,
40,
'Ativo')

insert into tbServico values(
2,
'Barba',
'Corte e design da barba',
30,
20,
'Ativo')

insert into tbServico values(
3,
'Sobrancelha',
'Remoção do excesso de pelos e design',
15,
10,
'Ativo')

insert into tbServico values(
4,
'Cabelo + Barba',
'Combo envolvendo os dois serviços',
50,
60,
'Ativo')

insert into tbServico values(
5,
'Nevou',
'Descoloração do cabelo e aplicação de tintura prateada',
50,
120,
'Ativo')









