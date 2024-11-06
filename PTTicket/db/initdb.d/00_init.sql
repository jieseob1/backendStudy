CREATE DATABASE IF NOT EXISTS pass_local;
USE pass_local;

GRANT ALL PRIVILEGES ON pass_local.* TO 'pass_local_user'@'%';
FLUSH PRIVILEGES;
