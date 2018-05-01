SET FOREIGN_KEY_CHECKS=0;

/* must be dropped in this order to avoid constraint violations */
DROP TABLE IF EXISTS produktbatchkomponent;
DROP TABLE IF EXISTS produktbatch;
DROP TABLE IF EXISTS operatoer;
DROP TABLE IF EXISTS receptkomponent;
DROP TABLE IF EXISTS recept;
DROP TABLE IF EXISTS raavarebatch;
DROP TABLE IF EXISTS raavare;

SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE operatoer(opr_id INT PRIMARY KEY, opr_fornavn TEXT, opr_efternavn TEXT,  ini TEXT, cpr TEXT, password TEXT) ENGINE=innoDB;
 
CREATE TABLE raavare(raavare_id INT PRIMARY KEY, raavare_navn TEXT, leverandoer TEXT) ENGINE=innoDB;

CREATE TABLE raavarebatch(rb_id INT PRIMARY KEY, raavare_id INT, maengde REAL, 
   FOREIGN KEY (raavare_id) REFERENCES raavare(raavare_id)) ENGINE=innoDB;

CREATE TABLE recept(recept_id INT PRIMARY KEY, recept_navn TEXT) ENGINE=innoDB;

CREATE TABLE receptkomponent(recept_id INT, raavare_id INT, nom_netto REAL, tolerance REAL, 
   PRIMARY KEY (recept_id, raavare_id), 
   FOREIGN KEY (recept_id) REFERENCES recept(recept_id), 
   FOREIGN KEY (raavare_id) REFERENCES raavare(raavare_id)) ENGINE=innoDB;

CREATE TABLE produktbatch(pb_id INT PRIMARY KEY, status INT, recept_id INT, 
   FOREIGN KEY (recept_id) REFERENCES recept(recept_id)) ENGINE=innoDB;

CREATE TABLE produktbatchkomponent(pb_id INT, rb_id INT, tara REAL, netto REAL, opr_id INT, 
   PRIMARY KEY (pb_id, rb_id), 
   FOREIGN KEY (pb_id) REFERENCES produktbatch(pb_id), 
   FOREIGN KEY (rb_id) REFERENCES raavarebatch(rb_id), 
   FOREIGN KEY (opr_id) REFERENCES operatoer(opr_id)) ENGINE=innoDB;


INSERT INTO operatoer(opr_id, opr_fornavn, opr_efternavn, ini, cpr, password) VALUES
(1, 'Angelo', 'A', 'AA', '070770-7007', 'lKje4fa'),
(2, 'Antonella', 'B', 'AB', '080880-8008', 'atoJ21v'),
(3, 'Luigi', 'C', 'LC', '090990-9009', 'jEfm5aQ');

INSERT INTO raavare(raavare_id, raavare_navn, leverandoer) VALUES
(1, 'dej', 'Wawelka'),
(2, 'tomat', 'Knoor'),
(3, 'tomat', 'Veaubais'),
(4, 'tomat', 'Franz'),
(5, 'ost', 'Ost og Skinke A/S'),
(6, 'skinke', 'Ost og Skinke A/S'),
(7, 'champignon', 'Igloo Frostvarer');

INSERT INTO raavarebatch(rb_id, raavare_id, maengde) VALUES
(1, 1, 1000),
(2, 2, 300),
(3, 3, 300),
(4, 5, 100),
(5, 5, 100), 
(6, 6, 100),
(7, 7, 100);

INSERT INTO recept(recept_id, recept_navn) VALUES
(1, 'margherita'),
(2, 'prosciutto'),
(3, 'capricciosa');


INSERT INTO receptkomponent(recept_id, raavare_id, nom_netto, tolerance) VALUES
(1, 1, 10.0, 0.1),
(1, 2, 2.0, 0.1),
(1, 5, 2.0, 0.1),

(2, 1, 10.0, 0.1),
(2, 3, 2.0, 0.1),  
(2, 5, 1.5, 0.1),
(2, 6, 1.5, 0.1),

(3, 1, 10.0, 0.1),
(3, 4, 1.5, 0.1),
(3, 5, 1.5, 0.1),
(3, 6, 1.0, 0.1),
(3, 7, 1.0, 0.1);

INSERT INTO produktbatch(pb_id, recept_id, status) VALUES
(1, 1, 2), 
(2, 1, 2),
(3, 2, 2),
(4, 3, 1),
(5, 3, 0);


INSERT INTO produktbatchkomponent(pb_id, rb_id, tara, netto, opr_id) VALUES
(1, 1, 0.5, 10.05, 1),
(1, 2, 0.5, 2.03, 1),
(1, 4, 0.5, 1.98, 1),

(2, 1, 0.5, 10.01, 2),
(2, 2, 0.5, 1.99, 2),
(2, 5, 0.5, 1.47, 2),

(3, 1, 0.5, 10.07, 1),
(3, 3, 0.5, 2.06, 2),
(3, 4, 0.5, 1.55, 1),
(3, 6, 0.5, 1.53, 2),

(4, 1, 0.5, 10.02, 3),
(4, 5, 0.5, 1.57, 3),
(4, 6, 0.5, 1.03, 3),
(4, 7, 0.5, 0.99, 3);

-- *************************************************************************************************
-- **	HER STARTER VORES ÆNDRINGER!!!!!!!!!!!!! - NU MED KOBLING AF LEVERANDOER OG RAAVAREBATCH
-- *************************************************************************************************
DROP TABLE IF EXISTS

	raavareleverandoer,
	leverandoer,
    operatoer_roller,
    roller;

CREATE TABLE leverandoer(
	lev_id			INT(11) PRIMARY KEY,
	navn			VARCHAR(30)
);

CREATE TABLE raavareleverandoer(
	raavare_id		INT(11),
	lev_id			INT(11),
	foreign key (raavare_id) references raavare (raavare_id),
    foreign key (lev_id) references leverandoer(lev_id)
);

CREATE TABLE roller(
	rolle_navn VARCHAR(30) PRIMARY KEY
) ENGINE=innoDB;

CREATE TABLE operatoer_roller(
	opr_id INT(11),
    rolle_navn VARCHAR(30),
    FOREIGN KEY (opr_id) REFERENCES operatoer(opr_id) ON DELETE CASCADE,
	FOREIGN KEY (rolle_navn) REFERENCES roller(rolle_navn) ON DELETE CASCADE
) ENGINE=innoDB;

ALTER TABLE produktbatchkomponent
DROP COLUMN tara;


-- INSERT
INSERT INTO roller VALUES ('Administrator');
INSERT INTO roller VALUES ('Foreman');
INSERT INTO roller VALUES ('Operatoer');
INSERT INTO roller VALUES ('Master_Chef');

INSERT INTO operatoer_roller VALUES (1, 'Administrator');
INSERT INTO operatoer_roller VALUES (2, 'Operatoer');
INSERT INTO operatoer_roller VALUES (3, 'Foreman');
INSERT INTO operatoer_roller VALUES (3, 'Master_Chef');


INSERT INTO leverandoer VALUES(1, 'Wawelka');
INSERT INTO leverandoer VALUES(2, 'Knoor');
INSERT INTO leverandoer VALUES(3, 'Veaubais');
INSERT INTO leverandoer VALUES(4, 'Franz');
INSERT INTO leverandoer VALUES(5, 'Ost og Skinke A/S');
INSERT INTO leverandoer VALUES(6, 'Igloo Frostvarer');
INSERT INTO raavareleverandoer VALUES(1, 1);
INSERT INTO raavareleverandoer VALUES(2, 2);
INSERT INTO raavareleverandoer VALUES(2, 3);
INSERT INTO raavareleverandoer VALUES(2, 4);
INSERT INTO raavareleverandoer VALUES(5, 5);
INSERT INTO raavareleverandoer VALUES(6, 5);
INSERT INTO raavareleverandoer VALUES(7, 6);

ALTER TABLE raavare DROP COLUMN leverandoer;
ALTER TABLE raavarebatch ADD COLUMN lev_id INT(11),
ADD CONSTRAINT raavarebatch_ibfk_2 FOREIGN KEY (lev_id) REFERENCES leverandoer(lev_id);

UPDATE raavarebatch SET lev_id = 2 WHERE raavare_id = 2;
UPDATE raavarebatch SET lev_id = 3 WHERE raavare_id = 3;
UPDATE raavarebatch SET lev_id = 4 WHERE raavare_id = 4;
UPDATE raavarebatch SET lev_id = 5 WHERE raavare_id = 5;
UPDATE raavarebatch SET lev_id = 5 WHERE raavare_id = 6;
UPDATE raavarebatch SET lev_id = 6 WHERE raavare_id = 7;
UPDATE raavarebatch SET lev_id = 1 WHERE raavare_id = 1;
UPDATE raavarebatch SET raavare_id = 2 WHERE raavare_id IN (3,4);
UPDATE receptkomponent SET raavare_id = 2 WHERE raavare_id IN (3,4);

DELETE FROM raavare WHERE raavare_id IN (3,4);

-- Dropping veiws
	DROP VIEW IF exists viewProduktBatch;
	DROP VIEW IF exists v_product_provider_list;
    DROP VIEW IF exists v_raavare_batches;
    DROP VIEW IF exists	v_recepter;
    DROP VIEW IF exists v_recepter_ver2;
    
    
-- Veiws

Delimiter //
create view viewProduktBatch as 	
Select *
From
produktbatch;
// Delimiter;

Delimiter //
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `nybaad_dk`@`%` 
    SQL SECURITY DEFINER
VIEW `v_product_provider_list` AS
    SELECT DISTINCT
        `raavare`.`raavare_navn` AS `raavare_navn`,
        `leverandoer`.`navn` AS `navn`,
        `raavarebatch`.`maengde` AS `maengde`
    FROM
        (((`raavare`
        JOIN `raavarebatch` ON ((`raavare`.`raavare_id` = `raavarebatch`.`raavare_id`)))
        JOIN `raavareleverandoer` ON ((`raavare`.`raavare_id` = `raavareleverandoer`.`raavare_id`)))
        JOIN `leverandoer` ON ((`raavareleverandoer`.`lev_id` = `leverandoer`.`lev_id`)))
    WHERE
        (`raavarebatch`.`maengde` >= 100)
    ORDER BY `raavare`.`raavare_navn`
// Delimiter ;
 
Delimiter //
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `nybaad_dk`@`%` 
    SQL SECURITY DEFINER
VIEW `v_raavare_batches` AS
    SELECT DISTINCT
        `raavare`.`raavare_navn` AS `raavare_navn`,
        `raavarebatch`.`maengde` AS `maengde`,
        `leverandoer`.`navn` AS `leverandoer`
    FROM
        ((`raavare`
        JOIN `raavarebatch` ON ((`raavare`.`raavare_id` = `raavarebatch`.`raavare_id`)))
        JOIN `leverandoer` ON ((`raavarebatch`.`lev_id` = `leverandoer`.`lev_id`)))
    ORDER BY `raavare`.`raavare_navn`
// Delimiter ;

Delimiter //
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `nybaad_dk`@`%` 
    SQL SECURITY DEFINER
VIEW `v_recepter` AS
    SELECT DISTINCT
        `recept`.`recept_navn` AS `recept`,
        `raavare`.`raavare_navn` AS `raavare`,
        `receptkomponent`.`nom_netto` AS `maengde`
    FROM
        ((`raavare`
        JOIN `receptkomponent` ON ((`raavare`.`raavare_id` = `receptkomponent`.`raavare_id`)))
        JOIN `recept` ON ((`receptkomponent`.`recept_id` = `recept`.`recept_id`)))
    ORDER BY `recept`.`recept_id`
// Delimiter ;

Delimiter //
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `nybaad_dk`@`%` 
    SQL SECURITY DEFINER
VIEW `v_recepter_ver2` AS
    SELECT DISTINCT
        `recept`.`recept_navn` AS `recept`,
        `recept`.`recept_id` AS `recept_id`,
        `raavare`.`raavare_navn` AS `raavare`,
        `raavare`.`raavare_id` AS `raavare_id`,
        `receptkomponent`.`nom_netto` AS `maengde`,
        `receptkomponent`.`tolerance` AS `tolerance`
    FROM
        ((`raavare`
        JOIN `receptkomponent` ON ((`raavare`.`raavare_id` = `receptkomponent`.`raavare_id`)))
        JOIN `recept` ON ((`receptkomponent`.`recept_id` = `recept`.`recept_id`)))
    ORDER BY `recept`.`recept_id`
// Delimiter ;

Delimiter //
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `nybaad_dk`@`%` 
    SQL SECURITY DEFINER
VIEW `v_view_users` AS
    SELECT 
        `operatoer`.`opr_id` AS `opr_id`,
        `operatoer`.`opr_navn` AS `opr_navn`,
        `operatoer`.`ini` AS `opr_ini`,
        `operatoer`.`cpr` AS `opr_cpr`
    FROM
        `operatoer`
    ORDER BY `operatoer`.`opr_id`
// Delimiter ;

-- Dropping Stored procedure

DROP Procedure if  exists createProduktBatchKomponent;
DROP Procedure if  exists updateRaavareBatch;
DROP Procedure if  exists fm_create_productbatch;
DROP Procedure if  exists fm_update_productbatch;
DROP Procedure if  exists fm_create_raavarebatch;
DROP Procedure if  exists sp_create_medarbejder;
DROP Procedure if  exists sp_delete_medarbejder;
DROP Procedure if  exists sp_show_recept;
DROP Procedure if  exists sp_show_recept_ver2;
-- Stored procedure 

Delimiter // 
Create Procedure createProduktBatchKomponent(
in_pb int,
in_rb int ,
in_opr int,
in_netto real)
Begin
insert into produktbatchkomponent(pb_id, rb_id, opr_id, netto)
Values(in_pb, in_rb, in_opr, in_netto);
End 
// Delimiter ; 

Delimiter // 
Create Procedure updateRaavareBatch(
in_rb_idToUpdate int,
in_rb_id int, 
in_raavare_id int, 
in_maengde real)
Begin
Update raavarebatch 
Set rb_id = in_rb_id,raavare_id = in_raavare_id, maengde = in_maengde
where(in_rb_idToUpdate = rb_id); 
END
// Delimiter ;

Delimiter // 

CREATE DEFINER=`nybaad_dk`@`%` PROCEDURE `fm_create_productbatch`(in pb_id_input int(11),
 in statuz_input int(11), in recept_id_input int(11))
begin
 /*declare  new_id int(11);
set new_id = max(rb_id) ;
set new_id = count pb_id;*/
 insert into produktbatch(pb_id, status, recept_id)
 values(pb_id_input, statuz_input, recept_id_input);
 end
 // Delimiter ;
 
 Delimiter //
 CREATE DEFINER=`nybaad_dk`@`%` PROCEDURE `fm_update_productbatch`(in pb_id_input int(11),
 in statuz_input int(11), in recept_id_input int(11))
begin
 update produktbatch
 set status=statuz_input, recept_id=recept_id_input
 where pb_id = pb_id_input;
end
  // Delimiter ;

Delimiter //
 CREATE DEFINER=`nybaad_dk`@`%` PROCEDURE `fm_create_raavarebatch`(in rb_id_input int(11), 
 in raavare_id_input int (11), maengde_input double , lev_id_input INT(11))
begin
 insert into raavarebatch(rb_id, raavare_id, maengde, lev_id)
 values(rb_id_input, raavare_id_input, maengde_input, lev_id_input);
 end
  // Delimiter ;

Delimiter //
 CREATE DEFINER=`nybaad_dk`@`%` PROCEDURE `fm_update_raavarebatch`(in rb_id_input int(11), 
 in raavare_id_input int (11), maengde_input double )
begin
 update raavarebatch
 set raavare_id = raavare_id_input, maengde = maengde_input
 where rb_id = rb_id_input;
 end
  // Delimiter ;

Delimiter //
 CREATE DEFINER=`nybaad_dk`@`%` PROCEDURE `sp_create_medarbejder`(IN id_input INT(2), navn_input VARCHAR(20), ini_input VARCHAR(4), cpr_input VARCHAR(11), pass_input VARCHAR(20))
BEGIN
INSERT INTO operatoer (opr_id,opr_navn,ini,cpr,password) VALUES (id_input,navn_input,ini_input,cpr_input,pass_input);
END
  // Delimiter ;
  
Delimiter //
CREATE DEFINER=`nybaad_dk`@`%` PROCEDURE `sp_delete_medarbejder`(IN id_input VARCHAR(5))
BEGIN
DELETE FROM operatoer
WHERE (opr_id=id_input);
END 
  // Delimiter ;

Delimiter //
CREATE DEFINER=`nybaad_dk`@`%` PROCEDURE `sp_show_recept`(IN recept_navn_input VARCHAR(20))
BEGIN
SELECT raavare, maengde
FROM v_recepter
WHERE recept=recept_navn_input;
END
// Delimiter ;

Delimiter //
CREATE DEFINER=`nybaad_dk`@`%` PROCEDURE `sp_show_recept_ver2`(IN recept_navn_input VARCHAR(20))
BEGIN
SELECT recept_id, raavare, raavare_id, maengde, tolerance
FROM v_recepter_ver2
WHERE recept=recept_navn_input;
END
// Delimiter ;