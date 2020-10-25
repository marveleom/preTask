
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	서율교통공사 승하차 순위 - CSV파일 적재
</h1>



<form method="POST" enctype="multipart/form-data" id="excelForm">
	
	<table>
		<tr>
			<td>첨부파일</td>
			<td>
				<input type="file" name="file1" value="">
			</td>
			<td>
				<input type="button" name="btnFile" value="첨부">
			</td>
		</tr>
	</table>
</form>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
	$(document).ready(function () {
		
		$('[name="btnFile"]').on('click', function() {

			var form = $('#excelForm')[0];
			var formData = new FormData(form);

			$.ajax({
				type : "POST",
				url : "/insDataProc.do",
				data : formData,
				processData: false,
				contentType: false,
				success : function(data) {
					if(data.fileUpload == 'ok'){
						alert("파일 적재 성공, 조회화면으로 이동합니다.");
						location.href ="/selectMenu.do";
					}else{
						alert('파일 적재 실패');
					}
					
				},
				err : function(err) {
					alert(err.status);
				}
			});


		});
	});
</script>
</body>
</html>
