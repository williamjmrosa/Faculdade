create table FacEndereco(
idEndereco bigint not null,
rua varchar(30) not null,
cidade varchar(30) not null,
bairro varchar(30) not null,
estado varchar(30) not null,
numero int not null,
cep varchar(10) not null,
primary key(idEndereco));

create table FacTelefone(
idTelefone bigint not null,
tipo varchar(15) not null,
numero varchar(20) not null,
primary key(idTelefone));

create table FacCurso(
idCurso bigint auto_increment,
nome varchar(30) not null,
descricao varchar(250),
primary key(idCurso));

create table FacDisciplina(
idDisciplina bigint auto_increment,
nome varchar(30) not null,
descricao varchar(30) not null,
primary key(idDisciplina));

create table FacCursoDisciplina(
idDisciplina bigint not null,
idCurso bigint not null,
primary key(idDisciplina,idCurso),
foreign key(idDisciplina) references FacDisciplina(idDisciplina),
foreign key(idCurso) references FacCurso(idCurso));

create table FacResponsavel(
idResponsavel bigint auto_increment,
nome varchar(60) not null,
rg bigint not null unique,
cpf varchar(12) not null unique,
idEndereco bigint not null,
idTelefone bigint not null,
email varchar(25) not null,
senha varchar(25) not null,
acesso bigint not null,
primary key(idResponsavel),
foreign key(idEndereco) references FacEndereco(idEndereco),
foreign key(idTelefone) references FacTelefone(idTelefone));

create table FacProfessor(
matricula bigint not null,
nome varchar(60) not null,
rg bigint not null unique,
cpf varchar(12) not null unique,
idEndereco bigint not null,
idTelefone bigint not null,
formacao varchar(40) not null,
email varchar(25) not null,
senha varchar(25) not null,
acesso bigint not null,
primary key(matricula),
foreign key(idEndereco) references FacEndereco(idEndereco),
foreign key(idTelefone) references FacTelefone(idTelefone));

create table FacAluno(
matricula bigint not null,
nome varchar(60) not null,
rg bigint not null unique,
cpf varchar(12) not null unique,
dataNascimento date not null,
idEndereco bigint not null,
idTelefone bigint not null,
idCurso bigint not null,
idResponsavel bigint not null,
email varchar(25) not null,
senha varchar(25) not null,
acesso bigint not null,
primary key(matricula),
foreign key(idEndereco) references FacEndereco(idEndereco),
foreign key(idTelefone) references FacTelefone(idTelefone),
foreign key(idResponsavel) references FacResponsavel(idResponsavel));

create table FacTurma(
idTurma bigint auto_increment,
nome varchar(15) not null,
matriculaProfessor bigint not null,
idDisciplina bigint not null,
primary key(idTurma),
foreign key(matriculaProfessor) references FacProfessor(matricula),
foreign key(idDisciplina) references FacDisciplina(idDisciplina));

create table FacTurmaAluno(
matricula bigint not null,
idTurma bigint not null,
primary key(matricula,idTurma),
foreign key(matricula) references FacAluno(matricula),
foreign key(idTurma) references FacTurma(idTurma));

create table FacNota(
idNota bigint auto_increment,
nota1 double(4,2) not null,
nota2 double(4,2) not null,
nota3 double(4,2) not null,
matriculaAluno bigint not null,
matriculaProfessor bigint not null,
primary key(idNota),
foreign key(matriculaAluno) references FacAluno(matricula),
foreign key(matriculaProfessor) references FacProfessor(matricula));