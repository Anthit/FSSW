function joinform_check() {
    var email_id = document.getElementById("email_id");
    var pwd = document.getElementById("password");
    var repwd = document.getElementById("repwd");
    var nick = document.getElementById("nick");

    if (email_id == "") {
        alert("이메일을 입력하세요");
        email_id.focus(); //커서 깜빡이기
        return false; //아무것도 반환하지 말아라 아래 코드부터 아무것도 진행하지 말것 이란뜻
    }
    ;
    if (pwd.value == "") {
        alert("비밀번호를 입력하세요.");
        pwd.focus();
        return false;
    }
    var pwd_check = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;

    if (!pwd_check.test(pwd.value)) {
        alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야 합니다");
        pwd.focus();
        return false;
    }
    document.join_form.submit();
}

function nick_check(){
    var data={
        "nick" : $("#nick").val()
    };

    $.ajax({
        async: true,
        type: "POST",
        data: {
            email: $("#nick").val()
        },
        url :"/signupNick",
        dataType:'json' ,
        contentType: "application/json; charset=UTF-8",
        success: function(count){
            if(count==1){
                $("#nick_check").html("중복된 닉네임이 있습니다");
            }else{
                $("#nick_check").html("");
            }
        }
    });
}

function email_check(){
    alert("됐나요?");
    var data=
        {
            "email_id" : $("#email_id").val()
        };

    $.ajax({
        async: true ,
        type : "POST",
        data : JSON.stringify(data),
        url:"/signupEmail",
        dataType : 'text',
        contentType: "application/json; charset=UTF-8",
        success: function(count){
            alert("됐음")
            if(count==1){
                $("#email_check").html("중복된 이메일이 있습니다");
            }else{
                $("#email_check").html("");
            }
        },
        error: function(msg){
            alert("error");
        }
    });
    //success는 ajax가 실행됐을때 실행되는 함수의 반환을 매개변수로 받는다.
}