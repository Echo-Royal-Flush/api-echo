DROP TABLE IF EXISTS "feedback" CASCADE;

DROP TABLE IF EXISTS "user_team" CASCADE;

DROP TABLE IF EXISTS "team" CASCADE;

DROP TABLE IF EXISTS "card" CASCADE;

DROP TABLE IF EXISTS "users" CASCADE;

DROP TABLE IF EXISTS "service" CASCADE;

DROP TABLE IF EXISTS "company" CASCADE;

CREATE TABLE
    "feedback" (
        "id" UUID PRIMARY KEY NOT NULL,
        "id_card" UUID NOT NULL,
        "id_evaluator" UUID NOT NULL,
        "id_evaluated" UUID NOT NULL,
        "description" VARCHAR(255) NOT NULL,
        "date" DATE NOT NULL,
        "is_anon" BOOLEAN NOT NULL,
        "type" VARCHAR(30) CHECK ("type" IN ('USER', 'SERVICE')) NOT NULL
    );

CREATE TABLE
    "users" (
        "id" UUID PRIMARY KEY NOT NULL,
        "name" VARCHAR(70) NOT NULL,
        "role" VARCHAR(30) CHECK ("role" IN ('ADMIN', 'EMPLOYEE')) NOT NULL,
        "position" VARCHAR(50) NOT NULL,
        "email" VARCHAR(80) UNIQUE NOT NULL,
        "password" VARCHAR(60) NOT NULL
    );

CREATE TABLE
    "card" (
        "id" UUID PRIMARY KEY NOT NULL,
        "category" VARCHAR(15) CHECK ("category" IN ('Comum', 'Raro', 'Epico', 'Lendario')) NOT NULL,
        "type" VARCHAR(30) CHECK ("type" IN ('NEGATIVE', 'POSITIVE')) NOT NULL
    );


CREATE TABLE
    "users_team" (
        "id" UUID PRIMARY KEY NOT NULL,
        "id_users" UUID NOT NULL,
        "id_team" UUID NOT NULL
    );

CREATE TABLE
    "team" (
        "id" UUID PRIMARY KEY NOT NULL,
        "name" VARCHAR(80) NOT NULL,
        "id_service" UUID NOT NULL,
        "length" INTEGER NOT NULL,
        "id_company" UUID NOT NULL
    );

CREATE TABLE
    "company" (
        "id" UUID PRIMARY KEY NOT NULL,
        "name" VARCHAR(80) NOT NULL,
        "ad_country" VARCHAR(70) NOT NULL,
        "ad_state" VARCHAR(70) NOT NULL,
        "ad_city" VARCHAR(70) NOT NULL,
        "ad_street" VARCHAR(70) NOT NULL,
        "ad_number" INTEGER NOT NULL,
        "ad_cep" INTEGER NOT NULL
    );

CREATE TABLE
    "service" (
        "id" UUID PRIMARY KEY NOT NULL,
        "name" VARCHAR(70) NOT NULL
    );

ALTER TABLE "team" ADD CONSTRAINT "team_id_company_foreign" FOREIGN KEY ("id_company") REFERENCES "company" ("id");

ALTER TABLE "team" ADD CONSTRAINT "team_id_service_foreign" FOREIGN KEY ("id_service") REFERENCES "service" ("id");

ALTER TABLE "feedback" ADD CONSTRAINT "feedback_id_evaluator_foreign" FOREIGN KEY ("id_evaluator") REFERENCES "users" ("id");

ALTER TABLE "users_team" ADD CONSTRAINT "users_team_id_team_foreign" FOREIGN KEY ("id_team") REFERENCES "team" ("id");

ALTER TABLE "feedback" ADD CONSTRAINT "feedback_id_card_foreign" FOREIGN KEY ("id_card") REFERENCES "card" ("id");

ALTER TABLE "users_team" ADD CONSTRAINT "users_team_id_users_foreign" FOREIGN KEY ("id_users") REFERENCES "users" ("id");

-- MOCK COMPANIES
INSERT INTO "company" ("id", "name", "ad_country", "ad_state", "ad_city", "ad_street", "ad_number", "ad_cep") VALUES
  ('11111111-1111-1111-1111-111111111111', 'TechCorp', 'Brasil', 'RS', 'Porto Alegre', 'Rua dos Andradas', 100, 90020001);

-- MOCK SERVICES
INSERT INTO "service" ("id", "name") VALUES
  ('22222222-2222-2222-2222-222222222222', 'Desenvolvimento de Software');

-- MOCK TEAMS
INSERT INTO "team" ("id", "name", "id_service", "length", "id_company") VALUES
  ('33333333-3333-3333-3333-333333333333', 'Dev Team', '22222222-2222-2222-2222-222222222222', 6, '11111111-1111-1111-1111-111111111111');

-- MOCK usersS
INSERT INTO "users" ("id", "name", "role", "position", "email", "password") VALUES
  ('44444444-4444-4444-4444-444444444444', 'João Silva', 'ADMIN', 'Product Owner', 'joao@techcorp.com', '123456'),
  ('55555555-5555-5555-5555-555555555555', 'Maria Souza', 'EMPLOYEE', 'Dev Backend', 'maria@techcorp.com', '123456');

-- MOCK users_TEAM
INSERT INTO "users_team" ("id", "id_users", "id_team") VALUES
  ('66666666-6666-6666-6666-666666666666', '44444444-4444-4444-4444-444444444444', '33333333-3333-3333-3333-333333333333'),
  ('77777777-7777-7777-7777-777777777777', '55555555-5555-5555-5555-555555555555', '33333333-3333-3333-3333-333333333333');

-- MOCK CARDS
INSERT INTO "card" ("id", "category", "type") VALUES
  ('88888888-8888-8888-8888-888888888888', 'Comum', 'POSITIVE'),
  ('99999999-9999-9999-9999-999999999999', 'Raro', 'NEGATIVE'),
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Epico', 'POSITIVE'),
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Lendario', 'NEGATIVE');


-- MOCK FEEDBACKS
INSERT INTO "feedback" ("id", "id_card", "id_evaluator", "id_evaluated", "description", "date", "is_anon", "type") VALUES
  ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', '88888888-8888-8888-8888-888888888888', '44444444-4444-4444-4444-444444444444', '55555555-5555-5555-5555-555555555555', 'Muito colaborativa no último sprint!', '2024-05-31', false, 'users'),
  ('aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', '99999999-9999-9999-9999-999999999999', '55555555-5555-5555-5555-555555555555', '44444444-4444-4444-4444-444444444444', 'Teve alguns atrasos nas entregas.', '2024-05-31', true, 'users');
