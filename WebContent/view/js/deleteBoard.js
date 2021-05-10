let confirmDel = (pageNum) =>{
	if(confirm("정말로 삭제하시겠습니까?")){
		location.href=`../controller/delBoard.jsp?num=${pageNum}`;
	}
}