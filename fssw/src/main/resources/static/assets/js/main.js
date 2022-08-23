$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if (text === 'Edit') {
            $.get(href, function (comments, status) {
                $('.myForm #content').val(comments.text);

            });
            $('.myForm #exampleModal').modal();
        }

    });

    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModal #delRef').attr('href', href);
        $('#myModal').modal();
    });
});