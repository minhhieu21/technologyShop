const app = angular.module("shopping-cart-app", [])

app.controller("shopping-cart-ctrl", function($scope, $http) {

    // Quản lí giỏ hàng

    $scope.cart = {
        items: [],

        //thêm sản phẩm vào giỏ hàng
        add(id) {

            alert(id)
                //tìm xem có mặt hàng nào có ID này chưa
            var item = this.items.find(item => item.id == id);

            //Có
            if (item) {
                //Tăng số lượng lên và lưu vào local
                item.qty++;
                this.saveToLocalStorage();
            }
            //chưa có
            else {

                //tải sản phẩm trên server thông qua API
                $http.get(`/rest/products/${id}`).then(resp => {

                    //đặt số lượng = 1
                    resp.data.qty = 1;

                    //Bỏ vào danh sách các mặt hàng đã chọn
                    this.items.push(resp.data);

                    //lưu vào local
                    this.saveToLocalStorage();
                })
            }
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

            //đổi ds các mặt hàng sang file json
            var json = JSON.stringify(angular.copy(this.items));

            //lưu vào local
            localStorage.setItem("cart", json);
        },


    }
})