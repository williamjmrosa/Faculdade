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
idCurso bigint not null,
nome varchar(30) not null,
descricao varchar(250),
primary key(idCurso));

create table FacDisciplina(
idDisciplina bigint not null,
nome varchar(30) not null,
descricao varchar(30) not null,
primary key(idDisciplina));

create table FacCursoDisciplina(
idDisciplina bigint not null,
idCurso bigint not null,
primary key(idDisciplina,idCurso),
foreign key(idDisciplina) references Disciplina(idDisciplina),
foreign key(idCurso) references Curso(idCurso));

create table FacResponsavel(
idResponsavel bigint not null,
nome varchar(60) not null,
rg bigint not null,
cpf varchar(12) not null,
idEndereco bigint not null,
idTelefone bigint not null,
email varchar(25) not null,
senha varchar(25) not null,
acesso bigint not null,
primary key(idResponsavel),
foreign key(idEndereco) references Endereco(idEndereco),
foreign key(idTelefone) references Telefone(idTelefone));

create table FacProfessor(
matricula bigint not null,
nome varchar(60) not null,
rg bigint not null,
cpf varchar(12) not null,
idEndereco bigint not null,
idTelefone bigint not null,
formacao varchar(40) not null,
email varchar(25) not null,
senha varchar(25) not null,
acesso bigint not null,
primary key(matricula),
foreign key(idEndereco) references Endereco(idEndereco),
foreign key(idTelefone) references Telefone(idTelefone));

create table FacAluno(
matricula bigint not null,
nome varchar(60) not null,
rg bigint not null,
cpf varchar(12) not null,
dataNascimento date not null,
idEndereco bigint not null,
idTelefone bigint not null,
idCurso bigint not null,
idResponsavel bigint not null,
email varchar(25) not null,
senha varchar(25) not null,
acesso bigint not null,
primary key(matricula),
foreign key(idEndereco) references Endereco(idEndereco),
foreign key(idTelefone) references Telefone(idTelefone),
foreign key(idResponsavel) references Responsavel(idResponsavel));

create table FacTurma(
idTurma bigint not null,
nome varchar(15) not null,
matriculaProfessor bigint not null,
idDisciplina bigint not null,
primary key(idTurma),
foreign key(matriculaProfessor) references Professor(matricula),
foreign key(idDisciplina) references Disciplina(idDisciplina));

create table FacNota(
idNota bigint not null,
nota1 double(4,2) not null,
nota2 double(4,2) not null,
nota3 double(4,2) not null,
matriculaAluno bigint not null,
matriculaProfessor bigint not null,
primary key(idNota),
foreign key(matriculaAluno) references Aluno(matricula),
foreign key(matriculaProfessor) references Professor(matricula));