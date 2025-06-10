DROP TABLE IF EXISTS users;
-- USERS
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100),
                       email VARCHAR(100) UNIQUE,
                       password VARCHAR(255),
                       role ENUM('user', 'admin') DEFAULT 'user'
);

DROP TABLE IF EXISTS posts;
-- POSTS
CREATE TABLE posts (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT,
                       title VARCHAR(200),
                       platform VARCHAR(100),
                       caption TEXT,
                       date DATE,
                       time TIME,
                       media_path VARCHAR(255),
                       media_type ENUM('image', 'video'),
                       status ENUM('draft', 'scheduled') DEFAULT 'scheduled',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (user_id) REFERENCES users(id)
);
