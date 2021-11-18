<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.mainSectionForm {
    width: 800px;
    margin-left: auto;
    margin-right: auto;
    margin-top: 30px;
    margin-bottom: 30px;
}
.sectionTitles {
	padding: 10px 20px;
	border-bottom: 1px solid lightgray;
    background: linear-gradient( 45deg, #D10024 60%, rgb(255, 99, 99) );
    color: white;
    font-size: 18px;
}
tr>th:not(.sectionTitles) {
	text-align: center;
	padding: 30px 10px;
}
select {
	padding: 3px;
	padding-left: 15px;
	width: 150px;
}
.defaultTime {
	height: 30px;
	width: 100px;
}
table input[type="text"], select {
	height: 30px;
	border-radius: 0px;
	border: 1px solid #E4E7ED;	
}
#productName {
	width: 500px;
}
.productMainImg, .productAdditionalImg, .productContentImg {
	height: 200px;
	border: 1px solid lightgray;
	font-size: 50px;
	display: flex;
	align-items: center;
    justify-content: center;
    color: lightgray;
    margin: 5px;
}
.productAdditionalImg {
	height: 120px;
	width: 150px;
}
#additionalImgSection {
	display: flex;
	justify-content: space-between;
}
.dateTitles, .defaultTime {
	display: inline-block;
	margin: 0px 20px;
	text-align: center;
}
</style>
</head>
<body>
<c:import url="../common/header.jsp"/>

<div class="mainSectionForm">
<h3>상품 등록</h3>
<form action="">
    <!-- sellerId 정보 hidden 으로 담아주기 -->
    <table border="0" id="mainTable">
        <th colspan="2" class="sectionTitles">상품 기본 정보</th>
        <tr>
            <th style="width: 150px;">카테고리</th>
            <td>
                <div id="categorySelects">
                    <select name="categroyNo" id="categroyNo">
                        <option value="">대분류</option>
                        <option value="">의류</option>
                        <option value="">슈즈</option>
                        <option value="">가방</option>
                        <option value="">액세서리</option>
                        <option value="">주얼리</option>
                    </select>
                    <select name="categroyNo2" id="categroyNo2">
                    	<option value="">소분류</option>
                        <option value="">탑</option>
                        <option value="">니트웨어</option>
                        <option value="">셔츠/자켓</option>
                        <option value="">셔츠</option>
                        <option value="">수트</option>
                        <option value="">팬츠</option>
                        <option value="">언더웨어</option>
                        <option value="">비치웨어</option>
                        <option value="">기능성의류</option>
                    </select>
                </div>
            </td>
        </tr>
        <tr>
            <th>상품명</th>
            <td>
                <input type="text" name="productName" id="productName" placeholder="상품명을 입력하세요.">                
            </td>
        </tr>
        <tr>
            <th>판매기간</th>
            <td>
                <div class="datePickerDiv">
                	<span class="dateTitles">시작일시</span>
                	<input type="text" name="" id="startDate"> <input type="text" value="00:00:00" class="defaultTime" disabled> ~ 
                </div>
                <div class="datePickerDiv">
                	<span class="dateTitles">종료일시</span>
                	<input type="text" name="" id="endDate">
                	<input type="text" value="23:59:59" class="defaultTime" disabled>
                </div>
            </td>
        </tr>

        <th colspan="2" class="sectionTitles">상품 옵션 정보</th>
        <tr>
            <th>옵션생성</th>
            <td>
                <table border="1" id="optionTable" style="width: 100%;">
                    <tr>
                        <td colspan="6">옵션을 등록해주세요. <button type="button" id="btn" onclick="addOptionRow();" value="val확인">옵션 추가</button><button>선택 삭제</button></td>
                    </tr>
                    <tr>
                        <th>선택</th>
                        <th>NO.</th>
                        <th>상품명</th>
                        <th>옵션명</th>
                        <th>판매가격</th>
                        <th>판매수량</th>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="" id=""></td>
                        <td>자동 부여</td>
                        <td>상단의 상품명 가져오기</td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </table> 
            </td>
        </tr>
        <th colspan="2" class="sectionTitles">컨텐츠 정보</th>
        <tr>
            <th rowspan="3">이미지 등록</th>
            	<td>상품 이미지 등록
	            	<div class="productMainImg">
	            		+
	            	</div>
	            	<input type="file" name="" id="" />
            	</td>
	    <tr>
	    	<td id="additionalImgSection" style="border-top: 1px dotted lightgray;">옵션 이미지 등록
	            <div class="productAdditionalImg">+</div>
	            <div class="productAdditionalImg">+</div>
	            <div class="productAdditionalImg">+</div>
	        </td>
	    </tr>
        </tr>
        <tr>
        	<td id="contentImgSection" style="border-top: 1px dotted lightgray;">컨텐츠 이미지 등록
        		<div class="productContentImg">+</div>
        	</td>
        </tr>
        <tr>
            <th>텍스트 등록</th>
            <td><textarea name="" id="" cols="30" rows="10"></textarea></td>
        </tr>
    </table>
    <button>등록</button>
    <button>취소</button>
</form>
</div>

<c:import url="../common/footer.jsp"/>
<script>
	// datepicker 사용
    $(function() {
       //input을 datepicker로 선언
        $('#startDate, #endDate').datepicker({
            dateFormat: 'yy-mm-dd'
            ,showOtherMonths: true
            ,showMonthAfterYear:true
            ,changeYear: true
            ,changeMonth: true             
            ,showOn: "both"
            ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif"
            ,buttonImageOnly: true
            ,buttonText: "선택"             
            ,yearSuffix: "년"
            ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
            ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
            ,dayNamesMin: ['일','월','화','수','목','금','토']
            ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일']
            ,minDate: "today"
        });                    
       
       //초기값을 오늘 날짜로 설정해줘야 합니다.
        $('#startDate').datepicker('setDate', '+1D'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
        $('img.ui-datepicker-trigger').attr('style', 'padding: 7px;');
    });
	
	function addOptionRow(){
		console.log('클릭 확인');
		console.log($(this));
		var options = $(this).next();
		console.log(options.css({'border': 'none'}));
		$('.productMainImg').text("");
	}
	
</script>

</body>
</html>