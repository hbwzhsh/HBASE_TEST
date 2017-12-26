mysql -h192.168.1.11 -uroot -phfwl@2109 -DHFStats;

DROP TABLE IF EXISTS `HFStats.bar`;
CREATE TABLE `HFStats.bar` (
  `name` varchar(500) DEFAULT NULL,
  `num` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO HFStats.bar VALUES ('aa', '20');
INSERT INTO HFStats.bar VALUES ('bb', '30');
INSERT INTO HFStats.bar VALUES ('cc', '14');
INSERT INTO HFStats.bar VALUES ('dd', '1');
INSERT INTO HFStats.bar VALUES ('zuidaima.com', '100');


DROP TABLE IF EXISTS HFStats.temp;
CREATE TABLE HFStats.temp (
  id int(10) not NULL primary key,
  hight int(10) DEFAULT 20
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO HFStats.temp VALUES (11,15);
INSERT INTO HFStats.temp VALUES (12,22);
INSERT INTO HFStats.temp VALUES (13,20);
INSERT INTO HFStats.temp VALUES (14,28);
INSERT INTO HFStats.temp VALUES (15,15);
INSERT INTO HFStats.temp VALUES (16,30);
INSERT INTO HFStats.temp VALUES (17,12);
INSERT INTO HFStats.temp VALUES (18,18);
INSERT INTO HFStats.temp VALUES (19,25);
INSERT INTO HFStats.temp VALUES (20,19);