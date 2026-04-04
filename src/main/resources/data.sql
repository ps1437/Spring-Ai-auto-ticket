INSERT INTO share(id, company, quantity) VALUES (1, 'AAPL', 100);
INSERT INTO share(id, company, quantity) VALUES (2, 'AMZN', 300);
INSERT INTO share(id, company, quantity) VALUES (3, 'META', 300);
INSERT INTO share(id, company, quantity) VALUES (4, 'MSFT', 400);
INSERT INTO share(id, company, quantity) VALUES (5, 'NVDA', 200);


--
---- =============================
---- CMDB PRODUCT TABLE
---- =============================
--CREATE TABLE cmdb_product (
--    product_id BIGINT PRIMARY KEY,
--    product_name VARCHAR(255) NOT NULL,
--    owner_email VARCHAR(255),
--    description VARCHAR(500),
--    team_name VARCHAR(100),
--    criticality VARCHAR(50),
--    status VARCHAR(50)
--);
--
---- =============================
---- DISTRIBUTION LIST TABLE
---- =============================
--CREATE TABLE dl_list (
--    dl_id BIGINT PRIMARY KEY,
--    dl_name VARCHAR(255) NOT NULL,
--    team_name VARCHAR(100),
--    description VARCHAR(500)
--);
--
---- =============================
---- DL MEMBER TABLE
---- =============================
--CREATE TABLE dl_member (
--    member_id BIGINT PRIMARY KEY,
--    dl_id BIGINT,
--    member_name VARCHAR(255),
--    member_email VARCHAR(255),
--    FOREIGN KEY (dl_id) REFERENCES dl_list(dl_id)
--);
--
---- =============================
---- EMAIL → DL Mapping (Optional)
---- =============================
--CREATE TABLE email_dl_map (
--    id BIGINT PRIMARY KEY,
--    email VARCHAR(255) NOT NULL,
--    dl_id BIGINT,
--    FOREIGN KEY (dl_id) REFERENCES dl_list(dl_id)
--);

