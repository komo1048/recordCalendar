$(document).ready(function(){
    login.initFn();

})

let login = {
    initFn : function(){
        $('#loginBtn').click(function(){
            let params = {
                id : $('#id').val(),
                password : $('#pwd').val()
            }
            post('/member/login', params, function(result){
                if(result <= 0){
                    alertModal("아이디 혹은 비밀번호를 확인해주세요.", "error", "e");
                }else{
                    location.href="/calendar";
                }
            })

        });

        $('#registerBtn').click(function(){
            let params = {
                id : $('#id').val(),
                password : $('#password').val()
            }
            post('/member/register', params, function(result){
				if(result > 0){
					alertModal("회원가입에 성공했습니다.", "success", 's');
					setTimeout(function(){document.location.href="/";}, 1500);
				}else{
					alertModal("회원가입에 실패했습니다.", "error", 'e');
					setTimeout(function(){location.href="/register";}, 1500);
				}
            })
        });

        $('#logoutBtn').click(function(){
            Swal.fire({
              title: '로그아웃 하시겠습니까?',
              icon: 'warning',
              showCancelButton: true,
              confirmButtonColor: '#3085d6',
              cancelButtonColor: '#d33',
              confirmButtonText: 'Logout'
            }).then((result) => {
                if (result.isConfirmed) {
                   location.href="/member/logout";
                }
            })
        });

        $('#resetPwd').click(function(){
            let params = {
                id : $('#id').val(),
                uid : getUid()
            }
            Swal.fire({
                icon: 'success',
                text: '임시비밀번호는 '+ params.uid +'입니다.',
                showConfirmButton : true
            }).then(function(){
                $('#pwd').val(params.uid);
            });
        });

        $('#updateTempPwd').click(function(){
            let params = {
                id : $('#id').val(),
                password : $('#pwd').val()
            }

            post('/updateTempPwd',params, function(result){
                if(result > 0){
                    Swal.fire({
                        icon: 'success',
                        text: '비밀번호 초기화가 되었습니다',
                        showConfirmButton : true
                    }).then(function(){
                        location.href="/";
                    });
                }else{
                    alertModal('비밀번호 초기화에 실패했습니다.', 'error', 'e');
                }
            })

        })
    }
}

function checkId(){
    let params = {
        id : $('#id').val()
    }
    post('/checkId', params, function(data){
        if(params == "" && data == '0'){
            $('#id').css("background-color", "#FFCECE");
        }else if(data=='0'){
            $('#id').css("background-color", "#B0F6AC");
        }else if(data=='1'){
            $('#id').css("background-color", "#FFCECE");
        }
    })
}

function checkPwd(){
    let pwd = $('#password').val();
    let pwdCheck = $('#confirm_password').val();

    if(pwdCheck !== pwd){
        $('#confirm_password').css("background-color", "#FFCECE");
    }else if(pwdCheck === pwd){
        $('#confirm_password').css("background-color", "#B0F6AC");
    }
}

function getUid() {
    function s4() {
        return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
    }
    return s4();
}
