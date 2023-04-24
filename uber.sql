-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema uber
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema uber
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `uber` DEFAULT CHARACTER SET utf8mb3 ;
USE `uber` ;

-- -----------------------------------------------------
-- Table `uber`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `uber`.`carmaker`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`carmaker` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `maker` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idCarMaker_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `companyName_UNIQUE` (`maker` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `uber`.`carmodel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`carmodel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `car_maker_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idCarModel_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_carmodel_carmaker1_idx` (`car_maker_id` ASC) VISIBLE,
  CONSTRAINT `fk_carmodel_carmaker1`
    FOREIGN KEY (`car_maker_id`)
    REFERENCES `uber`.`carmaker` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `uber`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `city_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `uber`.`color`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`color` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `color` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `color_UNIQUE` (`color` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `uber`.`driver`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`driver` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(10) NOT NULL,
  `last_name` VARCHAR(10) NOT NULL,
  `phone_number` VARCHAR(11) NOT NULL,
  `national_id` DECIMAL(14,0) NOT NULL,
  `driver_licence` DECIMAL(7,0) NOT NULL,
  `gender` DECIMAL(1,0) NOT NULL,
  `birth_date` DATE NOT NULL,
  `avalible` TINYINT NOT NULL DEFAULT '1',
  `city_id` INT NOT NULL,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `phoneNumber_UNIQUE` (`phone_number` ASC) VISIBLE,
  UNIQUE INDEX `nationalId_UNIQUE` (`national_id` ASC) VISIBLE,
  UNIQUE INDEX `driverLicenceId_UNIQUE` (`driver_licence` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `account_id_UNIQUE` (`account_id` ASC) VISIBLE,
  INDEX `fk_driver_city1_idx` (`city_id` ASC) VISIBLE,
  INDEX `fk_driver_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_driver_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `uber`.`account` (`id`),
  CONSTRAINT `fk_driver_city1`
    FOREIGN KEY (`city_id`)
    REFERENCES `uber`.`city` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `uber`.`driverwallet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`driverwallet` (
  `balance` FLOAT NOT NULL,
  `driver_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `driverWalletId_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `driver_id_UNIQUE` (`driver_id` ASC) VISIBLE,
  INDEX `fk_driverWallet_driver1` (`driver_id` ASC) VISIBLE,
  CONSTRAINT `fk_driverWallet_driver1`
    FOREIGN KEY (`driver_id`)
    REFERENCES `uber`.`driver` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `uber`.`paymentmethod`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`paymentmethod` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `paymentmethod_id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `uber`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(45) NOT NULL,
  `paymentmethod_id` INT NOT NULL,
  PRIMARY KEY (`id`, `paymentmethod_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_payment_paymentMethod1_idx` (`paymentmethod_id` ASC) VISIBLE,
  CONSTRAINT `fk_payment_paymentMethod1`
    FOREIGN KEY (`paymentmethod_id`)
    REFERENCES `uber`.`paymentmethod` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `uber`.`release_year`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`release_year` (
  `year` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`year`),
  UNIQUE INDEX `year_UNIQUE` (`year` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2011
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `uber`.`rider`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`rider` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(10) NOT NULL,
  `last_name` VARCHAR(10) NOT NULL,
  `phone_number` VARCHAR(11) NOT NULL,
  `account_id` INT NOT NULL,
  `city_id` INT NOT NULL,
  `birthdate` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `phoneNumber_UNIQUE` (`phone_number` ASC) VISIBLE,
  UNIQUE INDEX `account_id_UNIQUE` (`account_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_rider_account1_idx` (`account_id` ASC) VISIBLE,
  INDEX `fk_rider_city1_idx` (`city_id` ASC) VISIBLE,
  CONSTRAINT `fk_rider_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `uber`.`account` (`id`),
  CONSTRAINT `fk_rider_city1`
    FOREIGN KEY (`city_id`)
    REFERENCES `uber`.`city` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `uber`.`riderwallet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`riderwallet` (
  `balance` FLOAT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `rider_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `rider_id_UNIQUE` (`rider_id` ASC) VISIBLE,
  INDEX `fk_riderwallet_rider1_idx` (`rider_id` ASC) VISIBLE,
  CONSTRAINT `fk_riderwallet_rider1`
    FOREIGN KEY (`rider_id`)
    REFERENCES `uber`.`rider` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `uber`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `status_UNIQUE` (`status` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `uber`.`trip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`trip` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `act_pick_loc` DOUBLE NULL DEFAULT NULL,
  `pick_loc` DOUBLE NOT NULL,
  `act_drop_loc` DOUBLE NULL DEFAULT NULL,
  `drop_loc` DOUBLE NOT NULL,
  `pick_time` TIME NULL DEFAULT NULL,
  `drop_time` TIME NULL DEFAULT NULL,
  `create_date` DATETIME NULL DEFAULT NULL,
  `fare` FLOAT NULL DEFAULT NULL,
  `duration` FLOAT NULL DEFAULT NULL,
  `distance` FLOAT NULL DEFAULT NULL,
  `status_id` INT NOT NULL,
  `driver_id` INT NOT NULL,
  `rider_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_trip_status1_idx` (`status_id` ASC) VISIBLE,
  INDEX `fk_trip_driver1_idx` (`driver_id` ASC) VISIBLE,
  INDEX `fk_trip_rider1_idx` (`rider_id` ASC) VISIBLE,
  CONSTRAINT `fk_trip_driver1`
    FOREIGN KEY (`driver_id`)
    REFERENCES `uber`.`driver` (`id`),
  CONSTRAINT `fk_trip_rider1`
    FOREIGN KEY (`rider_id`)
    REFERENCES `uber`.`rider` (`id`),
  CONSTRAINT `fk_trip_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `uber`.`status` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `uber`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`transaction` (
  `trip_id` INT NOT NULL,
  `value` INT NOT NULL,
  `payment_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `trip_id_UNIQUE` (`trip_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `payment_id_UNIQUE` (`payment_id` ASC) VISIBLE,
  INDEX `fk_transaction_trip1_idx` (`trip_id` ASC) VISIBLE,
  INDEX `fk_transaction_payment1_idx` (`payment_id` ASC) VISIBLE,
  CONSTRAINT `fk_transaction_payment1`
    FOREIGN KEY (`payment_id`)
    REFERENCES `uber`.`payment` (`id`),
  CONSTRAINT `fk_transaction_trip1`
    FOREIGN KEY (`trip_id`)
    REFERENCES `uber`.`trip` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `uber`.`vehicle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uber`.`vehicle` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `licence_plate` VARCHAR(9) NOT NULL,
  `vehicle_licence` DECIMAL(7,0) NOT NULL,
  `color_id` INT NOT NULL,
  `relase_year` INT NOT NULL,
  `car_model_id` INT NOT NULL,
  `car_maker_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `licencePlate_UNIQUE` (`licence_plate` ASC) VISIBLE,
  UNIQUE INDEX `vehicleLicence_UNIQUE` (`vehicle_licence` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_vehicle_color1_idx` (`color_id` ASC) VISIBLE,
  INDEX `fk_vehicle_relaseYear1_idx` (`relase_year` ASC) VISIBLE,
  INDEX `fk_vehicle_carmodel1_idx` (`car_model_id` ASC) VISIBLE,
  INDEX `fk_vehicle_carmaker1_idx` (`car_maker_id` ASC) VISIBLE,
  CONSTRAINT `fk_vehicle_carmaker1`
    FOREIGN KEY (`car_maker_id`)
    REFERENCES `uber`.`carmaker` (`id`),
  CONSTRAINT `fk_vehicle_carmodel1`
    FOREIGN KEY (`car_model_id`)
    REFERENCES `uber`.`carmodel` (`id`),
  CONSTRAINT `fk_vehicle_color1`
    FOREIGN KEY (`color_id`)
    REFERENCES `uber`.`color` (`id`),
  CONSTRAINT `fk_vehicle_relaseYear1`
    FOREIGN KEY (`relase_year`)
    REFERENCES `uber`.`release_year` (`year`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
