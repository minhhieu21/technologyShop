app.controller("product-ctrl", function($scope, $http) {
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};

    $scope.initialize = function() {
        //load product
        $http.get("/rest/products").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate)
            });
        }).catch((err) => {
            console.log("Lỗi")
        });

        //load category
        $http.get("/rest/categories").then(resp => {
            $scope.cates = resp.data;
        })
    }

    //Hiển thị thông tin lên form
    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show')
    }

    //Upload hình
    $scope.imageChanged = function(files) {
        //Tạo đối tường formdata
        var data = new FormData();

        //Lấy file chọn
        data.append('file', files[0]);

        //Post lên server
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            //upload thành công
            //Lấy name data thế vào thuộc tính images 
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Lỗi upload hình ảnh")
            console.log("Error", error);
        })
    }

    //Reset form
    $scope.reset = function() {
        $scope.form = {
            createDate: new Date(),
            image: 'cloud-upload.jpg',
            available: true
        }


    }

    //Thêm mới sản phẩm
    $scope.create = function() {
        //Lấy dữ liệu từ form
        var item = angular.copy($scope.form);

        //Post dữ liệu lên
        $http.post(`/rest/products`, item).then(resp => {
            //
            resp.data.createDate = new Date(resp.data.createDate)

            //bỏ vào list product
            $scope.items.push(resp.data);

            Swal.fire(
                    'Thành công',
                    'Thêm mới sản phẩm thành công ',
                    'success'
                )
                //reset form
            $scope.reset();
            $scope.initialize();

        }).catch(error => {
            Swal.fire(
                'Opps...!',
                'Thêm mới sản phẩm thất bại ',
                'error'
            )
            console.log("Error", error);
        })
    }

    //Cập nhật sản phẩm
    $scope.update = function() {

        Swal.fire({
            title: 'Are you sure?',
            text: "Thay đổi thông tin sản phẩm",
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
                $http.put(`/rest/products/${item.id}`, item).then(resp => {

                    //Tìm trong product id == id resp trả về => thay đổ i
                    var index = $scope.items.findIndex(p => p.id == item.id);
                    $scope.items[index] = item;
                    Swal.fire(
                        'Thành công',
                        'Cập nhật sản phẩm thành công',
                        'success'
                    )
                    $scope.reset();
                    $scope.initialize();
                }).catch(error => {
                    console.log("Error", error);
                    Swal.fire(
                        'Opps...!',
                        'Không thể cập nhật sản phẩm',
                        'error'
                    )
                })
            }
        })



    }

    //Xóa sản phẩm
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
                $http.delete(`/rest/products/${item.id}`).then(resp => {

                    //Tìm trong product id == id resp trả về => =xóa
                    var index = $scope.items.findIndex(p => p.id == item.id);
                    $scope.items.splice(index, 1);
                    $scope.reset();
                    Swal.fire(
                        'Thành công',
                        'Xóa sản phẩm thành công',
                        'success'
                    )
                    $scope.reset();
                    $scope.initialize();
                }).catch(error => {
                    // console.log("Error", error);
                    Swal.fire(
                        'Opps...!',
                        'Không thể xóa sản phẩm',
                        'info'
                    )
                })
            }
        })
    }

    $scope.pager = {
        page: 0,
        size: 7,
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

    //Khởi tạo
    $scope.initialize();
    $scope.reset();
});