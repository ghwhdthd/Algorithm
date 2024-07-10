-- 코드를 입력하세요
SELECT B.CATEGORY ,sum(A.SALES) as TOTAL_SALES
from BOOK_SALES as A
left join BOOK as B
on A.BOOK_ID = B.BOOK_ID
where A.SALES_DATE >= '2022-01-01' and A.SALES_DATE <= '2022-01-31'
group by B.CATEGORY
order by B.CATEGORY