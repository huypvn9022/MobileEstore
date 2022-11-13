
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
	// action
	$("#addComment").click(function() {
		let addObj = {
			"name": $("#name").val(),
			"date": today,
			"body": $("#body").val()
		}
		content.push(addObj)
		localStorage.comentData = JSON.stringify(content)
		render(addObj);
		$("#name").val('')
		$("#date").val('')
		$("#body").val('')
	})

	//delete
	for (let i = 0; i < content.length; i++) {
		$("#delete").click(function() {
			console.log("delete" + i)
		})
	}


})
