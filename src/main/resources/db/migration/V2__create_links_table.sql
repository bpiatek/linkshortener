CREATE TABLE links
(
    link_id         SERIAL PRIMARY KEY,
    original_link   TEXT        NOT NULL,
    short_link      VARCHAR(10) NOT NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    isActive        BOOLEAN   DEFAULT TRUE,
    hits            INT       DEFAULT 0,
    is_custom_alias BOOLEAN   DEFAULT FALSE,
    expiration_date DATE        NOT NULL
);
