var map
var infoWindowArr = []; //�앹뾽
var resultMarkerArr = []; //留덉빱
var resultDrawArr = []; //�대━�쇱씤
var drawInfoArr = []; //寃쎈줈洹몃┝�뺣낫
var checkTraffic = []; //�몃옒�쎌꽕�뺤젙蹂�

var thumb_img_path = "";

String.prototype.replaceHtmlEntites = function() {
	var s = this;
	var translate_re = /&(nbsp|amp|quot|lt|gt);/g;
	var translate = {"nbsp": " ","amp" : "&","quot": "\"","lt"  : "<","gt"  : ">"};
	return ( s.replace(translate_re, function(match, entity) {
	  return translate[entity];
	}) );
};

function initTmap(){
	var mapObj = "map_wrap";
	var height = "100%"
   	if($("#media1023").css('display') != "none") {
   		mapObj = "map_mob_wrap";
   		height = "calc(100% - 45px)"
   	}
	map = new Tmapv2.Map(mapObj, {
		center: new Tmapv2.LatLng(37.88459763, 127.73019333), // 지도 초기화
		width : "100%",
		height : height,
		zoom : 12,
		zoomControl : true,
		scrollwheel : true,
		// scaleBar : true,
	});
}
function closeMobileMap() {
	$("#mob_map").removeClass('open');
	$("div.mapBg").hide();
}
function setMapCenter(regionMap) {
	var lon, lat;
	if(regionMap) {
		var region = regionMap.split(',');
		if(region.length > 0) {
			lon = String(region[1]);
			lat = String(region[0]);
		}
	}
	if(map && lon && lat) {
		map.setCenter(new Tmapv2.LatLng(lat,lon));
	}
}

function setPointAndDrawRoutes(lat, lon, status, idx) {
	// console.log("setPointAndDrawRoutes.....");
	addMarkers({
		lng : lon,
		lat : lat,
		pointType : status
	});

	var startAddr = $("input.start_word").eq(idx-1).val();
	var endAddr = $("input.arrival_word").eq(idx-1).val();

	var axisObj = {};
	axisObj['startX'] = $("input.start_word").eq(idx-1).data('lon');
	axisObj['startY'] = $("input.start_word").eq(idx-1).data('lat');
	axisObj['endX'] = $("input.arrival_word").eq(idx-1).data('lon');
	axisObj['endY'] = $("input.arrival_word").eq(idx-1).data('lat');

	if (status == "E" && startAddr && endAddr) {

		var viaPoints = [];		
		var tab_con = $("#day_lst li div.tab_con").eq(idx-1);
		// tab_con.css("border", "1px solid red");
		// var item = tab_con.find('ul.tab_list li:not(.list-group-item)');
		var item = tab_con.find('ul.tab_list li:not(.list-group-item):not(.way-point-info)');
	    item.each(function(index) {
	    	var viaObj = {};
			var lat = $(this).data('lat');
			var lon = $(this).data('lon');

			var pointId = $(this).data('destid');

			viaObj['viaX'] = String(lon);
			viaObj['viaY'] = String(lat);
			viaObj['viaPointId'] = pointId;
			viaObj['viaPointName'] = pointId;

			viaPoints.push(viaObj);
	    });

		if(item.length > 0) {
			getRouteUsingViaPoints(axisObj, viaPoints, "Y"); //�쇰컲 寃쎈줈�덈궡 - 寃쎌쑀吏�, 援먰넻�뺣낫 �ы븿
			// getRouteSequential(axisObj, viaPoints); //�ㅼ쨷 寃쎌쑀吏� 30 - 臾대즺�쒕퉬�ㅼ뿉�� �쇱씪�몃옒�� 100嫄� �쒗븳
		}

	} else {
		map.setCenter(new Tmapv2.LatLng(lat,lon));
		// map.setZoom(13);
	}
}

function searchAddress(status, idx){
	var pop = window.open("searchaddress.jsp?status="+status+"&idx="+idx,"pop","width=600,height=670, scrollbars=yes, resizable=yes");
}
function addrCallBack(roadFullAddr, latitude, longitude, status, idx){
   	var obj = $("#day_lst");
   	if($("#media1023").css('display') != "none") {
   		obj = $("dl.day_sc");
   	}

	var addrObj;
	if (status == "E") {
		addrObj = $(obj).find("input.arrival_word").eq(idx-1);
	} else {
		addrObj = $(obj).find("input.start_word").eq(idx-1);
	}
	addrObj.val(roadFullAddr);
	addrObj.attr('data-lat', latitude);
	addrObj.attr('data-lon', longitude);
	
   	if($("#media1023").css('display') == "none") {
		setPointAndDrawRoutes(latitude, longitude, status, idx);
	}
}

function setDestinationSticky(destAxisY, destAxisX, destTitle) {
	var infoObj = {};
	infoObj.lon = destAxisX;
	infoObj.lat = destAxisY;
	// infoObj.pointType = "P";
	infoObj.pointNum = "0";
	infoObj.infoWinTitle = destTitle;
	setPointSticky(infoObj);
}

function getDestinationDetail(idx) {
	// console.log("run discount");
	$.get(contextPath+'/travel/destination/retrieveDestinationDetail.do?destId='+idx, function(data) {
		// console.log(data);
		try {
			var obj = JSON.parse(data);
			if(obj && obj.hasOwnProperty("destTitle")) {
				var bx_tit = obj.destTitle;
				var detail_txt = obj.destInformation;
				if(obj.destAdSlogan === null){
					obj.destAdSlogan = " ";
				}
				var info_slogan = '<li>'+obj.destAdSlogan+ ' ' +obj.destTitle+'</li>';
				var info_phone = '<li>전화번호 : '+obj.destPhone+'</li>';
				var info_addr = '<li>주소 : '+obj.destAddress+'</li>';
				// var thumb_img = thumb_img_path + obj.destImgPath;
				var thumb_img = contextPath+obj.destImgPath;
				if(obj.travelFileList && obj.travelFileList.length > 0) {
					thumb_img = contextPath+obj.travelFileList[0].imgFilePath;
				}

				$('#detail_info_area p.bx_tit').text(bx_tit);
				$('#detail_info_area div.info_lst > ul').empty();
				$('#detail_info_area div.info_lst > ul').append(info_slogan);
				$('#detail_info_area div.info_lst > ul').append(info_phone);
				$('#detail_info_area div.info_lst > ul').append(info_addr);
				if(detail_txt == null || !detail_txt) {
					$('#detail_info_area div.detail_txt').text('');
				} else {
					$('#detail_info_area div.detail_txt').text(detail_txt.replace(/(<([^>]+)>)/ig,"").replaceHtmlEntites());
				}
				$('#detail_info_area div.detail_txt').css('margin', '23px 25px');
				// $('#detail_info_area div.detailzone ul.lst li').empty();
				$('#detail_info_area div.detailzone ul.lst li img').attr("src", thumb_img);
				$('#detail_info_area div.detailzone ul.lst li img').attr("alt", bx_tit);
				$('#detail_info_area div.detailzone ul.lst li img').attr("width", 251);
				$('#detail_info_area div.detailzone ul.lst li img').attr("height", 210);

				$("#detail_info_area").show();
			} else {
	    		throw new SyntaxError("�붿껌�댁슜�� 議고쉶�섏� 紐삵븿");
			}
		} catch (e) {
			// console.log( e.name );
			// console.log( e.message );
			alert("�쇱떆�곸씤 �먮윭媛� 諛쒖깮�덉뒿�덈떎. �좎떆 �� �ㅼ떆 �쒕룄�� 二쇱꽭��."); return false;
		}
	});
}

function getClipDestinationList(page) {
	var pageNo = 1;
	if(page) pageNo = page;

	// var clipUserId = $("#routRegMember").val();
	var clipUserId = $("input[name='routRegMember']").val();
	if(!clipUserId) {
		alert("�뚯썝�꾩슜 �쒕퉬�� �낅땲��.\n�쒕퉬�� �꾩뿉 �댁슜�� 二쇱꽭��");
		location.replace(contextPath+"/travel/login.jsp");
	} else {
	   	var reqUrl = contextPath+"/travel/member/retrieveClipDestinationList.do";
		var formData = "clipUserId=" + clipUserId + "&pageIndex=" + pageNo;
	    var promise = getAsyncDataList(reqUrl, formData);
	    promise.success(function (data) {
	    	setDestinationList(data, "getClipDestinationList");
	    });
	}
}

function initTagList() {
	var tagObj = ("ul.normal-cat-list li label input[type=checkbox]");
	if($("#media1023").css('display') != "none") tagObj = $("ul.mobile-cat-list li label input[type=checkbox]");
	$(tagObj).each(function() {
		$(this).prop('checked', false);
	});
	
}

function changeDestinationRegion(regionObj) {
	
	// var region = $("select[name='dest_region']").val();
	var region = $(regionObj).val();
	var regionMap = {};
regionMap["강원도"] = "37.555837,128.209315";
	regionMap["경기도"] = "37.567167,127.190292";
	regionMap["경상남도"] = "35.259787,128.664734";
	regionMap["경상북도"] = "36.248647,128.664734";
	regionMap["충청남도"] = "36.557229,126.779757";
	regionMap["충청북도"] = "36.628503,127.929344";
	regionMap["서울특별시"] = "37.540705,126.956764";
	regionMap["광주광역시"] = "35.126033,126.831302";
	regionMap["대구광역시"] = "35.798838,128.583052";
	regionMap["대전광역시"] = "36.321655,127.378953";
	regionMap["부산광역시"] = "35.198362,129.053922";
	regionMap["울산광역시"] = "35.519301,129.239078";
	regionMap["인천광역시"] = "37.469221,126.573234";
	regionMap["전라북도"] = "35.716705,127.144185";
	regionMap["전라남도"] = "34.819400,126.893113";
	regionMap["제주특별자치도"] = "33.364805,126.542671";

	setMapCenter(regionMap[region]);

	initTagList();
	getDestinationList('1');
}

function changeCategory(page) {
	initTagList();
	getDestinationList(page);
}

function getDestinationList(page, tag_list) {
	var tag_arr = [];
	if(tag_list) tag_arr = tag_list;
	var pageNo = 1;
	if(page) pageNo = page;

	var region = $("#travelRoute select[name='dest_region']").val();
	if($("#media1023").css('display') != "none") region = $("#dest_region").val();


	if(!tag_list) {
		var tagObj = ("ul.normal-cat-list li label input[type=checkbox]");
		if($("#media1023").css('display') != "none") tagObj = $("ul.mobile-cat-list li label input[type=checkbox]");
		// $("ul.lst li label input[type=checkbox]").each(function() {
		$(tagObj).each(function(idx) {
			if($(this).is(":checked")) {
				tag_arr.push($(this).val());
			}
		});
	}

   	var reqUrl = contextPath+"/travel/destination/retrieveDestinationList.do";
	var formData = "destRegion=" + region + "&pageIndex=" + pageNo;
	/*if($("#media1023").css('display') != "none") {
		formData += "&pageUnit=5&pageSize=5";
	}*/
	if(tag_arr.length > 0) {
		formData += "&destTag=" + tag_arr;
	}

    var promise = getAsyncDataList(reqUrl, formData);
    promise.success(function (data) {
    	
    	if($("#media1023").css('display') == "none") {
    		setDestinationList(data);
    	} else {
    		setDestinationListMobile(data);
    	}
    });
}

function getAsyncDataList(reqUrl, formData) {
	/* for IE11 cache disabling
	var timestamp = + new Date();
	formData += "&rtime=" + timestamp;
	*/
	console.log("formData", formData);
    return $.ajax({
        type: "GET"
        , url: reqUrl
        , data:encodeURI(formData)
        , cache:false
        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        , error: function(data, status, err) {
        	console.log("data", data);
        	console.log("status", status);
        	console.log("err", err);
            // alert('�쒕쾭���� �듭떊�� �ㅽ뙣�덉뒿�덈떎.');
        }
    });
}

function setDestinationList(data, paginationType) {
	// console.log(data); //return;
	try {
		var obj = JSON.parse(data);
	    if(obj.constructor !== Object){
	    	console.log("�곗씠�곕� 媛��몄삤吏� 紐삵뻽�듬땲��.");
    		throw new SyntaxError("�곗씠�곕� 媛��몄삤吏� 紐삵븿");
	    }else{
	        var lists = "";
	        var dest = obj.resultList;
	    	for(var i=0; i<dest.length; i++) {
	    		var destImgPath = (dest[i]["destImgPath"] == null || !dest[i]["destImgPath"] )? contextPath+"/images/travel/content/noimg.jpg" : contextPath+dest[i]["destImgPath"];
				lists += "<li data-lat=\""+dest[i]["destAxisY"]+"\"  data-lon=\""+dest[i]["destAxisX"]+"\"  data-destid=\""+dest[i]["destId"]+"\">";
				lists += "<div class=\"img\"><img src=\""+destImgPath+"\" alt='"+dest[i]["destTitle"]+"' /></div>";
				lists += "<div class=\"txt_box\">";
				lists += "<p class=\"txt\"><a href=\"javascript:;\" onclick=\"setDestinationSticky('"+dest[i]["destAxisY"]+"', '"+dest[i]["destAxisX"]+"', '"+dest[i]["destTitle"]+"');\">"+dest[i]["destTitle"]+"</a></p>";
				lists += "<a href=\"javascript:;\" class=\"more\" onclick=\"getDestinationDetail('"+dest[i]["destId"]+"');\">�붾낫湲�</a>";
				lists += "</div>";
				lists += "<div class=\"icon_box\">";
				/*lists += "<a href=\"#none\" class=\"ico_heart\">醫낆븘��</a>";*/
				lists += "<a href=\"javascript:;\" class=\"ico_add\" onclick=\"addPoint(this);\">異붽�</a>";
				lists += "<a href=\"javascript:;\" class=\"ico_close\" onclick=\"delPoint(this);\" class=\"ico_close\">�リ린</a>";
				lists += "</div>";
				lists += "</li>";
			}
	       	$("ul.result_list").empty();
	       	$("ul.result_list").append(lists);
			//pagination
	       	setPaginationInfo(obj.paginationInfo, paginationType);
	    }
	} catch (e) {
		// console.log(e.name);
		// console.log(e.message);
		alert("�쇱떆�곸씤 �먮윭媛� 諛쒖깮�덉뒿�덈떎. �좎떆 �� �ㅼ떆 �쒕룄�� 二쇱꽭��."); return false;
	}
}
function setDestinationListMobile(data, paginationType) {
	// console.log(data); return;
	try {
		var obj = JSON.parse(data);
	    if(obj.constructor !== Object){
	    	console.log("�곗씠�곕� 媛��몄삤吏� 紐삵뻽�듬땲��.");
	    	throw new SyntaxError("�곗씠�곕� 媛��몄삤吏� 紐삵븿");
	    }else{
	        var lists = "";
	        var dest = obj.resultList;
	    	for(var i=0; i<dest.length; i++) {
				lists += "<li data-lat=\""+dest[i]["destAxisY"]+"\" data-lon=\""+dest[i]["destAxisX"]+"\" data-destid=\""+dest[i]["destId"]+"\">";
				lists += "<div class=\"day_box\">";
				lists += "<a href=\"#none\" class=\"tbox\">";
				lists += "<span class=\"txt\">"+dest[i]["destTitle"]+"</span>";
				lists += "</a>";
				lists += "<div class=\"mob_detail_info_area\" style=\"display:none;\">";
				lists += "<div class=\"tit_box\">";
				lists += "<p>�곸꽭�뺣낫</p>";
				lists += "</div>";
				lists += "<div class=\"info_lst\">";
				lists += "<ul>";
				lists += "<li>�꾪솕踰덊샇 : "+dest[i]["destPhone"]+"</li>";
				lists += "<li>二쇱냼 : "+dest[i]["destAddress"]+"</li>";
				lists += "<li>紐낆묶 : "+dest[i]["destTitle"]+"</li>";
				lists += "</ul>";
				lists += "</div>";
				lists += "<div class=\"detail_txt\">"+(dest[i]["destInformation"] == null ? '' : dest[i]["destInformation"])+"</div>";
				lists += "</div>";
				lists += "<a href=\"javascript:;\" class=\"ico_add mob_plus\" onclick=\"addPointMobile(this);\">�좏깮</a>";
				lists += "</div>";
				lists += "</li>";
			}
	       	$("#result_list").empty();
	       	$("#result_list").append(lists);
			//pagination
	       	setPaginationInfo(obj.paginationInfo, paginationType);
	    }
	} catch (e) {
		// console.log(e.name);
		// console.log(e.message);
		alert("�쇱떆�곸씤 �먮윭媛� 諛쒖깮�덉뒿�덈떎. �좎떆 �� �ㅼ떆 �쒕룄�� 二쇱꽭��."); return false;
	}
}

function setPaginationInfo(pageInfo, paginationType) {
	var pageSize = pageInfo.pageSize;
   	var totalPageCount = pageInfo.totalPageCount;
   	var currentPageNo = pageInfo.currentPageNo;
   	var firstPageNo = pageInfo.firstPageNo;
   	var startPageNoOnList = pageInfo.firstPageNoOnPageList;
   	var lastPageNoOnList = pageInfo.lastPageNoOnPageList;
   	var lastPageNo = pageInfo.lastPageNo;

   	var pageNavFuntion = "getDestinationList";
   	if(paginationType) {
   		pageNavFuntion = paginationType;
   	}

   	var pageEl = "";
	pageEl += "<div class=\"pagination\">";
	// if(startPageNoOnList > firstPageNo) { //�먮옒 �닿쾶 留욌뒗�� 湲곗〈 �꾩옄�뺣� pagination �쇰━�ㅻ쪟�� �곕Ⅸ �쒓린�ㅻ쪟寃곌낵�� UI瑜� 留욎텛湲� �꾪빐 �꾨옒�� 媛숈씠 �섏젙��
	if(totalPageCount > pageSize) {
		pageEl += "<a href=\"?pageIdx="+firstPageNo+"\" onclick=\""+pageNavFuntion+"("+firstPageNo+"); return false;\" title=\"泥섏쓬�섏씠吏�\" class=\"page_prevend\"><span>泥섏쓬</span></a>&nbsp;";
		pageEl += "<a href=\"?pageIdx="+(currentPageNo-1)+"\" onclick=\""+pageNavFuntion+"("+(currentPageNo-1)+"); return false;\" title=\"�댁쟾�섏씠吏�\" class=\"page_prev\"><span>�댁쟾</span></a>&nbsp;";
	}
   	for(var no=startPageNoOnList; no<=lastPageNoOnList; no++) {
   		if(currentPageNo == no) {
			pageEl += "<strong title=\"�꾩옱�섏씠吏�\">"+currentPageNo+"</strong>&nbsp;";
   		} else {
			pageEl += "<a href=\"?pageIdx="+no+"\" onclick=\""+pageNavFuntion+"("+no+"); return false;\" title=\""+no+"�섏씠吏�\">"+no+"</a>&nbsp;";
   		}
	}
	// if(lastPageNoOnList < lastPageNo) { //�먮옒 �닿쾶 留욌뒗�� 湲곗〈 �꾩옄�뺣� pagination �쇰━�ㅻ쪟�� �곕Ⅸ �쒓린�ㅻ쪟寃곌낵�� UI瑜� 留욎텛湲� �꾪빐 �꾨옒�� 媛숈씠 �섏젙��
	if(totalPageCount > pageSize) {
		pageEl += "<a href=\"?pageIdx="+(currentPageNo+1)+"\" onclick=\""+pageNavFuntion+"("+(currentPageNo+1)+"); return false;\" title=\"�ㅼ쓬�섏씠吏�\" class=\"page_next\"><span>�ㅼ쓬</span></a>&nbsp;";
		pageEl += "<a href=\"?pageIdx="+lastPageNo+"\" onclick=\""+pageNavFuntion+"("+lastPageNo+"); return false;\" title=\"留덉�留됲럹�댁�\" class=\"page_nextend\"><span>留덉�留�</span></a>";
	}
	pageEl += "</div>";
	$("div.pagination").empty().append(pageEl);
}

function launchMap(idx) {
	// $("#wizard").steps('next');
	// $("#wizard").steps('next');
	if(idx) {

		var axisObj = {};
		var viaPoints = [];		
		var item = $("dl.day_sc:visible").eq(idx-1);
		// var len = item.length;
	    // item.each(function(index) {
			
			axisObj['startX'] = $(item).find("input.start_word").data('lon');
			axisObj['startY'] = $(item).find("input.start_word").data('lat');
			axisObj['endX'] = $(item).find("input.arrival_word").data('lon');
			axisObj['endY'] = $(item).find("input.arrival_word").data('lat');

			var viaCount = item.find("ul.day_list li:not(.way-point-info)").length;
			// item.find("ul.day_list li").each(function() {
			item.find("ul.day_list li:not(.way-point-info)").each(function() {
				var lat = $(this).data('lat');
				var lon = $(this).data('lon');
				var pointId = $(this).data('destid');
				viaPoints.push({
					"viaX" : String(lon),
					"viaY" : String(lat),
					"viaPointId" : pointId,
					"viaPointName" : pointId,
				});
			});

	    // });

		if(viaCount > 0) {
			getRouteUsingViaPoints(axisObj, viaPoints, "Y"); //�쇰컲 寃쎈줈�덈궡 - 寃쎌쑀吏�, 援먰넻�뺣낫 �ы븿
			// getRouteSequential(axisObj, viaPoints); //�ㅼ쨷 寃쎌쑀吏� 30 - 臾대즺�쒕퉬�ㅼ뿉�� �쇱씪�몃옒�� 100嫄� �쒗븳
		}
	}

}

function addDayInput() {
	var idx = $('.day_sc').index($('.day_sc:visible').last());
	// console.log(idx);
	var obj = $('.day_sc').eq(idx+1);
	if(idx < 6) {
		$(obj).show();
		$(obj).find('dd.day_area').show();
		animateScroll(obj);
	} else {
		// console.log('last');
		return;
	}
}

function searchPoint(idx) {
	$("#wizard").steps('next');

	$('.day_sc').each(function(index) {
		if(index == (idx-1)) {
			$(this).addClass("on");
		} else {
			$(this).removeClass("on");
		}
	});
}

function setPointSticky(infoObj) {
	var lon = infoObj.lon;
	var lat = infoObj.lat;
	var pointType = infoObj.pointType;
	var pointNum = infoObj.pointNum;
	var infoWinTitle = infoObj.infoWinTitle;

	var markerParam = {
		lng : lon,
		lat : lat
	};
	if(pointNum) {
		markerParam.pointNum = pointNum;
	} else {
		markerParam.pointType = pointType;
	}
	addMarkers(markerParam);

	var tit = infoWinTitle;
	var content = "<div style='position: relative; border-bottom: 1px solid #dcdcdc; "+
				  "line-height: 18px; padding: 3px; width: 150px; height: auto; text-align:center;'>"+
				  "<span style='font-size: 12px; line-height: 15px;'>"+tit+"</span>"+
				  "</div>";
	//Popup 媛앹껜 �앹꽦.
	var infoWindow = new Tmapv2.InfoWindow({
		position: new Tmapv2.LatLng(lat,lon), //Popup �� �쒖텧�� 留� 醫뚰몴
		content: content, //Popup �쒖떆�� text
		type: 2, //Popup�� type �ㅼ젙.
		map: map //Popup�� �쒖떆�� 留� 媛앹껜
	});
	infoWindowArr.push(infoWindow);

	map.setCenter(new Tmapv2.LatLng(lat,lon));
	/* map.setZoom(10); //TMap.prototype.setZoom - setScaleOffset 
	- Uncaught TypeError: n.screenPoint.equals is not a function */
}

//嫄곕━怨꾩궛 , �앹떆 �붽툑 怨꾩궛 �쒓컙 怨꾩궛
function setWayPointInfo(curObj, items) {

	var lat = $(curObj).data('lat');
	var lon = $(curObj).data('lon');

	var infoObj = {};
	infoObj.lon = lon;
	infoObj.lat = lat;
	// infoObj.pointType = "P";
	infoObj.pointNum = items.length;
	// infoObj.pointNum = $(items).not('.way-point-info').length;
	infoObj.infoWinTitle = $(curObj).find('p.txt').text();
	setPointSticky(infoObj);

	var axisObj = {};
	axisObj["endY"] = lat;
	axisObj["endX"] = lon;

	if(items.length > 1) {
	    var idx = items.length - 2;
	    // var idx = $(items).not('.way-point-info').length - 2;
	    // var prevObj = $(items).not('.way-point-info').eq(idx);
	    var prevObj = $(items).eq(idx);
		axisObj["startY"] = $(prevObj).data('lat');
		axisObj["startX"] = $(prevObj).data('lon');
		// axisObj["startId"] = items.eq(index-1).data('destid');
		// axisObj["endId"] = $(this).data('destid');
		// console.log(axisObj);
		getDistance(axisObj, $(curObj));
	} else {
		var panel = $(curObj).closest('div.tab_con');
		// console.log(panel);
		// $(panel).css('border', '1px solid red');
		var start_point = $(panel).find('input.start_word');
		if($(start_point).val()) {
			axisObj["startY"] = $(start_point).data('lat');
			axisObj["startX"] = $(start_point).data('lon');
			getDistance(axisObj, $(curObj));
		}
	}
}

function addPointByDrag(evt) {
	// var items = evt.to.children;
	var items = $(evt.to.children).not('.way-point-info');
	// console.log(items.length);
	/*$(item).each(function() {
		console.log($(this));
	});*/

    var curObj = evt.item;

	setWayPointInfo(curObj, items);
}

function getVoiaPointsLengthAlert()
{
	var sortableObj = $('#day_lst li:not([style*="display: none"]) div.tab_con:not([style*="display: none"]) ul.tab_list');
	
	var arrObj = $(sortableObj).children().not('.way-point-info');
	var acceptAble = $("#acceptAble").val();
	
	if(arrObj.length + 1 > 5)
	{
		if(acceptAble == 'N')
		{
			alert("寃쎌쑀吏�媛� 5媛� �댁긽�� 寃쎌슦 寃쎈줈瑜� �앹꽦�� �� �놁뒿�덈떎.");
			$("#acceptAble").val('Y');	
		}
	}
}

function addPoint(obj) {
	var sortableObj = $('#day_lst li:not([style*="display: none"]) div.tab_con:not([style*="display: none"]) ul.tab_list');
	
	getVoiaPointsLengthAlert();
	
	var list_item = $(obj).parents("li");
	list_item.clone().appendTo(sortableObj);

    var curObj = $(sortableObj).children().not('.way-point-info').last();
    var items = $(sortableObj).children().not('.way-point-info');

	setWayPointInfo(curObj, items);
	/*var lat = $(curObj).data('lat');
	var lon = $(curObj).data('lon');

	var infoObj = {};
	infoObj.lon = lon;
	infoObj.lat = lat;
	// infoObj.pointType = "P";
	infoObj.pointNum = items.length;
	infoObj.infoWinTitle = $(curObj).find('p.txt').text();
	setPointSticky(infoObj);

	if(items.length > 1) {
	    var idx = items.length - 2;
		var axisObj = {};
	    var prevObj = $(items).eq(idx);
		axisObj["startY"] = $(prevObj).data('lat');
		axisObj["startX"] = $(prevObj).data('lon');
		// axisObj["startId"] = items.eq(index-1).data('destid');
		axisObj["endY"] = lat;
		axisObj["endX"] = lon;
		// axisObj["endId"] = $(this).data('destid');
		// console.log(axisObj);
		getDistance(axisObj, $(curObj));
	}*/
}

function addPointMobile(obj) {
	// console.log(obj);	
	var destObj;
	var len;
	$('.day_sc').each(function(index) {
		if($(this).hasClass("on")) {
			len = $(this).find("ul.day_list li").length + 1;
			destObj = $(this).find("ul.day_list");
			// $(this).css('border', '1px solid red');
		}
	});

	if(destObj) {
		var day_box = $(obj).closest("div.day_box")
		var title = day_box.find("span.txt").text();
		var liObj = $(day_box).closest("li");
		var lat = $(liObj).data("lat");
		var lon = $(liObj).data("lon");
		var destid = $(liObj).data("destid");


		var lists = "";
		lists += "<li data-lat=\""+lat+"\" data-lon=\""+lon+"\" data-destid=\""+destid+"\">";
		lists += "<span class=\"num\">"+len+"</span>";
		lists += "<div class=\"day_box\">";
		lists += "<a href=\"#none\" class=\"tbox\">";
		lists += "<span class=\"txt\">"+title+"</span>";
		lists += "</a>";
		lists += "<a href=\"javascript:;\" onclick=\"delPoint(this);\" class=\"delect\">��젣</a>";
		lists += "</div>";
		lists += "</li>";
		// console.log(lists);
		destObj.append(lists);

		$("#wizard").steps('previous');

		animateScroll(destObj);
	}

}
/*function delPoint(obj) {
	var list_item = $(obj).closest("li");
	// list_item.css("border", "2px solid red");
	list_item.remove();
}*/
function delPoint(obj) {
	var list_item = $(obj).closest("li");
	// $(list_item).css('border', '1px solid red');
	$(list_item).prev(".way-point-info").remove();
	$(list_item).remove();
	
	if($($("#example3Left").children()[0]).hasClass("way-point-info") == true){
		$($("#example3Left").children()[0]).remove();
	}
	clearMap();
	
	
}

function animateScroll(obj) {
	var offset = $(obj).offset();
	$('html, body').animate({scrollTop : offset.top}, 400);
	// $(document).scrollTop($(document).height()+500);
}

function saveTravelRoute(frmName) {
	if(!$('#'+frmName+' input[name="routRegMember"]').val()) {
		alert('�뚯썝 �꾩슜 �쒕퉬�� �낅땲��.\n濡쒓렇�� �� �댁슜�� 二쇱꽭��');
		location.replace(contextPath+'/travel/login.jsp');
		return false;
	}

	if(!$('#'+frmName+' input[name="routTitle"]').val()) {
		alert("�ы뻾�쇱젙 �쒕ぉ�� �낅젰�� 二쇱꽭��");
		$('#'+frmName+' input[name="routTitle"]').focus();
		return false;
	}

	if(confirm("�묒꽦�� �ы뻾�쇱젙�� ���ν븯�쒓쿋�듬땲源�?\n異쒕컻吏�,�꾩갑吏��� �� 媛� �댁긽�� 寃쎈줈瑜� �낅젰�� �쇱젙留� ���λ맗�덈떎.")) {
		
		var errMsg = '';
		var hasError = 'N';
		var vParentObject;

		var travelRouteObj = {};
		var routId = $('#'+frmName+' input[name="routId"]').val();
		var routTitle = $('#'+frmName+' input[name="routTitle"]').val();
		// var routRegMember;
		var routRegion = $('#'+frmName+' select[name="dest_region"]').val();
		if(frmName == "travelRouteMobile") routRegion = $("#dest_region").val();
		var routMemo = $('#'+frmName+' textarea[name="routMemo"]').val();
		var routeDailyList = [];

		travelRouteObj["routTitle"] = routTitle;
		travelRouteObj["routRegion"] = routRegion;
		travelRouteObj["routMemo"] = routMemo;

		var pointObj = $("#day_lst li div.tab_con");
		if(frmName == "travelRouteMobile") pointObj = $("dl.day_sc");
		// $("#day_lst li div.tab_con").each(function(index) {
		$(pointObj).each(function(index) {
			var routDays;
			var routStartPoint;
			var routStartAxis;
			var routDestPoint;
			var routDestAxis;
			var routWayPoint;

			var startAddr = $(this).find("input.start_word").val();
			var endAddr   = $(this).find("input.arrival_word").val();
			
			var startX 	= $(this).find("input.start_word").data('lon');
			var startY 	= $(this).find("input.start_word").data('lat');
			var endX 	= $(this).find("input.arrival_word").data('lon');
			var endY 	= $(this).find("input.arrival_word").data('lat');

			var itemObj = "ul.tab_list li:not(.list-group-item):not(.way-point-info)";
			if(frmName == "travelRouteMobile") itemObj = $("ul.day_list li");
			// var item = $(this).find('ul.tab_list li:not(.list-group-item):not(.way-point-info)');
			var item = $(this).find(itemObj);
			var wayPoint = "";
			routDays = index + 1;

		    item.each(function(idx) {
		    	if(idx > 0) wayPoint += "|";
				wayPoint += $(this).data('destid');
		    });

		    // 異쒕컻吏� �꾩갑吏� �뺣낫媛� �덇퀬, 寃쎌쑀�쇱젙�� �� 媛� �댁긽�� 寃쎌슦留� �꾩넚
		    if(startAddr && endAddr && item.length > 0) {
				/*js �� java 媛� json object �몄떇諛⑹떇 李⑥씠濡� �꾨옒�� 媛숈씠 �묒꽦��*/
				travelRouteObj["routeDailyList["+ index +"].routDays"] = routDays;
				travelRouteObj["routeDailyList["+ index +"].routStartPoint"] = startAddr;
				travelRouteObj["routeDailyList["+ index +"].routStartAxis"] = startY + "," + startX;
				travelRouteObj["routeDailyList["+ index +"].routDestPoint"] = endAddr;
				travelRouteObj["routeDailyList["+ index +"].routDestAxis"] = endY + "," + endX;
				travelRouteObj["routeDailyList["+ index +"].routWayPoint"] = wayPoint;
		    }
		    else
		    {
				// 2022-04-15 異쒕컻吏�, 紐⑹쟻吏�, 寃쎌쑀吏� 理쒖냼 1媛� �댁긽 �낅젰 �꾩닔 -- 源��꾩닔
				vParentObject = $(this).parent();
				errMsg = index + 1 + ' DAY�� '
				if(!startAddr){errMsg += '異쒕컻吏�瑜� �낅젰�댁＜�몄슂.';}
				else if(!endAddr){errMsg += '�꾩갑吏�瑜� �낅젰�댁＜�몄슂.';}
				else if(!item.length > 0){errMsg += '寃쎌쑀吏�瑜� 理쒖냼 1媛� �댁긽 �낅젰�댁＜�몄슂.';}
				hasError = 'Y';
				return false;
			}
			
			if ($(".day_lst > ul >li:visible").length == index + 1)
			{
				return false;
			}
			
		});
		
		// 2022-04-15 異쒕컻吏�, 紐⑹쟻吏�, 寃쎌쑀吏� 理쒖냼 1媛� �댁긽 �낅젰 �꾩닔 -- 源��꾩닔
		if(hasError == 'Y')
		{
			if(!vParentObject)
			{
				errMsg = '�붿껌�섏떊 ���μ쓣 �꾨즺�섏� 紐삵뻽�듬땲��. 愿�由ъ옄�먭쾶 臾몄쓽�� 二쇱꽭��.';
			}
			else
			{
				$(vParentObject).children('a').click();
			}
			alert(errMsg);
			return false;
		}

	   	var reqUrl = contextPath+"/travel/route/insertAsync.do";
    	var actionName = "����";
		if(routId) {
			travelRouteObj["routId"] = routId;
			reqUrl = contextPath+"/travel/route/updateAsync.do";
    		actionName = "�섏젙";
		}
	    console.dir(travelRouteObj);
	    // var params = JSON.stringify(travelRouteObj);
	    var promise = sendAsyncData(reqUrl, travelRouteObj);
	    promise.success(function (data) {
	    	// console.log(data);
			try {
		    	var obj = JSON.parse(data);
		    	if(obj.status == "success") {
					alert(obj.title + " " + "�쇱젙�� "+ actionName +"�섏��듬땲��.");
					location.replace(contextPath+'/travel/member/myroute.do');
		    	} else {
		    		throw new Error("�붿껌�댁슜�� �뺤긽�곸쑝濡� ���ν븯吏� 紐삵븿");
		    	}
			} catch (e) {
				// console.log(e.name);
				// console.log(e.message);
				alert("�붿껌�섏떊 "+ actionName +"�� �꾨즺�섏� 紐삵뻽�듬땲��. �좎떆 �� �ㅼ떆 �쒕룄�� 二쇱꽭��."); return false;
			}
	    });
	    promise.error(function(request,status,error){
			// console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			alert("�붿껌�섏떊 "+ actionName +"�� �꾨즺�섏� 紐삵뻽�듬땲��. 愿�由ъ옄�먭쾶 臾몄쓽�� 二쇱꽭��."); return false;
		});

	}
}

function sendAsyncData(reqUrl, params) {
    return $.ajax({
        type: "POST"
        , url: reqUrl
        , data:params
		, async:false
		, cache:false
        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        , error: function(data, status, err) { 
            alert('�쒕쾭���� �듭떊�� �ㅽ뙣�덉뒿�덈떎.');
        }
    });
}

function initRegisterLayer() {
	location.reload();
}

function delDailyLayer() {
	// $("#day_lst ul li:visible").last().css('border', '1px solid red');
	// var dayObj = $("#day_lst>ul>li:visible").last();
	if($('#day_lst>ul>li:not([style*="display: none"])').length > 1) {
		var dayObj = $('#day_lst>ul>li:not([style*="display: none"])').last();
		// console.log(dayObj.find("div.tab_con ul.tab_list"));
		dayObj.find("div.tab_con ul.tab_list").empty();
		dayObj.find("div.tab_con input[type=text]").each(function(){
			$(this).val('');
		});
		dayObj.css('display', 'none');
		// dayObj.css('border', '2px solid aqua');
		// $("#day_lst>ul>li:visible").last().find('a.tabh').click();
		// $('#day_lst>ul>li:not([style*="display: none"])').last().find('a.tabh').click();

		// console.log($('#day_lst>ul>li:not([style*="display: none"])').length);

		var newObj = $('#day_lst>ul>li:not([style*="display: none"])').last();
		$(newObj).find("div").show();
		$(".tabh").not($(this)).css("border", "none");
		$(newObj).find(".tabh").css("border", "1px solid red");

		var idx = $('#day_lst>ul>li:not([style*="display: none"])').index(newObj);
		$("div.day_tit").find("span").text(idx+1);

		clearMap();
	}
}

$(function(){

	$("ul.lst li label input[type=checkbox]:not(.check-all)").bind("click", function() {
		var cat_box = $(this).closest("li.cat-box");
		var tag_list = [];
		cat_box.find("input[type=checkbox]:not(.check-all)").each(function(idx) {
			if($(this).is(":checked")) {
				tag_list.push($(this).val());
			}
		});
		// console.log(tag_list.toString());
		getDestinationList(1, tag_list);
		cat_box.find("div.cssSelect").removeClass("on");
	});

	$("ul.lst li label input.check-all").bind("click", function() {
		var cat_box = $(this).closest("li.cat-box");
		var tag_list = [];
		if($(this).is(":checked")) {
			cat_box.find("input[type=checkbox]:not(.check-all)").each(function(idx) {
				$(this).prop('checked', true);
				tag_list.push($(this).val());
			});
			getDestinationList(1, tag_list);
			cat_box.find("div.cssSelect").removeClass("on");

		} else {
			cat_box.find("input[type=checkbox]:not(.check-all)").each(function(idx) {
				$(this).prop('checked', false);
			});
		}
		// console.log(tag_list.toString());
	});

});

/*$(window).on('resize', function () {
	console.log('display ', $("#media1023").css('display'));
});*/