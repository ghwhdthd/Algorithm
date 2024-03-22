-- 코드를 입력하세요
select U.user_id as USER_ID, U.nickname as NICKNAME, U.city ||' '|| U.STREET_ADDRESS1 || ' ' || U.STREET_ADDRESS2 as 전체주소, substr(U.TLNO,0,3) || '-' || substr(U.TLNO,4,4) || '-' || substr(U.TLNO,8,4) as 전화번호
from USED_GOODS_BOARD B
left join USED_GOODS_USER U
on B.writer_id = U.user_id
group by U.user_id, U.nickname, U.city, U.STREET_ADDRESS1, U.STREET_ADDRESS2, U.TLNO
having count(B.writer_id) >= 3
order by user_id DESC
