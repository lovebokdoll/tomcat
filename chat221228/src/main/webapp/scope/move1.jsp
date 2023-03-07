<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.book.scope.Sonata"%>
<%
Sonata             myCar   = ( Sonata ) request.getAttribute( "myCar" );
Sonata             herCar  = ( Sonata ) request.getAttribute( "herCar" );
Sonata             yourCar = ( Sonata ) session.getAttribute( "yourCar" );

String             ohMyCar   = request.getParameter( "ohMyCar" );
String             ohHerCar  = request.getParameter( "ohHerCar" );
String             ohYourCar = request.getParameter( "ohYourCar" );
out.print( "scope1.jsp에서 생성된 객체가 유지되나요?" );
out.print( "<hr>" );
out.print( myCar.carName+","+ ohMyCar.carName+","+ ohMyCar.concat("1")+"자동차".concat("1"));
out.print( "<hr>" );
out.print( myCar.carName+","+ ohHerCar.carName +","+ ohMyCar.indexOf( 3 )+"소나타".concat("1"));
out.print( "<hr>" );
out.print( myCar.carName+","+ ohYourCar.carName +","+ ohMyCar.charAt( 2 )+true.concat("1")+new Boolean(true).toString());
out.print( "<hr>" );
%>