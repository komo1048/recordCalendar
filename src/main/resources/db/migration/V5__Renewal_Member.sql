ALTER TABLE member ADD COLUMN(
    phone VARCHAR(50) NULL DEFAULT NULL,
    email VARCHAR(50) NULL DEFAULT NULL,
    name VARCHAR(30) NOT NULL,
    address VARCHAR(100) NULL DEFAULT NULL,
    job VARCHAR(50) NULL DEFAULT NULL,
    regdate DATETIME NOT NULL DEFAULT now()
)
