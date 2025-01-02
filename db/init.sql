CREATE TABLE `item` (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `Item_ID` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

CREATE TABLE `user` (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `Player_UUID` binary(16) NOT NULL,
    `Server_UUID` binary(16) NOT NULL,
    `Money` int(11) DEFAULT 0,
    PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

CREATE TABLE `offer` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `price` int(11) NOT NULL,
     `count` int(11) NOT NULL,
     `user` int(11) NOT NULL,
     `item` int(11) NOT NULL,
     PRIMARY KEY (`id`),
     KEY `user` (`user`),
     KEY `item` (`item`),
     CONSTRAINT `offer_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`ID`),
     CONSTRAINT `offer_ibfk_2` FOREIGN KEY (`item`) REFERENCES `item` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;