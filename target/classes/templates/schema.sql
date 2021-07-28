DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users`(
      `id` INT(200) AUTO_INCREMENT,
      `age` INT(4),
      `email` VARCHAR(30),
      `name` VARCHAR(30),
      `nameLast` VARCHAR(30),
      `password` VARCHAR(30),

      PRIMARY KEY (`id`)
);

 CREATE TABLE IF NOT EXISTS `roles` (
                          `id` bigint(20) ,
                          `role` varchar(250) DEFAULT NULL,
                          PRIMARY KEY (`id`)
 );
 CREATE TABLE IF NOT EXISTS `users_roles` (
                         `user_id` bigint(20) unsigned DEFAULT NULL,
                          `role_id` bigint(20)

 );
 INSERT INTO `users` VALUES
       (1,'kemp', 'kemp', 20, 'kemp@mail.ru', 'kemp', '1234'),
       (2,'kemp2', 'kemp2', 20, 'kemp@mail.ru', 'ben', '1234'),


 INSERT INTO `roles` (`id`, `role`)
       VALUES
       (1,'ROLE_ADMIN'),
       (2,'ROLE_USER');

 insert into users_roles
       values
       (1, 1),
       (1, 2),
       (2, 2);