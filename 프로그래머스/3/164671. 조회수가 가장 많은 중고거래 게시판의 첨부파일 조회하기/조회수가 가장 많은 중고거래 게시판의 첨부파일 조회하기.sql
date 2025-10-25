with tbl as 
(
    select board_id
    from used_goods_board
    order by views desc 
    FETCH FIRST 1 ROWS ONLY
)

select '/home/grep/src/'|| A.BOARD_ID || '/' ||A.FILE_ID || A.FILE_NAME || A.FILE_EXT FILE_PATH
from used_goods_file A
where a.board_id in (select board_id from tbl)
order by a.file_id desc
;