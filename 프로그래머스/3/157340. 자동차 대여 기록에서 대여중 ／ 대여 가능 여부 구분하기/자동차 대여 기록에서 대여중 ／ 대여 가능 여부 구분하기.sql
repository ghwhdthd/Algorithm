select a.car_id,
    case when b.history_id is not null then '대여중'
        else '대여 가능'
    end as AVAILABILITY
from (
    select car_id
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY  
    group by car_id
) a
left join
(
    select car_id, history_id
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where 1=1
        and TO_DATE('2022-10-16', 'yyyy-mm-dd') >= start_date
        and TO_DATE('2022-10-16', 'yyyy-mm-dd') <= end_date
) b
on a.car_id = b.car_id
order by a.car_id desc
;
