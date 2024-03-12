-- 코드를 작성해주세요
#부서별로 부서 ID, 영문 부서명, 평균 연봉
select E.DEPT_ID, H.DEPT_NAME_EN, round(avg(SAL),0) as AVG_SAL
from HR_EMPLOYEES as E
left join HR_DEPARTMENT as H
on E.DEPT_ID = H.DEPT_ID
group by E.DEPT_ID
order by 3 DESC
;