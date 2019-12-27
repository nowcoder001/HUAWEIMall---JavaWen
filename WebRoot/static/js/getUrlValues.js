UrlParam = function() { // url²ÎÊý 
¡¡¡¡var data, index; 
¡¡¡¡(function init() { 
¡¡¡¡¡¡¡¡data = []; //Öµ£¬Èç[["1","2"],["zhangsan"],["lisi"]] 
¡¡¡¡¡¡¡¡index = {}; //¼ü:Ë÷Òý£¬Èç{a:0,b:1,c:2} 
¡¡¡¡¡¡¡¡var u = window.location.search.substr(1); 
¡¡¡¡¡¡¡¡if (u != '') { 
¡¡¡¡¡¡¡¡¡¡¡¡var params = decodeURIComponent(u).split('&'); 
¡¡¡¡¡¡¡¡¡¡¡¡for (var i = 0, len = params.length; i < len; i++) { 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡if (params[i] != '') { 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡var p = params[i].split("="); 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡if (p.length == 1 || (p.length == 2 && p[1] == '')) {// p | p= | = 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡data.push(['']); 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡index[p[0]] = data.length - 1; 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡} else if (typeof(p[0]) == 'undefined' || p[0] == '') { // =c ÉáÆú 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡continue; 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡} else if (typeof(index[p[0]]) == 'undefined') { // c=aaa 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡data.push([p[1]]); 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡index[p[0]] = data.length - 1; 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡} else {// c=aaa 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡data[index[p[0]]].push(p[1]); 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡} 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡} 
¡¡¡¡¡¡¡¡¡¡¡¡} 
¡¡¡¡¡¡¡¡} 
¡¡¡¡})(); 
¡¡¡¡return { 
 ¡¡¡¡¡¡¡¡// »ñµÃ²ÎÊý,ÀàËÆrequest.getParameter() 
¡¡¡¡¡¡¡¡param : function(o) { // o: ²ÎÊýÃû»òÕß²ÎÊý´ÎÐò 
¡¡¡¡¡¡¡¡¡¡¡¡try { 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡return (typeof(o) == 'number' ? data[o][0] : data[index[o]][0]); 
¡¡¡¡¡¡¡¡¡¡¡¡} catch (e) { 
¡¡¡¡¡¡¡¡¡¡¡¡} 
¡¡¡¡¡¡¡¡}, 
¡¡¡¡¡¡¡¡//»ñµÃ²ÎÊý×é, ÀàËÆrequest.getParameterValues() 
¡¡¡¡¡¡¡¡paramValues : function(o) { // o: ²ÎÊýÃû»òÕß²ÎÊý´ÎÐò 
¡¡¡¡¡¡¡¡¡¡¡¡try { 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡return (typeof(o) == 'number' ? data[o] : data[index[o]]); 
¡¡¡¡¡¡¡¡¡¡¡¡} catch (e) {} 
¡¡¡¡¡¡¡¡}, 
¡¡¡¡¡¡¡¡//ÊÇ·ñº¬ÓÐparamName²ÎÊý 
¡¡¡¡¡¡¡¡hasParam : function(paramName) { 
¡¡¡¡¡¡¡¡¡¡¡¡return typeof(paramName) == 'string' ? typeof(index[paramName]) != 'undefined' : false; 
¡¡¡¡¡¡¡¡}, 
¡¡¡¡¡¡¡¡// »ñµÃ²ÎÊýMap ,ÀàËÆrequest.getParameterMap() 
¡¡¡¡¡¡¡¡paramMap : function() { 
¡¡¡¡¡¡¡¡¡¡¡¡var map = {}; 
¡¡¡¡¡¡¡¡¡¡¡¡try { 
¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡for (var p in index) { map[p] = data[index[p]]; } 
¡¡¡¡¡¡¡¡¡¡¡¡} catch (e) {} 
¡¡¡¡¡¡¡¡¡¡¡¡return map; 
¡¡¡¡¡¡¡¡} 
  ¡¡¡¡} 
}();