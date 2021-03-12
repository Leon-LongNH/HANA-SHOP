<%-- 
    Document   : admin
    Created on : Jan 13, 2021, 4:09:31 PM
    Author     : LongNH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>HANA | SHOP</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">

    </head>
    <body>
    <session-config>
        <tracking-mode></tracking-mode>
    </session-config>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Humberger Begin -->
    <div class="humberger__menu__overlay"></div>
    <div class="humberger__menu__wrapper">
        <div class="humberger__menu__logo">
            <a href="#"><img src="img/logo.jpg" alt=""></a>
        </div>
        <div class="humberger__menu__cart">
            <ul>
                <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
            </ul>
            <div class="header__cart__price">item: <span>$150.00</span></div>
        </div>
        <div class="humberger__menu__widget">
            <div class="header__top__right__language">
                <img src="img/language.png" alt="">
                <div>English</div>
                <span class="arrow_carrot-down"></span>
                <ul>
                    <li><a href="#">Spanis</a></li>
                    <li><a href="#">English</a></li>
                </ul>
            </div>
            <div class="header__top__right__auth">
                <a href="#"><i class="fa fa-user"></i> Login</a>
            </div>
        </div>
        <nav class="humberger__menu__nav mobile-menu">
            <ul>
                <li class="active"><a href="./welcome-page.jsp">Home</a></li>
                <li><a href="./shop-grid.html">Shop</a></li>
                <li><a href="#">Pages</a>
                    <ul class="header__menu__dropdown">
                        <li><a href="./shop-details.html">Shop Details</a></li>
                        <li><a href="./shoping-cart.html">Shoping Cart</a></li>
                        <li><a href="./checkout.html">Check Out</a></li>
                        <li><a href="./blog-details.html">Blog Details</a></li>
                    </ul>
                </li>
                <li><a href="./blog.html">Blog</a></li>
                <li><a href="./contact.html">Contact</a></li>
            </ul>
        </nav>
        <div id="mobile-menu-wrap"></div>
        <div class="header__top__right__social">
            <a href="#"><i class="fa fa-facebook"></i></a>
        </div>
        <div class="humberger__menu__contact">
            <ul>
                <li><i class="fa fa-envelope"></i> ng.hoang.long10320@gmail.com</li>
                <li>Free Shipping for all Order</li>
            </ul>
        </div>
    </div>
    <!-- Humberger End -->

    <!-- Header Section Begin -->
    <header class="header">
        <div class="header__top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <div class="header__top__left">
                            <ul>
                                <li><i class="fa fa-envelope"></i> ng.hoang.long10320@gmail.com</li>
                                <li>Free Shipping for all Order</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <div class="header__top__right">
                            <div class="header__top__right__social">
                                <a href="https://www.facebook.com/"><i class="fa fa-facebook"></i></a>
                            </div>
                            <div class="header__top__right__language">
                                <img src="img/language.png" alt="">
                                <div>English</div>
                                <span class="arrow_carrot-down"></span>
                                <ul>
                                    <li><a href="#">Spanis</a></li>
                                    <li><a href="#">English</a></li>
                                </ul>
                            </div>
                            <div class="header__top__right__auth">
                                <c:set var="account" value="${sessionScope.ACCOUNT}" />
                                <c:if test="${account == null}" >
                                    <a href="login.html"><i class="fa fa-user"></i> Login</a>
                                </c:if>
                                <c:if test="${account != null}" >
                                    <a href="DispatchController?btAction=LogOut"><i class="fa fa-sign-out"></i>Welcome,${account.lastname}</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- Header Section End -->
    
    <form action="DispatchController">
        <button type="submit" class="btn btn-primary btn-lg btn-block" name="btAction" value="CreateNewFood" style="margin: 20px; width: 95%;">Create new Food</button>
    </form>
    <!-- Hero Section Begin -->
    
    <section class="hero">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="hero__categories">
                        <div class="hero__categories__all">
                            <i class="fa fa-bars"></i>
                            <span>All category</span>
                        </div>
                        <ul>
                            <c:set var="listCategory" value="${requestScope.ALL_CATEGORY}" />
                            <c:if test="${listCategory != null}" >
                                <c:forEach var="Category" items="${listCategory}" >
                                    <li>
                                        <a href="DispatchController?KeyCategory=${Category.categoryName}&btAction=ChooseCategoryAdmin">${Category.categoryName}</a>
                                    </li>
                                </c:forEach>
                            </c:if>
                            <li><a href="#">Fresh Meat</a></li>
                            <li><a href="#">Vegetables</a></li>
                            <li><a href="#">Fruit & Nut Gifts</a></li>
                            <li><a href="#">Fresh Berries</a></li>
                            <li><a href="#">Ocean Foods</a></li>
                            <li><a href="#">Butter & Eggs</a></li>
                            <li><a href="#">Fastfood</a></li>
                            <li><a href="#">Fresh Onion</a></li>
                            <li><a href="#">Papayaya & Crisps</a></li>
                            <li><a href="#">Oatmeal</a></li>
                            <li><a href="#">Fresh Bananas</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                            <form action="DispatchController">
                                <c:set var="searchKey" value="${requestScope.SEARCH_KEY}" />
                                <c:if test="${searchKey == null}" >
                                    <c:set var="searchKey" value="" />
                                </c:if>
                                <input type="text" placeholder="What do you need?" value="${searchKey}" name="SearchKey">
                                <button type="submit" class="site-btn" name="btAction" value="SearchFoodAdmin">SEARCH</button>
                            </form>
                        </div>
                        <div class="hero__search__phone">
                            <div class="hero__search__phone__icon">
                                <i class="fa fa-phone"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->

    <!-- Featured Section Begin -->
    <section class="featured spad">
        <div class="container">
            <div class="row featured__filter">
                <c:set var="listFoods" value="${requestScope.ALL_FOOD}" />
                <c:if test="${listFoods != null}" >
                    <c:forEach var="Food" items="${listFoods}" >
                        <div class="col-lg-3 col-md-4 col-sm-6">
                            <div class="featured__item">
                                <c:set var="foodImg" value="img/${Food.foodImage}" />
                                <div class="featured__item__pic set-bg" data-setbg="${foodImg}">
                                    <ul class="featured__item__pic__hover">
                                        <li><a href="DispatchController?btAction=UpdateFood&foodID=${Food.foodID}"><i class="fa fa-cog"></i></a></li>
                                        <li><a href="DispatchController?btAction=DeleteFood&foodID=${Food.foodID}" onclick="{
                                                    return confirm('Are you sure you want to continue');
                                                }"><i class="fa fa-trash-o"></i></a></li>
                                    </ul>
                                </div>
                                <div class="featured__item__text">
                                    <h6><a href="#">${Food.foodName}</a></h6>
                                    <h5>$${Food.foodPrice}</h5>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <c:forEach begin="1" end="${requestScope.END_PAGE}" var="index">
                        <c:set var="keyCategory" value="${requestScope.KEY_CATEGORY}" />
                        <c:if test="${keyCategory == null and empty searchKey}" >
                            <c:url var="urlRewrite" value="DispatchController?index=${index}&btAction=LoadAdminPage" >
                            </c:url>
                        </c:if>
                        <c:if test="${not empty searchKey and keyCategory == null}" >
                            <c:url var="urlRewrite" value="DispatchController" >
                                <c:param name="btAction" value="SearchFoodAdmin" />
                                <c:param name="SearchKey" value="${searchKey}" />
                                <c:param name="index" value="${index}" />
                            </c:url>
                        </c:if>
                        <c:if test="${empty searchKey and keyCategory != null}" >
                            <c:url var="urlRewrite" value="DispatchController" >
                                <c:param name="btAction" value="ChooseCategoryAdmin" />
                                <c:param name="KeyCategory" value="${keyCategory}" />
                                <c:param name="index" value="${index}" />
                            </c:url>
                        </c:if>
                        <li class="page-item"><a class="page-link" href="${urlRewrite}">${index}</a></li>
                        </c:forEach>
                </ul>
            </nav>
        </div>
    </section>
    <!-- Featured Section End -->

    <!-- Js Plugins -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>



</body>
</html>
