/**
 * Author:  Shuey
 * Created: Jul. 19, 2020
 */

DROP DATABASE IF EXISTS klfdatabase;
CREATE DATABASE klfdatabase;
USE klfdatabase;

--SHOW GRANTS;

DROP USER IF EXISTS sample_user@'localhost';
CREATE USER sample_user@'localhost' IDENTIFIED WITH mysql_native_password BY 'sample_app' REQUIRE NONE;
GRANT ALL ON klfdatabase.* TO sample_user@'localhost';

FLUSH PRIVILEGES;

