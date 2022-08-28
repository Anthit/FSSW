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
        "re-boardId": $("#re-boardId").val(),
        "reComment-text": $("#reComment-text").val(),
        "ano-reComment-floor": $("#ano-reComment-floor").val(),
        "ano-reComment-order": $("#ano-reComment-order").val(),
        "ano-reComment-group": $("#ano-reComment-group").val()

    };

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