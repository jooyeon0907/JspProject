<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ include file = "/inc/work_header.jsp" %> --%>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->


   
  <div class="dropdown">
    <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">멤버목록
    <span class="caret"></span></button>
    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" id="ws_member">활동 중인 멤버</a></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" id="invi_member">초대 중인 멤버</a></li>
      <li role="presentation" class="divider"></li>
     <!--  <li role="presentation"><a role="menuitem" tabindex="-1" href="#" id="delete_member">삭제된 멤버</a></li> -->
    </ul>
  </div>
  

	<script>
		$(function(){
			$("#ws_member").click(function(){ alert('활동 중인 멤버');
				
			}); //end click
			$("#invi_member").click(function(){ alert('초대 중인 멤버');
				
			}); //end click
			$("#delete_member").click(function(){ alert('삭제된 멤버');
			
			}); //end click
		}); // end ready
	</script>

	
	
<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
<!-- </body>
</html> -->
