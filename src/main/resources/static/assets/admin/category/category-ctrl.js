app.controller("category-ctrl", function($scope, $http) {
    $scope.items = [];
    $scope.form = {};

    $scope.initialize = function() {
        //load category
        $http.get("/rest/categories").then(resp => {
            $scope.items = resp.data;
        })
    }

    //Hiển thị thông tin lên form
    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show')
    }

    //Reset form
    $scope.reset = function() {
        $scope.form = {}
    }

    //Thêm mới loại sản phẩm
    $scope.create = function() {
        //Lấy dữ liệu từ form
        var item = angular.copy($scope.form);

        //Post dữ liệu lên
        $http.post(`/rest/categories`, item).then(resp => {
            //bỏ vào list product
            $scope.items.push(resp.data);

            Swal.fire(
                'Thành công',
                'Thêm mới loại sản phẩm thành công ',
                'success'
            )

            $scope.reset();

            $scope.initialize();

        }).catch(error => {
            Swal.fire(
                'Opps...!',
                'Thêm mới loại sản phẩm thất bại ',
                'error'
            )
            console.log("Error", error);
        })
    }

    //Cập nhật sản phẩm
    $scope.update = function() {

        Swal.fire({
            title: 'Are you sure?',
            text: "Thay đổi thông tin loại sản phẩm",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, update it!'
        }).then((result) => {
            if (result.isConfirmed) {
                //Lấy dữ liệu từ form
                var item = angular.copy($scope.form);

                //Put dữ liệu lên theo id
                $http.put(`/rest/categories/${item.id}`, item).then(resp => {

                    //Tìm trong product id == id resp trả về => thay đổ i
                    var index = $scope.items.findIndex(p => p.id == item.id);
                    $scope.items[index] = item;
                    Swal.fire(
                        'Thành công',
                        'Cập nhật loại sản phẩm thành công',
                        'success'
                    )
                    $scope.reset();
                    $scope.initialize();
                }).catch(error => {
                    Swal.fire(
                        'Opps...!',
                        'Không thể cập nhật loại sản phẩm',
                        'error'
                    )
                    console.log("Error", error);
                })
            }
        })
    }

    //Xóa loại sản phẩm
    $scope.delete = function(item) {

        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                //delete dữ liệu lên theo id
                $http.delete(`/rest/categories/${item.id}`).then(resp => {

                    //Tìm trong product id == id resp trả về => =xóa
                    var index = $scope.items.findIndex(p => p.id == item.id);
                    $scope.items.splice(index, 1);

                    Swal.fire(
                        'Thành công',
                        'Xóa loại sản phẩm thành công',
                        'success'
                    )

                    $scope.reset();
                    $scope.initialize();

                }).catch(error => {
                    // console.log("Error", error);
                    Swal.fire(
                        'Opps...!',
                        'Không thể xóa loại sản phẩm',
                        'info'
                    )
                })
            }
        })
    }

    $scope.pager = {
        page: 0,
        size: 5,
        get items() {
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.items.length / this.size);
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;

            //
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        }
    }

    $scope.initialize();
});