CREATE TABLE `evento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `area_influencia` varchar(255) DEFAULT NULL,
  `autoridades` varbinary(255) DEFAULT NULL,
  `cant_victimas` int(11) DEFAULT NULL,
  `tiempo_fin` datetime(6) DEFAULT NULL,
  `tiempo_inicio` datetime(6) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `ubi_latitud` varchar(255) DEFAULT NULL,
  `ubi_longitud` varchar(255) DEFAULT NULL,
  `ubicacion_evento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contrasenia` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
