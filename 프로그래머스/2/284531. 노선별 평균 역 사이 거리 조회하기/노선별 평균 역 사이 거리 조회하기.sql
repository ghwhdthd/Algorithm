-- 코드를 작성해주세요
Select ROUTE, 
CONCAT(ROUND(sum(D_BETWEEN_DIST),1),'km') as TOTAL_DISTANCE, 
CONCAT(round(avg(D_BETWEEN_DIST),2),'km') as AVERAGE_DISTANCE 
from subway_distance
group by route
order by ROUND(SUM(D_BETWEEN_DIST), 1) DESC
;