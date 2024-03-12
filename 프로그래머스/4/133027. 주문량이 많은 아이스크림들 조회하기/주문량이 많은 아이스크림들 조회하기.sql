-- 코드를 입력하세요
select flavor
from
    (select temp.flavor, sum(temp.TOTAL_ORDER) as s
    from(
        select f.flavor, fs + js as TOTAL_ORDER
        from(
            SELECT f.flavor, sum(f.total_order) as fs
            from first_half f
            group by f.FLAVOR ) as f
        left join(
            SELECT j.flavor, sum(j.total_order) as js
            from july j
            group by j.FLAVOR) as j
        on f.flavor = j.flavor
    )as temp
    group by temp.flavor
    order by s desc
    limit 3
     ) as res
;