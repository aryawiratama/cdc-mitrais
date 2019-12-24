CREATE TABLE fund_transfer(
    id varchar(36) PRIMARY KEY,
    transaction_date date NOT NULL,
    amount numeric(10,2) NOT NULL,
    reference_number varchar(100) NOT NULL,
    id_account_from varchar(36) NOT NULL,
    id_account_to varchar(36) NOT NULL,
    FOREIGN KEY (id_account_from) REFERENCES account(id),
    FOREIGN KEY (id_account_to) REFERENCES account(id)
);