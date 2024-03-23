-- 코드를 입력하세요
SELECT A.APNT_NO, P.PT_NAME, P.PT_NO, A.MCDP_CD, D.DR_NAME, A.APNT_YMD
from APPOINTMENT A
left join PATIENT P
on A.PT_NO = P.PT_NO
left join DOCTOR D
on A.MDDR_ID = D.DR_ID
where D.MCDP_CD = 'CS'
and A.APNT_CNCL_YN = 'N'
and TRUNC(A.APNT_YMD) = to_date('2022-04-13','YYYY-MM-DD')
order by A.APNT_YMD
;