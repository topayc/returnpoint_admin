create view chart_test as 
	select tb1.date as date,
		tb1.greenPoint as greenPoint,tb1.conversionPoint as conversionPoint,tb2.redPoint as redPoint,sum(tb2.redPoint) as accRedPoint 
	from chart_test1 tb1 
	join chart_test2 tb2 
	on tb2.date < tb1.date
	group by tb1.date 
	order by tb1.date desc;

create view chart_test1 as 
	select 
		dates.date as date,floor(rand() * 100) as greenPoint,floor(rand() * 100) as conversionPoint 
	from dates 
	where dates.date between (curdate() + interval -(30) day) and curdate();
	
create view chart_test2 as 
	select 
		dates.date as date,floor(rand() * 100) as redPoint 
	from dates 
	where dates.date between (curdate() + interval -(30) day) and curdate();
	
create view dates as 
	select 
		curdate() - interval numbers.number day as date 
	from numbers 
	union all 
	select 
		curdate() + interval (numbers.number + 1) day as date 
	from numbers;
	
create view digits as 
	select 0 as digit 
	union all select 1 as 1 
	union all select 2 as 2 
	union all select 3 as 3 
	union all select 4 as 4 
	union all select 5 as 5 
	union all select 6 as 6 
	union all select 7 as 7 
	union all select 8 as 8 
	union all select 9 as 9;
	
create view exec_point as 
	select 	
		b.pointConversionTansactionNo as pointConversionTansactionNo,
		b.pointConvertRequestNo as pointConvertRequestNo,b.memberNo as memberNo,
		b.eachPoint as eachPoint,
		b.accPoint as accPoint,
		b.remainPoint as remainPoint,
		b.accRate as accRate,
		if(b.accPoint > a.conversionTotalPoint or a.conversionTotalPoint = 0,a.conversionTotalPoint,b.accPoint) as conversionAccPoint,
		if(b.accPoint > a.conversionTotalPoint or a.conversionTotalPoint = 0,a.conversionTotalPoint - b.accPoint,b.eachPoint) as conversionEachPoint,
		if(b.accRate > 1 or a.conversionTotalPoint = 0,1,b.accRate) as conversionAccRate,
		if(1 > b.accRate and a.conversionTotalPoint > 0,1,3) as conversionStatus 
	from plan_point b join point_conversion_transaction a 
	on a.pointConversionTansactionNo = b.pointConversionTansactionNo 
	and a.pointConvertRequestNo = b.pointConvertRequestNo 
	and a.memberNo = b.memberNo 
	where a.conversionStatus = 1;
		
create view member_green_point as 
	select 
		green_point.memberNo as memberNo,
		sum(if(green_point.nodeType = '1',green_point.pointAmount,0)) as sumMemberGreenPoint,
		sum(if(green_point.nodeType = '2',green_point.pointAmount,0)) as sumRecommendGreenPoint,
		sum(if(green_point.nodeType = '3',green_point.pointAmount,0)) as sumBranchGreenPoint,
		sum(if(green_point.nodeType = '4',green_point.pointAmount,0)) as sumAgenctGreenPoint,
		sum(if(green_point.nodeType = '5',green_point.pointAmount,0)) as sumAffiliateGreenPoint,
		sum(if(green_point.nodeType = '6',green_point.pointAmount,0)) as sumSaleManagerGreenPoint,
		sum(if(green_point.nodeType = '7',green_point.pointAmount,0)) as sumSoleDistGreenPoint 
	from green_point;
	
create view member_red_point as
	select 
		red_point.memberNo as memberNo,sum(red_point.pointAmount) as sumMemberRedPoint 
	from red_point 
	group by red_point.memberNo;
	
create view numbers as 
	select 
		ones.digit + tens.digit * 10 + hundreds.digit * 100 + thousands.digit * 1000 as number 
	from digits ones join digits tens 
	join digits hundreds 
	join digits thousands;
	
create view plan_point as 
	select 
		point_conversion_transaction.pointConversionTansactionNo as pointConversionTansactionNo,
		point_conversion_transaction.pointConvertRequestNo as pointConvertRequestNo,
		point_conversion_transaction.memberNo as memberNo,
		round(point_conversion_transaction.conversionTotalPoint * point_conversion_transaction.pointTransRate,2) as eachPoint,
		round((point_conversion_transaction.conversionAccPoint + point_conversion_transaction.conversionTotalPoint * point_conversion_transaction.pointTransRate),0) as accPoint,
		if(point_conversion_transaction.conversionTotalPoint - point_conversion_transaction.conversionAccPoint > 0,	point_conversion_transaction.conversionTotalPoint - point_conversion_transaction.conversionAccPoint,0) as remainPoint,
		round(point_conversion_transaction.conversionAccRate + point_conversion_transaction.pointTransRate,2) as accRate 
	from point_conversion_transaction 
	where point_conversion_transaction.conversionStatus = 1;

create view point_conversion_plan as
  select    
    gp.memberNo,
    gp.nodeType,
    gp.pointAmount,
    ROUND(gp.pointAmount*p.redPointAccRate,2) as conversionAccPoint,
    p.redPointAccRate as pointTransRate,
    '3' as conversionStatus,
    now() as createTime,
    now() as updateTime
  from
    green_point gp,
    policy p join information_schema.tables t on p.policyNo = t. auto_increment-1 
    where t.table_name = 'policy'
    and t.table_schema = 'returnp_db'
    and gp.pointAmount>p.pointTransLimit;
    
create view dash_board AS
    select 
        count(mb.memberNo) 			   as totalMemberCount,
        sum(mb.isRecommender 	= 'Y') as totalRecommendCount,
        sum(mb.isSaleManager 	= 'Y') as totalSaleManagerCount,
        sum(mb.isBranch 		= 'Y') as totalBranchCount,
        sum(mb.isAffiliate 		= 'Y') as totalAffiliateCount,
        sum(mb.isAgency 		= 'Y') as totalAgenctCount,
        sum(mb.isSoleDist 		= 'Y') as totalSoleDistCount,
        sum(mb.createTime > CURDATE()) as todayMemberCount,
        sum(mb.isRecommender = 'Y' 	and rc.createTime > CURDATE()) as todayRecommendCount,
        sum(mb.isSaleManager = 'Y' 	and sm.createTime > CURDATE()) as todaySaleManagerCount,
        sum(mb.isBranch = 'Y' 		and br.createTime > CURDATE()) as todayBranchCount,
        sum(mb.isAffiliate = 'Y' 	and af.createTime > CURDATE()) as todayAffiliateCount,
        sum(mb.isAgency = 'Y' 		and ag.createTime > CURDATE()) as todayAgenctCount,
        sum(mb.isSoleDist = 'Y' 	and sd.createTime > CURDATE()) as todaySoleDistCount,
        sum(mb.memberStatus = '3') 									as readyMemberCount,
        sum(mb.isRecommender = 'Y' 	and rc.recommenderStatus = '1') as readyRecommendCount,
        sum(mb.isSaleManager = 'Y' 	and sm.saleManagerStatus = '1') as readySaleManagerCount,
        sum(mb.isBranch = 'Y' 		and br.branchStatus = '1') 		as readyBranchCount,
        sum(mb.isAffiliate = 'Y' 	and af.affiliateStatus = '1') 	as readyAffiliateCount,
        sum(mb.isAgency = 'Y' 		and ag.agencyStatus = '1') 		as readyAgenctCount,
        sum(mb.isSoleDist = 'Y' 	and sd.soleDistStatus = '1') 	as readySoleDistCount,
        round(SUM(red.sumMemberRedPoint)	,0) as sumMemberRedPoint,
        round(green.sumMemberGreenPoint		,0) as sumMemberGreenPoint,
        round(green.sumRecommendGreenPoint	,0) as sumRecommendGreenPoint,
        round(green.sumSaleManagerGreenPoint,0) as sumSaleManagerGreenPoint,
        round(green.sumBranchGreenPoint		,0) as sumBranchGreenPoint,
        round(green.sumAffiliateGreenPoint	,0) as sumAffiliateGreenPoint,
        round(green.sumAgenctGreenPoint		,0) as sumAgenctGreenPoint,
        round(green.sumSoleDistGreenPoint	,0) as sumSoleDistGreenPoint
    from
        member mb
        left join recommender rc ON mb.memberNo = rc.memberNo
        left join sale_manager sm ON mb.memberNo = sm.memberNo
        left join branch br ON mb.memberNo = br.memberNo
        left join affiliate af ON mb.memberNo = af.memberNo
        left join agency ag ON mb.memberNo = ag.memberNo
        left join sole_dist sd ON mb.memberNo = sd.memberNo
        left join member_red_point red ON mb.memberNo = red.memberNo
        left join member_green_point green ON mb.memberNo = green.memberNo;
        
create view node_tree
  as
	select 
		'7' node_type, concat('7','-',soleDistNo) node_id, null up_node_id, 
   	 	soleDistNo node_no,	null up_node_no, soleDistName name , null recommenderNo, null saleManagerNo, 
    	memberNo, null as recommenderMemberNo,null saleManagerMemberNo 
    from sole_dist 
    
	union all
    
	select 
		'3' node_type, concat('3','-',branchNo) node_id, concat('7','-',soleDistNo) up_node_id, 
   	 	branchNo node_no,soleDistNo up_node_no, branchName name, a.recommenderNo,b.saleManagerNo, 
    	a.memberNo, b.memberNo as recommenderMemberNo,c.memberNo as saleManagerMemberNo 
    from branch a 
    left outer join recommender b 
    on a.recommenderNo = b.recommenderNo
    left outer join sale_manager c
    on b.saleManagerNo = c.saleManagerNo
    
	union all
    
	select 
		'4' node_type, concat('4','-',agencyNo) node_id, concat('3','-',branchNo) up_node_id, 
    	agencyNo node_no, branchNo up_node_no, agencyName name , a.recommenderNo,b.saleManagerNo, 
    	a.memberNo, b.memberNo as recommenderMemberNo,c.memberNo as saleManagerMemberNo  
    from agency a 
    left outer join recommender b 
    on a.recommenderNo = b.recommenderNo
    left outer join sale_manager c
    on b.saleManagerNo = c.saleManagerNo
    
	union all
    
	select 
		'5' node_type, concat('5','-',affiliateNo) node_id, concat('4','-',agencyNo) up_node_id, 
    	affiliateNo node_no,	agencyNo up_node_no, affiliateName name , a.recommenderNo,b.saleManagerNo, 
    	a.memberNo, b.memberNo as recommenderMemberNo,c.memberNo as saleManagerMemberNo  
    from affiliate a 
    left outer join recommender b 
    on a.recommenderNo = b.recommenderNo
    left outer join sale_manager c
    on b.saleManagerNo = c.saleManagerNo
    
    