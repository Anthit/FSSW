$("#com-btn-save").click(function () {
    let data = {
        "boardId": $("#boardId").val(),
        "contentvalue": $("#com_content").val(),
        "reCommentGroup": $("#reComment-group").val(),
        "reCommentFloor": $("#reComment-floor").val(),
        "reCommentOrder": $("#reComment-order").val()
    };

    if($("#com_content").val() == ""){
        alert("댓글을 입력하세요!");
        $("#com_content").focus();
        return false;
    }

    $.ajax({
        type: "POST",
        url: '/community/{id}/comDetail/',
        data: JSON.stringify(data),
        contentType: "application/json; charset=uft-8",
        dataType: "text",

        success: function(parameter) {
            location.reload();
        },
        error: function (e) {
            alert("error");
        }
    })


})

$("#reComment-save").click(function (){
    let reData ={
        "re-boardId": $("#re-boardId").val(),
        "reComment-text": $("#reComment-text").val(),
        "ano-reComment-floor": $("#ano-reComment-floor").val(),
        "ano-reComment-order": $("#ano-reComment-order").val(),
        "ano-reComment-group": $("#ano-reComment-group").val()

    };

    //유효성 검사하는곳.
    $("#reComment_Close").click(function (){
        document.getElementById("reComment_valid").innerHTML="";
    });
    if($("#reComment-text").val() == ""){
        document.getElementById("reComment_valid").innerHTML="댓글을 입력해주세요";
        $("#reComment-text").focus();
        return false;
    }
    //


    $.ajax({
        type: "POST",
        url: '/community/{id}/comDetail/reComment',
        data: JSON.stringify(reData),
        contentType: "application/json; charset=uft-8",
        dataType: "text",

        success: function(parameter) {
            location.reload();
        },
        error: function (e) {
            alert("error");
        }
    })

})