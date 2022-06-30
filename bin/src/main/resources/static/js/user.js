let index={
		init: function(){
			$("#btn-save").on("click", ()=>{
				this.save();
			});
		},

	save:function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		// ajax 통신을 이용해서 3개의 데이터를 Json으로 변경하여 insert 요청
		$.ajax({
			type : "POST",
			url : "/blog/api/user",
			data : JSON.stringify(data),  // http body data
			contentType: 'application/json; charset=utf-8', // Body data Type
			dataType : "json" // response,자동으로 Json 에서 java object로 바꿔주네 
		}).done(function(resp){
			alert("Success Join");
//			console.log(resp);
			location.href = "/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	}
}

index.init();