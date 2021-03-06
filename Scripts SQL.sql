-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mediscreen
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mediscreen
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mediscreen` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mediscreen` ;

-- -----------------------------------------------------
-- Table `mediscreen`.`patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mediscreen`.`patient` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT,
  `lastName` VARCHAR(45) NULL DEFAULT NULL,
  `firstName` VARCHAR(45) NULL DEFAULT NULL,
  `birthDate` DATE NULL DEFAULT NULL,
  `sex` VARCHAR(1) NULL DEFAULT NULL,
  `address` VARCHAR(200) NULL DEFAULT NULL,
  `phone` VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
