<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%String context = request.getContextPath(); %>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<div class="weatherDisplay">
	<div>
		<span class="weatherDesc"></span> <span class="Icon"></span>
	</div>
	<div class="place"></div>
	<div class="temp"></div>
	<div class="display__main" style="display: none;"></div>
</div>
<script src="<%=context %>/js/getCoord.js"></script>
<script src="<%=context %>/js/weatherParse.js"></script>