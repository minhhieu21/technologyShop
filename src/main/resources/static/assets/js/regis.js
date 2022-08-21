var app = angular.module("Myapp", [])
app.controller("account-ctrl", function($scope, $http) {
    $scope.form = {};
    //Thêm mới sản phẩm
    $scope.create = function() {
        //Lấy dữ liệu từ form
        var item = angular.copy($scope.form);

        //Post dữ liệu lên
        $http.post(`/rest/registration`, item).then(resp => {

            //bỏ vào list product
            $scope.items.push(resp.data);

            Swal.fire(
                    'Thành công',
                    'Đăng kí tài khoản thành công ',
                    'success'
                )
                //reset form

        }).catch(error => {
            Swal.fire(
                'Opps...!',
                'Đăng kí tài khoản thất bại ',
                'error'
            )
            console.log("Error", error);
        })
    }
});