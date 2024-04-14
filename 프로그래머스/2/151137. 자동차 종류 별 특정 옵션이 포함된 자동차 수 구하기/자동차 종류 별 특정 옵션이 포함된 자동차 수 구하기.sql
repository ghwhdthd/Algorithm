-- 코드를 입력하세요

-- car_id, car_type, daily_fee, options

select car_type, count(*) cars
from CAR_RENTAL_COMPANY_CAR
where options like '%통풍시트%' or options like '%열선시트%' or options like '%가죽시트%'
group by car_type
order by car_type
;