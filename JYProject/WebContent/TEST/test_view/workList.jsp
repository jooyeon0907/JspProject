<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel ="stylesheet" 
	href = "https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	
<style>
.flex-container {
  display: flex;
  flex-wrap: wrap;
  background-color: DodgerBlue;
}

.flex-container > div {
  background-color: #f1f1f1;
  width: 100px;
  margin: 10px;
  text-align: center;
  line-height: 75px;
  font-size: 30px;
}
</style>
</head>
<body>
<h3>The flex-wrap Property</h3>
<form name="flexForm">
  <label><input type="radio" name="fDTypes" value="wrap" checked/>wrap</label>
  <label><input type="radio" name="fDTypes" value="nowrap"/>nowrap</label>
  <label><input type="radio" name="fDTypes" value="wrap-reverse"/>wrap-reverse</label>
</form>

<div class="flex-container">
  <div>1</div>
  <div>2</div>
  <div>3</div>  
  <div>4</div>
  <div>5</div>
  <div>6</div>  
  <div>7</div>
  <div>8</div>
  <div>9</div>  
  <div>10</div>
  <div>11</div>
  <div>12</div>  
</div>

<p>Try resizing the browser window.</p>

<script>
  var rad = document.flexForm.fDTypes;
  var fContainer = document.getElementsByClassName('flex-container');
  var prev = null;
    for(var i = 0; i < rad.length; i++) {
      rad[i].addEventListener('change', function() {
        fContainer.item(0).style.flexWrap = this.value;
      });
    }
</script>



</body>
</html>







