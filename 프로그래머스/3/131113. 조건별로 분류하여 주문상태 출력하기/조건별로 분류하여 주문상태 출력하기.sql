-- 코드를 입력하세요
SELECT ORDER_ID, PRODUCT_ID, to_char(OUT_DATE, 'yyyy-mm-dd'),
    case
        when trunc(OUT_DATE) <=to_date('2022-05-01','yyyy-mm-dd') then '출고완료'
        when trunc(OUT_DATE) > to_date('2022-05-01','yyyy-mm-dd') then '출고대기'
        else '출고미정'
    end as 출고여부
    from FOOD_ORDER
    order by ORDER_ID
;