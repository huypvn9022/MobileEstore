/**
 *  mobile cart
 */

var app = angular.module("mobile-estore-app", [])
app.controller("mobile-estore-ctrl", function($scope, $http) {
	$scope.cart = {
		items: [],
		imageArr: [],
		add(id) {
			let item = this.items.find(item => item.maSP == id);
			if (item) {
				item.soLuong++;
				this.saveToLocalStorage();
			} else {	
				$http.get(`/rest/products/${id}`).then(response => {
					response.data.soLuong = 1;
					this.items.push(response.data);
					this.saveToLocalStorage();
				}).catch(eror => {
					console.log(eror)
				})		
			}
		},
		remove(id) {
			let item = this.items.findIndex(item => item.maSP == id);
			let text = confirm(`Bạn có muốn xóa điện thoại này không ?`)
			if(text){
				this.items.splice(item, 1);
				this.imageArr.splice(item, 1);
				this.saveToLocalStorage();
			}else{
				this.saveToLocalStorage();
			}
		},
		clear(){
			this.items = [];
			this.saveToLocalStorage();
		},
		get count(){
			return this.items
				.map(item => item.soLuong)
				.reduce((total, soLuong) => total += soLuong, 0);
		},
		get price(){
			return this.items
				.map(item => item.soLuong * item.donGia)
				.reduce((total, soLuong) => total += soLuong, 0);
		},
		saveToLocalStorage(){
			$http.get(`/cart/images`).then(response => {
				imageArr = response.data
				let json = JSON.stringify(imageArr)
				localStorage.setItem("cartImages", json);
			})	
			let json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cartMobileEstore",json);		
		},
		loadToLocalStorage(){
			let json = localStorage.getItem("cartMobileEstore");
			this.items = json ? JSON.parse(json) : [];
			let jsonImage = localStorage.getItem("cartImages");
			this.imageArr = jsonImage ? JSON.parse(jsonImage) : [];
		},		
	}
	
	$scope.cart.loadToLocalStorage();
	
	function getCookie(cname) {
		var name = cname + '=';
		var decodedCookie = decodeURIComponent(document.cookie);
		var ca = decodedCookie.split(';');
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == ' ') {
				c = c.substring(1);
			}
			if (c.indexOf(name) == 0) {
				return c.substring(name.length, c.length);
			}
		}
		return '';
	}
	
	$scope.order = {
		ngayTao: new Date(),
		tongTien: $scope.cart.items
			.map(item => item.donGia * item.soLuong)
			.reduce((total, soLuong) => total += soLuong, 0),
		ghiChu: "Giao hàng cẩn thận",
		trangThai: "Processing",
		makh: {
			taiKhoan:$("#taikhoankh").text()
		},		
		get ctdh(){
			return $scope.cart.items.map(item => {
				return {
					masp: {maSP: item.maSP},
					donGia: item.donGia,
					soLuong: item.soLuong
				}
			})
		},
		datHang(){
			let order = angular.copy(this);
			$http.post("/rest/orders", order).then(resp => {
				let message = confirm("Bạn có chắc chắn đặt hàng không ?");
				if(message){
					alert("Đặt hàng thành công !");
					$scope.cart.clear();
					location.href="/index/order-management";	
				}
			}).catch(error => {
				alert("Đặt hàng thất bại !");
				console.log(error + "error");
			})
		}
	}
	

	
})





	
	

