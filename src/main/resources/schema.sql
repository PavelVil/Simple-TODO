CREATE SCHEMA IF NOT EXISTS `todo_dao`;
DROP TABLE IF EXISTS `todo`;

CREATE TABLE `todo` (
  `id`        BIGINT NOT NULL AUTO_INCREMENT,
  `label`     VARCHAR(120)    DEFAULT NULL,
  `active`    BOOL            DEFAULT TRUE,
  `important` BOOL            DEFAULT FALSE,
  PRIMARY KEY (`id`)
)