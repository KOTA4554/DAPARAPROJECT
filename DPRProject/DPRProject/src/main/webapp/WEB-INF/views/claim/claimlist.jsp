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
    width: 1100px;
    margin-left: auto;
    margin-right: auto;
    margin-top: 30px;
    margin-bottom: 30px;
}
.pageBarSection {
	display:flex;
    justify-content: center;
}
.mainSectionForm > table {
 	width: 100%;
}
.sectionTitles {
	padding: 10px 20px;
	border-bottom: 1px solid lightgray;
    background: linear-gradient( 45deg, #D10024 60%, rgb(255, 99, 99) );
    color: white;
    font-size: 18px;
}
tr>th:not(.sectionTitles, .optionTh){
	text-align: center;
	padding: 15px 20px;
}
table input[type="text"], select, input[type="number"] {
	height: 30px;
	border-radius: 0px;
	border: 1px solid #E4E7ED;
	padding: 0px 5px;	
}
#searchProdName {
	width: 500px;
}
#searchProdBrand {
	width: 500px;
}
.searchBtnSection {
	border-top: 1px solid lightgray;
	margin: 20px 0px;
}
#searchBtn {
	float: right;
	margin: 20px 0px;
	padding: 8px 18px;
	border-radius: 0px;
	border: 1px solid lightgray;
}
.datepicker {
	text-align: center;
}
#categorySelects {
	display: flex;
	justify-content: space-between;
}
.cateSelector {
	width: 130px;
}
#searchProdState, #searchProdNo {
	width: 100%;
}
#prodListTable td {
	padding: 10px 10px;
}
#prodListTable {
	font-size: 12px;
}
#prodListTitle {
	font-size: 13px;
	text-align: center;
	font-weight: bold;
}
#prodListTitle > td {
	padding: 10px;
}
.explainTitles {
	font-size: 13px;
	font-weight: 500;
}
li {
    display: flex;
    justify-content: space-between;
    padding: 5px 0px;
}
.prodListRows {
	border-bottom: 1px dotted lightgray;
	margin-bottom: 5px;
}
.prodRowPno {
	text-align:center;
	width: 85px;
}
.prodRowImg { width: 90px; }
.prodRowBrand {	width: 160px; }
.prodRowName { width: 275px; }
.prodRowPrice { width: 100px; text-align: center; }
.prodRowStart, .prodRowEnd { width: 100px; text-align:center; }
.prodRowOptionCnt { width: 70px; text-align:center; }
.prodRowCateNm {}

</style>

</head>
<body>
<c:import url="../common/header.jsp"/>	
	
<div class="mainSectionForm">
	<table border="0">
		<tr>
			<th colspan="4" class="sectionTitles">검색 조건 설정</th>
		</tr>
	    <tr>
	        <th>상품명</th>
	        <td><input type="text" name="searchNm" id="searchProdName"></td>
	        <th>카테고리</th>
		        <td>
		        <div id="categorySelects">
	                    <select name="searchCate1" id="categoryNo" class="cateSelector">
	                        <option value="999">대분류</option>
	                        <option value="0">의류</option>
	                        <option value="1">슈즈</option>
	                        <option value="2">가방</option>
	                        <option value="3">액세서리</option>
	                        <option value="4">주얼리</option>
	                    </select>
	                    <select name="searchCate2" id="categoryNo2" class="cateSelector">
	                    	<option value="999">소분류</option>
	                        <option value="1">탑</option>
	                        <option value="2">니트웨어</option>
	                        <option value="3">셔츠/자켓</option>
	                        <option value="4">셔츠</option>
	                        <option value="5">수트</option>
	                        <option value="6">팬츠</option>
	                        <option value="7">언더웨어</option>
	                        <option value="8">비치웨어</option>
	                        <option value="9">기능성의류</option>
	                    </select>
	                </div>
		        </td>
	    </tr>
	    <tr>
	        <th>판매기간</th>
	        <td><input type="text" class="datepicker" name="startDate" id="startDate" placeholder="판매 시작일">
	        	<span style="display: inline-block; margin: 0px 25px;"> ~ </span> 
	        	<input type="text" class="datepicker" name="endDate" id="endDate" placeholder="판매 종료일"></td>
	        <th>클레임 상태</th>
	        <td>
	            <select name="saleState" id="searchProdState">
	            	<option value="6">선택</option>
	                <option value="0">취소</option>
	                <option value="1">환불</option>
	                <option value="2">교환</option>
	            </select>
	        </td>
	    </tr>
	    <tr>
	        <th>브랜드</th>
	        <td><input type="text" name="searchBrand" id="searchProdBrand"></td>
	        <th>상품번호</th>
	        <td>
	        	<input type="text" name="searchPno" id="searchProdNo" />
	        </td>
	    </tr>
	</table>
	<div class="searchBtnSection">
		<button id="searchBtn" onclick="searchProduct();">검색</button>
	</div>
	<table id="prodListTable" border="0">
		<tr>
			<th colspan="9" class="sectionTitles">상품 리스트
			<div class="explainTitles">현재 조회 상품 수 : 총 ${totalProduct}개</div></th>
		</tr>
	    <tr id="prodListTitle">
	        <td>상품번호</td>
	        <td>주문상세 번호</td>
	        <td>카테고리</td>
	        <td>브랜드</td>
	        <td>상품명</td>
	        <td>클레임 내용</td>
	        <td>클레임 상태</td>
	        <td>클레임 완료 날짜</td>
	    </tr>
	    <c:forEach items="${list}" var="prod">
	    <tr class="prodListRows" id="${prod.productNo}">
	    	<td class="orderNo" id="${prod.orderNo}"
			<td class="prodRowCateNm">${prod.categoryName}</td>
			<td class="prodRowBrand">${prod.productBrand}</td>
			<td class="prodRowName">${prod.productName}</td>
			<td class="prodRowPrice">${prod.claimContent }</td>
			<td class="prodRowStart">${prod.claimCode}</td>
			<td class="prodRowEnd">${prod.completeClaim}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="pageBarSection">
		<c:out value="${pageBar}" escapeXml="false"/>
	</div>
</div>

<script>
	function searchProduct(){
		location.href="${pageContext.request.contextPath}/seller/searchProd.do";
	}

	//datepicker 사용
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
	
	    $('img.ui-datepicker-trigger').attr('style', 'padding: 7px;');
	});
</script>

<c:import url="../common/footer.jsp"/>
</body>
</html>