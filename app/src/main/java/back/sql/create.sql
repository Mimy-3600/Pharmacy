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
  medoc_add_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE purchase (
  purchase_id VARCHAR(30) PRIMARY KEY,
  purchase_client VARCHAR(64) NOT NULL DEFAULT '',
  purchase_date TIMESTAMP NOT NULL
);

CREATE TABLE purchase_row (
  purchase_id VARCHAR(30) NOT NULL,
  medoc_number VARCHAR(30) NOT NULL,
  purchase_row_unit_price NUMERIC(10, 2) NOT NULL,
  purchase_row_count INTEGER NOT NULL DEFAULT 1,

  FOREIGN KEY (purchase_id) REFERENCES purchase(purchase_id) ON DELETE CASCADE,
  FOREIGN KEY (medoc_number) REFERENCES medoc(medoc_number) 
);

CREATE TABLE entry (
  entry_number VARCHAR(30) PRIMARY KEY,
  medoc_number VARCHAR(30) NOT NULL,
  entry_stock INTEGER NOT NULL,
  entry_date TIMESTAMP NOT NULL,

  FOREIGN KEY (medoc_number) REFERENCES medoc(medoc_number) ON DELETE CASCADE
);

CREATE INDEX idx_purchase_row_purchase_id ON purchase_row(purchase_id);
CREATE INDEX idx_purchase_row_medoc_number ON purchase_row(medoc_number);

CREATE INDEX idx_entry_medoc_number ON entry(medoc_number);