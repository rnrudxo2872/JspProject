    let init = () => {
            var params = `lat=${lat}&lon=${long}`; //쿼리, 데이터 넘긴다
 
            $.ajax({
                type:"POST",                //전송방식
                url:"weatherPage.jsp",    //주소
                data:params,            //전송데이터
                dataType:"xml", // 받을 때 데이터 타입
                success:function(args){ // 이 xml 형태의 데이터를 args로 받음 (바깥으로부터 들어옴)
                                                            // xml 형태니깐 parsing 작업을 해서 받아야함
                    $(args).find("h1").each(function(){    //status 해당 태그 검색. eaxh는 반복문
 
                        alert($(this).text()); // this의 text 형태로 출력해라 
 
                    });
                },
 
                error:function(e){
                    // 에러가 나면 e: 에러메서지가 여기 들어와 있을것임    
                    alert(e.responseText); // error msg는 String이기 때문에 responseText
                    //xml을 받을때는 e.responseXml
                }
            });
    }
 
   
    
    