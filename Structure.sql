  DROP TABLE IF EXISTS USERS CASCADE ;
  DROP TABLE IF EXISTS PURCHASES CASCADE ;
  DROP TABLE IF EXISTS LOGINS CASCADE ;
  DROP TABLE IF EXISTS TICKETS CASCADE ;
  DROP TABLE IF EXISTS EVENTS CASCADE ;
  DROP TABLE IF EXISTS TICKET_KINDS CASCADE ;
  DROP TABLE IF EXISTS CLIENTS_DISCOUNTS CASCADE ;
  DROP TABLE IF EXISTS DISCOUNTS CASCADE ;
  DROP TABLE IF EXISTS EVENT_Types CASCADE ;
  DROP TABLE if exists LAST_LOGINS CASCADE;


  CREATE TABLE TICKET_KINDS(
    Kind VARCHAR PRIMARY KEY,
    Discount NUMERIC DEFAULT 0
  );

  create table USERS (
    ID NUMERIC PRIMARY KEY,
    Name VARCHAR NOT NULL,
    Surname VARCHAR NOT NULL
  );

  CREATE TABLE LAST_LOGINS(
  IDUser numeric not null references USERS(ID),
  lastLog timestamp default now()
);

  CREATE TABLE DISCOUNTS(
    ID NUMERIC PRIMARY KEY,
    Dicsount NUMERIC DEFAULT 0
  );

  CREATE TABLE CLIENTS_DISCOUNTS(
    --ID NUMERIC PRIMARY KEY,
    Discount_ID NUMERIC REFERENCES DISCOUNTS(ID),
    Clients_ID NUMERIC NOT NULL REFERENCES USERS(ID),
    number NUMERIC DEFAULT(0)
 --   Date_From DATE NOT NULL,
 --   Date_Due DATE NOT NULL CHECK(Date_From < Date_Due )
  );

  CREATE TABLE EVENT_Types(
    type VARCHAR PRIMARY KEY,
    price NUMERIC NOT NULL
  );

  CREATE TABLE EVENTS(
    ID NUMERIC PRIMARY KEY,
    Name VARCHAR NOT NULL,
    Type VARCHAR NOT NULL REFERENCES EVENT_Types(type),
    Date DATE NOT NULL
  );

  CREATE TABLE TICKETS(
    ID NUMERIC PRIMARY KEY,
    Event_ID NUMERIC NOT NULL REFERENCES EVENTS(ID),
    Kind VARCHAR NOT NULL REFERENCES TICKET_KINDS(Kind)
  );

  create table PURCHASES (
    User_ID NUMERIC NOT NULL REFERENCES USERS(ID),
    Ticket_ID NUMERIC NOT NULL REFERENCES TICKETS(ID),
    Date DATE NOT NULL
  );

  CREATE TABLE LOGINS(
    ID NUMERIC PRIMARY KEY REFERENCES USERS(ID),
    Username VARCHAR NOT NULL,
    Password VARCHAR NOT NULL
  );

INSERT INTO event_types VALUES ('small', 100), ('medium', 200), ('big', 300);
INSERT INTO DISCOUNTS VALUES (0, 0), (1, 0.10), (2, 0.20), (3, 0.30);
INSERT INTO TICKET_KINDS VALUES ('children', 0.3),('normal', 0), ('special', 0.2), ('elderly', 0.4);

CREATE OR REPLACE FUNCTION countBought() RETURNS TRIGGER AS
$BODY$ BEGIN
  UPDATE clients_discounts SET
    number = number+1 WHERE Clients_ID = new.User_ID;
  UPDATE clients_discounts SET
    Discount_ID = CASE
        WHEN number < 4 then 0
        WHEN number < 7 then 1
        WHEN number < 10 then 2
        ELSE 3
        END;
  RETURN new;
END $BODY$
LANGUAGE PLPGSQL;

CREATE TRIGGER countTickets AFTER INSERT OR UPDATE ON purchases FOR EACH ROW EXECUTE PROCEDURE countBought();

CREATE OR REPLACE FUNCTION price(IN ticketID NUMERIC, IN userID NUMERIC)
  RETURNS TABLE (a NUMERIC)
  AS
  $BODY$ BEGIN
    RETURN QUERY SELECT (price * (1-TICKET_KINDS.Discount-DISCOUNTS.Dicsount))
    FROM
        TICKETS
        join ticket_kinds on TICKETS.Kind = TICKET_KINDS.Kind
        join EVENTS on TICKETS.Event_ID = EVENTS.ID
        join EVENT_Types on EVENTS.Type = EVENT_Types.type
        join PURCHASES on TICKETS.ID = PURCHASES.Ticket_ID
        join users on PURCHASES.User_ID = USERS.ID
        join CLIENTS_DISCOUNTS ON USERS.ID = CLIENTS_DISCOUNTS.Clients_ID
        join DISCOUNTS ON CLIENTS_DISCOUNTS.Discount_ID = DISCOUNTS.ID
    where TICKETS.ID = ticketID and USERS.ID = userID;
  END;$BODY$
    LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION countTickets(VARCHAR) RETURNS INTEGER AS $$
Begin
  RETURN (SELECT
      count(*)
    FROM logins
      JOIN users ON logins.id = users.id
      JOIN purchases ON purchases.User_ID = USERS.ID
      JOIN TICKETS on TICKETS.ID = PURCHASES.Ticket_ID
      JOIN events on EVENTS.ID = TICKETS.Event_ID
    WHERE logins.Username = $1);
END;$$
LANGUAGE PLPGSQL;


CREATE OR REPLACE FUNCTION getTickets(VARCHAR) RETURNS TABLE (
  Name VARCHAR,
  Ticket_kind VARCHAR,
  Date DATE,
  Price NUMERIC
) AS $$
BEGIN
  RETURN QUERY select
    EVENTS.Name,
    Tickets.kind,
    purchases.date,
    price(tickets.id, users.id)
  from (logins
    JOIN users ON logins.id = users.id
    JOIN purchases ON purchases.User_ID = USERS.ID
    JOIN TICKETS on TICKETS.ID = PURCHASES.Ticket_ID
    join events on EVENTS.ID = TICKETS.Event_ID
    join event_types on events.type = event_types.type)
  where logins.Username = $1;
END;$$
LANGUAGE PLPGSQL;

CREATE OR REPLACE VIEW upcoming AS SELECT events.name, events.date FROM events where date > now() order by 2;

create or replace FUNCTION manageLoginsFunc() RETURNS TRIGGER as
$$BEGIN
  if 10 < (SELECT count(*) FROM LAST_LOGINS where IDUser = new.IDUser) THEN
    DELETE FROM LAST_LOGINS
    WHERE lastLog = (
      SELECT
        min(lastLog)
      FROM LAST_LOGINS
      WHERE
        IDUser = new.IDUser
    );
  END IF;
  RETURN new;
END;$$
LANGUAGE PLPGSQL;

CREATE TRIGGER manageLogins AFTER INSERT OR UPDATE on LAST_LOGINS for each ROW EXECUTE PROCEDURE manageLoginsFunc();
