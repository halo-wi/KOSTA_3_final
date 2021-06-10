/**
 * 공통 자바스크립트
 */

var replyManager =(function(){
	
	var getAll = function(obj, callback){
		console.log("get All..."+obj);
		//$.getJSON은 Ajax의 하나의 방법
		$.getJSON("/replies/board/"+obj, callback)
		
		
	};
	
	var add = function(obj, callback){
	
		console.log("add...");
		$.ajax({
			url:"/replies/" + obj["bno"],
			data:JSON.stringify(obj),
			dataType:"json",
			type: "post",
			contentType:"application/json",
			success:callback  /*printlist가 실행됨*/
		});
	};
	
	var update = function(obj, callback){
		console.log("update reply.........");
		$.ajax({
			url:"/replies/" + obj["bno"],
			data: JSON.stringify(obj),
			dataType:"json",
			type:"put",
			contentType:"application/json",
			success:callback
			
		});
	};

	
	var remove = function(obj, callback){
		console.log("remove...");
		$.ajax({
			url:"/replies/"+obj["bno"]+"/"+obj["rno"],
			type:"delete",
			success:callback
		});
	};
	
	return{
		"getAll" : getAll,
		"add": add,
		"update" : update,
		"remove" : remove
	};
	
})();