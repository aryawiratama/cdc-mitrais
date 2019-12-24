CREATE TABLE withdraw(
    id varchar(36) PRIMARY KEY,
    transaction_date date NOT NULL,
    amount numeric(10,2) NOT NULL,
    id_account varchar(36) NOT NULL,
    FOREIGN KEY (id_account) REFERENCES account(id)
);