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
	서울교통공사 승하차 순위 조건별 조회
</h1>

일평균 인원이 가장 많은 상위 10개의 역 명과 인원수 
<input type="button" value="조회" name="btn1"><br><br>

월 인원수 평균이 가장 낮은 역 명과 인원수 
<input type="button" value="조회" name="btn2"><br><br>

월 인원수 최대 최소의 차이가 가장 큰 역 명 
<input type="button" value="조회" name="btn3"><br><br>



< 조 회 결 과 ><br>
<ul class="result"></ul>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>

	$(document).ready(function () {
		$('[name="btn1"]').on('click', function() {
			$.ajax({
				type: "get",
				url: "/selectTopTenByDayAvg",
				success: function (response) {
                    var str = '';
					$.each(response, function (indexInArray, valueOfElement) { 
						str += '<li>'+ valueOfElement.STATION_NM  +'('+ valueOfElement.TOT_CNT +')</li>';
					});
					// $('#result').html(str);
					$('.result').html(str);
				}
			});
		})

		$('[name="btn2"]').on('click', function() {
			$.ajax({
				type: "get",
				url: "/selectLowestByMonAvg",
				success: function (response) {
					var str = '<li>'+ response.STATION_NM  +'('+ response.AVG_CNT +')</li>';
					$('.result').html(str);
				}
			});
		})

		$('[name="btn3"]').on('click', function() {
			$.ajax({
				type: "get",
				url: "/selectDiffByMonNum",
				success: function (response) {
					var str = '<li>'+ response.STATION_NM  +'('+ response.MAX_SUB_CNT +')</li>';
					$('.result').html(str);
				}
			});
		})

	});
</script>

</body>
</html>
