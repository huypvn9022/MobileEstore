document.querySelector("html").classList.add('js');

		var fileInput = document.querySelector(".input-file"),
			button = document.querySelector(".input-file-trigger"),
			the_return = document.querySelector(".file-return");

		button.addEventListener("keydown", function (event) {
			if (event.keyCode == 13 || event.keyCode == 32) {
				fileInput.focus();
			}
		});
		button.addEventListener("click", function (event) {
			fileInput.focus();
			return false;
		});
		fileInput.addEventListener("change", function (event) {
			console.log(this.value);
			the_return.innerHTML = this.value;
		});  
		
		//2
			var fileInput2 = document.querySelector(".input-file2"),
			button2 = document.querySelector(".input-file-trigger2"),
			the_return2 = document.querySelector(".file-return2");

		button2.addEventListener("keydown", function (event) {
			if (event.keyCode == 13 || event.keyCode == 32) {
				fileInput2.focus();
			}
		});
		button2.addEventListener("click", function (event) {
			fileInput2.focus();
			return false;
		});
		fileInput2.addEventListener("change", function (event) {
			console.log(this.value);
			the_return2.innerHTML = this.value;
		}); 
		
		//3
		var fileInput3 = document.querySelector(".input-file3"),
			button3 = document.querySelector(".input-file-trigger3"),
			the_return3 = document.querySelector(".file-return3");

		button3.addEventListener("keydown", function (event) {
			if (event.keyCode == 13 || event.keyCode == 32) {
				fileInput3.focus();
			}
		});
		button3.addEventListener("click", function (event) {
			fileInput3.focus();
			return false;
		});
		fileInput3.addEventListener("change", function (event) {
			console.log(this.value);
			the_return3.innerHTML = this.value;
		});  
		
		//4
			var fileInput4 = document.querySelector(".input-file4"),
			button4 = document.querySelector(".input-file-trigger4"),
			the_return4 = document.querySelector(".file-return4");

		button4.addEventListener("keydown", function (event) {
			if (event.keyCode == 13 || event.keyCode == 32) {
				fileInput4.focus();
			}
		});
		button4.addEventListener("click", function (event) {
			fileInput4.focus();
			return false;
		});
		fileInput4.addEventListener("change", function (event) {
			console.log(this.value);
			the_return4.innerHTML = this.value;
		}); 