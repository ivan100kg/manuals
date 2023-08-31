-- PL/pgSQL is a procedural programming language for the PostgreSQL database system

-- Создание функций, хранимых процедур и триггеров
-- Управляющие структуры - if, case и loop.
-- Наследует все пользовательские функции, операторы и типы

-- dollar-quoted string constant syntax
-- используется в функциях, анонимных блоках и процедурах
select 'String constant';        -- обычный SQL синтаксис
select $tag$String constant$tag$; -- tag - тэг
select $$String constant$$;       -- можно без тэга

-- PL/pgSQL Block Structure
-- функции и процедуры на языке PL/pgSQL организрваны в блоках:
[ <<label>> ]       -- label указывают для обращения к нему в теле блока  
[ declare           -- тут объявляются переменные для использования в теле
    declarations ]  -- каждое объявление переменной разделяется ;
begin               -- body - обязательная секция
    statements;     -- логика/код
	...
end [ label ];      -- конец блока

-- пример на анонимном блоке
-- do и $$ относятся к анонимному блоку
do $$
<<first_block>>
declare
  film_count integer := 0;      -- переменная film_count
begin
   -- get the number of films
   select count(*)              -- кол-во строк в таб film
   into film_count              -- присваиваем кол-во в нашу переменную
   from film;                   -- таблица film
   -- вывод в консоль, вместо % подставить film_count
   raise notice 'The number of films is %', film_count;
end first_block $$;
-- $$ относятся к анонимному блоку
-- вывод
NOTICE:  The current value of counter is 1