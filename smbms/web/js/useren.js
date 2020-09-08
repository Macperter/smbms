$('#gojunp').ready(function () {
	$('div [style="background-color: #1E90FF; padding: 6px;border-radius: 3px;cursor: pointer;"]').click(
		function () {
			var name = $('#liinput').text();
			let job = $('#liselect').val();
			let page = $('#gojunp').val();
			let colt = "";
			$.ajax({
				url:'../usermar',
				type:'get',
				data:`name=${name}&job=${job}&page=${page}`,
				contentType:'application/json;charset=UTF-8',
				success:function(data,stp,xhi){
					let tmp = "";
					tmp+='<tr>';
					for (let i in data.data[0]){
						tmp+=`<td>${i}</td>`;
					}
					tmp+='</tr>';
					for (let i=0;i<data.data.length;i++){
						tmp+='<tr>';
						for (let t in data.data[i]){
							tmp+=`<td>${data.data[i][t]}</td>`;
						}
						tmp+=colt + '</tr>';
					}
					$('#ullitable').html(tmp);
				},
				error:function(con,f){
					console.log(con);
					console.log(f);

				}
			})
		}

	)
});
