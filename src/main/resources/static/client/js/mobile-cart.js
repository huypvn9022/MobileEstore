/**
 *  mobile cart
 */

var app = angular.module("mobile-estore-app", [])
app.controller("mobile-estore-ctrl", function($scope, $http) {
	
	$scope.cart = {
		items: [],
		// thêm sp vào giỏ
		add(id){
			alert(id)
		},
		// xóa sản phẩm khỏi giỏ
		remove(id){},
		// xóa sạch các mặt hàng trong giỏ
		clear(){}
	}


})