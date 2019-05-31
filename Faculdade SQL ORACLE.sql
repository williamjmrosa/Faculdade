create table FacEndereco(
idEndereco number not null,
rua varchar2(30) not null,
cidade varchar2(30) not null,
bairro varchar2(30) not null,
estado varchar2(30) not null,
numero int not null,
cep varchar2(10) not null,
primary key(idEndereco));

create table FacTelefone(
idTelefone number not null,
tipo varchar2(15) not null,
numero varchar2(20) not null,
primary key(idTelefone));

create table FacCurso(
idCurso number not null,
nome varchar2(30) not null,
descricao varchar2(250),
primary key(idCurso));

create table FacDisciplina(
idDisciplina number not null,
nome varchar2(30) not null,
descricao varchar2(30) not null,
primary key(idDisciplina));

create table FacCursoDisciplina(
idDisciplina number not null,
idCurso number not null,
primary key(idDisciplina,idCurso),
foreign key(idDisciplina) references FacDisciplina(idDisciplina),
foreign key(idCurso) references FacCurso(idCurso));

create table FacResponsavel(
idResponsavel number not null,
nome varchar2(60) not null,
rg number not null,
cpf varchar2(12) not null,
idEndereco number not null,
idTelefone number not null,
email varchar(25) not null,
senha varchar(25) not null,
acesso number not null,
primary key(idResponsavel),
foreign key(idEndereco) references FacEndereco(idEndereco),
foreign key(idTelefone) references FacTelefone(idTelefone));

create table FacProfessor(
matricula number not null,
nome varchar2(60) not null,
rg number not null,
cpf varchar2(12) not null,
idEndereco number not null,
idTelefone number not null,
formacao varchar2(40) not null,
email varchar(25) not null,
senha varchar(25) not null,
acesso number not null,
primary key(matricula),
foreign key(idEndereco) references FacEndereco(idEndereco),
foreign key(idTelefone) references FacTelefone(idTelefone));

create table FacAluno(
matricula number not null,
nome varchar2(60) not null,
rg number not null,
cpf varchar2(12) not null,
dataNascimento date not null,
idEndereco number not null,
idTelefone number not null,
idCurso number not null,
idResponsavel number not null,
email varchar(25) not null,
senha varchar(25) not null,
acesso number not null,
primary key(matricula),
foreign key(idEndereco) references FacEndereco(idEndereco),
foreign key(idTelefone) references FacTelefone(idTelefone),
foreign key(idResponsavel) references FacResponsavel(idResponsavel));

create table FacTurma(
idTurma number not null,
nome varchar2(15) not null,
matriculaProfessor number not null,
idDisciplina number not null,
primary key(idTurma),
foreign key(matriculaProfessor) references FacProfessor(matricula),
foreign key(idDisciplina) references FacDisciplina(idDisciplina));

create table FacNota(
idNota number not null,
nota1 number(4,2) not null,
nota2 number(4,2) not null,
nota3 number(4,2) not null,
matriculaAluno number not null,
matriculaProfessor number not null,
primary key(idNota),
foreign key(matriculaAluno) references FacAluno(matricula),
foreign key(matriculaProfessor) references FacProfessor(matricula));

CREATE SEQUENCE facIdEndereco;
CREATE SEQUENCE facIdTelefone;
CREATE SEQUENCE facIdCurso;
CREATE SEQUENCE facIdDisciplina;
CREATE SEQUENCE facIdResponsavel;
CREATE SEQUENCE facIdTurma;
CREATE SEQUENCE facIdNota;