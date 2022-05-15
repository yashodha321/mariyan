<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
 
 
<%@page import="com.Supply"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="views/bootstrap.min.css">
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/supply.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Supply Management </h1>
<form id="formsupply" name="formsupply">
 supply id:
 <input id="supplyid" name="supplyid" type="text"
 class="form-control form-control-sm">
 <br> supply name:
 <input id="supplyName" name="supplyName" type="text"
 class="form-control form-control-sm">
 <br> quntity:
 <input id="supplyquntity" name="supplyqunitity" type="text"
 class="form-control form-control-sm">
 <br>  price:
 <input id="supplyprice" name="supplyprice" type="text"
 class="form-control form-control-sm">
 <br> price :
 <input id="total" name="total" type="text"
 class="form-control form-control-sm">
 <br> total :
 <input id="date" name="date" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidSupplyIDSave"
 name="hidSupplyIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divSupplyGrid">
 <%
 Supply supplyObj = new Supply();
 out.print(supplyObj.readPayments());
 %>
</div>
</div> </div> </div>
</body>
</html>