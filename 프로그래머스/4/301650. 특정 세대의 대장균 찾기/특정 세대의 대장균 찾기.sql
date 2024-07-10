-- 코드를 작성해주세요
select C.id as ID
from ECOLI_DATA as C
right join (select A.id 
            from ECOLI_DATA as A
            right join (select id from ECOLI_DATA where PARENT_ID is null) as B
            on A.PARENT_ID = B.id) as D
on C.PARENT_ID = D.id
where C.id is not null
order by C.id
;



