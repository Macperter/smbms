var clickN = 0;
var page = ['./','','./'];
var weblogflag = false;

window.onload = function () {
	var t = document.querySelector('.top');
	// t.style.borderSpacing = document.body.offsetWidth *0.004 + 'px';
	// var p = $(document.body).css('height');
	//  $("td[style='text-align: center;width: 10%;'] > div" ).css('height',p)	;
	
	$("td[style='text-align: center;width: 10%;'] > div" ).click(function(){
		$("td[style='text-align: center;width: 10%;'] > div" ).css("background-color","");
		$(this).css("background-color","#ffffff");
	});
	
	$('#unline').click(function(){exitsystem()});
	$('td[style="text-align: center;width: 10%;"]>div:eq(4)').click(function(){exitsystem()});
	function exitsystem () {
		$.ajax ({
			url:'../exitsystem',
			type:'post',
			contentType:'text/plain;charset=UTF-8',
			data:'',
			success:function(data,stp,xhi){
				window.location.reload();
			}
		});
	}
	function fill (address){
		$.ajax({
			async:false,
			url:address,
			type:'get',
			data:'',
			contentType:'text/plain;charset=UTF-8',
			success:function(data,stp,xhi){
				console.log(data);
				console.log(xhi.response);
				$('#inner').html(data);
				weblogflag = true;
				//webloadpass();
			},
			error:function(con,f){
				console.log(con);
				console.log(f);

			}
		});
	}
	$('td[style="text-align: center;width: 10%;"]>div:eq(2)').click(function(){
		clickN = 2;
		fill('./table_na.html');
		weblogflag = false;
	});
	$('td[style="text-align: center;width: 10%;"]>div:eq(3)').click(function(){
		fill('./passchange.html');
		//if (weblogflag)
			webloadpass();
		// $.ajax({
		// 	url:'./passchange.html',
		// 	type:'get',
		// 	data:'',
		// 	contentType:'text/plain;charset=UTF-8',
		// 	success:function(data,stp,xhi){
		// 		console.log(data);
		// 		console.log(xhi.response);
		// 		$('#inner').html(data);
		// 		webloadpass();
		// 	},
		// 	error:function(con,f){
		// 		console.log(con);
		// 		console.log(f);
		//
		// 	}
		// });
	})
	function webloadpass() {
		console.log("zhix");
		var tm = document.getElementById('passchage').getElementsByTagName('input');
		var msg = document.querySelectorAll('.magp');
		tm[2].oninput = function() {
			console.log("zhix"+"tm[2].oninput ");
			console.log(tm[2].value);
			console.log(tm[1].value);
			if (tm[2].value === tm[1].value){
				msg[1].innerText = msg[2].innerText = "";
			}else{
				msg[1].innerText = msg[2].innerText = "两次密码输入不一致";
			}
				
		}
		for (let i =0;i<3;i++){
			tm[i].onfocus = function (){
				msg[i].innerText = "";
			}
		}
		// $('#passchage input').focus(function(){
		// 	for(let i =0;i<3;i++){
		// 		if ($(this) == $('#passchage input:eq('+i +')') ){
		// 			msg[i].innerText="";
		// 			break;
		// 		}
		// 	}
		// });
		$('#passfig').click(function(){
			console.log("zhix"+"$('#passfig').click(function()" + $('#passfig'));
			if (tm[2].value !== tm[1].value){
				msg[1].innerText = msg[2].innerText = "两次密码输入不一致"
				return;
			}
			if (tm[0].value===""){
				msg[0].innerText = '原密码为空';
				return;
			}
				
			if ("" === tm[1].value){
				msg[1].innerText = msg[2].innerText = "新密码不能为空"
				return;
			}
				
			$.ajax({
				url:'../changepass',
				type:'post',
				contentType:'application/x-www-form-urlencoded',
				data:'oldpass='+tm[0].value +'&' +'newpass='+tm[1].value,
				success:function(data,sta,xhr){
					if (data ==="[666]"){
						msg[0].innerHTML = "修改成功,5秒后重新登录";
						setTimeout(function () {
							window.location.reload();
						},3000);
					}else if (data ==="[665]"){
						msg[0].innerHTML = "旧密码与原密码相同";
					}else if (data === "[101]")
						msg[0].innerHTML = "旧密码错误";
					else{
						msg[0].innerHTML = data + "未知错误";
					}
				}
			})
		});
		
	}
	$.ajax ({
		url:'../getname',
		type:'post',
		contentType:'text/plain;charset=UTF-8',
		data:'',
		success:function(data,stp,xhi){
			let p = document.getElementById('name');
			p.innerText = data;
		}
	});
}
