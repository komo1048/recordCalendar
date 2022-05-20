jQuery(function ($) {
    $(".sidebar-dropdown > a").click(function() {
        $(".sidebar-submenu").slideUp(200);
        if ($(this).parent().hasClass("active")) {
            $(".sidebar-dropdown").removeClass("active");
            $(this).parent().removeClass("active");
        } else {
            $(".sidebar-dropdown").removeClass("active");
            $(this).next(".sidebar-submenu").slideDown(200);
            $(this).parent().addClass("active");
        }
    });

    $("#close-sidebar").click(function() {
        $(".page-wrapper").removeClass("toggled");
    });
    $("#show-sidebar").click(function() {
        $(".page-wrapper").addClass("toggled");
    });

    profileData.initFn();
});

let profileData = {
    initFn : function(){
        $('#modifybutton').click(function(){
            Swal.fire({
                text: '비밀번호를 입력해주세요.',
                input: 'password',
                inputAttributes: {
                    autocapitalize: 'off'
                },
                showCancelButton: true,
                confirmButtonText: '입력',
                showLoaderOnConfirm: true,
                preConfirm: (login) => {
                    let params = {
                        password : login
                    }
                    post('/member/passCheck', params, function(result){
                        let $table = $('#userInfoTable').find('tr td');
                        if(result>0){
                            $('#userInfoModal').modal('show');
                            $('#modalId').val($table[0].innerHTML);
                            $('#modalUserName').val($('#username').text());
                            $('#modalLocation').val($table[1].innerHTML);
                            $('#modalJob').val($table[3].innerHTML);
                            $('#modalPhone').val($table[4].innerHTML);
                            $('#modalEmail').val($table[5].innerHTML);
                        }else{
                            alertModal('비밀번호를 다시 확인해주세요.', 'error', 'e');
                        }

                    })
                },
                allowOutsideClick: () => !Swal.isLoading()
            })
        }),

        $('#closeBtn').click(function(){
            $('#userInfoModal').modal('hide');
        }),

        $('#userModifyBtn').click(function(){
            let params = $("#userForm").serializeObject();
            params.pwdFlag = 0;
            if($('#password').val() != null && $('#password').val() != ""){
                params.pwdFlag = 1;
            }
            post('/member/modifyProfile', params, function(result){
                if(result > 0){
                    alertModal('수정이 완료 되었습니다.', 'success', 's');
                }else{
                    alertModal('수정에 실패 하였습니다.', 'error', 'e');
                }
            })
        })

    }
}

