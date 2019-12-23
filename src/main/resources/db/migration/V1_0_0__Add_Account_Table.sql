CREATE TABLE  account(
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    pin VARCHAR(6) NOT NULL,
    account_number VARCHAR(6) NOT NULL,
    balance numeric(10,2)
);
INSERT INTO account (id, name, pin, balance, account_number)
values('50ee5c8a-2532-11ea-978f-2e728ce88125', 'John Doe', '012108', 100, '112233');

INSERT INTO account (id, name, pin, balance, account_number)
values('50ee5eec-2532-11ea-978f-2e728ce88125', 'Jane Doe', '932012', 30, '112244');