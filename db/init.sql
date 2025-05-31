DROP TABLE IF EXISTS "feedback" CASCADE;

DROP TABLE IF EXISTS "users_team" CASCADE;

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
        "password" VARCHAR(60) NOT NULL,
        "id_company" UUID NOT NULL
    );

CREATE TABLE
    "card" (
        "id" UUID PRIMARY KEY NOT NULL,
        "category" VARCHAR(50) CHECK ("category" IN ('LEGEND', 'EPIC', 'RARE', 'COMMON')),
        "type" VARCHAR(30) CHECK ("type" IN ('CRITICISM', 'POSITIVE')) NOT NULL
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

ALTER TABLE "users" ADD CONSTRAINT "user_id_company_foreign" FOREIGN KEY ("id_company") REFERENCES "company" ("id");

ALTER TABLE "team" ADD CONSTRAINT "team_id_service_foreign" FOREIGN KEY ("id_service") REFERENCES "service" ("id");

ALTER TABLE "feedback" ADD CONSTRAINT "feedback_id_evaluator_foreign" FOREIGN KEY ("id_evaluator") REFERENCES "users" ("id");

ALTER TABLE "users_team" ADD CONSTRAINT "users_team_id_team_foreign" FOREIGN KEY ("id_team") REFERENCES "team" ("id");

ALTER TABLE "feedback" ADD CONSTRAINT "feedback_id_card_foreign" FOREIGN KEY ("id_card") REFERENCES "card" ("id");

ALTER TABLE "users_team" ADD CONSTRAINT "users_team_id_users_foreign" FOREIGN KEY ("id_users") REFERENCES "users" ("id");

-- Populando company
INSERT INTO
    "company" (
        "id",
        "name",
        "ad_country",
        "ad_state",
        "ad_city",
        "ad_street",
        "ad_number",
        "ad_cep"
    )
VALUES
    (
        '11111111-1111-1111-1111-111111111111',
        'Empresa Exemplo',
        'Brasil',
        'SP',
        'São Paulo',
        'Rua A',
        100,
        12345678
    );

-- Populando service
INSERT INTO
    "service" ("id", "name")
VALUES
    (
        '22222222-2222-2222-2222-222222222222',
        'Consultoria'
    );

-- Populando users
INSERT INTO
    "users" (
        "id",
        "name",
        "role",
        "position",
        "email",
        "password",
        "id_company"
    )
VALUES
    (
        '33333333-3333-3333-3333-333333333333',
        'João Silva',
        'ADMIN',
        'Gerente',
        'joao@empresa.com',
        '$2a$12$sESEj/5ELomkqXvN2CArUOYUlHDTO/ws7Eb/Exxa1SvCP7VxiMpcK',
        '11111111-1111-1111-1111-111111111111'
    ),
    (
        '33333333-3333-3333-3333-333333333334',
        'Maria Souza',
        'EMPLOYEE',
        'Analista',
        'maria@empresa.com',
        '$2a$12$sESEj/5ELomkqXvN2CArUOYUlHDTO/ws7Eb/Exxa1SvCP7VxiMpcK',
        '11111111-1111-1111-1111-111111111111'
    ),
    (
        '33333333-3333-3333-3333-333333333335',
        'Joana Moura',
        'EMPLOYEE',
        'Dev',
        'joana@empresa.com',
        '$2a$12$sESEj/5ELomkqXvN2CArUOYUlHDTO/ws7Eb/Exxa1SvCP7VxiMpcK',
        '11111111-1111-1111-1111-111111111111'
    );

-- Populando card
INSERT INTO
    "card" ("id", "category", "type")
VALUES
    (
        '44444444-4444-4444-4444-444444444444',
        null,
        'CRITICISM'
    ),
    (
        '44444444-4444-4444-4444-444444444445',
        'LEGEND',
        'POSITIVE'
    ),
    (
        '44444444-4444-4444-4444-444444444446',
        'EPIC',
        'POSITIVE'
    ),
    (
        '44444444-4444-4444-4444-444444444447',
        'RARE',
        'POSITIVE'
    ),
    (
        '44444444-4444-4444-4444-444444444448',
        'COMMON',
        'POSITIVE'
    );

;

-- Populando team
INSERT INTO
    "team" (
        "id",
        "name",
        "id_service",
        "length",
        "id_company"
    )
VALUES
    (
        '55555555-5555-5555-5555-555555555555',
        'Equipe Alpha',
        '22222222-2222-2222-2222-222222222222',
        5,
        '11111111-1111-1111-1111-111111111111'
    );

-- Populando user_team
INSERT INTO
    "users_team" ("id", "id_users", "id_team")
VALUES
    (
        '66666666-6666-6666-6666-666666666666',
        '33333333-3333-3333-3333-333333333333',
        '55555555-5555-5555-5555-555555555555'
    ),
    (
        '66666666-6666-6666-6666-666666666667',
        '33333333-3333-3333-3333-333333333334',
        '55555555-5555-5555-5555-555555555555'
    ),
    (
     '66666666-6666-6666-6666-666666666668',
     '33333333-3333-3333-3333-333333333335',
     '55555555-5555-5555-5555-555555555555'
    );

-- Populando feedback
INSERT INTO
    "feedback" (
        "id",
        "id_card",
        "id_evaluator",
        "id_evaluated",
        "description",
        "date",
        "is_anon",
        "type"
    )
VALUES
    (
        '77777777-7777-7777-7777-777777777777',
        '44444444-4444-4444-4444-444444444444',
        '33333333-3333-3333-3333-333333333333',
        '33333333-3333-3333-3333-333333333334',
        'Ótima comunicação.',
        '2024-05-01',
        false,
        'USER'
    ),
    (
        '77777777-7777-7777-7777-777777777778',
        '44444444-4444-4444-4444-444444444445',
        '33333333-3333-3333-3333-333333333334',
        '33333333-3333-3333-3333-333333333333',
        'Excelente trabalho em equipe.',
        '2024-05-02',
        true,
        'USER'
    );