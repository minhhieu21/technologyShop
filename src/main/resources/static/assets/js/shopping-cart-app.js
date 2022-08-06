const app = angular.module("shopping-cart-app", [])

app.controller("shopping-cart-ctrl", function($scope, $http) {

    // Quản lí giỏ hàng

    $scope.cart = {
        items: [],

        //thêm sản phẩm vào giỏ hàng
        add(id) {
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
            //tìm mã sản phẩm có trong giỏ hàng thông qua id
            var index = this.items.findIndex(item => item.id == id);

            //xóa phần tử khỏi mảng
            this.items.splice(index, 1);

            //lưu lại vào local
            this.saveToLocalStorage();
        },

        //Xóa sạch giỏ hàng
        clear() {

            //cho mảng rỗng
            this.items = [];

            //lưu lại vào local
            this.saveToLocalStorage();
        },

        //Tính thành tiền của 1 sản phẩm
        amt_of(item) {

        },

        //Tính tổng số lượng các mặt hàng trong giỏ
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },

        //Tổng thành tiền các mặt hàng có trong giỏ
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },

        //Lưu giỏ hàng vào local storage
        saveToLocalStorage() {

            //đổi ds các mặt hàng sang file json
            var json = JSON.stringify(angular.copy(this.items));

            //lưu vào local
            localStorage.setItem("cart", json);
        },

        loadFromLocalStorage() {
            //đọc cart từ localstorage
            var json = localStorage.getItem("cart")

            //nếu có chuyển sang json và dán vào items
            this.items = json ? JSON.parse(json) : [];
        }
    }
    $scope.cart.loadFromLocalStorage();
})