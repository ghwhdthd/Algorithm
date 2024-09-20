-- 코드를 작성해주세요
select t.QUATER QUARTER ,count(t.QUATER) ECOLI_COUNT
from
(select 
    case 
        when m.d >= 1 and m.d <= 3 then '1Q'
        when m.d >= 4 and m.d <= 6 then '2Q'
        when m.d >= 7 and m.d <= 9 then '3Q'
        when m.d >= 10 and m.d <= 12 then '4Q'
    end as QUATER
from
    (select substring(DIFFERENTIATION_DATE, 6, 2) as d
    from ECOLI_DATA) as m ) as t
    
group by t.QUATER
order by 1 
