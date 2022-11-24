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
			console.log(this.items[item] + " item ne ")
			let text = confirm(`Bạn có muốn xóa điện thoại này không ?`)
			if(text){
				this.items.splice(item, 1);
				this.saveToLocalStorage();
			}else{
				this.saveToLocalStorage();
			}
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
	
	$scope.initialize = function() {
		
		arrs = [],
		
		$http.get(`/cart/images`).then(response => {
			this.arrs.push(response.data)
			let json = JSON.stringify(angular.copy(this.arrs));
			localStorage.setItem("cartImages",json);
			console.log(  arrs + " response ne ")
		})
	}	
		
	
})





	
	

