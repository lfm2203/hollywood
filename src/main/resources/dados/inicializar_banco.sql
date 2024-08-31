create table if not exists FILME (
    ID int AUTO_INCREMENT PRIMARY KEY,
    VL_ANO INT,
    DS_TITULO VARCHAR(255),
    DS_ESTUDIO VARCHAR(255),
    DS_PRODUTOR VARCHAR(255),
    TP_VENCEDOR CHAR(1)
);

insert into FILME(  VL_ANO,
                    DS_TITULO,
                    DS_ESTUDIO,
                    DS_PRODUTOR,
                    TP_VENCEDOR)
            SELECT  cast(MOVIEYEAR as int),
                    TITLE,
                    STUDIOS,
                    PRODUCERS,
                    case when lower(trim(WINNER)) = 'yes' then 'S' else 'N' end
            from    CSVREAD('classpath:dados/movielist.csv', null, 'fieldSeparator=;');