 let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{   // arrow function : this를 binding하기 위하여 사용,
			this.save();
		});
	},
	
	save: function(){
		//alert('user의 save함수 호출');
		// javascript object
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val(),
		};	
		
		$.ajax({
			// 회원가입 수행 요청 
			type :"POST",
			url:"/blog/api/user",
			data: JSON.stringify(data), // http body data 따라서 mime 타입이 필요
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		})
		.done(function(resp){   //  1
			alert("join seccess");
			console.log(resp);
			//location.href="/blog";
		})
		.fail(function(error){
			alert(JSON.stringify(error));
		})
	}
	//ajax 호출 시 default 가 비동기호출
   // ajax 통신을 이용해서 3개의 parameter를 json으로 변경하여 insert 요청할 예정
}

index.init();
