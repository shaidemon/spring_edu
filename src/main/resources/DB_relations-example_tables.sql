create table brands(
                       brand_id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
                       brand_name varchar(64) NOT NULL UNIQUE
);

create table aims(
                     aim_id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
                     aim_name varchar(64) NOT NULL UNIQUE
);

create table models(
                       model_id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
                       brand_id int REFERENCES brands(brand_id),
                       model_name varchar(128) NOT NULL UNIQUE ,
                       engine_vol int check ( engine_vol > 0 )
);

create table models_aims(
                            model_id int REFERENCES models(model_id),
                            aim_id int REFERENCES aims(aim_id),
                            PRIMARY KEY (model_id, aim_id)
);

insert into brands (brand_name) values ('Honda');
insert into brands (brand_name) values ('BMW');
insert into brands (brand_name) values ('Yamaha');
insert into brands (brand_name) values ('Suzuki');
insert into brands (brand_name) values ('Kawasaki');

insert into aims (aim_name) values ('Road');
insert into aims (aim_name) values ('Off-road');
insert into aims (aim_name) values ('City');
insert into aims (aim_name) values ('Long tour');

insert into models (brand_id, model_name, engine_vol) VALUES (1, 'Africa Twin', 1100);
insert into models (brand_id, model_name, engine_vol) VALUES (2, 'R1250GS', 1250);
insert into models (brand_id, model_name, engine_vol) VALUES (2, 'F650GS', 650);
insert into models (brand_id, model_name, engine_vol) VALUES (1, 'Shadow 750', 750);
insert into models (brand_id, model_name, engine_vol) VALUES (3, 'YBR 125', 125);
insert into models (brand_id, model_name, engine_vol) VALUES (4, 'Djebel 250', 250);
insert into models (brand_id, model_name, engine_vol) VALUES (5, 'ZZR 400', 400);
insert into models (brand_id, model_name, engine_vol) VALUES (5, 'Vulcan 1800', 1800);

insert into models_aims values (1, 1);
insert into models_aims values (1, 2);
insert into models_aims values (1, 4);
insert into models_aims values (2, 1);
insert into models_aims values (2, 2);
insert into models_aims values (2, 4);
insert into models_aims values (3, 1);
insert into models_aims values (3, 2);
insert into models_aims values (4, 1);
insert into models_aims values (4, 3);
insert into models_aims values (5, 3);
insert into models_aims values (6, 2);
insert into models_aims values (6, 3);
insert into models_aims values (7, 1);
insert into models_aims values (7, 3);
insert into models_aims values (7, 4);
insert into models_aims values (8, 1);
insert into models_aims values (8, 4);

select * from brands;
select * from models;
select * from aims;

select brand_name, model_name, engine_vol from brands join models m on brands.brand_id = m.brand_id;

select model_name, aim_name from models left join models_aims ma on models.model_id = ma.model_id
                                        join aims a on a.aim_id = ma.aim_id;

select brand_name, model_name, aim_name
from brands join models on brands.brand_id = models.brand_id
            left join models_aims ma on models.model_id = ma.model_id
            join aims a on a.aim_id = ma.aim_id;

select aim_name, brand_name, model_name
from aims
         join models_aims ma on aims.aim_id = ma.aim_id
         join models m on ma.model_id = m.model_id
         join brands on m.brand_id = brands.brand_id;