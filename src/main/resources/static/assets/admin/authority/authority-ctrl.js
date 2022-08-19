app.controller("authority-ctrl", function($scope, $http, $location) {
    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];

    $scope.initialize = function() {
        //Load all roles
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        })

        //Load staff and directors (admin)
        $http.get("/rest/accounts?admin=true").then(resp => {
            $scope.admins = resp.data;
        })

        //Load authorities of staff and director
        //Tải các quyền đã được cấp của admin
        $http.get("/rest/authorities?admin=true").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            $location.path("/unauthorized")
        })
    }


    $scope.authority_of = function(acc, role) {
        if ($scope.authorities) {
            return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
        }
    }

    $scope.authority_changed = function(acc, role) {
        //Tìm authority
        var authority = $scope.authority_of(acc, role);

        if (authority) //đã cấp quyề => thu hồi quyền (xóa)
        {
            $scope.revoke_authority(authority);
        } else { //chưa được cấp quyền => cấp quyền (thêm mới)
            authority = { account: acc, role: role };
            $scope.grant_authority(authority);
        }
    }



    //thêm mới authority
    $scope.grant_authority = function(authority) {
        $http.post('/rest/authorities', authority).then(resp => {
            $scope.authorities.push(resp.data)
            Swal.fire(
                'Thành công',
                'Cấp quyền sử dụng thành công',
                'success'
            )
        }).catch(error => {
            Swal.fire(
                'Opps...!',
                'Cấp quyền sử dụng thất bại',
                'error'
            )
            console.log(error);
        })
    }

    //Xóa authoriti
    $scope.revoke_authority = function(authority) {
        $http.delete(`/rest/authorities/${authority.id}`).then(resp => {
            var index = $scope.authorities.findIndex(a => a.id == authority.id);
            $scope.authorities.splice(index, 1);
            Swal.fire(
                'Thông báo !',
                'Thu hồi quyền sử dụng thành công',
                'info'
            )

        }).catch(error => {
            Swal.fire(
                'Opps...!',
                'Thu hồi quyền sử dụng thất bại',
                'error'
            )
            console.log(error);
        })
    }
    $scope.initialize();
});