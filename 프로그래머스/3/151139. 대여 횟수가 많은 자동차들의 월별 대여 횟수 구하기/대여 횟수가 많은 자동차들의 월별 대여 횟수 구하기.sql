-- 코드를 입력하세요
select month(start_date) as MONTH, p.car_id as CAR_ID, count(history_id) as RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY as p
right join (SELECT CAR_ID
            from CAR_RENTAL_COMPANY_RENTAL_HISTORY
            where START_DATE >= '2022-08-01' and START_DATE <= '2022-10-31'
            group by CAR_ID
            having count(car_id) >= 5) as sub
on p.car_id = sub.car_id
where p.START_DATE >= '2022-08-01' and p.START_DATE <= '2022-10-31'
group by month(start_date), p.car_id
having RECORDS > 0
order by MONTH, CAR_ID desc;