-- 코드를 작성해주세요

select id,
    case 
        when rank_size / (select count(*) from ECOLI_DATA) <= 1/4 then 'CRITICAL'
        when rank_size / (select count(*) from ECOLI_DATA) > 1/4 
            and rank_size / (select count(*) from ECOLI_DATA) <= 1/2 then 'HIGH'
        when rank_size / (select count(*) from ECOLI_DATA) > 1/2 
            and rank_size / (select count(*) from ECOLI_DATA) <= 3/4 then 'MEDIUM'
        else 'LOW'
    end as COLONY_NAME
from (select id, SIZE_OF_COLONY,
    rank() over (order by SIZE_OF_COLONY DESC) as rank_size
from ECOLI_DATA) as A
order by id
;
