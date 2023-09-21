-- PL/pgSQL is a procedural programming language for the PostgreSQL database system

-- Создание функций, хранимых процедур и триггеров
-- Управляющие структуры - if, case и loop.
-- Наследует все пользовательские функции, операторы и типы

-- dollar-quoted string constant syntax
-- используется в функциях, анонимных блоках и процедурах
select 'String constant';         -- обычный SQL синтаксис
select $tag$String constant$tag$; -- tag - тэг
select $$String constant$$;       -- можно без тэга


-- PL/pgSQL структура блока-----------------------------------------------
-- функции и процедуры на языке PL/pgSQL организрваны в блоках:
[ <<label>> ]       -- label указывают для обращения к нему в теле блока  
[ declare           -- тут объявляются переменные для использования в теле
    declarations ]  -- каждое объявление переменной разделяется ;
begin               -- body - обязательная секция
    statements;     -- логика/код
	...
end [ label ];      -- конец блока

-- блоки могут быть вложенными друг в друга
-- пример на анонимном блоке
-- do и $$ относятся к анонимному блоку
do $$
<<first_block>>
declare
  created_at time := now();      -- переменная created_at
begin
   -- вывод в консоль, вместо % подставить created_at
   raise notice 'now is %', created_at;
end first_block $$;
-- $$ относятся к анонимному блоку
-- вывод
NOTICE:  The current value of counter is 1


-- Переменные ------------------------------------------------------
-- имя_перем  тип       := или =  по умолчанию NULL
var1 data_type [[:]= expression];   -- выбор типа вручную
var2 table_name.column_name%type;   -- тип на основе колонки
var3 var2%type;                     -- тип на основе др переменной
var4 table_name%rowtype;            -- переменная содержит всю строку

-- row type
-- синтаксис переменная имя_таблицы%rowtype
-- содержит в себе все поля строки
-- доступ к полям строки осуществляется через точку var.field
declare
  fil filial%rowtype;               -- объявить row type
begin
	SELECT * FROM filial f INTO fil WHERE f.id = 131;
    raise notice '% % %', fil.name, fil.address, fil.phone;

-- example
declare
   counter    integer := 1;   
   first_name varchar(50) := 'John';
   film_title film.title%type;
   featured_title film_title%type;
   payment    numeric(11,2) := 20.5;
   created_at time := now();
   fil filial%rowtype;


-- Select Into -----------------------------------------------------
-- позволяет выбрать данные из базы данных и присвоить их переменной
-- в обычный запрос вставляем INTO переменная и результат сохр в ней
SELECT f.name INTO vr FROM filial f WHERE f.id = 131;