DROP TABLE IF EXISTS entry;
DROP TABLE IF EXISTS purchase_row;
DROP TABLE IF EXISTS purchase;
DROP TABLE IF EXISTS medoc;

CREATE TABLE medoc (
  medoc_number VARCHAR(30) PRIMARY KEY,
  medoc_designation VARCHAR(64) NOT NULL,
  medoc_unit_price NUMERIC(10, 2) NOT NULL DEFAULT 0.50,
  medoc_stock INTEGER NOT NULL DEFAULT 0,
  medoc_is_active BOOLEAN NOT NULL DEFAULT TRUE, 
  medoc_type VARCHAR(30) NOT NULL CHECK (medoc_type IN (
    'TABLE',
    'CAPSULE',
    'SYRUP',
    'DROPS',
    'INJECTION',
    'CREAM',
    'GEL',
    'SPRAY',
    'INHALER'
  ))
);


CREATE TABLE purchase (
  purchase_id VARCHAR(30) PRIMARY KEY,
  purchase_client VARCHAR(64) NOT NULL DEFAULT '',
  purchase_date TIMESTAMP NOT NULL
);

CREATE TABLE purchase_row (
  purchase_id VARCHAR(20) NOT NULL,
  medoc_number VARCHAR(20) NOT NULL,
  purchase_row_unit_price NUMERIC(10, 2) NOT NULL,
  purchase_row_count INTEGER NOT NULL DEFAULT 1,

  FOREIGN KEY (purchase_id) REFERENCES purchase(purchase_id) ON DELETE CASCADE,
  FOREIGN KEY (medoc_number) REFERENCES medoc(medoc_number) 
);

CREATE TABLE entry (
  entry_number VARCHAR(20) PRIMARY KEY,
  medoc_number VARCHAR(20) NOT NULL,
  entry_stock INTEGER NOT NULL,
  entry_date TIMESTAMP NOT NULL,

  FOREIGN KEY (medoc_number) REFERENCES medoc(medoc_number) ON DELETE CASCADE
);