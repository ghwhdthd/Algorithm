WITH sub AS(
    select MEMBER_ID, count(MEMBER_ID)
    from REST_REVIEW
    group by MEMBER_ID
    order by count(MEMBER_ID) DESC
    limit 1
)

select M.MEMBER_NAME, R.REVIEW_TEXT, date_format(REVIEW_DATE,'%Y-%m-%d') as REVIEW_DATE
    from MEMBER_PROFILE as M
    left join REST_REVIEW as R
    on M.MEMBER_ID = R.MEMBER_ID
    where M.MEMBER_ID = (select MEMBER_ID from sub)
    order by 3,2
;