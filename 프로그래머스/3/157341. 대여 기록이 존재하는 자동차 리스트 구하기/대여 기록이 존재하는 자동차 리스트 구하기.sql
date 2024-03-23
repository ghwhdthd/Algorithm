-- 코드를 입력하세요
SELECT distinct(C.CAR_ID)
from CAR_RENTAL_COMPANY_RENTAL_HISTORY H
left join CAR_RENTAL_COMPANY_CAR C
on H.CAR_ID = C.CAR_ID
where CAR_TYPE = '세단'
and trunc(H.START_DATE) <= to_date('2022-10-31','yyyy-mm-dd')
and trunc(H.START_DATE) >= to_date('2022-10-01','yyyy-mm-dd')
order by C.CAR_ID DESC
;
