select J.FLAVOR
    from JULY as J
    left join FIRST_HALF as F
    on J.FLAVOR = F.FLAVOR
    group by J.FLAVOR
    order by sum(J.TOTAL_ORDER) + F.TOTAL_ORDER DESC
    limit 3
;