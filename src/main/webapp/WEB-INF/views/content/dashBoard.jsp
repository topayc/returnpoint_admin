<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">	
	<div class="col-lg-3 col-md-6 col-sm-6">
		<div class="card card-stats nodetitle">     
			<div class="card-top">
				<div class="card-title">
					<i class="fas fa-chart-pie"></i> 포인트 및 가입 현황
				</div>	
			</div>
			<div class="card-redtotal">
			   Red Point Total
				<span class="sumMemberRedPoint">0</span>
			</div>
			<div class="card-footer">         
				<div class="pointunit">포인트 단위 : <label class="sumMemberRedPoint">0</label>P</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6 col-sm-6 node1">
		<div class="card card-stats active"> <!-- 해당 단계의 정보가 활성화될 때 active class가 활성화됨니다. -->
			<div class="card-top">
				<div class="card-title">
					<i class="fas fa-th "></i> 총판
				</div>
				<div class="card-point">
					<p class="card-category"><span class="gpoint">G</span><label id="sumSoleDistGreenPoint">0</label>P</p>
				</div>
			</div>
			<div class="card-footer">         
				<ul class="stats">     
					<li>누적가입 <span><label id="totalSoleDistCount">0</label>건</span></li>
					<li>당일가입 <span><label id="todaySoleDistCount">0</label>건</span></li>
					<li>승인대기 <span><label id="readySoleDistCount">0</label>건</span></li>
					<!-- <li>우수총판 <span>안영철총판</span></li> -->
				</ul>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6 col-sm-6 node2">
		<div class="card card-stats">
			<div class="card-top">
				<div class="card-title">
					<i class="fas fa-th "></i> 지사
				</div>
				<div class="card-point">
						<p class="card-category"><span class="gpoint">G</span><label id="sumBranchGreenPoint">0</label>P</p>
				</div>
			</div>
			<div class="card-footer">
				<ul class="stats">     
					<li>누적가입 <span><label id="totalBranchCount">0</label>건</span></li>
					<li>당일가입 <span><label id="todayBranchCount">0</label>건</span></li>
					<li>승인대기 <span><label id="readyBranchCount">0</label>건</span></li>
					<!-- <li>우수총판 <span>안영철총판</span></li> -->
				</ul>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6 col-sm-6 node3">
		<div class="card card-stats">
			<div class="card-top">
				<div class="card-title">
						<i class="fas fa-store-alt"></i> 대리점
				</div>
				<div class="card-point">
						<p class="card-category numbers"><span class="gpoint">G</span><label id="sumAgenctGreenPoint">0</label>P</p>
				</div>
			</div>
			<div class="card-footer">
				<ul class="stats">
					<li>누적가입 <span><label id="totalAgenctCount">0</label>건</span></li>
					<li>당일가입 <span><label id="todayAgenctCount">0</label>건</span></li>
					<li>승인대기 <span><label id="readyAgenctCount">0</label>건</span></li>
					<!--<li>우수대리점 <span>안영철지사</span></li>-->
				</ul>
				
			</div>
		</div>
	</div>
	
</div>
<div class="row">	
    <div class="col-lg-3 col-md-6 col-sm-6 node4">
		<div class="card card-stats">
			<div class="card-top">
				<div class="card-title">
						<i class="fas fa-store"></i> 협력업체
				</div>
				<div class="card-point">
						<p class="card-category numbers"><span class="gpoint">G</span><label id="sumAffiliateGreenPoint">0</label>P</p>
				</div>
			</div>
			<div class="card-footer">
				<ul class="stats">
					<li>누적가입 <span><label id="totalAffiliateCount">0</label>건</span></li>
					<li>당일가입 <span><label id="todayAffiliateCount">0</label>건</span></li>
					<li>승인대기 <span><label id="readyAffiliateCount">0</label>건</span></li>
					<!--<li>우수가맹점 <span>안영철대리점</span></li>-->
				</ul>
				
			</div>
		</div>
	</div>	
	
	<div class="col-lg-3 col-md-6 col-sm-6 node6">
		<div class="card card-stats">
			<div class="card-top">
				<div class="card-title">	
						<i class="fas fa-user-tie"></i> 영업
				</div>
				<div class="card-point">
						<p class="card-category numbers"><span class="gpoint">G</span><label id="sumSaleManagerGreenPoint">0</label>P</p>
				</div>
			</div>
			<div class="card-footer">
				<ul class="stats">
					<li>누적가입 <span><label id="totalSaleManagerCount">0</label>명</span></li>
					<li>당일가입 <span><label id="todaySaleManagerCount">0</label>명</span></li>
					<li>승인대기 <span><label id="readySaleManagerCount">0</label>명</span></li>
					<!--<li>우수영업인 <span>안영철회원</span></li>-->
				</ul>
			</div>
		</div>
	</div>
	
	<div class="col-lg-3 col-md-6 col-sm-6 node5">
		<div class="card card-stats"> 
			<div class="card-top">
				<div class="card-title">	
						<i class="fas fa-users"></i> 정회원
				</div>
				<div class="card-point">
						<p class="card-category numbers"><span class="gpoint">G</span><label id="sumRecommendGreenPoint">0</label>P</p>
				</div>
			</div>			
			<div class="card-footer">     
				<ul class="stats">
					<li>누적가입 <span><label id="totalRecommendCount">0</label>명</span></li>
					<li>당일가입 <span><label id="todayRecommendCount">0</label>명</span></li>
					<li>승인대기 <span><label id="readyRecommendCount">0</label>명</span></li>
					<!--<li>우수회원 <span>안영철맹점</span></li>-->
				</ul>  
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6 col-sm-6 node7">
		<div class="card card-stats">
			<div class="card-top">
				<div class="card-title">	
						<i class="fa fa-user"></i> 회원
				</div>
				<div class="card-point">
						<p class="card-category numbers"><span class="gpoint">G</span><label id="sumMemberGreenPoint">0</label>P</p>
				</div>
			</div>
			<div class="card-footer">
				<ul class="stats">
					<li>누적가입 <span><label id="totalMemberCount">0</label>명</span></li>
					<li>당일가입 <span><label id="todayMemberCount">0</label>명</span></li>
					<li>미인증 <span><label id="readyMemberCount">0</label>명</span></li>
					<!--<li>우수회원 <span>안영철회원</span></li>-->
				</ul>
			</div>
		</div>
	</div>	
</div>
<div class="row">
	<div class="col-md-12">
		<div class="card card-chart" id="pointChartArea">
			<div class="card-header">
				<h5 class="card-title">최근 포인트 통계 -<span>총판</span></h5>
				<p class="card-category">최근 1년간의 항목별 포인트 통계를 확인하실 수 있습니다.</p>
			</div>
			<div class="card-body ">
				<canvas id="pointChart" width="400" height="100"></canvas>
			</div>
			<div class="card-footer ">
				<div class="chart-legend">
					<i class="fa fa-circle text-info"></i> Red Point <i
						class="fa fa-circle text-warning"></i> Green Point <i
						class="fa fa-circle text-danger"></i> 전환 Point
				</div>
				<hr>
				<div class="stats">
					<i class="fa fa-history"></i> Updated 3 minutes ago
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 요기는 나중에 필요할까 하여 남겨두었습니다.-->
<div class="row">
	<div class="col-md-4">
		<div class="card ">
			<div class="card-header ">
				<h5 class="card-title">Email Statistics</h5>
				<p class="card-category">Last Campaign Performance</p>
			</div>
			<div class="card-body ">
				<canvas id="chartEmail"></canvas>
			</div>
			<div class="card-footer ">
				<div class="legend">
					<i class="fa fa-circle text-primary"></i> Opened <i
						class="fa fa-circle text-warning"></i> Read <i
						class="fa fa-circle text-danger"></i> Deleted <i
						class="fa fa-circle text-gray"></i> Unopened
				</div>
				<hr>
				<div class="stats">
					<i class="fa fa-calendar"></i> Number of emails sent
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-8">
		<div class="card card-chart">
			<div class="card-header">
				<h5 class="card-title">최근 포인트 누적현황</h5>
				<p class="card-category">최근 1년간의 항목별 포인트 누적현황을 확인하실 수 있습니다.</p>
			</div>
			<div class="card-body ">
				<canvas id="chartHours" width="400" height="100"></canvas>
			</div>
			<div class="card-footer ">
				<hr>
				<div class="stats">
					<i class="fa fa-history"></i> Updated 3 minutes ago
				</div>
			</div>
		</div>
	</div>
</div>


<!--   Page CSS Files   -->
<link href="resources/css/dashboard/bootstrap.min.css" rel="stylesheet" />
<link href="resources/css/dashboard/page-dashboard.css?v=2.0.0" rel="stylesheet" />


<!--   Core JS Files   -->
<script src="resources/js/lib/bootstrap.min.js"></script>

<!-- Chart JS -->
<script src="resources/js/lib/chart/chartjs.min.js"></script>
<script src="resources/js/lib/chart/demo.js"></script>

<!--   Page JS Files   -->
<script src="resources/js/${viewReqName}.js"></script>


