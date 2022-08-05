const app = angular.module("shopping-cart-app", function($scope, $http) {
    // Quản lí giỏ hàng

    $scope.cart = {
        items: [],

        //thêm sản phẩm vào giỏ hàng
        add(id) {
            alert(id)
        },

        //Xóa sản phẩm khỏi giỏ hàng
        remove(id) {

        },

        //Xóa sạch giỏ hàng
        clear() {

        },

        //Tính thành tiền của 1 sản phẩm
        amt_of(item) {

        },

        //Tính tổng số lượng các mặt hàng trong giỏ
        get count() {

        },

        //Tổng thành tiền các mặt hàng có trong giỏ
        get amount() {

        },

        //Lưu giỏ hàng vào local storage
        saveToLocalStorage() {

        },


    }
})