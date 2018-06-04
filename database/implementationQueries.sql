--Queries on the implementation of scamChecker
------------------------------------------------
--**All scams**

select s.title, u.username, s.description, s.url, s.dateadded
from scam s, user u
where s.idauthor = u.id;

--**All scams by user <Change name por parametro>**

select s.title, u.username, s.description, s.url, s.dateadded
from scam s, user u
where s.idauthor = u.id and u.username = 'name';

--**All scams on date <change '12,08,91' by date on same format>**

select s.title, u.username, s.description, s.url, s.dateadded
from scam s, user u
where s.idauthor = u.id and s.dateadded = STR_TO_DATE('12,08,91','%d,%m,%yy');

--**All scams that include <change PASS for parameter>**

select s.title, u.username, s.description, s.url, s.dateadded
from scam s, user u
where s.idauthor = u.id and (upper(s.title) like '%PASS%' or upper(s.description) like '%PASS%');

--**Count number of aparitions of <replace PASS for a parameter> in each title/description**

select (((length(title) - length(replace(upper(title),'PASS','')))/length('PASS'))*3
  + (((length(description) - length(replace(upper(description),'PASS','')))/length('PASS')))) as numTimes
from scam;

--**All queries with <replace PASS for a parameter> and a number of aparitions of <replace PASS for a parameter> in each title/description**

select s.title, u.username, s.description, s.url, s.dateadded, (
  select (((length(title) - length(replace(upper(title),'PASS','')))/length('PASS'))*3
    + (((length(description) - length(replace(upper(description),'PASS','')))/length('PASS')))) as numTimes
  from scam s2
  where s2.id = s.id) as numTimes
from scam s, user u
where s.idauthor = u.id and (upper(s.title) like '%PASS%' or upper(s.description) like '%PASS%');

--**Insert <replace MyName for a parameter> user into the database**

insert into user(username)
values('MyName');

--**Insert scam into the database <add in parenthesis values of the fields in scam(), url can be null, now() to put current time>**

insert into scam(idauthor, title, description, url, dateadded)
values(9, 'title', 'description', 'url', now());

--**Get user id for <replace name with parameter>**

select id
from user
where username='name';
