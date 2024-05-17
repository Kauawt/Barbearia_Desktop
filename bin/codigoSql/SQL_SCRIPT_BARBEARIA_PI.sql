create database dbBarbearia;
use dbBarbearia;

create table tbCliente(
codCliente int identity(1,1) primary key,
nomeCliente varchar(50) not null,
enderecoCliente varchar(100) not null,
telefoneCliente varchar(100) not null unique,
cpfCliente varchar(14) not null unique,
statusCliente varchar(7) not null);

create table tbUsuario(
codUsuario int identity(1,1) primary key,
nomeUsuario varchar(50) not null,
cpfUsuario varchar(50) not null unique,
dataNascimentoUsuario date not null,
salarioUsuario money not null,
emailUsuario varchar(40) not null unique,
senhaUsuario varchar(20) not null,
perfilUsuario varchar(13) not null,
statusUsuario varchar(7) not null); 

create table tbServico(
codServico int identity(1,1) primary key,
tipoServico varchar(20) not null unique,
descricaoServico varchar(120) not null,
precoServico float not null,
duracaoServico float not null,
statusServico varchar(10) not null);

create table tbAgendamento(
codAgendamento int identity(1,1) primary key,
codUsuario int not null references tbUsuario,
codCliente int not null references tbCliente,
codServico int not null references tbServico,
precoServico float not null, --tratar no c�digo pra puxar o valor da tabela servi�o
dataAgendamento date not null,
horaAtendimento time not null);

insert into tbUsuario values (
'Jo�o Paulo',
'260.263.490-57',
'1996-05-21',
10000,
'Admin@gmail.com',
'Admin',
'Administrador',
'Ativo');

insert into tbUsuario values (
'Fabricio Vieira',
'091.492.780-96',
'1991-08-21',
3000,
'Fabricio@hotmail.com',
'Fabricio123',
'Funcion�rio',
'Ativo');

insert into tbUsuario values (
'Matheus Inacio',
'164.738.720-51',
'1996-09-11',
3000,
'Matheus@gmail.com',
'Matheuzinho123',
'Funcion�rio',
'Inativo');

insert into tbUsuario values (
'Anthony Oliveira',
'851.858.320-96',
'2001-05-29',
3000,
'Anthony@gmail.com',
'minhasenha',
'Funcion�rio',
'Inativo');

insert into tbUsuario values (
'Sergio Ramos',
'977.279.120-009',
'1971-05-29',
3000,
'sergiramos@outlook.com',
'ramos',
'Funcion�rio',
'Ativo');

insert into tbCliente values(
'Mauricio de Souza',
'Rua das Flores, 510',
'(11)99708-73121',
'106.554.540-12',
'Ativo')

insert into tbCliente values(
'Luiz Felipe',
'Rua dos bobos, 0',
'(11)98728-7121',
'884.298.970-38',
'Inativo')

insert into tbCliente values(
'Vanessa Camargo',
'Rua Afonso Vergueiro, 25',
'(11)97348-8981',
'568.967.360-97',
'Inativo')

insert into tbCliente values(
'Megan Fox',
'Rua das Ostras, 35',
'(11)95448-8981',
'685.685.680-68',
'Inativo')

insert into tbCliente values(
'Steve Rogers',
'Rua Am�rica, 3125',
'(11)92348-8981',
'503.581.880-24',
'Inativo')

insert into tbServico values(
'Cabelo',
'Corte de cabelo simples, utilizando tesoura e navalha',
45,
40,
'Ativo')

insert into tbServico values(
'Barba',
'Corte e design da barba',
30,
20,
'Ativo')

insert into tbServico values(
'Sobrancelha',
'Remo��o do excesso de pelos e design',
15,
10,
'Ativo')

insert into tbServico values(
'Cabelo + Barba',
'Combo envolvendo os dois servi�os',
50,
60,
'Ativo')

insert into tbServico values(
'Nevou',
'Descolora��o do cabelo e aplica��o de tintura prateada',
50,
120,
'Ativo')

insert into tbAgendamento values(1, 2, 3, 30,'2000-05-20','15:30')
insert into tbAgendamento values(2, 2, 2, 20,'2024-05-17', '14:00')
insert into tbAgendamento values(3, 1, 4, 40,'2024-05-18', '08:00')
insert into tbAgendamento values(5, 3, 5, 50,'2024-05-20', '10:00')
insert into tbAgendamento values(4, 4, 4, 70,'2024-05-21', '17:00')

select * from tbAgendamento
select * from tbUsuario
select * from tbCliente
select * from tbServico