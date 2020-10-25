<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	서울교통공사 승하차 순위
</h1>

로그인하세요. 인가된 사용자만 조회가 가능합니다.<br/><br/>

USER ID : <input type="text" id="userId" value="" /><br/>
USER PW : <input type="password" id="userPwd" value="" /><br/>
<input type="button" value="로그인" name="btn1">

<ul class="result"></ul>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>

	$(document).ready(function () {
		$('[name="btn1"]').on('click', function() {
		    var userId = $('#userId').val();
		    var userPwd = $('#userPwd').val();
			$.ajax({
				type: "post",
				url: "/login/authProcess.do",
				data: {'userId':userId, 'userPwd':userPwd},
				success: function (response) {
                    if(response.login == 'ok'){
                        location.href ="/insData.do";
                    }
                    else{
                        alert("로그인 정보를 다시 확인하여 입력해주세요.");
                        location.href ="/login/auth.do";
                    }
				}
			});
		})

	});
</script>

</body>
</html>
