$("#com-btn-save").click(function () {
    let data = {
        "boardId": $("#boardId").val(),
        "content": $("#com-content").val()
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