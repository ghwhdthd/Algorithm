-- 코드를 작성해주세요

select id, ifnull(B.CHILD_COUNT, 0) as CHILD_COUNT
from ECOLI_DATA as A
left join (select PARENT_ID, count(parent_id) as CHILD_COUNT
            from ECOLI_DATA
            group by PARENT_ID) as B
on A.id = B.parent_id

