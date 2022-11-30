/**
 *  mobile cart
 */

var app = angular.module("mobile-estore-app", [])
app.controller("mobile-estore-ctrl", function($scope, $http) {
	$scope.cart = {
		items: [],
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
		imageArr: [],
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
	
	$scope.order = {
		ngayTao: new Date(),
		account: {
			taikhoan:$("#taikhoan").text()
		},
		get orderDetails(){
			return $scope.cart.items.map(item => {
				return {
					product: {masp: item.maSP},
					dongia: item.donGia,
					soluong: item.soLuong
				}
			})
		},
		datHang(){
			let order = angular.copy(this);
			$http.post("/rest/orders", order).then(resp => {
				alert("Đặt hàng thành công !")
				$scope.cart.clear()
			}).catch(error => {
				alert("Đặt hàng không thành công !")
				console.log(error + " error ")
			})
			
		}
	}
		
	
})





	
	

