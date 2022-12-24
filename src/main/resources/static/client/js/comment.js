function render(data) {
	let htmls = "<div class='leftPaneImg'><i class='fa fa-user'></i></div>"
		+ "<div class='rightPanel'><span class='title'>" + data.name + "</span><span class='straight'>|</span><span class='date'>"
		+ data.date + "</span><span id='delete' class='delete float-right'>...</span>"
		+ "<span class='description'>" + data.body + "</span></div><div class='clear'></div><div class='border-list'></div>";
	$('.comment-list').append(htmls);
}

$(document).ready(function() {
	let today = new Date().toLocaleString();
	let content = []
	if (!localStorage.comentData) {
		localStorage.comentData = []
	} else {
		content = JSON.parse(localStorage.comentData)
	}
	for (let i = 0; i < content.length; i++) {
		render(content[i])
	}

	// button toggle
	$("#buttonToggle").click(function() {
		$("#panelToggle").slideToggle()
	})

	// if content temp
	if (content.length == 0) {
		$(".commentBox").css("display", "none")
	} else {
		$(".commentBox").css("display", "block")
	}
	// action
	$("#addComment").click(function() {
		let addObj = {
			"taikhoan": $("#taikhoan").val(),
			"masp": $('#masp').val(),
			"noidung": $("#noidung").val()
		}
		alert(addObj.noidung)
		
		
		
		
		
		
		
		
		/** 
		content.push(addObj)
		localStorage.comentData = JSON.stringify(content)
		render(addObj);
		$(".commentBox").fadeIn()
		$("#name").val('')
		$("#date").val('')
		$("#body").val('')
		*/
	})

	//delete
	$("#delete").each(function(index) {
		$(this).click(function() {
			console.log(index)
		})
	})


	for (let i = 0; i < content.length; i++) {

	}


})
