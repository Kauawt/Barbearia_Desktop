create database dbBarbearia;
use dbBarbearia;

create table tbCliente(
codCliente int primary key,
nomeCliente varchar(50) not null,
enderecoCliente varchar(100) not null,
telefoneCliente varchar(100) not null,
cpfCliente varchar(14) not null);

select * from tbUsuario;
insert into tbCliente values(
1,
'Mauricio de Souza',
'Rua das Flores 510',
'(11)99708-73121',
'465.176.722-71',
'Ativo')

insert into tbCliente values(
2,
'Luiz Felipe',
'Rua dos bobos 0',
'(11)98728-7121',
'465.146.721-76',
'Ativo')

insert into tbCliente values(
3,
'Vanessa Camargo',
'Rua Afonso Vergueiro 25',
'(11)97348-8981',
'265.746.921-16',
'Inativo')

create table tbUsuario(
codUsuario int primary key,
nomeUsuario varchar(50) not null,
cpfUsuario varchar(50) not null,
dataNascimentoUsuario date not null,
salarioUsuario money not null,
userUsuario varchar(40) not null unique,
senhaUsuario varchar(20) not null,
perfilUsuario varchar(10) not null);

insert into tbUsuario values (
1,
'João Paulo',
'342.173.728-12',
'1996-05-21',
10000,
'Admin@gmail.com',
'Admin',
'Admin');

insert into tbUsuario values (
2,
'Fabricio Vieira',
'522.313.228-12',
'1991-08-21',
3000,
'Fabricio@hotmail.com',
'Fabricio123',
'user');

insert into tbUsuario values (
3,
'Matheus Inacio',
'999.999.999-99',
'1996-09-11',
3000,
'Matheus@gmail.com',
'Matheuzinho123',
'user');

insert into tbUsuario values (
5,
'Anthony Oliveira',
'999.999.999-99',
'2001-05-29',
3000,
'Anthony',
'senhaSenha',
'user');

create table tbServico(
codServico int primary key,
tipoServico varchar(20) not null,
descricaoServico varchar(120) not null,
precoServico float not null,
duracaoServico float not null,
statusServico varchar(10) not null);





