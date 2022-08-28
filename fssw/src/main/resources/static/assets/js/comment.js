$("#com-btn-save").click(function () {
    let data = {
        "boardId": $("#boardId").val(),
        "contentvalue": $("#com-content").val(),
        "reCommentGroup": $("#reComment-group").val(),
        "reCommentFloor": $("#reComment-floor").val(),
        "reCommentOrder": $("#reComment-order").val()
    };

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


    }

})