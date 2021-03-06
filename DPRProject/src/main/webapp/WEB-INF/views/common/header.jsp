

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
       <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

      <title>main page</title>

      <!-- Google font -->
      <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

      <!-- Bootstrap -->
      <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css"/>

      <!-- Slick -->
      <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/slick.css"/>
      <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/slick-theme.css"/>

      <!-- nouislider -->
      <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/nouislider.min.css"/>

      <!-- Font Awesome Icon -->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>


      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"/>

      <!-- Custom stlylesheet -->
      <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css"/>
      <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/common.css"/>
      
      <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
      
      <!-- jeongho Menubar -->
      <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/jeongho.css"/>
      <script src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
      
      <!-- datepicker ?????? sources -->
      <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


      <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
      <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
      <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->

      
      <!-- jQuery Plugins -->
      <!--  <script src="${pageContext.request.contextPath }/resources/js/jquery.min.js"></script>-->
      <script src="${pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>
      <script src="${pageContext.request.contextPath }/resources/js/slick.min.js"></script>
      <script src="${pageContext.request.contextPath }/resources/js/nouislider.min.js"></script>
      <script src="${pageContext.request.contextPath }/resources/js/jquery.zoom.min.js"></script>
      <script src="${pageContext.request.contextPath }/resources/js/main.js"></script>
      


</head>

      <!-- HEADER -->
      <header>
         <!-- TOP HEADER -->
         <div id="top-header">
            <div class="container">
               <ul class="header-links pull-right">


               <c:if test="${empty seller && empty member}">
                  <li><a href="${pageContext.request.contextPath}/member/memberLoginView.do"><i class="fas fa-key"></i> ?????????</a></li>
                  <li><a href="${pageContext.request.contextPath}/member/memberSignUp.do"><i class="fa fa-user-o"></i> ????????????</a></li>
               </c:if>
                  <c:if test="${!empty seller}">
                     <li><i class="fa fa-user-o"></i><a>(seller) ${seller.sellerId} ??? ???????????????.</a></li>
                     <li><i class="fas fa-sign-out-alt"></i><a href="${pageContext.request.contextPath}/seller/logout.do">????????????</a></li>
                  </c:if>
                  <c:if test="${!empty member}">
                     <li><i class="fa fa-user-o"></i><a>${member.userId} ??? ???????????????.</a></li>
                     <li><i class="fas fa-sign-out-alt"></i><a href="${pageContext.request.contextPath}/member/logout.do">????????????</a></li>
                  </c:if>

               </ul>
            </div>
         </div>
         <!-- /TOP HEADER -->


         <!-- MAIN HEADER -->

         <c:if test="${empty seller}">


         <div id="header">
            <!-- container -->
            <div class="container">
               <!-- row -->
               <div class="row">
                  <!-- LOGO -->
                  <div class="col-md-3">
                     <div class="header-logo">
                        <a href="#" class="logo">


                           <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="">


                        </a>
                     </div>
                  </div>
                  <!-- /LOGO -->

                  <!-- SEARCH BAR -->
                  <div class="col-md-6">
                     <div class="header-search">
                        <form>
                           <input class="input" id= sresult2 type="text" placeholder="???????????? ???????????????." name="sresult">
                           <button type="button" onclick="search2();" class="search-btn" >Search</button>
                           <script>
                           function search2(){
                              var a = $("#sresult2").val();
                              location.href="${pageContext.request.contextPath}/product/search.do?sresult=" + a ;
                           }
                           
                           </script>
                        </form>
                     </div>
                  </div>
                  <!-- /SEARCH BAR -->

                  <!-- ACCOUNT -->
                  <div class="col-md-3 clearfix">
                     <div class="header-ctn">
                        <!-- Wishlist -->
                        <div>


                           <a href="${pageContext.request.contextPath}/myPage/myPage.do">


                              <i class="fas fa-user"></i>
                              <span>My Page</span>
                              <!-- <div class="qty">2</div> -->
                           </a>
                        </div>
                       <!-- /Wishlist -->

                        <!-- Cart -->
                        <div onclick="cart();" class="dropdown">
                           <a href="${pageContext.request.contextPath}/prod/cart.do">
                              <i class="fa fa-shopping-cart"></i>
                              <span>Cart</span>                           
                           </a>   
                                                
                           </div>
                        </div>
                        <!-- /Cart -->

                        <!-- Menu Toogle -->
                     
                        <!-- /Menu Toogle -->
                     </div>
                  </div>
                  <!-- /ACCOUNT -->
               </div>
               <!-- row -->
            </div>
            <!-- container -->
         </div>
         <!-- /MAIN HEADER -->
      </header>
      <!-- /HEADER -->

      <!-- NAVIGATION -->


       <nav id="navigation">
          <!-- container -->
          <div class="container">
             <!-- responsive-nav -->
             <div id="responsive-nav">
                <!-- NAV -->
                
                <div class="menu">
                
               <ul class="main-nav nav navbar-nav">
                  <li class="active"><a href="${pageContext.request.contextPath}/" >BEST</a></li>
                  <li><a href="${pageContext.request.contextPath}/product/clothes.do">??????</a>
                     <ul>
                        <li><a href="${pageContext.request.contextPath}/product/top.do">???</a></li>&nbsp;
                        <li><a href="${pageContext.request.contextPath}/product/knit.do">????????????</a></li>&nbsp;
                        <li><a href="${pageContext.request.contextPath}/product/jacket.do">??????/??????</a></li>&nbsp;
                        <li><a href="${pageContext.request.contextPath}/product/shirt.do">??????</a></li>&nbsp;
                        <li><a href="${pageContext.request.contextPath}/product/suit.do">??????</a></li>&nbsp;
                        <li><a href="${pageContext.request.contextPath}/product/pants.do">??????</a></li>&nbsp;
                        <li><a href="${pageContext.request.contextPath}/product/under.do">????????????</a></li>&nbsp;
                        <li><a href="${pageContext.request.contextPath}/product/beach.do">????????????</a></li>&nbsp;
                        <li><a href="${pageContext.request.contextPath}/product/func.do">???????????????</a></li>&nbsp;
                     </ul>
                  </li>
                  <li><a href="/product/shoes">??????</a></li>
                     
                  <li><a href="${pageContext.request.contextPath}/product/bagpack.do">??????</a></li>
                  <li><a href="${pageContext.request.contextPath}/product/accessories.do">????????????</a></li>
                  <li><a href="${pageContext.request.contextPath}/product/jewellery.do">?????????</a></li>
                  <li><a href="${pageContext.request.contextPath}/product/clothes.do">SALES</a></li>
               </ul>
                </div>
                </div>
                  <!-- /NAV -->
               </div>
               <!-- /responsive-nav -->
            </div>
            <!-- /container -->
         </nav>
         <!-- /NAVIGATION -->
      </c:if>
      
      <c:if test="${!empty seller}">


      <nav id="navigation">
         <!-- container -->
         <div class="container">
            <!-- responsive-nav -->
            <div id="responsive-nav">
               <div class="menu">
               <!-- NAV -->
               <ul class="main-nav nav navbar-nav">


                  <li class="active"><a href="${pageContext.request.contextPath}/seller/sellerMain.do">HOME</a></li>
                  <li><a href="#">????????????</a>
                     <ul>
                        <li><a href="${pageContext.request.contextPath}/seller/addProduct.do">?????? ??????</a></li>
                        <li><a href="${pageContext.request.contextPath}/seller/productList.do">?????? ??????</a></li>
                        <li><a href="${pageContext.request.contextPath}/seller/delivery.do">?????????????? ??????</a></li>
                     </ul>
                  </li>
                  <li><a href="#">???????????????</a>
                     <ul>

                        <li><a href="${pageContext.request.contextPath}/seller/claimList.do">????????? ??????</a></li>

                        <li><a href="${pageContext.request.contextPath}/seller/qnaList.do">?????? ??????</a></li>

                        <li><a href="${pageContext.request.contextPath}/seller/reviewList.do">?????? ??????</a></li>
                     </ul>
                  </li>
                  <li><a href="#">????????????</a>
                     <ul>
                        <li><a href="">?????? ??????</a></li>
                        <li><a href="">?????? ??????</a></li>
                     </ul>
                  </li>
                  <li><a href="${pageContext.request.contextPath}/statistic/statisticPage.do">??????</a>
                  </li>
              <li><a href="${pageContext.request.contextPath}/myPage/sellerInfo.do">???????????? ??????</a></li>

               </ul>
               <!-- /NAV -->
               </div>
            </div>
            <!-- /responsive-nav -->
         </div>
         <!-- /container -->
      </nav>


      </c:if>
      <!-- /NAVIGATION -->
