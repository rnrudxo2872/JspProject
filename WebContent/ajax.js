    $(function(){

            var params = "subject=" + $("#subject").val() + 
                "&content=" + $("#content").val(); //쿼리임 데이터만 넘길 것!
 
            $.ajax({
                type:"POST",                //전송방식
                url:"test2_ok.jsp",    //주소
                data:params,            //전송데이터
                dataType:"xml", // 받을 때 데이터 타입
                success:function(args){ // 이 xml 형태의 데이터를 args로 받음 (바깥으로부터 들어옴)
                                                            // xml 형태니깐 parsing 작업을 해서 받아야함
                    $(args).find("status").each(function(){    //status 해당 태그 검색. eaxh는 반복문
 
                        alert($(this).text()); // this의 text 형태로 출력해라 
 
                    });
 
                    var str = "";
                    $(args).find("record").each(function(){    // each : 반복문 => record를 다 찾아내라
 
                        var id = $(this).attr("id"); // attribute 넣어라
                        var subject = $(this).find("subject").text();
                        var content = $(this).find("content").text();
 
                        str += "id=" + id +
                            ", subject=" + subject +
                            ", content=" + content + "<br/>";
                    });
 
                    // 반복문으로 만들어낸 데이터를 html로 바꿔서 str을 출력해라
                    $("#resultDiv").html(str); 
                    // javascript 방식에서 out.innerHTML = data; 이거랑 같은 코딩
 
 
                },
 
                beforeSend:showRequest,
                // 보내기 전에!  showRequest가서 검사 (showRequest는 사용자정의)
                //beforeSend는 true값을 받아야만, 위 ajax부분을 통해 서버로 데이터를 보냄
                error:function(e){
                    // 에러가 나면 e: 에러메서지가 여기 들어와 있을것임    
                    alert(e.responseText); // error msg는 String이기 때문에 responseText
                    //xml을 받을때는 e.responseXml
                }
            });
    });
 
    function showRequest(){ 
 
        var flag = true; //무조건 일단 true
 
        if(!$("#subject").val()) { //jQuery에서 가져오는 값이 없으면,
 
            alert("제목을 입력하세요!");
            $("#subject").focus();
            return false;
 
        }
 
        if(!$("#content").val()) { 
 
            alert("내용을 입력하세요!");
            $("#content").focus();
            return false;
 
        }
 
        // subject, content 둘 다 값이 넣어져 있으면
        return flag;
 
    }