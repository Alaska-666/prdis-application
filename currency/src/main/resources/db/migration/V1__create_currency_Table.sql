CREATE TABLE IF NOT EXISTS currency (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    rate DOUBLE PRECISION NOT NULL,
    date VARCHAR(50),
    PRIMARY KEY(id)
);