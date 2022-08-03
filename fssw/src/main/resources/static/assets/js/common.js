$(document).ready(function(){
    console.log($(location)/*.attr("pathname")*/);
    console.log($(location).attr("pathname"));
    console.log($(location).attr("search"));
    var pathname = $(location).attr("pathname");
    // sessionStorage.removeItem("redirectPath");
    // sessionStorage.setItem("redirectPath",
    //     $(location).attr("pathname") +
    //     ($(location).attr("search") != '') ? "/" + $(location).attr("search") : '');
    if( pathname == '/main' ) {
        $("#topSpan").append("FSSW");
    }
    else if( pathname == '/community' ) {
        $("#topSpan").append("일반 게시판");
    }
    else if( pathname == '/curriculum' ) {
        $("#topSpan").append("학습 과정");
    }
    else if( pathname == '/faq' ) {
        $("#topSpan").append("자주 묻는 질문");
    }
    else if( pathname == '/findteam' ) {
        $("#topSpan").append("팀원 구하기");
    }
    else if( pathname == '/hotKeyword' ) {
        $("#topSpan").append("뜨거운 이슈");
    }
    else if( pathname == '/login' ) {
        $("#topSpan").append("로그인");
    }
    else if( pathname == '/roadmap' ) {
        $("#topSpan").append("선배의 눈높이 교육");
    }
    else if( pathname == '/signup' ) {
        $("#topSpan").append("회원가입");
    }
    else if( pathname.toString().includes("/searchKeyword") ) {
        $("#topSpan").append("연관 키워드 검색");
    }
});



// $("#topSpan").click(function () {
//     location.href = sessionStorage.getItem("redirectPath");
// });