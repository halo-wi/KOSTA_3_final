/**
 * 
 */

var replyManager =(function(){

	var a= '??'
		console.log(a)

	
	var getAll = function(obj, callback){	
		console.log("get All..."+obj);

		$.getJSON("/replies/board/"+obj, callback)
		
		
	};
	
	var add = function(obj, callback){
	
		console.log("add...");
		$.ajax({
			url:"/replies/" + obj["bid"],
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
			url:"/replies/" + obj["bid"],
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
			url:"/replies/"+obj["bid"]+"/"+obj["rid"],
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