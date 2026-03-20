-- Para evitar que o SQL*Plus interprete o caractere '&' como um prompt para substituição de variável
   SET DEFINE OFF;

drop sequence fiap_sq_aula;
drop table fiap_t_aula;

create sequence fiap_sq_aula start with 1 increment by 1 nocache nocycle;

create table fiap_t_aula (
   id_aula       number primary key,
   nm_disciplina varchar2(200) not null,
   nm_professor  varchar2(150),
   nr_sala       varchar2(50),
   ds_dia_semana varchar2(20),
   hr_inicio     varchar2(10),
   hr_fim        varchar2(10),
   tp_aula       varchar2(30),
   st_aula       varchar2(30)
);


-- --- SEGUNDA-FEIRA ---
insert into fiap_t_aula (
   id_aula,
   nm_disciplina,
   nm_professor,
   nr_sala,
   ds_dia_semana,
   hr_inicio,
   hr_fim,
   tp_aula,
   st_aula
) values ( fiap_sq_aula.nextval,
           'ARQUITETURA ORIENTADA A SERVIÇOS (SOA) E WEB SERVICES',
           'SALATIEL LUZ MARINHO',
           '105',
           'SEGUNDA',
           '19:20',
           '21:00',
           'PRESENCIAL',
           'CONFIRMADA' );

insert into fiap_t_aula (
   id_aula,
   nm_disciplina,
   nm_professor,
   nr_sala,
   ds_dia_semana,
   hr_inicio,
   hr_fim,
   tp_aula,
   st_aula
) values ( fiap_sq_aula.nextval,
           'MOBILE DEVELOPMENT & IOT',
           'ADEILTON DA SILVA MENESES',
           '105',
           'SEGUNDA',
           '21:15',
           '22:55',
           'PRESENCIAL',
           'CONFIRMADA' );

-- --- TERÇA-FEIRA ---
insert into fiap_t_aula (
   id_aula,
   nm_disciplina,
   nm_professor,
   nr_sala,
   ds_dia_semana,
   hr_inicio,
   hr_fim,
   tp_aula,
   st_aula
) values ( fiap_sq_aula.nextval,
           'INTELIGÊNCIA ARTIFICIAL & MACHINE LEARNING',
           'DANILO RODRIGUES DE ASSIS ELIAS',
           '105',
           'TERCA',
           '19:20',
           '21:00',
           'PRESENCIAL',
           'CONFIRMADA' );

insert into fiap_t_aula (
   id_aula,
   nm_disciplina,
   nm_professor,
   nr_sala,
   ds_dia_semana,
   hr_inicio,
   hr_fim,
   tp_aula,
   st_aula
) values ( fiap_sq_aula.nextval,
           'C# SOFTWARE DEVELOPMENT',
           'RAFAEL SANTOS NOVO PEREIRA',
           '105',
           'TERCA',
           '21:15',
           '22:55',
           'PRESENCIAL',
           'CONFIRMADA' );

-- --- QUARTA-FEIRA ---
insert into fiap_t_aula (
   id_aula,
   nm_disciplina,
   nm_professor,
   nr_sala,
   ds_dia_semana,
   hr_inicio,
   hr_fim,
   tp_aula,
   st_aula
) values ( fiap_sq_aula.nextval,
           'TESTING, COMPLIANCE & QUALITY ASSURANCE',
           'ANDRE LUIZ DE ALCANTARA DA SILVA LEITE',
           '105',
           'QUARTA',
           '19:20',
           '21:00',
           'REMOTO',
           'CONFIRMADA' );

insert into fiap_t_aula (
   id_aula,
   nm_disciplina,
   nm_professor,
   nr_sala,
   ds_dia_semana,
   hr_inicio,
   hr_fim,
   tp_aula,
   st_aula
) values ( fiap_sq_aula.nextval,
           'OPERATING SYSTEMS',
           'BRUNO LUIZ DE ALMEIDA',
           '105',
           'QUARTA',
           '21:15',
           '22:55',
           'REMOTO',
           'CONFIRMADA' );

-- --- SEXTA-FEIRA ---
insert into fiap_t_aula (
   id_aula,
   nm_disciplina,
   nm_professor,
   nr_sala,
   ds_dia_semana,
   hr_inicio,
   hr_fim,
   tp_aula,
   st_aula
) values ( fiap_sq_aula.nextval,
           'PHYSICAL COMPUTING: IOT & IOB',
           'YAN GABRIEL COELHO',
           '105',
           'SEXTA',
           '19:20',
           '21:00',
           'PRESENCIAL',
           'CONFIRMADA' );

insert into fiap_t_aula (
   id_aula,
   nm_disciplina,
   nm_professor,
   nr_sala,
   ds_dia_semana,
   hr_inicio,
   hr_fim,
   tp_aula,
   st_aula
) values ( fiap_sq_aula.nextval,
           'CYBERSECURITY',
           'VITOR MIGUEL LASSE SILVA',
           '105',
           'SEXTA',
           '21:15',
           '22:55',
           'PRESENCIAL',
           'CONFIRMADA' );

commit;