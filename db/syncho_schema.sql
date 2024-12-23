-- Check if the syncho schema exists
CREATE DATABASE IF NOT EXISTS syncho;

USE syncho;

--
-- Users --
--

-- Avatar
CREATE TABLE `syncho`.`avatars` (
  `avatar_id` INT NOT NULL AUTO_INCREMENT,
  `avatar_name` VARCHAR(45) NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  `price` INT NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`avatar_id`),
  UNIQUE INDEX `avatar_name_UNIQUE` (`avatar_name` ASC) VISIBLE);

-- User
CREATE TABLE `syncho`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_email` VARCHAR(255) NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `avatar_id` INT NULL DEFAULT NULL,
  `role` ENUM('USER', 'ADMIN') NULL DEFAULT 'USER',
  `provider_type` ENUM('LOCAL', 'SOCIAL') NULL DEFAULT NULL,
  `social_type` ENUM('KAKAO', 'GOOGLE') NULL DEFAULT NULL,
  `token` VARCHAR(512) NULL DEFAULT NULL,
  `join_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC) VISIBLE,
  INDEX `user_avatar_id_idx` (`avatar_id` ASC) VISIBLE,
  CONSTRAINT `user_avatar_id`
    FOREIGN KEY (`avatar_id`)
    REFERENCES `syncho`.`avatars` (`avatar_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
    
--
-- Payments --
--

-- Plans
CREATE TABLE `syncho`.`plans` (
  `plan_id` INT NOT NULL AUTO_INCREMENT,
  `plan_name` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `duration_days` INT NOT NULL,
  `features` TEXT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`plan_id`));
  
-- Subscriptions
CREATE TABLE `syncho`.`subscriptions` (
  `subscription_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `plan_id` INT NOT NULL,
  `start_date` TIMESTAMP NULL,
  `end_date` TIMESTAMP NULL,
  `status` ENUM('ACTIVE', 'CANCELED', 'EXPIRED') NULL DEFAULT 'ACTIVE',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`subscription_id`),
  INDEX `subscriptions_user_idx` (`user_id` ASC) VISIBLE,
  INDEX `subscriptions_plan_idx` (`plan_id` ASC) VISIBLE,
  CONSTRAINT `subscriptions_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `syncho`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `subscriptions_plan`
    FOREIGN KEY (`plan_id`)
    REFERENCES `syncho`.`plans` (`plan_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
-- Payments
CREATE TABLE `syncho`.`payments` (
  `payment_id` INT NOT NULL AUTO_INCREMENT,
  `subscription_id` INT NOT NULL,
  `amount` INT NOT NULL,
  `payment_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_status` ENUM('SUCCESS', 'FAILED') NULL DEFAULT 'SUCCESS',
  `transaction_id` VARCHAR(255) NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE INDEX `transaction_id_UNIQUE` (`transaction_id` ASC) VISIBLE,
  INDEX `payments_subscription_idx` (`subscription_id` ASC) VISIBLE,
  CONSTRAINT `payments_subscription`
    FOREIGN KEY (`subscription_id`)
    REFERENCES `syncho`.`subscriptions` (`subscription_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

--
-- Document --
--

-- Documents
CREATE TABLE `syncho`.`documents` (
  `doc_no` INT NOT NULL AUTO_INCREMENT,
  `doc_name` VARCHAR(45) NOT NULL,
  `doc_detail` TEXT NULL,
  `doc_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `last_updated` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `last_member_id` INT NULL,
  `parent_no` INT NULL,
  PRIMARY KEY (`doc_no`),
  INDEX `documents_last_edit_member_idx` (`last_member_id` ASC) VISIBLE,
  CONSTRAINT `documents_last_edit_member`
    FOREIGN KEY (`last_member_id`)
    REFERENCES `syncho`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `documents_parent`
    FOREIGN KEY (`parent_no`)
    REFERENCES `syncho`.`documents`(`doc_no`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
--  Documents Member
CREATE TABLE `syncho`.`document_members` (
  `menber_no` INT NOT NULL AUTO_INCREMENT,
  `doc_no` INT NOT NULL,
  `user_id` INT NOT NULL,
  `join_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `menber_role` VARCHAR(45) NULL,
  `last_update` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`menber_no`),
  INDEX `doc_mem_doc_no_idx` (`doc_no` ASC) VISIBLE,
  INDEX `doc_mem_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `doc_mem_doc_no`
    FOREIGN KEY (`doc_no`)
    REFERENCES `syncho`.`documents` (`doc_no`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `doc_mem_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `syncho`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

--
-- Chat --
--

-- Chatrooms
CREATE TABLE `syncho`.`chatrooms` (
  `room_id` INT NOT NULL AUTO_INCREMENT,
  `document_no` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `owner_id` INT NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`room_id`),
  INDEX `chatrooms_document_id_idx` (`document_no` ASC) VISIBLE,
  INDEX `chatrooms_owner_id_idx` (`owner_id` ASC) VISIBLE,
  CONSTRAINT `chatrooms_document_no`
    FOREIGN KEY (`document_no`)
    REFERENCES `syncho`.`documents` (`doc_no`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `chatrooms_owner_id`
    FOREIGN KEY (`owner_id`)
    REFERENCES `syncho`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
-- Messages
CREATE TABLE `syncho`.`messages` (
  `message_id` INT NOT NULL AUTO_INCREMENT,
  `room_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `content` TEXT NULL,
  `send_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`message_id`),
  INDEX `messages_room_id_idx` (`room_id` ASC) VISIBLE,
  INDEX `messages_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `messages_room_id`
    FOREIGN KEY (`room_id`)
    REFERENCES `syncho`.`chatrooms` (`room_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `messages_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `syncho`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
-- User<->ChatRooms
CREATE TABLE `syncho`.`userchatrooms` (
  `userchatrooms_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `room_id` INT NOT NULL,
  `joined_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `role` VARCHAR(45) NULL,
  `alarm` INT NULL DEFAULT 1,
  `favorite` INT NULL DEFAULT 1,
  PRIMARY KEY (`userchatrooms_id`),
  INDEX `userchatrooms_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `userchatrooms_room_id_idx` (`room_id` ASC) VISIBLE,
  CONSTRAINT `userchatrooms_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `syncho`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `userchatrooms_room_id`
    FOREIGN KEY (`room_id`)
    REFERENCES `syncho`.`chatrooms` (`room_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


