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

<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>


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
.mainSectionForm table {
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
	height: 60px;
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
	<form action="${pageContext.request.contextPath}/seller/searchProd.do" method="get">
		<table border="0">
			<tr>
				<th colspan="4" class="sectionTitles">?????? ?????? ??????</th>
			</tr>
		    <tr>
		        <th>?????????</th>
		        <td><input type="text" name="searchNm" id="searchProdName"></td>
		        <th>????????????</th>
			        <td>
			        <div id="categorySelects">
		                    <select name="searchCate1" id="categoryNo" class="cateSelector">
		                        <option value="999">?????????</option>
		                        <option value="0">??????</option>
		                        <option value="1">??????</option>
		                        <option value="2">??????</option>
		                        <option value="3">????????????</option>
		                        <option value="4">?????????</option>
		                    </select>
		                    <select name="searchCate2" id="categoryNo2" class="cateSelector">
		                    	<option value="999">?????????</option>
		                        <option value="1">???</option>
		                        <option value="2">????????????</option>
		                        <option value="3">??????/??????</option>
		                        <option value="4">??????</option>
		                        <option value="5">??????</option>
		                        <option value="6">??????</option>
		                        <option value="7">????????????</option>
		                        <option value="8">????????????</option>
		                        <option value="9">???????????????</option>
		                    </select>
		                </div>
			        </td>
		    </tr>
		    <tr>
		        <th>????????????</th>
		        <td><input type="text" class="datepicker" name="startDate" id="startDate" placeholder="?????? ?????????">
		        	<span style="display: inline-block; margin: 0px 25px;"> ~ </span> 
		        	<input type="text" class="datepicker" name="endDate" id="endDate" placeholder="?????? ?????????"></td>
		        <th>????????????</th>
		        <td>
		            <select name="saleState" id="searchProdState">
		            	<option value="999">??????</option>
		                <option value="1">????????????</option>
		                <option value="2">?????????</option>
		                <option value="3">????????????</option>
		            </select>
		        </td>
		    </tr>
		    <tr>
		        <th>?????????</th>
		        <td><input type="text" name="searchBrand" id="searchProdBrand"></td>
		        <th>????????????</th>
		        <td>
		        	<input type="text" name="searchPno" id="searchProdNo" />
		        </td>
		    </tr>
		</table>
		<div class="searchBtnSection">
			<button id="searchBtn">??????</button>
		</div>
	</form>
	
	<div class="charArea">
		<!--????????? ????????? ??????-->
		<div>
			<canvas id="myChart" width="1100" height="500"></canvas>	
		</div>
		
		<div>
			<canvas id="productSaleRate" width="1100" height="400"></canvas>	
		</div>
		
		<div>
			<canvas id="productStar" width="300" height="300"></canvas>	
		</div>
		
		<div>
			<canvas id="genderRate" width="300" height="300"></canvas>	
		</div>
		
	</div>
	
</div>

<script>

	//datepicker ??????
	$(function() {
	   //input??? datepicker??? ??????
	    $('#startDate, #endDate').datepicker({
	        dateFormat: 'yy-mm-dd'
	        ,showOtherMonths: true
	        ,showMonthAfterYear:true
	        ,changeYear: true
	        ,changeMonth: true             
	        ,showOn: "both"
	        ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif"
	        ,buttonImageOnly: true
	        ,buttonText: "??????"             
	        ,yearSuffix: "???"
	        ,monthNamesShort: ['1???','2???','3???','4???','5???','6???','7???','8???','9???','10???','11???','12???']
	        ,monthNames: ['1???','2???','3???','4???','5???','6???','7???','8???','9???','10???','11???','12???']
	        ,dayNamesMin: ['???','???','???','???','???','???','???']
	        ,dayNames: ['?????????','?????????','?????????','?????????','?????????','?????????','?????????']
	        ,minDate: "today"
	    });                    
	
	    $('img.ui-datepicker-trigger').attr('style', 'padding: 7px;');
	});
	
	// Chart.js
	
	// monthly sales revenue
	var config = {
        type: 'line',
        data: {
        labels: ['1???','2???','3???','4???','5???','6???','7???','8???','9???','10???','11???'],
        datasets: [{
            label: '??? ??? ?????????(??????)',
            data: [700, 600, 900, 1200, 700, 800, 1700, 2200, 1800, 1500, 2000],
            backgroundColor: [
                'rgba(13, 126, 206, 0.2)',

            ],
            borderColor: [
                'rgba(13, 126, 206, 1)',

            ],
            pointBackgroundColor: [
                'rgba(13, 126, 206, 1)'
            ],
            borderWidth: 2,
            tension: 0.25
        	}]
    	},
        options: {}
   	};


    var myChart = new Chart(
      document.getElementById('myChart'),
      config
    );
	
 	// product SaleRate
	var config1 = {
        type: 'line',
        data: {
        labels: ['???????????????', '???????????????','??????????????????','??????????????????','???????????????','??????????????????','?????????????????????'],
        datasets: [{
            label: '?????? ??? ??? ?????????(??????)',
            data: [100, 470, 300, 430, 700, 210, 45],
            backgroundColor: [
                'rgba(255, 135, 36, 0.8)',

            ],
            borderColor: [
                'rgba(255, 135, 36, 1)',

            ],
            borderWidth: 2,
            tension: 0.25
        	}, {
            label: '?????? ??? ??? ?????????',
            data: [30, 45, 100, 20, 10, 23, 75],
            backgroundColor: [
                'rgba(28, 197, 79, 0.8)',

            ],
            borderColor: [
                'rgba(28, 197, 79, 1)',

            ],
            type: 'bar',
            borderWidth: 2,
            tension: 0.25
        	}]
    	},
        options: {}
   	};


    var productSaleRate = new Chart(
      document.getElementById('productSaleRate'),
      config1
    );
    
	
</script>

<c:import url="../common/footer.jsp"/>
</body>
</html>