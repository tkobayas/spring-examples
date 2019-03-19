    CREATE TABLE  `testdb2`.`mytable` (
      `id` int(10) unsigned NOT NULL auto_increment,
      `data` varchar(45) NOT NULL,
      PRIMARY KEY  USING BTREE (`id`)
    ) AUTO_INCREMENT=1;


    CREATE TABLE  `technicalkeeda`.`trn_person` (
      `person_id` int(10) unsigned NOT NULL auto_increment,
      `first_name` varchar(45)  NOT NULL,
      `last_name` varchar(45)  NOT NULL,
      `age` int(10) unsigned NOT NULL,
      PRIMARY KEY  USING BTREE (`person_id`)
    ) AUTO_INCREMENT=1;