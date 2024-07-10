-- 코드를 작성해주세요

select ID, FISH_NAME, LENGTH
from FISH_INFO as A
left join FISH_NAME_INFO as C
on A.FISH_TYPE = C.FISH_TYPE
right join (select FISH_TYPE, max(LENGTH) as LEN
            from FISH_INFO
            group by FISH_TYPE) as B
on A.FISH_TYPE = B.FISH_TYPE and A.LENGTH = B.LEN
order by A.id