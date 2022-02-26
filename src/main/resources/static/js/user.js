 let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ //this를 바인딩 하기 위해서 arrow function 사용
			this.save();
		});
		$("#btn-login").on("click", ()=>{ //this를 바인딩 하기 위해서 arrow function 사용
			this.login();
		});
	},
	
	save: function(){
		//alert('user의 save함수 호출');
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val(),
		};
		
		console.log(data);
		// ajax 통신을 이용해서 3개의 parameter를 json으로 변경하여 insert 요청할 예정
		// ajax는 비동기
		$.ajax({
			type:"POST",
			url:"/blog/api/user",
			data:JSON.stringify(data),
			contentType: "application/json; charset=utf-8", //body data가 어떤 타입인지(MIME)
			dataType:"json" // 
		}).done(function(resp){
			alert("Join Success");
		 // console.log(resp);
			location.href="/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	login: function(){
		//alert('user의 save함수 호출');
		let data = {
			username:$("#username").val(),
			password:$("#password").val()
		};

		$.ajax({
			type:"POST",
			url:"/blog/api/user/login",
			data:JSON.stringify(data),
			contentType: "application/json; charset=utf-8", //body data가 어떤 타입인지(MIME)
			dataType:"json" // 
		}).done(function(resp){
			alert("login Success");
			location.href="/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	}
}

index.init();